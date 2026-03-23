package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class SubirArchivoECMMasivoEncResult {

    @XmlElement(
            name = "string",
            namespace = "http://tempuri.org/"
    )
    private List<String> string;

    // getters y setters
}
