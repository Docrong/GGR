package com.boco.eoms.common.resource;

import org.apache.log4j.Category;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-6
 * Time: 17:52:36
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;

import org.w3c.dom.Document;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 *  The contents of this file are subject to the Mozilla Public
 *  License Version 1.1 (the "License"); you may not use this file
 *  except in compliance with the License. You may obtain a copy of
 *  the License at http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS
 *  IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  rights and limitations under the License.
 *
 *  The Original Code is pow2toolkit library.
 *
 *  The Initial Owner of the Original Code is
 *  Power Of Two S.R.L. (www.pow2.com)
 *
 *  Portions created by Power Of Two S.R.L. are
 *  Copyright (C) Power Of Two S.R.L.
 *  All Rights Reserved.
 *
 * Contributor(s):
 */


import org.apache.log4j.Category;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;


/**
 *  FileUtil class.
 *  <br>
 *  Contains static methods for file access.
 */
public class FileUtil {
    /** log4j category */
    private final static Category cat = Category.getInstance(FileUtil.class);


    /**
     *  Read the text file whose fullpath is specified by the input <code>file</code> parameter,
     *  and return the StringBuffer object containing its content.
     *
     * @param file the file fullpath
     * @return     the StringBuffer object containing the file content
     * @exception Exception if any error occurs.
     */
    public static StringBuffer read(String file) throws Exception {
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        String s = null;

        try {
            in = new BufferedReader(new FileReader(file));

            while ((s = in.readLine()) != null) {
                sb.append(s).append('\n');
            }

            return sb;
        } finally {
            if (in != null) in.close();
        }
    }


    /**
     *  Read the Java properties file specified by the input <code>propsFile</code>
     *  parameter, and use its content to instance a new Properties object.
     *
     *  @param     propsFile the name of the Java Properties file to read
     *  @return    the Properties object containing the properties readed
     *             from the input file
     *  @exception Exception if any error occcurs.
     */
    public static Properties getProperties(String propsFile) throws Exception {
        return getProperties(propsFile, false);
    }


    /**
     *  Read the Java properties file specified by the input <code>propsFile</code>
     *  parameter, and use its content to instance a new Properties object.
     *
     *  @param     propsFile        the name of the Java Properties file to read
     *  @     addToSystemProsp true to add the retrieved properties to the global
     *                              System properties; false otherwise.
     *  @return    the Properties object containing the properties readed
     *             from the input file
     *  @exception Exception if any error occcurs.
     */
    public static Properties getProperties(String propsFile, boolean addToSystemProps) throws Exception {
        FileInputStream fis = null;
        Properties props = null;

        try {
            fis = new FileInputStream(propsFile);
            props = (addToSystemProps) ? new Properties(System.getProperties()) : new Properties();
            props.load(fis);
            fis.close();
        } finally {
            if (fis != null) fis.close();
        }

        return props;
    }


    public static void StrSaveToFile(String fileName, String writeStr) {
        FileOutputStream myFileStream = null;
        try {
            File myfile = new File(fileName);
            if (!myfile.exists()) {
                myfile.createNewFile();
            }
            myFileStream = new FileOutputStream(myfile);
        } catch (FileNotFoundException e) {
            System.out.println("file not exist!");
            return;
        } catch (IOException e) {
            System.out.println("io error");
            return;
        }
        if (myFileStream != null) {
            byte[] tem = writeStr.getBytes();
            try {
                myFileStream.write(tem);
            } catch (IOException e) {
                System.out.print("input or output error");
                return;
            }
        }
    }

    public static String getAttrFromFile(String filePath, String xPath, String attrName) {
        String result = "";
        try {
            Document xmlDoc = XmlUtil.getDocument(filePath);
            Node temNode = XPathAPI.selectSingleNode(xmlDoc.getDocumentElement(), xPath);
            result = ((Element) temNode).getAttribute(attrName).toString();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            return result;
        }
    }

    //0:success
    //1:value is not exist
    //2:delete failure
    public static int deleteFile(String fileName){
        int result=0;
        File file = new File(fileName);
        if(file.exists()){
            if(!file.delete())
               result = 2;

        }else{
            result=1;
        }
        return result;

    }

}
