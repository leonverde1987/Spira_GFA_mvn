package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import org.junit.ComparisonFailure;
import org.openqa.selenium.remote.RemoteWebDriver;

public class stepsOneGrid extends generic{
    
    /**
     * 
     * @param driver
     * @param contador
     * @param Config
     * @param Escenario
     * @throws FileNotFoundException
     * @throws InterruptedException 
     */
    public void ingresar_A_URL(RemoteWebDriver driver, int contador, Properties Config, String Escenario) throws FileNotFoundException, InterruptedException {
        this.abrirURl(driver, Config.getProperty("urlApp"));
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    /**
     * 
     * @param driver
     * @param textoBuscar
     * @param contador
     * @param Config
     * @param Elementos
     * @param Escenario
     * @throws FileNotFoundException
     * @throws InterruptedException 
     */
    public void ingresar_contenido(RemoteWebDriver driver, String textoBuscar, int contador, Properties Config, Properties Elementos, String Escenario) throws FileNotFoundException, InterruptedException {
        this.ingresar_texto(driver, "xpath", Elementos.getProperty("txtBuscarGoogle"), textoBuscar);
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    /**
     * 
     * @param driver 
     */
    public void cerrar_Navegador(RemoteWebDriver driver) {
        this.cerrar_driver(driver);
    }
    /**
     * 
     * @param driver
     * @param Datos
     * @param Config
     * @param contador
     * @param Escenario
     * @return
     * @throws InterruptedException 
     */
    public String validarTitlePagina(RemoteWebDriver driver, Properties Datos, Properties Config, int contador, String Escenario) throws InterruptedException{
        String msj = "";
        try{
            msj = this.AssertMsjElemento(driver, Datos.getProperty("mensaje"));
        }catch(ComparisonFailure e){
            msj = "Fallido, Resultado Esperado: "+e;
        }
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
        return msj;
    }
    
    /**
     * 
     * @param driver
     * @param Config
     * @param error
     * @param Escenario
     * @throws InterruptedException 
     */
    public void capturarEvidencia(RemoteWebDriver driver, Properties Config, int error, String Escenario) throws InterruptedException{
        
        switch(error) {
            case 1:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error, Escenario);
                
                break;
            case 2:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error, Escenario);
                
                break;
        }
    }
    
    /**
     * 
     * @param Escenario
     * @param Resultado
     * @param contador
     * @param Pasos
     * @param RutaEvidencia
     * @param Modulo
     * @param Version
     * @throws Exception 
     */
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
    
    /**
     * 
     * @param Config
     * @throws InterruptedException
     * @throws FileNotFoundException 
     */
    public void ejecucionGrid(Properties Config) throws InterruptedException, FileNotFoundException{
        this.leventarNodosGrid();
    }
    
    /**
     * 
     * @throws InterruptedException 
     */
    public void finEjecucionGrid() throws InterruptedException{
        this.cierraNodosGrid();
    }
}
