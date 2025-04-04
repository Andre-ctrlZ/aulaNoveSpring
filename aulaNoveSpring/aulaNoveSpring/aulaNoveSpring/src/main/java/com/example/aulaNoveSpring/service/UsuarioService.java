package com.example.aulaNoveSpring.service;

import com.example.aulaNoveSpring.model.Usuario;
import com.example.aulaNoveSpring.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Validated
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(@Valid Usuario usuario){
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("Email já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(@Valid Usuario usuario){
        Usuario usuarioAtualizar = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuarioAtualizar.setEmail(usuario.getEmail());
        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuario);
    }

    public void excluir(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuarioRepository.deleteById(usuario.getId());
    }

    public String mensagem(Usuario usuario){
        return "Usuario " + usuario.getNome() + " Cadastrado com sucesso!";
    }

    public String mensagemAtualizar(Usuario usuario){
        return "Informações de " + usuario.getNome() + " Atualizadas!";
    }
}
