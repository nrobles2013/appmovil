package com.popularsafi.service;


import com.popularsafi.model.Menu;
import com.popularsafi.model.MenuUsuario;

import java.util.List;

public interface IMenuUsuarioService extends ICRUD<MenuUsuario,Integer> {
    List<MenuUsuario> buscarOpcMenu(String nombre,String tipo);
}
