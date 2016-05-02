package kalah;

public class GameManager {

	private Player one;
	private Player two;
	private PrintingManager print;
	private Player currentPlayer;
	private int input;
	private int index; // derived from input
	private boolean initialDone = false;

	private int increment;
	private int opposingIncrement;
	private boolean oppositionHousesRemaining;

	private int seedsRemaining;

	public GameManager(Player one,Player two,PrintingManager p) {
		this.one = one;
		this.two = two;
		print = p;
	}

	public void setCurrentPlayer(Player p) {
		currentPlayer = p;
	}

	public void run() {
		if(initialDone == false) {
			input = print.printInitial();
			initialDone=true;
		}else {
			input = print.getInput(currentPlayer);
		}
		// input is already in correct format
		if(input==-1) {
			int id_1 = one.getId();
			int id_2 = two.getId();
			if(id_1 == 1) {
				print.print(one,two,true);
			}else {
				print.print(two,one,true);
			}

		}else {
			// they did not press quit 

			// so a house has been picked

			// break the board into 4 sections
			// 1. current players side of the board
			// 2. current players store
			// 3. opp players side of the board
			// 4. back around to this player 

			// step 1
			houseSelected();

		}

	}

	private void houseSelected() {
		// seeds that are in the chosen house of the current player
		index = input-1;
		seedsRemaining = currentPlayer.getHouses()[index];
		increment = 1;
		while((seedsRemaining>0)&&((index+increment)<=5)) {
			placeSeedsOnCurrentPlayerSide((index+increment));
		}
		System.out.println(seedsRemaining);
		// if there were remaining then they were put in the store after reaching the end
		if(seedsRemaining>0) {
			putInStoreOfCurrentPlayer();

		}
		
		// if still remaining then put on the opposite players side
		opposingIncrement = 0;
		oppositionHousesRemaining = true;
		while((seedsRemaining>0) && (oppositionHousesRemaining == true) && (opposingIncrement<6)) {
			putSeedsInOppositionHouses();
		}

	}

	private void printAndRunAgain() {
		int id_1 = one.getId();
		int id_2 = two.getId();
		if(id_1 == 1) {
			print.print(one,two,false);
		}else {
			print.print(two,one,false);
		}

		run();
	}

	private void putSeedsInOppositionHouses() {

		if(opposingIncrement>5) {
			oppositionHousesRemaining = false;
			opposingIncrement = 0;
		}else {

			//decrement the seeds
			seedsRemaining--;

			if(seedsRemaining==0) {
				// minus from original house
				currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 

				// add to opposite players house
				currentPlayer.getOpposingPlayer().getHouses()[opposingIncrement] = currentPlayer.getOpposingPlayer().getHouses()[opposingIncrement] + 1;

				opposingIncrement = 0;
				oppositionHousesRemaining = true;

				// ending on opp player means its the other players turn
				swapCurrentPlayer();
				printAndRunAgain();
			}else {
				// minus from original house
				currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 

				// add to opposite players house
				currentPlayer.getOpposingPlayer().getHouses()[opposingIncrement] = currentPlayer.getOpposingPlayer().getHouses()[opposingIncrement] + 1;

				// increment to next house of opposition
				opposingIncrement++;

				oppositionHousesRemaining = true;
			}




		}
	}

	private void putInStoreOfCurrentPlayer() {
		seedsRemaining--;

		if(seedsRemaining==0) {
			currentPlayer.store = currentPlayer.store + 1;
			// take away one from chosen house
			currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 
			// no swapping players as getting it in the store last
			printAndRunAgain();
		}else {
			// add to store
			currentPlayer.store = currentPlayer.store + 1;
			// take away one from chosen house
			currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 
			// not 0 yet so more seeds hence no print
		}



	}

	private void placeSeedsOnCurrentPlayerSide(int house) {
		seedsRemaining--;

		if(seedsRemaining==0) {

			int houseLastPutIn = currentPlayer.getHouses()[house];

			if(houseLastPutIn != 0) {

				currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 
				currentPlayer.getHouses()[house]++; // the house gets add a seed
				increment = 1;
				swapCurrentPlayer(); // as this player no longer has the turn
				printAndRunAgain();
			}

			if(houseLastPutIn == 0) {

				// if opp house has 0 seeds then no capture
				// else capture
				int oppHouseIndex = getOppHouseIndex(house);
				int oppHouseSeeds = currentPlayer.getOpposingPlayer().getHouses()[oppHouseIndex];

				if(oppHouseSeeds ==0) {
					//NO CAPTURE
					currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 
					currentPlayer.getHouses()[house]++; // the house gets add a seed
					increment = 1;
					swapCurrentPlayer(); // as this player no longer has the turn
					printAndRunAgain();
				}else {
					// CAPTURE

					// remove all seeds from opp house
					currentPlayer.getOpposingPlayer().getHouses()[oppHouseIndex] = 0;

					// original house down by one
					currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 

					//no need to add to the current index as it goes to the store directly
					currentPlayer.store = currentPlayer.store + oppHouseSeeds + 1;

					// set increment back to 1
					increment = 1;

					swapCurrentPlayer(); // as this player no longer has the turn
					printAndRunAgain();
				}

			}

		}else {
			// the house that had the seeds has one taken away from it
			currentPlayer.getHouses()[index] = currentPlayer.getHouses()[index] -1; 
			currentPlayer.getHouses()[house]++; // the house gets add a seed
			increment++;
		}

	}

	private int getOppHouseIndex(int currentHouseIndex) {

		if(currentHouseIndex == 0) {
			return 5;
		}else if(currentHouseIndex == 1) {
			return 4;
		}else if(currentHouseIndex == 2) {
			return 3;
		}else if(currentHouseIndex == 3) {
			return 2;
		}else if(currentHouseIndex == 4) {
			return 1;
		}else if(currentHouseIndex == 5) {
			return 0;
		}else {
			System.out.println("Sorry you cannot have a index beyong 0 and 5");
			return -1;
		}

	}

	private void swapCurrentPlayer() {
		// p1 has id 1
		// p2 has id 2

		if(currentPlayer.getId() == 1) {
			currentPlayer = two;
		}else {
			currentPlayer = one;
		}
	}


}
