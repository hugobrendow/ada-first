package br.com.ada.ifome;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraSteps {
    private int primeiroNumero;
    private int segundoNumero;
    private int resultado;

    @Dado("que o primeiro numero é {int}")
    public void que_o_primeiro_numero_é(Integer primeiroNumero) {
        this.primeiroNumero = primeiroNumero;
    }

    @E("o segundo numero é {int}")
    public void o_segundo_numero_é(Integer segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

    @Quando("o usuario realizar a adição")
    public void o_usuario_realizar_a_adição() {
        this.resultado = this.primeiroNumero + this.segundoNumero;
    }

    @Então("o resultado deve ser {int}")
    public void o_resultado_deve_ser(Integer resultado) {
        assertEquals(resultado, this.resultado);
    }

}
