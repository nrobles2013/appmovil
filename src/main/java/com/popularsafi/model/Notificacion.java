package com.popularsafi.model;

import jakarta.persistence.Column;
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
@Table(name = "notificacion")
public class Notificacion {
    @Id
    private Long id;

    private Date fecha;

    @Column(nullable = false, length = 60)
    private String asunto;

    @Column(nullable = false, length = 255)
    private String mensaje;

    private Date fregistro;


}
