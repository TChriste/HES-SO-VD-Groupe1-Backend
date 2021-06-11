package org.acme.rest.client.ressource;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.DemandeDeBilan;
import org.acme.rest.client.domain.ListeAttente;
import org.acme.rest.client.dto.ListeAttenteVuePatientDto;
import org.acme.rest.client.dto.PageListeAttenteDto;
import org.acme.rest.client.mapper.ListeAttenteVuePatientMapper;
import org.acme.rest.client.repository.ListeAttenteRepository;

@Path("/liste-attente")
public class ListeAttenteRessource {

  @Inject
  ListeAttenteRepository listeAttenteRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public PageListeAttenteDto getListesAttentes(@QueryParam("page") int page,
                                               @QueryParam("region") Long regionId,
                                               @QueryParam("specialisation") Long specialisationId) {

    String query = "select liste from ListeAttente as liste "
        + "left join liste.logopediste as logo "
        + "left join logo.specialisations as spec ";
    PanacheQuery<ListeAttente> listeAttentePanacheQuery;
    if (regionId != null && specialisationId != null) {
      listeAttentePanacheQuery = listeAttenteRepository.find(query + " where logo.localite.region.id = ?1 and spec.id = ?2", regionId, specialisationId);
    } else if (regionId != null) {
      listeAttentePanacheQuery = listeAttenteRepository.find(query + " where logo.localite.region.id = ?1", regionId);
    } else if (specialisationId != null) {
      listeAttentePanacheQuery = listeAttenteRepository.find(query + " where spec.id = ?1", specialisationId);
    } else {
      listeAttentePanacheQuery = listeAttenteRepository.findAll();
    }
    listeAttentePanacheQuery.page(Page.of(page, 15));
    ListeAttenteVuePatientMapper dtoMapper = new ListeAttenteVuePatientMapper();

    return new PageListeAttenteDto(
        listeAttentePanacheQuery.list().stream().map(dtoMapper::enDto).collect(Collectors.toList()),
        listeAttentePanacheQuery.pageCount(),
        listeAttentePanacheQuery.count()
    );
  }
}
