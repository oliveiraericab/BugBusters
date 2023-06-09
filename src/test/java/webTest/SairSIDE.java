package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SairSIDE {
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
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void logout() {
        driver.get("https://testando.eveclass.com/pt/conta/meus-cursos");
        driver.manage().window().setSize(new Dimension(892, 720));
        driver.findElement(By.cssSelector(".user-avatar > p")).click();
        driver.findElement(By.cssSelector(".dropdown_footer")).click();
        driver.findElement(By.cssSelector(".swal2-confirm")).click();
        assertThat(driver.findElement(By.cssSelector("strong > span")).getText(), is("Advanced Tests - Teste"));
    }
}
