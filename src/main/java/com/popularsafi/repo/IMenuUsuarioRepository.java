package com.popularsafi.repo;


import com.popularsafi.model.Menu;
import com.popularsafi.model.MenuUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuUsuarioRepository extends IGenericRepo<Menu,Long> {

        @Query(value="select m.*,ur.username,r.name,mu.usuario_id,mu.menu_id,ur.apellido,ur.nombre,ur.cparticipe,ur.email ,uro.rol_id from menu m inner join menu_usuario mu on m.menu_id=mu.menu_id inner join usuario ur on ur.usuario_id=mu.usuario_id inner join usuario_rol uro on uro.usuario_id=mu.usuario_id inner join rol r on uro.rol_id=r.rol_id where mu.enabled=true and ur.tpparticipe like '%'||:tipo||'%'   and ur.username like '%'||:nombre||'%'  order by m.menu_id",nativeQuery = true)
        List<Object[]> findByUsername(@Param("nombre") String nombre,@Param("tipo") String tipo);
}
