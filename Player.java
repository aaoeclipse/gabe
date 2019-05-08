
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Player { // the goal of this class is to create players, and give them abilities to
						// strike at the coordinates!
	Socket PlayerSocket;
	Scanner PlayerInput;
	String ServerMessage;
	int PlayerBoard[][];
	int dimensions;

	public Player() {
		try { // try catch is just used to manage errors
			PlayerInput = new Scanner(System.in);
			PlayerSocket = new Socket("ec2-3-14-146-6.us-east-2.compute.amazonaws.com", 8767); // initializing the socket thats the home ip address
			DataInputStream HookAttack = new DataInputStream(PlayerSocket.getInputStream()); // read
			DataOutputStream Jab = new DataOutputStream(PlayerSocket.getOutputStream());
			while (true) {
				ServerMessage = HookAttack.readUTF();
				switch (ServerMessage) {
				case ("Welcome"):
					System.out.println(HookAttack.readUTF());
					dimensions = HookAttack.readInt();
					PlayerBoard = new int[dimensions][dimensions];
					ServerMessage = HookAttack.readUTF();
					break;
				default:
					System.out.println(ServerMessage);
					Jab.writeUTF(PlayerInput.nextLine());
				}

			}
//DONDE NOS QUEDAMOS: TODO:
			//PRINT BOARD FOR PLAYER IN MATRIX FORMAT
			//INPUT RECORD HITS OR MISSES IN THE GAME
			// GAME OVER WINNER OR LOSER
	
			// there's no place like 127.0.0.1
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // needs ip and port

	}

	public static void main(String... args) {

		Player Peter = new Player(); // creates peter runs everything

	}
}
