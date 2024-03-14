package com.popularsafi.repo;


import com.popularsafi.model.Movimientos;
import com.popularsafi.model.Transaccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransaccionRepo extends IGenericRepo<Transaccion,Integer> {
    @Query(value="Select * FROM transaccion",nativeQuery = true)
    List<Transaccion> findByAll();

}
