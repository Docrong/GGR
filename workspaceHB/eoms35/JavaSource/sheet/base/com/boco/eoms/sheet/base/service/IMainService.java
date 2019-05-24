/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.qo.IWorkSheetQO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:31:29
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IMainService {

	public BaseMain getSingleMainPO(String id) throws Exception;

	public IMainDAO getMainDAO();

	public void setMainDAO(IMainDAO mainDAO);

	public BaseMain getMainObject();

	public void setMainObject(BaseMain mainObject);
    
    public String getFlowTemplateName();
    
    public void setFlowTemplateName(String flowTemplateName);

	public IWorkSheetQO getWorkSheetQO();
	
	public void setWorkSheetQO(IWorkSheetQO workSheetQO);

	//public IWorkSheetQO getWorkSheetQO(String workSheetQOBeanId);
	

	/**
	 * save main to DB
	 * 
	 * @param obj
	 *            ORM object
	 * @throws Exception
	 */
	public void addMain(Object obj) throws Exception;
	

	public void addMainAndLink(Object mainObj,Object linkObj) throws Exception;
	
	/**
	 * 取归档列表
	 * 
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @return
	 * @throws SheetException
	 */
	public abstract List getHolds(final Map condition,final Integer curPage, final Integer pageSize)
			throws SheetException;

	/**
	 * 取归档列表数量
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract Integer getHoldsCount() throws SheetException;
	

	/**
	 * 取某人启动流程列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @param condition TODO
	 * @return
	 * @throws SheetException
	 */
	public abstract HashMap getStarterList(String userId, final Integer curPage,
			final Integer pageSize, HashMap condition) throws SheetException;
	/**
	 * 取某人启动流程数量
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 * @throws SheetException
	 */
	public abstract Integer getStarterCount(String userId)
			throws SheetException;

	/**
	 * 获取工单流水号
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract String getSheetId() throws SheetException;

	/**
	 * 工单查询
	 * @param hsql 可以为空, 保留目的:sql语句可能从cache中得到, 作为引用参数返回值.
	 * @param actionMap 查询条件的Map,用来给QO赋值,得到sql语句 condition存放一些查询特殊条件
	 * @param total 查询结果总数,作为引用参数返回当前查询的结果总数 
	 * @return 返回List结果
	 * @throws SheetException
	 */
	public abstract List getQueryResult(String[] hsql, Map actionForm,Map condition,
			Integer curPage, Integer pageSize, int[] aTotal, String queryType)
			throws SheetException;
	
	public abstract List getQueryResult(String[] hsql,Map condition,
			Integer curPage, Integer pageSize, int[] aTotal, String queryType)
			throws SheetException;
	/**
	 * 工单列表查询
	 * @param hsql 可以为空, 保留目的:sql语句可能从cache中得到, 作为引用参数返回值.
	 * @param actionMap 查询条件的Map,用来给QO赋值,得到sql语句,condition 查询所需参数条件的map.
	 * @param total 查询结果总数,作为引用参数返回当前查询的结果总数 
	 * @return 返回List结果
	 * @throws SheetException
	 */
	public abstract List getQueryAclListResult(String[] hsql, Map actionForm,Map condition,
			Integer curPage, Integer pageSize, int[] aTotal, String userId, String deptId)
			throws SheetException;
	
	/**
	 * 通过工单的父流水号，获取工单mian对象
	 * @param parentSheetId 工单父流程流水号
	 * @return
	 * @throws SheetException
	 */
	public abstract BaseMain getMainObjByParentSheetId(String parentSheetId) throws SheetException;
	
	/**
	 * 通过工单的父流水号，获取工单mian 对象列表
	 * @param parentSheetId 工单父流程流水号
	 * @return
	 * @throws SheetException
	 */
	public abstract List getMainListByParentSheetId(String parentSheetId) throws SheetException;
	
	/**
	 * 根据用户ID查找出他的所有模板（带分页）
	 * @author wangjianhua
	 * @date 2008-07-22
	 * @param userId
	 * @return sheets列表
	 * @throws SheetException
	 */
	public abstract List getTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal) throws SheetException;
	
	/**
	 * find All Attachments by mainSheet and linkSheet
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param userId
	 * @return sheets列表
	 * @throws SheetException
	 */
	public abstract List getAllAttachmentsBySheet(String where) throws SheetException;

	
	public void saveOrUpdateMain(final BaseMain main);
	
	public abstract HashMap getListForAdmin(Map condition ,Integer startIndex,Integer length) throws Exception;	
	
	/**
	 * 取撤销列表数量
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract Integer getCancelCount() throws SheetException;
	
	/**
	 * 取撤销列表
	 * 
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @param condition TODO
	 * @return
	 * @throws SheetException
	 */
	public abstract List getCancelList(final Integer curPage, final Integer pageSize, HashMap condition)
			throws SheetException;
	
	/**
	 * 获取流程定义文件的路径 格式为classpath:config/business-config.xml
	 * @return roleConfigPath
	 */
	public abstract String getRoleConfigPath();
	
	
	/**
	 * 设置流程定义文件的路径 格式为classpath:config/business-config.xml
	 * @return roleConfigPath
	 */
	public abstract void setRoleConfigPath(String roleConfigPath);
 
	/**
	 * 通过工单编号获取main对象
	 * @param sheetId
	 * @return
	 */
	public BaseMain getMainBySheetId(String sheetId);
	
    public abstract BaseMain getSinglePOByProcessId(String processId)throws Exception;	
    
	 /**
	 * 删除mian对象
	 * @param baseMain main对象
	 * @return
	 */
	public abstract void removeMain(Object baseMain);
	/**
	 * 取隐藏工单列表
	 * 
	 * @param condition
	 *            保存main、link、task对象
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @return
	 * @throws SheetException
	 */
	public abstract HashMap getHideList(Map condition, final Integer curPage,
			final Integer pageSize) throws SheetException;
	 /**
	 * 根据条件查出mian对象
	 * @param baseMain main对象
	 * @return
	 */
	public abstract List getMainsByCondition(String condition);
	
	/**
	 * 清除当前session
	 */
	public void clearObjectOfCurrentSession();
	/**
	 * 当有两个相同标识不同实体时执行
	 */
    public void mergeObject(Object obj);
    
    public  int getXYZ(String id) throws Exception;
    /**
	 * 显示草稿
	 * @param condition
	 * @param roleIds
	 * @param flowTemplateName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * 整合用
	 */
	public List getDraftList(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, Object obj) throws Exception;

	/*
	 * 列表中列出由登录人员处理的已归档的工单
	 */
	public List getHoldedListForUser(Map condition, Integer curPage,Integer length, int[] aTotal, String userId, String deptId)throws Exception;

}
