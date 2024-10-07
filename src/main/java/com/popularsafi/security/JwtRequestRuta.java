package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestRuta implements Serializable {
    private String tparticipe;
    private String username;
    private String ruta;
    private String cparticipe;
    private String tipodocumento;
    private String correo;
    private String anio;
    private String periodo;
    private String nombredocumento;
}
