/**File Name:MM7ConfigManager.java
 * Company:  �й��ƶ����Ź�˾
 * Date  :   2004-1-3
 * */

package com.cmcc.mm7.vasp.conf;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class MM7ConfigManager
{
  public HashMap hashmap = new HashMap();
  public MM7ConfigManager() {
  }

  /**read XML file through the parameter and get value*/
  public void load(String configFileName)
  {
    hashmap.clear();
    try{
/*      String strFileName = System.getProperty("user.dir");
     // System.out.println("strFileName="+strFileName);
      String fileNameArray[] = configFileName.split("/");
      if(fileNameArray !=null)
        //System.out.println("fileNameArray="+fileNameArray.length);
      if(fileNameArray != null)
      {
        if (fileNameArray[0].indexOf(":") > 0) {
          strFileName = configFileName;
        }
        else {
          /*if (fileNameArray[0].equals("..")) {
            int index = strFileName.lastIndexOf("/");
            System.out.println("index="+index);
            strFileName = strFileName.substring(0, index);
          }*/
/*          for (int i = 1; i < fileNameArray.length; i++) {
            strFileName = strFileName + File.separator + fileNameArray[i];
          }
        }
      }*/
      //hashmap = readXMLFile(strFileName);
      hashmap = readXMLFile(configFileName);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**save config file*/
  public void save(String configFileName)
  {
    String indent = "	";
    boolean newLines = false;
    String Charset = "gb2312";
    Document doc = null;
    SAXBuilder sb = new SAXBuilder();
    String inFile = System.getProperty("user.dir");
    String fileNameArray[] = configFileName.split("/");
    if(fileNameArray != null)
    {
      if(fileNameArray[0].indexOf(":")>0){
        inFile = configFileName;
      }
      else{
        if (fileNameArray[0].equals("..")) {
          int index = inFile.lastIndexOf("/");
          inFile = inFile.substring(0, index);
        }
        for (int i = 1; i < fileNameArray.length; i++) {
          inFile = inFile + File.separator + fileNameArray[i];
        }
      }
    }
    OutputStream out = null;
    try{
//      doc = sb.build(new FileInputStream(inFile));
//      XMLOutputter out = new XMLOutputter(indent,newLines,Charset);
//      out.output(doc,new FileOutputStream(inFile));
      doc = sb.build(new FileInputStream(configFileName));
      
//      XMLOutputter out = new XMLOutputter(indent,newLines,Charset);
//      out.output(doc,new FileOutputStream(configFileName));
      
      XMLOutputter outputter = new XMLOutputter();
		out = new BufferedOutputStream(new FileOutputStream(configFileName));
		outputter.output(doc, out);
		
    }catch (JDOMException jdom) {
      System.err.println(jdom.getMessage());
      System.exit(1);
    }catch (IOException ioe) {
      System.err.println(ioe);
      System.exit(1);
    }
  }

  /**read XML File and parser it*/
  //private ConcurrentHashMap readXMLFile(String inFile) throws Exception
  private HashMap readXMLFile(String inFile) throws Exception
  {
    Document doc;
    hashmap.clear();
    try {
    	
    	DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
    	
    	DocumentBuilder db = domfac.newDocumentBuilder();
    	org.w3c.dom.Document document =  db.parse(inFile);
    	
      DOMBuilder domb = new DOMBuilder();
      
      doc = domb.build(document);
      //SAXBuilder saxb = new SAXBuilder();
      //doc = saxb.build(new FileInputStream(inFile));
      Element root = doc.getRootElement();
      List children = root.getChildren();
      int size = root.getChildren().size();
      List mmscIP = new ArrayList();
      for(int i=0;i<size;i++)
      {
        Element element = (Element)root.getChildren().get(i);
        if(element.getName().equals("mmscIP"))
       {
         mmscIP.add(element.getTextTrim());
       }
       else{
         hashmap.put(element.getName(),element.getTextTrim());
       }
      }
      hashmap.put("mmscIP",mmscIP);
    }
    catch (IOException ioe) {
      System.err.println(ioe);
      System.exit(1);
    }
    return(this.hashmap);
  }
}