package kalah;

public class Player {
	
	private int initialSeeds;
	private int numberOfHouses;
	
	private int[] houses;
	
	private int id;
	
	private Player opposing;
	
	private int store;
	
	
	public Player(int numberOfHouses,int initialSeeds) {
		this.initialSeeds = initialSeeds;
		this.numberOfHouses = numberOfHouses;
		store = 0;
		initialise();
	}
	
	private void initialise() {
		houses = new int[numberOfHouses];
		for(int i = 0; i < houses.length; i++) {
			houses[i] = initialSeeds;
		}
	}
	
	public int[] getHouses() {
		return this.houses;
	}
	
	public void setOpposingPlayer(kalah.Player p) {
		opposing = p;
	}
	
	public Player getOpposingPlayer() {
		return this.opposing;
	}
	
	public int getStore() {
		return this.store;
	}
	
	public void setStore(int s) {
		store = s;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int a) {
		this.id = a;
	}

}
