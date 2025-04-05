package com.example.aulaNoveSpring.service;

import com.example.aulaNoveSpring.model.Cliente;

import com.example.aulaNoveSpring.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente salvar(@Valid Cliente cliente){
        if(clienteRepository.findByEmail(cliente.getEmail()).isPresent()){
            throw new RuntimeException("Email já cadastrado.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente){
        Cliente clienteAtualizar = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        clienteAtualizar.setEmail(cliente.getEmail());
        clienteAtualizar.setNome(cliente.getNome());
        clienteAtualizar.setTelefone(cliente.getTelefone());
        clienteAtualizar.setEndereco(cliente.getEndereco());

        return clienteRepository.save(cliente);
    }

    public void excluir(String email){
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        clienteRepository.deleteById(cliente.getId());
    }

    public String mensagem(Cliente cliente){
        return "Usuario " + cliente.getNome() + " Cadastrado com sucesso!";
    }

    public String mensagemAtualizar(Cliente cliente){
        return "Informações de " + cliente.getNome() + " Atualizadas!";
    }
}
