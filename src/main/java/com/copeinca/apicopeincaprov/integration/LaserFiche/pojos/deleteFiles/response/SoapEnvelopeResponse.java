package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "Envelope",
        namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
public class SoapEnvelopeResponse {

    @XmlElement(
            name = "Body",
            namespace = "http://schemas.xmlsoap.org/soap/envelope/"
    )
    private SoapBodyResponse body;
}
