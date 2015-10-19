package eetac.propsimulator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import eetac.test.Junit.Compressortest;

public class propsimconsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Request request = Request.aClass(Compressortest.class);

		Result result = new JUnitCore().run(request);
		System.exit(result.wasSuccessful() ? 0 : 1);

	}
}
