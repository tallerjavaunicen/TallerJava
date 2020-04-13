package clase6;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WritingMultipleTimesSameObject {

	public static void saveObjects(List<String> toSave,String path,int times) throws IOException{
		ObjectOutputStream seri = new ObjectOutputStream(new FileOutputStream(path));
		for(int i=0;i<times;i++)
			seri.writeObject(toSave);
		seri.close();
	}
	
	public static void saveObjectsAlways(List<String> toSave,String path,int times) throws IOException{
		ObjectOutputStream seri = new ObjectOutputStream(new FileOutputStream(path));
		for(int i=0;i<times;i++){
			seri.writeObject(toSave);
			seri.reset();
		}
		seri.close();
	}
	
	public static List<List<String>> readObjects(String path) throws ClassNotFoundException, IOException{
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
		List<List<String>> objects = new ArrayList<List<String>>();
		boolean hasNext = true;
		while(hasNext){
			try{
				objects.add((List<String>) in.readObject());
			}
			catch(EOFException e){
				hasNext = false;
			}

		}
		in.close();
		return objects;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Hello World!");
		
		List<String> toSave = new ArrayList<String>();
		toSave.add("a");
		toSave.add("b");
		toSave.add("c");
		toSave.add("d");
		
		saveObjects(toSave, "saveObjects.txt", 5);
		List<List<String>> objects = readObjects("saveObjects.txt");
		System.out.println(objects);
		objects.get(0).clear();
		System.out.println(objects);
		
		saveObjectsAlways(toSave, "saveObjectsAlways.txt", 5);
		List<List<String>> objectsAlways = readObjects("saveObjectsAlways.txt");
		System.out.println(objectsAlways);
		objectsAlways.get(0).clear();
		System.out.println(objectsAlways);
		
		
	}
}
