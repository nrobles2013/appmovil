package com.popularsafi.service.impl;


import com.popularsafi.model.Usuario;
import com.popularsafi.repo.IGenericRepo;
import com.popularsafi.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public  class UserServiceImpl  {
    @Autowired
    IUserRepo usuarioRepository;
    public Usuario findByUsernameLike(String pparticipe,String username){
        return usuarioRepository.findByUsernameLike(pparticipe,username);
    }



    public boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }



    public void save(Usuario usuario){
        System.out.println("guardando");
        usuarioRepository.save(usuario);
    }


    public Integer saveusuario(Integer pusuario_id,String password){
        return usuarioRepository.saveusuario(pusuario_id,password);
    }

     public Usuario existeEmailLike(String pemail){
        return usuarioRepository.existeEmailLike(pemail);
     }


}
