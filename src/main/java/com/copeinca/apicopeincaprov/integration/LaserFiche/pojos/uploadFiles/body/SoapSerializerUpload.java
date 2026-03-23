package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body;

import jakarta.xml.bind.*;
import java.io.StringWriter;

public class SoapSerializerUpload {

    public static String toXml(SoapEnvelope envelope) throws Exception {
        JAXBContext context = JAXBContext.newInstance(SoapEnvelope.class, SoapBody.class, SubirArchivoECMMasivoEnc.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(envelope, writer);

        return writer.toString();
    }
}