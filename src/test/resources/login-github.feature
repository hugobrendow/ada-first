#language: pt
Funcionalidade: Realizar login
  Cenario: login efetuado com sucesso
    Dado que foi feito a request para o endereco github.com
    E foi incluido no campo de usuario o valor "hugobrendow"
    E foi incluido no campo de password o valor "senha123"
    Quando clicar no botao de realizar login
    Então o resultado deve aparecer as informacoes do usuario