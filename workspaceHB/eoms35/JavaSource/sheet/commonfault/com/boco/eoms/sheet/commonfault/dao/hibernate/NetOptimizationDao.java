package com.boco.eoms.sheet.commonfault.dao.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.BocoConnection;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.*;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.commonfault.dao.INetOptimizationDAO;
import com.boco.eoms.sheet.commonfault.db.NetOptimizationDB;


public class NetOptimizationDao extends DAO {

    public NetOptimizationDao(ConnectionPool ds) {
        super(ds);
    }

    public void inserNetOpt(String sheetkey) throws SQLException {
        String sql = "INSERT INTO t_commonfault_main (id,sheetid) select m.id,m.sheetid FROM commonfault_main m where m.id = '8a9982f24f25e613014f25e834520003'";
        //   String sql ="SELECT ID,sheetid,title FROM commonfault_main where id = '"+sheetkey+"'";
        //	String sql ="INSERT INTO t_commonfault_main(ID,sheetid,title) values(?,?,?)";
        System.out.println("--------sql netOpt ---------" + sql);

        BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            //	  pstmt.setString(1, "8a9e63934700d4ee01471bc547ad496b");
            //     pstmt.setString(2, sheetkey);
            //     pstmt.setString(3, sheetkey);
            System.out.println("------netOpt--2--");
            pstmt.executeUpdate();
            //    rs= pstmt.executeQuery();
            System.out.println("----SheetKey----" + sheetkey);
            //    while (rs.next()){
            //  	System.out.println("---------netOpt:result----"+ rs.getString("sheetid"));
            //  }

            conn.commit();
            System.out.println("------netOpt--3--");
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

	/*public Integer getCountByMainId(final String sheetkey)
	throws HibernateException {

HibernateCallback callback = new HibernateCallback() {
	public Object doInHibernate(Session session)
			throws HibernateException {

		// 取列表数量
		String queryCountStr = "select count(id) from commonfault_main where id = '" + sheetkey + "'";
		Query query = session.createQuery(queryCountStr);
		// TODO 归档标记，需确认
		Integer total = (Integer) query.iterate().next();
		System.out.println("----------------netOpt -----total "+total);
		return total;

	}
};
return (Integer) getHibernateTemplate().execute(callback);
}

*/

    public void main(String[] args) throws SQLException {
        String sheekey = "8a9e63934700d4ee01471bc547ad496b";
        inserNetOpt(sheekey);
    }

}
