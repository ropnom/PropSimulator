package eetac.test.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eetac.model.realcomponent.Diffusser;

@RunWith(Suite.class)
@SuiteClasses({ Diffusortest.class, Compressortest.class, CombustionChamberTest.class, Turbinetest.class, Nozzletest.class, simulationProjetTest.class })
public class AllTests {

}
