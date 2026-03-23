package com.copeinca.apicopeincaprov.modules.core.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUPPLIER_USER")
public class SvSupplierUserEntity {

    @Id
    @Column(name = "SUPPLIER_USER_ID", length = 10)
    private Integer id;

    @NotEmpty
    @Column(name = "DISPLAY_NAME", length = 180)
    private String displayName;

    @Column(name = "EMAIL", length = 70)
    private String email;

    @Column(name = "RUC", length = 20)
    private String ruc;

}
