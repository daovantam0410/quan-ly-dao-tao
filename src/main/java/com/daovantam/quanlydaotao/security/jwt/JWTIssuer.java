package com.daovantam.quanlydaotao.security.jwt;

import com.daovantam.quanlydaotao.security.jwt.util.String2KeyConverter;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;

/*
*Class này có nhiệm vụ sinh ra token
**/
public class JWTIssuer {
    /*
    * privateKey: mã hóa token
    */
    private PrivateKey privateKey;

    /*
    * string2KeyConverter: Parse đường dẫn của privateKey -> privateKey
    */
    private String2KeyConverter string2KeyConverter;

    public JWTIssuer() {
        string2KeyConverter = new String2KeyConverter();
    }

    public JWTIssuer(String privateKey) {
        this();
        setPrivateKey(privateKey);
    }

    public JWTIssuer(PrivateKey privateKey) {
        this();
        this.privateKey = privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = string2KeyConverter.getPrivateKey(privateKey);
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /*
    * jsonWebSignature: là signature của JWT
    **/
    public String createToken(JwtClaims claims) {
        JsonWebSignature jsonWebSignature = new JsonWebSignature();
        jsonWebSignature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256); //Sử dụng thuật toán SHA256 (trong Header)
        jsonWebSignature.setPayload(claims.toJson()); //Lưu thông thông tin Claims (trong Payload)
        jsonWebSignature.setKey(privateKey);

        try {
            return jsonWebSignature.getCompactSerialization(); //Mã hóa sang dạng byte
        } catch (JoseException e) {
            throw new RuntimeException();
        }
    }
}
