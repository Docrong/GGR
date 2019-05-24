package com.boco.eoms.security.scheduler;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.security.service.dao.ITreeWithDeptDAO;
import com.boco.eoms.security.service.manager.DeptManager;
import com.boco.eoms.security.service.model.DeptDO;
import com.boco.eoms.security.service.model.TreeWithDept;

public class TawAdDeptScheduler implements Job {

	ITawSystemDeptManager tawSystemDeptManagerImpl;
	ITreeWithDeptDAO treeWithDeptDAO;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		executeIner();
	}

	public TawAdDeptScheduler() {
	}

	private void executeIner() {
		try {
			deptCompare();
		} catch (SQLException ex) {
			System.out.println("错误:-" + ex.getMessage());
		}
	}

	public synchronized void deptCompare() throws SQLException {
		BocoLog.info(this, 1, "开始同步AD部门树与本地数据库部门树！");
		try {
			// 中间表与AD相比较(比较notes)
			// 从AD中读取部门树(以AD为准,所以AD不变)
			Vector vDept = DeptManager.getSubDepartment();

			if (vDept != null) {
				// 读取中间表全部部门信息
				List treewithdeptList = treeWithDeptDAO.freedomSelect("", "",
						"", "", "0");
				if (treewithdeptList != null) {
					// 如果某部门在中间表存在,在AD不存在,在中间表逻辑删除该部门
					for (int i = 0; i < treewithdeptList.size(); i++) {
						boolean isExist = false;
						TreeWithDept twdMod = (TreeWithDept) treewithdeptList
								.get(i);
						String notes = twdMod.getNotes()
								+ "OU=nms_users,DC=NMS,DC=FJ,DC=CM";// 拼出原始notes
						for (int j = 0; j < vDept.size(); j++) {
							DeptDO deptDo = (DeptDO) vDept.get(j);
							if (deptDo.getNotes().equals(notes)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							// 不存在,逻辑删除.
							treeWithDeptDAO.logicDelete(StaticMethod
									.null2int(twdMod.getDeptId()));
						}
					}
				}

				// 如果某部门在AD存在,在中间表不存在,在中间表增加该部门
				for (int i = 0; i < vDept.size(); i++) {
					boolean isExist = false;
					DeptDO deptDo = (DeptDO) vDept.get(i);
					String notes = deptDo.getNotes();
					notes = notes.substring(0, notes.indexOf("OU=nms_users"));// 将部门树数据截取出来
					// 更新中间表全部部门信息
					treewithdeptList = treeWithDeptDAO.freedomSelect("", "",
							"", "", "0");
					if (treewithdeptList != null) {
						for (int j = 0; j < treewithdeptList.size(); j++) {
							TreeWithDept twdMod = (TreeWithDept) treewithdeptList
									.get(j);
							if (twdMod.getNotes().equals(notes)) {
								isExist = true;// 在中间表存在
								break;
							}

						}
						if (isExist == false) {
							String[] note = notes.split(",");
							String notesadd = "";
							TreeWithDept twdMod = null;
							for (int m = note.length - 1; m >= 0; m--) {//如查中间表中没有父结点，则插入父结点
								notesadd = note[m] + "," + notesadd;
								List list = treeWithDeptDAO.freedomSelect("",
										"", notesadd, "", "0");
								if (list != null && list.size() != 0) {
									twdMod = (TreeWithDept) list.get(0);
								} else {
									String parentId = "";
									String deptid = "";
									if (twdMod == null) {
										parentId = "-1";
									} else {
										parentId = twdMod.getDeptId();
									}

									if (parentId.equals("-1")) {
										deptid = "1";
									} else {
										deptid = (StaticMethod
												.null2Long(treeWithDeptDAO
														.getMaxSubDeptid(parentId)) + 1)
												+ "";
									}
									twdMod = new TreeWithDept();
									twdMod.setDeptId(deptid);
									twdMod.setDeptName(note[m].substring(3));
									twdMod.setNotes(notesadd);
									twdMod.setParentDeptId(parentId);
									twdMod.setDeleted(0);
									// 在数据库里增加记录
									treeWithDeptDAO.insert(twdMod);
								}
							}
						}
					}
					// deptDO.getNotes();
					// OU=合作伙伴,OU=部门树,OU=nms_users,DC=testnms,DC=fj,DC=cm
					// OU=国脉,OU=合作伙伴,OU=部门树,OU=nms_users,DC=testnms,DC=fj,DC=cm
					// OU=党群工作部,OU=人力资源部,OU=三明分公司,OU=福建公司,OU=部门树,OU=nms_users,DC=testnms,DC=fj,DC=cm

				}
			}

			// 本地与中间表相比较(比较deptid)
			// 从中间表中读取部门树(以中间表为准,所以中间表不变)
			List treewithdeptList = treeWithDeptDAO.freedomSelect("", "", "",
					"", "0");

			if (treewithdeptList != null) {

				// 从本地数据库中读取部门树
				List deptList = tawSystemDeptManagerImpl.getALLdept("-1", "0");
				// 如果某部门在本地存在,在中间表不存在,在本地删除该部门
				if (deptList != null) {
					for (int v = 0; v < deptList.size(); v++) {
						boolean isExist = false;
						TawSystemDept sysdept = (TawSystemDept) deptList.get(v);
						String deptid = sysdept.getDeptId();
						for (int k = 0; k < treewithdeptList.size(); k++) {
							TreeWithDept twdMod = (TreeWithDept) treewithdeptList
									.get(k);
							if (twdMod.getDeptId().equals(deptid)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							// 删除数据库
							tawSystemDeptManagerImpl
									.removeTawSystemDept(sysdept.getId());
							// 删除缓存
							tawSystemDeptManagerImpl
									.removeTawSystemDeptforCatch(sysdept
											.getId(),
											sysdept.getParentDeptid(), sysdept);
						}
					}
				}
				// 如果某部门在中间表存在,在本地不存在,在本地增加该部门
				for (int v = 0; v < treewithdeptList.size(); v++) {
					boolean isExist = false;
					TreeWithDept twdMod = (TreeWithDept) treewithdeptList
							.get(v);
					String deptid = twdMod.getDeptId();
					// 从本地数据库中更新部门树
					deptList = tawSystemDeptManagerImpl.getALLdept("-1", "0");
					if (deptList != null) {
						for (int k = 0; k < deptList.size(); k++) {
							TawSystemDept sysdept = (TawSystemDept) deptList
									.get(k);
							if (sysdept.getDeptId().equals(deptid)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							TawSystemDept sysdept = new TawSystemDept();
							sysdept.setDeptId(deptid);
							sysdept.setDeptName(twdMod.getDeptName());
							sysdept.setParentDeptid(twdMod.getParentDeptId());
							sysdept.setDeleted(twdMod.getDeleted() + "");
							tawSystemDeptManagerImpl.saveTawSystemDept(sysdept);
						}
					}
				}

			}

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

	public ITreeWithDeptDAO getTreeWithDeptDAO() {
		return treeWithDeptDAO;
	}

	public void setTreeWithDeptDAO(ITreeWithDeptDAO treeWithDeptDAO) {
		this.treeWithDeptDAO = treeWithDeptDAO;
	}

}
