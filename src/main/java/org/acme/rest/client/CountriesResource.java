package org.acme.rest.client;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class CountriesResource {

    @Inject
    TestRepository testRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Test> name() {
        List<Test> test = testRepository.listAll();
        return test;
    }

}
