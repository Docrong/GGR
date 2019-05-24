package com.boco.eoms.commons.system.dept.service.bo;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.exception.TawSystemException;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.commons.loging.BocoLog;

public class TawSystemDeptBo {

	private TawSystemDeptBo() {

	}

	private static TawSystemDeptBo instance = null;
	TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();	

	public static TawSystemDeptBo getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static TawSystemDeptBo init() {
		instance = new TawSystemDeptBo();

		return instance;
	}

	/**
	 * Retrieves all of the tawSystemDepts
	 * 
	 * @throws TawSystemException
	 */
	public List getTawSystemDepts(TawSystemDept tawSystemDept)
			throws TawSystemException {
		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.getTawSystemDepts(tawSystemDept);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询部门所有信息 出错 "
					+ ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;
	}

	/**
	 * Gets tawSystemDept's information based on id.
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 * @return tawSystemDept populated tawSystemDept object
	 * @throws TawSystemException
	 */
	public TawSystemDept getTawSystemDept(Integer id) throws TawSystemException {

		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			sysdept = deptmag.getTawSystemDept(id);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询ID为 " + id + " 的部门信息出错");
			throw new TawSystemException(ex);
		}
		return sysdept;
	}

	/**
	 * Saves a tawSystemDept's information
	 * 
	 * @param tawSystemDept
	 *            the object to be saved
	 * @throws TawSystemException
	 */
	public void saveTawSystemDept(TawSystemDept tawSystemDept)
			throws TawSystemException {

		try {

			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			deptmag.saveTawSystemDept(tawSystemDept);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "保存部门信息出错");
			throw new TawSystemException(ex);
		}

	}

	/**
	 * Removes a tawSystemDept from the database by id
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 * @throws TawSystemException
	 */
	public void removeTawSystemDept(final Integer id) throws TawSystemException {

		try {

			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			deptmag.removeTawSystemDept(id);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 删除ID为 " + id + " 的部门信息出错");
			throw new TawSystemException(ex);
		}

	}

	/**
	 * 根据部门ID和删除标志 查询某部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public TawSystemDept getDeptinfobydeptid(String deptid, String delid)
			throws TawSystemException {

		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			sysdept = deptmag.getDeptinfobydeptid(deptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询部门ID为 " + deptid + " 的信息出错"
					+ ex.getMessage());
			throw new TawSystemException(ex);
		}
		return sysdept;
	}

	/**
	 * 根据部门名称和删除标志查询部门信息
	 * 
	 * @param deptname
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public TawSystemDept getDeptinfoBydeptname(String deptname, String delid)
			throws TawSystemException {

		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			sysdept = deptmag.getDeptinfoBydeptname(deptname, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询部门 " + deptname + " 信息出错");
			throw new TawSystemException(ex);
		}
		return sysdept;
	}

	/**
	 * 根据部门ID 和特殊排序标志 删除标志 查询同级别的部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public List getSamelevelDeptbyDeptids(String deptid, String delid,
			String ordercode) throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.getSamelevelDeptbyDeptids(deptid, ordercode, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询与部门ID " + deptid
					+ " 同级别的部门信息出错" + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	public List getSamelevelDeptbyDeptids(String deptid, String delid) throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			String ordercode = String.valueOf(deptmag.getDeptinfobydeptid(deptid, delid).getOrdercode());
			list = deptmag.getSamelevelDeptbyDeptids(deptid, ordercode, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询与部门ID " + deptid
					+ " 同级别的部门信息出错" + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 根据删除标志 查询部门的增删情况
	 * 
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public List geDeptdelInfos(String delid) throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.geDeptdelInfos(delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门的增删情况 出错"
					+ ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 查询某用户创建的部门情况
	 * 
	 * @param operuserid
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public List getDeptdelInfosByoperuserid(String operuserid, String delid)
			throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.getDeptdelInfosByoperuserid(operuserid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "查询用户 " + operuserid
					+ " 创建的部门情况 出错" + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 根据deptids 和 delid 进行IN查询部门信息
	 * 
	 * @param deptids
	 * @param delid
	 *            0表示查询实际存在的 1表示查询停职或被删除的
	 * @return
	 * @throws TawSystemException
	 */
	public List getDeptByinDeptidsandDelids(String deptids, String delid)
			throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.getDeptByinDeptidsandDelids(deptids, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 根据部门ID们 " + deptids
					+ " 进行IN查询部门信息 出错" + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 判断部门名称是否存在
	 * 
	 * @param deptname
	 * @return
	 * @throws TawSystemException
	 */
	public boolean getDeptnameIsExist(String deptname, String delid)
			throws TawSystemException {
		boolean flag = false;

		try {

			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			flag = deptmag.getDeptnameIsExist(deptname, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, "判断部门名称 " + deptname
					+ " 是否存在 出错" + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return flag;

	}

	/**
	 * 查询所有的部门名称
	 * 
	 * @return
	 * @throws TawSystemException
	 */
	public List getDeptnames() throws TawSystemException {

		List list = null;
		try {
			list = new ArrayList();
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");

			list = deptmag.getDeptnames();
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询所有的部门名称 出错"
					+ ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 查询部门负责人以及联系方式 格式为:联系人ID 部门手机号 部门EMAIL 部门座机号 部门传真
	 * 
	 * @param deptname
	 * @return
	 * @throws TawSystemException
	 */
	public String getDeptManager(String deptname, String delid)
			throws TawSystemException {

		String deptmanager = "";
		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			sysdept = getDeptinfoBydeptname(deptname, delid);
			deptmanager = sysdept.getDeptmanager();
			StringBuffer buf = new StringBuffer();

			buf.append("部门负责人为:" + deptmanager);
			buf.append(" 部门手机号为:" + sysdept.getDeptmobile());
			buf.append(" 部门EMAIL为:" + sysdept.getDeptemail());
			buf.append(" 部门座机号为:" + sysdept.getDeptphone());
			buf.append(" 部门传真为:" + sysdept.getDeptfax());
			deptmanager = buf.toString();
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptname
					+ " 负责人以及联系方式出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}

		return deptmanager;
	}

	/**
	 * 得到部门的临时负责人
	 * 
	 * @param deptname
	 * @return
	 * @throws TawSystemException
	 */
	public String gettDeptmporaryManager(String deptname)
			throws TawSystemException {
		String deptmanager = "";
		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			sysdept = getDeptinfoBydeptname(deptname,
					StaticVariable.UNSTRSELETED);
			deptmanager = sysdept.getTmporaryManager();
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptname
					+ " 临时负责人出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return deptmanager;
	}

	/**
	 * 设置部门临时负责人生效时间
	 * 
	 * @param deptname
	 * @param begintime
	 * @param endtime
	 * @throws TawSystemException
	 */
	public void saveDeptmporaryManangerTimes(String deptname, String begintime,
			String endtime) throws TawSystemException {

		TawSystemDept sysdept = null;
		try {
			sysdept = new TawSystemDept();
			sysdept = getDeptinfoBydeptname(deptname,
					StaticVariable.UNSTRSELETED);
			sysdept.setTmporarybegintime(begintime);
			sysdept.setTmporarystopTime(endtime);
			saveTawSystemDept(sysdept);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 设置部门 " + deptname
					+ " 临时负责人生效时间段出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}
	}

	/**
	 * 查询所有子部门的部门信息 不包括本身
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 * @throws TawSystemException
	 */
	public List getChilddepts(String pardeptid, String delid)
			throws TawSystemException {

		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getALLSondept(pardeptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + pardeptid
					+ " 的所有子部门信息出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	public List getNextLevecDepts(String deptid, String delid) {
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getNextLevecDepts(deptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptid
					+ " 的所有子部门信息出错 " + ex.getMessage());
		}
		return list;
	}
	//没有代维的部门 2008-12-2  wangsixuan
	public List getNextLevecDeptsNoPartner(String deptid, String delid) {
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getNextLevecDeptsNoPartner(deptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptid
					+ " 的所有子部门信息出错 " + ex.getMessage());
		}
		return list;
	}
	/**
	 * 得到是否是代维部门信息 2008-12-2 wangsixuan
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public boolean isPartner(String delid){
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			
		return deptmag.isPartner(delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + delid
					+ " 的所有子部门信息出错 " + ex.getMessage());
			return false;
		}
	}
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是非代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNameNoPartner(String word){
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.searchByNameNoPartner(word);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + word
					+ " 的信息出错 " + ex.getMessage());
		}
		return list;
	}
	
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNamePartner(String word,String contacter,String deptphone){
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.searchByNamePartner(word,contacter,deptphone);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + word
					+ " 的信息出错 " + ex.getMessage());
		}
		return list;
	}
	
	//代维子部门 2008-11-17 liujinlong
	public List getNextLevecDaiweiDepts(String deptid, String delid) {
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getNextLevecDaiweiDepts(deptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptid
					+ " 的所有子部门信息出错 " + ex.getMessage());
		}
		return list;
	}

	/**
	 * 查询父部门信息 只查询上一级的部门
	 * 
	 * @param deptid
	 * @return
	 * @throws TawSystemException
	 */
	public TawSystemDept getFdept(String deptid, String delid)
			throws TawSystemException {

		TawSystemDept currentdept = new TawSystemDept();
		TawSystemDept fdept = new TawSystemDept();
		try {
			currentdept = getDeptinfobydeptid(deptid, delid);
			fdept = getDeptinfobydeptid(currentdept.getParentDeptid(), delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptid
					+ " 的父部门信息出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return fdept;
	}

	/**
	 * 查询某部门属于哪个地区
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 * @throws TawSystemException
	 */
	public Integer getRegion(String deptid, String delid)
			throws TawSystemException {

		TawSystemDept sysdept = new TawSystemDept();
		sysdept = getDeptinfobydeptid(deptid, delid);
		Integer regionid = new Integer(-1);
		try{
		  regionid = sysdept.getRegionflag();
		}catch(Exception e){
		
		}
		return regionid;
	}

	/**
	 * 查询属于某地市的部门信息
	 * 
	 * @param regionid
	 * @param delid
	 * @return
	 * @throws TawSystemException
	 */
	public List getDeptRegion(Integer regionid, String delid)
			throws TawSystemException {

		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getDeptRegion(regionid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询地区或部门 "
					+ String.valueOf(regionid) + " 的部门信息出错 " + ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;

	}

	/**
	 * 查询所有的地市 返回的是所有地市的ID
	 * 
	 * @param delid
	 * @return
	 * @throws TawSystemException
	 */
	public List getAllRegion(String delid) throws TawSystemException {

		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			list = new ArrayList();
			list = deptmag.getAllRegion(delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询所有的地市出错 "
					+ ex.getMessage());
			throw new TawSystemException(ex);
		}
		return list;
	}

	/**
	 * 得到所有的父部门
	 * 
	 * @param deptid
	 * @return
	 * @throws TawSystemException
	 */
	public List getAllFdept(String deptid, String deleted)
			throws TawSystemException {

		List list = new ArrayList();
		ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		list = deptmag.getFdeptInfo(deptid, deleted);

		return list;

	}

	/**
	 * 查询所有子部门 LIKE查询
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLSondept(String deptid, String delid) {

		List list = null;

		ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		list = new ArrayList();
		list = deptmag.getALLSondept(deptid, delid);

		return list;
	}
	/**
	 * 查询所有子部门 值班用
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLdept(String deptid, String delid) {

		List list = null;

		ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		list = new ArrayList();
		list = deptmag.getALLdept(deptid, delid);

		return list;
	}

	/**
	 * 当某个部门恢复删除后需要将对应的父部门也恢复删除
	 * 
	 * @param list
	 */
	public void upDateDeletedFdept(ArrayList list) {
		if (list != null && list.size() > 0) {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept dept = new TawSystemDept();
				dept = (TawSystemDept) list.get(i);
				dept.setDeleted(StaticVariable.UNSTRSELETED);
				dept.setLeaf(StaticVariable.UNSTRLEAF);
				deptmag.saveTawSystemDept(dept);
			}
		}
	}

	/**
	 * 通过部门ID查询部门名称
	 * 
	 * @param deptid
	 * @return
	 * @throws TawSystemException
	 */
	public String getDeptnameBydeptid(String deptid) throws TawSystemException {
		if (deptid != null && !deptid.equals("")) {
			return getDeptinfobydeptid(deptid, StaticVariable.UNSTRSELETED)
					.getDeptName();
		} else {

			return null;
		}

	}
	public List getNextLikeDepts(String deptid, String delid) {
		List list = null;
		try {
			ITawSystemDeptManager deptmag = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManagerFlush");
//			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManagerFlush");
			list = new ArrayList();
			list = deptmag.getNextLikeDepts(deptid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemDeptBo.class, " 查询部门 " + deptid
					+ " 的所有子部门信息出错 " + ex.getMessage());
		}
		return list;
	}
	/**
	 * 代维部门用户树
	 * 2008-11-17 liujinlong
	 * @param deptid
	 * @return
	 */
	public JSONArray getDaiweiDeptUserTree(String deptid,String showType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) this.getNextLevecDaiweiDepts(deptid, "0");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				List flaguser = userrolebo
						.getUserBydeptids(subDept.getDeptId());
				List flagdept = this.getNextLevecDaiweiDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				json.put(jitem);

			}
			if(showType!=null&&showType.equals("user")){
				ArrayList userlist = new ArrayList();
				userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
				TawSystemUser sysuser = new TawSystemUser();
				if (userlist.size() > 0) {
					for (int j = 0; j < userlist.size(); j++) {
						sysuser = (TawSystemUser) userlist.get(j);
	
						JSONObject jitem = new JSONObject();
						jitem.put("id", sysuser.getUserid());
						jitem.put("text", sysuser.getUsername());
						jitem.put(UIConstants.JSON_NODETYPE, "user");
						jitem.put("mobile", sysuser.getMobile());
						jitem.put("iconCls", "user");
						jitem.put("leaf", 1);
						json.put(jitem);
					}
				}
			} 
		}catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

}
