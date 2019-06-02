package com.boco.eoms.gzjhhead.bo;

/**
 * <p>Title: 接口业务类</p>
 * <p>Description:用于作业计划模块webservice接口业务 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
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
   * 该操作用于A系统向B系统上报月/年作业计划以及不同周期的作业计划执行情况(reportType=0)。<br>
   * 其中，附件信息放置在SOAP消息体中传输，附件由B系统按附件信息中的相关描述自行获取。<br>
   * 通过文件名区分计划/执行，以及不同粒度的执行情况。该操作同时用于A系统收到B系统的补报要求后，<br>
   * 向B系统补报所需限制条件内的作业计划/执行情况(reportType=1)。A系统也可自行触发本操作，<br>
   * 主动向B系统进行补报。本用例中，A系统只能为省级系统，B系统只能为总部系统
   * @param _codeA String 服务调用方(省份)代码
   * @param _codeB String 服务提供方(省份)代码
   * @param _attNum int 所包含的附件个数。
   * @param _attachInfoListType AttachInfoListType 附件数组
   * @param _reportFlag int 上传标志
   * @param _noteReportForm String 上报/补报时附带说明
   * @return String 为空(Nil)表示调用成功；非空表示调用失败，触发SOAP Fault元素，并返回以相应错误代码。
   */
  public String reportForm(String _codeA, String _codeB, int _attNum,
                           AttachInfoListType _attachInfoListType,int _reportFlag,
                           String _noteReportForm) {
    String resultIsAlive = null; //定义返回结果
    Hashtable fileHash = null;

//    GzPlanYearBO gzPlanYearBO = new GzPlanYearBO();
//    GzPlanMonthBO gzPlanMonthBO = new GzPlanMonthBO();
//    GzPlanExecuteBO gzPlanExecuteBO = new GzPlanExecuteBO();
    if(flag){

      AttachInfoType[] attachInfoType = _attachInfoListType.getAttachInfo(); //获取附件数组信息


      //判断是否有附件
      if (attachInfoType.length == 0) {
        resultIsAlive = "002";
        return resultIsAlive;
      }

      //判断附件传递的数量与预知数量是否相同
      if (attachInfoType.length != _attNum) {
        resultIsAlive = "003";
        return resultIsAlive;
      }

      String localFilePath = null; //附件本地存放路径
      String attachURL = null; //附件存放路径,包括文件名称

      String itemid = null;

      for (int i = 0; i < attachInfoType.length; i++) {

        fileHash = GzBaseInfo.analyseFileName(attachInfoType[i].getAttachName()); //获取一个附件信息对象

        if (fileHash == null) {
          resultIsAlive = "004";
        }
        else {
          //组织总部系统目录，根据上传的省份和日期不同，分别创建不同的文件夹
          localFilePath = ".." + File.separator + "EOMS_J2EE" + File.separator +  "gzjhreport" + File.separator + fileHash.get(GzBaseInfo.FILE_PROVINCE) +
              File.separator + fileHash.get(GzBaseInfo.FILE_YEAR) +
              fileHash.get(GzBaseInfo.FILE_MONTH) +
              fileHash.get(GzBaseInfo.FILE_DAY);

          //下载附件到总部服务器中
          attachURL = this.fileDownload(attachInfoType[i].getAttachURL(),
                                        localFilePath);

          if (attachURL == null) {
            resultIsAlive = "004";
          }
          else {
            //分析文件类型，分转到不同的处理逻辑中
            itemid = (String) fileHash.get(GzBaseInfo.FILE_ITEMID);

            if (itemid.equals("001")) {
//              gzPlanYearBO.reportPlanYear(attachURL,attachInfoType[i].getAttachName()); //年度作业计划上报
            }
            else if (itemid.equals("002")) {
//              gzPlanMonthBO.reportPlanMonth(attachURL,attachInfoType[i].getAttachName()); //月度作业计划上报
            }
            else {
//              gzPlanExecuteBO.reportPlanExecute(attachURL,attachInfoType[i].getAttachName()); //执行作业计划上报
            }
          }
        }
      }
    }
    else{
      resultIsAlive = "002";
    }

    return resultIsAlive;
  }

  public String fileDownload(String strRemoteAddr, String strLocalAddr) {
    String filePath = null;
    try {

      //初始化附件信息
      url = new URL(strRemoteAddr);
      newFileName = (new File(url.getFile())).getName();
      newAddr = strLocalAddr;

      URLConnection urlconnection = url.openConnection();
      urlconnection.setUseCaches(false);
      InputStream is = urlconnection.getInputStream();
      long filelength = urlconnection.getContentLength(); //附件的大小
      int ratio = 0;

      //开始下载附件
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
          }
          else {
            break;
          }
        } //if


        //保存附件
        File newFile = new File(newAddr);
        if(!newFile.exists()){
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
      }
      else {
        filePath = null;
      }
    }
    catch (MalformedURLException expt) {
      expt.printStackTrace();
      filePath = null;
    }
    catch (IOException eio) {
      eio.printStackTrace();
      filePath = null;
    }

    return filePath;
  }

  /**
   * 该操作用于A系统在调用B系统相应服务模块中其他方法之前，<br>
   * 判断B系统服务是否已经部署完毕，以确定所调用的方法当前是<br>
   * 否可以正常工作。本用例中，A系统只能为省级系统，B系统只能为总部系统。
   * @param _codeA String 服务调用方(省份)代码
   * @param _codeB String 服务提供方(省份)代码
   * @return String 为空(Nil)表示调用成功；非空表示调用失败，触发SOAP Fault元素，并返回以相应错误代码。
   */
  public String isAlive(String _codeA, String _codeB) {
    String resultIsAlive = null;

    System.out.println("省份：" + _codeA + "发出握手请求");

    return resultIsAlive;
  }

//  public void run() {
//    this.reportForm(this.codeA, this.codeB, this.attNum, this.attchInfoList,
//                    this.noteReportForm);
//  }


}
