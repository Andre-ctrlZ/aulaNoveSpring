package com.example.aulaNoveSpring.service;

import com.example.aulaNoveSpring.model.Funcionario;
import com.example.aulaNoveSpring.model.Usuario;
import com.example.aulaNoveSpring.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Validated
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll();
    }

    public Funcionario salvar(@Valid @RequestBody Funcionario funcionario){
        if(funcionarioRepository.findById(funcionario.getId()).isPresent()){
            throw new RuntimeException("Email já cadastrado.");
        }
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(@Valid Funcionario funcionario){
        Funcionario funcionarioAtualizar = funcionarioRepository.findById(funcionario.getId())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        funcionarioAtualizar.setNome(funcionario.getNome());
        funcionarioAtualizar.setSetor(funcionario.getSetor());
        funcionarioAtualizar.setSalario(funcionarioAtualizar.getSalario());

        return funcionarioRepository.save(funcionario);
    }

    public void excluir(@PathVariable Long id){
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        funcionarioRepository.deleteById(funcionario.getId());
    }

    public String mensagem(Funcionario funcionario){
        return "Funcionario " + funcionario.getNome() + " Cadastrado com sucesso!";
    }

    public String mensagemAtualizar(Funcionario funcionario){
        return "Informações de " + funcionario.getNome() + " Atualizadas!";
    }
}
