
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
@Table(name = "SV_PERSONA_RESTRINGIDA")
public class SvPersonaRestringidaEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PERSONA_RESTRINGIDA_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "NUMERO_DOCUMENTO", nullable = false, length = 50)
    private String numeroDocumento;

    @Size(max = 50)
    @Column(name = "LICENCIA_CONDUCIR", length = 50)
    private String licenciaConducir;

    @Size(max = 200)
    @Column(name = "NOMBRE_COMPLETO", length = 200)
    private String nombreCompleto;

    @Size(max = 200)
    @Column(name = "USUARIO_REPORTA", length = 200)
    private String usuarioReporta;

    @Column(name = "RESTRINGIDO_EL", length = 27)
    private LocalDateTime restringidoEl;

    @Column(name = "RESTRINGIDO_HASTA", length = 27)
    private LocalDateTime restringidoHasta;

    @Size(max = 250)
    @Column(name = "OBSERVACIONES", length = 250)
    private String observaciones;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, length = 50)
    private String tipoDocumentoIdentidadCode;

    @Column(name = "SEDE_ORIGINADORA_ID", length = 36, nullable = true)
    private String sedeOriginadoraId;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "SEDE_ORIGINADORA_ID", insertable = false, updatable = false)
    @ManyToOne
    private SvSedeEntity sedeOriginadoraIDEntity;

    @JoinColumn(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvTipoDocumentoIdentidadEntity tipoDocumentoIdentidadCodeEntity;

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
