package model;



public class Tile {
		private char snakes;
		private char ladders;
		private int rowXcolumn;
		private String contentLeft;
		private String contentRight;
		private String players;
		private Board nextMvmnt;
		

		/**
		 * Create a List instance
		 * pre:
		 * pos:build a List instance
		 * @param row and column of the list
		 */

		public Tile(int rowAndColumn) {
			this.rowXcolumn= rowAndColumn;
			contentLeft = "[";
			contentRight="]";
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
		

		public int getRowXcolumn() {
			return rowXcolumn;
		}
		


		public void setRowXcolumn(int rowAndColumn) {
			this.rowXcolumn = rowAndColumn;
		}

		public String getContentLeft() {
			return contentLeft;
		}

		public void setContentLeft(String contentLeft) {
			this.contentLeft = contentLeft;
		}
		
		
		public String getContentRight() {
			return contentRight;
		}
		

		public void setContentRight(String contentRight) {
			this.contentRight = contentRight;
		}

		
		public Board getNextMvmnt() {
			return nextMvmnt;
		}
		

		public void setNextList(Board nextMvmnt) {
			this.nextMvmnt = nextMvmnt;
		}
		


		public String getPlayers() {
			return players;
		}


		public void setPlayers(String players) {
			this.players = players;
		}

}