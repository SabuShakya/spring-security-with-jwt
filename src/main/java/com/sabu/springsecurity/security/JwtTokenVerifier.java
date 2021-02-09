package com.sabu.springsecurity.security;

import com.sabu.springsecurity.constants.JWTConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Intercepts every request to verify if the request is being sent by authorized user.
 * Checks the token.
 * */

public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader.isEmpty() || authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(JWTConstants.KEY.getBytes()))
                    .parseClaimsJws(token);
            Claims claimsJwsBody = claimsJws.getBody();
            String username = claimsJwsBody.getSubject();

            var authorities = (List<Map<String, String>>) claimsJwsBody.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                    .collect(Collectors.toSet());
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (JwtException e) {
            throw new IllegalStateException("Token cannot be trusted!");
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
