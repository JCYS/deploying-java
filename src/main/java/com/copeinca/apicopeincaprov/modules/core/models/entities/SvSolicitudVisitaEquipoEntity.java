
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

@Log4j2
@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_SOLICITUD_VISITA_EQUIPO")
public class SvSolicitudVisitaEquipoEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_EQUIPO_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Size(max = 50)
    @Column(name = "MARCA", length = 50)
    private String marca;

    @Size(max = 50)
    @Column(name = "MODELO", length = 50)
    private String modelo;

    @Size(max = 30)
    @Column(name = "NUMERO_SERIE", length = 30)
    private String numeroSerie;

    @Size(max = 50)
    @Column(name = "TIPO_EQUIPO", length = 50)
    private String tipoEquipo;

    @Size(max = 50)
    @Column(name = "CODIGO_EQUIPO", length = 50)
    private String codigoEquipo;

    @Digits(integer = 7, fraction = 3)
    @Column(name = "CANTIDAD", precision = 10, scale = 3)
    private BigDecimal cantidad;

    // -----------------------------------------------------------------
    // Foreign Key Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_ID", nullable = false, length = 36)
    private String solicitudVisitaId;

    // -----------------------------------------------------------------
    // Many to One Relations
    // -----------------------------------------------------------------

    @JoinColumn(name = "SOLICITUD_VISITA_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private SvSolicitudVisistaEntity solicitudVisitaIDEntity;

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
