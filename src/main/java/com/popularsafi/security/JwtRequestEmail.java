package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestEmail implements Serializable {
    private String correo;
    private String ipaddress;
    private String tipo;
    private String tparticipe;
    private String username;

}
