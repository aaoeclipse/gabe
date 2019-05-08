
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
	String stringUser;

	public Player() {
		try { // try catch is just used to manage errors
			PlayerInput = new Scanner(System.in);
			PlayerSocket = new Socket("127.0.0.1", 8767); // initializing the socket thats the home ip address
			DataInputStream HookAttack = new DataInputStream(PlayerSocket.getInputStream()); // read
			DataOutputStream Jab = new DataOutputStream(PlayerSocket.getOutputStream());
			
			System.out.println(HookAttack.readUTF()); //Login
			Jab.writeUTF(PlayerInput.nextLine());
			
			System.out.println(HookAttack.readUTF()); // Password
			Jab.writeUTF(PlayerInput.nextLine()); 
			dimensions = HookAttack.readInt(); // Board Dimensions
			System.out.println(HookAttack.readUTF()); //Welcome user
			ServerMessage = HookAttack.readUTF();
			System.out.println("Dimensions for the boards are: " + dimensions + "  by  " + dimensions);
			
			while (true) {
                System.out.println("Command List:");
                System.out.println("-b to print enemy board");
                System.out.println("-c to print your board");
                System.out.println("-a to commence attack");
                stringUser = PlayerInput.nextLine();
                switch (stringUser) { // # 3 switch
                    case ("-b"): //command to show the enemy board, just to check if the hit or miss are working
                        Jab.writeUTF("ServerBoard"); // Server expects serverboard
                    //Based on the switch we need to change a few things in dreamgame thread
                        printBoard(HookAttack.readUTF());
                        break;

                    case ("-c"):
                        Jab.writeUTF("PlayerBoard"); //server expects PlayerBoard
                        printBoard(HookAttack.readUTF());
                        break;

                    case ("-a"):
                        Jab.writeUTF("Attack"); //server expect attack
                    System.out.println("Dimensions are:" + dimensions + "by " + dimensions);
                        System.out.println("Input X Commander: ");
                        Jab.writeInt(Integer.parseInt(PlayerInput.nextLine())); //server expects an int
                        System.out.println("Input Y Commander: ");
                        Jab.writeInt(Integer.parseInt(PlayerInput.nextLine())); // serber expects an int
                        System.out.println(HookAttack.readUTF());
                        break;

                    default:
                        break;
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

	public void printBoard(String board){ //function to print the board.
        for(int i = 0; i < board.length(); i++){
            if (i % dimensions == 0) //if no remainder break
                System.out.println();
            System.out.print(board.charAt(i));
        }
        System.out.println(); //new line after the board is printed
        
    }
	
	public static void main(String... args) {

		Player Peter = new Player(); // creates peter runs everything

	}
}
