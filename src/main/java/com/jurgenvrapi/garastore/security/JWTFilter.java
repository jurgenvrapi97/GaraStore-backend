package com.jurgenvrapi.garastore.security;


import com.jurgenvrapi.garastore.entities.User;
import com.jurgenvrapi.garastore.exceptions.UnauthorizedException;
import com.jurgenvrapi.garastore.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token missing or invalid");
        String token = authorizationHeader.substring(7);
        jwtTools.validateToken(token);
        String subject = jwtTools.getSubjectFromToken(token);
        Optional<User> user = UserService.getUserById(Long.parseLong(subject));
        if (user.isPresent()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.get(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new UnauthorizedException("User not found");
        }


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/api/auth/**", request.getServletPath());
    }
}
