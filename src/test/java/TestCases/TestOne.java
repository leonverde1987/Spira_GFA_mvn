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

public class TestOne extends stepsOne{
    public Properties Config = null;
    public Properties Datos = null;
    public Properties Elementos = null;
    public WebDriver driver = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;
    public String Resultado = "Exitoso";
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
    }

    @Test
    @SpiraTestCase(testCaseId=6900)
    public void TestBuscarGoogle() throws InterruptedException, DocumentException, BadElementException, IOException {
        try{
            Escenario = "CP01_EMA_Ejemplo_Evidencia";
            
            //Paso 1
            this.ingresar_A_URL(driver, contador, Config);
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            
            //Paso 2
            contador++;
            this.ingresar_contenido(driver, Datos.getProperty("texto"), contador, Config, Elementos);
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto"));
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            Resultado = this.validarTitlePagina(driver, Datos, Config, contador);
            
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró el elemento: "+s;
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            System.out.println(Resultado);
        }finally{
            System.out.println("Lista: "+Pasos);
            //Generamos PDF
            this.crearPDF(Escenario, Resultado, contador, Pasos, RutaEvidencia);
            //Generamos PDF
            this.crearXML(Escenario, Resultado, contador, Pasos, RutaEvidencia);
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7010)
    public void TestBuscarGoogle2() throws InterruptedException, DocumentException, BadElementException, IOException {
        try{
            Escenario = "CP02_EMA_Ejemplo_Evidencia";
            
            //Paso 1
            this.ingresar_A_URL(driver, contador, Config);
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            
            //Paso 2
            contador++;
            this.ingresar_contenido(driver, Datos.getProperty("texto2"), contador, Config, Elementos);
            Pasos.add(contador+".- Agregamos el contenido para buscar en google: "+Datos.getProperty("texto2"));
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            this.validarTitlePagina(driver, Datos, Config, contador);
            
            
            
            
        }catch(NoSuchElementException s){
            System.out.println("No se encontró el elemento: "+s);
        }catch(InterruptedException e){
            System.out.println("No se ejecutó correctamente el script: "+e);
        }finally{
            System.out.println("Lista: "+Pasos);
            //Generamos PDF
            this.crearPDF(Escenario, Resultado, contador, Pasos, RutaEvidencia);
            //Generamos XML
            this.crearXML(Escenario, Resultado, contador, Pasos, RutaEvidencia);
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7011)
    public void TestBuscarGoogle3() throws InterruptedException, DocumentException, BadElementException, IOException {
        try{
            
            Escenario = "CP03_EMA_Ejemplo_Evidencia";
            
            //Paso 1
            this.ingresar_A_URL(driver, contador, Config);
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            
            //Paso 2
            contador++;
            this.ingresar_contenido(driver, Datos.getProperty("texto3"), contador, Config, Elementos);
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto3"));
            
            //paso 3
            contador++;
            Pasos.add(contador+".- Validamos la busqueda en Google.");
            this.validarTitlePagina(driver, Datos, Config, contador);
            
            
        }catch(NoSuchElementException s){
            System.out.println("No se encontró el elemento: "+s);
        }catch(InterruptedException e){
            System.out.println("No se ejecutó correctamente el script: "+e);
        }finally{
            System.out.println("Lista: "+Pasos);
            //Generamos PDF
            this.crearPDF(Escenario, Resultado, contador, Pasos, RutaEvidencia);
            //Generamos PDF
            this.crearXML(Escenario, Resultado, contador, Pasos, RutaEvidencia);
        }
    }

    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
        
    }
}
