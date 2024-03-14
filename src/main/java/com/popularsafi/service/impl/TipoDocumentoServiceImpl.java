package com.popularsafi.service.impl;

import com.popularsafi.dto.AnioDTO;
import com.popularsafi.dto.MesesDTO;
import com.popularsafi.dto.PeriodoDTO;
import com.popularsafi.model.Movimientos;
import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import com.popularsafi.repo.IMovimientosRepo;
import com.popularsafi.repo.ITipoDocumentoRepo;
import com.popularsafi.service.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService{
    @Autowired
    ITipoDocumentoRepo tipoDocumentoRepo;

    @Override
    public List<AnioDTO> findByAnioAll() {
        List<AnioDTO> anio =new ArrayList<>();
        List<Object[]> rows= tipoDocumentoRepo.findByAnioAll();
        anio = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            AnioDTO newaa=new AnioDTO();
            newaa.setAnio(Integer.parseInt(row[0].toString()));
            anio.add(newaa);
        }

        return anio;
    }

    @Override
    public List<TipoDocumento> findTipoDocumentos(String ptipo) {
        return tipoDocumentoRepo.findTipoDocumentos(ptipo);
    }

    @Override
    public TipoDocumento findTipoOneDocum(String ptipo) {
        return tipoDocumentoRepo.findTipoOneDocum(ptipo);
    }


    @Override
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return null;
    }

    @Override
    public TipoDocumento update(TipoDocumento tipoDocumento, Integer integer) {
        return null;
    }

    @Override
    public List<TipoDocumento> findAll() {
        return null;
    }

    @Override
    public TipoDocumento findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

}
