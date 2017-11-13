package tp.pr1;

public class Cell {
	
	/**
	 * @return : Muestra el valor.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Modifica el valor.
	 * @param valor
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	private int valor;
	
	public Cell (int valor) {
		super();
		//this.pos = pos;
		this.valor = valor;
	}
	
	/**
	 * Comprueba si el valor de la baldosa es 0.
	 * @param v
	 * @return : Devuelve un buleano.
	 */
	public boolean isEmpty(int v){
		boolean empty = false;
		
		if (v == 0){
			empty = true;
		}
		
		return empty;
	}
	
	/**
	 * Comprueba si se puede hacer la fusion de un baldosa con la baldosa vecina.
	 * Si es posible la fusion de las baldosas. 
	 * @param neighbour
	 * @return : Devuelve un buleano.
	 */
	public boolean doMerge(Cell neighbour) {
		boolean igual = false;
		
		if (neighbour.valor == this.valor){
			igual = true;
			this.setValor(this.getValor() + neighbour.getValor());
			neighbour.setValor(0);
		}
		
		return igual;
	}
	

	public static void main(String[] args) {
		Cell celda1 = new Cell (1);
		Cell celda2 = new Cell (1);
		
		System.out.print(celda1.getValor()+ " ");
		System.out.print(celda2.getValor()+ " ");
		
		celda1.doMerge(celda2);
		
		System.out.print(celda1.getValor());
		// TODO Auto-generated method stub

	}

}
