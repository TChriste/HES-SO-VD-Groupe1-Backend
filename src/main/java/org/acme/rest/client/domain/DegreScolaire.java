package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "DegreScolaire")
@Table(name = "DEGRE_SCOLAIRE")
@Data
public class DegreScolaire {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "libelle")
  private String libelle;

}
