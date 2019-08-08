package com.boco.eoms.workbench.infopub;

import org.jmock.Mock;

import com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao;

import junit.framework.TestCase;

public class TestInfopubService extends TestCase {
    InfopubService service = null;

    protected void setUp() throws Exception {
        super.setUp();
        service = new InfopubService();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNewThread() {
        String otherSystemType = "1";
        String forumsTitle = "接口公告";
        String title = "测试接口";
        String threadTypeId = "1";
        String reply = "1";
        String validity = "3";
        String canread = "省公司#dept,liqiuye#user";
        String isIncludeSubDept = "1";
        String auditUser = "yuwenchao";
        String content = "sdsdsdsdsdsd";
        String attachRef = null;
        String result = service.newThread(otherSystemType, forumsTitle, title,
                threadTypeId, reply, validity, canread, isIncludeSubDept, auditUser, content, attachRef);
        assertEquals("Success", result);
    }

}
