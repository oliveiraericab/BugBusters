package webTest;

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

public class ProcurarCursoSIDE {

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
    public void pesquisarcurso() {
        driver.get("https://testando.eveclass.com/pt");
        driver.manage().window().setSize(new Dimension(1382, 736));
        driver.findElement(By.cssSelector("#\\31 6237702146520 > .item-pill")).click();
        {
            WebElement element = driver.findElement(By.id("16237702146520"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.xpath("//input")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".no-link-style:nth-child(3) > .img-wrapper > .bg-img"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".no-link-style:nth-child(2) > .img-wrapper > .bg-img")).click();
        js.executeScript("window.scrollTo(0,0)");
        assertThat(driver.findElement(By.cssSelector("h1:nth-child(1)")).getText(), is("Pacote de cursos"));
    }
}