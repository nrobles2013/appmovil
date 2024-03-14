package com.popularsafi.service;


import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.model.Movimientos;

import java.util.List;

public interface IMovimientosService extends ICRUD<Movimientos,Integer>{

    List<AnioDTO> findByAnioAll();
    List<MesesDTO> findByMesUltimo(Integer pAnio);
    List<Movimientos> findMovimientos(Integer pUsuario,Integer pAnio,Integer pMes,String ptransa);
}
