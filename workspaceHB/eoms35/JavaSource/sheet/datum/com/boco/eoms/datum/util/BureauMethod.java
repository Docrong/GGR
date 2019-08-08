package com.boco.eoms.datum.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataBasicDaoHibernate;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataCityzoneHibernate;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataHlrDaoHibernate;
import com.boco.eoms.datum.model.TawBureaudataBasic;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;

import java.io.*;

import com.boco.RW_Excel.excel.Workbook;
import com.boco.RW_Excel.excel.Sheet;
import com.boco.RW_Excel.excel.Cell;
import com.boco.RW_Excel.excel.read.biff.BiffException;

import java.util.*;

public class BureauMethod {
    static PropertyFile PROP = PropertyFile.getInstance();

    public BureauMethod() {
    }

    /**
     * Added by Matao 2007-11-09
     * ��������������ƣ��������Ϊ500
     *
     * @param ips     InputStream
     * @param sheetId int
     * @return String
     */
    public String isStandard(InputStream ips, int sheetId) {
        String resultStr = "";
        if (ips != null) {
            int maxListSize = 50000;
            Workbook workBook = null;
            Cell[] row = null;
            try {
                workBook = Workbook.getWorkbook(ips);
                int sheetNum = 0; //Ĭ��ֻ�����һ��������
                Sheet sheet = workBook.getSheet(sheetNum);
                int size = sheet.getRows();
                System.out.println("size:" + size);
                if (sheetId == 1) { //δȷ��HLR�����ĺŶ�
                    size -= 3;
                    row = sheet.getRow(2);
                    if (!(row[3].getContents().matches("\\d\\d\\d\\d"))) {
                        resultStr = "��δȷ��HLR�����ĺŶΡ�ģ���ļ��и�ʽ����";
                    }
                } else if (sheetId == 2) { //���������
                    row = sheet.getRow(2);
                    size -= 3;
                    if ((row[4].getContents().matches("\\d\\d\\d\\d")) || !(row[5].getContents().matches("\\d\\d\\d\\d"))) {
                        resultStr = "�����������ģ���ļ��и�ʽ����";
                    }
                } else if (sheetId == 3) { //����HLR�ͺŶεĶ�Ӧ
                    row = sheet.getRow(2);
                    size -= 2;
                    if (row.length < 8 || row[1].getContents().trim().equals("") || row[2].getContents().trim().equals("") || row[5].getContents().trim().equals("") || row[7].getContents().trim().equals("")) {
                        resultStr = "������HLR�ͺŶεĶ�Ӧ��ϵ��ģ���ļ��и�ʽ����";
                    }
                } else if (sheetId == 4) { //HLR�б�
                    row = sheet.getRow(0);
                    size -= 1;
                    if (row.length != 3) {
                        resultStr = "��HLR�б�ģ���ļ��и�ʽ����";
                    }
                }
                if (size > maxListSize) {
                    resultStr = "ģ���ļ��м�¼���������ܴ���" + maxListSize + "���������" + maxListSize + "�����������!";
                }
            } catch (Exception ex) {
                resultStr = "���ϴ����ļ��޷���ȷ��ȡ�������Ƿ���Excelģ���ļ�!";
                ex.printStackTrace();
            }
        }
        return resultStr;
    }


