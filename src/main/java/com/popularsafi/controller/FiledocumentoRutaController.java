package com.popularsafi.controller;

import com.popularsafi.model.FileDocumentoRuta;
import com.popularsafi.security.JwtRequestRuta;
import com.popularsafi.service.IFileDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/filedocumento")
public class FiledocumentoRutaController {
    @Autowired
    private IFileDocumentoService iFileDocumentoService;


    @GetMapping("/listafile/{pfile}")
    public ResponseEntity<FileDocumentoRuta> listafile(@PathVariable("pfile") String pfile) throws Exception {
        System.out.println("datos"+pfile);
        FileDocumentoRuta oFileRuta= new FileDocumentoRuta();
        try {
            oFileRuta=iFileDocumentoService.findByDocumentoAll(pfile);
            System.out.println(oFileRuta.getContenido());
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no hay informacion");
        }
        return new ResponseEntity<FileDocumentoRuta >(oFileRuta, HttpStatus.OK) ;
    }


    @PostMapping(value="/generapdf",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generareport(@RequestBody JwtRequestRuta jwtRequestRuta) throws Exception{
        System.out.println("info"+jwtRequestRuta.getRuta());
        byte[] data=iFileDocumentoService.generateReport(jwtRequestRuta.getRuta());

        return new ResponseEntity<>(data,HttpStatus.OK);
    }


    @PostMapping(value="/verpdf",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE) //APPLICATION_PDF_VALUE
    public ResponseEntity<byte[]> visualizareporte(@RequestBody JwtRequestRuta jwtRequestRuta) throws Exception{
        System.out.println("info"+jwtRequestRuta.getRuta());
        byte[] data=iFileDocumentoService.generateReport(jwtRequestRuta.getRuta());
        System.out.println(data);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }


}
