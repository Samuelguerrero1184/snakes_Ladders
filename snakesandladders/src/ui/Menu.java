package ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import model.*;





public class Menu  {
	
	static BufferedReader br;
	private BoardControl boardC;


	public void showmenu() {
		System.out.println("-------------SNAKES AND LADDERS-------------");
		System.out.println("1) Empezar juego");
		System.out.println("2) Mostrar tabla de posiciones");
		System.out.println("3) Salir del juego");
		
	}
	public void startprogram() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		showmenu();
		System.out.println("Ingrese la opcion");
		int x = Integer.parseInt(br.readLine());
		if (x != 3) {
			switch(x) {
			case 1:
				creategamezone();
				
				break;
			case 2: 
			
			}
			startprogram();	
		}
		
	}


	public void creategamezone() throws IOException {
		System.out.println("Digita los siguientes datos separados por espacios \nNumero de filas de la cuadricula,"+
	" Numero de columnas de la cuadricula , cantidad de serpientes, cantidad de escaleras, Simbolos de los jugadores (sin separar)");
		String[] initialData = br.readLine().split(" ");
		//Getting amount of cells in the board, to verify if the amount of snakes and ladders is coherent
		int size = (Integer.parseInt(initialData[0])*Integer.parseInt(initialData[1]));
		//i multiply every snakes and ladders value by 2 because each one has a start and an end
		int snakesAndLaddersValidation = (Integer.parseInt(initialData[2])*2)+(Integer.parseInt(initialData[3])*2);
		
		
		if(size>snakesAndLaddersValidation) {
			startBoard(size);
			strtPlayers(size, initialData[4]);
			addEol(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[3]), 49);
		}
		else {
			System.out.println("La cantidad de serpientes y/o escaleras es demasiada para el tamaño del tablero, porfavor ingrese nuevamente");
			creategamezone();
		}
	}
	
	
	private void startBoard(int size) {

		}
	private void strtPlayers(int i, String players) {
		boardC.pos(i);
		boardC.symb(players);
	}
	
	private void addEol(int rows, int columns, int ladders, int type) {
		
		if(ladders>0) {
			
			int size = rows*columns;
			int firstHalf= (size/2)-1;	
			Random random = new Random();
			int down = (2 + random.nextInt(firstHalf));
		
			if(boardC.valLadder(boardC.Board(down))) {
				char Eol = (char)(type);
				boardC.addLadder(Eol, boardC.Board(down));
				addEol(rows, columns, ladders=ladders-1, type=type+1);
			}else {
				addEol(rows, columns, ladders, type);
			}		
		}
	}

	

}
