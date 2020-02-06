package generic;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.junit.ComparisonFailure;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class generic extends evidence {
    
    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    CONFIGURACIONES
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    
    /***
     * En este método abrimos el properties de configuración del proyecto debe estar en la ruta c:/ambiente/configuracion.
     * @return El archivo de propiedades con la configuración.
     * @throws FileNotFoundException si no encuentra el archivo en la ruta c:/ambiente/configuracion.
     */
    public Properties getPropetiesFile(String Archivo) throws FileNotFoundException{
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream(Archivo));
        }catch(IOException e){
            System.out.println("Mensaje Properties: "+ e);
        }
        return prop;
        
    }
    
    
    
    /**
     * El método regresa un webdriver iniciado para ejecutar pruebas y puede ejecutar Firefox, Chrome, EDGE.
     * @param Navegador Es el tipo de navegador Firefox, Chrome, EDGE.
     * @return Manda el WebDriver iniciado
     */    
    public WebDriver openBrowser(String Navegador) {
        WebDriver driver = null;
        try {
            switch(Navegador) {
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "selenium_drivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    
                    break;
                case "Firefox":
                    System.setProperty("webdriver.gecko.driver", "selenium_drivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    
                    break;
            }
            driver.manage().window().maximize();
            return driver;
        }catch(Exception e){
                System.out.println("Mensaje Driver: "+e.getMessage());

        }
        return driver;
    }
    
    public RemoteWebDriver openGridBrowser(String navegador, Properties config) throws MalformedURLException{
        RemoteWebDriver driver = null;
        DesiredCapabilities capa = new DesiredCapabilities();
        //capa.setCapability("platformName", "iOS");
        capa.setPlatform(Platform.WINDOWS);
        capa.setBrowserName(navegador);
        driver = new RemoteWebDriver(new URL("http://"+config.getProperty("ipHub")+":"+config.getProperty("hubPort")+"/grid/register"), capa);
        return driver;
    }
    
    public void leventarNodosGrid(Properties Config) throws InterruptedException{
        try {
            String cmd = "cmd /c start cmd.exe /K java -jar "+Config.getProperty("seleniumServer")+" -role hub -port "+Config.getProperty("hubPort");
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println ("Hub: "+ioe);
        }
        Thread.sleep(5000);
        try {
            String cmd = "cmd /c start cmd.exe /K java -Dwebdriver.gecko.driver="+Config.getProperty("gecko")+" -jar "+Config.getProperty("seleniumServer")+" -role webdriver -hub http://"+Config.getProperty("ipHub")+":"+Config.getProperty("hubPort")+"/grid/register -port "+Config.getProperty("geckoPort");
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println ("Gecko node: "+ioe);
        }
        Thread.sleep(5000);
        try {
            String cmd = "cmd /c start cmd.exe /K java -Dwebdriver.chrome.driver="+Config.getProperty("chrome")+" -jar "+Config.getProperty("seleniumServer")+" -role webdriver -hub http://"+Config.getProperty("ipHub")+":"+Config.getProperty("hubPort")+"/grid/register -port "+Config.getProperty("chromePort");
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println ("Chrome node: "+ioe);
        }
        Thread.sleep(5000);
        try {
            String cmd = "cmd /c start cmd.exe /K java -Dwebdriver.edge.driver="+Config.getProperty("edge")+" -jar "+Config.getProperty("seleniumServer")+" -role webdriver -hub http://"+Config.getProperty("ipHub")+":"+Config.getProperty("hubPort")+"/grid/register -port "+Config.getProperty("edgePort");
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println ("Edege node: "+ioe);
        } 
    }
    
    public void cierraNodosGrid(){
        try {
            String cmd = "cmd /c start cmd.exe /K TASKKILL /F /IM cmd.exe /T";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println ("Edege node: "+ioe);
        }
        
    }
    
    /*
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ASSERTS
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    
    /**
     * En este método vamos a validar que estamos en la URL correcta
     * @param driver 
     * @param msjActual Es el valr del texto que se compara.
     */
    public String AssertMsjElemento(WebDriver driver, String msjActual){
        String msj = "";
        try{
            Assert.assertEquals(driver.getTitle(), msjActual);
            msj = "Exitoso";
        }catch(AssertionError e){
            System.out.println("Mensaje Assert Fail: "+e);
            msj = "Fallido, Reusltado Esperado: "+e;
        }
        
        return msj;
    }
    
    
    /*
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    METODOS
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    
    /***
     * El método nos ayuda a dar clic a un elemento.
     * @param driver es el webDriver en el que se ejecuta la pruebas automatizada.
     * @param findby Es el tipo de selector selenium id, name o XPATH.
     * @param Elemento Es el selector selenium que se le va a dar Clic.
     */
    public void clic_btn(WebDriver driver, String findby, String Elemento){
        switch(findby) {
            case "id":
                driver.findElement(By.id(Elemento)).click();
                break;
            case "name":
                driver.findElement(By.name(Elemento)).click();
                break;
            case "xpath":
                driver.findElement(By.xpath(Elemento)).click();
                break;
        }

    }
    
    /***
     * El método nos ayuda a ingresar texto a un elemento.
     * @param driver Es el webDriver en el que se ejecuta la pruebas automatizada.
     * @param findby Es el tipo de selector selenium id, name o XPATH.
     * @param Elemento Es el selector selenium al que le vamos agregar texto.
     * @param Texto Es el texto que se va ingresar al campo.
     */
    public void ingresar_texto(WebDriver driver, String findby, String Elemento, String Texto){
        switch(findby) {
            case "id":
                driver.findElement(By.id(Elemento)).clear();
                driver.findElement(By.id(Elemento)).sendKeys(Texto);
                driver.findElement(By.id(Elemento)).submit();
                break;
            case "name":
                driver.findElement(By.name(Elemento)).clear();
                driver.findElement(By.name(Elemento)).sendKeys(Texto);
                driver.findElement(By.name(Elemento)).submit();
                break;
            case "xpath":
                driver.findElement(By.xpath(Elemento)).clear();
                driver.findElement(By.xpath(Elemento)).sendKeys(Texto);
                driver.findElement(By.xpath(Elemento)).submit();
                break;
        }
    }

    /***
     * El método nos ayuda a cerrar un WebDriver.
     * @param driver Es el webDriver en el que se ejecuta la pruebas automatizada.
     */
    public void cerrar_driver(WebDriver driver){
            driver.close();
    }

    /***
     * El método le da un tiempo de 10 segundos al webDriver.
     * @exception InterruptedException Para manejar excepciones con el hilo de procesamiento que se esta deteniendo.
     */
    public void dormir10seg() throws InterruptedException{
        Thread.sleep(10000);
    }
    
    /***
     * El método le da un tiempo de 10 segundos al webDriver.
     * 
     * @exception InterruptedException Para manejar excepciones con el hilo de procesamiento que se esta deteniendo.
     */
    public void abrirURl(WebDriver driver, String URL) throws InterruptedException{
        driver.get(URL);
    }
    
    /***
     * El método obtiene el texto de un objeto.
     * @param driver es el webDriver en el que se ejecuta la pruebas automatizada.
     * @param findby Es el tipo de selector selenium id, name o XPATH.
     * @param Elemento Es el selector selenium.
     * @return Regresa el texto del objeto o un vacio en caso de no encontrar el findby.
     */
    public String obtenerTexto(WebDriver driver, String findby, String Elemento){
        String texto = "";
        switch(findby) {
            case "id":
                texto = driver.findElement(By.id(Elemento)).getText();
                break;
            case "name":
                texto = driver.findElement(By.name(Elemento)).getText();
                break;
            case "xpath":
                texto = driver.findElement(By.xpath(Elemento)).getText();
                break;
        }
        return texto;
    }
    
}	
