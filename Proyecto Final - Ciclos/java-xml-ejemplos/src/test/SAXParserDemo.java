package test;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo {

   public static void main(String[] args) {

      try {
         
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         
         File inputFile = new File("Student.xml");
         UserHandler userhandler = new UserHandler();
         saxParser.parse(inputFile, userhandler);    
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}