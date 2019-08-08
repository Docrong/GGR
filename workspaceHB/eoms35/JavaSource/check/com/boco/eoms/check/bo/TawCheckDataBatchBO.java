package com.boco.eoms.check.bo;

import com.boco.eoms.check.dao.TawCheckBatchDAO;
import com.boco.eoms.check.dao.TawCheckDataDAO;
import com.boco.eoms.check.model.TawCheckDATAData;
import com.boco.eoms.check.model.TawCheckDATASCORE;
import com.boco.eoms.check.model.TawCheckModel;
import com.boco.eoms.check.model.TawCheckTransData;
import com.boco.eoms.check.model.TawCheckTransSCORE;
import com.boco.eoms.check.model.TawCheckWanggSCORE;
import com.boco.eoms.check.model.TawCheckWangySCORE;
import com.boco.eoms.check.model.TawCheckDiaoduSCORE;
import com.boco.eoms.check.model.TawCheckYunweiSCORE;
import com.boco.eoms.check.util.TawCheckSCOREMethod;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;

import jxl.CellType;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * Title: ���˹��?�뵼��
 * </p>
 * <p>
 * Description: EOMS��ϵͳ
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 *
 * @author HAO
 * @version 2.7
 */

public class TawCheckDataBatchBO extends BO {

    String filePathName = "";

    String dateTime = "";

    Date localDate = null;
    int regoinNumber = 12;
    String fileName = "";
    String zhuanye = "";
    String scoreYear;
    String scoreMonth;

    public TawCheckDataBatchBO(String pathname, String name, String zhuanye) {
        this.fileName = name;
        this.zhuanye = zhuanye;
        if (fileName.length() > 0) {
            dateTime = fileName.substring(fileName.length() - 10,
                    fileName.length() - 6)
                    + "-"
                    + fileName.substring(fileName.length() - 6,
                    fileName.length() - 4) + "-01";
        }
        this.filePathName = pathname;
    }

    public TawCheckDataBatchBO(ConnectionPool ds) {
        super(ds);
    }

    public TawCheckDataBatchBO(ConnectionPool ds, String str) {
        super(ds, str);
    }

