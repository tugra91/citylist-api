package com.kuehnenagel.citylist.config;

import com.kuehnenagel.citylist.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
@EnableWebSecurity
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value(value = "${custom.path.user.prefix}")
    private String userPath;
    @Value(value= "${custom.security.publickey}")
    private String publicKey;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(userPath).hasAnyAuthority("SCOPE_ROLE_ALLOW_EDIT")
                .and()
                .oauth2ResourceServer((oauth) -> oauth.jwt(jwtspec -> {
                    try {
                        jwtspec.decoder(jwtDecoder());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }) );
    }

    @Bean
    JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return NimbusJwtDecoder.withPublicKey(ConfigUtil.generatePublicKey(publicKey)).build();
    }
}
