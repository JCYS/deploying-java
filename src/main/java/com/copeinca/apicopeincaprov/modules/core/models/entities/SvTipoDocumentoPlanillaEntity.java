
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_TIPO_DOCUMENTO_PLANILLA")
public class SvTipoDocumentoPlanillaEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @NotEmpty
    @Size(max = 20)
    @Column(name = "TIPO_DOCUMENTO_PLANILLA_CODE", length = 20)
    @Id
    private String id;

    @Size(max = 200)
    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Size(max = 50)
    @Column(name = "AMBITO", length = 50)
    private String ambito;

    @Size(max = 50)
    @Column(name = "INFLUENCIA", length = 50)
    private String influencia;

    @Size(max = 200)
    @Column(name = "DEPENDENCIA", length = 200)
    private String dependencia;

    @Size(max = 200)
    @Column(name = "CAMPO_PLANTILLA_LF", length = 200)
    private String campoPlantillaLf;

    @NotNull
    @Column(name = "OBLIGATORIO", nullable = false, length = 2)
    private Boolean obligatorio;

    @NotNull
    @Column(name = "VENCIMIENTO", nullable = false, length = 2)
    private Boolean vencimiento;

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
