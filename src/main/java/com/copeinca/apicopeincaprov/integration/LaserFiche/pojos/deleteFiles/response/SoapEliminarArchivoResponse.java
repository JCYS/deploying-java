package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response;

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
public class SoapEliminarArchivoResponse {

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
                name = "EliminarArchivoECMMasivoEncResponse",
                namespace = "http://tempuri.org/"
        )
        private EliminarArchivoECMMasivoEncResponse response;
    }

    // =========================
    // RESPONSE
    // =========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EliminarArchivoECMMasivoEncResponse {

        @XmlElement(
                name = "EliminarArchivoECMMasivoEncResult",
                namespace = "http://tempuri.org/"
        )
        private EliminarArchivoECMMasivoEncResult result;
    }

    // =========================
    // RESULT
    // =========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EliminarArchivoECMMasivoEncResult {

        @XmlElement(
                name = "string",
                namespace = "http://tempuri.org/"
        )
        private List<String> string;
    }
}
