package com.popularsafi.service;


import com.popularsafi.model.FileDocumentoRuta;

import java.util.List;


public interface IFileDocumentoService extends ICRUD<FileDocumentoRuta,String>{
    FileDocumentoRuta findByDocumentoAll(String pfile);

    byte[] generateReport(String Ruta) throws Exception;
}
