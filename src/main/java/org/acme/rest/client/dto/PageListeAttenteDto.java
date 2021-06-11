package org.acme.rest.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageListeAttenteDto {
  private List<ListeAttenteVuePatientDto> listeAttente;
  private int nbPages;
  private Long nombreOccurences;
}
