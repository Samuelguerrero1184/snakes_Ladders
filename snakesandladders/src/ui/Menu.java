package ui;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import model.*;





public class Menu  {
	
	static BufferedReader br;
	private BoardControl boardC;
	private PlayerController leaderBoard;

	
	public Menu () {
		leaderBoard = new PlayerController();
	}


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
				createGameZone();
				
				break;
			case 2: 
				System.out.println();
				System.out.println("");
				leaderBoard.order(leaderBoard.getRoot());
				leaderBoard.restartPosition();
				System.out.println();
				startprogram();;
			}
			startprogram();	
		}
	}


	public void createGameZone() throws IOException {
		System.out.println("Digita los siguientes datos separados por espacios \nNumero de filas de la cuadricula,"+
	" Numero de columnas de la cuadricula , cantidad de serpientes, cantidad de escaleras, Simbolos de los jugadores (sin separar)");
		String[] initialData = br.readLine().split(" ");
		String[] symbArray = (initialData[4].split(""));
		int playerAmount = symbArray.length;
		//Getting amount of cells in the board, to verify if the amount of snakes and ladders is coherent
		int size1 = Integer.parseInt(initialData[0])*Integer.parseInt(initialData[1]);
		boardC = new BoardControl();
		//i multiply every snakes and ladders value by 2 because each one has a start and an end
		int snakesAndLaddersValidation = (Integer.parseInt(initialData[2])*2)+(Integer.parseInt(initialData[3])*2);
		if(snakesAndLaddersValidation<size1){
			if(Integer.parseInt(initialData[1]) <= 26 && Integer.parseInt(initialData[2]) <= size1 && Integer.parseInt(initialData[3]) <= size1) {
			startBoard(size1);
			strtPlayers(size1, initialData[4]);
			addEol(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[3]), 49);
			addSol(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[3]), 49);
			addEos(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[2]), 65);
			addSos(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]), Integer.parseInt(initialData[2]), 65);
			createPlayers(initialData[4],playerAmount-1);
			playGame(Integer.parseInt(initialData[0]), Integer.parseInt(initialData[1]));
			play(0, playerAmount, Integer.parseInt(initialData[0]), 
					Integer.parseInt(initialData[1]), Integer.parseInt(initialData[2]), 
					Integer.parseInt(initialData[3]), false);
		}
		}
		else {
			
			System.out.println("La cantidad de serpientes y/o escaleras es demasiada para el tamaño del leaderBoardro, porfavor ingrese nuevamente");
			createGameZone();
		}
	}
	
	private void startBoard(int size) {
		if(size>= 1) {	
			boardC.addBoard(size);
			size=size-1;
			startBoard(size);
		}
	}
	
	private void strtPlayers(int i, String players) {		
		boardC.pos(i);
		boardC.symb(players);
	}
	
	private void createPlayers(String player, int totalPlayer) {
		if(totalPlayer>=0){
			boardC.addPlayer(player.charAt(totalPlayer), totalPlayer);
			totalPlayer--;;
			createPlayers(player, totalPlayer);
		}
	}
	

	public void playGame(int row, int column) throws IOException {
		String starting = br.readLine();
		if(starting.isEmpty()) {
			System.out.println(boardC.addContent(row, column));
		}
	}
	
	public void play(int turn, int amountPlayers, int row, int column, int snake, int ladders, boolean simulationMode) throws IOException {
		
		if(!simulationMode) {
			if(turn<amountPlayers) {
				String next =br.readLine();
				if(next.isEmpty()) {
					char player = boardC.player(turn).getPlayer();		
					Random gen = new Random();
					int dice = 1+gen.nextInt(6);
					System.out.println("Player "+player+" has a score of "+dice);
					boardC.mvmnts(turn);
					if(boardC.movePlayers(player, turn, dice)) {
						System.out.println(boardC.showContent(row, column));
						System.out.println("Player " +player+" has won!" );
						System.out.println("Now, put your name: ");
						String nickname = br.readLine();
					
						leaderBoard.startGame(boardC.allMvmnts(boardC.player(turn))*(row*column), column, row, snake, ladders, amountPlayers, player);// binary three
						System.out.println("Player: "+nickname+"\n" +"Score: " +boardC.allMvmnts(boardC.player(turn))*(row*column)+"\n");
						startprogram();
					}else {
						System.out.println(boardC.showContent(row, column));
						play(turn++, amountPlayers, row,column, snake, ladders, simulationMode);
					}
				}else if(next.equalsIgnoreCase("num")){
					System.out.println(boardC.addContent(row, column));
					play(turn, amountPlayers, row, column, snake, ladders, simulationMode);
				}else if(next.equalsIgnoreCase("menu")){
					startprogram();
				}else if(next.equalsIgnoreCase("simul")) {
					System.out.println("Wait 2 seconds . . .");
					simulationMode = true;
					play(turn, amountPlayers, row,column, snake, ladders, simulationMode);
				}
			}else {
				play(0, amountPlayers,row , column, snake, ladders, simulationMode);
			}
		}else {
			if(turn<amountPlayers) {
				try {
					Thread.sleep(2000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				pressEnter();
				String next =br.readLine();
				if(next.isEmpty()) {
					char player = boardC.player(turn).getPlayer();		
					Random rdm = new Random();
					int dice = 1+rdm.nextInt(6);
					System.out.println("Player "+player+" has a score of "+dice);
					boardC.mvmnts(turn);
					if(boardC.movePlayers(player, turn, dice)) {
						System.out.println(boardC.showContent(row, column));
						System.out.println("Player " +player+" has won!" );
						System.out.println("Now, put your name: ");
						releaseEnter();
						String nickname = br.readLine();
						leaderBoard.startGame( boardC.allMvmnts(boardC.player(turn))*(row*column), column, row, snake, ladders, amountPlayers, player);// calling binary three
						System.out.println("Player: "+nickname+"\n" +"Score: " +boardC.allMvmnts(boardC.player(turn))*(row*column)+"\n");
						startprogram();
					}else {
						System.out.println(boardC.showContent(row, column));
						play(turn=turn+1, amountPlayers, row,column, snake, ladders, simulationMode);
					}
				}
			}else {
				play(0, amountPlayers,row , column, snake, ladders, simulationMode);
			}
		}
	}
	
	public void pressEnter() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		} 
	}
	
	public void releaseEnter() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		} 
	}
	
	private void addEos(int rows, int columns, int snakes, int type) {
		
		if(snakes>0) {
			Random random = new Random();
			int total = rows*columns;
			int part_1= (total/2)-1;
			int posDown = 2+random.nextInt(part_1);
		
			if(boardC.valLadder(boardC.readBoard(posDown)) && boardC.valSnake(boardC.readBoard(posDown))) {
				char snake_down = (char)(type);
				boardC.addSnake(snake_down, boardC.readBoard(posDown));
				
				addEos(rows, columns, snakes=snakes-1, type=type+1);
			}else {
				addEos(rows, columns, snakes, type);
			}
		}
	}
	
	private void addSos(int rows, int columns, int snakes, int type) {
		
		if(snakes>0) {
			Random random = new Random();
			int total = (rows*columns)-1;
			int part_1= (total/2)+2;
			int posUp = random.nextInt(total-part_1+1)+part_1;
			
			
			if(boardC.valLadder(boardC.readBoard(posUp)) && boardC.valSnake(boardC.readBoard(posUp))) {
				char snake_down = (char)(type);
				boardC.addSnake(snake_down, boardC.readBoard(posUp));
				addSos(rows, columns, snakes=snakes-1, type=type+1);
			}else {
				addSos(rows, columns, snakes, type);
			}	
		}
	}
	
	private void addEol(int rows, int columns, int ladders, int type) {
		if(ladders>0) {
			
			int size = rows*columns;
			int firstHalf= (size/2)-1;	
			Random random = new Random();
			int down = (2 + random.nextInt(firstHalf));
		
			if(boardC.valLadder(boardC.readBoard(down))) {
				char Eol = (char)(type);
				boardC.addLadder(Eol, boardC.readBoard(down));
				addEol(rows, columns, ladders=ladders-1, type=type+1);
			}else {
				addEol(rows, columns, ladders, type);
			}		
		}
	}
	
	private void addSol(int rows, int columns, int ladders, int type) {	
		if(ladders>0) {
			Random random = new Random();
			int total = rows*columns;
			int part_1= (total/2)+2;
			int posUp =random.nextInt(total-part_1+1)+part_1;
			if(boardC.valLadder(boardC.readBoard(posUp))){
				
				char ladder_up = (char)(type);
				boardC.addLadder(ladder_up, boardC.readBoard(posUp));
				addSol(rows, columns, ladders=ladders-1, type=type+1);
			}
			else{
				
				addSol(rows, columns, ladders, type);
			}
		}
	}
}
