package TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.inflectra.spiratest.addons.junitextension.*;
import java.io.FileNotFoundException;
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
    public WebDriver driver = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;

    @Before
    public void PrepararEjecucion() throws FileNotFoundException{
        Config = this.getPropetiesFile();
        Datos = this.getDatosFile();
        driver = this.openBrowser(Config.getProperty("Navegador"), Config.getProperty("urlApp"));
        contador = 1;
    }

    @Test
    @SpiraTestCase(testCaseId=6900)
    public void TestBuscarGoogle() throws InterruptedException {
        
        try{
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            //Paso 2
            this.ingresar_contenido(driver, Datos.getProperty("texto"));
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto"));
            //paso 3
            this.dormir10seg();
            contador++;
            Pasos.add(contador+".- Esperamos 10 segundos.");
            
        }catch(NoSuchElementException s){
            System.out.println("No se encontró el elemento: "+s);
        }catch(InterruptedException e){
            System.out.println("No se ejecutó correctamente el script: "+e);
        }finally{
            System.out.println("Lista: "+Pasos);
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7010)
    public void TestBuscarGoogle2() throws InterruptedException {
        try{
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            //Paso 2
            this.ingresar_contenido(driver, Datos.getProperty("texto2"));
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google: "+Datos.getProperty("texto2"));
            //paso 3
            this.dormir10seg();
            contador++;
            Pasos.add(contador+".- Esperamos 10 segundos.");
            
        }catch(NoSuchElementException s){
            System.out.println("No se encontró el elemento: "+s);
        }catch(InterruptedException e){
            System.out.println("No se ejecutó correctamente el script: "+e);
        }finally{
            System.out.println("Lista: "+Pasos);
        }
    }
    
    @Test
    @SpiraTestCase(testCaseId=7011)
    public void TestBuscarGoogle3() throws InterruptedException {
        try{
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlApp"));
            //Paso 2
            this.ingresar_contenido(driver, Datos.getProperty("texto3"));
            contador++;
            Pasos.add(contador+".- Agregamos el contenido para buscar en google"+Datos.getProperty("texto3"));
            //paso 3
            this.dormir10seg();
            contador++;
            Pasos.add(contador+".- Esperamos 10 segundos.");
            
        }catch(NoSuchElementException s){
            System.out.println("No se encontró el elemento: "+s);
        }catch(InterruptedException e){
            System.out.println("No se ejecutó correctamente el script: "+e);
        }finally{
            System.out.println("Lista: "+Pasos);
        }
    }

    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
        
    }
}
