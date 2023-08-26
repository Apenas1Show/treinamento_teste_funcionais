package melhorado;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class TestarAlertV2 {
    private WebDriver driver;
    private DSL dsl;
    @BeforeEach
    public void inicio(){
        driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        dsl = new DSL( driver );
    }

    @AfterEach
    public void finalizarSessao(){
        driver.quit();
    }
    @Test
    @Order( 1 )
    @DisplayName( "1. Testando alert confirm" )
    public void deveInteragirComAlertConfirmDismiss(){
        driver.findElement( By.id( "confirm" ) ).click();

        //selenium preciso que você altere o foco para o alerta, o switchTo indica mudar o foco para algum, ou mude para algum lugar
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Confirm Simples", alert.getText() );
        alert.accept();
        Assertions.assertEquals( "Confirmado", alert.getText() );
        alert.accept();

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Deu certo!" );
    }

    @Test
    @Order( 2 )
    @DisplayName( "2. Testando alert confirm com dismiss" )
    public void deveInteragirComAlertConfirmAccept(){

        driver.findElement( By.id( "confirm" ) ).click();
        //selenium preciso que você altere o foco para o alerta, o switchTo indica mudar o foco para algum, ou mude para algum lugar
        Alert alert = driver.switchTo().alert();
        String alerta = alert.getText();
        Assertions.assertEquals( "Confirm Simples", alert.getText() );
        alert.dismiss();
        Assertions.assertEquals( "Negado", alert.getText() );
        alert.accept();

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Deu certo!" );
    }

    @Test
    @Order( 3 )
    @DisplayName( "3. Testando alert com prompt" )
    public void deveInteragirComAlertEPrompt() {

        driver.findElement( By.id( "prompt" ) ).click();
        Alert prompt = driver.switchTo().alert();
        Assertions.assertEquals( "Digite um numero" , prompt.getText());
        prompt.sendKeys( "50" );
        prompt.accept();
        Assertions.assertEquals( "Era 50?" , prompt.getText());
        prompt.accept();
        Assertions.assertEquals( ":D", prompt.getText() );
        prompt.accept();


    }
}
