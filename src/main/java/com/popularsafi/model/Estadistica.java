package com.popularsafi.model;

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
@Entity
@Table(name = "estadistica")
public class Estadistica {
    @Id
    private int Id;
    private Double cyr;
    private Date fecha;
    private Date fecvalactual;
    private String  montoinvertido;
    private Integer numparticipe;
    private Double valactual;
    private Double valcuota;
    private String  valoractualinv;
    private Double ytd;

}
