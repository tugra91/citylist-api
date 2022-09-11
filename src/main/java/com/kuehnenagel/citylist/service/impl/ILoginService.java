package com.kuehnenagel.citylist.service.impl;

import com.kuehnenagel.citylist.dto.input.SignInInput;
import com.kuehnenagel.citylist.dto.model.Token;
import com.kuehnenagel.citylist.dto.output.SignInOutput;
import com.kuehnenagel.citylist.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ILoginService implements LoginService {

    private final RestTemplate restTemplate;

    private static final String OAuth2URL = "http://127.0.0.1:8082/oauth2/authorize";
    private static final String CLIENTID = "citylist-api";
    private static final String CLIENTSECRET = "z!yyklm37";
    private static final String RESPONSETYPE = "code";
    private static final String SCOPE = "api_endpoint_security";
    private static final String REDIRECTURI = "http://127.0.0.1:8082/welcome/getCode";

    private static final String OAuth2TOKENURL = "http://127.0.0.1:8082/oauth2/token";
    private static final String GRANTTYPE = "authorization_code";

    @Override
    public SignInOutput signIn(SignInInput input) {
        try {
            URI url = new URI(OAuth2URL + "?client_id=" + CLIENTID +
                    "&response_type=" + RESPONSETYPE +
                    "&scope=" + SCOPE +
                    "&redirect_uri=" + REDIRECTURI);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(getHttpHeaderForOAuthCode(input.username(), input.password()));

            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if(response.getStatusCode().is2xxSuccessful()) {
                URI tokenUrl = new URI(OAuth2TOKENURL);
                HttpHeaders body = new HttpHeaders();
                body.add("client_id", CLIENTID);
                body.add("redirect_uri",REDIRECTURI);
                body.add("code", response.getBody());
                body.add("grant_type", GRANTTYPE);
                HttpEntity<MultiValueMap<String, String>> tokenEntity = new HttpEntity<>(body, createHeaders(CLIENTID, CLIENTSECRET));
                ResponseEntity<Token> tokenResponse =
                        restTemplate.exchange(tokenUrl, HttpMethod.POST, tokenEntity, Token.class);

                if(tokenResponse.getStatusCode().is2xxSuccessful()) {
                    return new SignInOutput(Objects.requireNonNull(tokenResponse.getBody()).access_token());
                } else {
                    return new SignInOutput(null);
                }

            } else {
                return new SignInOutput(null);
            }
        } catch (Exception e) {
            return new SignInOutput(null);
        }
    }

    private HttpHeaders getHttpHeaderForOAuthCode(String username, String password) {
        return new HttpHeaders() {
            private static final long serialVersionUID = 8998352702764652594L;

            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(
                        auth.getBytes(Charset.forName("UTF-8")) );
                String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
            }};
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
        private static final long serialVersionUID = 8998352702764652594L;
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(
                        auth.getBytes(Charset.forName("UTF-8")) );
                String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
                set("Content-Type", "application/x-www-form-urlencoded");
            }};
    }
}
