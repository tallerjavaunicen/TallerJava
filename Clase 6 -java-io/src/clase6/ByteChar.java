package clase6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ByteChar {

	public static void readingAsAByte(String path) throws IOException{
		FileInputStream inputStream = new FileInputStream("hp.txt");
		FileOutputStream outputStream = new FileOutputStream("hp_byteoutput.txt");

		int c;
		while ((c = inputStream.read()) != -1)
			outputStream.write(c);
		
		inputStream.close();
		outputStream.close();
	}

	public static void readingAsAChar(String path) throws IOException{
		FileReader inputStream = new FileReader("hp.txt");
		FileWriter outputStream = new FileWriter("hp_characteroutput.txt");

		int c;
		while ((c = inputStream.read()) != -1)
			outputStream.write(c);

		inputStream.close();
		outputStream.close();
	}

	public static void readingAsLine(String path) throws IOException{
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		inputStream = new BufferedReader(new FileReader("hp.txt"));
		outputStream = new PrintWriter(new FileWriter("hp_linecharacteroutput.txt"));
		String l;
		while ((l = inputStream.readLine()) != null)
			outputStream.println(l);
		
		inputStream.close();
		outputStream.close();
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

}
