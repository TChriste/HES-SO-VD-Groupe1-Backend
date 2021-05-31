package org.acme.rest.client.mapper;

import java.util.ArrayList;
import java.util.List;
import org.acme.rest.client.domain.DegreScolaire;
import org.acme.rest.client.domain.Ecole;
import org.acme.rest.client.domain.Localite;
import org.acme.rest.client.domain.Patient;
import org.acme.rest.client.domain.RepresentantLegal;
import org.acme.rest.client.dto.SignUpPatientDto;

public class PatientMapper {

  public Patient enDomaine(SignUpPatientDto dto) {

    Patient patient = new Patient();
    patient.setNom(dto.getNom());
    patient.setPrenom(dto.getPrenom());
    patient.setDateNaissance(dto.getDateNaissance());
    patient.setRue(dto.getRue());
    patient.setAssurance(dto.getAssurance());
    patient.setNumeroAVS(dto.getNumeroAVS());
    patient.setEmail(dto.getEmail());
    patient.setMotDePasse(dto.getPassword());

    Localite localite = new Localite();
    localite.setNom(dto.getLocalite());
    localite.setNpa(dto.getNpa());
    patient.setLocalite(localite);

    Ecole ecole = new Ecole();
    ecole.setId(dto.getEcole().id);
    ecole.setNom(dto.getEcole().getNom());
    List<DegreScolaire> degreScolaireList = new ArrayList<>();
    dto.getEcole().getDegresScolaire().forEach(degresScolaireDto -> {

      if (degresScolaireDto.getLibelle().equals(dto.getDegreScolaire())) {
        DegreScolaire degreScolaire = new DegreScolaire();
        degreScolaire.setId(degresScolaireDto.getId());
        degreScolaire.setId(degresScolaireDto.getId());
        degreScolaire.setLibelle(degresScolaireDto.getLibelle());
        patient.setDegreScolaire(degreScolaire);
      }
    });
    ecole.setDegresScolaire(degreScolaireList);
    patient.setEcole(ecole);

    RepresentantLegal representantLegal = new RepresentantLegal();
    representantLegal.setNom(dto.getRepresentantLegalNom());
    representantLegal.setPrenom(dto.getRepresentantLegalPrenom());
    patient.setRepresentantLegal(representantLegal);

    return patient;
  }


}
