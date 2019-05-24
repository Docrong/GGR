package com.boco.eoms.common.tree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticObject;
import com.boco.eoms.db.util.ConnectionPool;
//TODO 需要修改
public class ModuleTree {
	ConnectionPool pool = ConnectionPool.getInstance();

	Connection con = null;

	private StaticObject obj = StaticObject.getInstance();

	private static String cacheFlag;

	// private EomsJCS rmTreeCache;
	public ModuleTree() {
		init();
	}

	// 根据给出的参数，列出以该参数为父节点下面的模块
	public String strMTree(String id) {
		String strwktree = "";
		int node = 0;
		String tempTree = "";

		BocoLog.trace(this, 33, "建立" + "MODULETREE" + id + "树开始");
		tempTree = StaticMethod.nullObject2String(obj.getObject("MODULETREE"
				+ id));
		if ((tempTree.equals("")) || (tempTree == null)) {
			if (StaticMethod.null2int(id) < 0) {
				node = 0;
			} else {
				node = 1;
			}

			try {
				strwktree = Tree(id);
				obj.putObject("MODULETREE" + id, strwktree);
			} catch (Exception e) {
				BocoLog.error(this, 40, "建立树报错:", e);
			}
		} else {
			strwktree = tempTree;
		}
		BocoLog.trace(this, 62, "建立" + "MODULETREE" + id + "树结束");
		return strwktree;

	}

	private String Tree(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer strTree = new StringBuffer();

		String intDept_id;
		String strDeptName;
		String strUrl;
		String pId;
		try {
			con = pool.getConnection();
			int i = 0;
			String sql = "select id,name,url,parent_id from taw_rm_tree "
					+ "where hide='0' and id like \'" + id + "%\' order by id";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				intDept_id = rs.getString(1);
				strDeptName = rs.getString(2);
				strDeptName = StaticMethod.dbNull2String(strDeptName);
				strUrl = rs.getString(3);
				pId = rs.getString(4);

				if (strUrl == null || strUrl == "") {
					strUrl = "#";
				}

				strTree.append("Tree[" + i + "]=\"" + intDept_id + "|" + pId
						+ "|" + strDeptName + "|" + strUrl + "\";");

				i++;
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException sqle) {
			BocoLog.error(this, 101, "树的递归中关闭数据库连接报错", sqle);
		} finally {
			con = null;
		}

		return strTree.toString();
	}

	// 根据给出的参数，列出以该参数为父节点下面的模块
	public String strMTreeAll(String id) {
		String strwktree = "";
		int node = 0;
		String tempTree = "";

		try {
			strwktree = TreeAll(id);
		} catch (Exception e) {
			BocoLog.error(this, 40, "建立树报错:", e);
		}
		return strwktree;
	}

	private String TreeAll(String id) {

		StringBuffer strTree = new StringBuffer();

		String intDept_id;
		String strDeptName;
		String strUrl;
		String pId;
		List list;
		// TawRmTree tawRmTree;
		Object obj = null;
		try {
			// id= "";
			if (cacheFlag.equals("ON") && (id != null)) {

				// obj = rmTreeCache.get(id);

				if (obj == null) {
					// RmTreeDAO treeDAO = new RmTreeDAO();
					// list = treeDAO.TreeAllDAO(id);

					// rmTreeCache.put(id,list);
				} else {
					list = (List) obj;
				}
			} else {
				// RmTreeDAO treeDAO = new RmTreeDAO();
				// list = treeDAO.TreeAllDAO(id);
			}

			// for (int i = 0; i < list.size(); i++) {
			// tawRmTree = (TawRmTree)list.get(i);

			// intDept_id = tawRmTree.getId();
			// strDeptName = tawRmTree.getName();
			// strUrl = tawRmTree.getUrl();
			// pId = tawRmTree.getParentId();

			// if (strUrl == null || strUrl == "") {
			// strUrl = "#";
			// }

			// strTree.append("Tree[" + i + "]=\"" + intDept_id + "|" +
			// pId + "|" + strDeptName + "|" + strUrl + "\";");
			// }
		} catch (ClassCastException cce) {
		} catch (Exception e) {
			BocoLog.error(this, 101, "树的递归中报错", e);
		}

		return strTree.toString();
	}

	private void init() {
		try {
			cacheFlag = StaticMethod.getNodeName("SYSTEM.cache.switch");

			if (cacheFlag.equals("ON")) {
				// rmTreeCache =
				// EomsJCS.getInstance(StaticMethod.getNodeName("SYSTEM.cache.rmtreecache"));
			}
		} catch (Exception e) {
			BocoLog.error(this, 0, "导航树cache错误", e);
		}
	}

}
