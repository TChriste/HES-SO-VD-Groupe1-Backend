package org.acme.rest.client.ressource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Patient;
import org.acme.rest.client.repository.PatientRepository;

@Path("/patient")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") Long id) {
        return patientRepository.getPatientById(id);
    }

}
