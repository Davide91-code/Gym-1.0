package com.davideleonino.palestra.service;

import com.davideleonino.palestra.model.Role;
import com.davideleonino.palestra.model.User;
import com.davideleonino.palestra.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Costruttore per iniettare il repository e passwordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Implementazione del metodo loadUserByUsername che è fondamentale per UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Troviamo l'utente nel DB per nome utente (usiamo la email per autenticare)
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con email: " + username));

        // Restituiamo un UserDetails con i dettagli dell'utente (nome, password, ruoli)
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    // Metodo per registrare nuovi utenti (con password cifrata)
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Cifra la password
        user.setRole(Role.USER); //  Imposta il ruolo di default
        return userRepository.save(user); // Salva nel database
    }
}