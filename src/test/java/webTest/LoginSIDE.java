package webTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSIDE {
    private WebDriver driver = new ChromeDriver();
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
    public void login() {
        driver.get("https://testando.eveclass.com");
        driver.manage().window().setSize(new Dimension(892, 720));
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div/div/div/input")).sendKeys("testee@yopmail.com");
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).click();
        driver.findElement(By.xpath("//div[@id=\'auth-panel\']/div[2]/div/div/div/div/div[2]/div/form/div/div[2]/div/div/input")).sendKeys("testee");
        driver.findElement(By.cssSelector(".button")).click();
        assertThat(driver.findElement(By.cssSelector(".header")).getText(), is("COURSES.ALL"));
    }
}

