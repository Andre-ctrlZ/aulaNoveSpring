package com.example.aulaNoveSpring.repository;

import com.example.aulaNoveSpring.model.Cliente;
import com.example.aulaNoveSpring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
