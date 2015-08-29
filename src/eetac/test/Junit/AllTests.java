package eetac.test.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CombustionChamberTest.class, Compressortest.class, EngineTest.class })
public class AllTests {

}
