/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author TestingIT
 */
public class evidence {
    
    public void capturaDriver(WebDriver driver, String rutaEvidencia, int contador){
        this.crea_Carpeta(rutaEvidencia);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
        try {
            FileUtils.copyFile(scrFile, new File(rutaEvidencia+fechaFormato()+"//"+"imagen"+contador+".png"));
        } catch (IOException ex) {
            Logger.getLogger(evidence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crea_Carpeta(String rutaEvidencia){
        File directorio = new File(rutaEvidencia+fechaFormato());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("El directorio ya existe");
            }
        }
    }
    
    public String fechaFormato(){
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
        Date fecha = new Date();
        return d.format(fecha).toString();
    }
    
    public void crearPDF(){
        
    }
    
    public void crearHTML(){
        
    }
    
}
