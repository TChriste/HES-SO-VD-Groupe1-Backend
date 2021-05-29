package org.acme.rest.client.repository;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.rest.client.domain.Logopediste;

@ApplicationScoped
public class LogopedisteRepository implements PanacheRepository<Logopediste>{
  public Optional<Logopediste> findByNumPrestataire(String numPrestataire) {
    return find("numeroConcordat", numPrestataire).singleResultOptional();
  }

  public Logopediste getLogopedisteById(Long id){
    return findById(id);
  }

}
