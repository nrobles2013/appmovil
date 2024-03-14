package com.popularsafi.controller;

import com.popularsafi.model.Estadistica;
import com.popularsafi.model.Inversion;
import com.popularsafi.security.JwtRequest;
import com.popularsafi.service.IEstadisticaService;
import com.popularsafi.service.IInversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/inversion")
public class InversionController {
    @Autowired
    private IInversionService iInversionService;


    @PostMapping("/listatodos")
    public ResponseEntity<List<Inversion>> listatodos(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest.getCparticipe());
        System.out.println(jwtRequest.getUsuario_id());
        List<Inversion> oInversion= new ArrayList<>();
        try {
            oInversion=iInversionService.findByAll(jwtRequest.getCparticipe(),jwtRequest.getUsuario_id());
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<List<Inversion> >(oInversion, HttpStatus.OK) ;
    }

    @PostMapping("/listatodosapp")
    public ResponseEntity<List<Inversion>> listatodosapp(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest.getCparticipe());
        System.out.println(jwtRequest.getUsuario_id());
        List<Inversion> oInversion= new ArrayList<>();
        try {
            oInversion=iInversionService.findByAll_app(jwtRequest.getCparticipe(),jwtRequest.getUsuario_id());
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<List<Inversion> >(oInversion, HttpStatus.OK) ;
    }


    @PostMapping("/listaultimo")
    public ResponseEntity<List<Inversion>> listaultimo(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest.getCparticipe());
        System.out.println(jwtRequest.getUsuario_id());
        List<Inversion> oInversion= new ArrayList<>();
        try {
            oInversion=iInversionService.findByUltimo(jwtRequest.getCparticipe(),jwtRequest.getUsuario_id());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("estadisticas no encontradas");
        }
        return new ResponseEntity<List<Inversion> >(oInversion, HttpStatus.OK) ;
    }
}
