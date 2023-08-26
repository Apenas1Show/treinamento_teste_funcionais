package melhorado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {
    private WebDriver driver;

    public DSL( WebDriver driver ) {
        this.driver = driver;
    }

    public void escrever( String id_campo, String texto ) {
        driver.findElement( By.id( id_campo ) ).sendKeys( texto );
    }

    public String obterValorCampo( String id_campo ) {
        return driver.findElement( By.id( id_campo ) ).getAttribute( "value" );
    }

    public void clicarBotao( String idBotao ) {
        driver.findElement( By.id( idBotao ) ).click();
    }

    public void clicarRadio( String idRadio ) {
        driver.findElement( By.id( idRadio ) ).click();
    }

    public void clicarCheckbox( String idCheckBox ) {
        driver.findElement( By.id( idCheckBox ) ).click();
    }
}
