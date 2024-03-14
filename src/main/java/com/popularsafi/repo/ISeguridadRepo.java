package com.popularsafi.repo;

import com.popularsafi.model.Seg_codigos_generados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ISeguridadRepo extends IGenericRepo<Seg_codigos_generados, Integer> {


    @Query(value = "select * from public.insertarcodigogene(:pvcodigo,:pusername,:pverificacion,:phoraini,:phorafin)",nativeQuery = true)
    Integer savecodigo( @Param("pvcodigo") Integer pvcodigo, @Param("pusername") String pusername,
                        @Param("pverificacion") String pverificacion,@Param("phoraini") String phoraini,@Param("phorafin") String phorafin);

    @Query(value = "select count(*) from seg_codigos_generados   where  usuario_id = :pusuario_id and username like '%'||:pusername||'%'" +
                " and d_fecha= current_Date and codigover like '%'||:pverifica||'%' and horafin>=to_char(CURRENT_TIMESTAMP, 'HH24:MI:SS')",nativeQuery = true)
    Integer existeVerificacion( @Param("pusuario_id") Integer pusuario_id, @Param("pusername") String pusername,
                                @Param("pverifica") String pverifica);

    @Query(value = "select * from public.permisoacceso(:ptparticipe,:pusername)",nativeQuery = true)
    Integer permisoacceso( @Param("ptparticipe") String ptparticipe, @Param("pusername") String pusername);

    @Query(value = "select sum(numintentos)  from usuario where tpparticipe= :ptparticipe and  username like '%'||:pusername||'%'  ",nativeQuery = true)
    Integer listaacceso( @Param("ptparticipe") String ptparticipe, @Param("pusername") String pusername);

    @Query(value = "select * from public.dapermiso(:ptparticipe,:pusername)",nativeQuery = true)
    Integer savepermiso(@Param("ptparticipe") String ptparticipe, @Param("pusername") String pusername);

}
