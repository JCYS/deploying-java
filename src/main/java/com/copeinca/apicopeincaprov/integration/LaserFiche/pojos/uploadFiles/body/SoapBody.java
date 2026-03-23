package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SoapBody {

    @XmlElement(name = "SubirArchivoECMMasivoEnc", namespace = "http://tempuri.org/")
    private SubirArchivoECMMasivoEnc request;
}
