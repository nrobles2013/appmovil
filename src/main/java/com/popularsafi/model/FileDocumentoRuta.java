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
@Table(name = "FileDocumentoRuta")
public class FileDocumentoRuta {
    @Id
    private Long id;
    @Column(nullable = false, length = 4)
    private String codigo;
    @Column(nullable = false, length = 60)
    private String descripcion;
    @Column(nullable = false, length = 200)
    private String ruta;
    @Column(length = 200)
    private String contenido;

    @Column(length = 200)
    private String respuesta;
}
