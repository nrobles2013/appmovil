package com.popularsafi.repo;


import com.popularsafi.model.Periodo;
import com.popularsafi.model.TipoDocumento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPeriodoRepo extends IGenericRepo<Periodo,String> {

}
