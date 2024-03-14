package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestContactanos implements Serializable {
    private String usuario;
    private String correo;
    private String asunto;
    private String mensaje;
}
