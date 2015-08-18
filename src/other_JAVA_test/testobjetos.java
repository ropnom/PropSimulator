package other_JAVA_test;

public class testobjetos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Defino objetos");
		
		Objeto1 a = new Objeto1(1, "este es le objeto a idnum1");
		
		Objeto1 b = new Objeto1(2,"campos del objetob num 2");
		Contenedor contene = new Contenedor();
		
		contene.setA(a);
		contene.setB(b);
		
		System.out.println(" imprimimso el contenedor");
		System.out.println();
		contene.Print();
		
		System.out.println("Modificadmos a");
		
		a.setCampos("He modificado el cmapo de a");
		
		System.out.println();
		
		System.out.println("Chequeo que se ha modificado en contenedor");
		
		System.out.println();
		contene.Print();
	}

}
