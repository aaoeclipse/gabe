
//game thread
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class GameThread implements Runnable {
	Socket CaptainHook;
	String Username;


	// # 1 server Board 
	public Board_Grid GameBoard, ServerBoard; //creating 2 boards one for the player and the enemy
	boolean GameOver;

	public GameThread(Socket CaptainHook, String Username) { //constructor for class
		this.CaptainHook = CaptainHook;
		this.Username = Username;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// check if there is another player connected
		GameBoard = new Board_Grid();
		GameBoard.populate();
		ServerBoard = new Board_Grid(GameBoard.dimension, GameBoard.Shipcount);
		ServerBoard.populate();

		// anything inside this function will run when we tell the thread to start.
		// TODO Auto-generated method stub

		DataInputStream Jab; // declaration of buffer
		DataOutputStream HookAttack;
		String userInput; //initializing


		try {
			Jab = new DataInputStream(CaptainHook.getInputStream()); // initialized
			HookAttack = new DataOutputStream(CaptainHook.getOutputStream()); // initialized
			HookAttack.writeInt(GameBoard.dimension); // gameboard.dimension 
			int CoordinateX;
			int CoordinateY;
			System.out.println("Game Initiated.");
			HookAttack.writeUTF("Welcome " + Username);
			HookAttack.writeUTF(GameBoard.toString()); //print out the board
			while (!GameOver) { // will have to change this in the future, to send to a new thread lobby
				userInput = Jab.readUTF();
				switch (userInput) {
				case ("ServerBoard"):
					HookAttack.writeUTF(ServerBoard.toString()); //print out the board

				break;
				case ("PlayerBoard"):
					HookAttack.writeUTF(GameBoard.toString()); //print out the board

				break;
				case ("Attack"):
				CoordinateX = Jab.readInt();
                CoordinateY = Jab.readInt();
                if (ServerBoard.board[CoordinateY][CoordinateX] == 1){ //1 represents a hit
                    HookAttack.writeUTF("Hit!");
                    ServerBoard.board[CoordinateY][CoordinateX] = 2; //2 is new change to board array

                }
                else if (ServerBoard.board[CoordinateY][CoordinateX] == 2){
                    HookAttack.writeUTF("You already hit this one!");

                } else {
                    HookAttack.writeUTF("Miss!");

                }
				break;
				default:
					break;
				}
				// LoginCheck Should occur here create new class

				// TODO Check for username login inside a text file
				// Make sure that sync works

			}
		} catch (IOException e) {
			System.err.println("Player Disconnected"); // player program terminated before the loop
			// TODO Auto-generated catch block
		}

	}

}
