
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/views/View.java.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "VIEW_SOLICITUD_VISITA_REPORT")
public class ViewSolicitudVisitaReportView implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "CODIGO_VISITA", nullable = false, length = 20)
    private String codigoVisita;

    @Size(max = 50)
    @Column(name = "CODIGO_SISTEMA_EXTERNO", length = 50)
    private String codigoSistemaExterno;

    @Column(name = "FECHA_INICIO", length = 27)
    private LocalDateTime fechaInicio;

    @Column(name = "FECHA_FIN", length = 27)
    private LocalDateTime fechaFin;

    @Size(max = 200)
    @Column(name = "MOTIVO_VISITA", length = 200)
    private String motivoVisita;

    @Column(name = "FECHA_APROBACION_PERSONA_VISITADA", length = 27)
    private LocalDateTime fechaAprobacionPersonaVisitada;

    @Column(name = "FECHA_OBSERVACION_ADMINISTRADOR", length = 27)
    private LocalDateTime fechaObservacionAdministrador;

    @Size(max = 250)
    @Column(name = "OBSERVACIONES", length = 250)
    private String observaciones;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "NRO_ORDEN_SERVICIO", nullable = false, length = 50)
    private String nroOrdenServicio;

    @NotNull
    @Column(name = "IND_ACTIVIDAD_ALTO_RIESGO", nullable = false, length = 2)
    private Boolean indActividadAltoRiesgo;

    @NotNull
    @Column(name = "IND_ACTIVIDAD_A_BORDO", nullable = false, length = 2)
    private Boolean indActividadABordo;

    @NotNull
    @Column(name = "IND_REQUIERE_ANDAMIOS", nullable = false, length = 2)
    private Boolean indRequiereAndamios;

    @NotNull
    @Column(name = "IND_REQUIERE_GRUA", nullable = false, length = 2)
    private Boolean indRequiereGrua;

    @NotNull
    @Column(name = "IND_REQUIERE_EQUIPOS_MOVILES", nullable = false, length = 2)
    private Boolean indRequiereEquiposMoviles;

    @NotNull
    @Column(name = "IND_TRABAJO_BUCEO", nullable = false, length = 2)
    private Boolean indTrabajoBuceo;

    @Size(max = 200)
    @Column(name = "SSO_MOTIVO", length = 200)
    private String ssoMotivo;

    @Size(max = 200)
    @Column(name = "SSO_DESCARGO", length = 200)
    private String ssoDescargo;

    @Size(max = 200)
    @Column(name = "CA_MOTIVO", length = 200)
    private String caMotivo;

    @Size(max = 200)
    @Column(name = "CA_DESCARGO", length = 200)
    private String caDescargo;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, length = 20)
    private String estadoCalidadAmbientalCode;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_SOLICITUD_VISITA_CODE", nullable = false, length = 20)
    private String estadoSolicitudVisitaCode;

    @NotEmpty
    @Size(max = 36)
    @Column(name = "SEDE_ID", nullable = false, length = 36)
    private String sedeId;

    @NotEmpty
    @Size(max = 36)
    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    @Size(max = 36)
    @Column(name = "PERSONAL_ID", length = 36)
    private String personalId;

    @Size(max = 100)
    @Column(name = "SEDE_NOMBRE", length = 100)
    private String sedeNombre;

    @Size(max = 20)
    @Column(name = "SEDE_AMBITO", length = 20)
    private String sedeAmbito;

    @Size(max = 200)
    @Column(name = "PERSONAL_NOMBRE_USUARIO", length = 200)
    private String personalNombreUsuario;

    @Size(max = 100)
    @Column(name = "PERSONAL_USUARIO", length = 100)
    private String personalUsuario;

    @Size(max = 200)
    @Column(name = "PROVEEDOR_NOMBRE_FISCAL", length = 200)
    private String proveedorNombreFiscal;

    @Size(max = 20)
    @Column(name = "PROVEEDOR_NRO_DOCUMENTO_IDENTIDAD", length = 20)
    private String proveedorNroDocumentoIdentidad;

    @Email
    @Size(max = 100)
    @Column(name = "PROVEEDOR_EMAIL", length = 100)
    private String proveedorEmail;

    @Size(max = 200)
    @Column(name = "PROVEEDOR_CONTACTO_PRINCIPAL", length = 200)
    private String proveedorContactoPrincipal;

    @Size(max = 100)
    @Column(name = "ESV_NOMBRE", length = 100)
    private String esvNombre;

    @Size(max = 100)
    @Column(name = "SSO_ESTADO_NOMBRE", length = 100)
    private String ssoEstadoNombre;

    @Size(max = 200)
    @Column(name = "SSO_ESTADO_DESCRIPCION", length = 200)
    private String ssoEstadoDescripcion;

    @Size(max = 100)
    @Column(name = "CA_ESTADO_NOMBRE", length = 100)
    private String caEstadoNombre;

    @Size(max = 200)
    @Column(name = "CA_ESTADO_DESCRIPCION", length = 200)
    private String caEstadoDescripcion;

    @Size(max = 5000)
    @Column(name = "TAR_DESCRIPCIONES", length = 5000)
    private String tarDescripciones;

    @Digits(integer = 19, fraction = 0)
    @Column(name = "N_DIAS", precision = 19)
    private Long nDias;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_TRABAJADORES", nullable = false, precision = 19)
    private Long nTrabajadores;

    @Size(max = 5000)
    @Column(name = "AREAS_IDS", length = 5000)
    private String areasIds;

    @Size(max = 5000)
    @Column(name = "AREAS_NOMBRES", length = 5000)
    private String areasNombres;

    @Size(max = 5000)
    @Column(name = "ZONAS_IDS", length = 5000)
    private String zonasIds;

    @Size(max = 5000)
    @Column(name = "ZONAS_NOMBRES", length = 5000)
    private String zonasNombres;

    @Size(max = 5000)
    @Column(name = "CONTACTO_PRINCIPAL_TELEFONO", length = 5000)
    private String contactoPrincipalTelefono;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // One to One Relations
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // One to Many Relations
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Many to Many Relations
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // One to Virtual One Relations
    // -----------------------------------------------------------------

}
