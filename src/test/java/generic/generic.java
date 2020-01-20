package generic;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    public Properties getPropetiesFile() throws FileNotFoundException{
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("configuracion\\configuracion.properties"));
        }catch(IOException e){
            System.out.println("Mensaje Properties: "+ e);
        }
        return prop;
        
    }
    
    /***
     * En este método abrimos el properties de datos para la ejecución de los scripts debe estar en la ruta c:/ambiente/configuracion/configuracion.properties.
     * @return El archivo de propiedades con los datos de los test.
     * @throws FileNotFoundException si no encuentra el archivo en la ruta c:/ambiente/configuracion/datos.properties.
     */
    public Properties getDatosFile() throws FileNotFoundException{
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("configuracion\\datos.properties"));
        }catch(IOException e){
            System.out.println("Mensaje Datos: "+ e);
        }
        return prop;
        
    }
    
    /**
     * El método regresa un webdriver iniciado para ejecutar pruebas y puede ejecutar Firefox, Chrome, EDGE.
     * @param Navegador Es el tipo de navegador Firefox, Chrome, EDGE.
     * @param URL Es la URL que va abrir el navegador.
     * @return Manda el WebDriver iniciado
     */    
    public WebDriver openBrowser(String Navegador, String URL) {
        WebDriver driver = null;
        try {
            switch(Navegador) {
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "selenium_drivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    driver.get(URL);
                    break;
                case "Firefox":
                    System.setProperty("webdriver.gecko.driver", "selenium_drivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    driver.get(URL);
                    break;
            }
            driver.manage().window().maximize();
            return driver;
        }catch(Exception e){
                System.out.println("Mensaje Driver: "+e.getMessage());

        }
        return driver;
    }
    
    /*
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ASSERTS
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    
    /**
     * En este método vamos a validar que estamos en la URL correcta
     * @param driver 
     */
    public void validarEstamosEnURL(WebDriver driver){
        
    }
    
    
    /*
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    METODOS
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

    public void cerrar_driver(WebDriver driver){
            driver.close();
    }

    

    public void dormir10seg() throws InterruptedException{
        Thread.sleep(10000);
    }
    
    
    
}	
