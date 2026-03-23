
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/views/View.java.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "VIEW_PERSONAL_REPORT")
public class ViewPersonalReportView implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PERSONAL_SEDE_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false, length = 2)
    private Boolean isActive;

    @NotEmpty
    @Size(max = 36)
    @Column(name = "PERSONAL_ID", nullable = false, length = 36)
    private String personalId;

    @NotEmpty
    @Size(max = 36)
    @Column(name = "SEDE_ID", nullable = false, length = 36)
    private String sedeId;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "PERSONAL_USUARIO", nullable = false, length = 100)
    private String personalUsuario;

    @Size(max = 200)
    @Column(name = "PERSONAL_NOMBRE_USUARIO", length = 200)
    private String personalNombreUsuario;

    @Size(max = 100)
    @Column(name = "PERSONAL_CODIGO", length = 100)
    private String personalCodigo;

    @Size(max = 200)
    @Column(name = "PERSONAL_DESCRIPCION", length = 200)
    private String personalDescripcion;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "SEDE_NAME", nullable = false, length = 100)
    private String sedeName;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "SEDE_AMBITO", nullable = false, length = 20)
    private String sedeAmbito;

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
