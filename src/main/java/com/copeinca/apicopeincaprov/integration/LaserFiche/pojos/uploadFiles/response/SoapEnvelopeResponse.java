package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlRootElement(
        name = "Envelope",
        namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapEnvelopeResponse {

    @XmlElement(
            name = "Body",
            namespace = "http://schemas.xmlsoap.org/soap/envelope/"
    )
    private SoapBodyResponse body;

    // getters y setters
}
