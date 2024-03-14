package com.popularsafi.controller;

import com.popularsafi.model.Estadistica;
import com.popularsafi.service.IEstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/estadistica")
public class EstadisticaController {
    @Autowired
    private IEstadisticaService iEstadisticaService;


    @GetMapping("/listatodos")
    public ResponseEntity<List<Estadistica>> listatodos() throws Exception {
        List<Estadistica> NewEstadistica= new ArrayList<>();
        try {
            NewEstadistica=iEstadisticaService.findByAll();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<List<Estadistica> >(NewEstadistica, HttpStatus.OK) ;
    }


    @GetMapping("/listatodosapp")
    public ResponseEntity<List<Estadistica>> listatodosapp() throws Exception {
        List<Estadistica> NewEstadistica= new ArrayList<>();
        try {
            NewEstadistica=iEstadisticaService.findByAll_app();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<List<Estadistica> >(NewEstadistica, HttpStatus.OK) ;
    }


    @GetMapping("/listaultimo")
    public ResponseEntity<List<Estadistica>> listaultimo() throws Exception {
        List<Estadistica> NewEstadistica= new ArrayList<>();
        try {
            NewEstadistica=iEstadisticaService.findByUltimo();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("estadisticas no encontradas");
        }
        return new ResponseEntity<List<Estadistica> >(NewEstadistica, HttpStatus.OK) ;
    }

}
