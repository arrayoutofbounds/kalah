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
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		
		int numberOfHouses = 6;
		int initialSeeds = 4;
		
		Player one = new Player(numberOfHouses,initialSeeds);
		one.setId(1);
		Player two = new Player(numberOfHouses,initialSeeds);
		two.setId(2);
		
		one.setOpposingPlayer(two);
		two.setOpposingPlayer(one);
		
		PrintingManager p = new PrintingManager(io);
		
		GameManager gm = new GameManager(one,two,p,one);
		gm.run();
	}
}
