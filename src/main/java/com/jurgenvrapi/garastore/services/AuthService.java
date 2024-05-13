package com.jurgenvrapi.garastore.services;

import com.jurgenvrapi.garastore.entities.User;
import com.jurgenvrapi.garastore.payloads.JWTDTO;
import com.jurgenvrapi.garastore.payloads.LoginDTO;
import com.jurgenvrapi.garastore.repositories.UserRepository;
import com.jurgenvrapi.garastore.security.JWTTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JWTTools jwtTools) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTools = jwtTools;
    }

    public JWTDTO login(LoginDTO loginAuthDTO) throws AuthenticationException {
        Optional<User> user = userRepository.findByEmail(loginAuthDTO.getEmail());
        if (user.isPresent()) {
            LOGGER.info("User found with email: " + loginAuthDTO.getEmail());
            if (passwordEncoder.matches(loginAuthDTO.getPassword(), user.get().getPassword())) {
                LOGGER.info("Password matches for user: " + loginAuthDTO.getEmail());
                String token = jwtTools.generateToken(user.get());
                return new JWTDTO("Bearer " + token);
            } else {
                LOGGER.error("Password does not match for user: " + loginAuthDTO.getEmail());
            }
        } else {
            LOGGER.error("No user found with email: " + loginAuthDTO.getEmail());
        }
        throw new AuthenticationException("Invalid email or password");
    }
}