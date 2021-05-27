package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	

	private int numRows;
	private int numCols;
	private int numSnakes;
	private int numLadders;
	private String numPlayers;
	
	public int getNumSnakes() {
		return numSnakes;
	}
	public void setNumSnakes(int numSnakes) {
		this.numSnakes = numSnakes;
	}
	public int getNumLadders() {
		return numLadders;
	}
	public void setNumLadders(int numLadders) {
		this.numLadders = numLadders;
	}
	public String getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(String numPlayers) {
		this.numPlayers = numPlayers;
	}

	private Tile first;
	private Tile actual;
	private List<Player>players;
	/**
	* Constructor of the class  <br>
	* <b> pre: </b>  
	* <b> post: </b> the variables are initialized, the numbers are 0 and the strings are empty.
	*
	*/
	public Board(int a, int b, int c, int d, String e) {
		numRows = a;
		numCols = b;
		numSnakes = c;
		numLadders = d;
		numPlayers = e;
		players = new ArrayList<>();

	}
	/**
	 * creation of the matrix and initialization of the attributes of the game zone class  <br>
	 * <b> pre: </b>
	 * <b> post: </b> a game zone type object in addition to the initialization of the variables.
	 * @param numPlayers the variable that stores the player's username. numPlayers != null.
	 * @param numfilas the number of rows in the matrix. numfilas != null.
	 * @param numcolumnas the number of columns in the matrix. numcolumnas != null.
	 * @param nummirrors the number of mirrors in the matrix. nummirrors != null.
	 */

	public void createPlayers(String e) {
		String[]array = e.split("");
		for(int i = 0; i<array.length; i++) {
			createPlayer(array[i], (i+1));
		}
	}
	
	
	public void createPlayer(String a, int b) {
		players.add(new Player(a, b));
	}
	
	public Tile getActual() {
		return actual;

	}

	
	

}

