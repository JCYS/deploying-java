package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body;

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
public class SoapEnvelopeR {

    @XmlElement(
            name = "Body",
            namespace = "http://schemas.xmlsoap.org/soap/envelope/"
    )
    private SoapBodyR body;

    // getters y setters
    public SoapBodyR getBody() {
        return body;
    }

    public void setBody(SoapBodyR body) {
        this.body = body;
    }
}