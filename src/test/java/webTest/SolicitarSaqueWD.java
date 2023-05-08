/*
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 2 - Classe
public class SolicitarSaqueWD {
    // 2.1 - Atributos
    private WebDriver driver; // declaramos o objeto do Selenium WebDriver

    // 2.2 - Funções e Métodos
    //Antes do Teste
    @BeforeEach
    public void setUp(){
        // declarar o gerenciador para baixar o chrome driver
        WebDriverManager.chromedriver().setup();
        // configuracao especifica a partir do Selenium WebDriver 4.8.1
        ChromeOptions options = new ChromeOptions();
        // adicionou ao choromeOptions a opcao de permitir qualquer origem de acesso remoto
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); // Instancia / Liga o Chrome Driver
        driver.manage().window().maximize(); // maximiza a janela do navegador
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }
    //Depois do Teste
    @AfterEach
    public void tearDown(){
        driver.quit(); // destroi a instancia do Selenium WebDriver
    }

    //O Teste
    @Test
    public void solicitarSaqueWD(){ //inicio do comprarPassagem
        driver.get("https://testando.eveclass.com/pt"); // abre o endereço alvo
        // selecionar o botão entrar
        driver.findElement(By.id("support-action")).click();

        { //inicio da seleção dentro da lista
            driver.findElement(By.id("email-i-7040439692103")).click().type("");
            lista.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        } //fim da seleção dentro da lista

        driver.findElement(By.name("toPort")).click();
        // selecionar a cidade na lista
        { // inicio da selecao dentro da lista
            WebElement lista = driver.findElement(By.name("toPort"));
            lista.findElement(By.xpath("//option[. = 'Berlin']")).click();
        } // fim da selecao dentro da lista

        //apertar botão
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // validar a frase que indica que o voo é de sao paolo para berlin
        assertEquals("Flights from São Paolo to Berlin:",driver.findElement(By.cssSelector("h3")).getText());
    }
    //driver

} // fim da classe

 */