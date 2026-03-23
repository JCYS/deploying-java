package com.copeinca.apicopeincaprov.integration.mail.smtp.core;

import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.SendEmailInput;
import lombok.*;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by Administrador on 13/11/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSender {

    private final Logger logger = LoggerFactory.getLogger( this.getClass( ) );

    @Value( "${destination.email}" )
    protected String destinationEmail;


    public String removeDuplicates( String s ) {
        return new LinkedHashSet<String>( Arrays.asList( s.split( "," ) ) ).toString( ).replaceAll( "(^\\[|\\]$)", "" ).replace( ", ", "," );
    }

    protected void sendEmailSAP( SendEmailInput input ) {

        String valueURL = this.destinationEmail;

        try {

            String recipients = input.getTo( );
            String subject = "<![CDATA[" + input.getSubject() + "]]>";
//            String body = input.getMailBody();
            String body = input.getMailBody( ).replaceAll( "\\r\\n|\\r|\\n", " " )

                    // Comillas
                    .replaceAll( "\"", "'" )

                    // Separadores problemáticos
                    .replaceAll( ",", "&#44" )
                    .replaceAll( ";", "&#59" )
//                    .replaceAll( ":", "&#58" )
                    .replaceAll( "\\.", "&#46" )

                    // Tildes minúsculas
                    .replaceAll( "á", "&aacute" )
                    .replaceAll( "é", "&eacute" )
                    .replaceAll( "í", "&iacute" )
                    .replaceAll( "ó", "&oacute" )
                    .replaceAll( "ú", "&uacute" )

                    // Tildes mayúsculas
                    .replaceAll( "Á", "&Aacute" )
                    .replaceAll( "É", "&Eacute" )
                    .replaceAll( "Í", "&Iacute" )
                    .replaceAll( "Ó", "&Oacute" )
                    .replaceAll( "Ú", "&Uacute" )

                    // Ñ / ñ
                    .replaceAll( "ñ", "&ntilde" )
                    .replaceAll( "Ñ", "&Ntilde" );

            OkHttpClient client = new OkHttpClient( );
            MediaType mediaType = MediaType.parse( "text/xml" );

            String tempXml = buildXml( subject, body, recipients );

            RequestBody requestBody = RequestBody.create( tempXml, mediaType );

            Buffer buffer = new Buffer( );
            requestBody.writeTo( buffer );
            System.out.println("CORREO: INICIO CPI");
            System.out.println(tempXml);
            System.out.println("CORREO: FIN CPI");
            Request request = new Request.Builder( )
                    .url( valueURL )
                    .post( requestBody )
                    .addHeader( "content-type", "text/xml" )
                    .addHeader( "Authorization", "Basic c2ItNDczOWIwMTItZjBiNS00MTZlLWEzYTAtODliOWMxNzYyZDNmIWIzMTAxMzh8aXQtcnQtY3BpLXByZC1jZi1kZHM1N2Y0bSFiNTYxODY6ZTllMWM5NTAtMGJmMC00OGI2LWE1M2QtZGIyZGZmNTcxNDVmJHJ3aHlvaE5sbnJBLWk3U3RHU0hGaTVXZ1NURTRZRDIwcFNaYjZIY0l5bXc9" ) //pprincipe
                    .build( );

            Response response = client.newCall( request ).execute( );

        } catch( Exception e ) {

            logger.error( "Connectivity operation failed", e );

        }
    }

    private String buildXml( String subject, String body, String recipients ) {
        String tempXml = "";
        tempXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
        tempXml = tempXml + "xmlns:sen=\"http://www.example.org/sendMailIprovider/\">";
        tempXml = tempXml + "<soapenv:Header/>";
        tempXml = tempXml + "<soapenv:Body>";
        tempXml = tempXml + "<sen:NewOperation>";
        tempXml = tempXml + "<auxiliar>auxi</auxiliar>";
        tempXml = tempXml + "<subject>" + subject + "</subject>";
        tempXml = tempXml + "<body><![CDATA[" + body + "]]></body>";
        int count = 0;
        if( recipients != null && !recipients.isEmpty( ) ) {

            recipients = removeDuplicates( recipients );

            String[] to = recipients.split( "," );

            for( String t : to ) {
                if( !t.isEmpty( ) ) {
                    count++;
                    tempXml = tempXml + "<to" + count + ">" + t.trim( ) + "</to" + count + ">";
                }
            }

        }
        tempXml = tempXml + "</sen:NewOperation>";
        tempXml = tempXml + "</soapenv:Body>";
        tempXml = tempXml + "</soapenv:Envelope>";

        return tempXml;

    }

}