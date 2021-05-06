package org.acme.rest.client;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {

  public Patient getPatientById(Long id){
    return findById(id);
  }

}
