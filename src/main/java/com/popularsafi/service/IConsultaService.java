package com.popularsafi.service;


import com.popularsafi.dto.AnioDTO;
import com.popularsafi.model.TipoDocumento;

import java.io.IOException;
import java.util.List;

public interface IConsultaService extends ICRUD<TipoDocumento,Integer>{

     void sendFromGMail(String from,String pass,String[] to, String subject, String body, String[] file1) throws IOException;


}
