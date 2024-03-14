package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestMovi implements Serializable {
    private Integer panio;
    private Integer pmes;
    private Integer usuario_id;
    private String  ptransa;
}
