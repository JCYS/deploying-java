
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/views/View.java.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.views;

import jakarta.persistence.*;
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
@EqualsAndHashCode
@Entity
@Table(name = "VIEW_SOLICITUD_VISITA_HISTORIAL_REPORT")
public class ViewSolicitudVisitaHistorialReportView implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_HISTORIAL_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Column(name = "FECHA_HORA", length = 27)
    private LocalDateTime fechaHora;

    @Size(max = 100)
    @Column(name = "USUARIO", length = 100)
    private String usuario;

    @Size(max = 200)
    @Column(name = "AMBITO", length = 200)
    private String ambito;

    @Size(max = 200)
    @Column(name = "REVISION", length = 200)
    private String revision;

    @Size(max = 200)
    @Column(name = "RPTA_BY_USUARIO", length = 200)
    private String rptaByUsuario;

    @Size(max = 36)
    @Column(name = "SOLICITUD_VISITA_ID", length = 36)
    private String solicitudVisitaId;

    @Size(max = 20)
    @Column(name = "ESTADO_SOLICITUD_VISITA_CODE", length = 20)
    private String estadoSolicitudVisitaCode;

    @Size(max = 50)
    @Column(name = "SOLVIS_NRO_ORDEN_SERVICIO", length = 50)
    private String solvisNroOrdenServicio;

    @Size(max = 20)
    @Column(name = "SOLVIS_CODIGO_VISITA", length = 20)
    private String solvisCodigoVisita;

    @Size(max = 100)
    @Column(name = "ESV_NOMBRE", length = 100)
    private String esvNombre;

    @Size(max = 20)
    @Column(name = "PREV_ESTADO_SOLICITUD_VISITA_CODE", length = 20)
    private String prevEstadoSolicitudVisitaCode;

    @Size(max = 100)
    @Column(name = "PREV_ESTADO_NOMBRE", length = 100)
    private String prevEstadoNombre;

    @Column(name = "PREV_FECHA_HORA", length = 27)
    private LocalDateTime prevFechaHora;

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
