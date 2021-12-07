package com.daovantam.quanlydaotao.security.jwt;

import com.daovantam.quanlydaotao.security.jwt.model.JwtPayload;
import com.daovantam.quanlydaotao.security.jwt.util.JwtClaimKey;
import org.jose4j.jwt.consumer.InvalidJwtException;

import java.security.PublicKey;
import java.util.Map;

/*
*Class này có nhiệm vụ tiêu thụ token. Parse token ra và lấy thông tin để xử lý
**/
public class TokenConsumer {
    private String audience;
    private JWTParser parser;

    public TokenConsumer(String audience, PublicKey publicKey){
        this.audience = audience;
        parser = new JWTParser(publicKey);
    }

    /*consume nhận về 1 token*/
    public JwtPayload consume(String token) throws InvalidJwtException {
        Map<String, Object> claims = parser.parseToken(token, audience); //path token sang dạng Map(Key, Value)

        JwtPayload jwtPayload = new JwtPayload();

        jwtPayload.setId((Long) claims.get(JwtClaimKey.ID.getValue()));
        jwtPayload.setUserName((String) claims.get(JwtClaimKey.USERNAME.getValue()));
//        jwtPayload.setCode((String) claims.get(JwtClaimKey.CODE.getValue()));
        jwtPayload.setFullName((String) claims.get(JwtClaimKey.FULLNAME.getValue()));
        jwtPayload.setEmail((String) claims.get(JwtClaimKey.EMAIL.getValue()));
        jwtPayload.setRole((String) claims.get(JwtClaimKey.ROLE.getValue()));

        return jwtPayload;
    }
}
