package com.popularsafi.service;

import com.popularsafi.dto.UsuarioDTO;
import com.popularsafi.model.NuevoUsuario;
import com.popularsafi.model.Usuario;

public interface IKeyCloakService {
    boolean addUser(Usuario user) throws Exception;
    boolean updateUser(Usuario user) throws Exception;

}
