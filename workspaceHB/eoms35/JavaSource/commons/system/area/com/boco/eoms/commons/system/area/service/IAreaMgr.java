package com.boco.eoms.commons.system.area.service;

import java.util.List;

import com.boco.eoms.commons.system.area.model.TawSystemArea;

/**
 * <p>
 * Title:区域管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 18, 2008 4:02:27 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public interface IAreaMgr {

	/**
	 * 取唯一地域
	 * 
	 * @param areaId
	 *            唯一标识符
	 * @return 地市
	 */
	public TawSystemArea getArea(String areaId);

	/**
	 * 取某部门的所在地
	 * 
	 * @param deptId
	 *            部门id
	 * @return 地市
	 */
	public TawSystemArea getAreaOfDept(String deptId);

	/**
	 * 取某地市是类型，包括：省，省会，直辖市
	 * 
	 * @param areaId
	 *            地域id
	 * @return 地市类型
	 */
	public String getAreaType(String areaId);

	/**
	 * 取某地域的上一级
	 * 
	 * @param areaId
	 *            地域id
	 * @return 某地域上级地域
	 */
	public TawSystemArea getParentArea(String areaId);

	/**
	 * 某地域上级的所有地域
	 * 
	 * @param areaId
	 *            地域id
	 * @return 地域上级的所有地域
	 */
	public List listParentAreas(String areaId);

	/**
	 * 地域（当前级）下的地域
	 * 
	 * @param areaId
	 *            地域id
	 * 
	 * @return 地域（当前级）下的地域
	 */
	public List listChildArea(String areaId);

	/**
	 * 某地域下的所有地域
	 * 
	 * @param areaId
	 *            地域id
	 * @return 某地域下的所有地域
	 */
	public List listAllChildArea(String areaId);

	/**
	 * 某用户所在地域
	 * 
	 * @param userId
	 *            用户id
	 * @return 某用户所在地域
	 */
	public TawSystemArea getAreaOfUser(String userId);

	/**
	 * 取所有地域信息
	 * 
	 * @return 地域列表
	 */
	public List listArea();

}
