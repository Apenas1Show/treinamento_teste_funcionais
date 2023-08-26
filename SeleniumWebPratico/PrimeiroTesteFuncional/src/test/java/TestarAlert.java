import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class TestarAlert {
    @Test
    @Order( 2 )
    @DisplayName( "2. Testando alert confirm" )
    public void deveInteragirComAlertConfirmDismiss(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "confirm" ) ).click();

        //selenium preciso que você altere o foco para o alerta, o switchTo indica mudar o foco para algum, ou mude para algum lugar
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Confirm Simples", alert.getText() );
        alert.accept();
        Assertions.assertEquals( "Confirmado", alert.getText() );
        alert.accept();

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Deu certo!" );
        driver.quit();
    }

    @Test
    @Order( 3 )
    @DisplayName( "3. Testando alert confirm com dismiss" )
    public void deveInteragirComAlertConfirmAccept(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );

        driver.findElement( By.id( "confirm" ) ).click();
        //selenium preciso que você altere o foco para o alerta, o switchTo indica mudar o foco para algum, ou mude para algum lugar
        Alert alert = driver.switchTo().alert();
        String alerta = alert.getText();
        Assertions.assertEquals( "Confirm Simples", alert.getText() );
        alert.dismiss();
        Assertions.assertEquals( "Negado", alert.getText() );
        alert.accept();

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Deu certo!" );
        driver.quit();
    }

    @Test
    @Order( 4 )
    @DisplayName( "3. Testando alert com prompt" )
    public void deveInteragirComAlertEPrompt() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );

        driver.findElement( By.id( "prompt" ) ).click();
        Alert prompt = driver.switchTo().alert();
        Assertions.assertEquals( "Digite um numero" , prompt.getText());
        prompt.sendKeys( "50" );
        prompt.accept();
        Assertions.assertEquals( "Era 50?" , prompt.getText());
        prompt.accept();
        Assertions.assertEquals( ":D", prompt.getText() );
        prompt.accept();

        driver.quit();

    }
}
