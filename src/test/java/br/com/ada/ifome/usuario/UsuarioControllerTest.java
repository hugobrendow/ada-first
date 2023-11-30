package br.com.ada.ifome.usuario;

import br.com.ada.ifome.endereco.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;
    @Mock
    private UsuarioService usuarioService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void cadastrarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCpf("05566644411");
        usuario.setNome("Hugo");
        usuario.setEmail("hugo@mail.com");
        var endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("72333111");
        endereco.setNumero(2L);
        endereco.setComplemento("apartamento");
        endereco.setLogradouro("Brasilia-DF");
        usuario.setEndereco(endereco);

        Mockito.when(usuarioService.salvar(any())).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
