package com.copeinca.apicopeincaprov.integration.mail.smtp.services;

import com.copeinca.apicopeincaprov.integration.mail.smtp.core.NotificationSender;
import com.copeinca.apicopeincaprov.integration.mail.smtp.core.interfaces.ITemplateProcessor;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.EmailConfig;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.NotifyInput;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.SendEmailInput;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.SendEmailResponse;
import com.copeinca.apicopeincaprov.integration.mail.smtp.services.interfaces.IEmailSenderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class EmailSenderService extends NotificationSender implements IEmailSenderService {

    private final ResourceLoader resourceLoader;
    private final ITemplateProcessor iTemplateProcessor;


    @Value("${com.innovax.isLocal}")
    private Boolean isLocal;

    @Override
    public SendEmailResponse send( NotifyInput input ) {

        //--- Obtenemos la configuración del coreo basándonos en el código del evento
        EmailConfig emailConfig = EmailConfig.builder( )
//        admin_iprovider@copeinca.com.pe
                .from( "admin_iprovider@copeinca.com.pe" )
                .eventCode( input.getEventNotifyEnum( ).getCode( ) )
                .subject( input.getEventNotifyEnum( ).getSubject( ) )
                .build( );

        //--- Obtenemos las copias ocultas configuradas
        String bcc = "";

        //--- Armamos el body
        String content = iTemplateProcessor.processSanitized( input.getData( ), input.getEventNotifyEnum( ).getTemplate( ) );
        System.out.println("CORREO: INICIO");
        System.out.println(content);
        System.out.println("CORREO: FIN");

        //--- Obtenemos el subject
        String subject = buildSubject( emailConfig.getSubject( ), input.getData( ) );

        SendEmailInput sendEmailInput = SendEmailInput.builder( )
                .attachments( input.getAttachments( ) )
                .from( emailConfig.getFrom( ) )
                .subject( subject )
                .to( input.getTo( ) )
                .cc( input.getCc( ) )
                .mailBody( content )
                .bcc( bcc )
                .build( );

        SendEmailResponse response = null;

        sendEmailSAP( sendEmailInput );

        //COPY
//        SendEmailInput COPY_TEMP = SendEmailInput.builder( )
//                .attachments( input.getAttachments( ) )
//                .from( emailConfig.getFrom( ) )
//                .subject( subject )
//                .to( "123456789@yopmail.com")
//                //.cc("julio.yupanqui.soria@gmail.com")
//                .mailBody( content )
//                .bcc( bcc )
//                .build( );
//
//        //SendEmailResponse response = null;
//
//        sendEmailSAP( COPY_TEMP );

        return response;

    }

    private String buildSubject( String subject, Map<String, Object> data ) {

        AtomicReference<String> response = new AtomicReference<>( subject );

        data.forEach( ( key, value ) -> {

            if( value instanceof String )
                response.set( response.get( ).replace( "${" + key + "}", value.toString( ) ) );

        } );

        return response.get( );

    }

}