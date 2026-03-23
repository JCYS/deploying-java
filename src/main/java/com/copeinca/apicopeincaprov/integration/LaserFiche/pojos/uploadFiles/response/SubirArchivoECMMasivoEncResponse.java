package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class SubirArchivoECMMasivoEncResponse {

    @XmlElement(name = "SubirArchivoECMMasivoEncResult")
    private SubirArchivoECMMasivoEncResult result;

    // getters y setters
}
