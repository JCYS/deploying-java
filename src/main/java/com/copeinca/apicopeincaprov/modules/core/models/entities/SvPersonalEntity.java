
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

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_PERSONAL")
public class SvPersonalEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PERSONAL_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "USUARIO", nullable = false, length = 100)
    private String usuario;

    @Size(max = 200)
    @Column(name = "NOMBRE_USUARIO", length = 200)
    private String nombreUsuario;

    @Size(max = 100)
    @Column(name = "CODIGO", length = 100)
    private String codigo;

    @Size(max = 200)
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Size(max = 100)
    @Column(name = "AMBITO", length = 100)
    private String ambito;

    @Size(max = 100)
    @Column(name = "INFLUENCIA", length = 100)
    private String influencia;

    @Size(max = 100)
    @Column(name = "DEPENDENCIA", length = 100)
    private String dependencia;

    @Size(max = 100)
    @Column(name = "CAMPO_PLANTILLA_LF", length = 100)
    private String campoPlantillaLf;

    @Size(max = 2)
    @Column(name = "OBLIGATORIO", length = 2)
    private String obligatorio;

    @Size(max = 10)
    @Column(name = "VENCIMIENTO", length = 10)
    private String vencimiento;

    @Size(max = 20)
    @Column(name = "NUMERO_CASO", length = 20)
    private String numeroCaso;

    @Size(max = 200)
    @Column(name = "MENSAJE", length = 200)
    private String mensaje;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", length = 50)
    private String tipoDocumentoIdentidadCode;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "TIPO_DOCUMENTO_IDENTIDAD_CODE", insertable = false, updatable = false)
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
