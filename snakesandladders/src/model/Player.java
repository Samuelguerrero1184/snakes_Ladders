package model;



public class Player {

	private char player;
	private int order;
	private int positions;
	private Player nextPlayer;
	private int score;
	private int col;
	private int rows;
	private int numSnakes;
	private int numLadders;
	private int numPlayers;
	private Player parent;
	private Player left;
	private Player right;
	
	public Player getLeft() {
		return left;
	}
	public void setLeft(Player left) {
		this.left = left;
	}
	public Player getRight() {
		return right;
	}
	public void setRight(Player right) {
		this.right = right;
	}
	public Player getParent() {
		return parent;
	}
	public void setParent(Player parent) {
		this.parent = parent;
	}
	public Player(char player, int order) {
		this.player = player;
		this.order = order;
		positions = 0;
	}
	public Player(int score, int col, int rows, int numSnakes, int numLadders, int numPlayers) {
		this.score = score;
		this.col = col;
		this.rows = rows;
		this.numSnakes = numSnakes;
		this.numLadders = numLadders;
		this.numPlayers = numPlayers;
	}
	
	public char getPlayer() {
		return player;
	}
	public void setPlayer(char player) {
		this.player = player;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getPositions() {
		return positions;
	}
	public void setPositions(int positions) {
		this.positions = positions;
	}
	public Player getNextPlayer() {
		return nextPlayer;
	}
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
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
	public int getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
}
