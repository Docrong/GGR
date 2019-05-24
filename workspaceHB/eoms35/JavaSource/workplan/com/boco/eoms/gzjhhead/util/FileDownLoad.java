package com.boco.eoms.gzjhhead.util;

import java.io.*;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import com.boco.eoms.common.util.BaseFtp;
import com.boco.eoms.common.util.StaticMethod;

public class FileDownLoad
    implements Runnable {

  private int sleepTime = 10;
  private static boolean flag = true;
  private URL url = null;
  private String newFileName = "";
  private String newAddr = "";
  private int count = 0;
  private int error = 0;

  public FileDownLoad(String strRemoteAddr, String strLocalAddr) {
    try {
      url = new URL(strRemoteAddr);
      newFileName = (new File(url.getFile())).getName();
      newAddr = strLocalAddr;
      flag = true;
    }
    catch (Exception ex) {
      System.out.println("---DOWND ERROR---" + ex.getMessage());
      stopThread();
    }
  }

  public static void stopThread() {
    flag = false;
  }

  public void run() {
    System.out.println("----------下载线程启动--------");

    while (flag) {
      try {
        URLConnection urlconnection = url.openConnection();
        urlconnection.setUseCaches(false);
        InputStream is = urlconnection.getInputStream();
        long filelength = urlconnection.getContentLength();

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
            }
            else {
              break;
            }
          } //if

          if (received != 0 && received == filelength) {
            FileOutputStream fos = new FileOutputStream(newAddr +
                File.separator +
                newFileName);
            baos.writeTo(fos);
            fos.close();

            System.out.println("file tranfering finished!! and be saved in " +
                               newAddr +
                               File.separator + newFileName);
            this.stopThread();
          }
        } // is == null
        else {
          error = 1;
        }
      }
      catch (MalformedURLException expt) {
        expt.printStackTrace();
        error = 1;
      }
      catch (IOException eio) {
        eio.printStackTrace();
        error = 1;
      }
      finally {
        if (error == 1) {
          if (count > 5) {
            System.out.println("----------重试次数超过10次，下载失败-----------");
            this.stopThread();
          }
          else {
            System.out.println("--------错误发生，等待" + sleepTime + "秒后重试--------");
            count = count + 1;
            try {
              Thread.currentThread().sleep(sleepTime);
            }
            catch (InterruptedException ei) {
              ei.printStackTrace();
              error = 1;
            }
          }
          error = 0;
        }
      }
    }
  }
}
