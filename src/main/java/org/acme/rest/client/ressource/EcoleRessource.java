package org.acme.rest.client.ressource;

import io.quarkus.panache.common.Sort;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Ecole;
import org.acme.rest.client.repository.EcoleRepository;

@Path("/ecoles")
public class EcoleRessource {

  @Inject
  EcoleRepository ecoleRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Ecole> getEcoles() {
    return ecoleRepository.listAll(Sort.by("nom"));
  }

}
