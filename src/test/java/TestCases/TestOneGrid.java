package TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.inflectra.spiratest.addons.junitextension.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.NoSuchElementException;

import steps.stepsOne;

@SpiraTestConfiguration(
	    url="https://testing-it.spiraservice.net",
	    login="laguilar",
	    password="test1234", 
	    projectId=67,
	    testSetId=73
	)

public class TestOneGrid extends stepsOne{
    public Properties Config = null;
    public Properties Datos = null;
    public Properties Elementos = null;
    public WebDriver driver = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;
    public String Resultado = "";
    public String Escenario = "";
    public String RutaEvidencia = "";

    @Before
    public void PrepararEjecucion() throws FileNotFoundException{
        Config = this.getPropetiesFile("configuracion\\configuracion.properties");
        Datos = this.getPropetiesFile("configuracion\\datos.properties");
        Elementos = this.getPropetiesFile("configuracion\\pageOne.properties");
        contador = 1;
        driver = this.openBrowser(Config.getProperty("Navegador"));
        RutaEvidencia = Config.getProperty("rutaEvidencia");
        Resultado = "";
    }

    @Test
    @SpiraTestCase(testCaseId=6900)
    public void TestBuscarGoogle() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "CP01_EMA_Ejemplo_Evidencia";
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driver, contador, Config);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto"));
            this.ingresar_contenido(driver, Datos.getProperty("texto"), contador, Config, Elementos);
            
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driver, Datos, Config, contador);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador);
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
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driver, contador, Config);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google: "+Datos.getProperty("texto2"));
            this.ingresar_contenido(driver, Datos.getProperty("texto2"), contador, Config, Elementos);
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driver, Datos, Config, contador);
            
            
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"));
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7011)
    public void TestBuscarGoogle3() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            
            Escenario = "CP03_EMA_Ejemplo_Evidencia";
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            this.ingresar_A_URL(driver, contador, Config);
            
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto3"));
            this.ingresar_contenido(driver, Datos.getProperty("texto3"), contador, Config, Elementos);
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driver, Datos, Config, contador);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"));
        }
    }

    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
        
    }
}