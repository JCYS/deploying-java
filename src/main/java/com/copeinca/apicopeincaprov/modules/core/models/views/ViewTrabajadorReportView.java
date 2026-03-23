
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
@Table(name = "VIEW_TRABAJADOR_REPORT")
public class ViewTrabajadorReportView implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "TRABAJADOR_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 36)
    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    @Size(max = 20)
    @Column(name = "PROV_NRO_DOCUMENTO_IDENTIDAD", length = 20)
    private String provNroDocumentoIdentidad;

    @Size(max = 200)
    @Column(name = "PROV_NOMBRE_FISCAL", length = 200)
    private String provNombreFiscal;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, length = 50)
    private String tipoDocumentoIdentidadCode;

    @Size(max = 50)
    @Column(name = "NRO_DOCUMENTO_IDENTIDAD", length = 50)
    private String nroDocumentoIdentidad;

    @Size(max = 100)
    @Column(name = "TDI_NOMBRE", length = 100)
    private String tdiNombre;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    @Size(max = 200)
    @Column(name = "SSO_MOTIVO", length = 200)
    private String ssoMotivo;

    @NotNull
    @Column(name = "SO_TIENE_VIGENCIA", nullable = false, length = 2)
    private Boolean soTieneVigencia;

    @Column(name = "SSO_FECHA_INICIO_VIGENCIA", length = 10)
    private LocalDate ssoFechaInicioVigencia;

    @Column(name = "SSO_FECHA_FIN_VIGENCIA", length = 10)
    private LocalDate ssoFechaFinVigencia;

    @NotNull
    @Column(name = "IS_DELETED", nullable = false, length = 2)
    private Boolean isDeleted;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false, length = 2)
    private Boolean isActive;

    @Size(max = 100)
    @Column(name = "SSO_ESTADO_NOMBRE", length = 100)
    private String ssoEstadoNombre;

    @Size(max = 100)
    @Column(name = "ESTA_RESTRINGIDO", length = 100)
    private String isValid;


    @Size(max = 100)
    @Column(name = "SSO_ESTADO_VIGENTE", length = 100)
    private String ssoEstadoVigente;

    @NotNull
    @Column(name = "IND_TRABAJO_ALTO_RIESGO", nullable = false, length = 2)
    private Boolean indTrabajoAltoRiesgo;

    @NotNull
    @Column(name = "IND_PREVENCIONISTA", nullable = false, length = 2)
    private Boolean indPrevencionista;

    @Size(max = 5000)
    @Column(name = "TAR_CODIGOS", length = 5000)
    private String tarCodigos;

    @Size(max = 5000)
    @Column(name = "TAR_DESCRIPCIONES", length = 5000)
    private String tarDescripciones;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_SSO_ADJUNTOS", nullable = false, precision = 19)
    private Long nDocsSsoAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_CALIDAD_ADJUNTOS", nullable = false, precision = 19)
    private Long nDocsCalidadAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AAR_ADJUNTOS", nullable = false, precision = 19)
    private Long nDocsAarAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AB_ADJUNTOS", nullable = false, precision = 19)
    private Long nDocsAbAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_PREV_ADJUNTOS", nullable = false, precision = 19)
    private Long nDocsPrevAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @Column(name = "N_DOCS_TOTALES_VALID", nullable = false, precision = 19)
    private Long nDocsTotalesValid;


    @Digits(integer = 19, fraction = 0)
    @Column(name = "N_DOCS_TOTALES", nullable = false, precision = 19)
    private Long nDocsTotales;

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
