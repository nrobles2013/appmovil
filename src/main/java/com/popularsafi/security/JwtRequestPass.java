package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestPass implements Serializable {
    private String username;
    private String password;
    private String email;
    private Integer usuario_id;
    private String codigover;
}
