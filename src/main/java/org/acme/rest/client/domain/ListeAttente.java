package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "ListeAttente")
@Table(name="LISTE_ATTENTE")
@Data
public class ListeAttente {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @ManyToMany(mappedBy = "listeAttentes")
  private List<DemandeDeBilan> demandeDeBilans = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "logopediste_id")
  private Logopediste logopediste;

}
