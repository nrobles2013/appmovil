package com.popularsafi.service.impl;

import com.popularsafi.model.Menu;
import com.popularsafi.model.MenuUsuario;
import com.popularsafi.model.Role;
import com.popularsafi.model.Usuario;
import com.popularsafi.repo.IMenuUsuarioRepository;
import com.popularsafi.service.IMenuUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuUsuarioServiceImpl implements IMenuUsuarioService {

    @Autowired
    private IMenuUsuarioRepository menuUsuarioRepository;


    @Override
    public List<MenuUsuario> buscarOpcMenu(String nombre,String tipo) {
        System.out.println("nombre"+nombre);
        List<MenuUsuario> menuusuario =new ArrayList<>();
        menuUsuarioRepository.findByUsername(nombre,tipo).forEach(
          x->{
              MenuUsuario m =new MenuUsuario();
              m.setUsuarioMenuId(Long.parseLong(String.valueOf(x[8])));
              Menu me=new Menu();
              me.setMenu_id(Long.parseLong(String.valueOf(x[0])));
              me.setIconMenu(String.valueOf(x[2]));
              me.setUrlMenu(String.valueOf(x[5]));
              me.setTitleMenu(String.valueOf(x[4]));
              me.setMenuPad(Long.parseLong(String.valueOf(x[3])));
              me.setFlag(Integer.parseInt(String.valueOf(x[1])));
              Usuario usu=new Usuario();
              usu.setUsername(String.valueOf(x[6]));
              usu.setUsuario_id(Integer.parseInt(String.valueOf(x[8])));
              usu.setApellido(String.valueOf(x[10]));
              usu.setNombre(String.valueOf(x[11]));
              usu.setCparticipe(String.valueOf(x[12]));
              usu.setEmail(String.valueOf(x[13]));
              Role rol=new Role();
              rol.setRol_id(Integer.parseInt(String.valueOf(x[14])));
              rol.setName(String.valueOf(x[7]));
              Set<Role> roles = new HashSet<>();
              roles.add(rol);
              usu.setRoles(roles);
              m.setMenu(me);
              m.setUsuario(usu);

              menuusuario.add(m);
          }
        );
        if(menuusuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return menuusuario;
    }


    @Override
    public MenuUsuario save(MenuUsuario menuUsuario) {
        return null;
    }

    @Override
    public MenuUsuario update(MenuUsuario menuUsuario, Integer integer) {
        return null;
    }

    @Override
    public List<MenuUsuario> findAll() {
        return null;
    }

    @Override
    public MenuUsuario findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
