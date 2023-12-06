package br.com.ada.ifome;

import br.com.ada.ifome.restaurante.BusinessException;
import br.com.ada.ifome.restaurante.CnpjInvalidoException;
import br.com.ada.ifome.restaurante.RestauranteCadastradoException;
import br.com.ada.ifome.restaurante.RestauranteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RestauranteNotFoundException.class)
    public ResponseEntity<String> captureRestauranteNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Void> captureBusinessException() {
        return ResponseEntity.badRequest().build();
    }

}
