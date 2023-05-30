import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class TesteCampoTreinamento {
    @Test
    @Order( 1 )
    @DisplayName( "1. Validando textField dentro do Formulário" )
    public void testeTextField() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Teste de escrita no campo" );
        System.out.println("\n\n\nO que retorna do meu findElement: " + driver.findElement( By.id( "elementosForm:nome" ) ).getAttribute( "value" ) + "\n\n\n");
        Assertions.assertEquals( "Teste de escrita no campo", driver.findElement( By.id( "elementosForm:nome" ) ).getAttribute( "value" ) );
        driver.quit();
    }

    @Test
    @Order( 2 )
    @DisplayName( "2. Validando textArea dentro do Formulário" )
    public void testeTextArea() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "elementosForm:sugestoes" ) ).sendKeys( "Duzz solta logo o som" );
        Assertions.assertEquals( "Duzz solta logo o som", driver.findElement( By.id( "elementosForm:sugestoes" ) ).getAttribute( "value" ) );
        driver.quit();
    }

    @Test
    @Order( 3 )
    @DisplayName( "3. Validando radio button" )
    public void testeRadioButton() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "elementosForm:sexo:0" ) ).click();
        Assertions.assertTrue( driver.findElement( By.id( "elementosForm:sexo:0" ) ).isSelected() );
        driver.quit();
    }

    @Test
    @Order( 4 )
    @DisplayName( "4. Validando checkbox" )
    public void testeCheckBox() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "elementosForm:comidaFavorita:2" ) ).click();
        Assertions.assertTrue( driver.findElement( By.id( "elementosForm:comidaFavorita:2" ) ).isSelected() );
        driver.quit();
    }

    @Test
    @Order( 5 )
    @DisplayName( "Validando combo" )
    public void testeCombo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        WebElement escolaridade = driver.findElement( By.id( "elementosForm:escolaridade" ) );
        Select combo = new Select( escolaridade );
        combo.selectByVisibleText( "Mestrado" );
        Assertions.assertEquals( "Mestrado", combo.getFirstSelectedOption().getText() );
        driver.quit();
    }

    @Test
    @Order( 6 )
    @DisplayName( "Deve validar os valores que estão no combo" )
    public void deveVerificarValoresCombo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        WebElement escolaridade = driver.findElement( By.id( "elementosForm:escolaridade" ) );
        Select combo = new Select( escolaridade );
        List< WebElement > options = combo.getOptions();
        System.out.println( "Quantidade de opções dentro do select: " + options.size() );
        Assertions.assertEquals( 8, options.size() );

        boolean encontrou = false;
        for( WebElement option : options ) {

            if( option.getText().equals( "Doutorado" ) ) {
                encontrou = true;
                break;
            } else {
                System.out.println( option.getText() );
            }
        }
        Assertions.assertTrue( encontrou );

        driver.quit();
    }

    @Test
    @Order( 7 )
    @DisplayName( "Validando combo de multipla escolha" )
    public void deveEncontrarComboMultiplo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        WebElement esportes = driver.findElement( By.id( "elementosForm:esportes" ) );
        Select combo = new Select( esportes );
        combo.selectByVisibleText( "Natacao" );
        combo.selectByVisibleText( "Corrida" );
        combo.selectByVisibleText( "O que eh esporte?" );

//      Valida se as 3 opções marcadas conferem na lista
        List< WebElement > allSelectedOptions = combo.getAllSelectedOptions();
        Assertions.assertEquals( 3, allSelectedOptions.size() );

//      Vai desmarcar a opção corrida e validar se ela foi desmarcada
        combo.deselectByVisibleText( "Corrida" );
        allSelectedOptions = combo.getAllSelectedOptions();
        Assertions.assertEquals( 2, allSelectedOptions.size() );

        driver.quit();
    }

    @Test
    @Order( 8 )
    @DisplayName( "Validando botões" )
    public void deveInteragirComBotoes() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.id( "buttonSimple" ) ).click();
        Assertions.assertEquals( "Obrigado!", driver.findElement( By.id( "buttonSimple" ) ).getAttribute( "value" ) );
        driver.quit();

//      Forma elegante de fazer o mesmo que fizemos ali em cima
//        WebElement botao = driver.findElement( By.id( "buttonSimple" ) );
//        botao.click();
//        Assertions.assertEquals( "Obrigado!", botao.getAttribute( "value" ) );
    }

    @Test
    @Order( 9 )
    @DisplayName( "Validando Links" )
    public void deveInteragirComLinks() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        driver.findElement( By.linkText( "Voltar" ) ).click();
        Assertions.assertEquals( "Voltou!", driver.findElement( By.id( "resultado" ) ).getText() );
    }

    @Test
    @Order( 10 )
    @DisplayName( "Encontrando textos na tela" )
    public void deveEncontrarTextosNaTela() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );
        Assertions.assertEquals( "Campo de Treinamento", driver.findElement( By.tagName( "h3" ) ).getText() );
//        Assertions.assertTrue( driver.findElement( By.tagName( "body" ) )
//                .getText().contains( "Campo de Treinamento" ) );
        System.out.println(driver.findElement( By.className( "facilAchar" ) ).getText());
        Assertions.assertEquals( "Cuidado onde clica, muitas armadilhas...", driver.findElement( By.className( "facilAchar" ) ).getText());
        driver.quit();
    }

}
