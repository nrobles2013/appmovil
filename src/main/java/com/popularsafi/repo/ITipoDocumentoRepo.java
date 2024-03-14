package com.popularsafi.repo;


import com.popularsafi.dto.PeriodoDTO;
import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoDocumentoRepo extends IGenericRepo<TipoDocumento,Integer> {

    @Query(value="Select distinct EXTRACT(year FROM fecha) anio FROM movimientos where EXTRACT(year FROM fecha)> EXTRACT(year FROM current_Date)-3  ",nativeQuery = true)
    List<Object[]> findByAnioAll();
    @Query(value="select * from tipo_documento where tipo=:ptipo and estado='01'",nativeQuery = true)
    List<TipoDocumento> findTipoDocumentos(@Param("ptipo") String ptipo);

    @Query(value="select * from tipo_documento where tipo='0001' and codigo=:ptipo",nativeQuery = true)
    TipoDocumento findTipoOneDocum(@Param("ptipo") String ptipo);
}
