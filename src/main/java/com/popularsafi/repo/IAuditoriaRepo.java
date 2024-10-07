package com.popularsafi.repo;


import com.popularsafi.model.Auditoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IAuditoriaRepo extends IGenericRepo<Auditoria,Integer> {


    @Query(value="select * from public.insertarauditoria(:pDescripcion,:pIp_maquina, :pTpparticipe,:pcparticipe,:pUsername,:ptipo)",nativeQuery = true)
    List<?> insertarAuditoria(@Param("pDescripcion") String pDescripcion,
                                     @Param("pIp_maquina") String pIp_maquina,
                                      @Param("pTpparticipe") String pTpparticipe,
                                      @Param("pcparticipe") String pcparticipe,
                                      @Param("pUsername") String pUsername,
                                      @Param("ptipo") String ptipo);
}
