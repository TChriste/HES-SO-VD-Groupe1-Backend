package org.acme.rest.client.mapper;

import java.util.ArrayList;
import java.util.List;
import org.acme.rest.client.domain.ListeAttente;
import org.acme.rest.client.dto.DemandeDeBilanVueLogoDto;
import org.acme.rest.client.dto.InfoPatientVueLogoDto;
import org.acme.rest.client.dto.ListeAttenteVueLogoDto;

public class ListeAttenteVueLogoMapper {
  public ListeAttenteVueLogoDto enDto(ListeAttente domaine) {
      ListeAttenteVueLogoDto listeAttenteVueLogoDto = new ListeAttenteVueLogoDto();
      listeAttenteVueLogoDto.setId(domaine.getId());

      List<DemandeDeBilanVueLogoDto> demandesDeBilanDto = new ArrayList<>();
      domaine.getDemandeDeBilans().forEach(demandeDeBilan -> {
        DemandeDeBilanVueLogoDto demandeDeBilanDto = new DemandeDeBilanVueLogoDto();
        demandeDeBilanDto.setId(demandeDeBilan.getId());
        demandeDeBilanDto.setDate(demandeDeBilan.getDate());
        demandeDeBilanDto.setStatut(demandeDeBilan.getStatut());
        demandeDeBilanDto.setDescription(demandeDeBilan.getDescription());
        demandeDeBilanDto.setOrigine(demandeDeBilan.getOrigine());
        demandeDeBilanDto.setDisponibilites(demandeDeBilan.getDisponibilites());

        InfoPatientVueLogoDto infoPatientDto = new InfoPatientVueLogoDto();
        infoPatientDto.setId(demandeDeBilan.getPatient().getId());
        infoPatientDto.setNom(demandeDeBilan.getPatient().getNom());
        infoPatientDto.setPrenom(demandeDeBilan.getPatient().getPrenom());
        infoPatientDto.setDateNaissance(demandeDeBilan.getPatient().getDateNaissance());
        infoPatientDto.setRue(demandeDeBilan.getPatient().getRue());
        infoPatientDto.setNumAVS(demandeDeBilan.getPatient().getNumeroAVS());
        infoPatientDto.setAssurance(demandeDeBilan.getPatient().getAssurance());
        if (demandeDeBilan.getPatient().getLocalite() != null) {
          infoPatientDto.setLocalite(demandeDeBilan.getPatient().getLocalite().getNom());
          infoPatientDto.setNpa(demandeDeBilan.getPatient().getLocalite().getNpa());
        }
        if (demandeDeBilan.getPatient().getEcole() != null) {
          infoPatientDto.setEcole(demandeDeBilan.getPatient().getEcole().getNom());
        }
        if (demandeDeBilan.getPatient().getRepresentantLegal() != null) {
          infoPatientDto.setRepresentantLegalNom(demandeDeBilan.getPatient().getRepresentantLegal().getNom());
          infoPatientDto.setRepresentantLegalPrenom(demandeDeBilan.getPatient().getRepresentantLegal().getPrenom());
        }
        infoPatientDto.setDegreScolaire("PAS IMPLEMENTE");
        demandeDeBilanDto.setPatient(infoPatientDto);

        demandesDeBilanDto.add(demandeDeBilanDto);
      });
      listeAttenteVueLogoDto.setDemandesDeBilans(demandesDeBilanDto);

      return listeAttenteVueLogoDto;
  }
}
