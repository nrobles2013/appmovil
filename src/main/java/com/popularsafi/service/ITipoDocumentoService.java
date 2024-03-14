package com.popularsafi.service;


import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.dto.PeriodoDTO;
import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoDocumentoService extends ICRUD<TipoDocumento,Integer>{

    List<AnioDTO> findByAnioAll();
    List<TipoDocumento> findTipoDocumentos(String ptransa);

    TipoDocumento findTipoOneDocum(String ptipo);

}
