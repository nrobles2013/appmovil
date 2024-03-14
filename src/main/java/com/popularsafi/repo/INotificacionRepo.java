package com.popularsafi.repo;


import com.popularsafi.model.Notificacion;
import com.popularsafi.model.Transaccion;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INotificacionRepo extends IGenericRepo<Notificacion,Integer> {
    @Query(value="Select * FROM notificacion",nativeQuery = true)
    List<Notificacion> findByAll();

}