    public Workbook getWorkbook() {
        Workbook book = null;
        try {
            // �ļ�·��
            book = Workbook.getWorkbook(new File(filePathName));
        } catch (BiffException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return book;
    }

    public Sheet getSheet(int i) {
        Sheet sheet = null;
        Workbook book = getWorkbook();
        sheet = book.getSheet(i);
        return sheet;
    }

    public Sheet getSheet(String name) {
        Sheet sheet = null;
        Workbook book = getWorkbook();
        sheet = book.getSheet(name);
        return sheet;
    }

    public String getCellValue(Cell cell) {
        String value = "";
        if (cell.getType() == CellType.NUMBER
                || cell.getType() == CellType.NUMBER_FORMULA) {
            value = Double.toString(((NumberCell) cell).getValue());
        } else {
            value = cell.getContents().trim();
        }
        if (value == null || value.equals("")) {
            value = "0";
        }
        return value;
    }

    private TawCheckModel getCheckModelByAliasName(String modelAliasName) {
        String columnNames = "";
        TawCheckModelBO modelBO = new TawCheckModelBO();
        TawCheckModel model = modelBO.getTawCheckModelByModelAliasName(modelAliasName);
        columnNames = model.getModelTarger();
        return model;
    }

    /**
     * ���봫��רҵ�Ŀ���ָ����ݣ��������
     *
     * @param sheet
     * @return
     */
    public List getTransExcPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        int startrow = model.getModelStartRow();
        int startcol = model.getModelStartCol();
        for (int i = startrow; i < regoinNumber + startrow; i++) {
            TawCheckTransData tawCheckTransData = new TawCheckTransData();
            TawCheckTransSCORE tawCheckTransSCORE = new TawCheckTransSCORE();
            Cell[] cell = sheet.getRow(i);

            String regoin = this.getCellValue(cell[1]);
            String t1 = this.getCellValue(cell[2]); // һ�ɵ�·�������
            String t2 = this.getCellValue(cell[4]);// ���ɵ�·�������
            String t3 = this.getCellValue(cell[6]); // �������·�������
            String t1_score = this.getCellValue(cell[3]); // һ�ɵ�·�������
            String t2_score = this.getCellValue(cell[5]);// ���ɵ�·�������
            String t3_score = this.getCellValue(cell[7]); // �������·�������
            String full_score = this.getCellValue(cell[8]); // �ܷ�
            tawCheckTransData.setFirst_result(localDate);
            tawCheckTransData.setCompress_date(localDate);
            tawCheckTransData.setRegion_zh(regoin);
            tawCheckTransData.setT42(Double.parseDouble(t1));
            tawCheckTransData.setT43(Double.parseDouble(t2));
            tawCheckTransData.setT45(Double.parseDouble(t3));
            tawCheckTransData.setInsert_time(new Date());
            tawCheckTransData.setNe_zh(regoin);
            tawCheckTransData.setProvince_zh(regoin);
            tawCheckTransData.setDeleted("0");

            tawCheckTransSCORE.setScore_area_name(regoin);
            tawCheckTransSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckTransSCORE.setScore_deleted("0");
            tawCheckTransSCORE.setScore_year(scoreYear);
            tawCheckTransSCORE.setScore_month(scoreMonth);
            tawCheckTransSCORE.setT42(Double.parseDouble(t1));
            tawCheckTransSCORE.setT42_score(Double.parseDouble(t1_score));
            tawCheckTransSCORE.setT42_full(Double.parseDouble("75"));
            tawCheckTransSCORE.setT43(Double.parseDouble(t2));
            tawCheckTransSCORE.setT43_score(Double.parseDouble(t2_score));
            tawCheckTransSCORE.setT43_full(Double.parseDouble("12.5"));
            tawCheckTransSCORE.setT45(Double.parseDouble(t3));
            tawCheckTransSCORE.setT45_score(Double.parseDouble(t3_score));
            tawCheckTransSCORE.setT45_full(Double.parseDouble("12.5"));
            tawCheckTransSCORE.setFull_score(Double.parseDouble(full_score));
            list.add(tawCheckTransData);
            list.add(tawCheckTransSCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckTransSCORE.class.getSimpleName(), "");
        }
        return list;
    }

