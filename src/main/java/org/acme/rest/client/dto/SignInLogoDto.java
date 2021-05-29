package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInLogoDto {
  private String numPrestataire;
  private String password;
}
