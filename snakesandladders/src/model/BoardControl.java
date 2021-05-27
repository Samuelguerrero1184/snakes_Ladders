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
	
	
	//Using and controlling the cells

	
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
	
	
}