    /**
     * ���봫��רҵ�Ŀ���ָ����ݣ��������
     *
     * @param sheet
     * @return
     */
    public List getYunweiTransExcPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        int startrow = model.getModelStartRow();
        int startcol = model.getModelStartCol();
        int num = startcol;
        for (int i = startrow; i < startrow + 11; i++) {
            TawCheckYunweiSCORE tawCheckYunweiSCORE = new TawCheckYunweiSCORE();
            Cell[] cell = sheet.getRow(i);

            String regoin = cell[0].getContents().trim();

            String t1 = this.getCellValue(cell[num]); // һ�ɵ�·�������
            String t2 = this.getCellValue(cell[num + 2]);// ���ɵ�·�������
            String t3 = this.getCellValue(cell[num + 4]); // �������·�������
            String t4 = "0"; // ��fָ��
            String t1_score = this.getCellValue(cell[num + 1]); // һ�ɵ�·������ʷ���
            String t2_score = this.getCellValue(cell[num + 3]);// ���ɵ�·������ʷ���
            String t3_score = this.getCellValue(cell[num + 5]); // �������·������ʷ���
            String t4_score = this.getCellValue(cell[num + 6]); // ��fָ�����
            String full_score = this.getCellValue(cell[num + 7]); // �ܷ�

            tawCheckYunweiSCORE.setScore_area_name(regoin);
            tawCheckYunweiSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckYunweiSCORE.setScore_deleted("0");
            tawCheckYunweiSCORE.setScore_year(scoreYear);
            tawCheckYunweiSCORE.setScore_month(scoreMonth);
            tawCheckYunweiSCORE.setT42(Double.parseDouble(t1));
            tawCheckYunweiSCORE.setT42_score(Double.parseDouble(t1_score));
            tawCheckYunweiSCORE.setT42_full(Double.parseDouble("10"));
            tawCheckYunweiSCORE.setT43(Double.parseDouble(t2));
            tawCheckYunweiSCORE.setT43_score(Double.parseDouble(t2_score));
            tawCheckYunweiSCORE.setT43_full(Double.parseDouble("6"));
            tawCheckYunweiSCORE.setT45(Double.parseDouble(t3));
            tawCheckYunweiSCORE.setT45_score(Double.parseDouble(t3_score));
            tawCheckYunweiSCORE.setT45_full(Double.parseDouble("4"));
            tawCheckYunweiSCORE.setT49(Double.parseDouble(t4));
            tawCheckYunweiSCORE.setT49_score(Double.parseDouble(t4_score));
            tawCheckYunweiSCORE.setT49_full(Double.parseDouble("5"));
            tawCheckYunweiSCORE.setFull_score(Double.parseDouble(full_score));
            tawCheckYunweiSCORE.setModelflag(modelAliasName);
            list.add(tawCheckYunweiSCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckYunweiSCORE.class.getSimpleName(), modelAliasName);
        }
        return list;
    }

    /**
     * �������רҵ�Ŀ���ָ����ݣ��������
     *
     * @param sheet
     * @return
     */

    public List getDATAExcPart(Sheet sheet) {
        ArrayList list = new ArrayList();
        int r = sheet.getRows();
        int c = sheet.getColumns();
        for (int i = 5; i < 5 + regoinNumber; i++) {
            TawCheckDATAData tawCheckDATAData = new TawCheckDATAData();
            TawCheckDATASCORE tawCheckDATASCORE = new TawCheckDATASCORE();
            Cell[] cell = sheet.getRow(i);
            String t1 = "0", t2 = "0", t3 = "0", t4 = "0", t5 = "0", t6 = "0", t7 = "0", t8 = "0", t9 = "0", t10 = "0", t11 = "0";
            String t1_score = "0", t2_score = "0", t3_score = "0", t4_score = "0", t5_score = "0", t6_score = "0", t7_score = "0", t8_score = "0", t9_score = "0", t10_score = "0", t11_score = "0";
            int m = 1;
            String regoin = cell[m].getContents().trim();
            m++;
            t1 = getCellValue(cell[m]);
            m++;
            t1_score = getCellValue(cell[m]);
            m++;
            t2 = getCellValue(cell[m]);
            m++;
            t2_score = getCellValue(cell[m]);
            m++;
            t3 = getCellValue(cell[m]);
            m++;
            t3_score = getCellValue(cell[m]);
            m++;
            t4 = getCellValue(cell[m]);
            m++;
            t4_score = getCellValue(cell[m]);
            m++;
            t5 = getCellValue(cell[m]);
            m++;
            t5_score = getCellValue(cell[m]);
            m++;
            t6 = getCellValue(cell[m]);
            m++;
            t6_score = getCellValue(cell[m]);
            m++;
            t7 = getCellValue(cell[m]);
            m++;
            t7_score = getCellValue(cell[m]);
            m++;
            t8 = getCellValue(cell[m]);
            m++;
            t8_score = getCellValue(cell[m]);
            m++;
            t9 = getCellValue(cell[m]);
            m++;
            t9_score = getCellValue(cell[m]);
            m++;
            t10 = getCellValue(cell[m]);
            m++;
            t10_score = getCellValue(cell[m]);
            m++;
            t11 = getCellValue(cell[m]);
            m++;
            t11_score = getCellValue(cell[m]);
            m++;
            String full_score = getCellValue(cell[m]);
            tawCheckDATAData.setFirst_result(localDate);
            tawCheckDATAData.setCompress_date(localDate);
            tawCheckDATAData.setRegion_zh(regoin);
            tawCheckDATAData.setT42(Double.parseDouble(t1));
            tawCheckDATAData.setT43(Double.parseDouble(t2));
            tawCheckDATAData.setT45(Double.parseDouble(t3));
            tawCheckDATAData.setT49(Double.parseDouble(t4));
            tawCheckDATAData.setT65(Double.parseDouble(t5));
            tawCheckDATAData.setT66(Double.parseDouble(t6));
            tawCheckDATAData.setT411(Double.parseDouble(t7));
            tawCheckDATAData.setT412(Double.parseDouble(t8));
            tawCheckDATAData.setT413(Double.parseDouble(t9));
            tawCheckDATAData.setT414(Double.parseDouble(t10));
            tawCheckDATAData.setT415(Double.parseDouble(t11));
            tawCheckDATAData.setInsert_time(new Date());
            tawCheckDATAData.setNe_zh(regoin);
            tawCheckDATAData.setProvince_zh(regoin);
            tawCheckDATAData.setDeleted("0");

            tawCheckDATASCORE.setScore_area_name(regoin);
            tawCheckDATASCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckDATASCORE.setScore_deleted("0");
            tawCheckDATASCORE.setScore_year(scoreYear);
            tawCheckDATASCORE.setScore_month(scoreMonth);
            tawCheckDATASCORE.setT42(Double.parseDouble(t1));
            tawCheckDATASCORE.setT42_score(Double.parseDouble(t1_score));
            tawCheckDATASCORE.setT42_full(Double.parseDouble("15"));
            tawCheckDATASCORE.setT43(Double.parseDouble(t2));
            tawCheckDATASCORE.setT43_score(Double.parseDouble(t2_score));
            tawCheckDATASCORE.setT43_full(Double.parseDouble("10"));
            tawCheckDATASCORE.setT45(Double.parseDouble(t3));
            tawCheckDATASCORE.setT45_score(Double.parseDouble(t3_score));
            tawCheckDATASCORE.setT45_full(Double.parseDouble("15"));

            tawCheckDATASCORE.setT49(Double.parseDouble(t4));
            tawCheckDATASCORE.setT49_score(Double.parseDouble(t4_score));
            tawCheckDATASCORE.setT49_full(Double.parseDouble("10"));
            tawCheckDATASCORE.setT65(Double.parseDouble(t5));
            tawCheckDATASCORE.setT65_score(Double.parseDouble(t5_score));
            tawCheckDATASCORE.setT65_full(Double.parseDouble("10"));
            tawCheckDATASCORE.setT66(Double.parseDouble(t6));
            tawCheckDATASCORE.setT66_score(Double.parseDouble(t6_score));
            tawCheckDATASCORE.setT66_full(Double.parseDouble("13"));
            tawCheckDATASCORE.setT411(Double.parseDouble(t7));
            tawCheckDATASCORE.setT411_score(Double.parseDouble(t7_score));
            tawCheckDATASCORE.setT411_full(Double.parseDouble("0"));
            tawCheckDATASCORE.setT412(Double.parseDouble(t8));
            tawCheckDATASCORE.setT412_score(Double.parseDouble(t8_score));
            tawCheckDATASCORE.setT412_full(Double.parseDouble("13"));
            tawCheckDATASCORE.setT413(Double.parseDouble(t9));
            tawCheckDATASCORE.setT413_score(Double.parseDouble(t9_score));
            tawCheckDATASCORE.setT413_full(Double.parseDouble("6"));
            tawCheckDATASCORE.setT414(Double.parseDouble(t10));
            tawCheckDATASCORE.setT414_score(Double.parseDouble(t10_score));
            tawCheckDATASCORE.setT414_full(Double.parseDouble("4"));
            tawCheckDATASCORE.setT415(Double.parseDouble(t11));
            tawCheckDATASCORE.setT415_score(Double.parseDouble(t11_score));
            tawCheckDATASCORE.setT415_full(Double.parseDouble("4"));
            tawCheckDATASCORE.setFull_score(Double.parseDouble(full_score));
            list.add(tawCheckDATAData);
            list.add(tawCheckDATASCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckDATASCORE.class.getSimpleName(), "");
        }
        return list;
    }

    /**
     * �������G������
     *
     * @param sheet
     * @return
     */
    public List getWanggExcPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        String targetNames[] = model.getModelTarger().split(",");
        for (int i = 5; i < 5 + regoinNumber; i++) {
            TawCheckWanggSCORE tawCheckWanggSCORE = new TawCheckWanggSCORE();
            Cell[] cell = sheet.getRow(i);
            String regoin = cell[1].getContents().trim();
            for (int j = 0; j < targetNames.length; j++) {
                try {
                    Class[] parameterTypes = new Class[1];
                    parameterTypes[0] = double.class;
                    Method method = null;
                    if (targetNames[j].equals("FULL_SCORE")) {
                        method = tawCheckWanggSCORE.getClass().getMethod("setFull_score", parameterTypes);//
                    } else {
                        method = tawCheckWanggSCORE.getClass().getMethod("set" + targetNames[j], parameterTypes);//
                    }
                    Object[] args = new Object[1];
                    String t1 = "";
                    if (j >= 3) {
                        t1 = getCellValue(cell[j + 3]);
                    } else {
                        t1 = getCellValue(cell[j + 2]);
                    }
//				args[0]  =  Double.parseDouble(t1); 
                    method.invoke(tawCheckWanggSCORE, args);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            tawCheckWanggSCORE.setScore_year(scoreYear);
            tawCheckWanggSCORE.setScore_month(scoreMonth);
            tawCheckWanggSCORE.setScore_area_name(regoin);
            tawCheckWanggSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckWanggSCORE.setScore_deleted("0");
            list.add(tawCheckWanggSCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckWanggSCORE.class.getSimpleName(), "");
        }
        return list;
    }

    /**
     * C������
     *
     * @param sheet
     * @return
     */
    public List getWangyExcPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        String targetNames[] = model.getModelTarger().split(",");
        for (int i = 5; i < 5 + regoinNumber; i++) {
            TawCheckWangySCORE tawCheckWangySCORE = new TawCheckWangySCORE();
            Cell[] cell = sheet.getRow(i);
            String regoin = cell[1].getContents().trim();
            for (int j = 0; j < targetNames.length; j++) {
                try {
                    Class[] parameterTypes = new Class[1];
                    parameterTypes[0] = double.class;
                    Method method = null;
                    if (targetNames[j].equals("FULL_SCORE")) {
                        method = tawCheckWangySCORE.getClass().getMethod("setFull_score", parameterTypes);//
                    } else {
                        method = tawCheckWangySCORE.getClass().getMethod("set" + targetNames[j], parameterTypes);//
                    }
                    Object[] args = new Object[1];
                    String t1 = "";
                    if (j >= 3) {
                        t1 = getCellValue(cell[j + 3]);
                    } else {
                        t1 = getCellValue(cell[j + 2]);
                    }
//				args[0]  =  Double.parseDouble(t1); 
                    method.invoke(tawCheckWangySCORE, args);
                } catch (Exception ex) {

                }
            }
            tawCheckWangySCORE.setScore_year(scoreYear);
            tawCheckWangySCORE.setScore_month(scoreMonth);
            tawCheckWangySCORE.setScore_area_name(regoin);
            tawCheckWangySCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckWangySCORE.setScore_deleted("0");
            list.add(tawCheckWangySCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckWangySCORE.class.getSimpleName(), "");
        }
        return list;
    }

    /**
     * ���е��
     *
     * @param sheet
     * @param modelAliasName
     * @return
     */
    public List getDiaoduPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        String targetNames[] = model.getModelTarger().split(",");
        for (int i = 5; i < 5 + regoinNumber; i++) {
            TawCheckDiaoduSCORE tawCheckDiaoduSCORE = new TawCheckDiaoduSCORE();
            Cell[] cell = sheet.getRow(i);
            String regoin = cell[1].getContents().trim();
            for (int j = 0; j < targetNames.length; j++) {
                try {
                    Class[] parameterTypes = new Class[1];
                    parameterTypes[0] = double.class;
                    Method method = null;
                    if (targetNames[j].equals("FULL_SCORE")) {
                        method = tawCheckDiaoduSCORE.getClass().getMethod("setFull_score", parameterTypes);//
                    } else {
                        method = tawCheckDiaoduSCORE.getClass().getMethod("set" + targetNames[j], parameterTypes);//
                    }
                    Object[] args = new Object[1];
                    String t1 = "";
                    t1 = getCellValue(cell[j + 2]);
//				args[0]  =  Double.parseDouble(t1); 
                    method.invoke(tawCheckDiaoduSCORE, args);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            tawCheckDiaoduSCORE.setScore_year(scoreYear);
            tawCheckDiaoduSCORE.setScore_month(scoreMonth);
            tawCheckDiaoduSCORE.setScore_area_name(regoin);
            tawCheckDiaoduSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckDiaoduSCORE.setScore_deleted("0");
            list.add(tawCheckDiaoduSCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckDiaoduSCORE.class.getSimpleName(), "");
        }
        return list;
    }

    public List getYunweiPart(Sheet sheet, String modelAliasName) {
        ArrayList list = new ArrayList();
        int r = sheet.getRows();
        int c = sheet.getColumns();
        TawCheckModel model = this.getCheckModelByAliasName(modelAliasName);
        String targetNames[] = model.getModelTarger().split(",");
        String modelFullScores[] = StaticMethod.nullObject2String(model.getModelFullScore()).split(",");
        String typeFlag = model.getTypeFlag();
        int startrow = model.getModelStartRow();
        int startcol = model.getModelStartCol();
        int modeltype = model.getTargerModelType();
        int number = 11;
        if (modelAliasName.equals("�Ӽ��") || modelAliasName.equals("G��") || modelAliasName.equals("C��") || modelAliasName.equals("�����") || modelAliasName.equals("�����豸") || modelAliasName.equals("��վ��վ") || modelAliasName.equals("���е��") || modelAliasName.equals("����ά����") || modelAliasName.equals("����")) {
            number = 12;
        }

        for (int i = startrow; i < startrow + number; i++) {
            TawCheckYunweiSCORE tawCheckYunweiSCORE = new TawCheckYunweiSCORE();
            Cell[] cell = sheet.getRow(i);
            String regoin = cell[startcol].getContents().trim();
            int currentnum = startcol;
            int sm = 0;
            for (int j = 0; j < targetNames.length; j++) {
                try {
                    Class[] parameterTypes = new Class[1];
                    parameterTypes[0] = double.class;
                    Method method = null;
                    if (targetNames[j].equals("FULL_SCORE")) {
                        method = tawCheckYunweiSCORE.getClass().getMethod("setFull_score", parameterTypes);//
                    } else {
                        method = tawCheckYunweiSCORE.getClass().getMethod("set" + targetNames[j], parameterTypes);//
                    }
                    currentnum = currentnum + 1;
                    Object[] args = new Object[1];
                    String t1 = getCellValue(cell[currentnum]);
//				args[0]  =  Double.parseDouble(t1); 
                    method.invoke(tawCheckYunweiSCORE, args);
                    if (modeltype == 1 && !targetNames[j].equals("FULL_SCORE") && (typeFlag == null || typeFlag.indexOf(targetNames[j]) == -1)) {
                        method = tawCheckYunweiSCORE.getClass().getMethod("set" + targetNames[j] + "_score", parameterTypes);//
                        args = new Object[1];
                        currentnum = currentnum + 1;
                        t1 = getCellValue(cell[currentnum]);
//					args[0]  =  Double.parseDouble(t1); 
                        method.invoke(tawCheckYunweiSCORE, args);
                        //�������
                        method = tawCheckYunweiSCORE.getClass().getMethod("set" + targetNames[j] + "_full", parameterTypes);//
                        args = new Object[1];
                        t1 = modelFullScores[sm++];
//					args[0]  =  Double.parseDouble(t1); 
                        method.invoke(tawCheckYunweiSCORE, args);

                    }
                } catch (Exception ex) {

                }
            }
            tawCheckYunweiSCORE.setScore_year(scoreYear);
            tawCheckYunweiSCORE.setScore_month(scoreMonth);
            tawCheckYunweiSCORE.setScore_area_name(regoin);
            tawCheckYunweiSCORE.setScore_area_id(TawCheckSCOREMethod.getareaId(regoin));
            tawCheckYunweiSCORE.setModelflag(modelAliasName);
            tawCheckYunweiSCORE.setScore_deleted("0");
            list.add(tawCheckYunweiSCORE);
        }
        if (list.size() > 0) {
            TawCheckDataDAO tawCheckDataDAO = new TawCheckDataDAO();
            tawCheckDataDAO.delScoreData(scoreYear, scoreMonth, TawCheckYunweiSCORE.class.getSimpleName(), modelAliasName);
        }
        return list;
    }

    // ��EXCEL�ļ��ж�ȡ����רҵ�������
    public List getExcPart(String[] names) {
        ArrayList list = new ArrayList();
        List nameList = new ArrayList();
        for (int i = 0; i < names.length; i++) {
            nameList.add(names[i]);
        }
        for (int ii = 0; ii < names.length; ii++) {
            Sheet sheet = getSheet(names[ii]);
            String sheetName = sheet.getName();
            if (sheetName.equals("����ָ�꿼��") && nameList.contains("����ָ�꿼��")) {
                list.addAll(getYunweiTransExcPart(sheet, sheetName));
            } else if (sheetName.equals("������") && nameList.contains("������")) {
                list.addAll(getTransExcPart(sheet, sheetName));
                //}else if (sheetName.equals("�����")&&nameList.contains("�����")) {
                //	list.addAll(getDATAExcPart(sheet));
            } else if (sheetName.equals("G����") && nameList.contains("G����")) {
                list.addAll(getWanggExcPart(sheet, sheetName));
            } else if (sheetName.equals("C����") && nameList.contains("C����")) {
                list.addAll(getWangyExcPart(sheet, sheetName));
                //}else if (sheetName.equals("���е��")&&nameList.contains("���е��")) {
                //	list.addAll(getDiaoduPart(sheet,sheetName));
            } else if (nameList.contains(sheetName)) {
                list.addAll(getYunweiPart(sheet, sheetName));
            }
        }
		/*for (int ii = 0; ii < 10; ii++) {
			Sheet sheet = getSheet(ii);
			String sheetName = sheet.getName();
			if (sheetName.equals("������")&&nameList.contains("������")) {
				list.addAll(getTransExcPart(sheet));
			}else if (sheetName.equals("�����")&&nameList.contains("�����")) {
				list.addAll(getDATAExcPart(sheet));
			}else if (sheetName.equals("G����")&&nameList.contains("G����")) {
				list.addAll(getWanggExcPart(sheet,sheetName));
			}else if (sheetName.equals("C����")&&nameList.contains("C����")) {
				list.addAll(getWangyExcPart(sheet,sheetName));
			}else if (sheetName.equals("���е��")&&nameList.contains("���е��")) {
				list.addAll(getDiaoduPart(sheet,sheetName));
			}else if (nameList.contains(sheetName)){
				list.addAll(getYunweiPart(sheet,sheetName));
			}
		}*/
        return list;
    }

    public String[] getExcSheetNames(String zhuanye) {
        Vector vector = new Vector();
        String names[] = null;
        Workbook book = getWorkbook();
        Sheet sheets[] = book.getSheets();
        for (int ii = 0; ii < sheets.length; ii++) {
            Sheet sheet = sheets[ii];
            String sheetName = sheet.getName();
            vector.add(sheetName);
        }
        names = (String[]) vector.toArray(new String[0]);
        return (new TawCheckModelBO()).getAllExcelSheetNames(names, zhuanye);
    }

    // �������
    public boolean importData(String[] names) {
        boolean flag = true;
        TawCheckDataDAO dao = new TawCheckDataDAO();
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            localDate = f.parse(dateTime);
            scoreYear = dateTime.substring(0, 4);
            scoreMonth = dateTime.substring(5, 7);
            System.out.println(localDate.toLocaleString());
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }
        TawCheckBatchDAO tawCheckBatchDAO = new TawCheckBatchDAO(ds);
        tawCheckBatchDAO.save(scoreYear, scoreMonth, fileName, zhuanye);
        List batchList = getExcPart(names);
        /*
         * Date dt = new Date(); SimpleDateFormat smpDateFormat = new
         * SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); String sTime =
         * smpDateFormat.format(dt);
         */
        if (batchList != null) {
            for (int i = 0; i < batchList.size(); i++) {
                Object obj = batchList.get(i);
                // if(obj instanceof TawCheckTransData)
                // TawCheckTransData tawCheckTransData = (TawCheckTransData)
                // obj;
                try {
                    dao.save(obj);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    flag = false;
                }
            }

        }

        return flag;
    }

    public void setYearMonth(String year, String month) {
        if (month.length() < 2) {
            month = "0" + month;
        }
        dateTime = year
                + "-"
                + month + "-01";
    }

    public List getSumdefines(String typeId) {
        TawCheckBatchDAO tawCheckBatchDAO = new TawCheckBatchDAO(ds);
        return tawCheckBatchDAO.getSumDefine(typeId);
    }

    public void save(Object obj) {
        TawCheckDataDAO dao = new TawCheckDataDAO();
        try {
            dao.save(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
