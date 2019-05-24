package com.boco.eoms.commons.log.dao.castor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;


import com.boco.eoms.commons.log.model.castor.TawCommonLogFileInfo;
import com.boco.eoms.commons.log.model.castor.TawCommonLogFileInfoMapping;

public class TawCommonFileInfoHandlel {
	private String xmlfile;

	private Mapping mapping;

	public TawCommonFileInfoHandlel() throws Exception {
		String locallogxml = StaticMethod
				.getFilePath("classpath:com/boco/eoms/commons/log/dao/castor/tawcommonlogfileinfo.xml");
		String logxml = "";
		logxml = locallogxml.replaceAll("/", "\\\\");
		this.xmlfile = logxml;
		try {
			mapping = new Mapping();
			String mappingfile = StaticMethod
					.getFilePath("classpath:com/boco/eoms/commons/log/dao/castor/tawcommonlogfileinfomapping.xml");
			String logmapping = "";
			logmapping = mappingfile.replaceAll("/", "\\\\");
			mapping.loadMapping(logmapping);
		} catch (Exception e) {
			BocoLog.error(this, "查找的日志文件:" + xmlfile + " 不存在或者被删除!");
		}
	}

	private TawCommonLogFileInfoMapping read() throws Exception {
		TawCommonLogFileInfoMapping homepages = null;
		try {
			Unmarshaller un = new Unmarshaller(
					TawCommonLogFileInfoMapping.class);
			un.setMapping(mapping);

			FileReader in = new FileReader(xmlfile);
			homepages = (TawCommonLogFileInfoMapping) un.unmarshal(in);
			in.close();
		} catch (Exception e) {
			BocoLog.error(this, "查找的日志文件:" + xmlfile + " 不存在或者被删除!");
		}
		return homepages;

	}

	public List getFileInfO() {
		List list = new ArrayList();
		TawCommonLogFileInfoMapping infomapping = new TawCommonLogFileInfoMapping();
		try {
			TawCommonFileInfoHandlel infohandle = new TawCommonFileInfoHandlel();
			infomapping = infohandle.read();
			list = infomapping.getLogfileinfo();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			BocoLog.error(this, "查找的日志文件:" + xmlfile + " 不存在或者被删除!");
		}
		return list;
	}

	public void saveinfoToxml(String currenttime, String modelid,
			String operid, String userid, String xmlfilepath, String loglevel) {

		TawCommonLogFileInfo fileinfo = new TawCommonLogFileInfo();
		fileinfo.setFilepath(xmlfilepath);
		fileinfo.setOperid(operid);
		fileinfo.setLoglevel(loglevel);
		fileinfo.setModeid(modelid);
		fileinfo.setOperid(operid);
		fileinfo.setSavetime(currenttime);
		fileinfo.setUserid(userid);

		FileOutputStream fo;
		OutputStreamWriter ow;
		BufferedWriter writer;

		Marshaller marshaller;
		try {

			fo = new FileOutputStream(xmlfile);

			ow = new OutputStreamWriter(fo, "GBK");
			writer = new BufferedWriter(ow);
			marshaller = new Marshaller(writer);

			marshaller.setMapping(mapping);

			marshaller.marshal(fileinfo);
			writer.close();
			ow.close();
			fo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			BocoLog.error(this, "保存日志文件失败! " + e.getMessage());
		}
	}
}
