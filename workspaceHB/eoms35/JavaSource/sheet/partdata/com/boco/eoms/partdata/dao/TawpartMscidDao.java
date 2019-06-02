package com.boco.eoms.partdata.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.partdata.model.TawpartMscid;

/**
 * <p>
 * Title:MSCID码号管理
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 * 
 * @author fengshaohong
 * @version 3.6
 * 
 */
public interface TawpartMscidDao extends Dao {

    /**
    *
    *取MSCID码号管理列表
    * @return 返回MSCID码号管理列表
    */
    public List getTawpartMscids();
    
    /**
    * 根据主键查询MSCID码号管理
    * @param id 主键
    * @return 返回某id的MSCID码号管理
    */
    public TawpartMscid getTawpartMscid(final String id);
    
    /**
    *
    * 保存MSCID码号管理    
    * @param tawpartMscid MSCID码号管理
    * 
    */
    public void saveTawpartMscid(TawpartMscid tawpartMscid);
    
    /**
    * 根据id删除MSCID码号管理
    * @param id 主键
    * 
    */
    public void removeTawpartMscid(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawpartMscids(final Integer curPage, final Integer pageSize,
			final String whereStr);
    public List getM0byHeadNumber(final String headNumber);
    public List getM1byHeadNumber(final String headNumber);
    public List getIDbyHeadnumber(final String headNumber);
    public List getM2byHeadNumber(final String headNumber);
    public List getTawpartMscidsbyHeadnumber(final String headNumber);
    public void removeTawpartMscid(final String headNumber,final String numberM0,final String numberM1,final String numberM2);
}