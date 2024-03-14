package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "rol")

public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Integer rol_id;

    @Column(nullable = false, length = 50)
    private String name;


}
