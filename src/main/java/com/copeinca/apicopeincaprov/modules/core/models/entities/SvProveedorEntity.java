
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_PROVEEDOR")
public class SvProveedorEntity extends BaseEntity implements Serializable {

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

    @Size(max = 200)
    @Column(name = "CONTACTO_PRINCIPAL", length = 200)
    private String contactoPrincipal;

    @Size(max = 20)
    @Column(name = "CONTACTO_PRINCIPAL_TELEFONO", length = 20)
    private String contactoPrincipalTelefono;

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

    @NotNull
    @Column(name = "IND_REALIZA_ACTIVIDAD_ALTO_RIESGO", nullable = false, length = 2)
    private Boolean indRealizaActividadAltoRiesgo;

    @NotNull
    @Column(name = "IND_REALIZA_ACTIVIDAD_A_BORDO", nullable = false, length = 2)
    private Boolean indRealizaActividadABordo;

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

    @Size(max = 50)
    @Column(name = "ORIGEN_REGISTRO", length = 50)
    private String origenRegistro;

    @Column(name = "IND_PROVEEDOR_NOTIFICADO_CREACION", length = 2)
    private Boolean indProveedorNotificadoCreacion;

    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "NACIONAL", length = 2)
    private Boolean nacional;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, length = 20)
    private String estadoCalidadAmbientalCode;

    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    @Column(name = "NOTIFICA_CA", length = 2)
    private Boolean notificaCa;

    @Column(name = "NOTIFICA_SSO", length = 2)
    private Boolean notificaSso;
    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "ESTADO_SSO_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoSsoEntity estadoSsoCodeEntity;

    @JoinColumn(name = "ESTADO_CALIDAD_AMBIENTAL_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoCalidadAmbientalEntity estadoCalidadAmbientalCodeEntity;

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
