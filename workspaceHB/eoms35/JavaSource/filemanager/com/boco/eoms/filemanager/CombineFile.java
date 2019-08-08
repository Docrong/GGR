package com.boco.eoms.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;

import com.boco.eoms.common.util.StaticMethod;

import jxl.CellType;
import jxl.Sheet;
import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CombineFile {
    public String DoCombinFile(String realPath, String templatePath, String[] filelist, String type) {
        Random rnd = new Random();

        String retFile = File.separator + "filemanager" + File.separator + "files" + File.separator + "combine" + File.separator +
                StaticMethod.getTimestamp().toString().replace(' ', '-').replace(':', '-') + rnd.nextInt() +
                ".xls";
        if (type.equals("2")) {
            this.makObjXls_A(realPath + retFile, realPath + filelist[0]);
            for (int i = 1; i < filelist.length; i++) {
                filelist[i] = realPath + filelist[i];
                this.ConvergeXls_A(realPath + retFile, filelist[i]);
            }

        } else if (type.equals("1")) {
            this.makObjXls_B(realPath + retFile, realPath + templatePath);
            int row_id = 1;
            for (int i = 0; i < filelist.length; i++) {
                filelist[i] = realPath + filelist[i];
                this.ConvergeXls_B(realPath + retFile, filelist[i], row_id);
                row_id++;
            }
            this.sum_x(realPath + retFile);
            this.sum_y(realPath + retFile);

        }
        return retFile;

    }

    private void makObjXls_A(String obj_file, String temp_file) {
        Workbook workbook = null;

        try {
            InputStream ins = new FileInputStream(temp_file);
            workbook = Workbook.getWorkbook(ins);
            File outFile = new File(obj_file);
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作完成时，关闭对象，释放占用的内存空间
            workbook.close();
        }

    }

    private void ConvergeXls_A(String obj_file, String old_file) {

        Workbook workbook = null;
        Workbook workbook2 = null;
        try {

            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook, 从输入流创建Workbook
            InputStream ins = new FileInputStream(obj_file);
            workbook = Workbook.getWorkbook(ins);


            InputStream ins_2 = new FileInputStream(old_file);
            workbook2 = Workbook.getWorkbook(ins_2);
            Sheet dataSheet2 = workbook2.getSheet(0);

            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            File outFile = new File(obj_file);
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            WritableSheet dataSheet = wwb.getSheet(0);


            // 读取第一张工作表

            for (int i = 0; i < dataSheet.getColumns(); i++) {
                for (int j = 0; j < dataSheet.getRows(); j++) {
                    WritableCell cell = dataSheet.getWritableCell(i, j);
                    if (cell.getType() == CellType.LABEL) {
                        //System.out.println("LABEL");
                    } else if (cell.getType() == CellType.NUMBER) {


                        float o_obj = 0;
                        float n_obj = 0;


                        if (isNumeric(dataSheet.getCell(i, j).getContents().trim())) {

                            o_obj = Float.parseFloat(dataSheet.getCell(i, j).getContents());

                        }
                        System.out.println("dataSheet2=" + dataSheet2.getCell(i, j).getContents() + "长度=" + dataSheet2.getCell(i, j).getContents().length());
                        if (isNumeric(dataSheet2.getCell(i, j).getContents().trim())) {
                            //System.out.println("dataSheet2="+dataSheet2.getCell(i,j).getContents());
                            n_obj = Float.parseFloat(dataSheet2.getCell(i, j).getContents());

                        }

                        Number lbl = (Number) cell;
                        lbl.setValue(o_obj + n_obj);

                    }

                }
            }


            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            workbook2.close();
            workbook.close();
        }


    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("\\+?\\-?[0-9]+\\,?[0-9]*\\.?[0-9]*");

        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            System.out.println(str + " false");
            return false;
        }
        System.out.println(str + " true");
        return true;
    }


    private void makObjXls_B(String obj_file, String temp_file) {
        Workbook workbook = null;
        try {
            InputStream ins = new FileInputStream(temp_file);
            workbook = Workbook.getWorkbook(ins);
            File outFile = new File(obj_file);
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            WritableSheet dataSheet = wwb.getSheet(0);
            dataSheet.removeRow(1);

            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作完成时，关闭对象，释放占用的内存空间
            workbook.close();
        }

    }

    private void ConvergeXls_B(String obj_file, String old_file, int i_row) {

        Workbook workbook = null;
        Workbook workbook2 = null;
        try {
            InputStream ins = new FileInputStream(obj_file);
            workbook = Workbook.getWorkbook(ins);

            InputStream ins_2 = new FileInputStream(old_file);
            workbook2 = Workbook.getWorkbook(ins_2);
            Sheet dataSheet2 = workbook2.getSheet(0);

            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            File outFile = new File(obj_file);

            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            WritableSheet dataSheet = wwb.getSheet(0);
            // 读取第一张工作表
            Sheet[] sheet = workbook.getSheets();
            Cell[] cell = dataSheet2.getRow(1);

            for (int i = 0; i < cell.length; i++) {
                //dataSheet.insertRow(i_row);
                //WritableCell w_cell = dataSheet.getWritableCell(cell[i].getColumn(),dataSheet.getRows()-1);
                if (cell[i].getType() == CellType.NUMBER) {
                    // 数字单元格修改
                    Number lbl = new Number(cell[i].getColumn(), i_row, 1);
                    lbl.setValue(Float.parseFloat(cell[i].getContents()));
                    dataSheet.addCell(lbl);
                } else if (cell[i].getType() == CellType.LABEL) {

                    Label lbl = new Label(cell[i].getColumn(), i_row, "1");

                    lbl.setString(String.valueOf(cell[i].getContents()));
                    dataSheet.addCell(lbl);
                }


            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作完成时，关闭对象，释放占用的内存空间
            workbook2.close();
            workbook.close();
        }

    }

    private void sum_x(String obj_file) {
        Workbook workbook = null;
        try {
            InputStream ins = new FileInputStream(obj_file);
            workbook = Workbook.getWorkbook(ins);

            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            File outFile = new File(obj_file);

            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            WritableSheet dataSheet = wwb.getSheet(0);
            int col = dataSheet.getColumns();
            int row = dataSheet.getRows();
            for (int i = 0; i < row; i++) {

                float sum_row = 0;

                //Cell[] cell=dataSheet.getColumn(i);


                for (int j = 0; j < col - 1; j++) {
                    Cell cell = dataSheet.getCell(j, i);

                    System.out.println(j + "   " + i);
                    System.out.println(dataSheet.getCell(j, i).getContents());
                    if (cell.getType() == CellType.NUMBER) {

                        if (isNumeric(dataSheet.getCell(j, i).getContents())) {
                            sum_row = Float.parseFloat(dataSheet.getCell(j, i).getContents()) + sum_row;
                        }

                    }
                }
                if (i != 0) {
                    Number lbl = new Number(col, i, sum_row);
                    dataSheet.addCell(lbl);
                } else {
                    Label lbl = new Label(col, i, "总计");
                    dataSheet.addCell(lbl);
                }

            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作完成时，关闭对象，释放占用的内存空间

            workbook.close();
        }
    }

    private void sum_y(String obj_file) {
        Workbook workbook = null;
        try {
            InputStream ins = new FileInputStream(obj_file);
            workbook = Workbook.getWorkbook(ins);

            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            File outFile = new File(obj_file);

            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            WritableSheet dataSheet = wwb.getSheet(0);
            int col = dataSheet.getColumns();
            int row = dataSheet.getRows();
            for (int j = 0; j < col; j++) {

                float sum_row = 0;

                //Cell[] cell=dataSheet.getColumn(i);


                for (int i = 0; i < row; i++) {
                    Cell cell = dataSheet.getCell(j, i);

                    System.out.println(j + "   " + i);
                    System.out.println(dataSheet.getCell(j, i).getContents());
                    if (cell.getType() == CellType.NUMBER) {

                        if (isNumeric(dataSheet.getCell(j, i).getContents())) {
                            sum_row = Float.parseFloat(dataSheet.getCell(j, i).getContents()) + sum_row;
                        }

                    }
                }
                if (j != 0) {
                    Number lbl = new Number(j, row, sum_row);
                    dataSheet.addCell(lbl);
                } else {
                    Label lbl = new Label(j, row, "总计");
                    dataSheet.addCell(lbl);
                }

            }
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 操作完成时，关闭对象，释放占用的内存空间

            workbook.close();
        }
    }


}
