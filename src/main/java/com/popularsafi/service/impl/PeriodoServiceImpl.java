package com.popularsafi.service.impl;

import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import com.popularsafi.repo.IPeriodoRepo;
import com.popularsafi.service.IPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeriodoServiceImpl implements IPeriodoService {

    @Autowired
    IPeriodoRepo iPeriodoRepo;


    @Override
    public Periodo save(Periodo periodo) {
        return null;
    }

    @Override
    public Periodo update(Periodo periodo, String string) {
        return null;
    }

    @Override
    public List<Periodo> findAll() {
        return iPeriodoRepo.findAll();
    }


    @Override
    public Periodo findById(String s) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

}
