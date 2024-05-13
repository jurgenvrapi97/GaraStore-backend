package com.jurgenvrapi.garastore.controllers;


import com.jurgenvrapi.garastore.exceptions.BadRequestException;
import com.jurgenvrapi.garastore.payloads.JWTDTO;
import com.jurgenvrapi.garastore.payloads.LoginDTO;

import com.jurgenvrapi.garastore.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;


@RestController
public class LoginController {

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public JWTDTO login(@RequestBody @Validated LoginDTO loginAuthDTO, BindingResult validation) throws AuthenticationException {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return authService.login(loginAuthDTO);
    }
}