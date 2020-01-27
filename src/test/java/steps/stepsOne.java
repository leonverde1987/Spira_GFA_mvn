package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.junit.ComparisonFailure;

public class stepsOne extends generic{
    
    public void ingresar_A_URL(WebDriver driver, int contador, Properties Config) throws FileNotFoundException, InterruptedException {
        this.abrirURl(driver, Config.getProperty("urlApp"));
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador);
    }
    
    public void ingresar_contenido(WebDriver driver, String textoBuscar, int contador, Properties Config, Properties Elementos) throws FileNotFoundException, InterruptedException {
        this.ingresar_texto(driver, "xpath", Elementos.getProperty("txtBuscarGoogle"), textoBuscar);
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador);
    }
    
    public void cerrar_Navegador(WebDriver driver) {
        this.cerrar_driver(driver);
    }
	
    public String validarTitlePagina(WebDriver driver, Properties Datos, Properties Config, int contador) throws InterruptedException{
        String msj = "";
        try{
            msj = this.AssertMsjElemento(driver, Datos.getProperty("mensaje"));
        }catch(ComparisonFailure e){
            msj = "Fallido, Resultado Esperado: "+e;
        }
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador);
        return msj;
    }
	
}
