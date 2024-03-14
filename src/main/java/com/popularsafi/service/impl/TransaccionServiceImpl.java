package com.popularsafi.service.impl;

import com.popularsafi.model.Transaccion;
import com.popularsafi.repo.ITransaccionRepo;
import com.popularsafi.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionServiceImpl implements ITransaccionService {
    @Autowired
    ITransaccionRepo transaccionRepo;


    @Override
    public Transaccion save(Transaccion transaccion) {
        return null;
    }

    @Override
    public Transaccion update(Transaccion transaccion, Integer integer) {
        return null;
    }

    @Override
    public List<Transaccion> findAll() {
        return null;
    }

    @Override
    public Transaccion findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Transaccion> findByAll() {
        return transaccionRepo.findByAll();
    }
}
