package com.boco.eoms.partdata.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.partdata.model.TawPartFourteenSignal;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 * 
 * @author Josh
 * @version 3.5
 * 
 */
public interface TawPartFourteenSignalDao extends Dao {

    /**
    *
    *取14位信令点列表
    * @return 返回14位信令点列表
    */
    public List getTawPartFourteenSignals();
    
    /**
    * 根据主键查询14位信令点
    * @param id 主键
    * @return 返回某id的14位信令点
    */
    public TawPartFourteenSignal getTawPartFourteenSignal(final String id);
    
    /**
    *
    * 保存14位信令点    
    * @param tawPartLac 14位信令点
    * 
    */
    public void saveTawPartFourteenSignal(TawPartFourteenSignal tawPartFourteenSignal);
    
    /**
    * 根据id删除14位信令点
    * @param id 主键
    * 
    */
    public void removeTawPartFourteenSignal(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawPartFourteenSignals(final Integer curPage, final Integer pageSize,
			final String whereStr);
    
    public Map getExistTawPartFourteenSignals(final Integer startNum, final Integer EndNum
    		);
    
    public Map getAllTawPartFourteenSignals(final String whereStr);
}