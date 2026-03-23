package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "Envelope",
        namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
public class SubirArchivoECMSoapResponse {

    @XmlElement(
            name = "Body",
            namespace = "http://schemas.xmlsoap.org/soap/envelope/"
    )
    private Body body;

    // =========================
    // BODY
    // =========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {

        @XmlElement(
                name = "SubirArchivoECMMasivoEncResponse",
                namespace = "http://tempuri.org/"
        )
        private SubirArchivoECMMasivoEncResponse response;
    }

    // =========================
    // RESPONSE
    // =========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SubirArchivoECMMasivoEncResponse {

        @XmlElement(
                name = "SubirArchivoECMMasivoEncResult",
                namespace = "http://tempuri.org/"
        )
        private SubirArchivoECMMasivoEncResult result;
    }

    // =========================
    // RESULT
    // =========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SubirArchivoECMMasivoEncResult {

        @XmlElement(
                name = "string",
                namespace = "http://tempuri.org/"
        )
        private List<String> string;
    }
}
