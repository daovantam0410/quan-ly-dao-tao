package com.daovantam.quanlydaotao.security;

import com.daovantam.quanlydaotao.exception.NotiTemplate;
import com.daovantam.quanlydaotao.exception.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private final TokenAuthenticator authenticator;
    private ObjectMapper mapper;

    public AuthenticationFilter(TokenAuthenticator authenticator, ObjectMapper mapper) {
        this.authenticator = authenticator;
        this.mapper = mapper;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Authentication userAuth;
//        String token = ((HttpServletRequest) req).getHeader(AUTH_HEADER_NAME); //Lấy ra token từ AUTH_HEADER_NAME

        try {
            String jwt = parseJwt((HttpServletRequest) req);
            userAuth = this.authenticator.getAuthentication(jwt);
//            userAuth = this.authenticator.getAuthentication(token);
        } catch (InvalidJwtException e) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ResponseStatus responseStatus;
            if (e.getMessage().contains("no longer valid")){
                responseStatus = NotiTemplate.EXPIRE_TOKEN;
            } else {
                responseStatus = NotiTemplate.TOKEN_IN_VALID;
            }
            this.mapper.writeValue(response.getOutputStream(), responseStatus);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(userAuth);
        filterChain.doFilter(req, resp);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader(AUTH_HEADER_NAME);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7,headerAuth.length());
        }
        return null;
    }

}
