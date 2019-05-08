
//game thread
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class GameThread implements Runnable {
	Socket CaptainHook;
	String Username;
	
	
	public Board_Grid GameBoard;
	boolean GameOver;

	public GameThread(Socket CaptainHook, String Username) {
		this.CaptainHook = CaptainHook;
		this.Username = Username;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// check if there is another player connected
		GameBoard = new Board_Grid();
		GameBoard.populate();
		// anything inside this function will run when we tell the thread to start.
		// TODO Auto-generated method stub

		DataInputStream Jab; // declaration of buffer
		DataOutputStream HookAttack;
		
		try {
			Jab = new DataInputStream(CaptainHook.getInputStream()); // initialized
			HookAttack = new DataOutputStream(CaptainHook.getOutputStream()); // initialized
			int CoordinateX;
			int CoordinateY;
			System.out.println("Game Initiated.");
			HookAttack.writeUTF("Welcome " + Username);
			HookAttack.writeUTF(GameBoard.toString()); //print out the board
			while (!GameOver) { // will have to change this in the future, to send to a new thread lobby
				
				HookAttack.writeUTF("Coordinate X:"); // writeutf is a writing																		// protocol, to interpret input
				CoordinateX = Jab.readInt();
				HookAttack.writeUTF("Coordinate Y:");
				CoordinateY = Jab.readInt();
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
