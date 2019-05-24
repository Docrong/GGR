package com.boco.eoms.interfaces.util;
import java.io.*;
import java.util.*;
import java.net.URL;

public class DownLoad {
  public static void main(String arg[]) {
    try {
      String url = "http://down.17down.com/tools/17down_EditPlus-v2.12H.rar";
//String url = "http://javaz.3558.com/res/book/5.rar";
      Thread downThread = new Thread(new FileDownLoad(url, "f:"), "nThread");
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
