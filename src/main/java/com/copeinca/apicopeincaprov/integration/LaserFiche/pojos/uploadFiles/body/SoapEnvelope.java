package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SoapEnvelope {

    @XmlElement(name = "Header", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private Object header = null;

    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private SoapBody body;

    // Constructor solo con body
    public SoapEnvelope(SoapBody body) {
        this.body = body;
        this.header = null;
    }

}