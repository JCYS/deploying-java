
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "SV_SOLICITUD_VISITA_DETALLE")
public class SvSolicitudVisitaDetalleEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_DETALLE_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Size(max = 200)
    @Column(name = "PROVEEDOR_COMENTARIO", length = 200)
    private String proveedorComentario;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_ID", nullable = false, length = 36)
    private String solicitudVisitaId;

    @Column(name = "TRABAJADOR_ID", nullable = false, length = 36)
    private String trabajadorId;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "SOLICITUD_VISITA_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvSolicitudVisistaEntity solicitudVisitaIDEntity;

    @JoinColumn(name = "TRABAJADOR_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvTrabajadorEntity trabajadorIDEntity;

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
