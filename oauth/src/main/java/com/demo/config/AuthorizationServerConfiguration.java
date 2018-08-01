package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("john")
                .secret("123")
                // .accessTokenValiditySeconds(10)
                // .refreshTokenValiditySeconds(5)
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write");
    }


    @Bean
    public DefaultTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        //  defaultTokenServices.setClientDetailsService(clientDetailsService);
        //defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public InMemoryTokenStore tokenStore() {
        InMemoryTokenStore store = new InMemoryTokenStore();
        store.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator());
        return store;
    }

    @Bean
    public MyTokenEnhancer tokenEnhancer() {
        return new MyTokenEnhancer();
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenServices(defaultTokenServices()).authenticationManager(authenticationManager);
    }

    private static class MyTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            //  final User user = (User) authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("userId", "coucou");
            //   additionalInfo.put("roles", user.getAuthorities());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        }
    }
}