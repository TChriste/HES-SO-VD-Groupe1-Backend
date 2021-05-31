package org.acme.rest.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "Patient")
@Table(name = "PATIENT")
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "assurance")
    private String assurance;

    @Column(name = "numeroAVS")
    private String numeroAVS;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    private String motDePasse;

    @Column(name = "rue")
    private String rue;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private RepresentantLegal representantLegal;

    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    private Ecole ecole;

    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    private DegreScolaire degreScolaire;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private Localite localite;
}
