package model;

public class BoardControl {
	private Board start;
	private Board finish;
	private Player first;
	private Player last;
	private String table;
	private String table2;

	

	
	public BoardControl() {
		start=null;
		finish=null;
		table="";
		table2="";
	}
	
	
	//validating and controlling the cells

	
	public void addBoard(int rowXcolumns) {  
		if(start==null) {
			Board bd =  new Board(rowXcolumns);
			start = bd;
			finish = bd;
		}else {
			Board board =  new Board(rowXcolumns);
			finish.setNextMvmnt(board);
			finish=board;
		}
	}
	
	public Board Board(int pos) {  
		return searchMvmnt(pos, start);
	}	

	private Board searchMvmnt(int pos, Board temp) {
		if(temp.getRowXcolumn()==pos) {
			return temp;
		}else {
			temp=temp.getNextMvmnt();
			return searchMvmnt(pos, temp);
		}
	}
	
	public void assignSpaces(int i) {
		assignSpaces(i,start);
	}
	
	private void assignSpaces(int i, Board temporal) {
		if(i>0) {
		temporal.setPlayers("");
		temporal=temporal.getNextMvmnt();
		assignSpaces(i=i-1,temporal);
		}
	}
	
	public void assignPlayer(String players) {
		finish.setPlayers(players);
	}
	
	//validation for snakes and ladders
	
	public boolean checkLadder(Board ladders) {
		boolean found = false;
		if(ladders.getLadders()==0) {
			found =true;
		}
		return found;
	}
	
	public void putLadder(char ladder, Board pos) {
		pos.setLadders(ladder);
	}

	public boolean checkSnake(Board snakes) {
		boolean found = false;
		if(snakes.getSnakes()==0) {
			found =true;
		}
		return found;
	}

	public void putSnake(char snake, Board pos) {
		pos.setSnakes(snake);
	}

	public Board searchSnake(char snake) {
		return searchSnake(snake, start);
	}

	
	private Board searchSnake(char s, Board temp) {
		if(temp.getSnakes()==s) {
			return temp;
		}else {
			temp=temp.getNextMvmnt();
			return searchSnake(s, temp);
		}
	}

	
	public Board searchLadder(char ladder) {
		return searchLadder(ladder, start);
	}

	
	private Board searchLadder(char l, Board temp) {
		if(temp.getLadders()==l) {
			return temp;
		}else {
			temp=temp.getNextMvmnt();
			return searchLadder(l, temp);
		}
	}
	
	
}
