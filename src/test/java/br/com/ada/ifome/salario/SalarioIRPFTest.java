package br.com.ada.ifome.salario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalarioIRPFTest {

    @Test
    public void deveCalcularDescontoIRPF() {
        double salario = 4000;
        double porcentagem = 20;

        var salarioIrpf = new SalarioIRPF();
        double desconto = salarioIrpf.calcularDesconto(salario, porcentagem);
        assertEquals(800, desconto);
    }
}
