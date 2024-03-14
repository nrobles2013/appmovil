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
public class MovimientosDTO {
    private Long id;
    private String cparticipe;
    private Date fecha;
    private Usuario usuario_id;
    private String tipoopera;
    private String destipoopera;
    private String descripcion;
    private Double monto;
    private Date fregistro;

}
