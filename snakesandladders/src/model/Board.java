package model;


public class Board {

	private char snakes;
	private char ladders;
	private int size;
	private char leftCorchette;
	private char rightCorchette;
	private String players;
	private Board nextMvmnt;
	

	public Board(int rowAndColumn) {
		this.size= rowAndColumn;
		leftCorchette = '['; rightCorchette =']';
	}
	
	public char getSnakes() {
		return snakes;
	}

	public void setSnakes(char snakes) {
		this.snakes = snakes;
	}

	public char getLadders() {
		return ladders;
	}

	public void setLadders(char ladders) {
		this.ladders = ladders;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int rowAndColumn) {
		this.size = rowAndColumn;
	}

	public char getLeftCorchette() {
		return leftCorchette;
	}

	public void setLeftCorchette(char leftCorchette) {
		this.leftCorchette = leftCorchette;
	}
	
	public char getRightCorchette() {
		return rightCorchette;
	}
	
	public void setRightCorchette(char rightCorchette) {
		this.rightCorchette = rightCorchette;
	}

	public Board getNextMvmnt() {
		return nextMvmnt;
	}

	public void setNextMvmnt(Board nextMvmnt) {
		this.nextMvmnt = nextMvmnt;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}
}

