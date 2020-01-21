package SpiraRun;



import org.junit.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import org.junit.runner.JUnitCore;

public class Run{

    @Test
    public void TestBuscarGoogle() throws InterruptedException {
        JUnitCore core = new JUnitCore();
        //Add the custom SpiraTest listener
        core.addListener(new SpiraTestListener());
        //Finally run the test fixture
        core.run (TestCases.TestOne.class);
    }

}
