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

	public void gameOver(Player one, Player two) {
		// so sorted by id in arguments
		int[] one_house = one.getHouses();
		int[] two_house = two.getHouses();
		
		io.println("Game over");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[" + convertToString(two_house[5])+ "] | 5[" + convertToString(two_house[4])+ "] | 4[" + convertToString(two_house[3])+ "] | 3[" + convertToString(two_house[2])+ "] | 2[" + convertToString(two_house[1])+ "] | 1[" + convertToString(two_house[0])+ "] | " + convertToString(one.getStore()) + " |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("| " + convertToString(two.getStore()) + " | 1[" + convertToString(one_house[0])+ "] | 2[" +convertToString(one_house[1])+ "] | 3[" + convertToString(one_house[2])+ "] | 4[" + convertToString(one_house[3])+ "] | 5[" + convertToString(one_house[4])+ "] | 6[" + convertToString(one_house[5])+ "] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
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
