package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SubirArchivoECMMasivoEnc", namespace = "http://tempuri.org/")
public class SubirArchivoECMMasivoEnc {

    @XmlElement(name = "pUsuario", namespace = "http://tempuri.org/")
    private String pUsuario;

    @XmlElement(name = "pClave", namespace = "http://tempuri.org/")
    private String pClave;

    @XmlElement(name = "pIP", namespace = "http://tempuri.org/")
    private String pIP;

    @XmlElementWrapper(name = "pArchivoOrigen", namespace = "http://tempuri.org/")
    @XmlElement(name = "base64Binary", namespace = "http://tempuri.org/")
    private List<String> pArchivoOrigen;

    @XmlElementWrapper(name = "pRutaArchivoDestino", namespace = "http://tempuri.org/")
    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private List<String> pRutaArchivoDestino;

    @XmlElementWrapper(name = "pCadenaMD", namespace = "http://tempuri.org/")
    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private List<String> pCadenaMD;

    @XmlElement(name = "pHaciaECM", namespace = "http://tempuri.org/")
    private Boolean pHaciaECM;

    @XmlElement(name = "pNombreGabinete", namespace = "http://tempuri.org/")
    private String pNombreGabinete;

    @XmlElementWrapper(name = "pNombreTipoDocumento", namespace = "http://tempuri.org/")
    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private List<String> pNombreTipoDocumento;

    @XmlElementWrapper(name = "pNombresDestino", namespace = "http://tempuri.org/")
    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private List<String> pNombresDestino;
}

