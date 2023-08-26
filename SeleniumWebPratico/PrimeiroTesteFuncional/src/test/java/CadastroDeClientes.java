import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class CadastroDeClientes {

    @Test
    @Order( 0 )
    @DisplayName( "Cadastro completo de cliente" )
    public void cadastrarCliente(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:sobrenome" ) ).sendKeys( "Bertolai" );
        driver.findElement( By.id( "elementosForm:sexo:0" ) ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:0" ) ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:1" ) ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:2" ) ).click();

        WebElement escolaridade = driver.findElement( By.id( "elementosForm:escolaridade" ) );
        Select combo = new Select( escolaridade );
            combo.selectByVisibleText( "Superior" );

        WebElement esportes = driver.findElement( By.id( "elementosForm:esportes" ) );
        Select comboEsportes = new Select( esportes );
            comboEsportes.selectByVisibleText( "Futebol" );

        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();

        //Modo como o professor resolveu o problema
        Assertions.assertTrue(  driver.findElement( By.id( "resultado" ) ).getText().startsWith( "Cadastrado!" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descNome" ) ).getText().endsWith( "Rafael" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descSobrenome" ) ).getText().endsWith( "Bertolai" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descComida" ) ).getText().endsWith( "Carne Frango Pizza" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descEscolaridade" ) ).getText().endsWith( "superior" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descEsportes" ) ).getText().endsWith( "Futebol" ));
        Assertions.assertTrue(  driver.findElement( By.id( "descSugestoes" ) ).getText().endsWith( "" ));

//        Modo como eu resolvi o problema
//        Assertions.assertEquals( "Cadastrado!" , driver.findElement( By.xpath( "//*[@id='resultado']/span" ) ).getText());
//        Assertions.assertEquals( "Rafael", driver.findElement( By.xpath( "//*[@id='descNome']/span" ) ).getText() );
//        Assertions.assertEquals( "Bertolai", driver.findElement( By.xpath( "//*[@id='descSobrenome']/span" ) ).getText() );

        driver.quit();

    }

    @Test
    @Order( 1 )
    @DisplayName( "Obrigatoriedade do campo nome" )
    public void validarCampoNome(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );

        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Nome eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    @Order( 2 )
    @DisplayName( "Obrigatoriedade de campo sobrenome" )
    public void validarCampoSobrenome(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html" );

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Sobrenome eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    @Order( 3 )
    @DisplayName( "Obrigatoriedade do campo sexo" )
    public void validarCampoDeSexo(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html");

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:sobrenome" ) ).sendKeys( "Bertolai" );
        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Sexo eh obrigatorio", alert.getText());

        driver.quit();
    }

    @Test
    @Order( 4 )
    @DisplayName( "Checkbox comida favorita conflito com carne e vegetariano" )
    public void validarConflitoComidaCarne(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html");

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:sobrenome" ) ).sendKeys( "Bertolai" );
        driver.findElement( By.id("elementosForm:sexo:0") ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:0" ) ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:3" ) ).click();
        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Tem certeza que voce eh vegetariano?", alert.getText());

        driver.quit();
    }
    @Test
    @Order( 5 )
    @DisplayName( "Checkbox comida favorita conflito com frango e vegetariano" )
    public void validarConflitoComidaFrango(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html");

        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:sobrenome" ) ).sendKeys( "Bertolai" );
        driver.findElement( By.id("elementosForm:sexo:0") ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:1" ) ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:3" ) ).click();
        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Tem certeza que voce eh vegetariano?", alert.getText());

        driver.quit();
    }

    @Test
    @Order( 6 )
    @DisplayName( "Conflito na lista de esportes" )
    public void validarListaEsportes(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize( new Dimension( 1080, 720 ) );
        driver.get( "file://" + System.getProperty( "user.dir" ) + "/src/main/resources/componentes.html");
        driver.findElement( By.id( "elementosForm:nome" ) ).sendKeys( "Rafael" );
        driver.findElement( By.id( "elementosForm:sobrenome" ) ).sendKeys( "Bertolai" );
        driver.findElement( By.id("elementosForm:sexo:0") ).click();
        driver.findElement( By.id( "elementosForm:comidaFavorita:1" ) ).click();

        WebElement escolaridade = driver.findElement( By.id( "elementosForm:escolaridade" ) );
        Select combo = new Select( escolaridade );
        combo.selectByVisibleText( "Superior" );

        WebElement esportes = driver.findElement( By.id( "elementosForm:esportes" ) );
        Select comboEsporte = new Select( esportes );
        comboEsporte.selectByVisibleText( "Futebol" );
        comboEsporte.selectByVisibleText( "O que eh esporte?" );

        driver.findElement( By.id( "elementosForm:cadastrar" ) ).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals( "Voce faz esporte ou nao?", alert.getText());

        driver.quit();
    }

}
