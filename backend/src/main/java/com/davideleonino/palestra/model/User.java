package com.davideleonino.palestra.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")  // Cambia il nome della tabella in "users"
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;  // Campo per la password

    @Enumerated(EnumType.STRING)
    private Role role;  // Il ruolo dell'utente, come USER, ADMIN

    // Costruttori
    public User() {
    }

    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Metodo richiesto dall'implementazione di UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;  // Usando l'email come username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Se non vuoi gestire la scadenza dell'account
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Se non vuoi gestire il blocco dell'account
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Se non vuoi gestire la scadenza delle credenziali
    }

    @Override
    public boolean isEnabled() {
        return true;  // Se non vuoi gestire l'attivazione dell'utente
    }
}
