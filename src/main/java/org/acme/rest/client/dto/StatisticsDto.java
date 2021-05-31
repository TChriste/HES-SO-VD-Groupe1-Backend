package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {
  public Long nbPatientsEnListeAttente;
  public Long nbPatientsAcceptes;
  public Long nbPatientsRefuses;
  public Long dureeAttenteEstimee;
}
