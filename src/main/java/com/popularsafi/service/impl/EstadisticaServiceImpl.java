package com.popularsafi.service.impl;

import com.popularsafi.model.Estadistica;
import com.popularsafi.model.Menu;
import com.popularsafi.model.MenuUsuario;
import com.popularsafi.model.Usuario;
import com.popularsafi.repo.IEstadisticaRepo;
import com.popularsafi.repo.IUserRepo;
import com.popularsafi.service.IEstadisticaService;
import com.popularsafi.service.IMenuUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadisticaServiceImpl implements IEstadisticaService{
    @Autowired
    IEstadisticaRepo estadisticaRepo;



    @Override
    public List<Estadistica> findByUltimo() {
        List<Estadistica> estadistica =new ArrayList<>();
        estadistica=estadisticaRepo.findByUltimo();

        if(estadistica == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return estadistica;
    }


    @Override
    public List<Estadistica> findByAll() {
        List<Estadistica> estadistica =new ArrayList<>();
        estadistica=estadisticaRepo.findByAll();

        if(estadistica == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return estadistica;
    }

    @Override
    public List<Estadistica> findByAll_app() {
        List<Estadistica> estadistica =new ArrayList<>();
        estadistica=estadisticaRepo.findByAll_app();

        if(estadistica == null){
            throw new UsernameNotFoundException("estadisticas no encontrado");
        }
        return estadistica;
    }

    @Override
    public Estadistica findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Estadistica save(Estadistica estadistica) {
        return null;
    }

    @Override
    public Estadistica update(Estadistica estadistica, Integer integer) {
        return null;
    }

    @Override
    public List<Estadistica> findAll() {
        return null;
    }

}
