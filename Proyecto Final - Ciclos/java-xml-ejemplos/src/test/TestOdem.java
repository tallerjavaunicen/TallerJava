package test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.org.apache.xerces.internal.impl.Constants;

public class TestOdem {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        File inputFile = new File("apache-camel-2.0.0-src.odem");
        Document doc = dBuilder.parse(inputFile);		
        System.out.println(doc.getDocumentElement().getNodeName());
        
        System.out.println(doc.getElementsByTagName("namespace"));
		
		
	}
	
}
