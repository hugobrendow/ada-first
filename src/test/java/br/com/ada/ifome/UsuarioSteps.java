package br.com.ada.ifome;

import br.com.ada.ifome.endereco.Endereco;
import br.com.ada.ifome.usuario.Usuario;
import br.com.ada.ifome.usuario.UsuarioService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class UsuarioSteps {

    private Usuario usuario;
    @Autowired
    private UsuarioService usuarioService;
    private String cpf;

    @Dado("um usuario com o nome {string} e o cpf {string}")
    public void um_usuario_com_o_nome_e_o_cpf(String nome, String cpf) {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail("hugo@mail.com");
        var endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("72333111");
        endereco.setNumero(2L);
        endereco.setComplemento("apartamento");
        endereco.setLogradouro("Brasilia-DF");
        usuario.setEndereco(endereco);
        this.usuario = usuario;
        this.cpf = cpf;
    }

    @Quando("clicar no botao para salvar usuario")
    public void clicar_no_botao_para_salvar_usuario() {
        // Endpoint para salvar => /api/v1/usuarios
        this.usuarioService.salvar(usuario);
    }

    @Então("usuario deve ter sido criado com sucesso")
    public void usuario_criado_com_sucesso() {
        var usuarios = this.usuarioService.buscarUsuarios();
        boolean existeUsuario = usuarios.stream().anyMatch(usuario -> usuario.getCpf().equals(this.cpf));
        Assert.isTrue(existeUsuario);
    }

}
