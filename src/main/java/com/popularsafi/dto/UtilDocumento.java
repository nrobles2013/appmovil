package com.popularsafi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilDocumento {
    private Integer id;
    private String documento;
    private Date fecha;
    private String ruta;
}
