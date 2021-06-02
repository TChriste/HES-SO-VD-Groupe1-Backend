package org.acme.rest.client.ressource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.DemandeDeBilan;
import org.acme.rest.client.domain.DemandeStatut;
import org.acme.rest.client.domain.ListeAttente;
import org.acme.rest.client.domain.Logopediste;
import org.acme.rest.client.dto.ListeAttenteVueLogoDto;
import org.acme.rest.client.dto.ListeAttenteVuePatientDto;
import org.acme.rest.client.dto.SignInLogoDto;
import org.acme.rest.client.dto.StatisticsDto;
import org.acme.rest.client.dto.UserDto;
import org.acme.rest.client.mapper.ListeAttenteVueLogoMapper;
import org.acme.rest.client.repository.DemandeDeBilanRepository;
import org.acme.rest.client.repository.ListeAttenteRepository;
import org.acme.rest.client.repository.LogopedisteRepository;

@Path("/logopediste")
public class LogopedisteRessource {

  private Long DUREE_MOYEN_EN_JOURS_POUR_PRENDRE_NOUVELLE_DEMANDE = 20L;

  @Inject
  LogopedisteRepository logopedisteRepository;

  @Inject
  ListeAttenteRepository listeAttenteRepository;

  @Inject
  DemandeDeBilanRepository demandeDeBilanRepository;

  @POST
  @Path("/sign-in")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public UserDto signIn(SignInLogoDto dto) {
    Optional<Logopediste> logopediste = this.logopedisteRepository.findByNumPrestataire(dto.getNumPrestataire());
    if (logopediste.isPresent()) {
      if (logopediste.get().getMotDePasse().equals(dto.getPassword())) {
        return new UserDto(logopediste.get().getId(),
            null,
            logopediste.get().getNumeroConcordat(),
            logopediste.get().getNom(),
            logopediste.get().getPrenom());
      }
    }
    throw new NotAuthorizedException("Nom d'utilisateur ou mot de passe incorrect !");
  }

  @GET()
  @Path("/{idLogo}/liste-attente")
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public ListeAttenteVueLogoDto getListesAttentesByIdLogo(@PathParam("idLogo") Long idLogo) {
    ListeAttenteVueLogoMapper dtoMapper = new ListeAttenteVueLogoMapper();
    return listeAttenteRepository.list("logopediste.id", idLogo).stream().map(dtoMapper::enDto).findFirst().get();
  }

  /**
   * Refuser une demande : Lorsqu'un logopédiste refuse une demande, la demande est supprimée de sa liste
   * Elle reste acvite sur les autres listes avec le statut "EN_ATTENTE"
   * @param idListeAttente : id de la liste d'attente du logopédiste
   * @param idDemandeBilan : id de la demande à refuser.
   */
  @PUT
  @Path("/liste-attente/{idListeAttente}/demande/{idDemandeBilan}/refuser")
  @Transactional
  public ListeAttenteVueLogoDto refuserDemande(@PathParam("idListeAttente") Long idListeAttente,
                                               @PathParam("idDemandeBilan") Long idDemandeBilan) {

    ListeAttente listeAttente = this.listeAttenteRepository.findById(idListeAttente);
    listeAttente.removeDemandeDeBilan(idDemandeBilan, idListeAttente);
    listeAttente.setNbDemandesRefusees(listeAttente.getNbDemandesRefusees() + 1);
    this.listeAttenteRepository.persist(listeAttente);

    DemandeDeBilan demandeDeBilan = this.demandeDeBilanRepository.findById(idDemandeBilan);
    if(demandeDeBilan.getListeAttentes().size() <= 0) {
      demandeDeBilan.setStatut(DemandeStatut.REFUSEE);
      this.demandeDeBilanRepository.persist(demandeDeBilan);
    }

    ListeAttenteVueLogoMapper dtoMapper = new ListeAttenteVueLogoMapper();
    return dtoMapper.enDto(listeAttente);
  }


  /**
   * Accepter une demande :
   * @param idListeAttente : id de la liste d'attente du logopédiste
   * @param idDemandeBilan : id de la demande à accepter.
   */
  @PUT
  @Path("/liste-attente/{idListeAttente}/demande/{idDemandeBilan}/accepter")
  @Transactional
  public ListeAttenteVueLogoDto accepterDemande(@PathParam("idListeAttente") Long idListeAttente,
                                                @PathParam("idDemandeBilan") Long idDemandeBilan) {

    DemandeDeBilan demandeDeBilan = this.demandeDeBilanRepository.findById(idDemandeBilan);
    demandeDeBilan.getListeAttentes().forEach(listeAttente -> {
      if (!listeAttente.getId().equals(idListeAttente)) {
        listeAttente.removeDemandeDeBilan(demandeDeBilan.getId(),  listeAttente.getId());
      } else {
        listeAttente.setNbDemandesAcceptees(listeAttente.getNbDemandesAcceptees() + 1);
      }
    });

    if (demandeDeBilan.getListeAttentes().size() == 1) {
      demandeDeBilan.setStatut(DemandeStatut.ACCEPTEE);
    }
    this.demandeDeBilanRepository.persist(demandeDeBilan);

    ListeAttente listeAttente = this.listeAttenteRepository.findById(idListeAttente);
    ListeAttenteVueLogoMapper dtoMapper = new ListeAttenteVueLogoMapper();
    return dtoMapper.enDto(listeAttente);
  }

  @GET
  @Path("/{idLogo}/statistics")
  @Transactional
  public StatisticsDto getStatistics(@PathParam("idLogo") Long idLogo) {
    ListeAttente listeAttente = listeAttenteRepository.list("logopediste.id", idLogo).stream().findFirst().get();
    StatisticsDto statisticsDto = new StatisticsDto();
    statisticsDto.setNbPatientsEnListeAttente(listeAttente.getNbDemandesEnAttente());
    statisticsDto.setNbPatientsAcceptes(listeAttente.getNbDemandesAcceptees());
    statisticsDto.setNbPatientsRefuses(listeAttente.getNbDemandesRefusees());
    statisticsDto.setDureeAttenteEstimee(listeAttente.getDureeAttenteEstimee());
    return statisticsDto;
  }

}
