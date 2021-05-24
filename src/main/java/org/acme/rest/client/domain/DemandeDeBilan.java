package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

  @Enumerated(EnumType.STRING)
  @Column(name = "statut")
  private DemandeStatut statut;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "origine")
  private DemandeOrigine origine;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<ListeAttente> listeAttentes = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL)
  private List<Disponibilite> disponibilites = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL)
  private Patient patient;

  public void addListeAttente(ListeAttente listeAttente) {
    listeAttente.getDemandeDeBilans().add(this);
    this.getListeAttentes().add(listeAttente);
  }

  public void addDisponibilite(Disponibilite disponibilite) {
    this.getDisponibilites().add(disponibilite);
  }
}
