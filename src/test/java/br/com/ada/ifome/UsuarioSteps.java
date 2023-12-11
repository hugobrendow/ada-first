package br.com.ada.ifome;

import br.com.ada.ifome.endereco.Endereco;
import br.com.ada.ifome.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

public class UsuarioSteps {

    private RequestSpecification request;
    private ValidatableResponse response;
    private static final String BASE_URL = "http://localhost:8080";

    @Given("o endpoint {string} do tipo {string}")
    public void setEndpoint(String endpoint, String tipo) {
        RestAssured.baseURI = BASE_URL.concat(endpoint);
        request = RestAssured.given();
    }

    @When("eu enviar uma requisição para salvar um novo usuário com o CPF {string}")
    public void enviarRequest(String cpf) {
        var usuario = this.criarUsuario(cpf);
        response = request.body(asJsonString(usuario)).contentType(ContentType.JSON).post().then();
    }

    @When("eu enviar uma requisição para salvar um novo usuário com o CPF {string} e Nome {string}")
    public void eu_enviar_uma_requisição_para_salvar_um_novo_usuário_com_o_cpf_e_nome(String cpf, String nome) {
        var usuario = this.criarUsuario(cpf);
        usuario.setNome(nome);
        response = request.body(asJsonString(usuario)).contentType(ContentType.JSON).post().then();
    }

    @Then("eu recebo uma resposta com código {int}")
    public void compararResultado(Integer codigo) {

        response.assertThat().statusCode(codigo);
//        response.assertThat().body("nome", equalTo("Hugo"));
//        response.assertThat().body("cpf", equalTo("05566644411"));
        //response.then().statusCode(codigo);
    }

    @And("conteudo igual cpf {string} e o nome {string}")
    public void conteudoIgualCpfCpfEONomeNome(String cpf, String nome) {
        response.assertThat().body("nome", equalTo(nome));
        response.assertThat().body("cpf", equalTo(cpf));
    }

    @And("conteudo com a mensagem {string}")
    public void conteudoComAMensagemMensagem(String mensagem) {
        response.assertThat().body(equalTo(mensagem));
    }

    private Usuario criarUsuario(String cpf) {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNome("Hugo");
        usuario.setEmail("hugo@mail.com");
        var endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("72333111");
        endereco.setNumero(2L);
        endereco.setComplemento("apartamento");
        endereco.setLogradouro("Brasilia-DF");
        usuario.setEndereco(endereco);
        return usuario;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
