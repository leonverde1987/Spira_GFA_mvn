package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;

public class stepsOne extends generic{
    
	
    public void ingresar_A_URL(String URL) {
        this.openBrowser("Firefox", URL);
    }

    public void ingresar_contenido(WebDriver driver, String textoBuscar) {
        this.ingresar_texto(driver, "xpath", "/html/body/div/div[4]/form/div[2]/div[1]/div[1]/div/div[2]/input", textoBuscar);
        this.capturaDriver(driver, "C:\\Evidencia", 0);
    }
    public void cerrar_Navegador(WebDriver driver) {
        this.cerrar_driver(driver);
    }
	
	
}
