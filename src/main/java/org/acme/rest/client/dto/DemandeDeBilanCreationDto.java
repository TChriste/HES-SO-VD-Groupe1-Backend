package org.acme.rest.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.rest.client.domain.DemandeOrigine;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDeBilanCreationDto {
  public Long idPatient;
  public List<Long> idsListesAttente;
  public List<Long> idsDisponibilites;
  public String description;
  public DemandeOrigine origine;
}
