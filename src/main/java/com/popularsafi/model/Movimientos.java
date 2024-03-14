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
@Table(name = "movimientos")
public class Movimientos {

    @Id
    private Long id;

    @Column(nullable = false, length = 5)
    private String cparticipe;



    private Date fecha;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario_id;


    @Column(nullable = false, length = 2)
    private String tipoopera;

    @Column(nullable = false, length = 120)
    private String destipoopera;

    @Column(nullable = false, length = 120)
    private String descripcion;

    private Double monto;

    private Date fregistro;

    private Integer NANO;

    private Integer NPERIODO;

}
