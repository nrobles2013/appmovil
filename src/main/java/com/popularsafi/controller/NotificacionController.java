package com.popularsafi.controller;

import com.popularsafi.dto.MovimientosDTO;
import com.popularsafi.dto.NotificacionDTO;
import com.popularsafi.model.Movimientos;
import com.popularsafi.model.Notificacion;
import com.popularsafi.service.INotificacionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/notificacion")
@RequiredArgsConstructor
public class NotificacionController {
    @Autowired
    private INotificacionService iNotificacionService;


    @Qualifier("notificacionMapper")
    private final ModelMapper modelMapper;


    @GetMapping("/listatodos")
    public ResponseEntity<List<NotificacionDTO>> listaTodos() throws Exception {
        List<NotificacionDTO>   oNotificacion=iNotificacionService.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        List<NotificacionDTO>   oNotificacion2= new ArrayList<>();
        Long x=0L;
        for(NotificacionDTO dataGas:oNotificacion){
            x = x +1;
            dataGas.setId(x);
            oNotificacion2.add(dataGas);
        }
        return new ResponseEntity<>(oNotificacion2, HttpStatus.OK) ;
    }



    private NotificacionDTO convertToDto(Notificacion obj){
        return modelMapper.map(obj, NotificacionDTO.class);
    }

    private Notificacion convertToEntity(NotificacionDTO dto){
        return modelMapper.map(dto, Notificacion.class);
    }


}
