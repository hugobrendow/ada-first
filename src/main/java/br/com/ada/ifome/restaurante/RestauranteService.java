package br.com.ada.ifome.restaurante;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository repository;

    public Restaurante salvar(Restaurante restaurante) {
        this.validarCnpj(restaurante.getCnpj());
        this.validarRestauranteCadastrado(restaurante);
        return repository.save(restaurante);
    }

    public List<Restaurante> listar() {
        return repository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(RestauranteNotFoundException::new);
    }

    public Restaurante buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj).orElseThrow(RestauranteNotFoundException::new);
    }

    public void remover(Long id) {
        var restaurante = this.buscarPorId(id);
        repository.delete(restaurante);
    }

    private void validarRestauranteCadastrado(Restaurante restaurante) {
        var restauranteOptional = repository.findByCnpj(restaurante.getCnpj());
        if (Objects.isNull(restaurante.getId()) && restauranteOptional.isPresent()) {
            throw new RestauranteCadastradoException();
        }
    }

    private void validarCnpj(String cnpj) {
        if (Objects.isNull(cnpj) || !cnpj.matches("\\d{14}")) {
            throw new CnpjInvalidoException();
        }
    }
}
