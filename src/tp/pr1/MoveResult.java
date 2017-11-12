package tp.pr1;

/**
 * @author usuario_local
 *
 */
public class MoveResult {

	private boolean moved;
	private int points;
	private int maxToken;
	private static final String NEWLINE = System.getProperty ("line.separador");
	
	
	
	public boolean isMoved() {
		return moved;
	}

	public int getPoints() {
		return points;
	}

	public int getMaxToken() {
		return maxToken;
	}

	/**
	 * @param moved
	 * @param points
	 * @param maxToken
	 */

	public MoveResult(boolean moved, int points, int maxToken) {
		super();
		this.moved = moved;
		this.points = points;
		this.maxToken = maxToken;
	}
	
	@Override
	/**
	 * Imprime por pantalla.
	 */
	public String toString(){
		return "Puntuación: " + this.points + NEWLINE + "Máximo: " + this.maxToken;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
