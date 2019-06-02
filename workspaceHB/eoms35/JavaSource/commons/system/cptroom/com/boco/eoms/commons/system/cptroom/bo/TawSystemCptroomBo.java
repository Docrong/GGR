package com.boco.eoms.commons.system.cptroom.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 权限分配的内部业务逻辑
 * 
 * @author panlong May 31, 2007 10:15:13 AM
 */
public class TawSystemCptroomBo {

	private TawSystemCptroomBo() {

	}

	private static TawSystemCptroomBo instance = null;

	public static TawSystemCptroomBo getInstance() {
		if( instance == null){
			instance = init();
		}
		return instance;
	}

	private static TawSystemCptroomBo init(){
		instance = new TawSystemCptroomBo();
		return instance;
	}
	/**
	 * @see 根据model删除
	 * @param tawSystemCptroom
	 */
	public void removeTawSystemCptroom(TawSystemCptroom tawSystemCptroom) {
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		cptroommanager.removeTawSystemCptroom(tawSystemCptroom);
	}
	/**
     * @author 孙圣泰
     * @see 通过机房ID、机房名称和删除标记取机房model
     * @param Integer id 机房ID
     * @param String roomname 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroom(Integer id ,String name, int deleted) {
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemCptroomManager");
		TawSystemCptroom cptroom = new TawSystemCptroom();
		cptroom = cptroommanager.getTawSystemCptroom(id, name, deleted);
		return cptroom;
	}

	/**
     * @author 孙圣泰
     * @see 通过机房ID、删除标记取机房model
     * @param Integer id 机房ID
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroomById(Integer id ,int deleted) {
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemCptroomManager");
		TawSystemCptroom cptroom = new TawSystemCptroom();
		cptroom = cptroommanager.getTawSystemCptroomById(id, deleted);
		return cptroom;
	}
	
	/**
     * @author 孙圣泰
     * @see 通过机房名称、删除标记取机房model
     * @param String String 机房名称
     * @param int deleted 删除标记
     * @return TawSystemCptroom 机房model
     */
	public TawSystemCptroom getTawSystemCptroomByRoomname(String name ,int deleted) {
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemCptroomManager");
		TawSystemCptroom cptroom = new TawSystemCptroom();
		cptroom = cptroommanager.getTawSystemCptroomByRoomname(name, deleted);
		return cptroom;
	}
	
	/**
	   * @see 根据机房ID，检索机房信息！
	   * @see 该方法是单表检索，只对taw_system_cptroom表检索。
	   * @param id  Integer  机房ID
	   * @return String类型值。
	   */
	public String getTawSystemCptroomName(Integer id) {
		String cptroom = "";
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		cptroom = cptroommanager.getTawSystemCptroomName(id);
		return cptroom;
	}
	/**
	   * @see 通过用户的部门编号、删除标志得到该部门的机房列表
	   * @author 孙圣泰
	   * @param  deptId  String  查询条件
	   * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	   */
	public List getTawSystemCptroomList(String deptid, int deleted) {
		ArrayList list = new ArrayList();
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		list = (ArrayList)cptroommanager.getTawSystemCptroomList(deptid,deleted);
		return list;
	}
	/**
     * @author 孙圣泰
     * @see 根据机房ID取机房地址
     * @param Integer id 机房ID
     * @return String 机房地址
     */
	public String getTawSystemCptroomAddress(Integer id) {
		String address = "";
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		address = cptroommanager.getTawSystemCptroomAddress(id);
		return address;
	}
	/**
     * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	 */
	public List getTawSystemCptroomList() {
		ArrayList list = new ArrayList();
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		list = (ArrayList)cptroommanager.getTawSystemCptroomList();
		return list;
	}
	/**
     * @author 孙圣泰
     * @see 通过用户的部门编号得到该部门的机房列表
     * @param deptid 部门ID
     * @return List 机房model的list
     */
	public List getTawSystemCptroomListByDeptid(String deptid) {
		ArrayList list = new ArrayList();
		ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemCptroomManager");
		list = (ArrayList)cptroommanager.getTawSystemCptroomListByDeptid(deptid);
		return list;
	}
	public List getNextLevelCptrooms(String parentid, String delid) {
		List list = null;
		try {
			ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemCptroomManager");
			list = new ArrayList();
			list = cptroommanager.getNextLevelCptrooms(parentid, delid);
		} catch (Exception ex) {
			BocoLog.error(TawSystemCptroomBo.class, " 查询机房 " + parentid
					+ " 的所有子机房信息出错 " + ex.getMessage());
		}
		return list;
	}
}
