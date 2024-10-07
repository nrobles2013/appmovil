package com.popularsafi.service;

import com.popularsafi.model.Auditoria;
import org.apache.james.mime4j.dom.datetime.DateTime;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IAuditoriaService extends ICRUD<Auditoria,Integer>{
    List<?> insertarAuditoria(String pDescripcion, String pIp_maquina, String pTpparticipe,String pcparticipe, String pUsername,String ptipo);
}
