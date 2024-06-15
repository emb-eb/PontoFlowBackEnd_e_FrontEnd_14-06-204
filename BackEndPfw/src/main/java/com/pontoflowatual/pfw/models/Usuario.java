package com.pontoflowatual.pfw.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbusuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(min = 3, max = 50, message = "O nome de usuário deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 100, message = "A senha deve ter pelo menos 6 caracteres")
    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(name = "account_enabled")
    @Getter
    @Setter
    private boolean accountEnabled;

    @Column(name = "role")
    @Getter
    @Setter
    private UserRoles role;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    // Métodos da interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte o papel do usuário em uma autoridade para Spring Security
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // A conta nunca expira
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // A conta nunca é bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // As credenciais nunca expiram
        return true;
    }

    @Override
    public boolean isEnabled() {
        // O usuário está habilitado
        return accountEnabled;
    }
}
