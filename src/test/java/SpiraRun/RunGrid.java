/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpiraRun;

import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import java.io.FileNotFoundException;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 *
 * @author TestingIT
 */
public class RunGrid {
    @Test
    public void TestBuscarGoogleGrid() throws InterruptedException, FileNotFoundException {
        JUnitCore core = new JUnitCore();
        //Add the custom SpiraTest listener
        core.addListener(new SpiraTestListener());
        //Finally run the test fixture
        new generic.generic().leventarNodosGrid();
        core.run (TestCases.TestOneGrid.class);
        
        new generic.generic().cierraNodosGrid();
    }
}