package org.acme.rest.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInPatientDto {
  private String email;
  private String password;
}
