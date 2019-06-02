package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:油机信息
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public interface TawPartnerOilDao extends Dao {

    /**
    *
    *取油机信息列表
    * @return 返回油机信息列表
    */
    public List getTawPartnerOils();
    
    /**
    * 根据主键查询油机信息
    * @param id 主键
    * @return 返回某id的油机信息
    */
    public TawPartnerOil getTawPartnerOil(final String id);
    
    /**
    *
    * 保存油机信息    
    * @param tawPartnerOil 油机信息
    * 
    */
    public void saveTawPartnerOil(TawPartnerOil tawPartnerOil);
    
    /**
    * 根据id删除油机信息
    * @param id 主键
    * 
    */
    public void removeTawPartnerOil(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawPartnerOils(final Integer curPage, final Integer pageSize,
			final String whereStr);
    /**
	 * 判断油机编号是否唯一
	 * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
	 *      
	 */
	public Boolean isunique(final String oil_number);
	/**
	 * name2Id，即字典id转为字典名称 added by fengshaohong
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String name2Id(final String dictName,final String parentDictId);
}