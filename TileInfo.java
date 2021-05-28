package main;


public class TileInfo {
	String name;
	int tNum;
	int x;
	int y;
	int toll;
	private Player owner = null;
	
	public void buy(Player owner) {
		this.setOwner(owner);
		owner.setMoney(owner.minusMoney(toll));
	}
	
	
	TileInfo(String name, int tNum, int x, int y, int toll){
		this.name = name;
		this.tNum = tNum;
		this.x = x;
		this.y = y;
		this.toll = toll;
	}


	public Player getOwner() {
		return owner;
	}


	public void setOwner(Player owner) {
		this.owner = owner;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int gettNum() {
		return tNum;
	}


	public void settNum(int tNum) {
		this.tNum = tNum;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getToll() {
		return toll;
	}


	public void setToll(int toll) {
		this.toll = toll;
	}
	
	
}

