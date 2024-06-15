package com.pontoflowatual.pfw.services;

import com.pontoflowatual.pfw.models.Usuario;
import com.pontoflowatual.pfw.repositories.UsuarioRepository;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private static UsuarioRepository usuarioRepository = null;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        UsuarioService.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return usuarioRepository.findById(id);
    }

    public static Long getUserIdByUsername(String username) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException(
                "Usuário não encontrado com o nome de usuário fornecido: " + username));
        return usuario.getId();
    }

    public Usuario saveUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(@NonNull Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(@NonNull Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
