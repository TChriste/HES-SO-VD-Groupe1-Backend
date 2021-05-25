package org.acme.rest.client.ressource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.DemandeDeBilan;
import org.acme.rest.client.domain.DemandeStatut;
import org.acme.rest.client.domain.Disponibilite;
import org.acme.rest.client.domain.ListeAttente;
import org.acme.rest.client.domain.Patient;
import org.acme.rest.client.dto.DemandeDeBilanCreationDto;
import org.acme.rest.client.dto.DemandeDeBilanDto;
import org.acme.rest.client.dto.ListeAttenteDto;
import org.acme.rest.client.dto.LogopedisteLightDto;
import org.acme.rest.client.dto.SignInPatientDto;
import org.acme.rest.client.dto.SignUpPatientDto;
import org.acme.rest.client.dto.UserDto;
import org.acme.rest.client.mapper.PatientMapper;
import org.acme.rest.client.repository.DemandeDeBilanRepository;
import org.acme.rest.client.repository.DisponibiliteRepository;
import org.acme.rest.client.repository.ListeAttenteRepository;
import org.acme.rest.client.repository.PatientRepository;

@Path("/patient")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;
    @Inject
    DemandeDeBilanRepository demandeDeBilanRepository;
    @Inject
    ListeAttenteRepository listeAttenteRepository;
    @Inject
    DisponibiliteRepository disponibiliteRepository;

    @POST
    @Path("/sign-in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto signIn(SignInPatientDto dto) {
        Optional<Patient> patient = this.patientRepository.findByEmail(dto.getEmail());
        if (patient.isPresent()) {
            if (patient.get().getMotDePasse().equals(dto.getPassword())) {
                return new UserDto(patient.get().getId(),
                                   patient.get().getEmail(),
                                   patient.get().getNom(),
                                   patient.get().getPrenom());
            }
        }
        throw new NotAuthorizedException("Nom d'utilisateur ou mot de passe incorrect !");
    }

    @POST
    @Path("/sign-up")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Patient signUp(SignUpPatientDto dto) {
        PatientMapper patientMapper = new PatientMapper();
        Patient patient = patientMapper.enDomaine(dto);
        patientRepository.persist(patient);
        return patient;
    }

    @POST
    @Path("/demande-suivi")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public DemandeDeBilan addDemandeBilan(DemandeDeBilanCreationDto dto) {
        Patient patient = patientRepository.getPatientById(dto.getIdPatient());

        DemandeDeBilan demandeDeBilan = new DemandeDeBilan();
        demandeDeBilan.setPatient(patient);
        demandeDeBilan.setDate(LocalDate.now());
        demandeDeBilan.setStatut(DemandeStatut.EN_ATTENTE);
        demandeDeBilan.setDescription(dto.getDescription());
        demandeDeBilan.setOrigine(dto.getOrigine());

        dto.getIdsListesAttente().forEach(id -> {
            ListeAttente listeAttente = listeAttenteRepository.findById(id);
            demandeDeBilan.addListeAttente(listeAttente);
        });

        dto.getIdsDisponibilites().forEach(id -> {
            Disponibilite disponibilite = disponibiliteRepository.findById(id);
            demandeDeBilan.addDisponibilite(disponibilite);
        });

        demandeDeBilanRepository.persist(demandeDeBilan);

        return null;
    }

    @GET
    @Path("/{idPatient}/demandes-bilan")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<DemandeDeBilanDto> getDemandeDeBilans(@PathParam("idPatient") Long idPatient) {
        List<DemandeDeBilan> demandes = demandeDeBilanRepository.list("patient.id", idPatient);

        List<DemandeDeBilanDto> demandesDto = new ArrayList<>();
        demandes.forEach(demande -> {
            DemandeDeBilanDto demandeDto = new DemandeDeBilanDto();
            demandeDto.setId(demande.getId());
            demandeDto.setDate(demande.getDate());
            demandeDto.setStatut(demande.getStatut());
            demandeDto.setDescription(demande.getDescription());
            demandeDto.setDisponibilites(demande.getDisponibilites());

            List<ListeAttenteDto> listesAttenteDtos = new ArrayList<>();
            demande.getListeAttentes().forEach(liste -> {
                ListeAttenteDto listeAttenteDto = new ListeAttenteDto();
                listeAttenteDto.setId(liste.getId());

                LogopedisteLightDto logopedisteLightDto = new LogopedisteLightDto();
                logopedisteLightDto.setId(liste.getLogopediste().getId());
                logopedisteLightDto.setNom(liste.getLogopediste().getNom());
                logopedisteLightDto.setPrenom(liste.getLogopediste().getPrenom());

                listeAttenteDto.setLogopediste(logopedisteLightDto);
                listesAttenteDtos.add(listeAttenteDto);
            });
            demandeDto.setListesAttente(listesAttenteDtos);

            demandesDto.add(demandeDto);
        });

        return demandesDto;
    }



}
