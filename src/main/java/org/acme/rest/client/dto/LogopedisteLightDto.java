package org.acme.rest.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogopedisteLightDto {
  private Long id;
  private String nom;
  private String prenom;
  private String rue;
  private String npa;
  private String localite;
  private List<SpecialisationDto> specialisations;
}
