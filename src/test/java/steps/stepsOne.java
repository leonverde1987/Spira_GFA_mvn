package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import java.io.FileNotFoundException;
import java.util.List;
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
    
    public void capturarEvidencia(WebDriver driver, Properties Config, int error) throws InterruptedException{
        
        switch(error) {
            case 1:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error);
                
                break;
            case 2:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error);
                
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
    }
}
