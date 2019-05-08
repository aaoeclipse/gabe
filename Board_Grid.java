import java.util.Random;

public class Board_Grid {
	/*
	 * The board is going to be a matrix composed of integers if a cell in board is:
	 * 0: then it's empty 1: there's a vessel 2: it has been shot Ships: The game
	 * will consists of 5 ships: Carrier (occupies 5 spaces), Battleship (4),
	 * Cruiser (3), Submarine (3), and Destroyer (2). the ships will be places
	 * randomly on the screen
	 */

	public int[][] board; // inorder to be accessed by all classes in the package, will help us determine ship position
	public int dimension;
	private Random rand;
	public int Shipcount;

	public Board_Grid() { // constructor
		// We use random to create a matrix with dimension between 8 to 24
		rand = new Random();
		this.dimension = rand.nextInt(16) + 8;

		// we create a board with those dimensions and then we populate it
		board = new int[dimension][dimension];
		Shipcount = rand.nextInt(10);

	}

	public Board_Grid(int dimension, int Shipcount) {
		this.dimension = dimension;
		this.Shipcount = Shipcount;

		board = new int[dimension][dimension];

	}

	public void populate(){
		rand = new Random();
		int randX = 0;
		int randY = 0;
		int shipDirection = 0; //1 is vertical and 0 is horizontal
		int shipSize = 4;

		boolean Overlaps;
		for (int i = 0; i < Shipcount; i++){
			do{
				Overlaps = false;
				shipDirection = rand.nextInt(2); //we set direction for the ship bet 0-1
				// check that ship doesnt't get out of bounds
				if (shipDirection == 0){ //horizontal
					randX = rand.nextInt(dimension - shipSize); //where do we start filling up the values?
					randY = rand.nextInt(dimension); //y coordinate
					for (int y = 0; y < shipSize; y++){
						if (board[randX+y][randY] == 1){
							Overlaps = true;
							break; //breaks the for loop, in the case the boats overlap
						}
					}
					if (!Overlaps) { //if does not overlap
						for (int z = 0; z < shipSize; z++) {

							board[randX+z][randY] = 1;

						}}
					} else {
						randY = rand.nextInt(dimension - shipSize);
						randX = rand.nextInt(dimension);
						for (int j = 0; j < shipSize; j++){
							if (board[randX][randY+j] == 1){
								Overlaps = true;
								break;
							}
							board[randX][randY+j] = 1;
						}
					}
				} while(Overlaps); //while overlaps is true
			}
		}
	public String toString() {
		String matrix = "";
        for(int y = 0; y < dimension; y++){
            for(int x = 0; x < dimension; x++){
               matrix += board[y][x];
            }
        }
        return matrix;
	}
	}