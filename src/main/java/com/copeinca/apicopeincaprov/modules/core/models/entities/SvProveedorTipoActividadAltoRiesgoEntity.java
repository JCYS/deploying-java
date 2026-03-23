
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "SV_PROVEEDOR_TIPO_ACTIVIDAD_ALTO_RIESGO")
public class SvProveedorTipoActividadAltoRiesgoEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_TIPO_ACTIVIDAD_ALTO_RIESGO_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Column(name = "IS_ACTIVE", length = 2)
    private Boolean isActive;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", nullable = false, length = 50)
    private String tipoActividadAltoRiesgoCode;

    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvTipoActividadAltoRiesgoEntity tipoActividadAltoRiesgoCodeEntity;

    @JoinColumn(name = "PROVEEDOR_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvProveedorEntity proveedorIDEntity;

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
