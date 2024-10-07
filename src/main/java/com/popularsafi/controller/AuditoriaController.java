package com.popularsafi.controller;

import com.popularsafi.dto.AuditoriaDTO;
import com.popularsafi.model.Auditoria;
import com.popularsafi.model.Estadistica;
import com.popularsafi.security.JwtRequest;
import com.popularsafi.service.IAuditoriaService;
import com.popularsafi.service.IEstadisticaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auditoria")
public class AuditoriaController {
    @Autowired
    private IAuditoriaService iAuditoriaService;

    @PostMapping("/insertar")
    public ResponseEntity<Auditoria> insertar(@RequestBody AuditoriaDTO audi) throws Exception {
         List<?> oAuditoria= new ArrayList<>();
        System.out.println(
                "cparticipe"+audi.getCparticipe()
        );
        try {
            oAuditoria=iAuditoriaService.insertarAuditoria(audi.getDescripcion(),audi.getIp_maquina(),audi.getTpparticipe(),audi.getCparticipe(), audi.getUsername(),audi.getTipo());
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        return new ResponseEntity<>( HttpStatus.OK) ;
    }


}
