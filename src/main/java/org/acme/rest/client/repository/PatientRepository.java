package org.acme.rest.client.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import org.acme.rest.client.domain.Patient;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {

  public Optional<Patient> findByEmail(String email) {
    return find("email", email).singleResultOptional();
  }

  public Patient getPatientById(Long id){
    return findById(id);
  }

}
