package com.boco.eoms.commons.system.dept.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

public interface TawSystemDeptDao extends Dao {

	/**
	 * Retrieves all of the tawSystemDepts
	 */
	public List getTawSystemDepts(TawSystemDept tawSystemDept);

	/**
	 * Gets tawSystemDept's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 * @return tawSystemDept populated tawSystemDept object
	 */
	public TawSystemDept getTawSystemDept(final Integer id);

	/**
	 * Saves a tawSystemDept's information
	 * 
	 * @param tawSystemDept
	 *            the object to be saved
	 */
	public void saveTawSystemDept(TawSystemDept tawSystemDept);

	/**
	 * Removes a tawSystemDept from the database by id
	 * 
	 * @param id
	 *            the tawSystemDept's id
	 */
	public void removeTawSystemDept(final Integer id);

	/**
	 * 根据部门ID和删除标志 查询某部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfobydeptid(String deptid, String delid);

	/**
	 * 根据部门名称和删除标志查询部门信息
	 * 
	 * @param deptname
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfoBydeptname(String deptname, String delid);

	/**
	 * 根据部门ID 和特殊排序标志 删除标志 查询同级别的部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getSamelevelDeptbyDeptids(String deptid, String ordercode,
			String delid);

	/**
	 * 根据删除标志 查询部门的增删情况
	 * 
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List geDeptdelInfos(String delid);

	/**
	 * 查询某用户创建的部门情况
	 * 
	 * @param operuserid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getDeptdelInfosByoperuserid(String operuserid, String delid);

	/**
	 * 根据deptids 和 delid 进行IN查询部门信息
	 * 
	 * @param deptids
	 * @param delid
	 *            delid 0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getDeptByinDeptidsandDelids(String deptids, String delid);

	/**
	 * 判断部门名称是否存在
	 * 
	 * @param deptname
	 * @return
	 */
	public boolean getDeptnameIsExist(String deptname, String delid);

	/**
	 * 查询所有的部门名称
	 * 
	 * @return
	 */
	public List getDeptnames();
	public List getworkplanDeptnames();
	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDepts(String pardeptid, String delid);
	/**
	 * 得到下一级非代维部门信息
	 * 2008-12-2 wangsixuan
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDeptsNoPartner(String pardeptid, String delid);
	/**
	 * 得到是否是非代维部门信息 2008-12-2 wangsixuan
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public boolean isPartner(String delid);
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是非代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNameNoPartner(String word);
	
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNamePartner(String word,String contacter,String deptphone);
	/**
	 * 得到下一级代维子部门的部门信息
	 * 2008-11-17 liujinlong
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDaiweiDepts(String pardeptid, String delid); 

	/**
	 * 查询某个地区的部门信息
	 * 
	 * @param regionid
	 * @return
	 */
	public List getDeptRegion(Integer regionid, String delid);

	/**
	 * 查询所有的地市
	 * 
	 * @param delid
	 * @return
	 */
	public List getAllRegion(String delid);

	/**
	 * 查询所有子部门 LINK查询
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLSondept(String deptid, String delid);

	/**
	 * 查询所有子部门 值班用
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLdept(String deptid, String delid);

	/**
	 * 得到所有得父部门除了根
	 * 
	 * @param deptid
	 * @param len
	 * @param deleted
	 * @return
	 */
	public List getFdeptInfo(String deptid, int len, String deleted);

	/**
	 * 得到所有得父部门除了根 值班
	 * 
	 * @param deptid
	 * @param len
	 * @param deleted
	 * @return
	 */
	public List getFdeptInfoduty(String deptid, int len, String deleted);

	/*
	 * id2name，即部门id转为部门名称 added by qinmin
	 * 
	 * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(final String id) throws DictDAOException;

	/**
	 * 取根部门信息
	 * 
	 * @return 根部门信息
	 */
	public List getRootDept();

	/**
	 * 根据关键字查询部门名称，得到部门列表
	 * 
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByName(String word);

	public List getNextLikeDepts(String parentLinkId, String deleted);

	/**
	 * 查询某地域下的所有部门
	 * 
	 * @param areaId
	 *            地域id
	 * @return 某地域下的所有部门
	 */
	public List listDeptOfArea(String areaId);

	/**
	 * 分页取部门
	 * 
	 * @param curPage
	 *            要取的页码
	 * @param pageSize
	 *            每页显示条数
	 * @param whereStr
	 *            条件
	 * @return
	 */
	public Map getDepts(final Integer curPage, final Integer pageSize,
			final String whereStr);
	/**
	 * 仅适用于动态流程图所使用
	 * @author : wangjianhua
	 * @date : 2009-07-06
	 * @param id
	 * @return
	 */
	public TawSystemDept getTawSystemDeptById(Integer id);
	
	/**
	 * 根据linkID和删除标志 查询某部门信息
	 * 
	 * @param linkid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfobylinkid(String linkid, String delid);
	
}
