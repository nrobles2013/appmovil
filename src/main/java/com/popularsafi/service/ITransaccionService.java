package com.popularsafi.service;


import com.popularsafi.model.Transaccion;

import java.util.List;

public interface ITransaccionService extends ICRUD<Transaccion,Integer>{

    List<Transaccion> findByAll();

}
