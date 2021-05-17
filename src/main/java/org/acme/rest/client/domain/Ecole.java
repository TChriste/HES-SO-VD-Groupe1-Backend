package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Ecole")
@Table(name = "ECOLE")
@Data
public class Ecole {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "nom")
  private String nom;

  @OneToMany
  private List<DegreScolaire> degresScolaire;

}
