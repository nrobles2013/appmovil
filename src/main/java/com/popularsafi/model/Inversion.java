package com.popularsafi.model;

import jakarta.persistence.*;
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
@Table(name = "inversion")
public class Inversion {
    @Id
    private int Id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario_id;

    @Column(nullable = false, length = 5)
    private String cparticipe;
    private Double montoinvertido;
    private Double valinvertido;
    private Integer numparticipacion;
    private Date fecha;
    private Date fregistro;

}
