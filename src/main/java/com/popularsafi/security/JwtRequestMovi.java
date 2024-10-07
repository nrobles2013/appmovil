package com.popularsafi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestMovi implements Serializable {
    private Integer anio;
    private Integer mes;
    private Integer usuario_id;
    private String  transa;
}
