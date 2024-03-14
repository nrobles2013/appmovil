package com.popularsafi.service;

import com.popularsafi.dto.EstadisticaDTO;
import com.popularsafi.model.Estadistica;
import com.popularsafi.model.MenuUsuario;

import java.util.List;

public interface IEstadisticaService extends ICRUD<Estadistica,Integer>{

    List<Estadistica> findByAll();
    List<Estadistica> findByAll_app();
    List<Estadistica> findByUltimo();
}
