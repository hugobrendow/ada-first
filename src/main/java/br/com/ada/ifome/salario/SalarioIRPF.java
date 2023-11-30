package br.com.ada.ifome.salario;

public class SalarioIRPF {

    public double calcularDesconto(double salario, double porcentagem) {
        double porcentagemDecimal = porcentagem / 100;
        return salario * porcentagemDecimal;
    }
}
