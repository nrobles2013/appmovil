package com.popularsafi.controller;

import com.popularsafi.dto.Mensaje;
import com.popularsafi.dto.NuevoUsuarioDTO;
import com.popularsafi.enums.Name;
import com.popularsafi.model.NuevoUsuario;
import com.popularsafi.model.Role;
import com.popularsafi.model.Usuario;
import com.popularsafi.security.JwtRequest;
import com.popularsafi.security.JwtResponse;
import com.popularsafi.security.JwtTokenUtil;
import com.popularsafi.security.JwtUserDetailsService;
import com.popularsafi.service.IUserService;

import com.popularsafi.service.impl.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception{
        System.out.println( req.getUsername()+req.getPassword());
        System.out.println( req.getTpparticipe().trim());
        authenticate(req.getUsername().trim(), req.getPassword().trim());

        final UserDetails userDetails = userDetailsService.loadUserByTipoUsername(req.getTpparticipe().trim(), req.getUsername().trim());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}