    /**
     * Added by Matao 2007-11-09
     * �������Ƿ���ȷʱ���Ȱ����еĳ�����ϢPO�Գ�������ΪKey���뵽Map�У�Ȼ���ó������Ŵ�Map��ȡ��������ϢPO
     *
     * @param ips      InputStream
     * @param bureauno String
     * @return List ����ڶ�ȡһ��˳���Ͱѷ������������ݷ��뵽List��
     * @throws Exception ����������Ų�ƥ����������ݲ�׼ȷ������ͻ��׳��쳣
     */
    public List getNobelonghlr(InputStream ips, String bureauId, Set citySetOutput) throws Exception {
        ArrayList list = new ArrayList();
        TawBureaudataCityzone cityPO = null;
        String cellcontents[] = null;
        bureauId = bureauId + ";";
        if (citySetOutput == null) {
            citySetOutput = new HashSet();
        }
        if (ips != null) { //�ļ�����
            Workbook workBook = Workbook.getWorkbook(ips);
            int sheetNum = 0; //Ĭ��ֻ�����һ��������
            Sheet sheet = workBook.getSheet(sheetNum);
            int rows = sheet.getRows(); //������
            Cell[] celltitle = null;
            Cell[] cell = null;
            int endcol = 0; //��ͷ�з������е�λ��
            //��ʼ��ʼ�����ͷ,ȡ����ͷ�ĵ�һ���������е��к�
            celltitle = sheet.getRow(2);
            for (int i = 3; i < celltitle.length; i++) {
                if (!(celltitle[i].getContents()).matches("\\d\\d\\d\\d")) {
                    break;
                }
                endcol = i;
            }
            ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataCityzoneManager");
            ITawBureaudataBasicDAOManager base = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataBasicDAOManager");
            Map cityMap = mgr.getAllCityIntoMapKeyZoneNum();
            Set bdBasicSet = base.getAllBureaudataBasic();
            //��ʼ��������,�ӵ����п�ʼ�������ݣ��������ݵ��е���Ч��ΧΪ��1~endcol
            for (int i = 3; i < rows; i++) {
                cell = sheet.getRow(i); //
                if (cell[0].getContents().indexOf("����") < 0) {
                    continue;
                }
                cityPO = (TawBureaudataCityzone) cityMap.get(new Integer(cell[2].getContents()).toString());
                citySetOutput.add(cityPO);
                if (cityPO == null) { //û���ҵ���Ӧ�ĳ���
                    String errorInfo = "��ģ���ļ��е�Ԫ��<b>" + StaticMethod.getExcelPosition(i + 1, 3) + "</b>�ĳ�������[" + cell[2].getContents() + "]�����ڣ�����";
                    throw new Exception(errorInfo);
                } else {
                    for (int j = 3; j <= endcol; j++) {
                        if (!cell[j].getContents().equals("")) {
                            //ȡ����ʼ�ͽ����ĺŶ�
                            cellcontents = (cell[j].getContents()).split(",");
                            for (int k = 0; k < cellcontents.length; k++) {
                                //�����磺767-779����ϸ��
                                String[] strArrTmp = cellcontents[k].split("-");
                                int beginSegment = 0;
                                int endSegment = 0;
                                try {
                                    beginSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[0]);
                                    endSegment = 0;
                                    if (strArrTmp.length < 2) {
                                        endSegment = beginSegment;
                                    } else {
                                        endSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[1]);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    String errorInfo = "��ģ���ļ��е�Ԫ��<b>" + StaticMethod.getExcelPosition(i + 1, j + 1) + "</b>������[" + cell[j].getContents() + "]��������";
                                    throw new Exception(errorInfo);
                                }
                                if (beginSegment > endSegment) {
                                    int dataTmp = beginSegment;
                                    beginSegment = endSegment;
                                    endSegment = dataTmp;
                                }
                                for (int segTmp = beginSegment; segTmp <= endSegment; segTmp++) {
                                    if (bdBasicSet.contains(new Integer(segTmp))) {
                                        String errorInfo = "�Ŷ�ֵ:<b>" + segTmp + "</b>�Ѵ���,��ģ���ļ��е�Ԫ��<b>" + StaticMethod.getExcelPosition(i + 1, j + 1) + "</b>������[" + cell[j].getContents() + "]�ظ�������!";
                                        throw new Exception(errorInfo);
                                    }
                                    TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
                                    nobelongPO.setPrezonenum(new Integer(cityPO.getZonenum()));
                                    nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
                                    nobelongPO.setBelongflag(new Integer(0));
                                    nobelongPO.setSegmentid(new Integer(segTmp));
                                    nobelongPO.setNewbureauid(bureauId);
                                    list.add(nobelongPO);
                                }
                            }
                        }
                    }
                }
            }
            workBook.close();
        }
        return list;
    }

    /**
     * Added by Matao 2007-11-09
     * �ӡ�BOSS�ʻ�����ʡ�ڵ���������.xls���͡��������ʻ�����ʡ�ڵ���������.xls���ļ���ȡ�����ݷŵ�List��
     * �����������Ƿ���ȷʱ���Ȱ����еĳ�����ϢPO�Գ�������ΪKey���뵽Map�У�Ȼ���ó������ƴ�Map��ȡ��������ϢPO
     *
     * @param ips      InputStream
     * @param bureauno String
     * @return List ����ڶ�ȡһ��˳���Ͱѷ������������ݷ��뵽List��
     * @throws Exception ����������Ų�ƥ����������ݲ�׼ȷ������ͻ��׳��쳣
     */
    public List getApprove(InputStream ips, String bureauId, Set citySetOutput) throws Exception,
            BiffException {
        ArrayList list = new ArrayList();
        TawBureaudataCityzone preCityPO = null;
        TawBureaudataCityzone cityPO = null;
        String cellcontents[] = null;
        bureauId = bureauId + ";";
        if (citySetOutput == null) {
            citySetOutput = new HashSet();
        }
        if (ips != null) { //�ļ�����
            Workbook workBook = Workbook.getWorkbook(ips);
            int sheetNum = 0; //Ĭ��ֻ�����һ��������
            Sheet sheet = workBook.getSheet(sheetNum);
            int rows = sheet.getRows(); //������
            Cell[] celltitle = null;
            Cell[] cell = null;
            int endcol = 0; //��ͷ�з������е�λ��
            //��ʼ��ʼ�����ͷ,ȡ����ͷ�ĵ�һ���������е��к�
            celltitle = sheet.getRow(2);
            for (int i = 5; i < celltitle.length; i++) {
                if (!(celltitle[i].getContents()).matches("\\d\\d\\d\\d")) {
                    break;
                }
                endcol = i;
            }
            ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataCityzoneManager");
            Map cityMap = mgr.getAllCityIntoMapKeyZoneNum();
            //��ʼ��������,�ӵ����п�ʼ�������ݣ��������ݵ��е���Ч��ΧΪ��1~endcol
            for (int i = 3; i < rows; i++) {
                cell = sheet.getRow(i); //
                if (cell[0].getContents().indexOf("����") < 0) {
                    continue;
                }
                preCityPO = (TawBureaudataCityzone) cityMap.get(cell[2].getContents().toString());
                cityPO = (TawBureaudataCityzone) cityMap.get(cell[4].getContents().toString());
                citySetOutput.add(cityPO);
                if (preCityPO == null) { //û���ҵ���Ӧ�ĳ���
                    String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, 3) + "</b>�ĳ�������[" + cell[2].getContents() + "]�����ڣ�����";
                    throw new Exception(errorInfo);
                } else if (cityPO == null) { //û���ҵ���Ӧ�ĳ���
                    String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, 5) + "</b>�ĳ�������[" + cell[4].getContents() + "]�����ڣ�����";
                    throw new Exception(errorInfo);
                } else {
                    for (int j = 5; j <= endcol; j++) {
                        try {
                            if (!cell[j].getContents().equalsIgnoreCase("")) {
                                //ȡ����ʼ�ͽ����ĺŶ�
                                cellcontents = (cell[j].getContents()).split(",");
                                for (int k = 0; k < cellcontents.length; k++) {
                                    //�����磺767-779����ϸ��
                                    String[] strArrTmp = cellcontents[k].split("-");
                                    int beginSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[0]);
                                    int endSegment = 0;
                                    if (strArrTmp.length < 2) {
                                        endSegment = beginSegment;
                                    } else {
                                        endSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[1]);
                                    }
                                    for (int segTmp = beginSegment; segTmp <= endSegment; segTmp++) {
                                        TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
                                        nobelongPO.setPrezonenum(new Integer(cityPO.getZonenum()));
                                        nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
                                        nobelongPO.setBelongflag(new Integer(0));
                                        nobelongPO.setSegmentid(new Integer(segTmp));
                                        nobelongPO.setNewbureauid(bureauId);
                                        list.add(nobelongPO);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, j + 1) + "</b>������[" + cell[j].getContents() + "]��������";
                            throw new Exception(errorInfo);
                        }
                    }
                }
            }
            workBook.close();
        }
        return list;
    }

    /**
     * Added by Matao 2007-11-09
     * �ӡ�ȫʡ�Ŷι������ܱ�.xls���ļ���ȡ�����ݷŵ�List��
     * �����������Ƿ���ȷʱ���Ȱ����еĳ�����ϢPO�Գ�������ΪKey���뵽Map�У�Ȼ���ó������ƴ�Map��ȡ��������ϢPO
     *
     * @param ips      InputStream
     * @param bureauno String
     * @return List ����ڶ�ȡһ��˳���Ͱѷ������������ݷ��뵽List��
     * @throws Exception ����������Ų�ƥ����������ݲ�׼ȷ������ͻ��׳��쳣
     */
    public List getBureaudataHLR(InputStream ips) throws Exception,
            BiffException {
        ArrayList list = new ArrayList();
        if (ips != null) { //�ļ�����
            Workbook workBook = Workbook.getWorkbook(ips);
            int sheetNum = 0; //Ĭ��ֻ�����һ��������
            Sheet sheet = workBook.getSheet(sheetNum);
            int rows = sheet.getRows(); //������
            Cell[] cell = null;

            ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataCityzoneManager");
            ITawBureaudataHlrDAOManager hlr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
                    .getInstance().getBean("iTawBureaudataHlrDAOManager");
            Map cityMap = mgr.getAllCityIntoMapKeyCityName();
            Map hlrMap = hlr.getAllHLRIntoMapKeySignalId();
            //��ʼ��������,�ӵ����п�ʼ�������ݣ��������ݵ��е���Ч��ΧΪ��1~endcol
            for (int i = 2; i < rows; i++) {
                cell = sheet.getRow(i); //
                String cityName = StaticMethod.null2String(cell[1].getContents());
                String hlrSignalId = StaticMethod.null2String(cell[5].getContents());
                if (cityName.equals("") || hlrSignalId.equals("")) {
                    continue;
                }
                TawBureaudataCityzone cityPO = (TawBureaudataCityzone) cityMap.get(cityName);
                TawBureaudataHlr hlrPo = (TawBureaudataHlr) hlrMap.get(hlrSignalId.replace('.', '-'));
                if (cityPO == null) { //û���ҵ���Ӧ�ĳ���
                    String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, 2) + "</b>�ĳ���[" + cityName + "]�����ڣ�����";
                    throw new Exception(errorInfo);
                } else if (hlrPo == null) { //û���ҵ���Ӧ��HLR��Ϣ
                    String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, 6) + "</b>��HLR[" + hlrSignalId + "]�����ڣ�����";
                    throw new Exception(errorInfo);
                } else {
                    try {
                        int segment = Integer.parseInt(cell[7].getContents().substring(2));
                        String remark = StaticMethod.null2String((cell.length > 8) ? cell[8].getContents() + ";" : "");

                        TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
                        nobelongPO.setSegmentid(new Integer(segment));
                        nobelongPO.setBelongflag(new Integer(1));
                        nobelongPO.setNewbureauid(remark);
                        nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
                        nobelongPO.setHlrsignalid(hlrPo.getHlrsignalid());
                        list.add(nobelongPO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String errorInfo = "��ģ���ļ���<b>" + StaticMethod.getExcelPosition(i + 1, 8) + "</b>�ĺŶ�[" + cell[7].getContents() + "]��ʽ����ȷ������";
                        throw new Exception(errorInfo);
                    }
                }
            }
            workBook.close();
        }
        return list;
    }

}
