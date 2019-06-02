package com.boco.eoms.base.test;

import junit.framework.TestCase;

import com.boco.eoms.base.util.FileDownLoad;

public class FileDownloadTest extends TestCase {

    public FileDownloadTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testFileDownLoad() {
    	try {
    	      String url = "http://www.abada.cn/html/2008-04/5805.htm";
    	      Thread downThread = new Thread(new FileDownLoad(url, "d:\\download"), "nThread");
    	      downThread.start();
    	      System.out.println("thread have gone,we will go on");
    	    }
    	    catch (Exception e) {
    	      e.printStackTrace();
    	    }
    	    finally {

    	    }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(FileDownloadTest.class);
    }
}
