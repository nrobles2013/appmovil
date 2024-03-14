package com.popularsafi.repo;

import com.popularsafi.enums.Name;
import com.popularsafi.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RolRepository extends IGenericRepo<Role, String> {
    Role findByName(Name rolNombre);
}
