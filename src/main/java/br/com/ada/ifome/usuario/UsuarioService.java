package br.com.ada.ifome.usuario;

import br.com.ada.ifome.endereco.EnderecoRepository;
import br.com.ada.ifome.usuario.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public Usuario salvar(Usuario usuario) { // Salvar um usuário com ID
        if (usuario == null) {
            throw new UsuarioInvalidoException();
        }

        if (usuario.getId() != null) {
            throw new UsuarioExistenteException();
        }
        var isCpfValido = this.validaCpf(usuario.getCpf());
        if (!isCpfValido) {
            throw new CpfInvalidoException();
        }
        var usuarioEncontrado = this.usuarioRepository.findByCpf(usuario.getCpf());
        if (usuarioEncontrado.isPresent()) {
            throw new CpfExistenteException();
        }

        var endereco = enderecoRepository.save(usuario.getEndereco());
        usuario.setEndereco(endereco);
        return usuarioRepository.save(usuario); // Mockar o usuário repository...
    }

    public List<Usuario> buscarUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario atualizar(Usuario usuarioRequest) {
        var usuario = usuarioRepository.findById(usuarioRequest.getId());
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        return usuarioRepository.save(usuarioRequest);
    }

    private boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }

    public Usuario remover(Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        usuarioRepository.deleteById(id);
        return usuario.get();
    }
}
