
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/views/View.java.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@EqualsAndHashCode
@Entity
@Table(name = "VIEW_ADJUNTO_REPORT")
public class ViewAdjuntoReportView implements Serializable {

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

    @Size(max = 36)
    @Column(name = "PROVEEDOR_ID", length = 36)
    private String proveedorId;

    @Size(max = 36)
    @Column(name = "TRABAJADOR_ID", length = 36)
    private String trabajadorId;

    @Size(max = 20)
    @Column(name = "TIPO_DOCUMENTO_PLANILLA_CODE", length = 20)
    private String tipoDocumentoPlanillaCode;

    @Size(max = 36)
    @Column(name = "SOLICITUD_VISITA_ID", length = 36)
    private String solicitudVisitaId;

    @Size(max = 50)
    @Column(name = "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", length = 50)
    private String tipoActividadAltoRiesgoCode;

    @Size(max = 200)
    @Column(name = "TDP_DESCRIPCION", length = 200)
    private String tdpDescripcion;

    @Size(max = 50)
    @Column(name = "TDP_AMBITO", length = 50)
    private String tdpAmbito;

    @Size(max = 50)
    @Column(name = "TDP_INFLUENCIA", length = 50)
    private String tdpInfluencia;

    @Size(max = 200)
    @Column(name = "TDP_DEPENDENCIA", length = 200)
    private String tdpDependencia;

    @Size(max = 200)
    @Column(name = "TDP_CAMPO_PLANTILLA_LF", length = 200)
    private String tdpCampoPlantillaLf;

    @Column(name = "TDP_OBLIGATORIO", length = 2)
    private Boolean tdpObligatorio;

    @Column(name = "TDP_VENCIMIENTO", length = 2)
    private Boolean tdpVencimiento;

    @NotEmpty
    @Size(max = 16)
    @Column(name = "TIPO_PROPIETARIO", nullable = false, length = 16)
    private String tipoPropietario;

    @NotNull
    @Column(name = "DOCUMENTO_VENCIDO", nullable = false, length = 2)
    private Boolean documentoVencido;

    @Digits(integer = 19, fraction = 0)
    @Column(name = "DIAS_HASTA_VENCIMIENTO", precision = 19)
    private Long diasHastaVencimiento;

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
