package SpiraRun;



import org.junit.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import generic.evidence;
import generic.generic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;

public class RunEvidencia extends generic{

    @Test
    public void TestBuscarGoogle() throws InterruptedException, DocumentException, BadElementException, IOException {
        List <String> Pasos = new ArrayList<String>();
        //Paso 1
        Pasos.add("1.- Abrimos navegador en la URL.");
        //Paso 2
        Pasos.add("2.- Buscamos GFA.");
            
            
        //this.crearPDF("CP01_EMA_Ejemplo_Evidencia", "Exitoso", 2, Pasos, "C:\\Evidencia");
        //this.crearXML("CP01_EMA_Ejemplo_Evidencia", "Exitoso", 2, Pasos, "C:\\Evidencia");
        //this.crearHTML("CP01_EMA_Ejemplo_Evidencia", "Exitoso", 2, Pasos, "C:\\Evidencia", "Mantenimiento EAM", "5.12.3");
        //this.crearHTML("CP02_EMA_Ejemplo_Evidencia", "Fallido", 2, Pasos, "C:\\Evidencia", "Mantenimiento EAM", "5.12.3");
        
        this.leventarNodosGrid(this.getPropetiesFile("configuracion\\configuracion.properties"));
        Thread.sleep(5000);
        this.cierraNodosGrid();
    }

}
