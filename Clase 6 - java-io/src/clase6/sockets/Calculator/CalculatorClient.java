package clase6.sockets.Calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {

	public static void main(String args[]) throws IOException {
		  // Open your connection to a server, at port 1254
		
		if(args.length == 0){
			System.out.println("0 --> Numero de puerto");
			System.exit(0);
		}
		
        Socket s1 = new Socket("localhost",Integer.parseInt(args[0]));
        // Get an input file handle from the socket and read the input
        Scanner scanln = new Scanner(System.in);
         
        System.out.println("Enter number1: ");
        int num1 = scanln.nextInt();
         
        System.out.println("Enter number2: ");
        int num2 = scanln.nextInt();
         
        System.out.println("Operation: ");
        String operation = scanln.next();
         
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);
        dos.writeInt(num1); //send to server6
        dos.writeInt(num2); //send to server6
        dos.writeUTF(operation);
        //dos.writeInt(operation);  //send to server6
         
         
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = new String (dis.readUTF());
         
        System.out.println("" + st);
        // When done, just close the connection and exit
        dis.close();
        s1In.close();
        s1.close();
		}
	
}
