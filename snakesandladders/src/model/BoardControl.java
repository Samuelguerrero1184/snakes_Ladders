package model;

public class BoardControl {
	private Board start;
	private Board end;
	private Player one;
	private Player two;
	Player player;
	String leaderBoard;
	String leaderBoard2;
	int x;
	
	public BoardControl() {
		start=null;
		end=null;
		
	}
	//-----------------------------------------------------------------------------------------------------------------
	
	private String show(int col, int row,Board t, int i) {
		
		if(row!=0) {
			x=row%2;
			if(col>=1){
				if (x==0) {
					i++;
					leaderBoard = leaderBoard+"[ "+t.getSize()+
							t.getLadders()+t.getSnakes()+"]";
					
					return show(col=col-1, row, t.getNextMvmnt(), i);
				}
				else {
					i++;
					int n=t.getSize()-col+i;
					leaderBoard=leaderBoard+"[ "+readBoard(n).getSize()
					+readBoard(n).getLadders()+readBoard(n).getSnakes()+"]";
					
					return show(col=col-1, row, t.getNextMvmnt(), i);
				}
			}
			else {
				leaderBoard=leaderBoard+"\n";
				return show(i, row=row-1, t,0);
			}
			
		}
		else {
			return leaderBoard;
		}
	}
	
	private String showColumns(int col, int row, Board t, int i) {
			
		if(row!=0) {
			x=row%2;
			if(col>=1){
				if (x==0) {
					i++;
					leaderBoard2=leaderBoard2+"["+t.getLadders()+t.getSnakes()+t.getPlayers()+"]";
					return showColumns(col=col-1, row, t.getNextMvmnt(), i);
				}else {
					i++;
					int n=t.getSize()-col+i;
					leaderBoard2=leaderBoard2+"["+readBoard(n).getLadders()+readBoard(n).getSnakes()+readBoard(n).getPlayers()+"]";
					return showColumns(col=col-1, row, t.getNextMvmnt(), i);
				}
			}else {
				leaderBoard2=leaderBoard2+"\n";
				return showColumns(i, row=row-1, t,0);
			}
		}else {
			return leaderBoard2;
		}
	}
	
	public String addContent(int row, int col) {
		leaderBoard="";
		return show(col, row, start, 0);
	}
	
	public String showContent(int row, int col) {
		leaderBoard2="";
		return showColumns(col, row, start, 0);
	}
	
	//validation for snakes and ladders
	
	public boolean valLadder(Board ladders) {
		boolean encounter = false;
		if(ladders.getLadders()==0) {
			encounter = true;
		}
		return encounter;
	}
	
	public void addLadder(char ladder, Board pos) {
		pos.setLadders(ladder);
	}
	public Board readLadder(char ladder) {
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
		boolean encounter = false;
		if(snakes.getSnakes()==0) {
			encounter =true;
		}
		return encounter;
	}

	public void addSnake(char snake, Board pos) {
		pos.setSnakes(snake);
	}

