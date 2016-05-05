package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		
		int numberOfHouses = 6;
		int initialSeeds = 4;
		
		Player one = new Player(numberOfHouses,initialSeeds);
		one.setId(1);
		Player two = new Player(numberOfHouses,initialSeeds);
		two.setId(2);
		
		one.setOpposingPlayer(two);
		two.setOpposingPlayer(one);
		
		PrintingManager p = new PrintingManager(io,one,two);
		
		GameManager gm = new GameManager(one,two,p);
		gm.setCurrentPlayer(one);
		gm.run();
	}
}
