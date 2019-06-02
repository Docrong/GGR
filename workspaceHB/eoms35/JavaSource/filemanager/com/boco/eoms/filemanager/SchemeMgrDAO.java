package com.boco.eoms.filemanager;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.UUIDHexGenerator;
import com.boco.eoms.filemanager.extra.fileupload.FileInfo;
import com.boco.eoms.common.resource.Util;
import com.boco.eoms.filemanager.form.SchemeMgrForm;

import com.boco.eoms.filemanager.extra.scheduler.SchedulerManager;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.upload.FormFile;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-15
 * Time: 10:28:49
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class SchemeMgrDAO {
    public static final String _APP_ID="25";
   //����Ȩ��id����
    public static final int OPERATOR_TOPIC_MGR_ID=2501;//ר��ģ�����
    public static final int OPERATOR_SCHEME_MGR_ID=2502;//�����ɷ�����
    public static final int OPERATOR_REPORT_COLLECT_ID=2503;//�������
    public static final int OPERATOR_REPORT_STAT_ID=2504;//����ͳ��

    public static final int SCHEME_CYCLE_TYPE_TEMP = 1;     //��ʱ
    public static final int SCHEME_CYCLE_TYPE_WEEK = 2;    //��
    public static final int SCHEME_CYCLE_TYPE_MONTH = 3;   //��
    public static final int SCHEME_CYCLE_TYPE_QUARTER = 4; //����
    public static final int SCHEME_CYCLE_TYPE_PROFESSIONAL = 5; //ֱ���������ֶ�
    public static final int MON = 1;
    public static final int WED = 2;
    public static final int THU = 3;
    public static final int TUE = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
    public static final int SUN = 7;
    public static final String SCHEDULER_CLASS_NAME="com.boco.eoms.filemanager.FileMgrScheme";
    public static final String SCHEME_FIRE_TIME="0 0 0";    //"0 0 0"
    Connection conn = null;
    Statement stat = null;
//    TawWsDictBO tawWsDictBO = new TawWsDictBO();

    public SchemeMgrDAO() {
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public SchemeMgrDAO(Connection conn) {
        try {
            this.conn = conn;
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Vector getPage(int start, int pageCount, String sql, int size) throws Exception {
        ResultSet rs = null;
        try {

            rs = stat.executeQuery(sql.toString());
        } catch (SQLException e) {
            if (rs != null) rs.close();
            throw e;
        }

        Vector list = new Vector();
        if (!rs.next()) return list;
        try {
            for (int i = 0; i < size && i < (start + pageCount); i++) {
                if (i < start) {
                    rs.next();
                    continue;
                }

                StaticMethod.dbNull2String(rs.getString("connName"));
                int timeLevel = rs.getInt("time_level");
                int regionLevel = rs.getInt("region_level");
                int dataSourceType = rs.getInt("data_source_type");
                String timeLevelName = "";
                String regionLevelName = "";
                String dataSourceTypeName = "";
                list.add("");
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return list;
    }


    public int getResutCount(StringBuffer sql) {
        ResultSet rs = null;
        int count = 0;
        try {
            rs = stat.executeQuery(sql.toString());
            if (rs.next()) count = rs.getInt(1);
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("sql" + sql);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return count;
    }

    public StringBuffer getCountSql(StringBuffer querySql) {
        //get count sql
        StringBuffer result = new StringBuffer();
        result.append("select count(*) ");
        int sub_begin = querySql.lastIndexOf(" from ");
        if (querySql.indexOf("order by") > 0)
            result.append(querySql.substring(sub_begin, querySql.indexOf("order by")));
        else
            result.append(querySql.substring(sub_begin));
        return result;
    }

    public void release() {
        if (stat != null) {
            try {
                stat.close();
            } catch (Exception e) {
                System.out.println("release Error");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("release Error");
            }
        }
    }

    //���ı�
    public static final String ITEM = "100";
    public static final String ITEM_NAME = StaticMethod.toBaseEncoding("������?ʱ����");
    private String insertSubscription = "insert into st_subscription(id,sub_id,cycle,type," +
            "class_name,subscribe_time,remark,item,item_name)" +
            " values(?,?,?,?,?,?,?,?,?)";
    private String updateSubScription = "update st_subscription set cycle=?,subscribe_time=? where item=? and remark=?";

    public String save(String contextRealPath, SchemeMgrForm form) throws Exception {
        boolean isAuto = conn.getAutoCommit();
        String schemeId = "-1";
        conn.setAutoCommit(false);
        String cycle= getScheduleCycle(form);
        String insertSql = "insert into taw_file_mgr_scheme(topic_id,title,report_description," +
                "fault_class,send_dept_id,send_contact,send_dept_name,create_user_id,accept_dept_id," +
                "accept_dept_name,scheme_cycle_type,scheme_cycle,scheme_description," +
                "scheme_ahead,scheme_time,file_mgr_scheme_id,combintype,audit_user_id,audit_user_name,report_user_id,report_user_name,is_audit) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
             ResultSet rs = conn.createStatement().executeQuery("select max(file_mgr_scheme_id)+1 from taw_file_mgr_scheme");
            if (rs.next()) {
                if( rs.getString(1)==null)
                    schemeId="1";
                else
                    schemeId = rs.getString(1);
            } else {
                schemeId = "-1";
            }

            if (rs != null) rs.close();

            PreparedStatement pmts = conn.prepareStatement(insertSql);
            pmts.setString(1, form.getTopicId());
            pmts.setString(2, form.getTitle());
            pmts.setString(3, form.getReportDescription());
            pmts.setString(4, form.getFaultClass());
            pmts.setString(5, form.getSendDeptId());
            pmts.setString(6, form.getSendContact());
            pmts.setString(7, form.getSendDeptName());
            pmts.setString(8, form.getCreateUser());
            pmts.setString(9, form.getAcceptDeptId());
            pmts.setString(10, form.getAcceptDeptName());
            //pmts.setString(11, form.getSpecialtyId());
            pmts.setString(11, form.getCycleType());
            pmts.setString(12, cycle);
            pmts.setString(13, form.getCycleDescription());
            pmts.setInt(14, form.getSchemeAhead());
            pmts.setString(15, form.getSchemeTime());
            pmts.setString(16, schemeId);
            pmts.setString(17, form.getCombinType());
            if(form.getIsAudit()==null||"".equals(form.getIsAudit()))
            {
            	pmts.setString(22, "0");
            }else{
            	pmts.setString(22, "1");
            }
            if(form.getAuditUserId()==null||"".equals(form.getAuditUserId()))
            {
            	pmts.setString(18, "");
            	pmts.setString(19, "");
            }else{
            	pmts.setString(18, ","+form.getAuditUserId()+",");
            	pmts.setString(19, form.getAuditUserName());
            }
            
            if(form.getReportUserId()==null||"".equals(form.getReportUserId()))
            {
            	pmts.setString(20, "");
            	pmts.setString(21, "");
            }else{
            	pmts.setString(20, ","+form.getReportUserId()+",");
            	pmts.setString(21, form.getReportUserName());
            }
            pmts.executeUpdate();

            String path = "/filemanager/files/template";
            String real_path = contextRealPath + path;
            java.io.File folder = new java.io.File(real_path);
            if (!folder.exists())
                folder.mkdir();
            MultipartRequestHandler multiHash = form.getMultipartRequestHandler();
            Hashtable elements = multiHash.getFileElements();
            Enumeration keys = elements.keys();
            Vector files = new Vector();
            Date date = new Date();
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(date);  //��ȡ��ǰʵ��Ǽ�ʱ�䣩
            String fileRealName = currentTime.replace(' ', '-').replace(':', '-');
            int count = 0;
            while (keys.hasMoreElements()) {
                count++;
                String fileId = keys.nextElement().toString();
                System.out.println("fileId = " + fileId);
                FormFile myFile = (FormFile) elements.get(fileId);
                String name = myFile.getFileName();
                if ("".equalsIgnoreCase(name) || myFile.getFileSize() <= 0) continue;
                //name = StaticMethod.strReverse(name,"ISO-8859-1","UTF-8");
//                  name=StaticMethod.toBaseEncoding(name);
                String fullPathName = real_path + "/" + name;
                folder = new java.io.File(fullPathName);
                if (folder.exists()) {
                    folder.delete();
                }
                try {
                    String extName = name.substring(name.lastIndexOf('.') + 1);
                    String realName = fileRealName + getRandomString(8) + count + "." + extName;
                    FileInfo fileInfo = new FileInfo();                      //��������
                    fileInfo.setFileName(name);
                    fileInfo.setFilePath(path);
                    fileInfo.setFileRealName(realName);
                    files.add(fileInfo);
                    formUpload(myFile, real_path, realName);
                } catch (Exception ex) {
                    throw new Exception("����ʧ��" + ex.getMessage());
                }
            }
            insertSql = "insert into taw_file_mgr_files(file_type ,file_time," +
                    "file_display_name ,file_real_name,file_path,owner_id,file_id)" +
                    "  values(?,?,?,?,?,?,?)";
            if (pmts != null) pmts.close();
//            java.sql.Date curr = new java.sql.Date(date.getTime());
            Timestamp stamp=new Timestamp(date.getTime());
            pmts = conn.prepareStatement(insertSql);
            for (int i = 0; i < files.size(); i++) {
                FileInfo fileInfo = (FileInfo) files.elementAt(i);
                pmts.setString(1, "1");
                pmts.setTimestamp(2, stamp);
                pmts.setString(3,fileInfo.getFileName());
                pmts.setString(4, fileInfo.getFileRealName());
                pmts.setString(5, fileInfo.getFilePath());
                pmts.setString(6, schemeId);
                pmts.setString(7, Util.getSequenceId(conn,"taw_file_mgr_files","file_id"));
                pmts.executeUpdate();
            }
            if (pmts != null) pmts.close();
            //д���ı� schedule
            String taskId = UUIDHexGenerator.getInstance().getID();
            String subId=getSubId(ITEM);
            pmts = conn.prepareStatement(insertSubscription);
            pmts.setString(1, taskId);
            pmts.setString(2,subId);
            pmts.setString(3, cycle);
            pmts.setString(4, "cron");
            pmts.setString(5, SCHEDULER_CLASS_NAME);
            pmts.setTimestamp(6, stamp);
            pmts.setString(7, schemeId);
            pmts.setString(8, ITEM);
            pmts.setString(9, ITEM_NAME);
            pmts.executeUpdate();
            SchedulerManager sm = SchedulerManager.getInstance();
            sm.add(subId,"group2",SCHEDULER_CLASS_NAME,"cron",cycle);//group2��װ����ϵͳ���й����ʵʱ��ӵĶ�����Ϣ
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            System.out.println(StaticMethod.dbNull2String(e.toString()));
            e.printStackTrace();
        }
        conn.setAutoCommit(isAuto);
        return schemeId;


    }

    public String getScheduleCycle(SchemeMgrForm form) {
        String cycle = "";
        int cycleType = Integer.parseInt(form.getCycleType());
        String time = form.getSchemeTime();
        switch (cycleType) {
            case SCHEME_CYCLE_TYPE_TEMP:
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = dateFormat.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH) + 1;
                int dd = calendar.get(Calendar.DATE);
                int hh = calendar.get(Calendar.HOUR_OF_DAY);
                int mi = calendar.get(Calendar.MINUTE);
                int ss = calendar.get(Calendar.SECOND);
                cycle = ss + " " + mi + " " + hh + " " + dd + " " + mm + " " + "?" + " " + yy;
                break;
            case SCHEME_CYCLE_TYPE_WEEK:
                int weekDay = Integer.parseInt(time);
                switch (weekDay) {
                    //�ܱ���ʱ�䰲�����賿0��
                    case MON:
                        cycle = SCHEME_FIRE_TIME+" ? * MON";
                        break;
                    case TUE:
                        cycle = SCHEME_FIRE_TIME+" ? * TUE";
                        break;
                    case WED:
                        cycle = SCHEME_FIRE_TIME+" ? * WED";
                        break;
                    case THU:
                        cycle = SCHEME_FIRE_TIME+" ? * THU";
                        break;
                    case FRI:
                        cycle = SCHEME_FIRE_TIME+" ? * FRI";
                        break;
                    case SAT:
                        cycle = SCHEME_FIRE_TIME+" ? * SAT";
                        break;
                    case SUN:
                        cycle = SCHEME_FIRE_TIME+" ? * SUN";
                        break;
                    default:
                        cycle = "";
                }
                cycle += " " + "*";        //������
                break;
            case SCHEME_CYCLE_TYPE_MONTH:
                cycle = SCHEME_FIRE_TIME+" " + time + " * ? *";
                break;
            case SCHEME_CYCLE_TYPE_QUARTER:
                cycle = SCHEME_FIRE_TIME+" " + time + " 1,4,7,10 ? *";
                break;
            case SCHEME_CYCLE_TYPE_PROFESSIONAL:
                cycle = time;
                break;
        }
        return cycle;
    }
    
    //为了解决临时的问题。。
//    public String getScheduleCycleForSave(SchemeMgrForm form) {
//        String cycle = "";
//        int cycleType = Integer.parseInt(form.getCycleType());
//        String time = form.getSchemeTime();
//        switch (cycleType) {
//            case SCHEME_CYCLE_TYPE_TEMP:
//                Date date = new Date();
//                Date date_new = new Date();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    date = dateFormat.parse(time);
//                    if(date_new.after(date))
//                    	date.setTime(date_new.getTime()+10000);
//                } catch (ParseException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(date);
//                int yy = calendar.get(Calendar.YEAR);
//                int mm = calendar.get(Calendar.MONTH) + 1;
//                int dd = calendar.get(Calendar.DATE);
//                int hh = calendar.get(Calendar.HOUR_OF_DAY);
//                int mi = calendar.get(Calendar.MINUTE);
//                int ss = calendar.get(Calendar.SECOND);
//                cycle = ss + " " + mi + " " + hh + " " + dd + " " + mm + " " + "?" + " " + yy;
//                break;
//            case SCHEME_CYCLE_TYPE_WEEK:
//                int weekDay = Integer.parseInt(time);
//                switch (weekDay) {
//                    //�ܱ���ʱ�䰲�����賿0��
//                    case MON:
//                        cycle = SCHEME_FIRE_TIME+" ? * MON";
//                        break;
//                    case TUE:
//                        cycle = SCHEME_FIRE_TIME+" ? * TUE";
//                        break;
//                    case WED:
//                        cycle = SCHEME_FIRE_TIME+" ? * WED";
//                        break;
//                    case THU:
//                        cycle = SCHEME_FIRE_TIME+" ? * THU";
//                        break;
//                    case FRI:
//                        cycle = SCHEME_FIRE_TIME+" ? * FRI";
//                        break;
//                    case SAT:
//                        cycle = SCHEME_FIRE_TIME+" ? * SAT";
//                        break;
//                    case SUN:
//                        cycle = SCHEME_FIRE_TIME+" ? * SUN";
//                        break;
//                    default:
//                        cycle = "";
//                }
//                cycle += " " + "*";        //������
//                break;
//            case SCHEME_CYCLE_TYPE_MONTH:
//                cycle = SCHEME_FIRE_TIME+" " + time + " * ? *";
//                break;
//            case SCHEME_CYCLE_TYPE_QUARTER:
//                cycle = SCHEME_FIRE_TIME+" " + time + " 1,4,7,10 ? *";
//                break;
//            case SCHEME_CYCLE_TYPE_PROFESSIONAL:
//                cycle = time;
//                break;
//        }
//        return cycle;
//    }

    private String getSubId(String faultType) throws Exception {
        String subId = "";
        String regionId = StaticMethod.getNodeName("STRREGIONCODE");
        String xyzLength = "100";
        String strYYMMDD = StaticMethod.getYYMMDD();
        subId += regionId + "-" + strYYMMDD + "-" + faultType;
        int xyz = getXYZ(subId) + 1;
        String strXYZ = String.valueOf(StaticMethod.null2int(xyzLength) + xyz);
        subId += "-" + strXYZ.substring(1);
        return subId;
    }

    public int getXYZ(String preSubId) {
        String subId = preSubId + "%";
        int count = 0;
        ResultSet rs=null;
        try {
             rs = stat.executeQuery("select count(*) from st_subscription where sub_id like '" + subId + "'");
            if (rs.next()) count = rs.getInt(1);
            if (rs != null) rs.close();
        } catch (SQLException e) {
        	
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return count;
    }

    public String update(String contextRealPath, SchemeMgrForm form) throws Exception {
        boolean isAuto = conn.getAutoCommit();
        String schemeId = form.getSchemeId();
        conn.setAutoCommit(false);
        //���»���Ϣ audit_user_id,audit_user_name,report_user_id,report_user_name
        String insertSql = "update taw_file_mgr_scheme set topic_id=?,title=?,report_description=?," +
                "fault_class=?,send_contact=?,create_user_id=?,accept_dept_id=?," +
                "accept_dept_name=?,scheme_cycle_type=?,scheme_cycle=?,scheme_description=?," +
                " scheme_ahead=?,scheme_time=? ,combintype=?,audit_user_id=?,audit_user_name=?,report_user_id=?," +
                "report_user_name=?,is_audit=? where file_mgr_scheme_id=? ";
        try {
            PreparedStatement pmts = conn.prepareStatement(insertSql);
            pmts.setString(1, form.getTopicId());
            pmts.setString(2, form.getTitle());
            pmts.setString(3, form.getReportDescription());
            pmts.setString(4, form.getFaultClass());
//            pmts.setString(5, form.getSendDeptId());
            pmts.setString(5, form.getSendContact());
//            pmts.setString(7, form.getSendDeptName());
            pmts.setString(6, form.getCreateUser());
            pmts.setString(7, form.getAcceptDeptId());
            pmts.setString(8, form.getAcceptDeptName());
            //pmts.setString(9, form.getSpecialtyId());
            pmts.setString(9, form.getCycleType());
            pmts.setString(10, getScheduleCycle(form));
            pmts.setString(11, form.getCycleDescription());
            pmts.setInt(12, form.getSchemeAhead());
            pmts.setString(13, form.getSchemeTime());
            pmts.setString(14, form.getCombinType());
               
            if(form.getIsAudit()==null||"".equals(form.getIsAudit())||"0".trim().equals(form.getIsAudit()))
            {
            	pmts.setString(15, "");
            	pmts.setString(16, "");
            	pmts.setString(19, "0");
            }else{
            	pmts.setString(15, ","+form.getAuditUserId()+",");
            	pmts.setString(16, form.getAuditUserName());
            	pmts.setString(19, "1");
            }
            if(form.getReportUserId()==null||"".equals(form.getReportUserId()))
            {
            	pmts.setString(17, "");
            	pmts.setString(18, "");
            }else{
            	pmts.setString(17, ","+form.getReportUserId()+",");
            	pmts.setString(18, form.getReportUserName());
            }
            
            pmts.setString(20, form.getSchemeId());

            pmts.executeUpdate();
            //�ļ��ϴ�
            String path = "/filemanager/files/template";
            String real_path = contextRealPath + path;
            java.io.File folder = new java.io.File(real_path);
            if (!folder.exists())
                folder.mkdir();
            MultipartRequestHandler multiHash = form.getMultipartRequestHandler();
            Hashtable elements = multiHash.getFileElements();
            Enumeration keys = elements.keys();
            Vector files = new Vector();
            Date date = new Date();
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(date);  //��ȡ��ǰʵ��Ǽ�ʱ�䣩
            String fileRealName = currentTime.replace(' ', '-').replace(':', '-');
            int count = 0;

            while (keys.hasMoreElements()) {
                count++;
                String fileId = keys.nextElement().toString();
                FormFile myFile = (FormFile) elements.get(fileId);
                String name = myFile.getFileName();
                if ("".equalsIgnoreCase(name) || myFile.getFileSize() <= 0) continue;
//                name = StaticMethod.strReverse(name,"ISO-8859-1","UTF-8");
                String fullPathName = real_path + "/" + name;
                folder = new java.io.File(fullPathName);
                if (folder.exists()) {
                    folder.delete();
                }
                try {
                    String extName = name.substring(name.lastIndexOf('.') + 1);
                    String realName = fileRealName + getRandomString(8) + count + "." + extName;
                    FileInfo fileInfo = new FileInfo();                      //��������
                    fileInfo.setFileName(name);
                    fileInfo.setFilePath(path);
                    fileInfo.setFileRealName(realName);
                    files.add(fileInfo);
                    formUpload(myFile, real_path, realName);
                } catch (Exception ex) {
                    throw new Exception("����ʧ��" + ex.getMessage());
                }
            }
            insertSql = "insert into taw_file_mgr_files(file_type ,file_time,file_display_name ,file_real_name,file_path,owner_id,file_id)  values(?,?,?,?,?,?,?)";

            if (pmts != null) pmts.close();
//            java.sql.Date curr = new java.sql.Date(date.getTime());
            Timestamp stamp=new Timestamp(date.getTime());
            pmts = conn.prepareStatement(insertSql);
            for (int i = 0; i < files.size(); i++) {
                FileInfo fileInfo = (FileInfo) files.elementAt(i);
                pmts.setString(1, "1");
                pmts.setTimestamp(2, stamp);
                pmts.setString(3, fileInfo.getFileName());
                pmts.setString(4, fileInfo.getFileRealName());
                pmts.setString(5, fileInfo.getFilePath());
                pmts.setString(6, schemeId);
                pmts.setString(7, Util.getSequenceId(conn,"taw_file_mgr_files","file_id"));
                pmts.executeUpdate();
            }
            if (pmts != null) pmts.close();
            //��������
            pmts = conn.prepareStatement(updateSubScription);
            pmts.setString(1, getScheduleCycle(form));
            pmts.setTimestamp(2, stamp);
            pmts.setString(3, ITEM);
            pmts.setString(4, schemeId);
            pmts.executeUpdate();
            if (pmts != null) pmts.close();
            //conn.commit();
            conn.commit();
            SchedulerManager sm = SchedulerManager.getInstance();
            sm.modify(getSchemeBean(schemeId).getSubId(),"group2",SCHEDULER_CLASS_NAME,"cron",getScheduleCycle(form));//group2��װ����ϵͳ���й����ʵʱ��ӵĶ�����Ϣ
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            System.out.println(StaticMethod.dbNull2String(e.toString()));
            e.printStackTrace();
        }
        conn.setAutoCommit(isAuto);
        return schemeId;


    }

    public String getRandomString(int len) {
        if (len <= 0) len = 8;
        Random rad = new Random();
        double intRan = rad.nextDouble();
        String temp = Double.toString(intRan).substring(2);
        String result = temp;
        if (temp.length() < len)
            for (int j = 0; j < len - temp.length(); j++)
                result += j;
        else
            result = temp.substring(0, len);
        return result;
    }

    public static void formUpload(FormFile file, String filePath, String file_name) throws Exception {

        try {

            InputStream stream = file.getInputStream();

            OutputStream bos = new FileOutputStream(filePath + "/" + file_name);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];

            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {

                bos.write(buffer, 0, bytesRead);

            }
            bos.close();
            stream.close();

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();
            throw fnfe;

        } catch (IOException ioe) {

            ioe.printStackTrace();
            throw ioe;

        }

    }

    private String getFaultClassName(String id) {
        List faultClass = getFaultClassList();
        for (int i = 0; i < faultClass.size(); i++) {
            LabelValueBean bean = (LabelValueBean) faultClass.get(i);
            if (id.equalsIgnoreCase(bean.getValue())) return bean.getLabel();
        }
        return "";
    }
    private String getCombinTypeName(String id) {
        List CombinType = getCombinTypeList();
        for (int i = 0; i < CombinType.size(); i++) {
            LabelValueBean bean = (LabelValueBean) CombinType.get(i);
            if (id.equalsIgnoreCase(bean.getValue())) return bean.getLabel();
        }
        return "";
    }

    private String getSpacialtyName(String id, List specialtyList) {
        for (int i = 0; i < specialtyList.size(); i++) {
            LabelValueBean bean = (LabelValueBean) specialtyList.get(i);
            if (id.equalsIgnoreCase(bean.getValue())) return bean.getLabel();
        }
        return "";
    }

    public SchemeMgrForm getFormBean(String schemeId) {
        StringBuffer strSQL = new StringBuffer();
        SchemeMgrForm info = new SchemeMgrForm();
        List specialtyList = getSpecialtyList();
        if (schemeId == null) return info;
        strSQL.append("select a.*," +
                "(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id) as topic_name ," +
                "(select username from taw_system_user d where a.create_user_id=d.userid) as create_user_name ," +
                "c.* from taw_file_mgr_scheme a left join taw_file_mgr_files c on (a.file_mgr_scheme_id=c.owner_id and  c.file_type='1')  " +
                " where  a.file_mgr_scheme_id='" + schemeId + "' order by c.file_time desc");
        ResultSet rs = null;
        String reportId = null;
        Vector fileUrl = new Vector();
        try {
            rs = stat.executeQuery(strSQL.toString());
            while (rs.next()) {
                FileInfo fileInfo = new FileInfo();                      //��������
                fileInfo.setFileId(rs.getInt("file_id"));
                if (rs.getString("file_display_name") != null)
                    fileInfo.setFileName(rs.getString("file_display_name").trim());
                if (rs.getString("file_path") != null)
                    fileInfo.setFilePath(rs.getString("file_path").trim());
                if (rs.getString("file_real_name") != null)
                    fileInfo.setFileRealName(rs.getString("file_real_name").trim());
                fileUrl.add(fileInfo);
                if (reportId == null) {
                    reportId = rs.getString("file_mgr_scheme_id");
                    info.setCreateUser(rs.getString("create_user_id"));
                    info.setCreateUserName(rs.getString("create_user_name"));
                    info.setSendContact(rs.getString("send_contact"));
                    info.setFaultClass(rs.getString("fault_class"));
                    info.setFaultClassName(getFaultClassName(rs.getString("fault_class")));
                    if (rs.getString("report_description") != null)
                        info.setReportDescription(rs.getString("report_description").trim());
                    info.setSchemeId(schemeId);
                    info.setSendDeptId(rs.getString("send_dept_id"));
                    info.setSendDeptName(rs.getString("send_dept_name"));
                    //info.setSpecialtyId(rs.getString("specialty_id"));
                   //info.setSpecialtyName(getSpacialtyName(rs.getString("specialty_id"), specialtyList));
                    if (rs.getString("accept_dept_id") != null)
                        info.setAcceptDeptId(rs.getString("accept_dept_id").trim());
                    if (rs.getString("accept_dept_name") != null)
                        info.setAcceptDeptName(rs.getString("accept_dept_name").trim());
                    info.setTopicId(rs.getString("topic_id"));
                    info.setTopicName(rs.getString("topic_name"));
                    info.setTitle(rs.getString("title"));
                    info.setCycleType(rs.getString("scheme_cycle_type"));
                    info.setCycle(rs.getString("scheme_cycle"));
                    info.setCycleDescription(rs.getString("scheme_description"));
                    info.setSchemeAhead(rs.getInt("scheme_ahead"));
                    info.setSchemeTime(rs.getString("scheme_time"));
                    info.setCombinType(rs.getString("combinType"));
                    info.setCombinTypeName(getCombinTypeName(rs.getString("combinType")));
                    info.setIsAudit(rs.getString("is_audit"));
                    if(rs.getString("is_audit").equals("0")){
                    	info.setAuditUserName("[不需要审核]");
                    }else{
                    	info.setAuditUserName("[需要审核]");
                    }
                    if (rs.getString("audit_user_id")!= null&&!"".equals(rs.getString("audit_user_id").trim())){
                        info.setAuditUserId(rs.getString("audit_user_id").trim().substring(1, rs.getString("audit_user_id").trim().length()-1));                      
                    }
                    if (rs.getString("audit_user_name") != null&&!"".equals(rs.getString("audit_user_name").trim()))
                        info.setAuditUserName(rs.getString("audit_user_name").trim());                 	
                    if (rs.getString("report_user_id") != null&&!"".equals(rs.getString("report_user_id").trim()))
                        info.setReportUserId(rs.getString("report_user_id").trim().substring(1, rs.getString("report_user_id").trim().length()-1));
                    if (rs.getString("report_user_name") != null&&!"".equals(rs.getString("report_user_name").trim()))
                        info.setReportUserName(rs.getString("report_user_name").trim());
                }
            }
        } catch (SQLException e) {
            System.out.println("strSQL = " + strSQL);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] files = new String[fileUrl.size()];
        for (int i = 0; i < files.length; i++) {
            FileInfo fileInfo = (FileInfo) fileUrl.elementAt(i);
            files[i] = fileInfo.getFileId() + "," + fileInfo.getFileName() + "," + fileInfo.getFilePath() + "/" + fileInfo.getFileRealName();
        }
        info.setFileUrl(files);            //file url �Զ��Ÿ�����,��һ��Ϊ�ļ�id,d�ڶ�����ʾ�������ļ���Ե�url
        return info;
    }

    String selectFilesSql = "select *  from taw_file_mgr_files  where file_type='1'and  owner_id=?";
    String deleteFilesSql = "delete from taw_file_mgr_files  where file_type='1'and  owner_id=?";
    String deleteSchemeSql = "delete from taw_file_mgr_scheme  where file_mgr_scheme_id=?";
    String deleteSubscriptionSql = "delete from st_subscription  where remark=? and item='"+ITEM+"'";
    public static String selectSubId="select sub_id,cycle,remark,item,item_name from st_subscription  where remark=? and item='"+ITEM+"'";
    public static SchemeBean getSchemeBean(String schemeId){
        SchemeBean bean=new SchemeBean();
        Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pmts = null;
        try {
            pmts=connection.prepareStatement(selectSubId);
            pmts.setString(1,schemeId);
            ResultSet resultSet=pmts.executeQuery();
            if(resultSet.next()){
               bean.setSubId(resultSet.getString("sub_id"));
               bean.setCycle(resultSet.getString("cycle"));
               bean.setRemark(resultSet.getString("remark"));
               bean.setItem(resultSet.getString("item"));
               bean.setItemName(resultSet.getString("item_name"));
            }
            if(resultSet!=null)resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            if(pmts!=null)pmts.close();
            if(connection!=null)connection.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return bean;
    }
    public boolean delete(String contextRealPath, SchemeMgrForm form) {
        String schemeId = form.getSchemeId();

        PreparedStatement pmts = null;
        try {
            conn.setAutoCommit(false);
            try {
                pmts = conn.prepareStatement(selectFilesSql);
                pmts.setString(1, schemeId);
                ResultSet rs = pmts.executeQuery();
                while (rs.next()) {
                    String fileName = contextRealPath + rs.getString("file_path").trim() + "/" + rs.getString("file_real_name").trim();
                    File file = new File(fileName);
                    file.delete();
                }
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (pmts != null) pmts.close();
            SchedulerManager sm = SchedulerManager.getInstance();
            sm.delete(getSchemeBean(schemeId).getSubId());//group2��װ����ϵͳ���й����ʵʱ��ӵĶ�����Ϣ

            pmts = conn.prepareStatement(deleteFilesSql);
            pmts.setString(1, schemeId);
            pmts.executeUpdate();
            if (pmts != null) pmts.close();
            pmts = conn.prepareStatement(deleteSchemeSql);
            pmts.setString(1, schemeId);
            pmts.executeUpdate();
            if (pmts != null) pmts.close();
            pmts = conn.prepareStatement(deleteSubscriptionSql);
            pmts.setString(1, schemeId);
            pmts.executeUpdate();
            if (pmts != null) pmts.close();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println(StaticMethod.dbNull2String(e.toString()));
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public Vector deleteFileInfo(String contextPath, String fileType, String ownerId, String fileId) {
        Vector result = new Vector();
        String fileSql = "select * from taw_file_mgr_files where file_id=" + fileId;
        String sql = "delete from taw_file_mgr_files where file_id=" + fileId;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try { 
            conn.setAutoCommit(false);            //record upload file information
            pst = conn.prepareStatement(fileSql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String fileName = contextPath + rs.getString("file_path").trim() + "/" + rs.getString("file_real_name").trim();
                File file = new File(fileName);
                file.delete();
            }
            rs.close();
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(Util.UNI2GBK(e.toString()));
        } finally {

            try {
                sql = "SELECT * FROM taw_file_mgr_files WHERE file_type='" + fileType + "'and owner_id='" + ownerId + "'";
                rs = stat.executeQuery(sql);
                while (rs.next()) {
                    FileInfo fileInfo = new FileInfo();                      //��������
                    fileInfo.setFileId(rs.getInt("file_id"));
                    fileInfo.setFileName(rs.getString("file_display_name"));
                    fileInfo.setFilePath(rs.getString("file_path"));
                    fileInfo.setFileRealName(rs.getString("file_real_name"));
                    result.add(fileInfo);
                }
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.out.println(Util.UNI2GBK(e.getMessage()));
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return result;

    }

    public List getSpecialtyList() {
        ArrayList list = new ArrayList();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("select dict_id,dict_name from taw_ws_dict where dict_type=2000"); //��רҵ������
        ResultSet rs = null;
        try {
            rs = stat.executeQuery(strSQL.toString());
            while (rs.next()) {
                list.add(new LabelValueBean(rs.getString("dict_name"), rs.getString("dict_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List getFaultClassList() {
        ArrayList list = new ArrayList();
        StringBuffer strSQL = new StringBuffer();
//    strSQL.append("");
//        ResultSet rs = null;
//        try {
//            rs = stat.executeQuery(strSQL.toString());
//            while (rs.next()) {
//               list.add(new LabelValueBean(StaticMethod.dbNull2String(rs.getString("report_business_name")),rs.getString("report_id")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        list.add(new LabelValueBean("紧急", "4"));
        list.add(new LabelValueBean("重要", "3"));
        list.add(new LabelValueBean("一般", "2"));
        list.add(new LabelValueBean("次要", "1"));
        return list;
    }
    public List getCombinTypeList() {
        ArrayList list = new ArrayList();
        StringBuffer strSQL = new StringBuffer();
//    strSQL.append("");
//        ResultSet rs = null;
//        try {
//            rs = stat.executeQuery(strSQL.toString());
//            while (rs.next()) {
//               list.add(new LabelValueBean(StaticMethod.dbNull2String(rs.getString("report_business_name")),rs.getString("report_id")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        
//      wangsixuan add:
        list.add(new LabelValueBean("不合并", "0"));
        
        list.add(new LabelValueBean("叠加", "2"));
        list.add(new LabelValueBean("追加","1"));

        return list;
    }
  public String getAcceptDeptName(SchemeMgrForm schemeForm){
	  String AcceptDeptName="";
	  String deptName=schemeForm.getAcceptDeptName();
	  String deptId=schemeForm.getAcceptDeptId();
	  String name[]=deptName.split(",");
	  String id[]=deptId.split(",");
	  for(int i=0;i<name.length;i++){
		  AcceptDeptName=AcceptDeptName+",{\"nodeType\":\"dept\",\"name\":\""+name[i]+"\",\"id\":\""+id[i]+"\"}";
	  }
	  AcceptDeptName=AcceptDeptName.substring(1, AcceptDeptName.length());
	  AcceptDeptName="["+AcceptDeptName+"]";
	  return AcceptDeptName;
	  
  }
  //返回审核人
  public String getAuditUserName(SchemeMgrForm schemeForm){
	  String AuditUserName="";
	  String deptName=schemeForm.getAuditUserName();
	  String deptId=schemeForm.getAuditUserId();
	  if(deptId!=null&&!"".equals(deptId))
	  {
	  String name[]=deptName.split(",");
	  String id[]=deptId.split(",");
	  for(int i=0;i<name.length;i++){
		  AuditUserName=AuditUserName+",{\"nodeType\":\"user\",\"name\":\""+name[i]+"\",\"id\":\""+id[i]+"\"}";
	  }
	  AuditUserName=AuditUserName.substring(1, AuditUserName.length());
	  AuditUserName="["+AuditUserName+"]";
	  return AuditUserName;
	  }else{
		  return "[]";
	  }
	  
  }
//返回接收人
  public String getReportUserName(SchemeMgrForm schemeForm){
	  String ReportUserName="";
	  String deptName=schemeForm.getReportUserName();
	  String deptId=schemeForm.getReportUserId();
	  if(deptId!=null&&!"".equals(deptId))
	  {
	  String name[]=deptName.split(",");
	  String id[]=deptId.split(",");
	  for(int i=0;i<name.length;i++){
		  ReportUserName=ReportUserName+",{\"nodeType\":\"user\",\"name\":\""+name[i]+"\",\"id\":\""+id[i]+"\"}";
	  }
	  ReportUserName=ReportUserName.substring(1, ReportUserName.length());
	  ReportUserName="["+ReportUserName+"]";
	  return ReportUserName;
	  }else{
		  return "[]";
	  }
  }
  
}
