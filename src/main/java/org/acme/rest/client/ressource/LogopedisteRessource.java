package org.acme.rest.client.ressource;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.domain.Logopediste;
import org.acme.rest.client.dto.SignInLogoDto;
import org.acme.rest.client.dto.UserDto;
import org.acme.rest.client.repository.LogopedisteRepository;

@Path("/logopediste")
public class LogopedisteRessource {

  @Inject
  LogopedisteRepository logopedisteRepository;

  @POST
  @Path("/sign-in")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public UserDto signIn(SignInLogoDto dto) {
    Optional<Logopediste> logopediste = this.logopedisteRepository.findByNumPrestataire(dto.getNumPrestataire());
    if (logopediste.isPresent()) {
      if (logopediste.get().getMotDePasse().equals(dto.getPassword())) {
        return new UserDto(logopediste.get().getId(),
            null,
            logopediste.get().getNumeroConcordat(),
            logopediste.get().getNom(),
            logopediste.get().getPrenom());
      }
    }
    throw new NotAuthorizedException("Nom d'utilisateur ou mot de passe incorrect !");
  }

}
