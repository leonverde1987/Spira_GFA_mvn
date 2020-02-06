package TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.inflectra.spiratest.addons.junitextension.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;

import steps.stepsOne;
import steps.stepsOneGrid;

@SpiraTestConfiguration(
	    url="https://testing-it.spiraservice.net",
	    login="laguilar",
	    password="test1234", 
	    projectId=67,
	    testSetId=73
	)

public class TestOneGrid extends stepsOneGrid{
    public Properties Config = null;
    public Properties Datos = null;
    public Properties Elementos = null;
    public RemoteWebDriver driverFX = null;
    public RemoteWebDriver driverCH = null;
    public RemoteWebDriver driverED = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;
    public String Resultado = "";
    public String Escenario = "";
    public String RutaEvidencia = "";

    
    
    @Before
    public void PrepararEjecucion() throws FileNotFoundException, MalformedURLException, InterruptedException{
        Config = this.getPropetiesFile("configuracion\\configuracion.properties");
        Datos = this.getPropetiesFile("configuracion\\datos.properties");
        Elementos = this.getPropetiesFile("configuracion\\pageOne.properties");
        contador = 1;
        
        RutaEvidencia = Config.getProperty("rutaEvidencia");
        Resultado = "";
        
    }
    /*
    @Test
    @SpiraTestCase(testCaseId=6900)
    public void TestBuscarGoogle() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "CP01_EMA_Ejemplo_Evidencia";
            driverCH = this.openGridBrowser("chrome", Config);
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driverCH, contador, Config);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto"));
            this.ingresar_contenido(driverCH, Datos.getProperty("texto"), contador, Config, Elementos);
            
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driverCH, Datos, Config, contador);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driverCH, Config, contador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driverCH, Config, contador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"));
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7010)
    public void TestBuscarGoogle2() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "CP02_EMA_Ejemplo_Evidencia";
            driverFX = this.openGridBrowser("chrome", Config);
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driverFX, contador, Config);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google: "+Datos.getProperty("texto2"));
            this.ingresar_contenido(driverFX, Datos.getProperty("texto2"), contador, Config, Elementos);
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driverFX, Datos, Config, contador);
            
            
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driverFX, Config, contador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driverFX, Config, contador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"));
        }
    }
    */
    @Test
    @SpiraTestCase(testCaseId=7011)
    public void TestBuscarGoogle3() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            
            Escenario = "CP03_EMA_Ejemplo_Evidencia";
            driverED = this.openGridBrowser("chrome", Config);
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driverED, contador, Config, Escenario);
            
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto3"));
            this.ingresar_contenido(driverED, Datos.getProperty("texto3"), contador, Config, Elementos, Escenario);
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driverED, Datos, Config, contador, Escenario);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driverED, Config, contador, Escenario);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driverED, Config, contador, Escenario);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"));
        }
    }

    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driverFX);
        this.cerrar_Navegador(driverCH);
        this.cerrar_Navegador(driverED);
        this.cierraNodosGrid();
    }
}
