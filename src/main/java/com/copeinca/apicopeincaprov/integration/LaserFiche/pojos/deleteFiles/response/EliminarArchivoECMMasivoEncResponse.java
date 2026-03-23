package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response;


import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "eliminarArchivoECMMasivoEncResult" })
public class EliminarArchivoECMMasivoEncResponse {

    @XmlElement(
            name = "EliminarArchivoECMMasivoEncResult",
            namespace = "http://tempuri.org/"
    )
    private EliminarArchivoECMMasivoEncResult eliminarArchivoECMMasivoEncResult;
}