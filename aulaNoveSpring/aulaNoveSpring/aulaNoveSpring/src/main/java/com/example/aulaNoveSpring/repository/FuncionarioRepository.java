package com.example.aulaNoveSpring.repository;

import com.example.aulaNoveSpring.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
