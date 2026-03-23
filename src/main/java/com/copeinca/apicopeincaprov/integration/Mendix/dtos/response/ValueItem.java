package com.copeinca.apicopeincaprov.integration.Mendix.dtos.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValueItem {
//    @JsonProperty("IDProveedor")
    private String IDProveedor;
    private String RUC;
    private String RazonSocial;
    private String PersonaContacto;
    private String MailContacto;
    private String TelefonoContacto;
    private String Email;

    private Sedes Sedes;

    private String EstadoSSOProveedor;
    private String MotivoSSOProveedor;
    private String FlagFechaVigenciaSSOProveedor;
    private String FechaVigenciaSSOProveedor;

    private DocumentosProveedorSSO DocumentosProveedorSSO;

    private String ActividadAltoRiesgoProveedor;
    private TipoAltoRiesgoProveedor TipoAltoRiesgoProveedor;

    private String ActividadABordoProveedor;
    private DocumsABordoProveedor DocumsABordoProveedor;

    private String EstadoCalidadAmbientalProveedor;
    private String MotivoCalidadAmbientalProveedor;
    private String FlagFechaVigenciaCalidadAmbientalProveedor;
    private String FechaVigenciaCalidadAmbientalProveedor;

    private DocumentosProveedorCalidadAmbiental DocumentosProveedorCalidadAmbiental;

    private Integer NroTrabajadores;

    private Trabajadores Trabajadores;
}
