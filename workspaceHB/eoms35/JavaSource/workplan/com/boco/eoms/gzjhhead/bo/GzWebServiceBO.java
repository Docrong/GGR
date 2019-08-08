package com.boco.eoms.gzjhhead.bo;

/**
 * <p>Title: �ӿ�ҵ����</p>
 * <p>Description:������ҵ�ƻ�ģ��webservice�ӿ�ҵ�� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;

import com.boco.eoms.gzjhhead.interfaces.*;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import java.io.*;

import com.boco.eoms.gzjhhead.util.*;

public class GzWebServiceBO {

    private URL url = null;
    private String newFileName = "";
    private String newAddr = "";
    private static boolean flag = true;


    /**
     * �ò�������Aϵͳ��Bϵͳ�ϱ���/����ҵ�ƻ��Լ���ͬ���ڵ���ҵ�ƻ�ִ�����(reportType=0)��<br>
     * ���У�������Ϣ������SOAP��Ϣ���д��䣬������Bϵͳ��������Ϣ�е�����������л�ȡ��<br>
     * ͨ���ļ������ּƻ�/ִ�У��Լ���ͬ���ȵ�ִ��������ò���ͬʱ����Aϵͳ�յ�Bϵͳ�Ĳ���Ҫ���<br>
     * ��Bϵͳ�����������������ڵ���ҵ�ƻ�/ִ�����(reportType=1)��AϵͳҲ�����д�����������<br>
     * ������Bϵͳ���в������������У�Aϵͳֻ��Ϊʡ��ϵͳ��Bϵͳֻ��Ϊ�ܲ�ϵͳ
     *
     * @param _codeA              String ������÷�(ʡ��)����
     * @param _codeB              String �����ṩ��(ʡ��)����
     * @param _attNum             int �������ĸ���������
     * @param _attachInfoListType AttachInfoListType ��������
     * @param _reportFlag         int �ϴ���־
     * @param _noteReportForm     String �ϱ�/����ʱ����˵��
     * @return String Ϊ��(Nil)��ʾ���óɹ����ǿձ�ʾ����ʧ�ܣ�����SOAP FaultԪ�أ�����������Ӧ������롣
     */
    public String reportForm(String _codeA, String _codeB, int _attNum,
                             AttachInfoListType _attachInfoListType, int _reportFlag,
                             String _noteReportForm) {
        String resultIsAlive = null; //���巵�ؽ��
        Hashtable fileHash = null;

//    GzPlanYearBO gzPlanYearBO = new GzPlanYearBO();
//    GzPlanMonthBO gzPlanMonthBO = new GzPlanMonthBO();
//    GzPlanExecuteBO gzPlanExecuteBO = new GzPlanExecuteBO();
        if (flag) {

            AttachInfoType[] attachInfoType = _attachInfoListType.getAttachInfo(); //��ȡ����������Ϣ


            //�ж��Ƿ��и���
            if (attachInfoType.length == 0) {
                resultIsAlive = "002";
                return resultIsAlive;
            }

            //�жϸ������ݵ�������Ԥ֪�����Ƿ���ͬ
            if (attachInfoType.length != _attNum) {
                resultIsAlive = "003";
                return resultIsAlive;
            }

            String localFilePath = null; //�������ش��·��
            String attachURL = null; //�������·��,�����ļ�����

            String itemid = null;

            for (int i = 0; i < attachInfoType.length; i++) {

                fileHash = GzBaseInfo.analyseFileName(attachInfoType[i].getAttachName()); //��ȡһ��������Ϣ����

                if (fileHash == null) {
                    resultIsAlive = "004";
                } else {
                    //��֯�ܲ�ϵͳĿ¼�������ϴ���ʡ�ݺ����ڲ�ͬ���ֱ𴴽���ͬ���ļ���
                    localFilePath = ".." + File.separator + "EOMS_J2EE" + File.separator + "gzjhreport" + File.separator + fileHash.get(GzBaseInfo.FILE_PROVINCE) +
                            File.separator + fileHash.get(GzBaseInfo.FILE_YEAR) +
                            fileHash.get(GzBaseInfo.FILE_MONTH) +
                            fileHash.get(GzBaseInfo.FILE_DAY);

                    //���ظ������ܲ���������
                    attachURL = this.fileDownload(attachInfoType[i].getAttachURL(),
                            localFilePath);

                    if (attachURL == null) {
                        resultIsAlive = "004";
                    } else {
                        //�����ļ����ͣ���ת����ͬ�Ĵ����߼���
                        itemid = (String) fileHash.get(GzBaseInfo.FILE_ITEMID);

                        if (itemid.equals("001")) {
//              gzPlanYearBO.reportPlanYear(attachURL,attachInfoType[i].getAttachName()); //�����ҵ�ƻ��ϱ�
                        } else if (itemid.equals("002")) {
//              gzPlanMonthBO.reportPlanMonth(attachURL,attachInfoType[i].getAttachName()); //�¶���ҵ�ƻ��ϱ�
                        } else {
//              gzPlanExecuteBO.reportPlanExecute(attachURL,attachInfoType[i].getAttachName()); //ִ����ҵ�ƻ��ϱ�
                        }
                    }
                }
            }
        } else {
            resultIsAlive = "002";
        }

        return resultIsAlive;
    }

    public String fileDownload(String strRemoteAddr, String strLocalAddr) {
        String filePath = null;
        try {

            //��ʼ��������Ϣ
            url = new URL(strRemoteAddr);
            newFileName = (new File(url.getFile())).getName();
            newAddr = strLocalAddr;

            URLConnection urlconnection = url.openConnection();
            urlconnection.setUseCaches(false);
            InputStream is = urlconnection.getInputStream();
            long filelength = urlconnection.getContentLength(); //�����Ĵ�С
            int ratio = 0;

            //��ʼ���ظ���
            if (is != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffers = new byte[4096];
                int len = 0;
                long received = 0;
                boolean isContinue = true;
                while (isContinue) {
                    len = is.read(buffers, 0, 4096);
                    if (len > 0) {
                        baos.write(buffers, 0, len);
                        received += len;
                        if (ratio != (int) (received * 100 / filelength)) {
                            ratio = (int) (received * 100 / filelength);
                            System.out.print(ratio + "%");
                        }
                    } else {
                        break;
                    }
                } //if


                //���渽��
                File newFile = new File(newAddr);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }

                if (received != 0 && received == filelength) {

                    FileOutputStream fos = new FileOutputStream(newFile + File.separator +
                            newFileName);
                    baos.writeTo(fos);
                    fos.close();

                    System.out.println("file tranfering finished!! and be saved in " +
                            newAddr +
                            File.separator + newFileName);
                }
                filePath = newAddr + File.separator + newFileName;
            } else {
                filePath = null;
            }
        } catch (MalformedURLException expt) {
            expt.printStackTrace();
            filePath = null;
        } catch (IOException eio) {
            eio.printStackTrace();
            filePath = null;
        }

        return filePath;
    }

    /**
     * �ò�������Aϵͳ�ڵ���Bϵͳ��Ӧ����ģ������������֮ǰ��<br>
     * �ж�Bϵͳ�����Ƿ��Ѿ�������ϣ���ȷ�������õķ�����ǰ��<br>
     * ����������������������У�Aϵͳֻ��Ϊʡ��ϵͳ��Bϵͳֻ��Ϊ�ܲ�ϵͳ��
     *
     * @param _codeA String ������÷�(ʡ��)����
     * @param _codeB String �����ṩ��(ʡ��)����
     * @return String Ϊ��(Nil)��ʾ���óɹ����ǿձ�ʾ����ʧ�ܣ�����SOAP FaultԪ�أ�����������Ӧ������롣
     */
    public String isAlive(String _codeA, String _codeB) {
        String resultIsAlive = null;

        System.out.println("ʡ�ݣ�" + _codeA + "������������");

        return resultIsAlive;
    }

//  public void run() {
//    this.reportForm(this.codeA, this.codeB, this.attNum, this.attchInfoList,
//                    this.noteReportForm);
//  }


}
