package com.lnovik.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManeger) {
        super(authManeger);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(SecurityConstrants.HEADER_STRING);
        if(header == null || !header.startsWith(SecurityConstrants.TOKEN_PREFIX)){
            chain.doFilter(req, res);
        return;
    }
    UsernamePasswordAuthenticationToken authetication = getAuthetication(req);
        SecurityContextHolder.getContext().setAuthentication(authetication);
        chain.doFilter(req, res);
}


 private UsernamePasswordAuthenticationToken getAuthetication(HttpServletRequest request){
        String token = request.getHeader(SecurityConstrants.HEADER_STRING);
        if(token == null){
            return null;
        }

        String user = JWT.require(Algorithm.HMAC512(SecurityConstrants.SECRET.getBytes()))
                .build()
                .verify(token.replace(SecurityConstrants.TOKEN_PREFIX, ""))
                .getSubject();
        if( user != null){
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
 }
}