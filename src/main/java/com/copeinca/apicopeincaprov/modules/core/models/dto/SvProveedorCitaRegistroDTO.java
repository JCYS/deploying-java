package com.copeinca.apicopeincaprov.modules.core.models.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SvProveedorCitaRegistroDTO {
    private SvProveedorDTO prov;
    private SvSolicitudVisistaDTO infoSolicitud;
    private List<SvAdjuntoDTO> deletesFiles;
    private List<SvSolicitudVisitaAreaAutorizadoraDTO> listAreas;
    private List<SvSolicitudVisitaAreaAutorizadoraDTO> deleteListAreas;
    private List<SvSolicitudVisitaMaterialDTO> listMaterials;
    private List<SvSolicitudVisitaMaterialDTO> deleteListMaterials;
    private List<SvSolicitudVisitaEquipoDTO> listEquipos;
    private List<SvSolicitudVisitaEquipoDTO> deleteListEquipos;
    private List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> listTAAR;
    private List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> deleteListTAAR;
    private List<SvSolicitudVisitaZonaDTO> listZona;
    private List<SvSolicitudVisitaZonaDTO> deleteListZona;
    private List<SvAdjuntosIndexDto> hsFilesSSO;
    private List<SvAdjuntosIndexDto> hsFilesQA;
    private List<SvAdjuntosIndexDto> hsFilesAAR;
    private List<SvAdjuntosIndexDto> hsFilesAB;
    private List<SvAdjuntosIndexDto> hsFilesNA;
    private List<SvAdjuntosIndexDto> hsFilesNG;
    private List<SvAdjuntosIndexDto> hsFilesEM;
    private List<SvAdjuntosIndexDto> hsFilesTB;
    private List<ViewTrabajadorReportDTO> listTrbjs;
}
