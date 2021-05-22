package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Logopediste")
@Table(name = "LOGOPEDISTE")
@Data
public class Logopediste{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "numeroConcordat")
    private String numeroConcordat;

    @Column(name = "motDePasse")
    private String motDePasse;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "rue")
    private String rue;

    @ManyToOne(optional = false)
    private Localite localite;

    @OneToMany
    private List<Specialisation> specialisations;

    @OneToOne(mappedBy = "logopediste")
    public ListeAttente listeAttente;
}
