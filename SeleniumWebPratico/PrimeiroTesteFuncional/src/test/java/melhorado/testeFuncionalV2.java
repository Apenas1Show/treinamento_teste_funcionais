package melhorado;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class testeFuncionalV2 {
    String LINK_AUTOMACAO = "https://www.instagram.com";
    String LOGIN_INSTAGRAM = "multivolo@gmail.com";
    String SENHA_INSTAGRAM = "Tecpix37@";
    @Test
    public void teste(){
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new InternetExplorerDriver();

//      Objeto WebDriver renomeado para driver que recebe um novo objeto de navegador chamado FirefoxDriver()
        WebDriver driver = new FirefoxDriver();

//      Abrindo o navegador e acessando a configuração dele para definir o tamanho da sessão que vai iniciar
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );

//      acessando o navegador e inserindo um site na aba de navegação
        driver.get( LINK_AUTOMACAO );

//      Valida se o <title> no site é Instagram
        Assertions.assertEquals( "Instagram", driver.getTitle() );

//      Acrescenta um timeout de 5 segundo assim que acessa o site, o tempo necessário para digitar o login e a senha
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//      Identificar o elemento pelo atributo name, e insere com o sendKeys o e-mail a baixo;
        driver.findElement( By.name( "username" ) ).sendKeys( LOGIN_INSTAGRAM );

        driver.findElement( By.name( "password" ) ).sendKeys( SENHA_INSTAGRAM );
        driver.findElement( By.xpath( "//div[text()='Log in']" ) ).click();
//        driver.quit();
    }

}
