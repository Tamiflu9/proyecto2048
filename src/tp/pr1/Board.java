package tp.pr1;


public class Board {
	private int _boardSize;
	private Cell  [][] _board = new Cell [_boardSize][_boardSize];
	
	private static final String NEWLINE = System.getProperty ("line.separador");
	
	public Board(int _boardSize) {
		super();
		_board = new Cell [_boardSize][_boardSize];
		this._boardSize = _boardSize;
	}

	public int get_boardSize() {
		return _boardSize;
	}

	public void set_boardSize(int _boardSize) {
		this._boardSize = _boardSize;
	}
	
	public Cell[][] get_board() {
		return _board;
	}

	public void set_board(Cell[][] _board) {
		this._board = _board;
	}
	
	public int valorCelda (int a, int b){
		return this._board[a][b].getValor();
	}
	
	/**
	 * @return : Devuelve el tablero "_board" inicializado.
	 */
	
	public Cell[][] inicializar(){
		
		for (int i = 0; i < _boardSize  ; i++){
			for(int j = 0; j< _boardSize; j++){
				_board [i][j]= new Cell (0);
			}
		}
		return _board;
	}
	
	/**
	 * @param pos
	 * @param value
	 * método usado para modificar el valor de la baldosa de la posición pos con el valor value
	 */
	
	public void setCell(Position pos, int value){
		_board [pos.getX()][pos.getY()].setValor(value);
		
	}
	
	/**
	 * @return : Devuelve la información del moviento cuando es hacia la derecha.
	 */
	
	private MoveResult moverDerecha(){
		boolean movimiento = false;
		int puntos=0;
		int maximo=2;// fuera para la primera vez hay que comprobar que no haya un 4.
		Cell board [][]= this.get_board();
		int size = this.get_boardSize();
		//Cell celda1 = new Cell (0);
		//Cell celda2;
		//int pos = size-1;
		
		for (int j =0; j< size; j++){
			Position p = new Position (size-1,j);
			for(int i= size-2; i >=0; i--){
				Position posV = new Position (p.posicionVecina(Direccion.Right, size).getX(),j);
				if (!board[i][j].isEmpty(board[i][j].getValor())){
					if(board[p.getX()][p.getY()].isEmpty(board[p.getX()][p.getY()].getValor())){
						board[p.getX()][p.getY()].setValor(board[i][j].getValor());
						board[i][j].setValor(0);
						movimiento = true;
						
					}
					else if (board[p.getX()][j].doMerge(board[i][j])){
						p=posV;
						movimiento = true;
						puntos = puntos + board [p.getX()][p.getY()].getValor();
					}
					else{
						board [posV.getX()][j].setValor(board[i][j].getValor());
						board[i][j].setValor (0);
						if (i != posV.getX()){
							movimiento = true;
						}
						p=posV;
					}
				}
			}
		}		
		MoveResult infoMov = new MoveResult(movimiento, puntos, maximo);
		return infoMov;
	}
	
	/**
	 * @return : Devuelve la información del moviento cuando es hacia la izquierda.
	 */
	
	private MoveResult moverIzquierda(){
		boolean movimiento = false;
		int puntos=0;
		int maximo=2;// fuera para la primera vez hay que comprobar que no haya un 4.
		Cell board [][]= this.get_board();
		int size = this.get_boardSize();
		
		for(int j =0; j< size; j++){
			Position p = new Position (0,j);
			for (int i = 1; i < size; i++){
				Position posV = new Position (p.posicionVecina(Direccion.Left, size).getX(), p.posicionVecina(Direccion.Left, size).getY());
				if (!board[i][j].isEmpty(board[i][j].getValor())){
					if(board[p.getX()][j].isEmpty(board[p.getX()][j].getValor())){
						board[p.getX()][j].setValor(board[i][j].getValor());
						board[i][j].setValor(0);
						movimiento = true;
					}
					else if (board[p.getX()][j].doMerge(board[i][j])){
						p=posV;
						movimiento = true;
						puntos = puntos + board [p.getX()][p.getY()].getValor();
						if (board[p.getX()][j].getValor()> maximo){
							maximo = board[p.getX()][j].getValor();
						}
					}
					else{
						board [posV.getX()][j].setValor(board[i][j].getValor());
						board[i][j].setValor (0);
						if (i != posV.getX()){
							movimiento = true;
						}
						p=posV;
					}
				}
			}
		}
		MoveResult infoMov = new MoveResult(movimiento, puntos, maximo);
		return infoMov;
	}
	
