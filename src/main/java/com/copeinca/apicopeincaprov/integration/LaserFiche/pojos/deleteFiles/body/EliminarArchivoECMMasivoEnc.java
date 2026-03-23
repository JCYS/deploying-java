package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "pIdArchivosECMEnc" })
public class EliminarArchivoECMMasivoEnc {

    @XmlElement(
            name = "pIdArchivosECMEnc",
            namespace = "http://tempuri.org/"
    )
    private IdArchivosECMEnc pIdArchivosECMEnc;

    public IdArchivosECMEnc getpIdArchivosECMEnc() {
        return pIdArchivosECMEnc;
    }

    public void setpIdArchivosECMEnc(IdArchivosECMEnc pIdArchivosECMEnc) {
        this.pIdArchivosECMEnc = pIdArchivosECMEnc;
    }
}