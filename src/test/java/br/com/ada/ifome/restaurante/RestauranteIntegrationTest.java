package br.com.ada.ifome.restaurante;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestauranteIntegrationTest {

     @Autowired
    private MockMvc mockMvc;

    @Test
    public void salvarRestauranteValido() throws Exception {
        Restaurante restaurante = generateRestaurante();
        restaurante.setCnpj("12345678912345");
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());


        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/restaurantes/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/restaurantes/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/restaurantes/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void salvarRestauranteComDocumentoInvalido() throws Exception {
        Restaurante restaurante = generateRestaurante();
        restaurante.setCnpj("12345678910");
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void salvarRestauranteComDocumentoUsandoLetras() throws Exception {
        Restaurante restaurante = generateRestaurante();
        restaurante.setCnpj("123456789H");
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void salvarRestauranteSemDocumento() throws Exception {
        Restaurante restaurante = generateRestaurante();
        restaurante.setCnpj(null);
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void salvarRestauranteExistente() throws Exception {
        Restaurante restaurante = generateRestaurante();
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/restaurantes/cnpj/".concat(restaurante.getCnpj()))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void salvarRestauranteDocumentoInvalidoComPontuacao() throws Exception {
        Restaurante restaurante = generateRestaurante();
        restaurante.setCnpj("37.770.668/0001-28");
        var restauranteJson = converterParaJson(restaurante);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/restaurantes")
                        .content(restauranteJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

    private static Restaurante generateRestaurante() {
        var restaurante = new Restaurante();
        restaurante.setCnpj("89885675000179");
        restaurante.setCnae("12345");
        restaurante.setCategoria("Comida Japonesa");
        restaurante.setNomeFantasia("AdaFood");
        restaurante.setRazaoSocial("Ada Food Alimentos Ltda");
        return restaurante;
    }


    public static String converterParaJson(final Restaurante obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
