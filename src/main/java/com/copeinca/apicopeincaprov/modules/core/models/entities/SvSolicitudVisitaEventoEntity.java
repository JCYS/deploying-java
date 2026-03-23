
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SV_SOLICITUD_VISITA_EVENTO")
public class SvSolicitudVisitaEventoEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "SOLICITUD_VISITA_EVENTO_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @NotNull
    @Column(name = "FECHA_EVENTO", nullable = false, length = 27)
    private LocalDateTime fechaEvento;

    @Size(max = 50)
    @Column(name = "USUARIO", length = 50)
    private String usuario;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "EVENTO", nullable = false, length = 100)
    private String evento;

    @Size(max = 100)
    @Column(name = "AMBITO", length = 100)
    private String ambito;

    @Size(max = 100)
    @Column(name = "REVISION", length = 100)
    private String revision;

    @Size(max = 200)
    @Column(name = "RESPUESTA_USUARIO", length = 200)
    private String respuestaUsuario;

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
