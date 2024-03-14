package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MenuUsuario {

    private Long usuarioMenuId;

    private Usuario usuario;

    private Menu menu;

}
