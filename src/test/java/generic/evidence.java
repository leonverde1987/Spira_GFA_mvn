/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;

/**
 *
 * @author TestingIT
 */
public class evidence {
    
    public void capturaDriver(WebDriver driver, String rutaEvidencia, int contador){
        this.crea_Carpeta(rutaEvidencia);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
        try {
            FileUtils.copyFile(scrFile, new File(rutaEvidencia+"//"+fechaFormato()+"//evidencia"+contador+".png"));
        } catch (IOException ex) {
            Logger.getLogger(evidence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crea_Carpeta(String rutaEvidencia){
        File directorio = new File(rutaEvidencia+"//"+fechaFormato());
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
        return d.format(fecha);
    }
    
    public void crearPDF(String CasoPrueba, String Resultado, int contador, List<String> Pasos, String rutaEvidencia) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        

        
        // Se crea el documento
        Document documento = new Document(PageSize.A4);
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream("C:\\Evidencia\\"+CasoPrueba+this.fechaFormato()+".pdf");
        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        // Se abre el documento.
        documento.open();
        
        Font fuenteEncabezado= new Font();
        fuenteEncabezado.setColor(Color.BLACK);
        fuenteEncabezado.setStyle(Font.BOLD);
        fuenteEncabezado.setSize(12);
        
        Font fuenteBlanco= new Font();
        fuenteBlanco.setColor(Color.WHITE);
        fuenteBlanco.setStyle(Font.BOLD);
        fuenteBlanco.setSize(8);
        
        Font fuenteVerde= new Font();
        fuenteVerde.setColor(Color.GREEN);
        fuenteVerde.setStyle(Font.BOLD);
        fuenteVerde.setSize(12);
        
        Font fuenteRojo= new Font();
        fuenteRojo.setColor(Color.RED);
        fuenteRojo.setStyle(Font.BOLD);
        fuenteRojo.setSize(12);
        
        Font fuenteAzul= new Font();
        fuenteAzul.setColor(Color.BLUE);
        fuenteAzul.setStyle(Font.BOLD);
        fuenteAzul.setSize(12);
        
        Table encabezado = new Table(3);
        
        Image ima = Image.getInstance("GFA.png"); 
        ima.scaleToFit(90, 90);
        
        Cell celdaImagen = new Cell();
        celdaImagen.setBorder(0);
        celdaImagen.add(ima);
        encabezado.addCell(celdaImagen);
        
        Paragraph parrafo = new Paragraph("Reporte de Evidencia Pruebas Automatizadas", fuenteEncabezado); 
        Cell celda = new Cell();
        celda.setBorder(0);
        celda.add(parrafo);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        encabezado.addCell(celda);
        
        ima = Image.getInstance("TestingIT.png"); 
        ima.scaleToFit(90, 90);
        celdaImagen = new Cell();
        celdaImagen.setBorder(0);
        celdaImagen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaImagen.add(ima);
        encabezado.addCell(celdaImagen);
        
        encabezado.setBorder(0);
        documento.add(encabezado);
        
        Table DatosEjec = new Table(3);
        
        Paragraph parrafo1 = new Paragraph("Módulo EAM", fuenteBlanco); 
        parrafo1.add(Chunk.NEWLINE);
        parrafo1.add(Chunk.NEWLINE);
        Cell celda1 = new Cell();
        celda1.setBorder(0);
        celda1.setBackgroundColor(Color.BLUE);
        celda1.add(parrafo1);
        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        DatosEjec.addCell(celda1);
        
        Paragraph parrafo2 = new Paragraph("v 1.8.1", fuenteBlanco);
        parrafo2.add(Chunk.NEWLINE);
        parrafo2.add(Chunk.NEWLINE);
        Cell celda2 = new Cell();
        celda2.setBorder(0);
        celda2.setBackgroundColor(Color.BLUE);
        celda2.add(parrafo2);
        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
        DatosEjec.addCell(celda2);
        
        Paragraph parrafo3 = new Paragraph(this.fechaFormato(), fuenteBlanco); 
        parrafo3.add(Chunk.NEWLINE);
        parrafo3.add(Chunk.NEWLINE);
        Cell celda3 = new Cell();
        celda3.setBorderColor(Color.BLUE);
        celda3.setBackgroundColor(Color.BLUE);
        celda3.add(parrafo3);
        celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
        DatosEjec.addCell(celda3);
        
        
        Paragraph parrafo4 = new Paragraph(CasoPrueba, fuenteBlanco); 
        parrafo4.add(Chunk.NEWLINE);
        parrafo4.add(Chunk.NEWLINE);
        Cell celda4 = new Cell();
        celda4.setBorderColor(Color.GRAY);
        celda4.setBackgroundColor(Color.GRAY);
        celda4.setColspan(3);
        celda4.add(parrafo4);
        celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
        DatosEjec.addCell(celda4);
        
        
        Paragraph parrafo5 = new Paragraph(); 
        if("Exitoso".equals(Resultado)){
            parrafo5 = new Paragraph(Resultado, fuenteVerde);
        }
        if("Fallido".equals(Resultado)){
            parrafo5 = new Paragraph(Resultado, fuenteRojo);
        }
        if("Ejecución Fallida".equals(Resultado)){
            parrafo5 = new Paragraph(Resultado, fuenteAzul);
        }
        parrafo5.add(Chunk.NEWLINE);
        parrafo5.add(Chunk.NEWLINE);
        Cell celda5 = new Cell();
        celda5.setColspan(3);
        celda5.add(parrafo5);
        celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
        DatosEjec.addCell(celda5);
        DatosEjec.setBorder(0);
        documento.add(DatosEjec);
        
        
        for(int a=0; a<contador; a++){
            Table PasosEvidencia = new Table(1);
            
            Paragraph parrafo6 = new Paragraph("Paso: "+Pasos.get(a)); 
            Cell celda6 = new Cell();
            celda6.setBorder(0);
            celda6.add(parrafo6);
            celda6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            PasosEvidencia.addCell(celda6);
            PasosEvidencia.setBorder(0);
            documento.add(PasosEvidencia);
            
            Table DatosEvidencia = new Table(1);
            Image imaEvi = Image.getInstance(rutaEvidencia+"\\"+this.fechaFormato()+"\\evidencia"+(a+1)+".png"); 
            Cell celdaImagenEvi = new Cell();
            celdaImagenEvi.setBorder(0);
            celdaImagenEvi.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaImagenEvi.add(imaEvi);
            DatosEvidencia.addCell(celdaImagenEvi);
            DatosEvidencia.setBorder(0);
            documento.add(DatosEvidencia);
        }
        
        
        documento.close();
    }
    
    public void crearXML(String CasoPrueba, String Resultado, int contador, List<String> Pasos, String rutaEvidencia){
        try {
              DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
              DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
              //Elemento raíz
              org.w3c.dom.Document doc = docBuilder.newDocument();
              org.w3c.dom.Element suiteCasosPrueba = doc.createElement("SuiteCasosPrueba");
              doc.appendChild(suiteCasosPrueba);
              
              org.w3c.dom.Element casoPrueba = doc.createElement("CasoPrueba");
              suiteCasosPrueba.appendChild(casoPrueba);
              //Primer elemento
              org.w3c.dom.Element nombreTC = doc.createElement("Nombre");
              casoPrueba.appendChild(nombreTC);
              nombreTC.setTextContent(CasoPrueba);
              casoPrueba.appendChild(nombreTC);
              
              org.w3c.dom.Element moduloTC = doc.createElement("Módulo");
              moduloTC.setTextContent("Mantenimiento EAM");
              casoPrueba.appendChild(moduloTC);
              
              org.w3c.dom.Element fechaTC = doc.createElement("Fecha");
              fechaTC.setTextContent(this.fechaFormato());
              casoPrueba.appendChild(fechaTC);
              
              org.w3c.dom.Element resultadoTC = doc.createElement("Resultado");
              resultadoTC.setTextContent(Resultado);
              casoPrueba.appendChild(resultadoTC);
              
              org.w3c.dom.Element pasosTC = doc.createElement("PasosTC");
              resultadoTC.setTextContent(Resultado);
              casoPrueba.appendChild(pasosTC);
              
              for(int a=0; a<contador; a++){
                  org.w3c.dom.Element steps = doc.createElement("paso");
                  steps.setTextContent("Paso: "+Pasos.get(a));
                  steps.setAttribute("ruta", rutaEvidencia+"\\"+this.fechaFormato()+"\\evidencia"+(a+1)+".png");
                  pasosTC.appendChild(steps);
              }
              
              //Se escribe el contenido del XML en un archivo
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
              Transformer transformer = transformerFactory.newTransformer();
              DOMSource source = new DOMSource(doc);
              StreamResult result = new StreamResult(new File(rutaEvidencia+"\\"+this.fechaFormato()+"\\"+CasoPrueba+this.fechaFormato()+".xml"));
              transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
          pce.printStackTrace();
        } catch (TransformerException tfe) {
          tfe.printStackTrace();
        }
        
        

    }
    
}
