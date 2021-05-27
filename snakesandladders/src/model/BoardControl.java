package model;

public class BoardControl {
	private Board start;
	private Board finish;

	
	public BoardControl() {
		start=null;
		finish=null;
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

	private Board searchMvmnt(int pos, Board a) {
		if(a.getSize()==pos) {
			return a;
		}else {
			a=a.getNextMvmnt();
			return searchMvmnt(pos, a);
		}
	}
	
	public void pos(int i) {
		tPos(i,start);
	}
	
	private void tPos(int i, Board aoral) {
		if(i>0) {
		aoral.setPlayers("");
		aoral=aoral.getNextMvmnt();
		tPos(i=i-1,aoral);
		}
	}
	
	public void symb(String players) {
		finish.setPlayers(players);
	}
	
	//validation for snakes and ladders
	
	public boolean valLadder(Board ladders) {
		boolean found = false;
		if(ladders.getLadders()==0) {
			found =true;
		}
		return found;
	}
	
	public void addLadder(char ladder, Board pos) {
		pos.setLadders(ladder);
	}
	public Board Ladder(char ladder) {
		return Ladder(ladder, start);
	}
	private Board Ladder(char l, Board a) {
		if(a.getLadders()==l) {
			return a;
		}else {
			a=a.getNextMvmnt();
			return Ladder(l, a);
		}
	}

	public boolean valSnake(Board snakes) {
		boolean found = false;
		if(snakes.getSnakes()==0) {
			found =true;
		}
		return found;
	}

	public void addSnake(char snake, Board pos) {
		pos.setSnakes(snake);
	}

	public Board Snake(char snake) {
		return Snake(snake, start);
	}

	private Board Snake(char s, Board a) {
		if(a.getSnakes()==s) {
			return a;
		}else {
			a=a.getNextMvmnt();
			return Snake(s, a);
		}
	}
}
