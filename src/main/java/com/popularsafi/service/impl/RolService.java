package com.popularsafi.service.impl;

import com.popularsafi.enums.Name;
import com.popularsafi.model.Role;
import com.popularsafi.repo.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Role getByName(Name rolNombre){
        return rolRepository.findByName(rolNombre);
    }

    public void save(Role rol){
        rolRepository.save(rol);
    }
}
