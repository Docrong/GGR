package com.boco.eoms.commons.message.msg.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.mail.javamail.JavaMailSender;

import com.boco.eoms.commons.message.msg.dao.TawCommonMessageEmail;

public class TawCommonMessageSendEmail {

	/**
	 * 发送EMAIL
	 * 
	 * @param subject
	 * @param from
	 * @param to
	 * @param message
	 */
	public static void sendEmail(String subject, String from, String to,
			String message) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"config/message.xml");

		JavaMailSender sender = (JavaMailSender) ctx.getBean("sender");
		TawCommonMessageEmail preparator = (TawCommonMessageEmail) ctx
				.getBean("messageemail");

		Map data = new HashMap();
		data.put("msg", message);
		preparator.setTo(to);
		preparator.setFrom(from);
		preparator.setSubject(subject);
		preparator.setData(data);

		sender.send(preparator);

	}
	/**
	 * 得到MESSAGE的配置属性值
	 * @return
	 */
	public static TawCommonMessageEmail getMessageEmailconfig(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"config/message.xml");
		TawCommonMessageEmail preparator = (TawCommonMessageEmail) ctx
		.getBean("messageemail");
		return preparator;
	}

}
