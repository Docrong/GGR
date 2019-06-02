package com.boco.eoms.sheet.autodistribute.dao;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 * 
 * @author 贾雷
 * @version 1.0
 * 
 */
public interface IAutoDistributeDAO extends Dao{
	 /**
    * 保存AutoDistribute
    * @param AutoDistribute
    */    
   public void saveAutoDistribute(AutoDistribute autoDistribute) throws HibernateException;
   /**
    * 删除AutoDistribute
    * @param id
    */  
   public void removeAutoDistribute(String id) throws HibernateException;
   /**
    * 根据id查询autodistribute记录
    * @param id
    * @return AutoDistribute
    */
   public AutoDistribute getAutoDistribute(String id) throws HibernateException;
	/**
	 * 得到所有的记录
	 */
	public HashMap getAllAutoDistribute(Integer pageIndex, Integer pageSize) throws HibernateException;
}
