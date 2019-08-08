package com.boco.eoms.km.core.crimson.jaxp;

import org.w3c.dom.Document;


public abstract class DocumentFactory {

    public static String xmlParser = null;

    public abstract Document newDocument();

    public abstract Document parseFile(String filePath) throws XmlException;

    public abstract Document parseString(String xmlString) throws XmlException;

    public static final DocumentFactory newInstance() {
        if (xmlParser == null) {
            xmlParser = System.getProperty("XML_PARSER");
            if (xmlParser == null)
                System.setProperty("XML_PARSER", "eoms");
            xmlParser = "eoms";
        }

        if (xmlParser.equals("eoms")) {
            return new DocumentFactoryEomsImpl();
        }
        if (xmlParser.equals("xerces")) {
            return null;// new PrimetonDocumentFactoryImpl();
        }
        if (xmlParser.equals("crimson")) {
            return null;// new PrimetonDocumentFactoryCrimsonImpl();
        }

        return new DocumentFactoryEomsImpl();
    }
}