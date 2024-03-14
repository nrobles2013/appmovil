package com.popularsafi.service;

import com.popularsafi.model.Estadistica;
import com.popularsafi.model.Inversion;

import java.util.List;

public interface IInversionService {

    List<Inversion> findByAll(String pparticipe,Integer pusername);
    List<Inversion> findByAll_app(String pparticipe,Integer pusername);
    List<Inversion> findByUltimo(String pparticipe,Integer pusername);
}
