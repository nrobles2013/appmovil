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
@Table(name = "auditoria")
public class Auditoria {
    @Id
    private Long id_auditoria;

    @Column(nullable = false, length = 20)
    private String ip_maquina;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private Usuario usuario_id;

    private Timestamp fecha_registro;

    private String tipo;

    @Column(nullable = false, length = 60)
    private String descripcion;

    @Column(nullable = false, length = 5)
    private String cparticipe;

    @Column(nullable = false, length = 1)
    private String tpparticipe;

}
