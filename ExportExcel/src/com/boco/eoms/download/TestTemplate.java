package com.boco.eoms.download;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;


public class TestTemplate {

    public static void main(String[] args) throws FileNotFoundException {
        TestTemplate template = new TestTemplate();
        template.output();
    }

    public void output() throws FileNotFoundException {
        long startTimne = System.currentTimeMillis();
        StringTemplateGroup stGroup = new StringTemplateGroup("stringTemplate");

        //写入excel文件头部信息
        StringTemplate head = stGroup.getInstanceOf("config/head");
        File file = new File("D:/output.xls");
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
        writer.print(head.toString());
        writer.flush();

        int sheets = 1;
        //excel单表最大行数是65535
        int maxRowNum = 100000;
        int columnNum = 5;


        //写入excel文件数据信息
        for (int i = 0; i < sheets; i++) {
            StringBuilder body = new StringBuilder();
            body.append("\r\n<Worksheet ss:Name=\"");
            body.append(i);
            body.append("\">\r\n<Table ss:ExpandedColumnCount=\"");
            body.append(columnNum);
            body.append("\" ss:ExpandedRowCount=\"");
            body.append(maxRowNum);
            body.append("\" x:FullColumns=\"1\" x:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">");
            for (int j = 0; j < maxRowNum; j++) {
                body.append("\r\n<Row>\r\n");
                for (int k = 0; k < columnNum; k++) {
                    body.append("<Cell><Data ss:Type=\"String\">");
                    body.append("中文");
                    body.append("</Data></Cell>");
                }
                body.append("\r\n</Row>");
                if (j % 1000 == 0) {
                    writer.print(body);
                    writer.flush();
                    body = new StringBuilder();
                }
            }
            body.append("\r\n</Table>\r\n</Worksheet>\r\n");
            writer.print(body);
            writer.flush();
            body = null;
            System.out.println("body===" + body);
            System.out.println("正在生成excel文件的 sheet" + (i + 1));
        }

        //写入excel文件尾部
        writer.print("</Workbook>");
        writer.flush();
        writer.close();
        System.out.println("生成excel文件完成");
        long endTime = System.currentTimeMillis();
        System.out.println("用时=" + ((endTime - startTimne) / 1000) + "秒");
    }

}
