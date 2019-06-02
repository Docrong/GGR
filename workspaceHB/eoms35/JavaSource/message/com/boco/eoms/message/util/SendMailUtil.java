package com.boco.eoms.message.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * 
 * @author User
 *
 */
public class SendMailUtil {
	  protected BodyPart messageBodyPart = null; //
	  protected Multipart multipart = null; //
	  protected MimeMessage mailMessage = null; //
	  protected Session mailSession = null; //
	  protected Properties mailProperties = System.getProperties(); //

	  protected InternetAddress mailFromAddress = null; //发邮件者
	  protected InternetAddress mailToAddress = null; //收邮件者
	  protected MailAuthenticator authenticator = null; //

	  protected String mailSubject = ""; //邮件主题
	  protected String mailContent = ""; //邮件内容
	  protected Date mailSendDate = null; //发送日期

	  protected String mailAddress = null; //
	  protected String username = null; //
	  protected String password = null; //
	  protected String smtpHost = null; //
	  protected String auth = null; //
	  private CommonUtil common=new CommonUtil();

  /**
   * 从数据库中获取邮件信息
   */
  public SendMailUtil() {

  }

  public SendMailUtil(String mailAddress, String username, String password,
                  String smtpHost, String auth) {
    //初始化邮件信息
	    this.setMailAddress(mailAddress); //发件人邮箱
	    this.setUsername(username); //用户名
	    this.setPassword(password); //密码
	    this.setSmtpHost(smtpHost); //邮件服务器
	    this.setAuth(auth); //是否验证
  }

  /**
   * 从配置文件中获取邮件信息
   * @param profile String
   */
  public SendMailUtil(String profile) {
    Properties propFile = new Properties();

    FileInputStream file = null;
    try {
      if (profile != null && profile.trim().length() > 0) {
        file = new FileInputStream(profile);
      }
      else {
        java.net.URL defaultfile = this.getClass().getResource(
            "mailDeploy.properties");
        System.out.println("defaultfile: " + defaultfile);
        if (defaultfile != null) {
          System.out.println(defaultfile.getPath());
          file = new FileInputStream(defaultfile.getPath());
        }
        else {
          System.exit(1);
        }
      }
    }
    catch (FileNotFoundException ex2) {
      ex2.printStackTrace();
      System.out.println("配置文件加载失败！");
    }
    BufferedInputStream bis = new BufferedInputStream(file);
    try {
      propFile.load(bis);
    }
    catch (IOException ex3) {
      ex3.printStackTrace();
      System.out.println("读配置文件错误！");
    }
    mailAddress = propFile.getProperty("mailAddress");
    username = propFile.getProperty("username");
    password = propFile.getProperty("password");
    smtpHost = propFile.getProperty("mailService");
    auth = propFile.getProperty("auth");

    System.out.println("semdmail: " + mailAddress);
    System.out.println("username: " + username);
    System.out.println("password: " + password);
    System.out.println("mailService: " + smtpHost);
    System.out.println("auth: " + auth);

  }

