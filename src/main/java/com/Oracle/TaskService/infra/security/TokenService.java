package com.Oracle.TaskService.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jwt.secret.oracle}")
    private String secret;

    public Long getUserId(String token){
        if(token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Oracle Project")
                    .build()
                    .verify(token);
        }catch(JWTVerificationException e){
            System.out.println(e.toString());
        }
        Long id = verifier.getClaim("id").asLong();
        if(id == null){
            throw new RuntimeException("Invalid verifier");
        }
        return id;
    }

}