	/**
	 * @return : Devuelve la información del moviento cuando es hacia arriba.
	 */
			
	private MoveResult moverArriba (){
		boolean movimiento = false;
		int puntos=0;
		int maximo=2;// fuera para la primera vez hay que comprobar que no haya un 4.
		Cell board [][]= this.get_board();
		int size = this.get_boardSize();
		
		for(int i =0; i< size; i++){
			Position p = new Position (i,0);
			for (int j = 1; j <size; j++){
				Position posV = new Position (p.posicionVecina(Direccion.Up, size).getX(), p.posicionVecina(Direccion.Up, size).getY());
				if (!board[i][j].isEmpty(board[i][j].getValor())){
					if(board[i][p.getY()].isEmpty(board[p.getX()][p.getY()].getValor())){
						board[p.getX()][p.getY()].setValor(board[i][j].getValor());
						board[i][j].setValor(0);
						movimiento = true;
					}
					else if (board[i][p.getY()].doMerge(board[i][j])){
						p=posV;
						movimiento = true;
						puntos = puntos + board [p.getX()][p.getY()].getValor();
						if (board[i][p.getY()].getValor()> maximo){
							maximo = board[p.getX()][p.getY()].getValor();
						}
					}
					else{
						board [posV.getX()][posV.getY()].setValor(board[i][j].getValor());
						board[i][j].setValor (0);
						if (j != posV.getY()){
							movimiento = true;
						}
						p=posV;
					}
				}
			}
		}
		MoveResult infoMov = new MoveResult(movimiento, puntos, maximo);
		return infoMov;
	}
	
	/**
	 * @return : Devuelve la información del moviento cuando es hacia abajo.
	 */
	
	private MoveResult moverAbajo(){
		boolean movimiento = false;
		int puntos=0;
		int maximo=2;// fuera para la primera vez hay que comprobar que no haya un 4.
		Cell board [][]= this.get_board();
		int size = this.get_boardSize();
		
		for(int i =0; i< size; i++){
			Position p = new Position (i,size-1);
			for (int j = size-2; j >= 0; j--){
				Position posV = new Position (p.posicionVecina(Direccion.Down, size).getX(), p.posicionVecina(Direccion.Down, size).getY());
				if (!board[i][j].isEmpty(board[i][j].getValor())){
					if(board[i][p.getY()].isEmpty(board[i][p.getY()].getValor())){
						board[i][p.getY()].setValor(board[i][j].getValor());
						board[i][j].setValor(0);
						movimiento = true;
					}
					else if (board[i][p.getY()].doMerge(board[i][j])){
						p=posV;
						movimiento = true;
						puntos = puntos + board [p.getX()][p.getY()].getValor();
						if (board[i][p.getY()].getValor()> maximo){
							maximo = board[i][p.getY()].getValor();
						}
					}
					else{
						board [i][posV.getY()].setValor(board[i][j].getValor());
						board[i][j].setValor (0);
						if (j != posV.getY()){
							movimiento = true;
						}
						p=posV;
					}
				}
			}
		}
		
		MoveResult infoMov = new MoveResult(movimiento, puntos, maximo);
		return infoMov;
	}
	
	/**
	 * @param dir
	 * @return : Devuelve la información del moviento en función de la direccon dir.
	 */
	
