package com.popularsafi.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuario {
    private String nombre;
    private String Apellido;
    private String email;
    private String username;
    private String password;
    private String telefono;
    private String celular;
    private String tpparticipe;

}