	public Board readSnake(char snake) {
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
	
	//validating and controlling the cells

	public void addBoard(int size) {  
		if(start==null) {
			Board bd = new Board(size);
			start = bd;
			end = bd;
		}else {
			Board board = new Board(size);
			end.setNextMvmnt(board);
			end=board;
		}
	}
	
	public Board readBoard(int pos) {  
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
	
	public void symb(String players) {
		end.setPlayers(players);
	}

	public void pos(int i) {
		tPos(i, start);
	}
	
	private void tPos(int i, Board board) {
		if(i>0) {
		board.setPlayers("");
		board=board.getNextMvmnt();
		tPos(i=i-1,board);
		}
	}
	
	//movement
	public boolean movePlayers(char symb, int pos, int moves) {
		return movePlayers(symb, pos, moves, start);
	}
	
	public boolean movePlayers(char symb, int pos, int mvmnt,Board t) {
		
		player = new Player(symb, pos);
		if(t.getPlayers()!="") {
			
			if(player(player,t)) {
				
				t.setPlayers(t.getPlayers().replace(String.valueOf(player.getPlayer()), ""));
				int currentPos = t.getSize();
				int newPos = currentPos+mvmnt;
				if(readBoard(maxCell(newPos))!=start) {
					
					if(valLadder(readBoard(newPos))) {
						
						if(valSnake(readBoard(newPos))) { 
							
							t = readBoard(newPos);
							if(t.getPlayers()!="") {	
								
								t.setPlayers(t.getPlayers().replace(t.getPlayers(), t.getPlayers()+String.valueOf(player.getPlayer())));
								return false;
							}
							else {
								
								t.setPlayers(String.valueOf(player.getPlayer()));
								return false;
							}
						}
						else { 
							
						t = readBoard(newPos);
						moveAnotherSnake(t, player);
						return false;
						}
					}
					else {
						
						t = readBoard(newPos);
						return useLadder(t, player);
					}
				}
				else {
					
					t = start;
					t.setPlayers(String.valueOf(player.getPlayer()));
					return true;
				}	
			}
			else {
				
				t = t.getNextMvmnt();
				return movePlayers(symb, pos, mvmnt ,t);
			}
		}
		else {
			
			t = t.getNextMvmnt();
			return movePlayers(symb, pos, mvmnt,t);
		}
	}
	
	private void moveAnotherSnake(Board snake, Player player){
		char snake_1 = snake.getSnakes();
		Board encounterSnake = readSnake(snake_1);
		if(encounterSnake.getSize()==snake.getSize()){
			
			encounterSnake = Snake(snake_1, encounterSnake.getNextMvmnt());
			if(encounterSnake.getPlayers()!=""){
				
				encounterSnake.setPlayers(encounterSnake.getPlayers().replace(encounterSnake.getPlayers(), encounterSnake.getPlayers()+String.valueOf(player.getPlayer())));
			}
			else{
				
				encounterSnake.setPlayers(String.valueOf(player.getPlayer()));
			}
		}
		else{
			
			if(snake.getPlayers()!=""){
				
				snake.setPlayers(snake.getPlayers().replace(snake.getPlayers(), snake.getPlayers()+String.valueOf(player.getPlayer())));
			}
			else{
				
				snake.setPlayers(String.valueOf(player.getPlayer()));
			}
		}
	}

	private boolean useLadder(Board ladder, Player player) {
		char ladder_1 = ladder.getLadders();
		Board encounterLadder = readLadder(ladder_1);
		if(encounterLadder.getSize()!=ladder.getSize()) {
			if(encounterLadder!=start) {
				if(encounterLadder.getPlayers()!="") {
					encounterLadder.setPlayers(encounterLadder.getPlayers().replace(encounterLadder.getPlayers(), encounterLadder.getPlayers()+String.valueOf(player.getPlayer())));
					return false;
				}else {
					encounterLadder.setPlayers(String.valueOf(player.getPlayer()));
					return false;
				}
			}else {
				encounterLadder.setPlayers(String.valueOf(player.getPlayer()));
				return true;
			}
		}else {
			if(ladder.getPlayers()!="") {
				ladder.setPlayers(ladder.getPlayers().replace(ladder.getPlayers(), ladder.getPlayers()+String.valueOf(player.getPlayer())));
				return false;
			}else {
				ladder.setPlayers(String.valueOf(player.getPlayer()));
				return false;
			}
		}	
	}

	public int maxCell(int move) {
		int maxCell = start.getSize();
		if(move>maxCell) {
			move=maxCell;
		}
		return move;
	}
	
	//----------------------------------------------------
	
	public void addPlayer(char player, int order) {
		if(one==null) {
			Player newPlayer =  new Player(player, order);
			one = newPlayer;
			two = newPlayer;
		}else {
			Player newPlayer =  new Player(player, order);
			two.setNextPlayer(newPlayer);
			two=newPlayer;
		}
	}
	
	public Player player(int pos) {
		return searchPlayer(pos, one);
	}
	
	public Player searchPlayer(int pos, Player t) {
		if(t.getOrder()==pos) {
			return t;
		}else {
			t=t.getNextPlayer();
			return searchPlayer(pos, t);
		}
	}
	
	public boolean findPlayers(Board winner) {
		boolean win= true;
		if(winner.getPlayers()==start.getPlayers()) {
			win=false;
		}
		return win;
	}
	
	public boolean win(boolean f) {
		return f;
	}

	public boolean winner(Player player) {
		boolean encounter=true;
		if(player!=null) {
			if(start.getPlayers()==String.valueOf(player.getPlayer())) {
				
				encounter=false;
			}else {
				player = player.getNextPlayer();
				winner(player);
			}
		}
		return encounter;
	}

	private boolean player(Player player, Board board) {
		boolean encounter = false;
		char players = 0;
		if(board.getPlayers().isEmpty()) {
		}else {
			players = board.getPlayers().charAt(0);
		}
	
		if(players==player.getPlayer()) {
				encounter=true;
				return encounter;
		}else {
			encounter=false;
			return encounter;
		}
	}
	
	public void mvmnts(int turn) {
		Player t = player(turn);
		int mvmnts = t.getPositions()+1;
		t.setPositions(mvmnts);
	}

	public int allMvmnts(Player player) {
		return player.getPositions();
	}
	
	public String winner() {
		return start.getPlayers();
	}
	//---------------------------------------------------------

}
