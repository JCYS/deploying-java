
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_TRABAJADOR")
public class SvTrabajadorEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "TRABAJADOR_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Size(max = 50)
    @Column(name = "NRO_DOCUMENTO_IDENTIDAD", length = 50)
    private String nroDocumentoIdentidad;

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
    @Column(name = "IND_TRABAJO_ALTO_RIESGO", nullable = false, length = 2)
    private Boolean indTrabajoAltoRiesgo;

    @NotNull
    @Column(name = "IND_PREVENCIONISTA", nullable = false, length = 2)
    private Boolean indPrevencionista;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false, length = 2)
    private Boolean isActive;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, length = 50)
    private String tipoDocumentoIdentidadCode;

    @Column(name = "ESTADO_SSO_CODE", nullable = false, length = 20)
    private String estadoSsoCode;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvTipoDocumentoIdentidadEntity tipoDocumentoIdentidadCodeEntity;

    @JoinColumn(name = "PROVEEDOR_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvProveedorEntity proveedorIDEntity;

    @JoinColumn(name = "ESTADO_SSO_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvEstadoSsoEntity estadoSsoCodeEntity;

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
