
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
@Table(name = "VIEW_PROVEEDOR_REPORT")
public class ViewProveedorReportView implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Size(max = 20)
    @Column(name = "NRO_DOCUMENTO_IDENTIDAD", length = 20)
    private String nroDocumentoIdentidad;

    @Size(max = 200)
    @Column(name = "NOMBRE_FISCAL", length = 200)
    private String nombreFiscal;

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Size(max = 200)
    @Column(name = "DIRECCION", length = 200)
    private String direccion;

    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "NACIONAL", length = 2)
    private Boolean nacional;

    @Size(max = 200)
    @Column(name = "CONTACTO_PRINCIPAL", length = 200)
    private String contactoPrincipal;

    @Size(max = 20)
    @Column(name = "CONTACTO_PRINCIPAL_TELEFONO", length = 20)
    private String contactoPrincipalTelefono;

    @Size(max = 50)
    @Column(name = "ORIGEN_REGISTRO", length = 50)
    private String origenRegistro;

    @Size(max = 5000)
    @Column(name = "SEDES_IDS", length = 5000)
    private String sedesIds;

    @Size(max = 5000)
    @Column(name = "SEDES_NOMBRES", length = 5000)
    private String sedesNombres;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    @NotNull
    @Column(name = "SSO_TIENE_VIGENCIA", nullable = false, length = 2)
    private Boolean ssoTieneVigencia;

    @Column(name = "SSO_FECHA_INICIO_VIGENCIA", length = 10)
    private LocalDate ssoFechaInicioVigencia;

    @Size(max = 10)
    @Column(name = "SSO_FECHA_FIN_VIGENCIA", length = 10)
    private String ssoFechaFinVigencia;

    @Size(max = 200)
    @Column(name = "SSO_MOTIVO", length = 200)
    private String ssoMotivo;

    @Size(max = 50)
    @Column(name = "SSO_USUARIO_EVALUADOR", length = 50)
    private String ssoUsuarioEvaluador;

    @Column(name = "SSO_FECHA_EVALUACION", length = 27)
    private LocalDateTime ssoFechaEvaluacion;

    @Size(max = 100)
    @Column(name = "SSO_ESTADO_NOMBRE", length = 100)
    private String ssoEstadoNombre;

    @Size(max = 200)
    @Column(name = "SSO_ESTADO_DESCRIPCION", length = 200)
    private String ssoEstadoDescripcion;

    @Size(max = 100)
    @Column(name = "SSO_ESTADO_VIGENTE", length = 100)
    private String ssoEstadoVigente;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, length = 20)
    private String estadoCalidadAmbientalCode;

    @NotNull
    @Column(name = "CA_TIENE_VIGENCIA", nullable = false, length = 2)
    private Boolean caTieneVigencia;

    @Column(name = "CA_FECHA_INICIO_VIGENCIA", length = 10)
    private LocalDate caFechaInicioVigencia;

    @Column(name = "CA_FECHA_FIN_VIGENCIA", length = 10)
    private LocalDate caFechaFinVigencia;

    @Size(max = 200)
    @Column(name = "CA_MOTIVO", length = 200)
    private String caMotivo;

    @Size(max = 50)
    @Column(name = "CA_USUARIO_EVALUADOR", length = 50)
    private String caUsuarioEvaluador;

    @Column(name = "CA_FECHA_EVALUACION", length = 27)
    private LocalDateTime caFechaEvaluacion;

    @Size(max = 100)
    @Column(name = "CA_ESTADO_NOMBRE", length = 100)
    private String caEstadoNombre;

    @Size(max = 200)
    @Column(name = "CA_ESTADO_DESCRIPCION", length = 200)
    private String caEstadoDescripcion;

    @Size(max = 100)
    @Column(name = "CA_ESTADO_VIGENTE", length = 100)
    private String caEstadoVigente;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_TRABAJADORES_APTOS", nullable = false, precision = 19)
    private Long nTrabajadoresAptos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_TRABAJADORES_TOTALES", nullable = false, precision = 19)
    private Long nTrabajadoresTotales;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_PROV_SSO", nullable = false, precision = 19)
    private Long nDocsProvSso;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_PROV_SSO_VALID", nullable = false, precision = 19)
    private Long nDocsProvSsoValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_CA", nullable = false, precision = 19)
    private Long nDocsCa;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_CA_VALID", nullable = false, precision = 19)
    private Long nDocsCaValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AAR", nullable = false, precision = 19)
    private Long nDocsAar;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AAR_VALID", nullable = false, precision = 19)
    private Long nDocsAarValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AB", nullable = false, precision = 19)
    private Long nDocsAb;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_AB_VALID", nullable = false, precision = 19)
    private Long nDocsAbValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    @Column(name = "N_DOCS_TRABAJ_SSO", nullable = false, precision = 19)
    private Long nDocsTrabajSso;

    @NotNull
    @Column(name = "IND_REALIZA_ACTIVIDAD_ALTO_RIESGO", nullable = false, length = 2)
    private Boolean indRealizaActividadAltoRiesgo;

    @NotNull
    @Column(name = "IND_REALIZA_ACTIVIDAD_A_BORDO", nullable = false, length = 2)
    private Boolean indRealizaActividadABordo;

    @Size(max = 5000)
    @Column(name = "TAR_CODIGOS", length = 5000)
    private String tarCodigos;

    @Size(max = 5000)
    @Column(name = "TAR_DESCRIPCIONES", length = 5000)
    private String tarDescripciones;

    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDate createdDate;

    @Column(name = "NOTIFICA_CA", nullable = false, length = 2)
    private Boolean notificaCa;

    @Column(name = "NOTICICA_SSO", nullable = false, length = 2)
    private Boolean notificaSso;




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
