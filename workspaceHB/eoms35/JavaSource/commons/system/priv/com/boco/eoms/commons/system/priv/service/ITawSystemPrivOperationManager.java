package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;

public interface ITawSystemPrivOperationManager extends Manager {
	/**
	 * Retrieves all of the tawSystemPrivOperations
	 */
	public List getTawSystemPrivOperations(
			TawSystemPrivOperation tawSystemPrivOperation);

	/**
	 * Gets tawSystemPrivOperation's information based on id.
	 * 
	 * @param id
	 *            the tawSystemPrivOperation's id
	 * @return tawSystemPrivOperation populated tawSystemPrivOperation object
	 */
	public TawSystemPrivOperation getTawSystemPrivOperation(final String id);

	/**
	 * Saves a tawSystemPrivOperation's information
	 * 
	 * @param tawSystemPrivOperation
	 *            the object to be saved
	 */
	public void saveTawSystemPrivOperation(
			TawSystemPrivOperation tawSystemPrivOperation);

	/**
	 * Removes a tawSystemPrivOperation from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivOperation's id
	 */
	public void removeTawSystemPrivOperation(final String id);

	public Map getTawSystemPrivOperations(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivOperations(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 得到某一模块下的所有对象（包括子模块与功能项,包括该模块）
	 */
	public List getAllSubObjects(String code);

	/**
	 * 得到某一模块下的所有子模块 （包括子模块的子模块，,包括该模块）
	 */
	public List getAllSubModules(String code);

	/**
	 * 得到某一模块下的所有功能项（包括子模块的功能项）
	 */

	public List getAllSubOperations(String code);

	/**
	 * 得到某一模块下的对象（仅仅是有关该模块的）
	 */
	public List getObjects(String code);

	/**
	 * 得到某一模块下的功能项（仅仅是有关该模块的）
	 */
	public List getOperations(String code);

	/**
	 * 得到某一模块下的子模块(仅仅是有关该模块的)
	 */

	public List getModules(String code, String deleted);

	/**
	 * 根据ID判断是否是模块（String id）
	 */

	public boolean isModule(String id);

	/**
	 * 得到该模块下下一个可以设置的CODE值
	 * 
	 * @param fatherId
	 * @return
	 */
	public String getCodeValueForModify(String fatherId);

	/**
	 * 得到某一模块的直接父模块
	 * 
	 * @param childObjectCode
	 * @return
	 */
	public TawSystemPrivOperation getFatherModule(String childObjectCode);

	/**
	 * 得到某一模块的所有父模块集合
	 */
	public List getAllFatherModules(String code);

	/**
	 * 得到所有的对象(根模块code=10,包括根模块)
	 */
	public List getAllObjects();

	/**
	 * 根据模块或功能项编码，获得对象
	 */
	public TawSystemPrivOperation getTawSystemPrivOpt(String code);

	public String getParentModuleName(String _strParentId);

	public List getDirectSubModules(String _strParentId);

	public void removeTawSystemPrivOperationByCode(String _strCode);
	
	//2009.03.24加，删除节点，顺带删除所有子节点
    public void removeAllNodeByCode(final String _strCode);

	/**
	 * 根据CODE取得对应的NAME
	 * 
	 * @param code
	 * @return
	 */
	public TawSystemPrivOperation getPrivOperationbyCode(String code);

	/**
	 * 根据父id取其子菜单
	 * 
	 * @param parentCode
	 *            父菜单id
	 * @return 子菜单list
	 */
	public List getTawSystemPrivOerationsByParentCode(String parentCode);

	/**
	 * 得到某一模块下的所有未删除未隐藏的对象 （包括子模块的子模块，,包括该模块）
	 */
	public List getAllEnableSubObjects(String code);


}
