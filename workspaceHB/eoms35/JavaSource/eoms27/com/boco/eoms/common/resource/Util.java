package com.boco.eoms.common.resource;


import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;


import javax.naming.NamingException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-6
 * Time: 17:53:54
 * To change this template use File | Settings | File Templates.
 */
/**
 *  The contents of this file are subject to the Mozilla Public
 *  License Version 1.1 (the "License"); you may not use this file
 *  except in compliance with the License. You may obtain a copy of
 *  the License at http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS
 *  IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  rights and limitations under the License.
 *
 *  The Original Code is pow2toolkit library.
 *
 *  The Initial Owner of the Original Code is
 *  Power Of Two S.R.L. (www.pow2.com)
 *
 *  Portions created by Power Of Two S.R.L. are
 *  Copyright (C) Power Of Two S.R.L.
 *  All Rights Reserved.
 *
 * Contributor(s):
 */


/**
 *  Utility class.
 *
 *  @author Luca Fossato
 */
public class Util {
    /**
     *  Test if the input string is null or empty (has 0 characters)
     *
     *  @ param  s the input string to test
     *  @ return true if the input string is null; false otherwise
     */
    static String  names = "jsp,js";
    public static boolean isNull(String s) {
        return ((s == null) || (s.length() < 1));
    }

    public static StringBuffer getCountSQL(String sql){
        StringBuffer result = new StringBuffer();
        result.append("select count(*) ");
        int sub_begin = sql.indexOf(" from ");

        if(sql.indexOf("order by")>0)
            result.append(sql.substring(sub_begin,sql.indexOf("order by")));
        else
            result.append(sql.substring(sub_begin)) ;
        return result;
    }
    /**
     *  Test if the input string is null or empty
     *  or if it's equal to the input <code>val</code> parameter.
     *
     *  @param  s     the input string to test
     *  @param  val   the value string to compare to <code>s</code>
     *  @return true  if <code>s</code> is null, or empty, or if it's
     *                equal to the <code>val</code> string;
     *                false otherwise.
     */
    public static boolean isNull(String s, String val) {
        return (isNull(s) || (s.compareTo(val) == 0));
    }


