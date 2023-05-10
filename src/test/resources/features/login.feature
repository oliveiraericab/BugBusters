Feature: Login
  Scenario: Realizar Login com Sucesso
  Given que acesso a Página de Login da plataforma Eveclass
  When digito "e-mail" e "Senha"
  And clico no botão Entrar
  Then sou redirecionada para "Página de Conteúdos"