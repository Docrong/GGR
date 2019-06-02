package com.boco.eoms.base.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.apache.soap.Constants;
import org.apache.soap.Envelope;
import org.apache.soap.SOAPException;
import org.apache.soap.messaging.Message;
import org.apache.soap.transport.SOAPTransport;
import org.apache.soap.util.xml.XMLParserUtils;
/**
 * <p>
 * Title: eoms
 * </p>
 * <p>
 * Description: SOAP相关处理方法
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: boco
 * </p>
 * 
 * @author dudajiang
 * @version 1.0 update dy dudajiang 2003-4-7 13:00
 */
public class SOAPUtil {
	/**
	 * send soap xml message
	 * 
	 * @param url
	 *            soap service's address and
	 *            port,example:http://localhost:8080/eoms_j2ee/servlet/messagerouter
	 * @param msg
	 *            message body in xml format,it is a string.
	 * @return
	 */
	public static String sendStrMessage(String url, String msg) {
		String sResult = "";
		try {
			// get the envelope to send
			StringReader sReader = new StringReader(msg);
			DocumentBuilder xdb = XMLParserUtils.getXMLDocBuilder();
			Document doc = xdb.parse(new InputSource(sReader));
			if (doc == null) {
				throw new SOAPException(Constants.FAULT_CODE_CLIENT,
						"parsing error");
			}
			Envelope msgEnv = Envelope.unmarshall(doc.getDocumentElement());

			// send the message
			Message msgObj = new Message();
			msgObj.send(new URL(url), "urn:this-is-the-action-uri", msgEnv);

			// receive whatever from the transport and dump it to the screen
			SOAPTransport st = msgObj.getSOAPTransport();
			BufferedReader br = st.receive();
			String line;
			while ((line = br.readLine()) != null) {
				sResult = sResult + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}

	/**
	 * send soap xml message
	 * 
	 * @param url
	 *            soap service's address and
	 *            port,example:http://localhost:8080/eoms_j2ee/servlet/messagerouter
	 * @param file
	 *            xml message file's address,it is a physic directory string
	 * @return
	 */
	public static String sendFileMessage(String url, String file) {

		String sResult = "";
		try {
			// get the envelope to send
			FileReader fr = new FileReader(file);
			DocumentBuilder xdb = XMLParserUtils.getXMLDocBuilder();
			Document doc = xdb.parse(new InputSource(fr));
			if (doc == null) {
				throw new SOAPException(Constants.FAULT_CODE_CLIENT,
						"parsing error");
			}
			Envelope msgEnv = Envelope.unmarshall(doc.getDocumentElement());

			// send the message
			Message msgObj = new Message();
			msgObj.send(new URL(url), "urn:this-is-the-action-uri", msgEnv);

			SOAPTransport st = msgObj.getSOAPTransport();
			BufferedReader br = st.receive();
			String line;
			while ((line = br.readLine()) != null) {
				sResult = sResult + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}
}
