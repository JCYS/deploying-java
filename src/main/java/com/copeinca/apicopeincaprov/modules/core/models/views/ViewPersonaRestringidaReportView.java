
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
@Table(name = "VIEW_PERSONA_RESTRINGIDA_REPORT")
public class ViewPersonaRestringidaReportView implements Serializable {

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

    @NotEmpty
    @Size(max = 50)
    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", nullable = false, length = 50)
    private String tipoDocumentoIdentidadCode;

    @Size(max = 36)
    @Column(name = "SEDE_ORIGINADORA_ID", length = 36)
    private String sedeOriginadoraId;

    @Size(max = 100)
    @Column(name = "TDI_NOMBRE", length = 100)
    private String tdiNombre;

    @Size(max = 100)
    @Column(name = "SEDE_NOMBRE", length = 100)
    private String sedeNombre;

    @Size(max = 20)
    @Column(name = "SEDE_AMBITO", length = 20)
    private String sedeAmbito;

    @NotNull
    @Column(name = "RESTRICCION_ACTIVA", nullable = false, length = 2)
    private Boolean restriccionActiva;

    @Digits(integer = 19, fraction = 0)
    @Column(name = "DIAS_RESTRICCION", precision = 19)
    private Long diasRestriccion;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "TIPO_RESTRICCION", nullable = false, length = 10)
    private String tipoRestriccion;

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
