#language: pt
Funcionalidade: salvar usuário
  Cenario: salvar usuario com sucesso
    Dado um usuario com o nome "Hugo" e o cpf "12345678910"
    Quando clicar no botao para salvar usuario
    Então usuario deve ter sido criado com sucesso