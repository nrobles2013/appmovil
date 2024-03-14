package com.popularsafi.service.impl;

import com.popularsafi.repo.ISeguridadRepo;
import com.popularsafi.service.ISeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISeguridadImpl implements ISeguridadService {
    @Autowired
    ISeguridadRepo iSeguridadRepo;


    @Override
    public Integer savecodigo(Integer pvcodigo, String pusername,String pverifica, String phoraini, String phorafin) {
        Integer resp =0;
        resp=iSeguridadRepo.savecodigo(pvcodigo,pusername,pverifica,phoraini,phorafin);
        return resp;
    }


    @Override
    public Integer existeVerificacion(Integer pusuario_id,String pusername, String pverifica){
        Integer resp =0;
        resp=iSeguridadRepo.existeVerificacion(pusuario_id,pusername,pverifica);
        return resp;
    }

    @Override
    public Integer permisoacceso(String ptparticipe, String pusername) {
        Integer resp =0;
        resp=iSeguridadRepo.permisoacceso(ptparticipe,pusername);
        return resp;
    }

    @Override
    public Integer listaacceso(String ptparticipe, String pusername) {
        Integer resp =0;
        resp=iSeguridadRepo.listaacceso(ptparticipe,pusername);
        return resp;
    }

    @Override
    public Integer savepermiso(String ptparticipe, String pusername) {
        Integer resp =0;
        resp=iSeguridadRepo.savepermiso(ptparticipe,pusername);
        return resp;
    }


}
