package com.popularsafi.controller;

import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.dto.MovimientosDTO;
import com.popularsafi.model.Movimientos;
import com.popularsafi.security.JwtRequestMovi;
import com.popularsafi.service.IMovimientosService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    @Autowired
    private IMovimientosService iMovimientosService;

    @Qualifier("movimientosMapper")
    private final ModelMapper modelMapper;

    @GetMapping("/listaanio")
    public ResponseEntity<List<AnioDTO>> listaAnio() throws Exception {
        List<AnioDTO> oMovimiento= new ArrayList<>();
        try {
            oMovimiento=iMovimientosService.findByAnioAll();
        } catch (Exception exception) {
            //    System.out.println("nombre"+nombre1);
            exception.printStackTrace();
            throw new Exception("no hay informacion");
        }
        return new ResponseEntity<List<AnioDTO> >(oMovimiento, HttpStatus.OK) ;
    }


    @GetMapping("/listames/{panio}")
    public ResponseEntity<List<MesesDTO>> listames(@PathVariable("panio") Integer panio) throws Exception {
        List<MesesDTO> oMovimiento= new ArrayList<>();
        try {
            oMovimiento=iMovimientosService.findByMesUltimo(panio);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("estadisticas no encontradas");
        }
        return new ResponseEntity<List<MesesDTO> >(oMovimiento, HttpStatus.OK) ;
    }


    @PostMapping("/listamovimientos")
    public ResponseEntity<List<MovimientosDTO>> listamovimientos(@RequestBody JwtRequestMovi jwtRequest) throws Exception {
         List<MovimientosDTO> oMovimiento= new ArrayList<>();
        try {
           oMovimiento  = iMovimientosService.findMovimientos(jwtRequest.getUsuario_id(),jwtRequest.getAnio(),jwtRequest.getMes(),jwtRequest.getTransa()).stream().map(this::convertToDto).collect(Collectors.toList());
            //oMovimiento=iMovimientosService.findMovimientos(
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("estadisticas no encontradas");
        }
        return new ResponseEntity<List<MovimientosDTO>>(oMovimiento, HttpStatus.OK) ;
    }



    private MovimientosDTO convertToDto(Movimientos obj){
        return modelMapper.map(obj, MovimientosDTO.class);
    }

    private Movimientos convertToEntity(MovimientosDTO dto){
        return modelMapper.map(dto, Movimientos.class);
    }

}
