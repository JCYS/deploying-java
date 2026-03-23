package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class EliminarArchivoECMMasivoEncResult {

    @XmlElement(name = "string")
    private List<String> string;
}