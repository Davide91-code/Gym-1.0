package com.davideleonino.palestra.repository;

import com.davideleonino.palestra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Trova utente per email (utile in futuro per autenticazione)
    Optional<User> findByEmail(String email);
}