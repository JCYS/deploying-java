package com.copeinca.apicopeincaprov.integration.mail.smtp.core;

import com.copeinca.apicopeincaprov.integration.mail.smtp.core.interfaces.ITemplateProcessor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VelocityProcessor implements ITemplateProcessor {

    private final String BASE_TEMPLATE = "com/copeinca/apicopeincaprov/templates/email/";

    public static final String DEFAULT_TEMPLATE = "EmailTemplateCustomer.html";

    private final VelocityEngine velocityEngine;

    public VelocityProcessor( VelocityEngine velocityEngine ){

        this.velocityEngine = velocityEngine;

    }

    @Override
    public String process( String htmlBody, Map<String, Object> parameters ) {

        //--- Definimos el contexto para añadir el HTML a la plantilla
        VelocityContext contextHtml = new VelocityContext(  );
        contextHtml.put( "CONTENT", htmlBody );

        //--- Combinamos el html de la plantilla con el pasado como parámetro
        String html = write( contextHtml, getTemplateByName( null ) );

        //--- Definimos el contexto con los parámetros - datos
        VelocityContext contextData = new VelocityContext( parameters );

        StringWriter stringWriter = new StringWriter();

        Velocity.evaluate( contextData, stringWriter, "Velocity-Engine", html );

        return stringWriter.toString();

    }

    @Override
    public String process( Map<String, Object> parameters ) {

        return process( parameters, null );

    }

    @Override
    public String process( Map<String, Object> parameters, String templateName ) {

        VelocityContext context = new VelocityContext( parameters );

        Template template = getTemplateByName( templateName );

        return write( context, template );

    }

    @Override
    public String processSanitized( Map<String, Object> parameters, String templateName ) {

        VelocityContext context = new VelocityContext( sanitize( parameters ) );

        Template template = getTemplateByName( templateName );

        return write( context, template );

    }

    private Template getTemplateByName( String templateName ){

        if( templateName == null || templateName.trim().isEmpty() )
            templateName = DEFAULT_TEMPLATE;

        return velocityEngine.getTemplate( BASE_TEMPLATE + templateName );

    }

    private String write( VelocityContext context, Template template ){

        StringWriter writer = new StringWriter(  );

        template.merge( context, writer );

        return writer.toString(  );

    }

    private Map<String, Object> sanitize( Map<String, Object> parameters ) {
        return parameters.entrySet( )
                .stream( )
                .collect( Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue( ) == null ? "" : e.getValue( )
                ) );
    }

}