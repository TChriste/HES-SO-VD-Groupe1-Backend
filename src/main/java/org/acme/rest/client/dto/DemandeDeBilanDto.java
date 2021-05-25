package org.acme.rest.client.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.rest.client.domain.DemandeStatut;
import org.acme.rest.client.domain.Disponibilite;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDeBilanDto {
  public Long id;
  public LocalDate date;
  public DemandeStatut statut;
  public String description;
  public List<ListeAttenteDto> listesAttente;
  public List<Disponibilite> disponibilites;
}
