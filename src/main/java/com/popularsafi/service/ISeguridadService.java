package com.popularsafi.service;

import com.popularsafi.model.Inversion;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ISeguridadService {

    Integer savecodigo(Integer pvcodigo,String pusername,String pverifica,String phoraini,String phorafin);
    Integer existeVerificacion(Integer pusuario_id,String pusername,String pverifica);
    Integer permisoacceso(String ptparticipe,String pusername);
    Integer listaacceso( String ptparticipe, String pusername);
    Integer savepermiso(String ptparticipe,String pusername);

}
