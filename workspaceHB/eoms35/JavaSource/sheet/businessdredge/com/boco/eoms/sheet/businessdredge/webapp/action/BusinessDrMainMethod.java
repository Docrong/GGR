package com.boco.eoms.sheet.businessdredge.webapp.action;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;

public class BusinessDrMainMethod extends BaseSheet {

    public Map getAttachmentAttributeOfOjbect() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getProcessOjbectAttribute() {
        // TODO Auto-generated method stub
        return null;
    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("=======wanghao===SystemOut.log");
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            String jndiName = "jdbc/eoms";

            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.ibm.websphere.naming.WsnInitialContextFactory");
            InitialContext ic = new InitialContext(env);
            DataSource ds = (DataSource) ic.lookup(jndiName);
            conn = ds.getConnection();
            String sql = "select * from commonfault_main_piid";
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            WPSSecutiryServiceImpl safeService = new WPSSecutiryServiceImpl();
            javax.security.auth.Subject subject = safeService.logIn(sessionform.getUserid(), "111");
            HashMap sessionMap = new HashMap();
            sessionMap.put("wpsSubject", subject);
            sessionMap.put("userId", sessionform.getUserid());
            sessionMap.put("password", "111");
            StringBuffer sqls = new StringBuffer();
            while (rs.next()) {
                //根据Piid取出对应的piid
                Map variablemainMap = getBusinessFlowService().getVariable(rs.getString(1), "main", sessionMap);
                CommonFaultMain main = new CommonFaultMain();
                SheetBeanUtils.populate(main, variablemainMap);
                sqls.append(saveSql(conn, variablemainMap, main) + ";");
            }

            //可以将 sqls.toString()用IO写成文件
            request.setAttribute("sqls", sqls.toString());
        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (RuntimeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

//		IBusinessDredgeMainManager ser = (IBusinessDredgeMainManager) ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");
//		ser.saveOrUpdateMain(main);

        return null;
    }


    private String saveSql(Connection conn, Map map, Object obj)
            throws Exception {
        // 将任务表结构信息
        String tableName = "commonfault_mains";
        String columnNames = HibernateConfigurationHelper.getColumnNames(obj.getClass());
        String PkColumnName = HibernateConfigurationHelper.getPkColumnName(obj.getClass());
        String propNames = PkColumnName + "," + HibernateConfigurationHelper.getPropNamesWithoutPK(obj.getClass());
//		System.out.println("tableName="+tableName+"columnNames="+columnNames+"PkColumnName="+PkColumnName+"propNames="+propNames);

        // 先查询是否存在任务记录
        try {
            String id = StaticMethod.nullObject2String(map.get(PkColumnName));
            String insertSql = "insert into " + tableName + "(";
            String value = "";
            String[] column = columnNames.split(",");
            for (int i = 0; i < column.length; i++) {
                String tempColumn = column[i];
                String propName = HibernateConfigurationHelper.getPropNameByColumnName(obj.getClass(), tempColumn);
                Object tempObject = map.get(propName);
                if (tempObject != null) {
                    insertSql = insertSql + tempColumn + ",";
                    if (!value.equals("")) {
                        value = value + ",";
                    }
                    if (tempObject.getClass().getName().equals("java.util.Date")) {
                        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
                        value = value + "to_date('" + df.format(tempObject) + "','YYYY-MM-DD hh24:mi:ss')";
                    } else if (tempObject.getClass().getName().equals("java.lang.Integer")) {
                        value = value + tempObject;
                    } else {
                        value = value + "'" + tempObject + "'";

                    }
                }
            }
            if (insertSql.indexOf(",") > 0)
                insertSql = insertSql.substring(0, insertSql.lastIndexOf(","));
            value = ",sendyear,sendmonth,sendday) values (" + value + ",'2010','07','15')";
            insertSql = insertSql + value;
            System.out.println("insertSql=" + insertSql);
//			Statement st = conn.createStatement();
//			st.execute(insertSql);
//			st.close();
            return insertSql;
//			pst = conn.prepareStatement(insertSql);
//			String[] prop = propNames.split(",");
//			int k = 1;
//			for (int i = 0; i < prop.length; i++) {
//				Object tempObject = null;
//				if (prop[i].equals(PkColumnName) && id.equals("")) {
//					tempObject = UUIDHexGenerator.getInstance().getID();
//				} else {
//					tempObject = map.get(prop[i]);
//				}
//				if (tempObject != null) {
//					System.out.println("propvalue  name=" + prop[i] + "values="	+ tempObject.toString()+"type="+tempObject.getClass().getName());
//					if("java.util.Date".equals(tempObject.getClass().getName())){
//						Date date = (Date) tempObject;
//						pst.setDate(k, date);
//					}else
//					pst.setObject(k, tempObject);
//					k++;
//				} else {
//					System.out.println("column name=" + prop[i]	+ "value is null");
//				}
//			} 
        } catch (Exception ex) {
            System.out.println("--------------savesql is error!-------------------" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

}
