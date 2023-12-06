package br.com.ada.ifome.restaurante;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {
    private final RestauranteService restauranteService;

    @PostMapping
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        return restauranteService.salvar(restaurante);
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        var restaurante = restauranteService.buscarPorId(id);
        return ResponseEntity.ok(restaurante);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Restaurante> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(restauranteService.buscarPorCnpj(cnpj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        restauranteService.remover(id);
        return ResponseEntity.ok().build();
    }

}
