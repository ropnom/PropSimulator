package eetac.test;

import static org.junit.Assert.*;

import org.junit.Test;

import eetac.model.structure.SimulationBlock;

public class SimulationBlockTest {

	SimulationBlock myblock;

	public void CargarValores() {
		myblock = new SimulationBlock();
	}

	@Test
	public void test_diferencial() {
		System.out.println("Test de calculo diferencial");
		
		//assertEquals("Diferencial must be ", 97000.0,myblock.get, 0.01);
	}

}
