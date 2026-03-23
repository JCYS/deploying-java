
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/entities/Entity.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.entities;

import com.copeinca.apicopeincaprov.commons.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "SV_USUARIO")
public class SvUsuarioEntity extends BaseEntity implements Serializable {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @Column(name = "USUARIO_ID", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    @Id
    private String id;

    @Size(max = 100)
    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Size(max = 200)
    @Column(name = "APELLIDOS", length = 200)
    private String apellidos;

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Size(max = 50)
    @Column(name = "IDENTIFICADOR_EXTERNO", length = 50)
    private String identificadorExterno;

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
