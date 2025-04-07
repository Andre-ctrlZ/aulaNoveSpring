package com.example.aulaNoveSpring.repository;

import com.example.aulaNoveSpring.model.Funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> FindByMatricula(string matricula);
}
