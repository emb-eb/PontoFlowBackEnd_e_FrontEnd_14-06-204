package com.pontoflowatual.pfw.seguranca;

import com.pontoflowatual.pfw.models.Usuario;
import com.pontoflowatual.pfw.repositories.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username));
        return usuario;
    }
}