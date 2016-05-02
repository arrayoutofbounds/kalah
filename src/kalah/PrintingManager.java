package kalah;

import com.qualitascorpus.testsupport.IO;

public class PrintingManager {

	IO io;

	public PrintingManager(IO io) {
		this.io = io;
	}
	
	public int printInitial() {
		int in = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");
		return in;
	}

	public void print(Player one, Player two,boolean gameOver) {
		// so sorted by id in arguments
		int[] one_house = one.getHouses();
		int[] two_house = two.getHouses();
		if(gameOver) {
			io.println("Game over");
		}
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[" + convertToString(two_house[5])+ "] | 5[" + convertToString(two_house[4])+ "] | 4[" + convertToString(two_house[3])+ "] | 3[" + convertToString(two_house[2])+ "] | 2[" + convertToString(two_house[1])+ "] | 1[" + convertToString(two_house[0])+ "] | " + convertToString(one.getStore()) + " |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("| " + convertToString(two.getStore()) + " | 1[" + convertToString(one_house[0])+ "] | 2[" +convertToString(one_house[1])+ "] | 3[" + convertToString(one_house[2])+ "] | 4[" + convertToString(one_house[3])+ "] | 5[" + convertToString(one_house[4])+ "] | 6[" + convertToString(one_house[5])+ "] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}
	
	public void noMovesLeft(Player one,Player two) {
		print(one, two, true);
		int score1 = 0;
		int score2 = 0;
		
		for(int i =0;i<one.getHouses().length;i++) {
			score1 = score1 + one.getHouses()[i];
		}
		score1 = score1 + one.store;
		
		for(int j =0;j<two.getHouses().length;j++) {
			score2 = score2 + two.getHouses()[j];
		}
		score2 = score2 + two.store;
		
		io.println("\tplayer 1:" + score1);
		io.println("\tplayer 2:" + score2);
		
		if(score1>score2) {
			io.println("Player 1 wins!");
		}
		
		if(score1<score2) {
			io.println("Player 2 wins!");
		}
		
		if(score1==score2) {
			io.println("A tie!");
		}
		
		
		
	}
	
	public void printHouseEmpty() {
		io.println("House is empty. Move again.");
	}

	private String convertToString(int h) {
		String toPrint = "";
		if((h + "").length()==2) {
			toPrint = h + "";
		}else {
			toPrint = " " + h;
		}
		return toPrint;
	}

	public int getInput(Player p) {
		int in;
		if(p.getId() == 1) {
			in = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");
		}else {
			in = io.readInteger("Player P2's turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");
		}
		return in;
	}

}
