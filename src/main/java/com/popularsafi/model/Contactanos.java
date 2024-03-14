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
@Table(name = "contactanos")
public class Contactanos {
    @Id
    private Long id;
    @Column(nullable = false, length = 120)
    private String asunto;
    @Column(nullable = false, length = 255)
    private String mensaje;

}
