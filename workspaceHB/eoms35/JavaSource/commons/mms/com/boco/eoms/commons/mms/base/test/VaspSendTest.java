package com.boco.eoms.commons.mms.base.test;

import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.service.MM7Sender;


public class VaspSendTest {

    public static void main(String[] args) throws Exception {
        String mm7c = StaticMethod.getFilePathForUrl("classpath:config/mms/base/mm7Config.xml");
        String connc = StaticMethod.getFilePathForUrl("classpath:config/mms/base/ConnConfig.xml");

        MM7Config mm7Config = new MM7Config(mm7c);
        mm7Config.setConnConfigName(connc);
        MM7Sender mm7Sender = new MM7Sender(mm7Config);
        MM7SubmitReq submit = new MM7SubmitReq();
        submit.setTransactionID("1000000");//11111111
        submit.addTo("15829550058");//13915002000
        submit.setVASID("10658218");
        submit.setVASPID("826211");//MyAdd
        submit.setServiceCode("000");
        submit.setSubject("测试");
        MMContent content = new MMContent();
        content.setContentType(MMConstants.ContentType.MULTIPART_MIXED);
        String picpath = StaticMethod.getFilePathForUrl("classpath:config/mms/test/picfilename.gif");
        MMContent sub1 = MMContent.createFromFile(picpath);//"f:\\yellow.gif"
        sub1.setContentID("1.gif");
        sub1.setContentType(MMConstants.ContentType.GIF);
        content.addSubContent(sub1);
        MMContent sub2 = MMContent.createFromString("This is a Test2!");
        sub2.setContentID("2.txt");
        sub2.setContentType(MMConstants.ContentType.TEXT);
        content.addSubContent(sub2);
        submit.setContent(content);
        MM7RSRes res = mm7Sender.send(submit);
        System.out.println("res.statuscode=" + res.getStatusCode() +
                ";res.statusText=" + res.getStatusText());
    }
}
