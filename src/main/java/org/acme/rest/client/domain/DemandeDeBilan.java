package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "DemandeDeBilan")
@Table(name = "DEMANDE_DE_BILAN")
@Data
public class DemandeDeBilan {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "statut")
  private DemandeStatut statut;

  @Column(name = "description")
  private String description;

  @Column(name = "origine")
  private DemandeOrigine origine;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<ListeAttente> listeAttentes;

  @OneToMany
  private List<Disponibilite> disponibilites;

}