	 public MoveResult executeMove(Direccion dir){
		 MoveResult res = new MoveResult (false,0,0);
		
		 	if (dir == Direccion.Right)	{
		 		/*función privada que mueva a la derecha y me devuelva el MoveResult que tengo
			 	 que devolver en esta función.*/
		 		
		 		res = this.moverDerecha();
		 	}
			
		 	else if(dir == Direccion.Left){
			 
		 		res = this.moverIzquierda();
		 	}
		 	else if (dir == Direccion.Up){
		 		res = this.moverArriba();
		 	}
		 	else if (dir == Direccion.Down){
		 		res = this.moverAbajo();
		 	}
		 	
		 	
		return res ; 
	 }
	 
	 
	 public String toString(){
		 int cellSize = 7;
			String tablero = "";
			String space = " ";
			String vDelimiter = "|";
			String hDelimiter = "-";
			
			for(int i = 0; i < _boardSize*2+1; i++){
				for(int j = 0; j < cellSize*_boardSize+_boardSize;j++){
					if(i%2 == 0){
						if(j!= 0){
							tablero += hDelimiter;
						}
						else{
							tablero+= space;
						}
					}
					else{
						if(j%(cellSize) == 0 ){
							tablero += vDelimiter;
						}
						int filaC = i/2;
						int colC = j/(cellSize);
	
						if(filaC < _boardSize && colC < _boardSize && j%4 == 0 && j!=0 && _board[filaC][colC].getValor() != 0){
							int valor = _board[filaC][colC].getValor();
							tablero +=valor;
						}
						else{
							tablero += space;
						}
					}
				}
				tablero+= "\n";
			}
		return tablero;
		
	 }
	 
	

	 /**
	  * Comprueba si la posicion [i][j] esta vacia.
	  * @return : Devuelve un buleano 
	  */
	 
	 public boolean hayEspacio(){
		 boolean espacio = false;
		 int i =0;
		 int j = 0;
		 
		 while( j< _boardSize && !espacio){
			 while( i< _boardSize&& !espacio){
				 if (_board[i][j].isEmpty(_board[i][j].getValor())){
					 espacio = true;
				 }
				 i++;
			 }
			 i=0;
			 j++;
		 }
		 
		 return espacio;
	 }
	 
	 /**
	  * Comprueba si se puede hacer el movimiento de fusionar dos baldosas.
	  * @return : Devuelve un buleano.
	  */
	 
	 public boolean hayDoMerge(){
		 boolean merge = false;
		 int j =0;
		 int i =0;
		 
		 while( j< _boardSize && !merge){
			 while( i< _boardSize&& !merge){
				 Position pos = new Position (i,j);
				 if (_board[i][j].getValor() == _board [pos.posicionVecina(Direccion.Down, _boardSize).getX()][pos.posicionVecina(Direccion.Down, _boardSize).getY()].getValor()){
					 merge = true;
				 }
				 else if (_board[i][j].getValor() == _board [pos.posicionVecina(Direccion.Left, _boardSize).getX()][pos.posicionVecina(Direccion.Left, _boardSize).getY()].getValor()){
					 merge = true;
				 }
				 else if (_board[i][j].getValor() == _board [pos.posicionVecina(Direccion.Right, _boardSize).getX()][pos.posicionVecina(Direccion.Right, _boardSize).getY()].getValor()){
					 merge = true;
				 }
				 else if (_board[i][j].getValor() == _board [pos.posicionVecina(Direccion.Up, _boardSize).getX()][pos.posicionVecina(Direccion.Up, _boardSize).getY()].getValor()){
					 merge = true;
				 }
				 i++;
			 }
			 i=0;
			 j++;
		 }
		 
		 return merge;
	 }
	 
	 /**
	  * Comprueba si se puede realizar la fusion de baldosas 
	  *     o si hay un espacio en blaco para un posible movimiento.
	  * @return : Devuelve un buleano.
	  */
	 
	 public boolean hayMov(){
		 boolean mov = false;
		 
		 if (hayEspacio() || hayDoMerge()){
			 mov = true;
		 }
		 
		 return mov;
	 }
	
	public static void main(String[] args) {
		/*Board tablero = new Board (2);
		System.out.print(tablero.get_boardSize()+ NEWLINE);
		tablero.inicializar();
		
		for (int j=0; j< tablero._boardSize; j++){
			for (int i=0; i< tablero._boardSize; i++){
				System.out.print(tablero.valorCelda(i, j)+ " ");
			}
			System.out.print(NEWLINE);
		}
		Position pos = new Position (0,1);
		tablero.setCell(pos, 2);
		for (int i=0; i< tablero._boardSize; i++){
			for (int j=0; j< tablero._boardSize; j++){
				System.out.print(tablero.valorCelda(i, j)+ " ");
			}
			System.out.println(NEWLINE);
		}
		tablero.executeMove(Direccion.Up);
		for (int i=0; i< tablero._boardSize; i++){
			for (int j=0; j< tablero._boardSize; j++){
				System.out.print(tablero.valorCelda(i, j)+ " ");
			}
			System.out.println(NEWLINE);
		}*/
		
	}

}
