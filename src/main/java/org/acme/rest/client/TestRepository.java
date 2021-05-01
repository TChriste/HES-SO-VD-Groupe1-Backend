package org.acme.rest.client;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestRepository implements PanacheRepository<Test> {

  public Test findByNom(String nom){
    return find("nom", nom).firstResult();
  }

}
