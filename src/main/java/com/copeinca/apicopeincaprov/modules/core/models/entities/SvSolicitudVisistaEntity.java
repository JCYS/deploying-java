
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_SOLICITUD_VISISTA")
public class SvSolicitudVisistaEntity extends BaseEntity implements Serializable {

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

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    @Column(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, length = 20)
    private String estadoCalidadAmbientalCode;

    @Column(name = "ESTADO_SOLICITUD_VISITA_CODE", nullable = false, length = 20)
    private String estadoSolicitudVisitaCode;

    @Column(name = "SEDE_ID", nullable = false, length = 36)
    private String sedeId;

    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    @Column(name = "PERSONAL_ID", length = 36)
    private String personalId;

    @Column(name = "CORRELATIVO")
    private Long correlativo;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoCalidadAmbientalEntity estadoCalidadAmbientalCodeEntity;

    @JoinColumn(name = "ESTADO_SSO_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoSsoEntity estadoSsoCodeEntity;

    @JoinColumn(name = "PERSONAL_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvPersonalEntity personalIDEntity;

    @JoinColumn(name = "PROVEEDOR_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvProveedorEntity proveedorIDEntity;

    @JoinColumn(name = "ESTADO_SOLICITUD_VISITA_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoSolicitudVisitaEntity estadoSolicitudVisitaCodeEntity;

    @JoinColumn(name = "SEDE_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvSedeEntity sedeIDEntity;



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
