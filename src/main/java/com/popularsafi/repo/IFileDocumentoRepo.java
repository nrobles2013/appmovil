package com.popularsafi.repo;


import com.popularsafi.dto.FileDocumentoRutaDTO;

import com.popularsafi.model.FileDocumentoRuta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFileDocumentoRepo extends IGenericRepo<FileDocumentoRuta,String> {
    @Query(value="select id,codigo,descripcion,ruta,contenido,respuesta from file_documento_ruta where codigo=:pfile",nativeQuery = true)
    FileDocumentoRuta findByDocumentoAll(@Param("pfile") String pfile);

}
