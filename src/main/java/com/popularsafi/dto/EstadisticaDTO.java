package com.popularsafi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class EstadisticaDTO {
    private int Id;
    private Double valcuota;
    private Date fecha;
    private Double valactual;
    private Date fecvalactual;
    private Double ytd;
    private Double cyr;
    private Integer numparticipe;
    private String  montoinvertido;
    private String  valoractualinv;
}
