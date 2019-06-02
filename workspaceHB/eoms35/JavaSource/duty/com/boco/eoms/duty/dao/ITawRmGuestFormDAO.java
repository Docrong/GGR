package com.boco.eoms.duty.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmGuestform;

/**
 * <p>
 * Title:进出机房管理
 * </p>
 * <p>
 * Description:进出机房管理
 * </p>
 * <p>
 * 2009-04-25
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 * 
 */
public interface ITawRmGuestFormDAO extends Dao {

	/**
	 * @boco.title 
	 * @boco.desc 
	 * @param tawRmGuestform
	 * @boco.modify panyunfu Apr 25, 2009
	 */
	public void save(final TawRmGuestform tawRmGuestform);
	
	/**
	 * @boco.title 
	 * @boco.desc 
	 * @param flag
	 * @return
	 * @boco.modify panyunfu Apr 25, 2009
	 */
	public List getUnChecklist(final int flag);
	
	/**
	 * @boco.title 
	 * @boco.desc 
	 * @param flag
	 * @return
	 * @boco.modify panyunfu Apr 25, 2009
	 */
	public List list(final String condition);
	
	
	/**
	 * 
	 * @boco.title 
	 * @boco.desc 
	 * @param id
	 * @return
	 * @boco.modify panyunfu Apr 25, 2009
	 */
	public TawRmGuestform getTawRmGuestform(final String id);
	
	
	/**
	 * @boco.title 
	 * @boco.desc 
	 * @param id
	 * @boco.modify panyunfu Apr 25, 2009
	 */
	public void deleteById(final String id);
	
	
	/**
	 * 
	 * @boco.title 
	 * @boco.desc 
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 * @boco.modify panyunfu Apr 28, 2009
	 */
	public Map getTawRmGuestForm(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
	
	
	/**
	 * 
	 * @boco.title 
	 * @boco.desc 
	 * @param conditionStr
	 * @return
	 * @boco.modify panyunfu Apr 28, 2009
	 */
	public String getCount(final String conditionStr);
}
