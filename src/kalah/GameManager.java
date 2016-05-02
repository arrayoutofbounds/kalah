package kalah;

public class GameManager {

	private Player one;
	private Player two;
	private PrintingManager print;
	private Player currentPlayer;
	private int input;
	private boolean initialDone = false;

	public GameManager(Player one,Player two,PrintingManager p,Player current) {
		this.one = one;
		this.two = two;
		print = p;
		currentPlayer = current;
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
				print.gameOver(one,two);
			}else {
				print.gameOver(two,one);
			}

		}else {
			// they did not press quit 
			
			// 
		}

	}


}
