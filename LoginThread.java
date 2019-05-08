/**
 * 
 */
import java.net.*;
import java.io.*;

/**
 * @author gabmo
 *
 */
public class LoginThread implements Runnable { //runnable is the default java implementation for threads
	Socket CaptainHook;
	Thread Game;
	GameThread Gamethread;
	public LoginThread (Socket CaptainHook) { //constructor
		this.CaptainHook = CaptainHook; //this.captainhook is the socket here, we set it to be equal to the one being received
		
	}
	
	@Override
	public void run() { //anything inside this function will run when we tell the thread to start.
						// TODO Auto-generated method stub
		
		DataInputStream Jab; //declaration of buffer
		DataOutputStream HookAttack;
		try {
			Jab = new DataInputStream(CaptainHook.getInputStream()); //initialized
			HookAttack = new DataOutputStream(CaptainHook.getOutputStream()); //initialized
			String Username; 
			String Password;
			System.out.println("Connection Successful!");
				while(true){ //will have to change this in the future, to send to a new thread lobby
		            HookAttack.writeUTF("Please input your login and password. \n Login:"); //writeutf is a writing protocol, to interpret input
		            Username = Jab.readUTF();
		            HookAttack.writeUTF("Password:"); 
		            Password = Jab.readUTF();
		            //LoginCheck Should occur here vamos a create new class
		            System.out.println("Username:" + Username);
		            System.out.println("Password:" + Password);
		            //TODO Check for username login inside a text file
		            // Make sure that sync works 
		            Gamethread = new GameThread(CaptainHook, Username); //constructor
		            Game = new Thread(Gamethread);
		            Game.start();
		            break;
		            
				}
		} catch (IOException e) {
			System.err.println("Player Disconnected"); //player program terminated before the loop
			// TODO Auto-generated catch block
		} 
		
	} 
	
}
