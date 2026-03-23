
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "SV_PROVEEDOR_SEDE")
public class SvProveedorSedeEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_SEDE_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false, length = 2)
    private Boolean isActive;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "PROVEEDOR_ID", nullable = false, length = 36)
    private String proveedorId;

    @Column(name = "SEDE_ID", nullable = false, length = 36)
    private String sedeId;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "PROVEEDOR_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvProveedorEntity proveedorIDEntity;

    @JoinColumn(name = "SEDE_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvSedeEntity sedeIDEntity;

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
