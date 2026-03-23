package com.copeinca.apicopeincaprov.integration.mail.smtp.config;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityEngineConfig {

    @Bean
    public VelocityEngine initVelocityEngine(  ){

        VelocityEngine engine = new VelocityEngine(  );
        engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
        engine.setProperty( Velocity.RESOURCE_LOADER, "classpath" );
        engine.setProperty( "input.encoding", "UTF-8" );

        engine.setProperty( Velocity.RUNTIME_REFERENCES_STRICT , false);

        engine.init();

        return engine;

    }

}