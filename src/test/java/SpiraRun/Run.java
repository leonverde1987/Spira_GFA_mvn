package SpiraRun;

import org.junit.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import org.junit.runner.JUnitCore;

public class Run{

    @Test
    public void TestBuscarGoogle() throws InterruptedException {
        JUnitCore core = new JUnitCore();
        core.addListener(new SpiraTestListener());
        core.run (TestCases.TestOne.class);
    }

}
