package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "RepresentantLegal")
@Table(name = "REPRESENTANT_LEGAL")
@Data
@NoArgsConstructor
public class RepresentantLegal {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "nom")
  private String nom;

  @Column(name = "prenom")
  private String prenom;

}
