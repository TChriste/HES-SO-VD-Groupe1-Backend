package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsGeneral {
  public Long nbLogoInscrits;
  public Long nbPatientsInscrits;
  public Long nbDemandesDeBilans;
}
