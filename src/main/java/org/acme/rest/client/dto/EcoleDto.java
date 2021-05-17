package org.acme.rest.client.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcoleDto {
  public Long id;
  public String nom;
  public List<DegresScolaireDto> degresScolaire;
}
