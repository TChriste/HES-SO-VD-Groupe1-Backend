package org.acme.rest.client.ressource;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Patient;
import org.acme.rest.client.dto.LoginPatientDto;
import org.acme.rest.client.dto.UserDto;
import org.acme.rest.client.repository.PatientRepository;

@Path("/patient")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto login(LoginPatientDto loginInfos) {
        Optional<Patient> patient = this.patientRepository.findByEmail(loginInfos.getEmail());
        if (patient.isPresent()) {
            if (patient.get().getMotDePasse().equals(loginInfos.getPassword())) {
                return new UserDto(patient.get().getId(),
                                   patient.get().getEmail(),
                                   patient.get().getNom(),
                                   patient.get().getPrenom());
            }
        }
        throw new NotAuthorizedException("Error");
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") Long id) {
        return patientRepository.getPatientById(id);
    }

}
