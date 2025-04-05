package com.example.aulaNoveSpring.controller;

import com.example.aulaNoveSpring.model.Funcionario;
import com.example.aulaNoveSpring.model.Usuario;
import com.example.aulaNoveSpring.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> listarTodos(){
        return funcionarioService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.salvar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Mensagem", funcionarioService.mensagem(funcionario)));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.atualizar(funcionario);
        return ResponseEntity.ok().body(Map.of("Mensagem", funcionarioService.mensagemAtualizar(funcionario)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id){
        funcionarioService.excluir(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Mensagem", "Usuario excluído com sucesso!"));
    }
}
