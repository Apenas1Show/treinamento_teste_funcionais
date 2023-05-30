import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class TestarAlert {
    @Test
    @Order( 1 )
    @DisplayName( "1. Primeiro teste com alert" )
    public void deveInteragirComAlertSimples(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "alert" ) ).click();

        //selenium preciso que você altere o foco para o alerta, o switchTo indica mudar o foco para algum, ou mude para algum lugar
        Alert alert = driver.switchTo().alert();
        String alerta = alert.getText();
        Assertions.assertEquals( "Alert Simples", alert.getText() );
        alert.accept();

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( alerta );

        driver.quit();
    }
}
