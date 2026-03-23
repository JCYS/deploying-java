package com.copeinca.apicopeincaprov.security;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Log4j2
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    @Value( "${com.innovax.isLocal}" )
    Boolean isLocal;

    @Autowired
    XsuaaServiceConfiguration xsuaaServiceConfiguration;

    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if( isLocal )
            return filterChainLocal( http );
        http
                .sessionManagement()
                // session is created by approuter
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // demand specific scopes depending on intended request
                .authorizeRequests()

                .requestMatchers("/**").authenticated()
                .anyRequest().denyAll() // deny anything not configured above
                .and()
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(getJwtAuthoritiesConverter());

        return http.build();
    }

    public SecurityFilterChain filterChainLocal( HttpSecurity http ) throws Exception {

        http
                .csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( o -> o.anyRequest( ).permitAll( ) )
                .cors( o -> o.configurationSource( corsFilter( ) ) )
                .sessionManagement( o -> o.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
        ;

        return http.build( );

    }
    public CorsConfigurationSource corsFilter( ) {

        log.debug( "************************************************************" );
        log.debug( "********************* Configurando CORS" );
        log.debug( "************************************************************" );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource( );
        CorsConfiguration config = new CorsConfiguration( );
        config.addAllowedOrigin( CorsConfiguration.ALL );
        config.addAllowedHeader( CorsConfiguration.ALL );
        config.addAllowedMethod( CorsConfiguration.ALL );
        config.addExposedHeader( CorsConfiguration.ALL );
        //config.setAllowCredentials( false );
        source.registerCorsConfiguration( "/**", config );

        log.debug( "************************************************************" );
        log.debug( "CORS configurados" );
        log.debug( "************************************************************" );

        return source;

    }

    /**
     * Customizes how GrantedAuthority are derived from a Jwt
     *
     * @returns jwt converter
     */
    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
        return converter;
    }

}
