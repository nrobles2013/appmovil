package com.popularsafi.controller;

import com.popularsafi.dto.AnioDTO;
import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import com.popularsafi.service.IPeriodoService;
import com.popularsafi.service.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tipodocumentos")
public class TipoDocumentosController {
    @Autowired
    private ITipoDocumentoService iTipoDocumentoService;
    @Autowired
    private IPeriodoService iPeriodoService;

    @GetMapping("/listaanio")
    public ResponseEntity<List<AnioDTO>> listaAnio() throws Exception {
        List<AnioDTO> oTipoDocumento= new ArrayList<>();
        try {
            oTipoDocumento=iTipoDocumentoService.findByAnioAll();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no hay informacion");
        }
        return new ResponseEntity<List<AnioDTO> >(oTipoDocumento, HttpStatus.OK) ;
    }

    @GetMapping("/listadocumentos/{ptipo}")
    public ResponseEntity<List<TipoDocumento>> listaTipoDocumentos(@PathVariable("ptipo") String ptipo) throws Exception {
        List<TipoDocumento> oTipoDocumento= new ArrayList<>();
        try {
            oTipoDocumento=iTipoDocumentoService.findTipoDocumentos(ptipo);
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no existe tipo de documento");
        }
        return new ResponseEntity<List<TipoDocumento> >(oTipoDocumento, HttpStatus.OK) ;
    }


    @GetMapping("/listaperiodo")
    public ResponseEntity<List<Periodo>> listaperiodo() throws Exception {
        List<Periodo> oPeriodo= new ArrayList<>();
        try {
            oPeriodo=iPeriodoService.findAll();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no hay informacion");
        }
        return new ResponseEntity<List<Periodo> >(oPeriodo, HttpStatus.OK) ;
    }
}
