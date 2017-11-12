package tp.pr1;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner in;
	static final int ganado = 2048;
	
	private static final String NEWLINE = System.getProperty ("line.separador");
	
	public Controller(Game game, Scanner in) {
		super();
		this.game = game;
		this.in = in;
	}

	/**
	 * Realiza la simulación del juego.
	 * Mientras la partida no esté finalizada, solicita una orden al usuario
	 *	 y la ejecuta invocando a algún método de la clase Game a través
	 * 	 de su atributo game
	 * Excepto para el comando help que imprime la ayuda del juego.
	 */
	
	public void run (){
		String movimiento;
		
		this.game.inicializar();
		this.game.toString(); //imprimo score y max
		System.out.println(this.game.get_board().toString());// imprimo tablero
		
		System.out.print("Command > ");
		movimiento = in.nextLine();
		movimiento.toLowerCase();
		
		while (movimiento != "exit" && game.get_board().hayMov() && game.getMaximo() != ganado){
			
			this.game.toString(); //imprimo score y max
			System.out.println(this.game.get_board().toString());// imprimo tablero
			movimiento.toLowerCase();
			System.out.print("Command > "); 
			movimiento = in.nextLine();
			
			switch (movimiento){
			case "move right": 
				this.game.move(Direccion.Right);
				
			break;
			
			case "move left":
				this.game.move(Direccion.Left);
				
			break;
			
			case "move up":
				this.game.move(Direccion.Up);
			
			break;
			
			case "move down":
				this.game.move(Direccion.Down);
				
			break;
		
			case "help":
			    System.out.print ("Move <direction>: execute a move in one of the four directions, up, down, left, right"+ NEWLINE);
				System.out.print ("Reset: start a new game" + NEWLINE);
				System.out.print ("Help: print this help message" + NEWLINE);
				System.out.print ("Exit: terminate the program" + NEWLINE );
			    
			break;
			
			case "reset":
				this.game.inicializar();
				
			break;
			
			default :
				System.err.println("WRONG COMMAND!");
			}
		}
		
		if (game.getMaximo() == ganado){
			System.out.println("You've won!");
		}
		
		System.err.println("GAME OVER!");
		
		
		
}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
