package com.pontoflowatual.pfw.controllers;

import com.pontoflowatual.pfw.models.Usuario;
import com.pontoflowatual.pfw.seguranca.AuthResponse;
import com.pontoflowatual.pfw.seguranca.JwtUserDetailsService;
import com.pontoflowatual.pfw.services.TokenService;
import com.pontoflowatual.pfw.services.UsuarioService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioController(UsuarioService usuarioService, TokenService jwtTokenUtil, JwtUserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody Usuario usuario) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        // Obtém o ID do usuário
        Long userId = usuarioService.getUserIdByUsername(usuario.getUsername());

        return ResponseEntity.ok(new AuthResponse(token, userId));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        String encodedPassword = usuarioService.encodePassword(usuario.getPassword());
        usuario.setPassword(encodedPassword);
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @GetMapping("/getByToken")
    public ResponseEntity<Usuario> getUsuarioByToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Verifique se o cabeçalho de autorização não está vazio e começa com "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extrai o token removendo "Bearer " do cabeçalho
            String token = authorizationHeader.substring(7); // O token começa após "Bearer "

            // Chama o serviço para obter o usuário com base no token
            String username = jwtTokenUtil.extractUsername(token);
            return usuarioService.getByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
          
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }

    @GetMapping("/getProfile")
    public ResponseEntity<Usuario> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            Optional<Usuario> usuarioOptional = usuarioService.getByUsername(username);
            return usuarioOptional
                    .map(usuario -> ResponseEntity.ok(usuario))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }



    

}
