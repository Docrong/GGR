package mcs.common.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

import mcs.common.pool.*;

public class mtools extends Object {
    //getMax
    public static int getMaxId(String sTableName) throws Exception {
        Connection con = null;
        Statement stmt = null;

        int iMaxId = 0;


        con = DBCPoolMgr.getInstance().getConnection();
        stmt = con.createStatement();
        String strsql = "select Max(pi_PK) from " + sTableName;
        ResultSet RS = stmt.executeQuery(strsql);

        while (RS.next()) {
            iMaxId = 0;
            iMaxId = RS.getInt(1) + 1;
        }

        return iMaxId;

    }

    public static int getMaxId(String sTableName, String dbname) throws Exception {
        Connection con = null;
        Statement stmt = null;

        int iMaxId = 0;


        if (dbname != null && !dbname.equals("")) con = DBCPoolMgr.getInstance().getConnection(dbname);
        if (con == null) con = DBCPoolMgr.getInstance().getConnection();
        stmt = con.createStatement();
        String strsql = "select Max(pi_PK) from " + sTableName;
        ResultSet RS = stmt.executeQuery(strsql);

        while (RS.next()) {
            iMaxId = 0;
            iMaxId = RS.getInt(1) + 1;
        }

        return iMaxId;

    }

    public static String CovDate(Date date1) {
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DateStr = smpDateFormat.format(date1);
        return DateStr;
    }

    public static String CovDateYtoD(Date date1) {
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyMMdd");
        String DateStr = smpDateFormat.format(date1);
        return DateStr;
    }

    public static String CovDateYtoD2(Date date1) {
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String DateStr = smpDateFormat.format(date1);
        return DateStr;
    }

    public static String CovDateHtoS(Date date1) {
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("HH:mm:ss");
        String DateStr = smpDateFormat.format(date1);
        return DateStr;
    }

    public static Date StrToDate(String strDate) {
        Date tDate = null;
        if (strDate == null) return tDate;
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        tDate = smpDateFormat.parse(strDate, pos);
        return tDate;
    }

    public static Date StrToDate2(String strDate) {
        Date tDate = null;
        if (strDate == null) return tDate;
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        tDate = smpDateFormat.parse(strDate, pos);
        return tDate;
    }

    public static Date StrToDateYtoD(String strDate) {
        Date tDate = null;
        if (strDate == null) return tDate;
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        tDate = smpDateFormat.parse(strDate, pos);
        return tDate;
    }

    /*****
     **		函数返回date之前iTimeInterval小时的日期字符串		**
     ** 	date:传人日期对象；									**
     **		iTimeInterval：差距的小时数   						**
     *****/

    public static String getRemainDate(Date date, int iTimeInterval) {

        long iMilliSeconds = date.getTime() - iTimeInterval * 3600 * 1000;

        Date remainDate = new Date(iMilliSeconds);

        return CovDate(remainDate);

    }


    // 把命令中的单引号变为两个单引号，使该单引号可插入数据库
    public static String strChange39CmdToDb(String Cmd) {

        int intPos = 0;
        String strCmd = null;

        if (Cmd == null) {
            return "";
        } else if (Cmd.equals("")) {
            return Cmd;
        } else {
            intPos = Cmd.indexOf(39);
            if (intPos == -1)
                return Cmd;

            strCmd = Cmd.substring(0, intPos) + "''" + strChange39CmdToDb(Cmd.substring(intPos + 1));
            return strCmd;
        }
    }


    private static String offmark(String original) {
        String rtn = "";
        char[] charValue = original.toCharArray();

        int i;
        for (i = 0; i < charValue.length; i++) {
            if (charValue[i] == '\\') {

                rtn += "\\";
                continue;
            }
            rtn += (new Character(charValue[i])).toString();
        }
        return rtn;

    }

}
