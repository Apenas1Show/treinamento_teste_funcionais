package melhorado;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameButtonV2 {
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
    public void validandoFrameButton(){
        // na linha abaixo estamos acessando o frame uma parte isolada dentro da nossa página web, estamos focando nela com esse comando
        driver.switchTo().frame( "frame1" );
        // clicando no botão que está dentro do frame
        driver.findElement( By.id("frameButton") ).click();
        // mudando o foco para o alert que está dentro do frame
        Alert alert = driver.switchTo().alert();
        // validando se o que está dentro do alert realmente aparece
        Assertions.assertEquals( "Frame OK!", alert.getText() );
        // dando ok no alert
        alert.accept();
    }

    @Test
    public void validarJanelas(){
        driver.findElement( By.id( "buttonPopUpEasy" ) ).click();
        driver.switchTo().window( "Popup" );
        driver.findElement( By.tagName( "textarea" ) ).sendKeys( "Testando o campo dentro do popup" );
        driver.close();
        driver.switchTo().window("");
        driver.findElement( By.tagName( "textarea" ) ).sendKeys( "Deu certo!" );
    }

    @Test
    public void validarJanelasSemTitulo(){
        driver.findElement( By.id( "buttonPopUpHard" ) ).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1] );
        driver.findElement( By.tagName( "textarea" ) ).sendKeys( "Testando o campo dentro do popup" );
        driver.close();
        driver.switchTo().window("");
        driver.findElement( By.tagName( "textarea" ) ).sendKeys( "Deu certo!" );
    }
}
