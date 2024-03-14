package com.popularsafi.config;

import com.popularsafi.dto.DocumentosDTO;
import com.popularsafi.dto.MovimientosDTO;
import com.popularsafi.dto.NotificacionDTO;
import com.popularsafi.model.Documentos;
import com.popularsafi.model.Movimientos;
import com.popularsafi.model.Notificacion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class MapperConfig {


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("notificacionMapper")
    public ModelMapper notificacionMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<NotificacionDTO, Notificacion> typeMap=mapper.createTypeMap(NotificacionDTO.class,Notificacion.class);
        typeMap.addMapping(NotificacionDTO::getFecha,(dest,v) -> dest.setFecha((Date) v));
        typeMap.addMapping(NotificacionDTO::getAsunto,(dest,v) -> dest.setAsunto((String) v));
        typeMap.addMapping(NotificacionDTO::getMensaje,(dest,v) -> dest.setMensaje((String) v));
        return mapper;
    }

    @Bean("movimientosMapper")
    public ModelMapper movimientosMapper() {
        ModelMapper mapper = new ModelMapper();
        //Escritura
        TypeMap<MovimientosDTO, Movimientos> typeMap1 = mapper.createTypeMap(MovimientosDTO.class, Movimientos.class);
        typeMap1.addMapping(MovimientosDTO::getId, (dest, v) -> dest.setId((Long) v));
        typeMap1.addMapping(MovimientosDTO::getCparticipe, (dest, v) -> dest.setCparticipe((String) v));
        typeMap1.addMapping(MovimientosDTO::getFecha,(dest,v)-> dest.setFecha((Date) v));
        typeMap1.addMapping(MovimientosDTO::getUsuario_id,(dest,v)-> dest.getUsuario_id().setUsuario_id((Integer) v));
        typeMap1.addMapping(MovimientosDTO::getTipoopera,(dest,v)-> dest.setTipoopera((String) v));
        typeMap1.addMapping(MovimientosDTO::getDestipoopera,(dest,v)-> dest.setDestipoopera((String) v));
        typeMap1.addMapping(MovimientosDTO::getDescripcion,(dest,v)-> dest.setDescripcion((String) v));
        typeMap1.addMapping(MovimientosDTO::getMonto,(dest,v)-> dest.setMonto((Double) v));
        typeMap1.addMapping(MovimientosDTO::getFregistro,(dest,v)-> dest.setFregistro((Date) v));
        return mapper;
    }


    @Bean("documentosMapper")
    public ModelMapper documentosMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<DocumentosDTO, Documentos> typeMap1 = mapper.createTypeMap(DocumentosDTO.class, Documentos.class);
        typeMap1.addMapping(DocumentosDTO::getId, (dest, v) -> dest.setId((Long) v));
        typeMap1.addMapping(DocumentosDTO::getTpparticipe, (dest, v) -> dest.setTpparticipe((String) v));
        typeMap1.addMapping(DocumentosDTO::getCparticipe,(dest,v)-> dest.setCparticipe((String) v));
        typeMap1.addMapping(DocumentosDTO::getUsuario_id,(dest,v)-> dest.getUsuario_id().setUsuario_id((Integer) v));
        typeMap1.addMapping(DocumentosDTO::getTipodocumento,(dest,v)-> dest.setTipodocumento((String) v));
        typeMap1.addMapping(DocumentosDTO::getNomdocumentoctacte,(dest,v)-> dest.setNomdocumentoctacte((String) v));
        typeMap1.addMapping(DocumentosDTO::getNomdocumentoliqui,(dest,v)-> dest.setNomdocumentoliqui((String) v));
        typeMap1.addMapping(DocumentosDTO::getAnio,(dest,v)-> dest.setAnio((Integer) v));
        typeMap1.addMapping(DocumentosDTO::getPeriodo,(dest,v)-> dest.setPeriodo((Integer) v));
        typeMap1.addMapping(DocumentosDTO::getConcepto,(dest,v)-> dest.setConcepto((String) v));
        typeMap1.addMapping(DocumentosDTO::getTrimestre,(dest,v)-> dest.setTrimestre((String) v));
        typeMap1.addMapping(DocumentosDTO::getFregistro,(dest,v)-> dest.setFregistro((Date) v));
        return mapper;
    }

}
