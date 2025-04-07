package com.example.aulaNoveSpring.controller;

import com.example.aulaNoveSpring.model.Cliente;
import com.example.aulaNoveSpring.model.Usuario;
import com.example.aulaNoveSpring.service.ClienteService;
import com.example.aulaNoveSpring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarTodos(){
        return clienteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Cliente cliente){
        clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Mensagem", usuarioService.mensagem(cliente)));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Cliente cliente){
        clienteService.atualizar(cliente);
        return ResponseEntity.ok().body(Map.of("Mensagem", clienteService.mensagemAtualizar(cliente)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable String email){
        clienteService.excluir(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Mensagem", "Cliente excluído com sucesso!"));
    }
}
