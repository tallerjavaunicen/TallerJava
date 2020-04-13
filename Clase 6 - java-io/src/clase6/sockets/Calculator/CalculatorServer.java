package clase6.sockets.Calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

	public static void main(String args[]) throws IOException {
		// Register service on port 1254
		if(args.length == 0){
			System.out.println("0 --> Numero de puerto");
			System.exit(0);
		}
		System.out.println("Listening on port "+args[0]);
		
		ServerSocket s = new ServerSocket(Integer.parseInt(args[0]));
		Socket s1=s.accept(); // Wait and accept a connection
		// Get a communication stream associated with the socket
		System.out.println("Accepting client!");
		InputStream s1In = s1.getInputStream();
		DataInputStream dis = new DataInputStream(s1In);
		int num1 = (dis.readInt());
		int num2 = (dis.readInt());
		String operation = (dis.readUTF());
		//String st = new String (dis.readUTF());
		int ans = 0;
		if (operation.equals("add") || operation.equals("+")){
			ans = num1 + num2;
		}
		else if (operation.equals("sub") || operation.equals("-")){
			ans = num1 - num2;
		}
		System.out.println(ans);
		OutputStream s1out = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream (s1out);
		System.out.println("Sending answer to client...");
		dos.writeUTF("Send Client "  + " " + ans); // Send a string to client
		// Close the connection, but not the server socket
		dos.close();
		s1out.close();
		s1.close();
	}


}
