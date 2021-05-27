package model;



public class Player {

	private String name;
	private int order;
	private Tile position;
	
	


	public Player(String name, int order) {
	
		this.order = order;
		this.name = name;
	}	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Tile getPosition() {
		return position;
	}
	
	public void setPosition(Tile  position) {
		this.position = position;
	}
	
	
	
}
