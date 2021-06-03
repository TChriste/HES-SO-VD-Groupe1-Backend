package org.acme.rest.client.ressource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.rest.client.dto.StatisticsGeneral;
import org.acme.rest.client.repository.DemandeDeBilanRepository;
import org.acme.rest.client.repository.LogopedisteRepository;
import org.acme.rest.client.repository.PatientRepository;

@Path("/statistiques")
public class StatsGeneralesRessources {

  @Inject
  PatientRepository patientRepository;

  @Inject
  LogopedisteRepository logopedisteRepository;

  @Inject
  DemandeDeBilanRepository demandeDeBilanRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public StatisticsGeneral getStatistics() {
    StatisticsGeneral statisticsGeneral = new StatisticsGeneral();
    statisticsGeneral.setNbLogoInscrits(this.logopedisteRepository.count());
    statisticsGeneral.setNbPatientsInscrits(this.patientRepository.count());
    statisticsGeneral.setNbDemandesDeBilans(this.demandeDeBilanRepository.count());
    return statisticsGeneral;
  }
}
