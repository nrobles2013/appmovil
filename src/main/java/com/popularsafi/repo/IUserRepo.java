package com.popularsafi.repo;

import com.popularsafi.model.FileDocumentoRuta;
import com.popularsafi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepo extends JpaRepository<Usuario, Integer> {

    //@Query("FROM sistema1.Usuario u WHERE u.username = ?")
    //Derived Query
    //@Query(value = "select *  FROM user_data u  where u.username = ?",nativeQuery = true)

    Usuario findByUsername(String username);

    @Query(value = "select * from usuario where username like '%'||:username||'%'  and tpparticipe like '%'||:tpparticipe||'%' ",nativeQuery = true)
    Usuario findByUsernameLike(@Param("tpparticipe") String tpparticipe,@Param("username") String username);

    boolean existsByUsername(String username);

    @Query(value = "select * from public.actualizausuario(:pusuario_id,:ppassword)",nativeQuery = true)
    Integer saveusuario(@Param("pusuario_id") Integer pusuario_id,@Param("ppassword") String ppassword);


    @Query(value = "select * from usuario where email like '%'||:pemail||'%' and username= :pusername  and tpparticipe= :ptpparticipe",nativeQuery = true)
    Usuario existeEmailLike(@Param("pemail") String pemail,@Param("pusername") String pusername,@Param("ptpparticipe") String ptpparticipe);




}
