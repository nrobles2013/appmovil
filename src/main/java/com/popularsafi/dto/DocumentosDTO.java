package com.popularsafi.dto;


import com.popularsafi.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class DocumentosDTO {
    private Long id;
    private String tpparticipe;
    private String cparticipe;
    private Usuario usuario_id;
    private String tipodocumento;
    private String nomdocumentoctacte;
    private String nomdocumentoliqui;
    private Integer anio;
    private Integer periodo;
    private String concepto ;
    private String trimestre ;
    private Date fregistro;


}
