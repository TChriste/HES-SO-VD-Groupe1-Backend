package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String email;
  private String nom;
  private String prenom;
}
