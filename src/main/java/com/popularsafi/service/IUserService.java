package com.popularsafi.service;


import com.popularsafi.model.Usuario;
import org.springframework.data.repository.query.Param;

public interface IUserService extends ICRUD<Usuario,Integer>{
    Usuario findByUsernameLike(String tpparticipe,String username);
    boolean existsByUsername(String username);
    Integer saveusuario(Integer pusuario_id,String ppassword, String pemail);

    Integer existeEmailLike(String pemail);
    Integer savepermiso(Integer pusuario_id);


}
