package org.acme.rest.client.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPatientVueLogoDto {
  private Long id;
  private String nom;
  private String prenom;
  private LocalDate dateNaissance;
  private String rue;
  private String npa;
  private String localite;
  private String ecole;
  private String degreScolaire;
  private String assurance;
  private String numAVS;
  private String representantLegalNom;
  private String representantLegalPrenom;
}
