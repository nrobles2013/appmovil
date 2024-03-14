package com.popularsafi.service.impl;

import com.popularsafi.model.Auditoria;
import com.popularsafi.model.Estadistica;
import com.popularsafi.repo.IAuditoriaRepo;
import com.popularsafi.repo.IEstadisticaRepo;
import com.popularsafi.service.IAuditoriaService;
import com.popularsafi.service.IEstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService{
    @Autowired
    IAuditoriaRepo auditoriaRepo;


    @Override
    public List<?> insertarAuditoria(String pDescripcion, String pIp_maquina, String pTpparticipe,String pcparticipe, String pUsername, Timestamp pFecha,String ptipo) {
        return auditoriaRepo.insertarAuditoria(pDescripcion,pIp_maquina,pTpparticipe,pcparticipe,pUsername,pFecha,ptipo);
    }

    @Override
    public Auditoria save(Auditoria auditoria) {
        return null;
    }

    @Override
    public Auditoria update(Auditoria auditoria, Integer integer) {
        return null;
    }

    @Override
    public List<Auditoria> findAll() {
        return null;
    }

    @Override
    public Auditoria findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
