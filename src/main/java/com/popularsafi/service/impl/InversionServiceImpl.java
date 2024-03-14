package com.popularsafi.service.impl;

import com.popularsafi.model.Inversion;
import com.popularsafi.repo.IInversionRepo;
import com.popularsafi.service.IInversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InversionServiceImpl implements IInversionService {
    @Autowired
    IInversionRepo inversionRepo;



    @Override
    public List<Inversion> findByUltimo(String pparticipe,Integer pusername) {
        List<Inversion> oInversion =new ArrayList<>();
        oInversion=inversionRepo.findByUltimo(pparticipe,pusername);

        if(oInversion == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return oInversion;
    }


    @Override
    public List<Inversion> findByAll(String pparticipe,Integer pusername) {
        List<Inversion> oInversion =new ArrayList<>();
        oInversion=inversionRepo.findByAll(pparticipe,pusername);

        if(oInversion == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return oInversion;
    }

    @Override
    public List<Inversion> findByAll_app(String pparticipe, Integer pusername) {
        List<Inversion> oInversion =new ArrayList<>();
        oInversion=inversionRepo.findByAll_app(pparticipe,pusername);

        if(oInversion == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return oInversion;
    }


}
