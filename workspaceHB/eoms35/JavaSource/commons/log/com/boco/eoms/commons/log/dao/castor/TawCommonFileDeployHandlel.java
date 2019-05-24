package com.boco.eoms.commons.log.dao.castor;

import java.io.FileReader;
import java.io.FileWriter;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.log.model.castor.TawCommonLogFileDeployMapping;

/**
 * 
 * @author panlong
 * @Mar 23, 2007 3:43:15 AM
 */
public class TawCommonFileDeployHandlel {

	private String xmlfile;

	private Mapping mapping;

	public TawCommonFileDeployHandlel() {

	}

	public TawCommonFileDeployHandlel(String mapfile, String xmlfile)
			throws Exception {
		this.xmlfile = xmlfile;
		try {
			mapping = new Mapping();
			mapping.loadMapping(mapfile);
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
		}
	}

	/**
	 * read xml
	 * 
	 * @return
	 * @throws Exception
	 */
	public TawCommonLogFileDeployMapping read() throws Exception {
		TawCommonLogFileDeployMapping homepages = null;
		try {
			Unmarshaller un = new Unmarshaller(
					TawCommonLogFileDeployMapping.class);
			un.setMapping(mapping);

			FileReader in = new FileReader(xmlfile);
			homepages = (TawCommonLogFileDeployMapping) un.unmarshal(in);

			in.close();

		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
		}
		return homepages;

	}

	public TawCommonLogFileDeployMapping save() throws Exception {
		TawCommonLogFileDeployMapping homepages = null;
		try {
			Unmarshaller un = new Unmarshaller(
					TawCommonLogFileDeployMapping.class);
			un.setMapping(mapping);
			FileWriter out = new FileWriter(xmlfile);

			out.close();
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
		}
		return homepages;

	}
}
