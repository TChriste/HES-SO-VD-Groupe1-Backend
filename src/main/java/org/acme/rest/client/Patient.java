package org.acme.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "test")
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;
    @Column(name = "nom")
    public String nom;
    @Column(name = "prenom")
    public String prenom;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
