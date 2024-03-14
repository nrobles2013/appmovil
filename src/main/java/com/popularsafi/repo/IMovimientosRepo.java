package com.popularsafi.repo;


import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.model.Movimientos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMovimientosRepo extends IGenericRepo<Movimientos,Integer> {
    @Query(value="Select distinct EXTRACT(year FROM fecha) anio FROM movimientos where EXTRACT(year FROM fecha)> EXTRACT(year FROM current_Date)-3  ",nativeQuery = true)
    List<Object[]> findByAnioAll();

    @Query(value="select distinct  EXTRACT(month FROM fecha) idmes," +
            "case when EXTRACT(month FROM fecha)=1 then 'ENERO' else " +
            "case when EXTRACT(month FROM fecha)=2 then 'FEBRERO' else " +
            "case when EXTRACT(month FROM fecha)=3 then 'MARZO' else " +
            "case when EXTRACT(month FROM fecha)=4 then 'ABRIL' else " +
            "case when EXTRACT(month FROM fecha)=5 then 'MAYO' else " +
            "case when EXTRACT(month FROM fecha)=6 then 'JUNIO' else " +
            "case when EXTRACT(month FROM fecha)=7 then 'JULIO' else " +
            "case when EXTRACT(month FROM fecha)=8 then 'AGOSTO' else " +
            "case when EXTRACT(month FROM fecha)=9 then 'SETIEMBRE' else " +
            "case when EXTRACT(month FROM fecha)=10 then 'OCTUBRE' else " +
            "case when EXTRACT(month FROM fecha)=11 then 'NOVIEMBRE' else 'DICIEMBRE'  END END END END END END END END END END END MES" +
            " from movimientos where EXTRACT(year FROM fecha)=:pAnio order by 1",nativeQuery = true)
    List<Object[]> findByMesUltimo(@Param("pAnio") Integer pAnio);

    @Query(value="select m.id,m.cparticipe,t.idtransaccion tipoopera,date(m.fecha) fecha,t.descripcion destipoopera,m.descripcion,m.monto,0 nano,0 nperiodo,current_date fregistro,m.usuario_id from transaccion t " +
            "inner join movimientos m on t.idtransaccion=m.tipoopera where usuario_id=:pUsuario and case when :pAnio=0 " +
            "then EXTRACT(year FROM fecha)=EXTRACT(year FROM fecha) else EXTRACT(year FROM fecha) =:pAnio end  and case when :pMes=0 then EXTRACT(month FROM fecha)=EXTRACT(month FROM fecha) else " +
            "EXTRACT(month FROM fecha)=:pMes end  and case when :ptransa='0' then  t.idtransaccion= t.idtransaccion else  t.idtransaccion=:ptransa  end and EXTRACT(year FROM fecha)> EXTRACT(year FROM current_Date)-3  order by fecha",nativeQuery = true)
    List<Movimientos> findMovimientos(Integer pUsuario,Integer pAnio,Integer pMes,String ptransa);




}
