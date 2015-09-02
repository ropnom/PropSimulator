package eetac.test.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eetac.model.realcomponent.Difusser;

@RunWith(Suite.class)
@SuiteClasses({ Diffusortest.class, CombustionChamberTest.class, Compressortest.class, Nozzletest.class, EngineTest.class })
public class AllTests {

}
