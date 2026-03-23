
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_SOLICITUD_VISITA_HISTORIAL")
public class SvSolicitudVisitaHistorialEntity extends BaseEntity implements Serializable {

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

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_ID", length = 36)
    private String solicitudVisitaId;

    @Column(name = "ESTADO_SOLICITUD_VISITA_CODE", length = 20)
    private String estadoSolicitudVisitaCode;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "ESTADO_SOLICITUD_VISITA_CODE", insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoSolicitudVisitaEntity estadoSolicitudVisitaCodeEntity;

    @JoinColumn(name = "SOLICITUD_VISITA_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvSolicitudVisistaEntity solicitudVisitaIDEntity;

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
