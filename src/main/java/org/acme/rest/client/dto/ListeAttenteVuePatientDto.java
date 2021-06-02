package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListeAttenteVuePatientDto {
  private Long id;
  private LogopedisteLightDto logopediste;
  private Long nbDemandesEnAttente;
  private Long dureeAttenteEstimee;
}
