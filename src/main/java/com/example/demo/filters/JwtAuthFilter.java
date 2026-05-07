package com.example.demo.filters;

import com.example.demo.entity.UserAuthDetails;
import com.example.demo.repository.UserAuthDetailsRepository;
import com.example.demo.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserAuthDetailsRepository authDetailsRepository;

    @Override
    protected void  doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain) throws ServletException, IOException {
        String request = httpRequest.getHeader("Authorization");
        if(request == null || request.isEmpty()  || !request.startsWith("Bearer")){
            filterChain.doFilter(httpRequest,httpResponse);
            return;
        }
        String token = request.split("Bearer ")[1];
        String username = jwtService.generateUserFromToken(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserAuthDetails userAuthDetails = authDetailsRepository.findByUsername(username).orElse(null);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAuthDetails, null,userAuthDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpRequest,httpResponse);
    }
}
