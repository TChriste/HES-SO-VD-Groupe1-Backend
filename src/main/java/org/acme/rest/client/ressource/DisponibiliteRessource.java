package org.acme.rest.client.ressource;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Disponibilite;
import org.acme.rest.client.repository.DisponibiliteRepository;

@Path("/disponibilites")
public class DisponibiliteRessource {

  @Inject
  DisponibiliteRepository disponibiliteRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Disponibilite> getDisponibilites() {
    return disponibiliteRepository.listAll();
  }
}
