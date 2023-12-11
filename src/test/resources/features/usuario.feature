Feature: Operações CRUD para usuário

#  Scenario: Criar um novo usuário
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "12345678910"
#    Then eu recebo uma resposta com código 201
#
#  Scenario: Criar um usuário com o cpf já existente
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "12345678910" e Nome "Hugo"
#    Then eu recebo uma resposta com código 400
#
#  Scenario: Criar um usuário com cpf inválido com 9 digitos
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "1234567891"
#    Then eu recebo uma resposta com código 400

  Scenario Outline: Criação de usuários com sucesso
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo usuário com o CPF <cpf>
    Then eu recebo uma resposta com código <codigo>
    And conteudo igual cpf <cpf> e o nome <nome>

  Examples:
  |       endpoint     |  tipo  |      cpf      |  codigo  |  nome |
  | "/api/v1/usuarios" | "POST" | "12345678910" |    201   | "Hugo" |


  Scenario Outline: Criação de usuários com falhas
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo usuário com o CPF <cpf>
    Then eu recebo uma resposta com código <codigo>
    And conteudo com a mensagem <mensagem>

    Examples:
      |       endpoint     |  tipo  |      cpf      |  codigo  |              mensagem          |
      | "/api/v1/usuarios" | "POST" | "12345678910" |    400   |    "O CPF informado já existe" |
      | "/api/v1/usuarios" | "POST" | "1234567891"  |    400   |  "O CPF informado não é valido"|
