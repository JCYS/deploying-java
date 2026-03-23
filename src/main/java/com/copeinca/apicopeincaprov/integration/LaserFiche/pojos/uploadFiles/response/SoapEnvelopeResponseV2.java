package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;


import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SoapEnvelopeResponseV2 {

    @XmlElement(
            name = "Body",
            namespace = "http://schemas.xmlsoap.org/soap/envelope/"
    )
    private Body body;

    // ================= INNER CLASSES =================

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {

        @XmlElement(
                name = "SubirArchivoECMMasivoEncResponse",
                namespace = "http://tempuri.org/"
        )
        private SubirArchivoECMMasivoEncResponse response;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SubirArchivoECMMasivoEncResponse {

        @XmlElement(name = "SubirArchivoECMMasivoEncResult")
        private SubirArchivoECMMasivoEncResult result;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"values"})
    public static class SubirArchivoECMMasivoEncResult {

        @XmlElement(name = "string")
        private List<String> values;
    }
}
