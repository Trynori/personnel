package com.kharitonov.personnel.web.controllers.authentication;

import com.kharitonov.personnel.services.authentication.AuthenticationService;
import com.kharitonov.personnel.web.contracts.router.ApiRouter;
import com.kharitonov.personnel.web.contracts.request.AuthenticationRequest;
import com.kharitonov.personnel.web.contracts.request.RegisterRequest;
import com.kharitonov.personnel.web.contracts.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(ApiRouter.AuthenticationController.BASE_URL)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.authenticationService = service;
    }

    @PostMapping(ApiRouter.AuthenticationController.REGISTER)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping(ApiRouter.AuthenticationController.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(ApiRouter.AuthenticationController.REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
