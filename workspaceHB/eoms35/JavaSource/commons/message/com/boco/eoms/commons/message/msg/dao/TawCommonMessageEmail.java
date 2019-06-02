package com.boco.eoms.commons.message.msg.dao;

import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class TawCommonMessageEmail implements MimeMessagePreparator,
		InitializingBean {

	private VelocityEngine velocityEngine;

	String classpath = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath()
			+ "";

	String logfilepaths = classpath.substring(1, classpath
			.lastIndexOf("/classes/"));

	String logfilepath = logfilepaths.replaceAll("/", "\\\\");

	private String plainTextTemplate = "plainText.vm";

	private String htmlTemplate = "html.vm";

	private String from ;

	private String to ;

	private String subject;

	private Map data;

	public void prepare(MimeMessage msg) throws Exception {

		msg.addFrom(InternetAddress.parse(from));
		msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject(subject);

		MimeMultipart ma = new MimeMultipart("panlong");
		msg.setContent(ma);
		BodyPart plainText = new MimeBodyPart();

		plainText.setText(VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, plainTextTemplate, data));

		ma.addBodyPart(plainText);

		BodyPart html = new MimeBodyPart();
		html.setContent(VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, htmlTemplate, data), "text/html");
		ma.addBodyPart(html);

	}

	public void afterPropertiesSet() throws Exception {

		if (velocityEngine == null) {
			throw new IllegalArgumentException(
					"Must set the velocityEngine property of "
							+ getClass().getName());
		}
	}

	public void setData(Map data) {
		this.data = data;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getLogfilepath() {
		return logfilepath;
	}

	public void setLogfilepath(String logfilepath) {
		this.logfilepath = logfilepath;
	}

	public String getLogfilepaths() {
		return logfilepaths;
	}

	public void setLogfilepaths(String logfilepaths) {
		this.logfilepaths = logfilepaths;
	}

	public String getPlainTextTemplate() {
		return plainTextTemplate;
	}

	public void setPlainTextTemplate(String plainTextTemplate) {
		this.plainTextTemplate = plainTextTemplate;
	}

	public Map getData() {
		return data;
	}

	public String getFrom() {
		return from;
	}

	public String getHtmlTemplate() {
		return htmlTemplate;
	}

	public String getSubject() {
		return subject;
	}

	public String getTo() {
		return to;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

}
