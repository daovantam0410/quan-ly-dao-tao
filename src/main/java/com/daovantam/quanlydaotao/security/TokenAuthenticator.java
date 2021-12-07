package com.daovantam.quanlydaotao.security;

import com.daovantam.quanlydaotao.security.jwt.TokenConsumer;
import com.daovantam.quanlydaotao.security.jwt.model.JwtPayload;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticator {
    private TokenConsumer consumer;

    @Autowired
    public TokenAuthenticator(TokenConsumer consumer) {
        this.consumer = consumer;
    }

    public Authentication getAuthentication(String token) throws InvalidJwtException {
        UserAuthentication userAuthentication = null;
        if (token != null){
            //Parse token -> JWTPayload
            JwtPayload jwtPayload = this.consumer.consume(token);
            UserDetails userDetails = new UserDetails();
            //Set ngược lại JWTPayload vào UserDetails
            userDetails.setUser(jwtPayload);
            userAuthentication = new UserAuthentication(userDetails);
        }

        return userAuthentication;
    }
}
