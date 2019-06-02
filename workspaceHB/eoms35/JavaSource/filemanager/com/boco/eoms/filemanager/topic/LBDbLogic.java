package com.boco.eoms.filemanager.topic;

/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-9-1
 * Time: 16:29:30
 * To change this template use Options | File Templates.
 */

import com.boco.eoms.common.util.StaticMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public abstract class LBDbLogic {

    //�ж��Ƿ�Ϊ��ҪsetSql()
    public boolean bFalg = false;
    //���м�¼����
    public static final String MACRO_MAX_NUM = "MACRO_MAX_NUM";
    private String m_strMaxNum = "";


    //��ǰҳ��
    public static final String MACRO_CURRENT_PAGE_NUM = "MACRO_CURRENT_PAGE_NUM";
    private String m_strCurrentPageNum = "";

    //��ҳ������
    public static final String MACRO_SUM_PAGE_NUM = "MACRO_SUM_PAGE_NUM";
    private String m_strSumPageNum = "";

    //�Ƿ�Ϊ��һҳ
    public static final String MACRO_IS_FIRST_PAGE = "MACRO_IS_FIRST_PAGE";

    //�Ƿ�Ϊ���һҳ
    public static final String MACRO_IS_LAST_PAGE = "MACRO_IS_LAST_PAGE";

    //�ɹ�
    public static final int MACRO_SUCCESS = 1;
    //ʧ��
    public static final int MACRO_ERROR = -1;
    //ҪҪִ�е�SQL��
    protected String m_strSql = "";
    //����hashmap��key
    protected String[] m_strArrayReturn = null;

    //���ص���ݼ���
    private HashMap hmRetrun = new HashMap();
    Connection m_con = null;

    public LBDbLogic() {
        super();
    }

    public LBDbLogic(Connection con) {
        m_con = con;
    }

    //�õ�SQL��
    public abstract void setSql();

    //�õ�SQL�ĵĲ���
    public abstract void setReturnParam();


    //����Ҫ��ҳ
    public int executeProcess(String[] strParamArray) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {

            setSql();
            setReturnParam();
            //��ѯ
            PreparedStatement pst = m_con.prepareStatement(m_strSql);

            //System.out.println("SQL = "+m_strSql);

            if (strParamArray != null && strParamArray.length > 0) {
                for (int i = 0; i < strParamArray.length; i++) {
                    pst.setString(i + 1, strParamArray[i]);
                    //System.out.println("param["+i+"] = "+strParamArray[i]);
                }
            }

            ResultSet rs = pst.executeQuery();

            Vector vAllElement = new Vector();
            while (rs.next()) {
                for (int i = 0; i < m_strArrayReturn.length; i++) {
                    vAllElement.addElement(rs.getString(i + 1));
                }
            }
            pst.close();
            rs.close();
            ////System.out.println("vAllElement="+vAllElement);
//�ֽ������
            for (int i = 0; i < m_strArrayReturn.length; i++) {
                Vector vTemp = new Vector();
                for (int j = i; j < vAllElement.size(); j = j + m_strArrayReturn.length) {
                    vTemp.addElement((String) vAllElement.get(j));
                }
                ////System.out.println("m_strArrayReturn["+i+"="+m_strArrayReturn[i]);
                ////System.out.println("vTemp="+vTemp);
                hmRetrun.put(m_strArrayReturn[i], vTemp);
            }

            iReturn = MACRO_SUCCESS;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return iReturn;
    }

    /**
     * ��Ҫ��ҳ
     *
     * @param strParamArray ��ѯ����
     * @param strCurentPage ��ǰҳ
     * @param strPageNum    һҳ���м����¼ Ĭ��Ϊ ��20��
     */
    public int executeProcess(String[] strParamArray, String strCurentPage, String strPageNum) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {
            if (!bFalg) {
                setSql();
            }
            setReturnParam();
            //��ǰҳ
            m_strCurrentPageNum = strCurentPage;

            //��ѯ
            PreparedStatement pst = m_con.prepareStatement(m_strSql);

            //System.out.println("SQL = "+m_strSql);

            if (strParamArray != null && strParamArray.length > 0) {
                for (int i = 0; i < strParamArray.length; i++) {
                    pst.setString(i + 1, strParamArray[i]);
                    //System.out.println("param["+i+"] = "+strParamArray[i]);
                }
            }

            ResultSet rs = pst.executeQuery();

            Vector vAllElement = new Vector();
            while (rs.next()) {
                for (int i = 0; i < m_strArrayReturn.length; i++) {
                    vAllElement.addElement(StaticMethod.dbNull2String(rs.getString(i + 1)));
                }
            }
            pst.close();
            rs.close();

            ////System.out.println("vAllElement="+vAllElement);
//�ֽ������
            for (int i = 0; i < m_strArrayReturn.length; i++) {
                Vector vTemp = new Vector();
                for (int j = i; j < vAllElement.size(); j = j + m_strArrayReturn.length) {
                    vTemp.addElement((String) vAllElement.get(j));
                }

                ////System.out.println("m_strArrayReturn["+i+"="+m_strArrayReturn[i]);
                ////System.out.println("vTemp="+vTemp);
                //���м�¼����
                m_strMaxNum = Integer.toString(vTemp.size());
                ////System.out.println("���м�¼����="+m_strMaxNum);
                //����ҳ��
                if (strPageNum == null || strPageNum.equals("0") || strPageNum.equals("")) {
                    strPageNum = "20";
                }
                m_strSumPageNum = Integer.toString((int) (vTemp.size() / (float) Integer.parseInt(strPageNum) + 0.9));
                ////System.out.println("����ҳ��="+m_strSumPageNum);
                //���ǰҳΪ0 �� ���� ���ҳ�� ��case
                if (Integer.parseInt(m_strCurrentPageNum) == 0) {
                    m_strCurrentPageNum = "1";
                } else if (Integer.parseInt(m_strCurrentPageNum) >= Integer.parseInt(m_strSumPageNum)) {
                    m_strCurrentPageNum = m_strSumPageNum;
                }
                //�õ���ҳ�Ľ��
                Vector vResult = getDivResult(vTemp, Integer.parseInt(m_strCurrentPageNum), Integer.parseInt(strPageNum));
                ////System.out.println("�õ���ҳ�Ľ��="+vResult);
                hmRetrun.put(m_strArrayReturn[i], vResult);
            }


            if (m_strMaxNum.equals("0")) {
                m_strCurrentPageNum = "0";
                m_strSumPageNum = "0";
            }
//��ǰҳ
            hmRetrun.put(MACRO_CURRENT_PAGE_NUM, m_strCurrentPageNum);
            //�ܼ�¼����
            hmRetrun.put(MACRO_MAX_NUM, m_strMaxNum);
            //��ҳ��
            hmRetrun.put(MACRO_SUM_PAGE_NUM, m_strSumPageNum);
            //�Ƿ�Ϊ��һҳ
            if (m_strCurrentPageNum.equals("1")) {
                hmRetrun.put(MACRO_IS_FIRST_PAGE, "1");
            } else {
                hmRetrun.put(MACRO_IS_FIRST_PAGE, "0");
            }
            //�Ƿ�Ϊ���һҳ
            if (m_strCurrentPageNum.equals(m_strSumPageNum)) {
                hmRetrun.put(MACRO_IS_LAST_PAGE, "1");
            } else {
                hmRetrun.put(MACRO_IS_LAST_PAGE, "0");
            }
            iReturn = MACRO_SUCCESS;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return iReturn;
    }


    //���ز�ѯ�Ľ�� ������Ϣ
    public HashMap getResult() {
        return hmRetrun;
    }

    /**
     * �õ���ҳ�Ľ��
     *
     * @param vParam       Ҫ�ֵĶ���
     * @param nCurrentPage ��ǰҳ
     * @param nCurrentPage һҳ������
     */
    private Vector getDivResult(Vector vParam, int nCurrentPage, int nPageNum) {
        Vector vReturn = new Vector();

        String[] allData = LBCommonFunc.vector2StringArray(vParam);
        if (allData != null && allData.length > 0) {
            int firstRow = (nCurrentPage - 1) * nPageNum;
            for (int i = firstRow; i < firstRow + nPageNum; i++) {
                if (i < allData.length) {
                    vReturn.addElement(allData[i]);
                }
            }
        }
        return vReturn;
    }
}

