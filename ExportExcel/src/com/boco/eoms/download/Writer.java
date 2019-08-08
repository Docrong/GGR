package com.boco.eoms.download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import com.boco.eoms.servlet.ExportExcel;

public class Writer extends Thread {
    private CountDownLatch countDownLatch;
    private DataSource ds;
    private Connection conn;
    private String currentSql;
    private String[] chineseName;
    private String fileName;
    private static int threadCount;
    private int countTest;

    public Writer(CountDownLatch countDownLatch, DataSource ds, String currentSql, String[] chineseName, String fileName, int countTest) {
        this.countDownLatch = countDownLatch;
        this.ds = ds;
        this.currentSql = currentSql;
        this.chineseName = chineseName;
        this.fileName = fileName;
        threadCount++;
        this.countTest = countTest;

    }

    public static int getThreadCount() {
        return threadCount;
    }

    public int getCountTest() {
        return countTest;
    }

    @Override
    public void run() {
        System.out.println("�߳�" + Thread.currentThread().getName() + "����д������...");
        Statement ssel = null;
        ResultSet rs = null;
        StringBuilder body = new StringBuilder();
        int columnNum = chineseName.length;
        int j = 1;
        try {

            File file = new File(fileName);
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
            long startTimne = System.currentTimeMillis();
            conn = ds.getConnection();
            ssel = conn.createStatement();
            rs = ssel.executeQuery(currentSql);
            long endTime = System.currentTimeMillis();
            System.out.println("�߳�" + Thread.currentThread().getName() + "��ʱ=" + ((endTime - startTimne) / 1000) + "��");
            while (rs.next()) {
                body.append("\r\n<Row>\r\n");
                for (int k = 0; k < columnNum; k++) {
                    body.append("<Cell><Data ss:Type=\"String\">");
                    //System.out.println(englishName[k] + "=" + rs.getString(englishName[k]));
                    body.append(rs.getString(chineseName[k]));
                    body.append("</Data></Cell>");
                }
                body.append("\r\n</Row>");
                if (j % 1000 == 0) {
                    writer.print(body);
                    writer.flush();
                    body.setLength(0);
                }
                j++;
            }
            writer.print(body);
            writer.flush();
            body = null;
            writer.close();
            System.out.println("�߳�" + Thread.currentThread().getName() + "д��������ϣ��ȴ������߳�д�����");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                synchronized (Writer.class) {
                    threadCount--;
                    countTest--;

                    Writer.class.notifyAll();
                }


                if (rs != null) {
                    rs.close();
                }
                if (ssel != null) {
                    ssel.close();
                }
                if (conn != null) {
                    conn.close();
                }
                //cp.close(conn);
                //cp.printDebug();
                countDownLatch.countDown();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        System.out.println("�����߳�д����ϣ�����������������...");
    }

}
