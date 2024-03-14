package com.popularsafi.service.impl;

import com.popularsafi.model.Notificacion;
import com.popularsafi.model.Transaccion;
import com.popularsafi.repo.INotificacionRepo;
import com.popularsafi.repo.ITransaccionRepo;
import com.popularsafi.service.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImpl implements INotificacionService {
    @Autowired
    INotificacionRepo notificacionRepo;


    @Override
    public Notificacion save(Notificacion notificacion) {
        return null;
    }

    @Override
    public Notificacion update(Notificacion notificacion, Integer integer) {
        return null;
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepo.findAll();
    }

    @Override
    public Notificacion findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

}
