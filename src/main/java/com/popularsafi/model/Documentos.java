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
@Table(name = "documentos")
public class Documentos {
    @Id
    private Long id;

    @Column(nullable = true, length = 1)
    private String tpparticipe;

    @Column(nullable = false, length = 5)
    private String cparticipe;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario_id;

    @Column(nullable = false, length = 4)
    private String tipodocumento;


    private byte[] content;

    @Column(nullable = false, length = 120)
    private String nomdocumentoctacte;

    @Column(nullable = false, length = 120)
    private String nomdocumentoliqui;
    private Integer anio;
    private Integer periodo;

    @Column(nullable = false, length = 2)
    private String concepto ;

    @Column(nullable = false, length = 2)
    private String trimestre ;

    private Date fregistro;


}
