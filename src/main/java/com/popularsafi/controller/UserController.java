package com.popularsafi.controller;

import com.popularsafi.model.Role;
import com.popularsafi.model.Usuario;
import com.popularsafi.security.*;
import com.popularsafi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    //private final KeyCloakServiceImpl keycloakService;

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
       // boolean rpta =  keycloakService.updateUser(user);

       // if (rpta) {
            System.out.println(passwordEncoder.encode(user.getPassword()));
            Usuario usuario =
                    new Usuario( user.getUsuario_id(),  passwordEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2, "User"));

            usuario.setRoles(roles);


            usuarioService.saveusuario(usuario.getUsuario_id(),usuario.getPassword());
        //}
        return new ResponseEntity<>(true, HttpStatus.OK);
    }




}
