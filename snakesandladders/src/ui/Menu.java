package ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.*;





public class Menu  {
	
	static BufferedReader br;
	private static Board board;
	private static Tile tile;


	public void showmenu() {
		System.out.println("-------------SNAKES AND LADDERS-------------");
		System.out.println("1) Empezar juego");
		System.out.println("2) Mostrar tabla de posiciones");
		System.out.println("3) Salir del juego");
		
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
		Board bd = new Board(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[2]), Integer.parseInt(initialData[3]),initialData[4]);
		System.out.println(bd);
		System.out.println(bd.getNumPlayers());
		}
	}
	
	private void startBoard(int size) {
		if(size>= 1) {	
			tile.addList(size);
			size=size-1;
			createList(size);
		}
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
}
