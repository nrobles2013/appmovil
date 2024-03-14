package com.popularsafi.controller;

import com.popularsafi.dto.MovimientosDTO;
import com.popularsafi.dto.NotificacionDTO;
import com.popularsafi.dto.UsuarioDTO;
import com.popularsafi.model.Movimientos;
import com.popularsafi.model.Role;
import com.popularsafi.model.TipoDocumento;
import com.popularsafi.model.Usuario;
import com.popularsafi.security.*;
import com.popularsafi.service.IConsultaService;
import com.popularsafi.service.INotificacionService;
import com.popularsafi.service.IUserService;
import com.popularsafi.service.impl.KeyCloakServiceImpl;
import com.popularsafi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
@RequiredArgsConstructor

public class UserController {



    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;



    @Autowired
    PasswordEncoder passwordEncoder;

    private final KeyCloakServiceImpl keycloakService;

    @Autowired
    UserServiceImpl usuarioService;



    @PostMapping("/lista")
    public ResponseEntity<Usuario> listarParticipe(@RequestBody JwtRequest jwtuser) throws Exception {
            Usuario  oUsuario  = usuarioService.findByUsernameLike(jwtuser.getTpparticipe().trim(),jwtuser.getUsername().trim());
        return new ResponseEntity<Usuario>(oUsuario, HttpStatus.OK) ;
    }




    @PutMapping(value="/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody Usuario user) throws  Exception{
        System.out.println("por aca paso"+user.getUsuario_id()+user.getPassword());
        boolean rpta =  keycloakService.updateUser(user);

        if (rpta) {
            System.out.println(passwordEncoder.encode(user.getPassword()));
            Usuario usuario =
                    new Usuario( user.getUsuario_id(),  passwordEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2, "User"));

            usuario.setRoles(roles);


            usuarioService.saveusuario(usuario.getUsuario_id(),usuario.getPassword());
        }
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }




}
