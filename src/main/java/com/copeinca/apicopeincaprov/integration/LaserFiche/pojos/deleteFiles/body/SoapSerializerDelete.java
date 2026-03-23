package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class SoapSerializerDelete {
    public static String toXml(SoapEnvelopeR request) throws Exception {

        JAXBContext context =
                JAXBContext.newInstance(SoapEnvelopeR.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);

        System.out.println(writer.toString());

        return  writer.toString();
    }
}
