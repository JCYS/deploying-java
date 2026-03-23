package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapBodyR {

    @XmlElement(
            name = "EliminarArchivoECMMasivoEnc",
            namespace = "http://tempuri.org/"
    )
    private EliminarArchivoECMMasivoEnc eliminarArchivoECMMasivoEnc;

    public EliminarArchivoECMMasivoEnc getEliminarArchivoECMMasivoEnc() {
        return eliminarArchivoECMMasivoEnc;
    }

    public void setEliminarArchivoECMMasivoEnc(EliminarArchivoECMMasivoEnc eliminarArchivoECMMasivoEnc) {
        this.eliminarArchivoECMMasivoEnc = eliminarArchivoECMMasivoEnc;
    }
}
