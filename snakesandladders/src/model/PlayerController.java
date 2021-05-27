package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerController {

	private Player root;
	private int position;
	
	
	public PlayerController () {
		root = null;
		position = 0;
	}
	
	public void startGame(int score, int columns, int rows, int snakes, int ladders, int players) throws FileNotFoundException, IOException {
		Player newPlayer = new Player(score, columns, rows, snakes, ladders, players);
		if (root == null) {
			root = newPlayer;
		} else {
			addPlayer(root, newPlayer);
		}
	}
	
	public void addPlayer(Player root, Player newPlayer) {
		if (newPlayer.getScore() <= root.getScore()) {
			if (root.getRight() != null) {
				addPlayer(root.getRight(), newPlayer);
			} else {
				root.setRight(newPlayer);
				newPlayer.setParent(root);
			}
		} else {
			if (root.getLeft() != null) {
				addPlayer(root.getLeft(), newPlayer);
			} else {
				root.setLeft(newPlayer);
				newPlayer.setParent(root);
			}
		} 
	}
	
}