  /**
   * 设置安全认证֤
   * @param auth String
   */
  public void setAuth(String auth) {
    if (auth == null) {
      auth = "true";
    }
    else if (auth.equals("1") || auth.equals("true")) {
      this.auth = "true";
    }
    else {
      this.auth = "false";
    }
    if ("true".equals(auth)) {
      authenticator = new MailAuthenticator(username, password);
      mailSession = Session.getDefaultInstance(mailProperties, authenticator);
    }
    else {
      mailSession = Session.getInstance(mailProperties);
    }
    mailMessage = new MimeMessage(mailSession);
    multipart = new MimeMultipart();
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public void setMailFromAddress(InternetAddress mailFromAddress) throws
      MessagingException {
    this.mailFromAddress = mailFromAddress;
    mailMessage.setFrom(mailFromAddress);
  }

  /**
   * 设置发送者邮箱
   * @param mailFromAddress String
   * @throws MessagingException
   */
  public void setMailFromAddress(String mailFromAddress) throws
      MessagingException {
    InternetAddress fromAddress = new InternetAddress(mailFromAddress.trim());
    this.mailFromAddress = fromAddress;
    mailMessage.setFrom(fromAddress);
  }

  public void setMailSendDate(Date mailSendDate) throws MessagingException {
    this.mailSendDate = mailSendDate;
    mailMessage.setSentDate(mailSendDate);
  }

  /**
   * 设置邮件主题
   * @param mailSubject String
   * @throws MessagingException
   */
  public void setMailSubject(String mailSubject) throws MessagingException {
    this.mailSubject = mailSubject;
    mailMessage.setSubject(mailSubject, "UTF-8");
  }

  public void setMailToAddress(InternetAddress mailToAddress) throws
      MessagingException {
    this.mailToAddress = mailToAddress;
    mailMessage.addRecipient(Message.RecipientType.TO, mailToAddress);
  }

  /**
   * 设置收件人地址(1)
   * @param mailType String
   * @param mailTo String
   * @throws Exception
   */
  public void setMailToSingle(String mailType, String mailTo) throws Exception {
    mailToAddress = new InternetAddress(mailTo);
    this.mailToAddress = mailToAddress;
    if (mailType.equalsIgnoreCase("to")) {
      mailMessage.addRecipient(Message.RecipientType.TO, mailToAddress);
    }
    else if (mailType.equalsIgnoreCase("cc")) {
      mailMessage.addRecipient(Message.RecipientType.CC, mailToAddress);
    }
    else if (mailType.equalsIgnoreCase("bcc")) {
      mailMessage.addRecipient(Message.RecipientType.BCC, mailToAddress);
    }
    else {
      throw new Exception("Unknown mailType: " + mailType + "!");
    }
  }

  /**
   * 设置收件人地址(n)，收件人类型为to,cc,bcc(大小写不限)
   * @param mailType String
   * @param mailTo String[]
   * @throws Exception
   */
  public void setMailToMul(String mailType, String[] mailTo) throws Exception {
    for (int i = 0; i < mailTo.length; i++) {
      String[] arg=common.splitAddress(mailTo[i]);
      try{
      mailToAddress = new InternetAddress(arg[1],arg[0],"gb2312");
      }catch(Exception e){
    	  System.out.println("数组越界或不存在！");
      }

      if (mailType.equalsIgnoreCase("to")) {
        mailMessage.addRecipient(Message.RecipientType.TO, mailToAddress);
      }
      else if (mailType.equalsIgnoreCase("cc")) {
        mailMessage.addRecipient(Message.RecipientType.CC, mailToAddress);
      }
      else if (mailType.equalsIgnoreCase("bcc")) {
        mailMessage.addRecipient(Message.RecipientType.BCC, mailToAddress);
      }
      else {
        throw new Exception("Unknown mailType: " + mailType + "!");
      }
    }
  }

  /**
   * 设置邮件内容
   * @param mailBody String
   */
  public void setBody(String mailBody) {
    try {
      messageBodyPart = new MimeBodyPart();
      messageBodyPart.setContent(mailBody, "text/plain; charset=UTF-8");
      multipart.addBodyPart(messageBodyPart);
    }
    catch (Exception e) {
      System.err.println("设置邮件正文时发生错误！" + e);
    }
  }

  //所有子类都需要实现的抽象方法，为了支持不同的邮件类型
  protected void setMailContent(String mailContent) throws
      MessagingException {}

  public void setPassword(String password) {
    this.password = password;
  }

  public void setSmtpHost(String smtpHost) {
    this.smtpHost = smtpHost;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * 设置邮件附件
   * @param file String
   * @throws MessagingException
   */
  public void setFile(String file) throws MessagingException {
    //邮件附件	
    if (file != null && file.trim().length() > 0) {
      MimeBodyPart mbp = new MimeBodyPart();
      FileDataSource fds = new FileDataSource(file.trim());
      mbp.setDataHandler(new DataHandler(fds));
      try {
        mbp.setFileName(MimeUtility.encodeText(fds.getName(), "GBK", null));
      }
      catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
      }
      catch (MessagingException ex) {
        ex.printStackTrace();
      }
      multipart.addBodyPart(mbp);
    }
	  
  }
  //自己定义
  public void setFile(String filename,String file) throws MessagingException {
	  if (file != null && file.trim().length() > 0) {
		  MimeBodyPart mbp = new MimeBodyPart();
		  DataHandler dh=new DataHandler(file,"text/plain; charset=UTF-8");
		  mbp.setDataHandler(dh);
		  try {
			mbp.setFileName(MimeUtility.encodeText(filename, "UTF-8", null));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		multipart.addBodyPart(mbp);

	  }
  }

  /**
   * 上传多个附件
   * @param file String
   * @throws MessagingException
   */
  public void setFile(String[] file) throws MessagingException {
    if (file != null) {
      int fileCount = file.length;
      for (int i = 0; i < fileCount; i++) {
        setFile(file[i]); //
      }
    }
  }
//自己添加
  public void setFile(String[] filename,String[] file) throws MessagingException{
	  if(filename != null&&file != null){
		  int fileCount = file.length;
	      for (int i = 0; i < fileCount; i++) {
	        setFile(filename[i],file[i]); //
	      }
	  }
  }
  /**
   * 删除文件
   * @param files String
   */
  protected void delFile(String files) {
    if (files != null && files.trim().length() > 0) {
      File uploadfile = new File(files);
      uploadfile.delete();
    }
  }

  /**
   * 设置发送基本信息
   * @param subject String
   * @param text String
   */
  protected void setBaseMail(String subject, String text) {
    if (mailAddress == null || username == null || password == null ||
        smtpHost == null) {
      System.out.println("发送邮件基本信息不全，请检查配置文件！");
      System.exit(1);
    }

    System.out.println("正在发送邮件，请稍候.......");
    try {
      mailProperties.put("mail.transport.protocol", "smtp"); //邮件协议
      mailProperties.put("mail.smtp.auth", auth); //是否需要验证
      mailProperties.put("mail.smtp.host", smtpHost.trim()); //smtp主机名或ip地址
      mailProperties.put("mail.smtp.user", username.trim()); //发送方邮件用户名
      mailProperties.put("mail.smtp.password", password.trim()); //邮件密码

      //邮件安全认证
      this.setAuth(auth);

      //发件人
      this.setMailFromAddress(mailAddress);

      //邮件主题
      this.setMailSubject(subject);

      //发送日期
      this.setMailSendDate(new Date());

      //邮件HTML内容
      this.setBody(text);
    }
    catch (MessagingException ex) {
        System.out.println("发送邮件失败！");
        ex.printStackTrace();
      }

    }

    /**
     * 发送一个附件给一个邮件
     * @param To String -- 收件人
     * @param subject String -- 主题
     * @param text String -- 邮件内容
     * @param files String -- 上传附件路径名,如果没有附件,此参数为null
     */
    public void send(String to, String subject, String text, String files) {
      try {
        this.setBaseMail(subject, text);
        //设置收邮件人
        try {
          this.setMailToSingle("to", to);
        }
        catch (Exception ex1) {
          ex1.printStackTrace();
        }
        //添加附件
        this.setFile(files);

        mailMessage.setContent(multipart);
        //发送
        Transport.send(mailMessage);
        System.out.println("发送邮件成功!");
      }
      catch (MessagingException ex) {
        System.out.println("发送邮件失败！");
        ex.printStackTrace();
      }
    }

    /**
     * 发送多个附件给一个邮件
     * @param To String
     * @param subject String
     * @param text String
     * @param files String[]
     */
    public void sendAffixsMail(String to, String subject, String text,
                               String[] files) {
      try {
        this.setBaseMail(subject, text);
        //设置收邮件人
        try {
          this.setMailToSingle("to", to);
        }
        catch (Exception ex1) {
          ex1.printStackTrace();
        }
        //添加附件
        this.setFile(files);

        mailMessage.setContent(multipart);
        //发送
        Transport.send(mailMessage);
        System.out.println("发送邮件成功!");
      }
      catch (MessagingException ex) {
        System.out.println("发送邮件失败！");
        ex.printStackTrace();
      }
    }

    /**
     * 发送一个附件给多个邮件
     * @param To String[] -- 收件人列表
     * @param subject String -- 主题
     * @param text String -- 邮件内容
     * @param files String -- 上传附件路径名,如果没有附件,此参数为null
     */
    public void sendAffixMails(String[] to,String[] cc,String[] bcc, String subject, String text,
                               String files) {
      try {
        this.setBaseMail(subject, text);
        //设置收邮件人
        try {
          this.setMailToMul("to", to);
          if(cc!=null)
          this.setMailToMul("cc", cc);
          if(bcc!=null)
          this.setMailToMul("bcc", bcc);
        }
        catch (Exception ex1) {
          ex1.printStackTrace();
        }
        //添加附件
        this.setFile(files);
        mailMessage.setContent(multipart);
        //发送
        Transport.send(mailMessage);
        System.out.println("发送邮件成功!");
      }
      catch (MessagingException ex) {
        System.out.println("发送邮件失败！");
        ex.printStackTrace();
      }
    }
    /**
     * 发送多个附件给多个邮件
     * @param To String[]
     * @param subject String
     * @param text String
     * @param files String[]
     */
    public void sendAffixsMails(String[] to,String[] cc,String[] bcc, String subject, String text,
                                String[] files) {
      try {
        this.setBaseMail(subject, text);
        //设置收邮件人
        try {
        	this.setMailToMul("to", to);
            if(cc!=null)
            this.setMailToMul("cc", cc);
            if(bcc!=null)
            this.setMailToMul("bcc", bcc);
        }
        catch (Exception ex1) {
          ex1.printStackTrace();
        }
        //添加附件
        this.setFile(files);
        mailMessage.setContent(multipart);
        //发送
        Transport.send(mailMessage);
        System.out.println("发送邮件成功!");
      }
      catch (MessagingException ex) {
        System.out.println("发送邮件失败！");
        ex.printStackTrace();
      }
    }

    public class MailAuthenticator
        extends Authenticator {
      String username = null;
      String password = null;
      public MailAuthenticator() {}

      public void setUserName(String username) {
        this.username = username;
      }

      public void setPassword(String password) {
        this.password = password;
      }

      public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
      }

      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    }
  }
