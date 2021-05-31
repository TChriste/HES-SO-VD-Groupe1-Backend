package org.acme.rest.client.dto;

import java.time.LocalDate;

public class SignUpPatientDto {
  public String nom;
  public String prenom;
  public LocalDate dateNaissance;
  public String rue;
  public String npa;
  public String localite;
  public EcoleDto ecole;
  public String degreScolaire;
  public String assurance;
  public String numeroAVS;
  public String representantLegalNom;
  public String representantLegalPrenom;
  public String email;
  public String password;

  public SignUpPatientDto() {
  }

  public SignUpPatientDto(String nom, String prenom, LocalDate dateNaissance, String rue,
      String npa, String localite, EcoleDto ecole, String degreScolaire, String assurance,
      String numeroAVS, String representantLegalNom, String representantLegalPrenom,
      String email) {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
    this.rue = rue;
    this.npa = npa;
    this.localite = localite;
    this.ecole = ecole;
    this.degreScolaire = degreScolaire;
    this.assurance = assurance;
    this.numeroAVS = numeroAVS;
    this.representantLegalNom = representantLegalNom;
    this.representantLegalPrenom = representantLegalPrenom;
    this.email = email;
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

  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public String getRue() {
    return rue;
  }

  public void setRue(String rue) {
    this.rue = rue;
  }

  public String getNpa() {
    return npa;
  }

  public void setNpa(String npa) {
    this.npa = npa;
  }

  public String getLocalite() {
    return localite;
  }

  public void setLocalite(String localite) {
    this.localite = localite;
  }

  public EcoleDto getEcole() {
    return ecole;
  }

  public void setEcole(EcoleDto ecole) {
    this.ecole = ecole;
  }

  public String getDegreScolaire() {
    return degreScolaire;
  }

  public void setDegreScolaire(String degreScolaire) {
    this.degreScolaire = degreScolaire;
  }

  public String getAssurance() {
    return assurance;
  }

  public void setAssurance(String assurance) {
    this.assurance = assurance;
  }

  public String getNumeroAVS() {
    return numeroAVS;
  }

  public void setNumeroAVS(String numeroAVS) {
    this.numeroAVS = numeroAVS;
  }

  public String getRepresentantLegalNom() {
    return representantLegalNom;
  }

  public void setRepresentantLegalNom(String representantLegalNom) {
    this.representantLegalNom = representantLegalNom;
  }

  public String getRepresentantLegalPrenom() {
    return representantLegalPrenom;
  }

  public void setRepresentantLegalPrenom(String representantLegalPrenom) {
    this.representantLegalPrenom = representantLegalPrenom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
