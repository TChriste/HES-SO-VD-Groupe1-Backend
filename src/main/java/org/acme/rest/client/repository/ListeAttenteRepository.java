package org.acme.rest.client.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.acme.rest.client.domain.ListeAttente;

@ApplicationScoped
public class ListeAttenteRepository implements PanacheRepository<ListeAttente> {
}
