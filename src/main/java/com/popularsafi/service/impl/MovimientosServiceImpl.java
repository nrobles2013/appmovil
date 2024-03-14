package com.popularsafi.service.impl;

import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.model.MenuUsuario;
import com.popularsafi.model.Movimientos;
import com.popularsafi.repo.IMovimientosRepo;
import com.popularsafi.service.IMovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosServiceImpl implements IMovimientosService {
    @Autowired
    IMovimientosRepo movimientosRepo;


    @Override
    public List<AnioDTO> findByAnioAll() {
        List<AnioDTO> anio =new ArrayList<>();
        List<Object[]> rows= movimientosRepo.findByAnioAll();
        anio = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            AnioDTO newaa=new AnioDTO();
            newaa.setAnio(Integer.parseInt(row[0].toString()));
            anio.add(newaa);
        }

        return anio;
    }

    @Override
    public List<MesesDTO> findByMesUltimo(Integer pAnio) {
        List<MesesDTO> mes =new ArrayList<>();
        List<Object[]> rows= movimientosRepo.findByMesUltimo(pAnio);
        mes = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            MesesDTO newaa=new MesesDTO();
            newaa.setIdmes(Integer.parseInt(row[0].toString()));
            newaa.setMes(row[1].toString());
            mes.add(newaa);
        }

        return mes;

    }

    @Override
    public List<Movimientos> findMovimientos(Integer pUsuario, Integer pAnio, Integer pMes,String ptransa) {
        return movimientosRepo.findMovimientos(pUsuario,pAnio,pMes,ptransa);
    }

    @Override
    public Movimientos save(Movimientos movimientos) {
        return null;
    }

    @Override
    public Movimientos update(Movimientos movimientos, Integer integer) {
        return null;
    }

    @Override
    public List<Movimientos> findAll() {
        return null;
    }

    @Override
    public Movimientos findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
