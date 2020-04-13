package clase6;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompression {
	final static int BUFFER = 2048;

	public static void descompressWithZip(String path){
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(path);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " +entry);
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) 
					dest.write(data, 0, count);
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void descompressWithZipFile(String path){
		try {
			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(path);
			Enumeration<? extends ZipEntry> e = zipfile.entries();
			while(e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				System.out.println("Extracting: " +entry);
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) 
					dest.write(data, 0, count);
				dest.flush();
				dest.close();
				is.close();
				zipfile.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void compressWithZip(String path) throws IOException{
		BufferedInputStream origin = null;
		FileOutputStream dest = new FileOutputStream(path);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
		//out.setMethod(ZipOutputStream.DEFLATED);
		byte data[] = new byte[BUFFER];
		// get a list of files from current directory
		File f = new File(System.getProperty("user.dir"));
		String files[] = f.list();

		for (int i=0; i<files.length; i++) {
			System.out.println("Adding: "+files[i]);
			FileInputStream fi = new FileInputStream(files[i]);
			origin = new BufferedInputStream(fi, BUFFER);
			ZipEntry entry = new ZipEntry(files[i]);
			out.putNextEntry(entry);
			int count;
			while((count = origin.read(data, 0, BUFFER)) != -1) 
				out.write(data, 0, count);
			origin.close();
		}
		out.close();
}

public static void compressWithChecksumZip(String path) throws IOException{
	BufferedInputStream origin = null;
	FileOutputStream dest = new FileOutputStream(path);
	CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
	ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
	//out.setMethod(ZipOutputStream.DEFLATED);
	byte data[] = new byte[BUFFER];
	// get a list of files from current directory
	File f = new File(path).getParentFile();
	String files[] = f.list();

	for (int i=0; i<files.length; i++) {
		System.out.println("Adding: "+files[i]);
		FileInputStream fi = new FileInputStream(files[i]);
		origin = new BufferedInputStream(fi, BUFFER);
		ZipEntry entry = new ZipEntry(files[i]);
		out.putNextEntry(entry);
		int count;
		while((count = origin.read(data, 0, BUFFER)) != -1) 
			out.write(data, 0, count);
		origin.close();
	}
	out.close();
	System.out.println("checksum: "+checksum.getChecksum().getValue());

}

public static void descompressWithChecksumZip(String path){
	try {
		final int BUFFER = 2048;
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(path);
		CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
		ZipEntry entry;
		while((entry = zis.getNextEntry()) != null) {
			System.out.println("Extracting: " +entry);
			int count;
			byte data[] = new byte[BUFFER];
			// write the files to the disk
			FileOutputStream fos = new FileOutputStream(entry.getName());
			dest = new BufferedOutputStream(fos, BUFFER);
			while ((count = zis.read(data, 0, BUFFER)) != -1)
				dest.write(data, 0, count);
			dest.flush();
			dest.close();
		}
		zis.close();
		System.out.println("Checksum: "+checksum.getChecksum().getValue());
	} catch(Exception e) {
		e.printStackTrace();
	}
}



public static void main(String[] args) {
	System.out.println("Hello World!");
}

}