    /**
     *  Return the string representation of the input exception
     *  stack trace.
     *
     *  @param  e  the input exception
     *  @return the string representation of the input exception
     */
    public static String stackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }


    /**
     *  Delimit the input string with single quote characters (').
     *
     * @param  v  The new string value
     * @return    the input string delimited with single quote characters;
     */
    public static String dbString(String v) {
        StringBuffer sb = new StringBuffer();
        return (isNull(v) ? "" : (sb.append("'").append(v).append("'").toString()));
    }

    public static String UNI2GBK(String s) {
        String temp = "";
        try {
            if (s!=null) {
                temp = s;
                temp = new String(s.getBytes("ISO8859_1"), "GB2312");
            }
        } catch (Exception e) {
        } finally {
            return temp;
        }
    }

    public static String replaceStr(String srcStr, String oldStr, String newStr) {
        String leftStr,rightStr;
        int leftDot;
        leftDot = srcStr.indexOf(oldStr);
        if (leftDot > 0) {
            leftStr = srcStr.substring(0, leftDot);
            rightStr = srcStr.substring(leftDot + oldStr.length());
            return leftStr + newStr + rightStr;
        } else {
            return srcStr;
        }
    }

    public static String GBK2UNI(String s) {
        String temp = "";
        try {
            if (s!=null) {
                temp = s;
                temp = new String(s.getBytes("GB2312"), "ISO8859_1");
            }
        } catch (Exception e) {

        } finally {
            return temp;
        }
    }


    public static String[] split(String source, String div) {
        int arynum = 0,intIdx = 0,intIdex = 0,div_length = div.length();
        if (source.compareTo("") != 0) {
            if (source.indexOf(div) != -1) {
                intIdx = source.indexOf(div);
                for (int intCount = 1; ; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdx = source.indexOf(div, intIdx + div_length);
                        arynum = intCount;
                    } else {
                        arynum += 2;
                        break;
                    }
                }
            } else
                arynum = 1;
        } else
            arynum = 0;

        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];

        if (source.compareTo("") != 0) {

            if (source.indexOf(div) != -1) {

                intIdx = source.indexOf(div);
                returnStr[0] = source.substring(0, intIdx);

                for (int intCount = 1; ; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdex =  source.indexOf(div, intIdx + div_length);

                        returnStr[intCount] =  source.substring(intIdx + div_length, intIdex);

                        intIdx =  source.indexOf(div, intIdx + div_length);
                    } else {
                        returnStr[intCount] = source.substring(intIdx + div_length, source.length());
                        break;
                    }
                }
            } else {
                returnStr[0] =  source.substring(0, source.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }

    public static String getUploadFileName(String file_name,String x){
        String result="";
        if(!Util.isNull(file_name)){
            int dot_idx = file_name.indexOf(".");
            if(dot_idx>0) {
                String file_src= file_name.substring(0,dot_idx);
                String file_ext= file_name.substring(dot_idx);
                result = file_src+x+file_ext;
            }
        }
        return result;
    }


    /**
   *  Description of the Method
   *
   *@param  oldFileName  Description of the Parameter
   *@return              Description of the Return Value
   */
  public static String repalceDangerName(String oldFileName) {
    String[] dangerName = names.split(",");
    String newFileName = oldFileName;
    try {
      String tempString = oldFileName.substring(oldFileName.lastIndexOf(".") +
                                                1, oldFileName.length());
      for (int i = 0; i < dangerName.length; i++) {
        if (tempString.equalsIgnoreCase(dangerName[i])) {
          newFileName = oldFileName + ".bak";
        }
      }
    }
    catch (Exception ex) {
      System.out.println("error in repalce name" + ex.toString());
    }
    return newFileName;
  }
    public static Object copyMap2Obj(Map srcMap, String className) {
            Object object = null;
            Field[] fields = null;
            if (srcMap == null) return null;
            try {
                object = Class.forName(className).newInstance();
                fields = object.getClass().getFields();
                for (int i = 0; i < fields.length; i++) {
                    try {
                        String fieldName = fields[i].getName();
                        Class TYPE=fields[i].getType();
                        Object rawValue = srcMap.get(fieldName.toLowerCase());   //rawValue is String[] or String
                        if (rawValue == null) continue;
                        Object structValue=null;
                        String value = "";
                        if (rawValue instanceof Object[])
                            value = ((Object[]) (rawValue))[0].toString();
                        else
                            value = rawValue.toString();

                        if(TYPE==Integer.TYPE){        //int type
                            try {
                                if(value.equalsIgnoreCase("")) value="0";
                                structValue=Integer.valueOf(value);
                            } catch (Exception e) {
                                structValue=new Integer("0");
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }else{
//                            if(value.length()>=Constants.MAX_INFO_LENGTH)
//                                value=value.substring(0,Constants.MAX_INFO_LENGTH-1);
                            structValue=StaticMethod.dbNull2String(value); //编码转换
                        }

                        System.out.println(i +" "+fieldName+ " value:" + structValue);
                        fields[i].set(object, structValue);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            return object;
        }




      public static String getCountByte(String src,int broken){
       if(broken>src.getBytes().length) return src;
       byte[] temp=src.getBytes();
       int realLen=0;
       int count=0;
       for(int i=0;i<broken;i++)
        if(temp[i]<0) count++;
       if(count%2!=0)
           realLen=broken-1;
       else
           realLen=broken;
       return new String(temp,0,realLen);
    }
    public static String getCountString(String src,int broken){
       if(broken<=0) return "";
       byte[] temp=src.getBytes();
       if(broken>temp.length) return src;
       String dist=new String(temp,0,broken);
       if(dist.equalsIgnoreCase("")) dist=new String(temp,0,broken-1);
       return dist;
    }

    public static String getSequenceId(Connection conn,String tableName,String fieldName) throws Exception{
        String sequenceId="-1";
        Statement stat=conn.createStatement();
        ResultSet rs = stat.executeQuery("select max("+fieldName+")+1 from "+tableName);
            if (rs.next()) {
                if( rs.getString(1)==null)
                    sequenceId="1";
                else
                    sequenceId = rs.getString(1);
            } else {
                sequenceId = "-1";
            }
        
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return sequenceId;
    }
}

