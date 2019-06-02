package com.boco.eoms.security.scheduler;

/**
 * <p>Title: Eoms_3.0</p>
 * <p>Description: 2006-07-12</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: boco</p>
 * @author liangyaohan
 * @version 1.0
 */

import java.util.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.*;

// import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.security.service.dao.ITreeWithDeptDAO;
import com.boco.eoms.security.service.manager.UserManager;
import com.boco.eoms.security.service.model.TreeWithDept;
import com.boco.eoms.security.service.model.UserDO;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;

public class TawAdUserScheduler implements Job {
	public void execute(JobExecutionContext context)
			throws org.quartz.JobExecutionException {
		executeIner();
	}

	ITawSystemDeptManager tawSystemDeptManagerImpl;
	ITawSystemUserManager tawSystemUserManagerImpl;
	ITawSystemUserBo tawSystemUserBo;
	ITreeWithDeptDAO treeWithDeptDAO;

	public TawAdUserScheduler() {
	}

	private void executeIner() {
		try {
			userCompare();
		} catch (SQLException ex) {
			System.out.println("错误:-" + ex.getMessage());
		}
	}

	public synchronized void userCompare() throws SQLException {
		BocoLog.info(this, 1, "开始本地数据库用户与AD的比较！");
		// ConnectionPool ds = ConnectionPool.getInstance();
		try {
			// 从AD中读取全部的用户信息
			Vector vUser = UserManager.searchLdapUser("");

			// 从本地数据库中读取全部的用户信息

			List listDBUser = new ArrayList();
			listDBUser = tawSystemUserManagerImpl
					.getTawSystemUsers(new TawSystemUser());

			// System.out.println(StaticMethod.getCurrentDateTime() );
			// 本地用户与AD用户进行比较，如果不存在则删除本地用户，同时清除cache
			for (int i = 0; i < listDBUser.size(); i++) {
				LabelValueBean lvBean = (LabelValueBean) listDBUser.get(i);
				String strUserId = lvBean.getValue().trim();
				boolean flag = false;
				for (int j = 0; j < vUser.size(); j++) {
					UserDO userDo = (UserDO) vUser.get(j);
					String userId = userDo.getUserId().trim();

					if (strUserId.equals(userId)) {
						flag = true;
						break;
					}
				}

				if (!flag) {
					// 删除
					// System.out.println(strUserId);
					tawSystemUserBo.removeUserbyuserid(strUserId);
					BocoLog.info(this, 2, "删除本地用户：" + strUserId);
				}
			}
			BocoLog.info(this, 3, "本地数据库用户与AD的比较结束！");
			// System.out.println(StaticMethod.getCurrentDateTime() );

			// AD用户与本地用户进行比较，如果不存在则添加本地用户，同时添加cache
			BocoLog.info(this, 3, "AD用户与本地数据库的比较开始！");
			for (int j = 0; j < vUser.size(); j++) {
				UserDO userDo = (UserDO) vUser.get(j);
				String userId = userDo.getUserId().trim();
				boolean flag = false;
				for (int i = 0; i < listDBUser.size(); i++) {
					LabelValueBean lvBean = (LabelValueBean) listDBUser.get(i);
					String strUserId = lvBean.getValue().trim();

					if (strUserId.equals(userId)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					TawSystemUser systemuser = new TawSystemUser();

					String userDept = userDo.getNotes();

					userDept = userDept.substring(userDept.indexOf(",") + 1,
							userDept.indexOf("OU=nms_users"));// 截取适合的部门信息
					List deptList = treeWithDeptDAO.freedomSelect("", "",
							userDept, "", "0");// 从部门与用户的联系表中，查取部门信息
					String deptid = "1005"; // AD临时部门，如果部门与用户的关系表中没有取到相关的部门，则设置一个临时部门
					if (deptList != null && deptList.size() > 0) {
						TreeWithDept treeWithDept = (TreeWithDept) deptList
								.get(0);
						deptid = treeWithDept.getDeptId();
					}

					systemuser.setUserid(userId);
					systemuser.setUsername(userDo.getName());
					// systemuser.setPassword(userDo.getPassword());

					systemuser
							.setPassword(new Md5PasswordEncoder()
									.encodePassword(userDo.getPassword(),
											new String()));

					systemuser.setDeptid(deptid); // 
					tawSystemUserManagerImpl.saveTawSystemUser(systemuser);
					BocoLog.info(this, 2, "添加本地用户：" + userId);

				}

			}
			BocoLog.info(this, 3, "AD用户与本地数据库的比较结束！");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public ITawSystemDeptManager getTawSystemDeptManagerImpl() {
		return tawSystemDeptManagerImpl;
	}

	public void setTawSystemDeptManagerImpl(
			ITawSystemDeptManager tawSystemDeptManagerImpl) {
		this.tawSystemDeptManagerImpl = tawSystemDeptManagerImpl;
	}

	public ITawSystemUserManager getTawSystemUserManagerImpl() {
		return tawSystemUserManagerImpl;
	}

	public void setTawSystemUserManagerImpl(
			ITawSystemUserManager tawSystemUserManagerImpl) {
		this.tawSystemUserManagerImpl = tawSystemUserManagerImpl;
	}

	public ITawSystemUserBo getTawSystemUserBo() {
		return tawSystemUserBo;
	}

	public void setTawSystemUserBo(ITawSystemUserBo tawSystemUserBo) {
		this.tawSystemUserBo = tawSystemUserBo;
	}

	public ITreeWithDeptDAO getTreeWithDeptDAO() {
		return treeWithDeptDAO;
	}

	public void setTreeWithDeptDAO(ITreeWithDeptDAO treeWithDeptDAO) {
		this.treeWithDeptDAO = treeWithDeptDAO;
	}

}
