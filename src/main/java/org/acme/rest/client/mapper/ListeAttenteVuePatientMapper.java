package org.acme.rest.client.mapper;

import java.util.ArrayList;
import java.util.List;
import org.acme.rest.client.domain.ListeAttente;
import org.acme.rest.client.dto.ListeAttenteVuePatientDto;
import org.acme.rest.client.dto.LogopedisteLightDto;
import org.acme.rest.client.dto.SpecialisationDto;

public class ListeAttenteVuePatientMapper {

  public ListeAttenteVuePatientDto enDto(ListeAttente domaine) {

    LogopedisteLightDto logopedisteLightDto = new LogopedisteLightDto();
    logopedisteLightDto.setId(domaine.getLogopediste().getId());
    logopedisteLightDto.setNom(domaine.getLogopediste().getNom());
    logopedisteLightDto.setPrenom(domaine.getLogopediste().getPrenom());
    logopedisteLightDto.setRue(domaine.getLogopediste().getRue());
    logopedisteLightDto.setNpa(domaine.getLogopediste().getLocalite().getNpa());
    logopedisteLightDto.setLocalite(domaine.getLogopediste().getLocalite().getNom());
    logopedisteLightDto.setIdRegion(domaine.getLogopediste().getLocalite().getRegion().getId());
    logopedisteLightDto.setLibelleRegion(domaine.getLogopediste().getLocalite().getRegion().getLibelle());

    List<SpecialisationDto> specialisationDtoList = new ArrayList<>();
    domaine.getLogopediste().getSpecialisations().forEach(specialisation -> {
      SpecialisationDto specialisationDto = new SpecialisationDto();
      specialisationDto.setId(specialisation.getId());
      specialisationDto.setLibelle(specialisation.getLibelle());
      specialisationDtoList.add(specialisationDto);
    });
    logopedisteLightDto.setSpecialisations(specialisationDtoList);

    ListeAttenteVuePatientDto listeAttenteVuePatientDto = new ListeAttenteVuePatientDto();
    listeAttenteVuePatientDto.setId(domaine.getId());
    listeAttenteVuePatientDto.setLogopediste(logopedisteLightDto);
    listeAttenteVuePatientDto.setNbDemandesEnAttente(domaine.getNbDemandesEnAttente());
    listeAttenteVuePatientDto.setDureeAttenteEstimee(domaine.getDureeAttenteEstimee());

    return listeAttenteVuePatientDto;
  }


}
