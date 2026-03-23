package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SvRegistroProveedorDto {
    private List<SvProveedorSedeDTO> sedes;
    private SvProveedorDTO proveedorInfo;
    private List<SvTipoActividadAltoRiesgoDTO> svTipoActividadAltoRiesgoDTOList;
    private List<SvProveedorTipoActividadAltoRiesgoDTO> svProveedorTipoActividadAltoRiesgoDTOList;
    private List<SvAdjuntoDTO> deletesFiles;
    private List<SvAdjuntosIndexDto> infoFilesSSO;
    private List<SvAdjuntosIndexDto> infoFilesQA;
    private List<SvAdjuntosIndexDto> infoFilesAB;
    private List<SvAdjuntosIndexDto> infoFilesAAR;
    private List<SvProveedorTipoActividadAltoRiesgoDTO> deleteSvTipoActividadAltoRiesgo;

}
