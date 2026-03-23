package com.copeinca.apicopeincaprov.integration.LaserFiche.services;

import com.copeinca.apicopeincaprov.integration.LaserFiche.factory.DestinationLaserFicheFactory;
import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.*;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response.SoapEliminarArchivoResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body.SoapBody;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body.SoapEnvelope;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body.SoapSerializerUpload;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.body.SubirArchivoECMMasivoEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SoapEnvelopeResponseV2;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LaserFicheClient implements ILaserFiche {

    private final DestinationLaserFicheFactory destinationFactory;

    public static String inputStreamToBase64(InputStream inputStream) throws IOException {
        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
    @Override
    public String generateXml() throws Exception{
        String base64Pdf =
                "JVBERi0xLjQKMSAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgMiAwIFI+PgplbmRvYmoKMiAwIG9iago8PC9UeXBlL1BhZ2VzL0tpZHNbMyAwIFJdL0NvdW50IDE+PgplbmRvYmoKMyAwIG9iago8PC9UeXBlL1BhZ2UvUGFyZW50IDIgMCBSL01lZGlhQm94WzAgMCAxIDFdPj4KZW5kb2JqCnhyZWYKMCA0CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDAxMCAwMDAwMCBuIAowMDAwMDAwMDUzIDAwMDAwIG4gCjAwMDAwMDAxMDEgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDQvUm9vdCAxIDAgUj4+CnN0YXJ0eHJlZgoxNDAKJSVFT0Y=";
        SubirArchivoECMMasivoEnc request = new SubirArchivoECMMasivoEnc(
                "", // pUsuario
                "", // pClave
                "", // pIP
                List.of(base64Pdf), // pArchivoOrigen
                List.of("COPEINCA\\COPETESTING\\"), // pRutaArchivoDestino
                List.of("Matricula;RTA00983"), // pCadenaMD
                true, // pHaciaECM
                "", // pNombreGabinete
                List.of("EXTR Certificado de Origen"), // pNombreTipoDocumento
                List.of("sample.pdf") // pNombresDestino
        );

        // Crear body y envelope
        SoapBody body = new SoapBody(request);
        SoapEnvelope envelope = new SoapEnvelope(body);

        // Serializar a XML
        String soapXml = SoapSerializerUpload.toXml(envelope);

        System.out.println("SOAP Request:\n" + soapXml);

        HttpDestination destination = destinationFactory.create();

        HttpPost post = new HttpPost("/wsECMEnc/IntegracionECM.asmx");

        // 4️⃣ Headers SOAP
        post.setHeader("Content-Type", "text/xml; charset=utf-8");
        post.setHeader("SOAPAction", "\"http://tempuri.org/SubirArchivoECMMasivoEnc\"");

        // 5️⃣ Body SOAP
        post.setEntity(new StringEntity(soapXml, "UTF-8"));

        // 6️⃣ Ejecutar
        HttpResponse response = HttpClientAccessor
                .getHttpClient(destination)
                .execute(post);

        System.out.println(response);
        System.out.println("HTTP Status: " + response.getStatusLine().getStatusCode());

        if (response.getEntity() != null) {
            String responseBody =
                    new String(response.getEntity().getContent().readAllBytes());
            System.out.println("Response:\n" + responseBody);


            JAXBContext jaxbContext = JAXBContext.newInstance(SoapEnvelopeResponseV2.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SoapEnvelopeResponseV2 envelopee = (SoapEnvelopeResponseV2) unmarshaller.unmarshal(new StringReader(responseBody));

            String resultado = null;
//            if (envelopee != null && envelope.getBody() != null && envelopee.getBody().getResponse() != null
//                    && envelopee.getBody().getResponse().getResult() != null) {
//                resultado = envelopee.getBody().getResponse().getResult().getValue();
//            }

            System.out.println("Resultado final: " + envelopee.toString());
        }
        return soapXml;
    }

    @Override
    public SoapEliminarArchivoResponse deleteFilesLaserFiche(IdArchivosECMEnc aIds) throws Exception{

//        IdArchivosECMEnc ids = new IdArchivosECMEnc(
//                List.of("C63F4-2E18D-96DBE-0467E-8A384-66C22-26000")
//        );

        EliminarArchivoECMMasivoEnc operacion =
                new EliminarArchivoECMMasivoEnc(aIds);

        SoapBodyR body = new SoapBodyR(operacion);
        SoapEnvelopeR envelope = new SoapEnvelopeR(body);

        String soapXml = SoapSerializerDelete.toXml(envelope);
        System.out.println(soapXml);



        HttpResponse response = this.callWS("/wsECMEnc/IntegracionECM.asmx","http://tempuri.org/EliminarArchivoECMMasivoEnc",soapXml);

        if (response.getEntity() != null) {
            String responseBody =
                    new String(response.getEntity().getContent().readAllBytes());
            System.out.println("Response:\n" + responseBody);
            JAXBContext context =
                    JAXBContext.newInstance(SoapEliminarArchivoResponse.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            SoapEliminarArchivoResponse result =
                    (SoapEliminarArchivoResponse) unmarshaller.unmarshal(
                            new StringReader(responseBody)
                    );

            System.out.println(result.getBody().getResponse().getResult().toString());
        }
        return null;
    }

    @Override
    public SubirArchivoECMSoapResponse uploadFilesMasive(List<FileUploadDto> aFiles) throws Exception {
        List<String> aArchivoOrigen = new ArrayList<>();
        List<String> aRutaArchivoDestino = new ArrayList<>();
        List<String> aCadenaMD = new ArrayList<>();
        List<String> aNombreTipoDocumento = new ArrayList<>();
        List<String> apNombresDestino = new ArrayList<>();
        List<String> aIdsResults = null;
        for(FileUploadDto item: aFiles){
            aArchivoOrigen.add(inputStreamToBase64(item.getPArchivoOrigen().getInputStream()));
            aRutaArchivoDestino.add(item.getPRutaArchivoDestino());
            aCadenaMD.add(item.getPCadenaMD());
            aNombreTipoDocumento.add(item.getPNombreTipoDocumento());
            apNombresDestino.add(item.getPArchivoOrigen().getOriginalFilename());

        }
        SubirArchivoECMMasivoEnc request = new SubirArchivoECMMasivoEnc(
                "", // pUsuario
                "", // pClave
                "", // pIP
                aArchivoOrigen, // pArchivoOrigen
                aRutaArchivoDestino, // pRutaArchivoDestino
                aCadenaMD, // pCadenaMD
                true, // pHaciaECM
                "", // pNombreGabinete
                aNombreTipoDocumento, // pNombreTipoDocumento
                apNombresDestino // pNombresDestino
        );
        SoapBody body = new SoapBody(request);
        SoapEnvelope envelope = new SoapEnvelope(body);

        // Serializar a XML
        String soapXml = SoapSerializerUpload.toXml(envelope);

        System.out.println("SOAP Request:\n" + soapXml);
        HttpDestination destination = destinationFactory.create();

        HttpPost post = new HttpPost("/wsECMEnc/IntegracionECM.asmx");

        // 4️⃣ Headers SOAP
        post.setHeader("Content-Type", "text/xml; charset=utf-8");
        post.setHeader("SOAPAction", "\"http://tempuri.org/SubirArchivoECMMasivoEnc\"");

        // 5️⃣ Body SOAP
        post.setEntity(new StringEntity(soapXml, "UTF-8"));

        // 6️⃣ Ejecutar
        HttpResponse response = HttpClientAccessor
                .getHttpClient(destination)
                .execute(post);


        System.out.println("HTTP Status: " + response.getStatusLine().getStatusCode());
        System.out.println(response.toString());
        System.out.println("SOAP response fin" );
        JAXBContext jaxbContext = JAXBContext.newInstance(SubirArchivoECMSoapResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        if (response.getEntity() != null) {
            String responseBody =
                    new String(response.getEntity().getContent().readAllBytes());
            //System.out.println("Response:\n" + responseBody);


//            JAXBContext jaxbContext = JAXBContext.newInstance(SubirArchivoECMSoapResponse.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SubirArchivoECMSoapResponse envelopee = (SubirArchivoECMSoapResponse) unmarshaller.unmarshal(new StringReader(responseBody));
            //System.out.println("Response:\n" + envelopee.getBody().getResponse().getResult().toString());
            String resultado = null;
            //System.out.println("Resultado final: " + envelopee.toString());
            return envelopee;
        }else{
            return null;
        }

    }

    public HttpResponse callWS(String sUrl,String sSoapAction, String sSoapXml)throws Exception{
        HttpDestination destination = destinationFactory.create();

//        HttpPost post = new HttpPost("/wsECMEnc/IntegracionECM.asmx");
        HttpPost post = new HttpPost(sUrl);
        post.setHeader("Content-Type", "text/xml; charset=utf-8");
        post.setHeader("SOAPAction", sSoapAction);
//        post.setHeader("SOAPAction", "\"http://tempuri.org/SubirArchivoECMMasivoEnc\"");
        post.setEntity(new StringEntity(sSoapXml, "UTF-8"));
        return HttpClientAccessor
                .getHttpClient(destination)
                .execute(post);
    }
}
