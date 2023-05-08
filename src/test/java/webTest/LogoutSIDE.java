package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LogoutSIDE {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Aponta onde está o Chrome Driver
        // System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver(options); // Instancia / Liga o Chrome Driver
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterEach
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