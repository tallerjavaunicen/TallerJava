package clase6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryingException {

	public static void main(String[] args) throws IOException{
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in = new FileInputStream("file.txt");
			out = new FileOutputStream("file_copy.txt");
			int c;
			while((c = in.read()) != -1){
				out.write(c);
			}
		}catch (IOException e){
			System.out.println("IO exception : " + e.getMessage());
		}
		finally{
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			}
	}

}
