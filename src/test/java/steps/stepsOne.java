package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import org.junit.ComparisonFailure;
import org.openqa.selenium.remote.RemoteWebDriver;

public class stepsOne extends generic{
    
    public void ingresar_A_URL(WebDriver driver, int contador, Properties Config, String Escenario) throws FileNotFoundException, InterruptedException {
        this.abrirURl(driver, Config.getProperty("urlApp"));
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    
    
    public void ingresar_contenido(WebDriver driver, String textoBuscar, int contador, Properties Config, Properties Elementos, String Escenario) throws FileNotFoundException, InterruptedException {
        this.ingresar_texto(driver, "xpath", Elementos.getProperty("txtBuscarGoogle"), textoBuscar);
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    public void ingresar_contenido_fail(WebDriver driver, String textoBuscar, int contador, Properties Config, Properties Elementos, String Escenario) throws FileNotFoundException, InterruptedException {
        this.ingresar_texto(driver, "xpath", Elementos.getProperty("txtBuscarGooglefail"), textoBuscar);
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    public void cerrar_Navegador(WebDriver driver) {
        this.cerrar_driver(driver);
    }
	
    public String validarTitlePagina(WebDriver driver, Properties Datos, Properties Config, int contador, String Escenario) throws InterruptedException{
        String msj = "";
        try{
            msj = this.AssertMsjElemento(driver, Datos.getProperty("mensaje"));
        }catch(ComparisonFailure e){
            msj = "Fallido, Resultado Esperado: "+e;
        }
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
        return msj;
    }
    
    public String validarTitlePaginafail(WebDriver driver, Properties Datos, Properties Config, int contador, String Escenario) throws InterruptedException{
        String msj = "";
        try{
            msj = this.AssertMsjElemento(driver, Datos.getProperty("mensaje"));
        }catch(ComparisonFailure e){
            msj = "Fallido, Resultado Esperado: "+e;
        }
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
        return msj;
    }
    
    public void capturarEvidencia(WebDriver driver, Properties Config, int error, String Escenario) throws InterruptedException{
        
        switch(error) {
            case 1:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error, Escenario);
                
                break;
            case 2:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error, Escenario);
                
                break;
        }
    }
    
    public void finalizarTestCase(String Escenario, String Resultado, int contador, List<String> Pasos, String RutaEvidencia, String Modulo, String Version) throws Exception{
        System.out.println("Lista: "+Pasos);
        //Generamos PDF
        this.crearPDF(Escenario, Resultado, contador, Pasos, RutaEvidencia, Modulo, Version);
        //Generamos PDF
        this.crearXML(Escenario, Resultado, contador, Pasos, RutaEvidencia);
        //Generamos HTML
        this.crearHTML(Escenario, Resultado, contador, Pasos, RutaEvidencia, Modulo, Version);

        if("Fallido".equals(Resultado.substring(0, 7))){
            throw new Exception(Resultado);
        }
        if(Resultado.length()>10){
            if("Ejecución Fallida".equals(Resultado.substring(0, 17))){
                throw new Exception(Resultado);
            }
        }
    }
    
    public void ejecucionGrid(Properties Config) throws InterruptedException, FileNotFoundException{
        this.leventarNodosGrid();
    }
    
    public void finEjecucionGrid() throws InterruptedException{
        this.cierraNodosGrid();
    }
}
