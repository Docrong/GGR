package com.boco.eoms.base.test;

import com.boco.eoms.base.util.FileDownLoad;

public class DownLoad {
  public static void main(String arg[]) {
    try {
      String url = "http://down.17down.com/tools/17down_EditPlus-v2.12H.rar";
      Thread downThread = new Thread(new FileDownLoad(url, "d:\\download"), "nThread");
      downThread.start();
      System.out.println("thread have gone,we will go on");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
  }
}
