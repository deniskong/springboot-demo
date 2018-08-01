package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class Auth2Application {

    public static void main(String[] args) {
        System.out.println("titi");
        SpringApplication.run(Auth2Application.class, args);
    }



   /* @Primary
    @Bean
    DefaultOAuth2RequestFactory defaultOAuth2RequestFactory() {
        System.out.println("lolco");
        return new DefaultOAuth2RequestFactory(null);
    }

    @Primary
    @Bean
    public AuthenticationKeyGenerator authenticationKeyGenerator() {
        System.out.println("lolco");
        return new DefaultAuthenticationKeyGenerator();
    }

    @Primary
    @Bean
    public TokenStore tokenStore() {
        System.out.println("lolo");
        InMemoryTokenStore token =  new InMemoryTokenStore();
        token.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator());
        return token;
    }*/

}


