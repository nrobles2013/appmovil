package com.popularsafi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TipoDocumento")
public class TipoDocumento {
    @Id
    private Long id;
    @Column(nullable = false, length = 4)
    private String codigo;
    @Column(nullable = false, length = 60)
    private String descripcion;
    @Column(nullable = false, length = 30)
    private String descorta;
    @Column(nullable = false, length = 4)
    private String tipo;
    @Column(length = 2)
    private String estado;
}
