package tp.pr1;

import java.util.Random;

public class Game {
	private int _size;
	private int _initCells;
	private Random _myRandom;
	private Board _board;
	private int puntuacion;
	private int maxValue;
	static final int PRC = 10;
	
	private static final String NEWLINE = System.getProperty ("line.separador");

	
		/**
		 * @param _size
		 * @param _initCells
		 * @param _myRandom
		 */

		public Game(int _size, int _initCells, Random _myRandom) {
			super();
			this._size = _size;
			this._initCells = _initCells;
			this._myRandom = _myRandom;
			_board =new Board (_size);
		}
		public int getMaximo (){
			return maxValue;
		}
		
		@Override
		public String toString() {
			return "Score: "+puntuacion + NEWLINE +"Highest: "+ maxValue + NEWLINE;
		}
		
		public int get_size() {
			return _size;
		}

		public void set_size(int _size) {
			this._size = _size;
		}

		public Board get_board() {
			return _board;
		}

		public void set_board(Board _board) {
			this._board = _board;
		}
		
		/**
		 * Dependiendo de la direccion dir se crea un MoveResult con la informacion del movimiento.
		 * @param dir
		 */

		public void move (Direccion dir){
			int score = 0;			
			int maximo = 0;
			boolean hayMov = false;
			
			
			MoveResult mov = new MoveResult (hayMov, score, maximo);
			mov = this._board.executeMove(dir);
			puntuacion = puntuacion + mov.getPoints();
			if (maxValue < mov.getMaxToken()){
				maxValue = mov.getMaxToken();
			}
			
		}
		
		/**
		 * Inicializa el tablero "_board" con un valor Random.
		 */
		
		public void inicializar(){
			this._board.inicializar();
			
			puntuacion =0;
			
			int iniciales =0;
			while (iniciales < this._initCells){
				int num1 = this._myRandom.nextInt(this._size);
				int num2 = this._myRandom.nextInt(this._size);
				if (num1 > num2){
					maxValue = num1;
				}
				else {
					maxValue = num2;
				}
				if (this._board.valorCelda(num1,num2)== 0){
					int valor = this._myRandom.nextInt()% PRC;
					Position pos = new Position (num1, num2);
					if (valor == 0){
						this._board.setCell(pos, 4);
					}
					else{
						this._board.setCell(pos, 2);
					}
					iniciales++;
				}
			}
			
		}
		
		public void reset (){
			this.inicializar();
		}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
