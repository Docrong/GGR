package com.boco.eoms.message.util;

public class SendMail {

    private SendMailUtil sendMail = null;

    public SendMail(String name, String pas, String add) {
        String username = name;
        String password = pas;
        String auth = "true";
        String mailService = "SunShengTai";
        String mailAddress = add;
        sendMail = new SendMailUtil(mailAddress, username, password, mailService, auth);
    }

    public void send(String to, String subject, String text, String files) {
        sendMail.send(to, subject, text, files);

    }

    public void sendAffixsMail(String to, String subject, String text, String[] files) {
        sendMail.sendAffixsMail(to, subject, text, files);

    }

    public void sendAffixMails(String[] to, String[] cc, String[] bcc, String subject, String text, String files) {
        sendMail.sendAffixMails(to, cc, bcc, subject, text, files);

    }

    public void sendAffixsMails(String[] to, String[] cc, String[] bcc, String subject, String text, String[] files) {
        sendMail.sendAffixsMails(to, cc, bcc, subject, text, files);

    }

}
