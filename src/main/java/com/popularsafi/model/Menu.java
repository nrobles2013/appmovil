package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "menu")

public class Menu {
    @Id
    private Long menu_id;
    private String urlMenu;
    private String iconMenu;
    private String titleMenu;
    private Long menuPad;
    private Integer flag;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_usuario",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    )

    private List<Usuario> usuariolist;
}
