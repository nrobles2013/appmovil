package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @Column(nullable = false, length = 2)
    private String idtransaccion;

    @Column(nullable = false, length = 60)
    private String descripcion;

}
