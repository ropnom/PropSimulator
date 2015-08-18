package other_JAVA_test;

public class Contenedor {
	
	protected Objeto1 a;
	protected Objeto1 b;
	
	
	public Objeto1 getA() {
		return a;
	}
	public void setA(Objeto1 a) {
		this.a = a;
	}
	public Objeto1 getB() {
		return b;
	}
	public void setB(Objeto1 b) {
		this.b = b;
	}
	
	public void Print(){
		System.out.println("Objet a is: "+ a.getCampos());
		System.out.println("Objet b is: "+ b.getCampos());
	}
	

}
