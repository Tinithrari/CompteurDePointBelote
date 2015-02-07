import junit.framework.*;
import android.test.suitebuilder.TestSuiteBuilder;


public class AllTests extends TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(AllTests.class).includeAllPackagesUnderHere().build();
    }
}
