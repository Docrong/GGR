package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:仪器仪表
 * </p>
 * <p>
 * Description:仪器仪表
 * </p>
 * <p>
 * Wed Feb 04 16:31:56 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public interface TawApparatusDao extends Dao {

    /**
    *
    *取仪器仪表列表
    * @return 返回仪器仪表列表
    */
    public List getTawApparatuss();
    
    /**
    * 根据主键查询仪器仪表
    * @param id 主键
    * @return 返回某id的仪器仪表
    */
    public TawApparatus getTawApparatus(final String id);
    
    /**
    *
    * 保存仪器仪表    
    * @param tawApparatus 仪器仪表
    * 
    */
    public void saveTawApparatus(TawApparatus tawApparatus);
    
    /**
    * 根据id删除仪器仪表
    * @param id 主键
    * 
    */
    public void removeTawApparatus(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawApparatuss(final Integer curPage, final Integer pageSize,
			final String whereStr);
    /**
	 * 判断仪表仪器编号是否唯一
	 * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
	 *      
	 */
	public Boolean isunique(final String apparatusId) ;
	/**
	 * name2Id，即字典id转为字典名称 added by fengshaohong
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String name2Id(final String dictName,final String parentDictId);
}