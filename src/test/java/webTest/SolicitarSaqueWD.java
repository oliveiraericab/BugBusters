
package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SolicitarSaqueWD {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void procurarCursoWD(){
        //logando
        driver.get("https://testando.eveclass.com/pt/auth/entrar");
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).sendKeys("_teste_@yopmail.com");
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).sendKeys("_teste_");
        driver.findElement(By.cssSelector(".button")).click();

        //solicitando saque
        driver.get("https://testando.eveclass.com/pt/conta/meus-cursos");
        driver.findElement(By.linkText("Admin")).click();
        //localiza vendas:
        //localida meu saldo
        //localiza sacar
        // localiza confirmar
        // assert

    }
}
