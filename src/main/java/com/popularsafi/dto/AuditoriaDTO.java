package com.popularsafi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuditoriaDTO {

    private String ip_maquina;
    private String usuario_id;
    private String username;
    private String descripcion;
    private String tpparticipe;
    private String cparticipe;
    private Timestamp fecha_registro;
    private String tipo;

}
