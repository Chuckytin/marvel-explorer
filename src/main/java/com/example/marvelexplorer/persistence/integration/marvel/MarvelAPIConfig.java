package com.example.marvelexplorer.persistence.integration.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private long timeStamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;

    @Value("${integration.marvel.private-key}")
    private String privateKey;

    public Map<String, String> getAuthenticationQueryParams() {
        Map<String, String> securityQueryParams = new HashMap<>();

        securityQueryParams.put("ts", Long.toString(timeStamp));
        securityQueryParams.put("apykey", publicKey);
        securityQueryParams.put("hash", getHash());

        return securityQueryParams;
    }

    /**
     * Genera el código hash para la autenticación de la aplicación del lado del Servidor.
     * Según la documentación web de marvel el hash debe ser: (e.g. md5(ts+privateKey+publicKey)
     */
    private String getHash() {
        String hashDecoded = Long.toString(timeStamp).concat(privateKey).concat(publicKey);
        return md5Encoder.encode(hashDecoded);
    }

}
