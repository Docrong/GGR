package com.boco.eoms.sheet.businessimplement.webapp.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.db.util.BocoConnection;
import com.boco.eoms.km.core.hibernate.Query;
import com.boco.eoms.sheet.businessimplement.dao.hibernate.BusinessImplementTaskDAOHibernate;

public class UbcmirmsUtilTest extends BusinessImplementTaskDAOHibernate {

    public List findSql(final String sql) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                SQLQuery query = getSession().createSQLQuery(sql);
                System.out.println("select sql:" + sql);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public String save(final List list) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Connection conn = getSession().connection();
                try {
                    conn.setAutoCommit(false);
                    Statement sta = conn.createStatement();
                    for (int i = 0; i < list.size(); i++) {
                        String sql = list.get(i).toString();
                        System.out.println("insert sql:" + sql);
                        sta.addBatch(sql);
                    }
                    sta.executeBatch();
                    conn.commit();
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        return (String) getHibernateTemplate().execute(callback);
    }

    public String getCuid(String id) {
        String sql = "select cuid from ub_cm_irms.t_biz_customer where label_cn='" + id + "'";
        List list = this.findSql(sql);
        if (list != null && list.size() > 0) {
            return list.get(0).toString();
        }
        return "";
    }

    public void insertsql(HashMap map) throws HibernateException {
        super.insertsql(map);
        String custName = StaticMethod.nullObject2String(map.get("custName"));
        String cuid = this.getCuid(custName);
        String prodCode = StaticMethod.nullObject2String(map.get("prodCode"));
        String prodType = StaticMethod.nullObject2String(map.get("prodType"));
        String prodName = StaticMethod.nullObject2String(map.get("prodName"));
        String transCuid = StaticMethod.nullObject2String(map.get("transCuid"));
        String trnsName = StaticMethod.nullObject2String(map.get("trnsName"));
        String OpticalCuid = StaticMethod.nullObject2String(map.get("OpticalCuid"));
        String OpticalName = StaticMethod.nullObject2String(map.get("OpticalName"));
        if (cuid == null || "".equals(cuid)) {
            cuid = "T_BIZ_CUSTOMER_GROUP-" + prodCode;
        }
        List list = new ArrayList();
        String sql = "insert into ub_cm_irms.t_biz_customer(cuid,label_cn, related_bmclasstype_cuid) " +
                "values('" + cuid + "','" + custName + "', 'T_BIZ_CUSTOMER_GROUP')";
        list.add(sql);

        sql = "insert into ub_cm_irms.t_biz_product(cuid, label_cn, name, code, related_bmclasstype_cuid)" +
                "values('T_BIZ_PRODUCT_TRANLINE-" + prodCode + "', '" + prodName + "', '" + prodName + "'," +
                "'" + prodCode + "', 'T_BIZ_PRODUCT_TRANLINE')";
        list.add(sql);

        sql = "insert into ub_cm_irms.t_md_res_res(cuid, related_left_cuid, related_l_type_cuid, related_right_cuid, related_r_type_cuid, related_relation_cuid)" +
                "values('T_MD_RES_RES-T_BIZ_CUSTOMER_GROUP-T_BIZ_PRODUCT_TRANLINE-" + prodCode + "'," +
                "'" + cuid + "', 'T_BIZ_CUSTOMER_GROUP', 'T_BIZ_PRODUCT_TRANLINE-" + prodCode + "'," +
                "'T_BIZ_PRODUCT_TRANLINE', 'T_BIZ_CUSTOMER_GROUP@T_BIZ_PRODUCT_TRANLINE0')";
        list.add(sql);

        sql = "insert into ub_cm_irms.t_sys_temp_res(prod_code,prod_cuid,main_code,related_res_type,main_cuid,cuid)" +
                "values('" + prodCode + "','T_BIZ_PRODUCT_TRANLINE-" + prodCode + "','" + trnsName + "'," +
                "'1','" + transCuid + "','T_SYS_TEMP_RES-" + transCuid.substring(13) + "')";
        list.add(sql);

        sql = "insert into ub_cm_irms.t_sys_temp_res(prod_code,prod_cuid,main_code,related_res_type,main_cuid,cuid)" +
                "values('" + prodCode + "','T_BIZ_PRODUCT_TRANLINE-" + prodCode + "','" + OpticalName + "'," +
                "'2','" + OpticalCuid + "','T_SYS_TEMP_RES-" + prodCode + "')";
        list.add(sql);

        this.save(list);


    }

    public static void main(String[] args) {
        System.out.println("ATTEMP_TRAPH-".length());
    }
}
