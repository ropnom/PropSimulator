package other_JAVA_test;

import eetac.model.AuxMethods;

public class testobjetos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Defino objetos");

		double[][] cosas1 = { { 1, 1 }, { 1, 2 } };
		double[][] cosas2 = { { 2, 2 }, { 2, 1 } };
		double[][] cosas3 = AuxMethods.Copy_matrix(cosas1);
		cosas3[0][0] = -3;
		Objeto1 a = new Objeto1(1, "este es le objeto a idnum1");
		a.setCosas(cosas1);
		
		

		Objeto1 b = new Objeto1(2, "campos del objetob num 2");
		b.setCosas(AuxMethods.Copy_matrix(a.getCosas()));
		Contenedor contene = new Contenedor();

		contene.setA(a);
		contene.setB(b);

		System.out.println(" imprimimso el contenedor");
		System.out.println();
		contene.Print();

		System.out.println("Modificadmos a");

		a.setCampos("He modificado el cmapo de a");
		a.getCosas()[0][0] = -5;
		a.getCosas()[0][1] = -5;

		System.out.println();

		System.out.println("Chequeo que se ha modificado en contenedor");

		System.out.println();
		contene.Print();

		System.out.println();
		
		for (int i = 0; i<cosas1.length; i++){
			for (int j = 0; j<cosas1.length; j++){
			System.out.println(" Element "+i+"_"+j+" :"+cosas3[i][j]);
			}
			
		}

		System.out.println("Chequeo que se ha modificado en contenedor");

		System.out.println();
		contene.Print();
	}

}
