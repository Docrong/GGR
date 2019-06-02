package com.boco.eoms.security.scheduler;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.security.service.dao.ITreeWithAreaDAO;
import com.boco.eoms.security.service.manager.RegionManager;
import com.boco.eoms.security.service.model.RegionDO;
import com.boco.eoms.security.service.model.TreeWithArea;

public class TawAdAreaScheduler implements Job {

	ITawSystemAreaManager tawSystemAreaManager;
	ITreeWithAreaDAO treeWithAreaDAO;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		executeIner();
	}

	public TawAdAreaScheduler() {
	}

	private void executeIner() {
		try {
			deptCompare();
		} catch (SQLException ex) {
			System.out.println("错误:-" + ex.getMessage());
		}
	}

	public synchronized void deptCompare() throws SQLException {
		BocoLog.info(this, 1, "开始同步AD单位树与本地数据库地域树！");
		try {
			// 中间表与AD相比较(比较notes)
			// 从AD中读取单位树(以AD为准,所以AD不变)
			// Vector vDept = DeptManager.getSubDepartment();
			Vector vArea = RegionManager.getRegionList();
			if (vArea != null) {
				// 读取中间表全部地域信息
				List treewithareaList = treeWithAreaDAO.selectAllArea(0);
				if (treewithareaList != null) {
					// 如果某地域在中间表存在,在AD不存在,在中间表逻辑删除该地域
					for (int i = 0; i < treewithareaList.size(); i++) {
						boolean isExist = false;
						TreeWithArea twdMod = (TreeWithArea) treewithareaList
								.get(i);
						String notes = twdMod.getNotes()
								+ "OU=nms_users,DC=NMS,DC=FJ,DC=CM";// 拼出原始notes
						for (int j = 0; j < vArea.size(); j++) {
							RegionDO regionDO = (RegionDO) vArea.get(j);
							if (regionDO.getNotes().equals(notes)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							// 不存在,逻辑删除.
							treeWithAreaDAO.logicDelete(twdMod.getAreaId());
						}
					}
				}

				// 如果某部门在AD存在,在中间表不存在,在中间表增加该部门
				for (int i = 0; i < vArea.size(); i++) {
					boolean isExist = false;
					RegionDO regionDO = (RegionDO) vArea.get(i);
					String notes = regionDO.getNotes();
					notes = notes.substring(0, notes.indexOf("OU=nms_users"));// 将部门树数据截取出来
					// 更新中间表全部部门信息
					treewithareaList = treeWithAreaDAO.selectAllArea(0);
					if (treewithareaList != null) {
						for (int j = 0; j < treewithareaList.size(); j++) {
							TreeWithArea twdMod = (TreeWithArea) treewithareaList
									.get(j);
							if (twdMod.getNotes().equals(notes)) {
								isExist = true;// 在中间表存在
								break;
							}

						}
						if (isExist == false) {
							String[] note = notes.split(",");
							String notesadd = "";
							TreeWithArea twdMod = null;
							for (int m = note.length - 1; m >= 0; m--) {// 如查中间表中没有父结点，则插入父结点
								notesadd = note[m] + "," + notesadd;
								List list = treeWithAreaDAO.selectByNotes(
										notesadd, 0);
								if (list != null && list.size() != 0) {
									twdMod = (TreeWithArea) list.get(0);
								} else {
									String parentId = "";
									String areaid = "";
									if (twdMod == null) {
										parentId = "-1";
									} else {
										parentId = twdMod.getAreaId();
									}

									if (parentId.equals("-1")) {
										areaid = "1";
									} else {
										areaid = (StaticMethod
												.null2Long(treeWithAreaDAO
														.getMaxSubAreaid(parentId)) + 1)
												+ "";
									}
									twdMod = new TreeWithArea();
									twdMod.setAreaId(areaid);
									twdMod.setAreaName(note[m].substring(3));
									twdMod.setNotes(notesadd);
									twdMod.setParentAreaId(parentId);
									twdMod.setDeleted(0);
									// 在数据库里增加记录
									treeWithAreaDAO.insert(twdMod);
								}
							}
						}
					}
					// deptDO.getNotes();
					// “OU=单位树,OU=nms_users,DC=testnms,DC=fj,DC=cm"
					// "OU=合作伙伴,OU=单位树,OU=nms_users,DC=testnms,DC=fj,DC=cm"
					// 

				}
			}

			// 本地与中间表相比较(比较areaid)
			// 从中间表中读取地域树(以中间表为准,所以中间表不变)
			List treewithareaList = treeWithAreaDAO.selectAllArea(0);

			if (treewithareaList != null) {

				// 从本地数据库中读取地域树,现在平台还没有提供这个方法

				// List areaList = EOMSMgr.getOrgMgrs().getAreaMgr().getAreas();
				List areaList = EOMSMgr.getSysMgrs().getAreaMgr().listArea();
				// 如果某地域在本地存在,在中间表不存在,在本地删除该地域
				if (areaList != null) {
					for (int v = 0; v < areaList.size(); v++) {
						boolean isExist = false;
						TawSystemArea sysarea = (TawSystemArea) areaList.get(v);
						String areaid = sysarea.getAreaid();
						for (int k = 0; k < treewithareaList.size(); k++) {
							TreeWithArea twdMod = (TreeWithArea) treewithareaList
									.get(k);
							if (twdMod.getAreaId().equals(areaid)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							// 删除数据库
							tawSystemAreaManager
									.removeTawSystemArea(StaticMethod
											.nullObject2String(sysarea.getId()));

							// 删除缓存

						}
					}
				}
				// 如果某地域在中间表存在,在本地不存在,在本地增加该地域
				for (int v = 0; v < treewithareaList.size(); v++) {
					boolean isExist = false;
					TawSystemArea twdMod = (TawSystemArea) areaList.get(v);
					String areaid = twdMod.getAreaid();
					// 从本地数据库中更新地域树

					// areaList =EOMSMgr.getOrgMgrs().getAreaMgr().getAreas();
					areaList = EOMSMgr.getSysMgrs().getAreaMgr().listArea();
					if (areaList != null) {
						for (int k = 0; k < areaList.size(); k++) {
							TawSystemArea sysarea = (TawSystemArea) areaList
									.get(k);
							if (sysarea.getAreaid().equals(areaid)) {
								isExist = true;
								break;
							}
						}
						if (isExist == false) {
							TawSystemArea sysarea = new TawSystemArea();
							sysarea.setAreaid(areaid);
							sysarea.setAreaname(twdMod.getAreaname());
							sysarea.setParentAreaid(twdMod.getParentAreaid());
							// sysarea.setsetDeleted(twdMod.getDeleted() + "");
							tawSystemAreaManager.saveTawSystemArea(sysarea);
						}
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ITreeWithAreaDAO getTreeWithAreaDAO() {
		return treeWithAreaDAO;
	}

	public void setTreeWithAreaDAO(ITreeWithAreaDAO treeWithAreaDAO) {
		this.treeWithAreaDAO = treeWithAreaDAO;
	}

	public ITawSystemAreaManager getTawSystemAreaManager() {
		return tawSystemAreaManager;
	}

	public void setTawSystemAreaManager(
			ITawSystemAreaManager tawSystemAreaManager) {
		this.tawSystemAreaManager = tawSystemAreaManager;
	}

}
