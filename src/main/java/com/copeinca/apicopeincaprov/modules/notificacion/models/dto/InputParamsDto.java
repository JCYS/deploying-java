package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputParamsDto {
    @NotEmpty
    private String recipients;

    private String estadoSSO;
    private String estadoCalAmb;
    private String detTrabajadores;
    private String urlBtp;
    private String codProveedor;
    private String razonSocial;
    private String sedes;
    private String motivoObs;
    private String motivoInhabilitacion;

    private String usuarioVisitado;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String ordenServicio;
    private String descVisita;
    private String obsEspeciales;
    private String areasAVisitar;
    private String zonasAVisitar;

    private String altoRiesgo;
    private String actABordo;
    private String andamios;
    private String grua;
    private String equiposMoviles;
    private String trabajoBuceo;

    private SvProveedorDTO svProveedor;
    private SvSedeDTO sedeDTO;
    private List<SvTrabajadorDTO> trabajadores;
}
