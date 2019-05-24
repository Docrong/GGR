package com.boco.eoms.common.bo;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BO implements java.io.Serializable{

  protected com.boco.eoms.db.util.ConnectionPool  ds = null;
  protected javax.servlet.http.HttpSession hs = null;

  public BO(com.boco.eoms.db.util.ConnectionPool ds) {
          this.ds = ds;
  }
  public BO(com.boco.eoms.db.util.ConnectionPool ds,String str) {
          this.ds = ds;
  }

  public BO() {
          this.ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
  }

  public void setHs(javax.servlet.http.HttpSession hs) {
    this.hs = hs;
  }




}