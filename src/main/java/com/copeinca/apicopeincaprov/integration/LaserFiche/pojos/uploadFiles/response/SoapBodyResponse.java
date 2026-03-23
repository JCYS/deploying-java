package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;


import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapBodyResponse {

    @XmlElement(
            name = "SubirArchivoECMMasivoEncResponse",
            namespace = "http://tempuri.org/"
    )
    private SubirArchivoECMMasivoEncResponse response;

    // getters y setters
}