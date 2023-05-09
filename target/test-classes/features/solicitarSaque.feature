#Feature: Solicitar Saque
#
#  Scenario: Solicitar saque com sucesso
#    Given que como administrador acesso a Plataforma Eveclass;
#    When realizo login;
#    And sou redirecionada ao Painel do Administrador;
#    And seleciono o menu superior Vendas;
#    And clico no menu lateral Meu Saldo;
#    And pressiono o botão Sacar no canto superior direito;
#    And visualizo uma mensagem que informa meu saldo disponível para saque, o valor da taxa de saque e as informações da conta bancária;
#    And clico no botão Confirmar;
#    Then visualizo uma mensagem de confirmação do saque.

Feature: Solicitar Saque

  Scenario: Solicitar saque com sucesso
    Given que acesso o Painel do Administrador da Plataforma Eveclass
    When seleciono o menu superior Vendas, em seguida o menu Meu Saldo e clico em  Sacar
    And visualizo uma mensagem que informa meu "saldo disponível" para saque
    And clico no botão Confirmar
    Then visualizo uma mensagem de confirmação do saque