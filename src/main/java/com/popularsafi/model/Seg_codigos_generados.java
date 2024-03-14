package com.popularsafi.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "seg_codigos_generados")
public class Seg_codigos_generados {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_codigo;

    private Date D_FECHA;

    @Column(nullable = false)
    private Integer usuario_id;

    @Column(nullable = false)
    private String codigover;


    @Column(nullable = false, length = 11)
    private String username;

    @Column(nullable = false, length = 8)
    private String HORAINI;
    @Column(nullable = false, length = 8)
    private String HORAFIN;
}
