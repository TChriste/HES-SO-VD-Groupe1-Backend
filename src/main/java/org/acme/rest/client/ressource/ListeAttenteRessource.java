package org.acme.rest.client.ressource;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.dto.ListeAttenteDto;
import org.acme.rest.client.mapper.ListeAttenteVuePatientMapper;
import org.acme.rest.client.repository.ListeAttenteRepository;

@Path("/liste-attente")
public class ListeAttenteRessource {

  @Inject
  ListeAttenteRepository listeAttenteRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ListeAttenteDto> getListesAttentes() {
    ListeAttenteVuePatientMapper dtoMapper = new ListeAttenteVuePatientMapper();
    return listeAttenteRepository.listAll().stream().map(dtoMapper::enDto).collect(Collectors.toList());
  }
}
