package com.daovantam.quanlydaotao.security.jwt;

import com.daovantam.quanlydaotao.security.jwt.util.String2KeyConverter;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.security.PublicKey;
import java.util.Map;

/*
 *Class này có nhiệm vụ parse token nhận từ phía client, để lấy thông tin trong token ra để so sánh
 **/
public final class JWTParser {
    private PublicKey publicKey;
    private String2KeyConverter converter;

    public JWTParser() {
        converter = new String2KeyConverter();
    }

    public JWTParser(String publicKey) {
        this();
        setPublicKey(publicKey);
    }

    public JWTParser(PublicKey publicKey) {
        this();
        this.publicKey = publicKey;
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    public void setPublicKey(String publicKey){
        this.publicKey = converter.getPublicKey(publicKey);
    }

    public Map<String, Object> parseToken(String token, String... audience) throws InvalidJwtException {
        JwtConsumerBuilder builder = new JwtConsumerBuilder().setRequireExpirationTime().setVerificationKey(publicKey);

        if (audience.length > 0){
            builder.setExpectedAudience(audience);
        }

        JwtConsumer consumer = builder.build();
        JwtClaims claims = consumer.processToClaims(token); //Chuyển từ dạng token sang dạng JwtClaims

        return claims.getClaimsMap(); //Chuyển từ dạng JwtClaims sang dạng Map<Key, Value>
    }
}
