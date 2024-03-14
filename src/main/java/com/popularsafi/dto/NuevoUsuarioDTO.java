package com.popularsafi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NuevoUsuarioDTO {
   private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private String telefono;
    private String celular;
    private Set<String> roles = new HashSet<>();
   public Set<String> getRoles() {
    return roles;
   }

}
