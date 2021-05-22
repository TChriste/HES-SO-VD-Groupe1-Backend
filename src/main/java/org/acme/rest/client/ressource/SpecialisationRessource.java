package org.acme.rest.client.ressource;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Specialisation;
import org.acme.rest.client.repository.SpecialisationRepository;

@Path("/specialisations")
public class SpecialisationRessource {

  @Inject
  SpecialisationRepository specialisationRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Specialisation> getSpecialisations() {
    return specialisationRepository.listAll();
  }

}
