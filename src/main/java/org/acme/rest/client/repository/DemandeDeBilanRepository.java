package org.acme.rest.client.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.acme.rest.client.domain.DemandeDeBilan;

@ApplicationScoped
public class DemandeDeBilanRepository implements PanacheRepository<DemandeDeBilan> {
}
