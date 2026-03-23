
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_ADJUNTO")
public class SvAdjuntoEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "ADJUNTO_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 200)
    @Column(name = "NOMBRE_ARCHIVO", nullable = false, length = 200)
    private String nombreArchivo;

    @Size(max = 50)
    @Column(name = "ID_REPOSITORIO", length = 50)
    private String idRepositorio;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "CLASIFICATION", nullable = false, length = 50)
    private String clasification;

    @Column(name = "FECHA_DOCUMENTO", length = 10)
    private LocalDate fechaDocumento;

    @Column(name = "FECHA_VENCIMIENTO", length = 10)
    private LocalDate fechaVencimiento;

    @Size(max = 200)
    @Column(name = "RUTA_RELATIVA", length = 200)
    private String rutaRelativa;

    @Size(max = 200)
    @Column(name = "SSO_COMENTARIO_REVISION", length = 200)
    private String ssoComentarioRevision;

    @Size(max = 200)
    @Column(name = "CA_COMENTARIO_REVISION", length = 200)
    private String caComentarioRevision;

    @Size(max = 200)
    @Column(name = "MOTIVO", length = 200)
    private String motivo;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_ID", length = 36)
    private String proveedorId;

    @Column(name = "TRABAJADOR_ID", length = 36)
    private String trabajadorId;

    @Column(name = "TIPO_DOCUMENTO_PLANILLA_CODE", length = 20)
    private String tipoDocumentoPlanillaCode;

    @Column(name = "SOLICITUD_VISITA_ID", length = 36)
    private String solicitudVisitaId;

    @Column(name = "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", length = 50)
    private String tipoActividadAltoRiesgoCode;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", insertable = false, updatable = false)
    @ManyToOne
    private SvTipoActividadAltoRiesgoEntity tipoActividadAltoRiesgoCodeEntity;

    @JoinColumn(name = "PROVEEDOR_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvProveedorEntity proveedorIDEntity;

    @JoinColumn(name = "TIPO_DOCUMENTO_PLANILLA_CODE", insertable = false, updatable = false)
    @ManyToOne
    private SvTipoDocumentoPlanillaEntity tipoDocumentoPlanillaCodeEntity;

    @JoinColumn(name = "SOLICITUD_VISITA_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvSolicitudVisistaEntity solicitudVisitaIDEntity;

    @JoinColumn(name = "TRABAJADOR_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvTrabajadorEntity trabajadorIDEntity;

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
