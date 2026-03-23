package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapBodyResponse {

    @XmlElement(
            name = "EliminarArchivoECMMasivoEncResponse",
            namespace = "http://tempuri.org/"
    )
    private EliminarArchivoECMMasivoEncResponse eliminarArchivoECMMasivoEncResponse;
}