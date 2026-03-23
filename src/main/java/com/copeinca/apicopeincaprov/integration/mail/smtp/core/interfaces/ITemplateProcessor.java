package com.copeinca.apicopeincaprov.integration.mail.smtp.core.interfaces;

import java.util.Map;

public interface ITemplateProcessor {

    String process( String htmlBody, Map<String, Object> parameters );

    String process( Map<String, Object> parameters );

    String process( Map<String, Object> parameters, String templateName );

    String processSanitized( Map<String, Object> parameters, String templateName );
}