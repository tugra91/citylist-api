package com.kuehnenagel.citylist.service.impl;

import com.kuehnenagel.citylist.common.constant.SystemErrorEnum;
import com.kuehnenagel.citylist.common.exception.SystemException;
import com.kuehnenagel.citylist.dto.input.SignInInput;
import com.kuehnenagel.citylist.dto.model.Token;
import com.kuehnenagel.citylist.dto.output.SignInOutput;
import com.kuehnenagel.citylist.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ILoginService implements LoginService {

    private final RestTemplate restTemplate;

    @Value(value = "${custom.security.oauth2url}")
    private String OAuth2URL;
    @Value(value = "${custom.security.client-id}")
    private String CLIENTID;
    @Value(value = "${custom.security.client-secret}")
    private String CLIENTSECRET;
    @Value(value = "${custom.security.response-type}")
    private String RESPONSETYPE;
    @Value(value = "${custom.security.scope}")
    private String SCOPE ;
    @Value(value = "${custom.security.redirect-uri}")
    private String REDIRECTURI;

    @Value(value = "${custom.security.oauth2-token-url}")
    private String OAuth2TOKENURL;
    @Value(value = "${custom.security.grant-type}")
    private String GRANTTYPE;

    private static final String CLIENTIDKEY = "client_id";
    private static final String REDIRECTURIKEY = "redirect_uri";
    private static final String CODEKEY = "code";
    private static final String GRANTTYPEKEY = "grant_type";

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
                body.add(CLIENTIDKEY, CLIENTID);
                body.add(REDIRECTURIKEY,REDIRECTURI);
                body.add(CODEKEY, response.getBody());
                body.add(GRANTTYPEKEY, GRANTTYPE);
                HttpEntity<MultiValueMap<String, String>> tokenEntity = new HttpEntity<>(body, createHeaders(CLIENTID, CLIENTSECRET));
                ResponseEntity<Token> tokenResponse =
                        restTemplate.exchange(tokenUrl, HttpMethod.POST, tokenEntity, Token.class);

                if(tokenResponse.getStatusCode().is2xxSuccessful()) {
                    return new SignInOutput(Objects.requireNonNull(tokenResponse.getBody()).access_token());
                } else {
                    throw new SystemException(SystemErrorEnum.ACCESS_TOKEN_SYSTEM_EXCEPTION.getCode(), SystemErrorEnum.ACCESS_TOKEN_SYSTEM_EXCEPTION.getMessage());
                }
            } else if(response.getStatusCode().is4xxClientError()) {
                throw new SystemException(SystemErrorEnum.UNAUTHORIZATION_SYSTEM_EXCEPTION.getCode(), SystemErrorEnum.UNAUTHORIZATION_SYSTEM_EXCEPTION.getMessage());
            } else {
                throw new SystemException(SystemErrorEnum.GENERAL_SYSTEM_EXCEPTION.getCode(), SystemErrorEnum.GENERAL_SYSTEM_EXCEPTION.getMessage());
            }
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.Unauthorized) {
                throw new SystemException(SystemErrorEnum.UNAUTHORIZATION_SYSTEM_EXCEPTION.getCode(), SystemErrorEnum.UNAUTHORIZATION_SYSTEM_EXCEPTION.getMessage());
            }
            throw new SystemException(SystemErrorEnum.GENERAL_SYSTEM_EXCEPTION.getCode(), SystemErrorEnum.GENERAL_SYSTEM_EXCEPTION.getMessage());
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
