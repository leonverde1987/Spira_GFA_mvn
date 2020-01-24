package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import java.io.FileNotFoundException;

public class stepsOne extends generic{
    
    public void ingresar_A_URL(WebDriver driver, String URL, int contador) throws FileNotFoundException {
        this.openBrowser("Firefox", URL);
        this.capturaDriver(driver, this.getPropetiesFile().getProperty("rutaEvidencia"), contador);
    }
    
    public void ingresar_contenido(WebDriver driver, String textoBuscar, int contador) throws FileNotFoundException {
        this.ingresar_texto(driver, "xpath", this.getDatosFile().getProperty("txtBuscarGoogle"), textoBuscar);
        this.capturaDriver(driver, this.getPropetiesFile().getProperty("rutaEvidencia"), contador);
    }
    
    public void cerrar_Navegador(WebDriver driver) {
        this.cerrar_driver(driver);
    }
	
	
}
