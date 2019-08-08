package com.boco.eoms.common.resource;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-9
 * Time: 21:01:33
 * To change this template use File | Settings | File Templates.
 */

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

public class ExcelUtil {

    public static String XML2ExcelStream(String doc_str) {

        String result = "";
        try {

            Document doc = XmlUtil.getNewDoc(doc_str);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("stat");
            HSSFHeader header = sheet.getHeader();
            header.setCenter("test");

            HSSFCellStyle style = wb.createCellStyle();
            style.setFillBackgroundColor(HSSFColor.SEA_GREEN.index);

            HSSFFont f = wb.createFont();
            f.setFontHeightInPoints((short) 10);
            f.setColor((short) HSSFColor.BLUE_GREY.index);
            f.setBoldweight(f.BOLDWEIGHT_BOLD);
            style.setFont(f);

            Element root = doc.getDocumentElement();
            Element head = (Element) root.getElementsByTagName("head").item(0);
            NodeList node_rows = root.getElementsByTagName("row");
            getHead(head, sheet, style);
            getBody(node_rows, sheet, style);
            sheet.createFreezePane(0, 2, 0, 2);


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wb.write(bos);
            bos.flush();
            result = new String(bos.toByteArray(), "ISO8859-1");
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw ex;
        } finally {
            return result;
        }

    }

    public static void XML2Excel(String doc_str, String excel_file_path) {

        Document doc = XmlUtil.getNewDoc(doc_str);
        XML2Excel(doc, excel_file_path);

    }

    public static HSSFWorkbook getWorkbook(Document doc) {
        if (doc == null) {
            //throw new Exception("source doc is null!");
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(Util.GBK2UNI("stat"));
        HSSFHeader header = sheet.getHeader();
        header.setCenter("test");

        HSSFCellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(HSSFColor.SEA_GREEN.index);

        HSSFFont f = wb.createFont();
        f.setFontHeightInPoints((short) 10);
        f.setColor((short) HSSFColor.BLUE_GREY.index);
        f.setBoldweight(f.BOLDWEIGHT_BOLD);
        style.setFont(f);

        try {
            Element root = doc.getDocumentElement();
            Element head = (Element) root.getElementsByTagName("head").item(0);
            NodeList node_rows = root.getElementsByTagName("row");
            getHead(head, sheet, style);
            getBody(node_rows, sheet, style);

            sheet.createFreezePane(0, 2, 0, 2);

            //return wb;
            //FileOutputStream fileOut = new FileOutputStream(excel_file_path);
            //wb.write(fileOut);
            //fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw ex;
        } finally {
            return wb;
        }

    }

    public static void XML2Excel(Document doc, String excel_file_path) {
        try {

            HSSFWorkbook wb = getWorkbook(doc);

            FileOutputStream fileOut = new FileOutputStream(excel_file_path);
            wb.write(fileOut);

            fileOut.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(Util.UNI2GBK(ex.toString()));

        }
    }

    public static void getBody(NodeList node_rows, HSSFSheet sheet, HSSFCellStyle style) {

        short num_all_col = 0;
        int num_rows = node_rows.getLength();
        int num_begin_row = sheet.getPhysicalNumberOfRows();

        HSSFRow row;
        HSSFCell cell;
        Element node_row, node_cnd, node_col;

        for (int i = 0; i < num_rows; i++) {
            num_all_col = 0;

            node_row = (Element) node_rows.item(i);


            if (node_row.getAttribute("bgcolor") != null) {
                String color = node_row.getAttribute("bgcolor");
                if (color == "2") {
                    style.setFillBackgroundColor(HSSFColor.RED.index);
                } else if (color == "1") {
                    style.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
                } else {
                    style.setFillBackgroundColor(HSSFColor.WHITE.index);
                }
            } else {
                style.setFillBackgroundColor(HSSFColor.WHITE.index);
            }

            NodeList node_cnds = node_row.getElementsByTagName("cnd");

            row = sheet.createRow((short) (num_begin_row + i));


            cell = row.createCell((short) num_all_col++);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(node_row.getAttribute("name"));
            cell.setCellStyle(style);

            int num_cnd = node_cnds.getLength();

            for (int j = 0; j < num_cnd; j++) {

                node_cnd = (Element) node_cnds.item(j);
                NodeList node_cols = node_cnd.getElementsByTagName("col");
                int num_col = node_cols.getLength();

                for (int k = 0; k < num_col; k++) {

                    node_col = (Element) node_cols.item(k);

                    cell = row.createCell(num_all_col++);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(node_col.getAttribute("value"));


                }
            }
        }
    }

    public static void getHead(Element head, HSSFSheet sheet, HSSFCellStyle style) {

        HSSFRow row0 = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell = row0.createCell((short) 0);
        cell.setCellValue("");
        cell = row1.createCell((short) 0);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue("");
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        NodeList nodeList = head.getElementsByTagName("cnd");
        int len = nodeList.getLength();
        for (int i = 0; i < len; i++) {

            short cell1_num = row1.getLastCellNum();

            Element cnd_node = (Element) nodeList.item(i);
            NodeList nodeList_col = cnd_node.getElementsByTagName("col");
            int len_col = nodeList_col.getLength();

            for (int j = 0; j < len_col; j++) {
                Element col_node = (Element) nodeList_col.item(j);
                cell = row1.createCell((short) (cell1_num + j + 1));
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(col_node.getAttribute("name"));
                cell.setCellStyle(style);
            }

            //cell1_num += len_col;
            cell = row0.createCell((short) (cell1_num + 1));
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(cnd_node.getAttribute("name"));
            cell.setCellStyle(style);

            //sheet.addMergedRegion(new Region(0,(short)(cell1_num+1),0,(short)(cell1_num+1+3)));
        }

    }

    public static void main(String arg[]) {
        String xml_path = "e:\\web_tmp\\excel\\test.xml";
        String excel_path = "e:\\web_tmp\\excel\\interface.xls";

        Document doc = null;
        try {
            doc = XmlUtil.getDocument(xml_path);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(Util.UNI2GBK(ex.toString()));
        }
        if (doc != null) {
            XML2Excel(doc, excel_path);
        }

    }
}
