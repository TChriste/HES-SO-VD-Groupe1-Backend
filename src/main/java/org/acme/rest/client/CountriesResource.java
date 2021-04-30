package org.acme.rest.client;

import java.util.Set;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Uni;

@Path("/test")
public class CountriesResource {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Country name(@PathParam String name) {
        return new Country(name);
    }

}
