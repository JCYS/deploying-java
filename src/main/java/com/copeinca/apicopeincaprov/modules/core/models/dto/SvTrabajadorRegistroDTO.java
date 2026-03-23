package com.copeinca.apicopeincaprov.modules.core.models.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SvTrabajadorRegistroDTO {

    private SvTrabajadorDTO trabajador;
    private SvProveedorDTO proveedor;
    private List<SvAdjuntoDTO> deletesFiles;
    private List<SvAdjuntosIndexDto> hsFilesSSO;
    private List<SvAdjuntosIndexDto> hsFilesAAR;
    private List<SvAdjuntosIndexDto> hsFilesP;
    private List<SvTrabajadorTipoActividadAltoRiesgoDTO> listTAAR;
    private List<SvTrabajadorTipoActividadAltoRiesgoDTO> deleteListTAAR;

}
