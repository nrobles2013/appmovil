package com.popularsafi.repo;


import com.popularsafi.model.Estadistica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEstadisticaRepo extends IGenericRepo<Estadistica,Integer> {
    @Query(value="select * from estadistica where fecha>current_date-480 order by fecha asc",nativeQuery = true)
    List<Estadistica> findByAll();


    @Query(value="select * from estadistica where fecha>current_date-400 order by fecha asc",nativeQuery = true)
    List<Estadistica> findByAll_app();

    @Query(value="select * from estadistica where fecha = (select max(fecha) from estadistica)",nativeQuery = true)
    List<Estadistica> findByUltimo();
}
