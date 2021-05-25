package org.acme.rest.client.ressource;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Region;
import org.acme.rest.client.repository.RegionRepository;

@Path("/regions")
public class RegionRessource {

  @Inject
  RegionRepository regionRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Region> getRegions() {
    return regionRepository.listAll();
  }

}
