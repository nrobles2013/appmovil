package com.popularsafi.repo;


import com.popularsafi.model.Inversion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInversionRepo extends IGenericRepo<Inversion,Integer> {
    @Query(value="select * from inversion where  cparticipe=:pparticipe and usuario_id =:pusername and  fecha>current_date -480 order by fecha asc ",nativeQuery = true)
    List<Inversion> findByAll(@Param("pparticipe") String pparticipe,@Param("pusername") Integer pusername);

    @Query(value="select * from inversion where cparticipe=:pparticipe and usuario_id=:pusername and fecha = (select max(fecha) from inversion where  cparticipe=:pparticipe and usuario_id =:pusername)",nativeQuery = true)
    List<Inversion> findByUltimo(@Param("pparticipe") String pparticipe,@Param("pusername") Integer pusername);

    @Query(value="select * from inversion where  cparticipe=:pparticipe and usuario_id =:pusername and  fecha>current_date -400 order by fecha asc ",nativeQuery = true)
    List<Inversion> findByAll_app(@Param("pparticipe") String pparticipe,@Param("pusername") Integer pusername);

}
