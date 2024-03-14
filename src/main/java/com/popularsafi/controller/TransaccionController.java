package com.popularsafi.controller;

import com.popularsafi.dto.AnioDTO;
import com.popularsafi.model.Transaccion;
import com.popularsafi.service.IMovimientosService;
import com.popularsafi.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/transaccion")
public class TransaccionController {
    @Autowired
    private ITransaccionService iTransaccionService;


    @GetMapping("/listatodos")
    public ResponseEntity<List<Transaccion>> listaTodos() throws Exception {
        List<Transaccion> oTransaccion= new ArrayList<>();
        try {
            oTransaccion=iTransaccionService.findByAll();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no existe transaccion");
        }
        return new ResponseEntity<List<Transaccion> >(oTransaccion, HttpStatus.OK) ;
    }

}
