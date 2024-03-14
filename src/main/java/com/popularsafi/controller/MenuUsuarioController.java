package com.popularsafi.controller;

import com.popularsafi.model.MenuUsuario;
import com.popularsafi.security.JwtRequest;
import com.popularsafi.service.IMenuUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/menuusuario")
public class MenuUsuarioController {

    @Autowired
    private IMenuUsuarioService iMenuusuarioService;


    @GetMapping("/listaid/{id}/{tipo}")
    public ResponseEntity<List<MenuUsuario>> listaid(@PathVariable("id") String nombre,@PathVariable("id") String tipo) throws Exception {
        System.out.println("respuesta"+nombre);
        List<MenuUsuario> omenuUsuario= new ArrayList<>();
        try {
            omenuUsuario=iMenuusuarioService.buscarOpcMenu(nombre,tipo);
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<List<MenuUsuario> >(omenuUsuario,HttpStatus.OK) ;
    }

    @PostMapping(value = "/lista")
    public ResponseEntity<List<MenuUsuario>> lista(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("respuesta"+jwtRequest.getUsername()  + " --"+jwtRequest.getTpparticipe());
        List<MenuUsuario> omenuUsuario= new ArrayList<>();
        try {
            omenuUsuario=iMenuusuarioService.buscarOpcMenu(jwtRequest.getUsername(),jwtRequest.getTpparticipe());
        } catch (Exception exception) {
        //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
       return new ResponseEntity<List<MenuUsuario> >(omenuUsuario,HttpStatus.OK) ;
    }
}
