package org.acme.rest.client.ressource;

import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Patient;
import org.acme.rest.client.dto.SignInPatientDto;
import org.acme.rest.client.dto.SignUpPatientDto;
import org.acme.rest.client.dto.UserDto;
import org.acme.rest.client.mapper.PatientMapper;
import org.acme.rest.client.repository.PatientRepository;

@Path("/patient")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;

    @POST
    @Path("/sign-in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto signIn(SignInPatientDto dto) {
        Optional<Patient> patient = this.patientRepository.findByEmail(dto.getEmail());
        if (patient.isPresent()) {
            if (patient.get().getMotDePasse().equals(dto.getPassword())) {
                return new UserDto(patient.get().getId(),
                                   patient.get().getEmail(),
                                   patient.get().getNom(),
                                   patient.get().getPrenom());
            }
        }
        throw new NotAuthorizedException("Nom d'utilisateur ou mot de passe incorrect !");
    }

    @POST
    @Path("/sign-up")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Patient signUp(SignUpPatientDto dto) {
        PatientMapper patientMapper = new PatientMapper();
        Patient patient = patientMapper.enDomaine(dto);
        patientRepository.persist(patient);
        return patient;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") Long id) {
        return patientRepository.getPatientById(id);
    }

}
