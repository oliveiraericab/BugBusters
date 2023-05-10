package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class login {
    WebDriver driver;
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("que acesso a Página de Login da plataforma Eveclass")
    public void que_acesso_a_página_de_login_da_plataforma_eveclass() {
        System.out.println("Passo 1");
        driver.get("https://testando.eveclass.com/pt/auth/entrar");
    }

    @When("digito {string} e {string}")
    public void digito_e(String string, String string2) {
        System.out.println("Passo 2");
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).sendKeys("_teste_@yopmail.com");
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).sendKeys("_teste_");
    }

    @When("clico no botão Entrar")
    public void clico_no_botão_entrar() {
        System.out.println("Passo 3");
        driver.findElement(By.cssSelector(".button")).click();
    }

    @Then("sou redirecionada para {string}")
    public void sou_redirecionada_para(String string) {
        System.out.println("Passo 4");
        assertThat(driver.findElement(By.cssSelector(".header")).getText(), is("COURSES.ALL"));
    }
}