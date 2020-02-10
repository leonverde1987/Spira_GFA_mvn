package steps;

import org.openqa.selenium.WebDriver;

import generic.generic;
import generic.genericGrid;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import org.junit.ComparisonFailure;
import org.openqa.selenium.remote.RemoteWebDriver;

public class stepsOneGrid extends genericGrid{
    
    /**
     * Esté Método nos ayuda a dirigir el driver a una URL en especifico.
     * @param driver Elemento WebDriver de la prueba.
     * @param contador Es el controlador de pasos ejecutados.
     * @param Config Es el archivo de configuración de la prueba.
     * @param Escenario Es el nombre del caso de prueba.
     * @throws FileNotFoundException Cacha cualquier excepción en la ejecución.
     * @throws InterruptedException Cacha si el archivo Config no existe.
     */
    public void ingresar_A_URL(RemoteWebDriver driver, int contador, Properties Config, String Escenario) throws FileNotFoundException, InterruptedException {
        try{
            this.abrirURl(driver, Config.getProperty("urlApp"));
            this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
        }catch(InterruptedException e){
            System.out.println("Mensaje: "+e);
        }
        
    }
    
    /**
     * Esté método nos ingresa contenido en un componente de texto.
     * @param driver Elemento WebDriver de la prueba.
     * @param textoBuscar Es el valor del texto que se va ingresar al componente.
     * @param contador Es el controlador de pasos ejecutados.
     * @param Config Es el archivo de configuración de la prueba.
     * @param Elementos Es el archivo con los elementos del aplicativo 
     * @param Escenario Nombre del caso de prueba a ejecutar
     * @throws FileNotFoundException Cacha cualquier excepción en la ejecución.
     * @throws InterruptedException Cacha si el archivo Config no existe. 
     */
    public void ingresar_contenido(RemoteWebDriver driver, String textoBuscar, int contador, Properties Config, Properties Elementos, String Escenario) throws FileNotFoundException, InterruptedException {
        this.ingresar_texto(driver, "xpath", Elementos.getProperty("txtBuscarGoogle"), textoBuscar);
        this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), contador, Escenario);
    }
    
    /**
     * Esté metodo nos ayuda a cerrar el driver.
     * @param driver  
     */
    public void cerrar_Navegador(RemoteWebDriver driver) {
        this.cerrar_driver(driver);
    }
    /**
     * Est´s método nos ayuda a validar el titulo del navegador. 
     * @param driver Elemento WebDriver de la prueba.
     * @param Datos Es el archivo de datos de la prueba.
     * @param contador Es el controlador de pasos ejecutados.
     * @param Config Es el archivo de configuración de la prueba.
     * @param Escenario Es el nombre del caso de prueba.
     * @return Regresa el resultado Exitoso o Fallido y su detalle. 
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
     * Captura una evidencia cuando el Test Falla.
     * @param driver Elemento WebDriver de la prueba.
     * @param Config Es el archivo config de la prueba.
     * @param error Es el número de error detectado.
     * @param Escenario Es el nombre del caso de prueba.
     * @throws InterruptedException Cacha cualquier excepción
     */
    public void capturarEvidencia(WebDriver driver, Properties Config, int error, String Escenario) throws InterruptedException{
        
        switch(error) {
            case 1:
                this.capturaDriver(driver, Config.getProperty("rutaEvidencia"), error, Escenario);
                break;
        }
    }
    
    /**
     * Esté método finaliza el Test Case y genera las evidencias.
     * @param Escenario Es el nombre del Test Case.
     * @param Resultado Es el resultado del Testa Case Exitoso, Fallido o Ejecución Fallida
     * @param contador Es el total de pasos ejecutados.
     * @param Pasos Es el detalle de los pasos ejecutados.
     * @param RutaEvidencia Es la ruta principal de la evidencia.
     * @param Modulo Es el módulo del aplicativo que se ejecuta.
     * @param Version Es la versión del aplicativo.
     * @throws Exception Cacha cualquier excepción en la ejecución.
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
     * Cerramos los nodos del grid selenium.
     * @throws InterruptedException Cacha cualquier excepción de ejecución.
     */
    public void finEjecucionGrid() throws InterruptedException{
        this.cierraNodosGrid();
    }
}
