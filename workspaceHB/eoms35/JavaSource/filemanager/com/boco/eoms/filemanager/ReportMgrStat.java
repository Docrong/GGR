package com.boco.eoms.filemanager;

import com.boco.eoms.filemanager.form.StatForm;
import com.boco.eoms.filemanager.form.StatResultBean;
import com.boco.eoms.common.resource.Util;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-26
 * Time: 10:35:12
 * Boco Corporation
 * ���ţ�������ͨ����о�Ժ EOMS
 * ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class ReportMgrStat {
    public static final int STAT_TYPE_REPORT = 2;
    public static final int STAT_TYPE_DEPT = 1;
    Connection conn = null;
    Statement stat = null;
    StatForm form = null;

    public ReportMgrStat(StatForm form) {
        this.form = form;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    String getStatSql() {
        StringBuffer sb = new StringBuffer();
        String clause = "";
        if (!Util.isNull(form.getStartTime())) {
            clause += "  and a.send_time>='" + form.getStartTime() + "'";
        }
        if (!Util.isNull(form.getEndTime())) {
            clause += "  and send_time<='" + form.getEndTime() + "'";
        }
        if (!Util.isNull(form.getReport_id())) {
            StringBuffer tem_sb = getMutiData(form.getReport_id());
            if (tem_sb.length() > 0) {
                sb.append(" and a.report_id in(")
                        .append(tem_sb)
                        .append(")");
            }
        }
        if (!Util.isNull(form.getSort1_deptid())) {
            StringBuffer tem_sb = getMutiData(form.getSort1_deptid());
            if (tem_sb.length() > 0) {
                sb.append(" and b.mgr_dept in(")
                        .append(tem_sb)
                        .append(")");
            }
        }
        if (!Util.isNull(form.getSort2_deptid())) {
            StringBuffer tem_sb = getMutiData(form.getSort2_deptid());
            if (tem_sb.length() > 0) {
                sb.append(" and a.acccept_dept_id in(")
                        .append(tem_sb)
                        .append(")");
            }
        }
        clause += sb.toString();
        String sql = "";
        switch (form.getStatType()) {
            case 2:   //���������
                sql = "select b.report_id,b.report_name,count(*) as totalCount,\n" +
                        "        sum(case\n" +
                        "                when a.upload_time is null then 0\n" +
                        "                when a.overtime<1 then 1\n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as intimeCount,\n" +
                        "            sum(case\n" +
                        "                when a.upload_time is null then 0\n" +
                        "                when a.status=1 then 1\n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as validCount,\n" +
                        "          sum(case\n" +
                        "                when upload_time is null then 1            \n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as notAccept\n" +
                        "from taw_file_mgr_list a,taw_file_mgr_report b where a.report_id=b.report_id " + clause + "group by b.report_id,b.report_name  order by validCount desc,intimeCount desc,1";
                break;
            case 1:   //������ͳ��
                sql = "select acccept_dept_id,acccept_dept_name,count(*) as totalCount,\n" +
                        "        sum(case\n" +
                        "                when a.upload_time is null then 0\n" +
                        "                when a.overtime<1 then 1\n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as intimeCount,\n" +
                        "            sum(case\n" +
                        "                when a.upload_time is null then 0\n" +
                        "                when a.status=1 then 1\n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as validCount,\n" +
                        "          sum(case\n" +
                        "                when upload_time is null then 1            \n" +
                        "                else 0 \n" +
                        "                end\n" +
                        "            ) as notAccept\n" +
                        "from taw_file_mgr_list a,taw_file_mgr_report b where a.report_id=b.report_id " + clause + " group by acccept_dept_id,acccept_dept_name  order by validCount desc,intimeCount desc,1";
                break;
            default:

        }
        return sql;
    }

    public Vector getStatReulst() {
        Vector list = new Vector();
        ResultSet rs = null;
        try {
            rs = stat.executeQuery(getStatSql());
            while (rs.next()) {
                StatResultBean bean = new StatResultBean();
                int type = form.getStatType();
                switch (type) {
                    case STAT_TYPE_REPORT:
                        bean.setReportId(rs.getString("report_id"));
                        bean.setReportName(rs.getString("report_name"));
                        break;
                    case STAT_TYPE_DEPT:
                        bean.setAcceptDeptId(rs.getString("acccept_dept_id"));
                        bean.setAcceptDeptName(rs.getString("acccept_dept_name"));
                        break;
                }
                bean.setTotalCount(rs.getInt("totalCount"));
                bean.setNotAcceptCount(rs.getInt("notAccept"));
                bean.setIntimeCount(rs.getInt("intimeCount"));
                bean.setValidCount(rs.getInt("validCount"));
                bean.setIntimeRate(getRate(bean.getIntimeCount(), bean.getTotalCount()));
                bean.setValidRate(getRate(bean.getValidCount(), bean.getTotalCount()));
                list.add(bean);

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return list;

    }

    private String getRate(int count, int toalCount) {
        if (toalCount <= 0) return "0.0%";
        float temp = count * 1000 / toalCount;
        if (temp < 1) return "0.0%";
        StringBuffer percent = new StringBuffer(Math.round(temp) + "");
        percent.insert(percent.length() - 1, '.');
        return percent + "%";
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

    public static StringBuffer getMutiData(String source) {

        StringBuffer sb = new StringBuffer();
        String[] mutiData = source.split(",");
        return getMutiData(mutiData);
    }

    public static StringBuffer getMutiData(String[] mutiData) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mutiData.length; i++) {
            if (!Util.isNull(mutiData[i])) {
                sb.append("'")
                        .append(mutiData[i])
                        .append("'");
                if ((i < mutiData.length - 1))
                    sb.append(",");
            }
        }
        return sb;
    }
}
