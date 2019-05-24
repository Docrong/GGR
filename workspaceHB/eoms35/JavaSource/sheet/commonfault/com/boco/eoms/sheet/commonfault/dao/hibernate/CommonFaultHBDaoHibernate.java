package com.boco.eoms.sheet.commonfault.dao.hibernate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultHBDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;



public class CommonFaultHBDaoHibernate extends BaseSheetDaoHibernate implements ICommonFaultHBDAO {

	

	public void updateObject(String[] sheetkeys) throws HibernateException {
		// TODO Auto-generated method stub
		String piids="";
		String sheetids="";

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getSession().connection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sheetkeys.length; i++) {
					String piid[] = sheetkeys[i].split("&");
					if (i == sheetkeys.length - 1)
					{
						piids = piids + "'" + piid[0] + "'";
						sheetids = sheetids + "'" + piid[1] + "'";
					} else
					{
						piids = piids + "'" + piid[0] + "',";
						sheetids = sheetids + "'" + piid[1] + "',";
					}

				
				
//				String sql = "update commonfault_main set status = '-14' where piid = '" + sheetkeys[i] + "'";
//				stmt = conn.createStatement();
//				stmt.executeUpdate(sql);
			}
				String sqlmain = "update commonfault_main set status = '-14' where sheetid in (" + sheetids + ")";
				String sqltask = "update commonfault_task  set taskstatus = '5' where sheetid in (" + sheetids + ")";
				
				stmt.addBatch(sqlmain);
				stmt.addBatch(sqltask);
				stmt.executeBatch();
				conn.commit();
				System.out.println("------sqlmain---"+sqlmain);
				conn.setAutoCommit(true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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

	public List selectObject(final String excelsql, final Integer curPage, final Integer pageSize) {
		

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List resultList = new ArrayList();
				try{
	    	  Query query = session.createSQLQuery(excelsql).addEntity(CommonFaultMain.class);    	
	    		// 分页查询条
			 if(pageSize.intValue() != -1){
	    	     query.setFirstResult(pageSize.intValue()
						* (curPage.intValue()));

				query.setMaxResults(pageSize.intValue());
			 }
			      resultList = query.list();
			    }
		        catch(Exception e){
		        	System.out.println("------- list error!---------");
		        	e.printStackTrace();
		        	throw new HibernateException(" list error");
		        }
		        
			
				return resultList;
	      }
	    };
	    return (List)getHibernateTemplate().execute(callback);
		}

	
	public int selectCount(String s) {

	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    int count = 0;
	    try {
	      conn = getSession().connection();
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery(s);
	      while (rs.next())
	        count = rs.getInt(1);
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (stmt != null) {
	          stmt.close();
	        }

	        if (conn != null)
	          conn.close();
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return count;
	}

	public void deleteFordel(String[] sheetkeys) {
		Connection conn = null;
	    Statement stmt = null;
	    try {
	      conn = getSession().connection();
	      stmt = conn.createStatement();
	      for (int i = 0; i < sheetkeys.length; i++) {
	        String[] sheetkey = sheetkeys[i].split("&");
	        String sql = "delete from commonfault_importfordel where sheetid = '" + sheetkey[1] + "'";
	        stmt.addBatch(sql);
	      }
	      stmt.executeBatch();
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (stmt != null) {
	          stmt.close();
	        }

	        if (conn != null)
	          conn.close();
	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	  }
	

	


/*	public boolean ifAuto(String mainnetsorttwo, String mainnetsortthree, String mainfaultresponselevel, String todeptid) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = this.getSession().connection();
				String sql = "select count(*) c from autoT2rule where (mainnetsorttwo = '" + mainnetsorttwo + "' or mainnetsortthree = '" + mainnetsortthree + "') and mainfaultresponselevel ='" + mainfaultresponselevel + "' and (todeptid ='" + todeptid + "' or todeptid = '18')";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					count = rs.getInt("c");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}*/
	/*
	public String getSubRoleId(String mainnetsorttwo, String mainnetsortthree, String todeptid) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String id = "";
		try {
			conn = this.getSession().connection();
				String sql1 = "select id from taw_system_sub_role where type3 = '" + mainnetsortthree + "' and area ='" + "1805" + "'";
				String sql2 = "select id from taw_system_sub_role where type2 = '" + mainnetsorttwo + "' and area ='" + todeptid + "'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					id = rs.getString("id");
				}
				if ("".equals(id)) {
					rs = stmt.executeQuery(sql2);
					while (rs.next()) {
						id = rs.getString("id");
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}*/
	
/*	public void inserNetOpt(String sheetkey) {
	//    Session session =null; 
		Connection con = null;
	//    Transaction tr = null;
//	    PreparedStatement ps = null; 
	    Statement stmt = null;
	    ResultSet rs = null;
	    String sql ="";
		try {
			con = this.getSession().connection();
			con.setAutoCommit(false);
	  	if (!"".equals(sheetkey)&& sheetkey!=null) {
				sql = "SELECT ID,sheetid,title FROM commonfault_main where id = '" + sheetkey + "'";						
		   }
	  	System.out.println("--------sql netOpt 123---------"+sql);	
	  	stmt = con.createStatement();
	  	stmt.addBatch(sql);
	    int[] o =stmt.executeBatch();
	    System.out.println("------netOpt--"+o[0]);
		con.commit();
		con.setAutoCommit(true);
	  	 session = getHibernateTemplate().getSessionFactory().openSession(); 
	     tr = session.beginTransaction();           
	     con = session.connection(); 
         ps = con.prepareStatement(sql);     
         ps.executeUpdate();        
         tr.commit();        
         session.close();      
  //       con.close();         
  //       ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}*/

}
