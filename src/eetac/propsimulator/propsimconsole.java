package eetac.propsimulator;

import eetac.model.component.Diffuser;

public class propsimconsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("*************************************************");
		System.out.println("iniciando primeras linea de propsim en modo test");		
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("Creamos y probamso el difusor");
		
		Diffuser difusor1 = new Diffuser(45.6);
		System.out.println(difusor1.Difuser_Values());
		
		System.out.println();
		Diffuser difusor2 = new Diffuser(56.5,6.4f,0.895);
		System.out.println(difusor2.Difuser_Values());
		
		System.out.println();
		Diffuser difusor3 = new Diffuser(62.4, 6.8f,95.8f, 0.87);
		System.out.println(difusor3.Difuser_Values());
		
		System.out.println();
		
		System.out.println("Test Diffusor End");
		
		System.out.println();
	}
}
