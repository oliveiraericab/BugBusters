package webTest;

// Generated by Selenium IDE

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//PRECISA FAZER LOGIN PRIMEIRO

public class SolicitarSaqueSIDE {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void solicitarSaque() {
        driver.get("https://testando.eveclass.com/pt/conta/meus-cursos");
        driver.manage().window().setSize(new Dimension(1364, 720));
        driver.findElement(By.linkText("Admin")).click();
        js.executeScript("window.scrollTo(0,0)");
        driver.findElement(By.cssSelector("#SALES > .item-pill")).click();
        driver.findElement(By.cssSelector(".nav-group:nth-child(4) > .nav-item:nth-child(2) > span")).click();
        {
            WebElement element = driver.findElement(By.linkText("Meu Saldo"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".button:nth-child(4)")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".button:nth-child(4)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        assertThat(driver.findElement(By.id("swal2-content")).getText(), is("O seu saldo total é de R$ 3,61. Uma taxa de transferência de R$ 3,67 será abatida deste valor. O valor total do saque é de -R$ 0,06 e será enviado para sua conta no Banco BANCO DO BRASIL (001) - Ag. 4884-2 - CC. 163369-0. A transferência será realizada em até 1 dia útil. Deseja confirmar o saque?"));
        driver.findElement(By.cssSelector(".swal2-confirm")).click();

        driver.navigate().refresh(); //dar refresh na tela
    }
}