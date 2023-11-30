package br.com.ada.ifome.usuario;

import br.com.ada.ifome.usuario.exceptions.CpfExistenteException;
import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.UsuarioExistenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsuarioExceptionHandler {

    @ExceptionHandler(CpfExistenteException.class)
    public ResponseEntity<String> handleCpfExistenteException() {
        return ResponseEntity.badRequest().body("O CPF informado já existe");
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<String> handleCpfInvalidoException() {
        return ResponseEntity.badRequest().body("O CPF informado não é valido");
    }

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontradoException() {
        return ResponseEntity.notFound().build();
    }
}
