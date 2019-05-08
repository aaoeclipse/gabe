/**
 * 
 */
import java.io.IOException;
import java.net.*;
import java.io.*;

/**
 * @author gabmo
 *
 */
public class BServer { //server receives the information
	//Socket Variables
	ServerSocket GameServer;
	Socket CaptainHook;
	//Thread variable declaration
	Thread Dream; //Dream within a dream 
	LoginThread Login; // Login Thread Declaration 
	
	
	public BServer() {
		
		try {
			GameServer = new ServerSocket(8767);
			while(true){
                System.out.println("[*] Waiting for players...");
                CaptainHook = GameServer.accept();
                Login = new LoginThread(CaptainHook); //constructor used to pass the value of CHook into the class
                Dream = new Thread(Login); // Falls asleep
                Dream.start(); //Start dreaming 
                
        // All this was moved in order to create the threads
                // System.out.println("Connection Successful!");
        // DataInputStream Jab = new DataInputStream(CaptainHook.getInputStream()); 
               // DataOutputStream HookAttack = new DataOutputStream(CaptainHook.getOutputStream());
               // HookAttack.writeUTF("Good Jab Peter, but the real match has not begun."); //writeutf is a writing protocol, to interpret input
                //System.out.println(Jab.readUTF());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //server doesn't need IP - because it is waiting for a connection
		
		
	}
	public static void main(String [] args){
		//remember this program is running on 2 different computers one on the server and one on the player pc
		BServer Hal9000 = new BServer();
		//I'm afraid I can't do that
		//Sometimes the only winning move is to not play
		
				
	}

}
