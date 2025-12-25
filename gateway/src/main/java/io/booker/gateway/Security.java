package io.booker.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class Security {
    @Autowired
    ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout((logout) -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                );
        return http.build();
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        var handler = new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);

        handler.setPostLogoutRedirectUri("{baseUrl}");

        return handler;
    }
}
