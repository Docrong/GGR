package com.boco.eoms.security.test;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.boco.eoms.security.authentication.callback.UsernamePasswordHandler;
import com.boco.eoms.security.crypto.Base64Encoder;

public class TestLogin {
  public TestLogin() {
  }

  public static void main(String[] args) {
//    TestLogin testLogin1 = new TestLogin();
    try {
      Subject subject = null;
      String s="weis";
      String s1="password";

      try {
        System.out.println("Before encode:"+s1);
        s1 = Base64Encoder.encode(s1);
        System.out.println("After encode:"+s1);
      }
      catch (IOException ex1) {
        System.out.println("password encode failed!!!");
      }

      LoginContext logincontext = new LoginContext("mylogin", new UsernamePasswordHandler(s, s1.toCharArray()));
      logincontext.login();
      subject = logincontext.getSubject();

      //System.out.println("1=["+subject+"]");
      //System.out.println("2="+subject.getPublicCredentials());
      //System.out.println("3="+subject.getPrivateCredentials());
    }
    catch (LoginException ex) {
      ex.printStackTrace();
    }
  }

}