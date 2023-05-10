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

public class ProcurarCursoWD {
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
        driver.quit();}

    @Test
    public void procurarCursoWD(){
        //procurar curso direto
        /* driver.get("https://testando.eveclass.com");
        driver.findElement(By.cssSelector("div.item-pill")).click();
        driver.findElement(By.cssSelector("div.bg-img")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is ("Pacote de cursos")); */

        //procurar curso digitando na busca
        driver.get("https://testando.eveclass.com/pt");
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        //driver.findElement(By.cssSelector("div.item-pill")).click(); clicava no botão errado
        driver.findElement(By.cssSelector("a[href='/pt/cursos']")).click();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("auto-i-46267412520541216")).click();
        driver.findElement(By.id("auto-i-46267412520541216")).sendKeys("Pacote de cursos");
        driver.findElement(By.cssSelector("div.bg-img")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Pacote de cursos"));
    }
}

