import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLOutput;

public class login {
    @Given("que acesso a Página de Login da plataforma Eveclass")
    public void que_acesso_a_página_de_login_da_plataforma_eveclass() {
        System.out.println("Passo 1");
    }
    @When("digito a {string}, {string} e {string}")
    public void digito_a_e(String string, String string2, String string3) {
        System.out.println("Passo 2");
    }
    @And("clico no botão {string}")
    public void clico_no_botão(String string) {
        System.out.println("Passo 3");
    }
    @Then("sou redirecionada para {string}")
    public void sou_redirecionada_para(String string) {
        System.out.println("Passo 4");
    }

}