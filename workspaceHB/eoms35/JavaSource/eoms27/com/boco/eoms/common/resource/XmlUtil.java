package com.boco.eoms.common.resource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.apache.xpath.XPathAPI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.DOMResult;
import java.io.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-6
 * Time: 17:46:08
 * To change this template use File | Settings | File Templates.
 */
/*
* Created by IntelliJ IDEA.
* User: administrator
* Date: Mar 26, 2002
* Time: 1:24:56 PM
* To change template for new class use
* Code Style | Class Templates options (Tools | IDE Options).
 */

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.DOMResult;
import java.io.*;
import java.util.Properties;

import org.xml.sax.InputSource;
import org.apache.xpath.XPathAPI;

public class XmlUtil implements java.io.Serializable {

    public XmlUtil() {
    }

    public static DocumentBuilder getBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        dFactory.setNamespaceAware(true);
        DocumentBuilder builder = dFactory.newDocumentBuilder();
        return builder;
    }

    //get a document from given file
    public static Document getDocument(String path) throws Exception {
        //BufferedReader fileIn=new BufferedReader(new FileReader(path));
        File f = new File(path);
        DocumentBuilder builder = getBuilder();
        Document doc = builder.parse(f);
        return doc;
    }

    //get a document from InputStream
    public static Document getDocument(InputStream in) throws Exception {
        DocumentBuilder builder = getBuilder();
        Document doc = builder.parse(in);
        return doc;
    }

    //create a empty document
    public static Document getNewDoc() throws Exception {
        DocumentBuilder builder = getBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    //create a document from given string
    public static Document getNewDoc(String xmlStr) {
        Document doc = null;
        try {
            StringReader sr = new StringReader(xmlStr);
            InputSource iSrc = new InputSource(sr);
            DocumentBuilder builder = getBuilder();
            doc = builder.parse(iSrc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    //save a document as a file at the given file path
    public static void save(Document doc, String filePath) {
        try {
            String STRXML = toString(doc); //Spit out DOM as a String
            String path = filePath;
            writeXml(STRXML, path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save a string(xml) in the given file path
    public static void writeXml(String STRXML, String path) {
        try {
            File f = new File(path);
//            PrintWriter out = new PrintWriter(new FileWriter(f));
        java.io.FileOutputStream stream = null;
        java.io.DataOutputStream output = null;
        try {
            stream = new java.io.FileOutputStream(f);
            output = new java.io.DataOutputStream(stream);
            output.writeBytes(new String(STRXML.getBytes("GB2312"), "ISO8859-1"));   //写文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
//            out.print(STRXML);
//            out.close();
        if(stream!=null)stream.close();
        if(output!=null)output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //format a document to string

    public static String toString(Document document){
        // load the transformer
        StringWriter buffer = new StringWriter();

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            // now lets create the TrAX source and result
            // objects and do the transformation
            Source source = new DOMSource(document);

            StreamResult result = new StreamResult( buffer );
            Properties props= new Properties();
            props.setProperty("encoding", "GB2312");
            props.setProperty("method", "xml");
            props.setProperty("omit-xml-declaration", "no");
            transformer.setOutputProperties(props);
            transformer.transform( source, result );
        } catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
            transformerFactoryConfigurationError.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } finally {
            return(buffer.toString());
        }
    }

    //format a node to string
    public static String toString(Node node) {
        StringWriter buffer = new StringWriter();

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            // now lets create the TrAX source and result
            // objects and do the transformation
            Source source = new DOMSource(node);

            StreamResult result = new StreamResult( buffer );

            Properties props= new Properties();
            props.setProperty("encoding", "GB2312");
            props.setProperty("method", "xml");
            props.setProperty("omit-xml-declaration", "yes");

            transformer.setOutputProperties(props);
            transformer.transform( source, result );
        } catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
            transformerFactoryConfigurationError.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } finally {
            String result = buffer.toString();
            /*if(result.indexOf("?>")>0){
                int a = result.indexOf("?>") + 3;
                result = result.substring(a);
            }*/
            return(result);
        }
    }

    public static String toString(Node node,Document doc) {
        StringWriter buffer = new StringWriter();

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            // now lets create the TrAX source and result
            // objects and do the transformation
            Source source = new DOMSource(node);

            StreamResult result = new StreamResult( buffer );

            Properties props= new Properties();
            props.setProperty("encoding", "GB2312");
            props.setProperty("method", "xml");
            props.setProperty("omit-xml-declaration", "yes");

            transformer.setOutputProperties(props);
            transformer.transform( source, result );
        } catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
            transformerFactoryConfigurationError.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } finally {
            String result = buffer.toString();
            if(result.indexOf("?>")>0){
                int a = result.indexOf("?>") + 3;
                result = result.substring(a);
            }
            return(result);
        }

//
//            String STRXML = null;
//            try {
//                OutputFormat format = new OutputFormat(doc);   //Serialize DOM
//                //OutputFormat format1 = new OutputFormat();  //Serialize DOM
//                format.setEncoding("GB2312");
//                StringWriter stringOut = new StringWriter();        //Writer will be a String
//                XMLSerializer serial = new XMLSerializer(stringOut, format);
//                //XMLSerializer serial1 = new XMLSerializer();
//                serial.asDOMSerializer();                     // As a DOM Serializer
//                serial.serialize((Element) node);
//                STRXML = stringOut.toString(); //Spit out DOM as a String
//                int a = STRXML.indexOf("?>") + 3;
//                STRXML = STRXML.substring(a);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return STRXML;



    }
    private static Node getDOMResultNode(Document xmlDoc, Document xslDoc) throws TransformerException, TransformerConfigurationException,
            FileNotFoundException, IOException {
        Node temNode = null;
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            if (tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE)) {
                DOMSource xslDomSource = new DOMSource(xslDoc);
                Transformer transformer = tFactory.newTransformer(xslDomSource);
                DOMSource xmlDomSource = new DOMSource(xmlDoc);
                //StringWriter stringOut = new StringWriter();
                DOMResult domResult = new DOMResult();
                transformer.transform(xmlDomSource, domResult);
                temNode = domResult.getNode();
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            return temNode;
        }

    }
/*
    public static void DOM2DOM(Document xmlDoc, Document xslDoc, Object out) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            if (tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE)) {
                DOMSource xslDomSource = new DOMSource(xslDoc);
                Transformer transformer = tFactory.newTransformer(xslDomSource);
                DOMSource xmlDomSource = new DOMSource(xmlDoc);
                DOMResult domResult = new DOMResult();
                transformer.transform(xmlDomSource, domResult);
                Properties outformat = OutputProperties.getDefaultMethodProperties("xml");
                outformat.setProperty("encoding", "GB2312");
                outformat.setProperty("method", "html");
                outformat.setProperty("omit-xml-declaration", "yes");
                Serializer serializer = SerializerFactory.getSerializer(outformat);
                String class_name = out.getClass().getName().toUpperCase();
                if (class_name.indexOf("STREAM") > 0) {
                    serializer.setOutputStream((OutputStream) out);
                } else {
                    serializer.setWriter((Writer) out);
                }
                serializer.asDOMSerializer().serialize(domResult.getNode());
            } else {
                throw new org.xml.sax.SAXNotSupportedException("DOM node processing not supported!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (xmlDoc != null) {
                System.out.println(XmlUtil.toString(xmlDoc));
            }
            if (xslDoc != null) {
                System.out.println(XmlUtil.toString(xslDoc));
            }
        }
    }

    public static void DOM2DOM(Document xmlDoc, Document xslDoc) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            if (tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE)) {
                DOMSource xslDomSource = new DOMSource(xslDoc);
                Transformer transformer = tFactory.newTransformer(xslDomSource);
                DOMSource xmlDomSource = new DOMSource(xmlDoc);
                DOMResult domResult = new DOMResult();
                transformer.transform(xmlDomSource, domResult);
                Properties outformat = OutputProperties.getDefaultMethodProperties("xml");
                outformat.setProperty("encoding", "GB2312");
                Serializer serializer = SerializerFactory.getSerializer(outformat);
                serializer.setOutputStream(System.out);
                serializer.asDOMSerializer().serialize(domResult.getNode());
            } else {
                throw new org.xml.sax.SAXNotSupportedException("DOM node processing not supported!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (xmlDoc != null) {
                System.out.println(XmlUtil.toString(xmlDoc));
            }
            if (xslDoc != null) {
                System.out.println(XmlUtil.toString(xslDoc));
            }
        }
    }

    public static void DOM2DOM(String xmlUrl, String xslUrl) throws Exception {
        Document xmlDoc = getDocument(xmlUrl);
        Document xslDoc = getDocument(xslUrl);
        DOM2DOM(xmlDoc, xslDoc);
    }

    public static void DOM2DOM(String xmlUrl, String xslUrl, Object out) throws Exception {
        Document xmlDoc = getDocument(xmlUrl);
        Document xslDoc = getDocument(xslUrl);
        DOM2DOM(xmlDoc, xslDoc, out);
    }

    public static void DOM2DOM(Document xmlDoc, String xslUrl) throws Exception {
        Document xslDoc = getDocument(xslUrl);
        DOM2DOM(xmlDoc, xslDoc);
    }

    public static void DOM2DOM(Document xmlDoc, String xslUrl, Object out) throws Exception {
        Document xslDoc = getDocument(xslUrl);
        DOM2DOM(xmlDoc, xslDoc, out);
    }*/

    public static Transformer DOM2Transformer(Document xslDoc) {
        String key = "javax.xml.transform.TransformerFactory";
        String value = Prefs.instance().getProperty(key);
        if (Util.isNull(value)) {
               value = "org.apache.xalan.xsltc.trax.TransformerFactoryImpl";
        }

        Properties props = System.getProperties();
        props.put(key, value);
        System.setProperties(props);
        Transformer transformer = null;

        if (xslDoc != null) {

            TransformerFactory tFactory = TransformerFactory.newInstance();

            if (tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE)) {
                DOMSource xslDomSource = new DOMSource(xslDoc);
                try {
                    transformer = tFactory.newTransformer(xslDomSource);
                } catch (TransformerConfigurationException ex) {
                    ex.printStackTrace();
                    System.out.println("transformer can't configurate");
                }
            }
        }
        return transformer;
    }

    public static Transformer DOM2Transformer(String xslUrl) {

        Document xslDoc = null;

        try {
            xslDoc = XmlUtil.getDocument(xslUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("can't get xslDoc from xsl url");
        }

        return DOM2Transformer(xslDoc);
    }

    public static String DOM2XMLStr(Document xmlDoc, Transformer translet) {
        StringWriter stringOut = new StringWriter();
        String xmlstr = null;

        try {
            translet.transform(new DOMSource(xmlDoc), new StreamResult(stringOut));
            xmlstr = stringOut.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (xmlDoc != null) {
                System.out.println(XmlUtil.toString(xmlDoc));
            }
        }
        return xmlstr;
    }

    public static String XMLStr2XMLStr(String xmlin, Transformer translet) {
        String xmlstr = null;
        if (xmlin != null && xmlin.length()>0) {
             StringWriter stringOut = new StringWriter();
             StringReader strginIn = new StringReader(xmlin);
             try {
                StreamSource xmlSrc=new StreamSource(strginIn);
                StreamResult xmlOut= new StreamResult(stringOut);
                synchronized (translet) {
                    translet.transform(xmlSrc, xmlOut);
                }
                xmlstr = stringOut.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                if (xmlin != null) {
                    System.out.println(xmlin);
                }
            } finally {
                 return xmlstr;
             }
        } else {
             return xmlstr;
        }
    }

    public static String transformNode(Document xmlDoc, Document xslDoc) throws Exception {

        String STRXML = "";
        try {
            STRXML = toString((Document) getDOMResultNode(xmlDoc, xslDoc));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("can't to String");
        } finally {
            int a = STRXML.indexOf("?>") + 3;
            STRXML = STRXML.substring(a);
            return STRXML;
        }

    }

    public static String getNodeValue(Document xmlDoc, String xPath) {
        String result = "";
        Node resultNode = null;
        Node root = xmlDoc.getDocumentElement();
        try {
            resultNode = XPathAPI.selectSingleNode(root, xPath);
            result = resultNode.getNodeValue();
        } catch (Exception ex) {
        } finally {
            return result;
        }

    }
     public static String GetNodeAttribute(String Attrname, String nodeSelect, Document selectDoc) {
        try {
            NodeList list = XPathAPI.selectNodeList(selectDoc, nodeSelect);
            return ((Element) (list.item(0))).getAttribute(Attrname);
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String args[]){

        try {
            Document doc = getDocument("d:/table.xml");
            System.out.println(toString(doc));
            save(doc,"d:/table1.xml");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        } finally {
        }

    }
}
