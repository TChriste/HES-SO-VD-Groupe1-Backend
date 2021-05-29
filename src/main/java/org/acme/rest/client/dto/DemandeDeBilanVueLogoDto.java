package org.acme.rest.client.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.rest.client.domain.DemandeOrigine;
import org.acme.rest.client.domain.DemandeStatut;
import org.acme.rest.client.domain.Disponibilite;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDeBilanVueLogoDto {
  public Long id;
  public LocalDate date;
  public DemandeStatut statut;
  public String description;
  public InfoPatientVueLogoDto patient;
  public List<Disponibilite> disponibilites;
  public DemandeOrigine origine;
}
