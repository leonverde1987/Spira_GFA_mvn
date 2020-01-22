/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestCases;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author TestingIT
 */
public class demoIPA {
    
    public WebDriver driver = null;
    public DesiredCapabilities capa = null;
    public String url = "";
    
    @Before
    public void PrepararEjecucion() throws MalformedURLException{
        
        capa = new DesiredCapabilities();
        capa.setCapability("platformName", "iOS");
        capa.setPlatform(Platform.MAC);
        capa.setBrowserName("Safari");
        capa.setCapability("deviceName", "iPhone 8");
        capa.setVersion("11.4");
        capa.setCapability("automationName", "XCUITest");
        driver = new RemoteWebDriver(new URL(""), capa);
    }

    @Test
    public void levantarGoogle() throws InterruptedException{
        driver.get("https://google.com.mx");
        Thread.sleep(10000);
    }
    
    @After
    public void CerrarTest(){
        driver.close();
    }
}
