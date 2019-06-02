package com.boco.eoms.parter.baseinfo.carmgr.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;


/**
 * <p>
 * Title:mainmetermgrList.jsp页面分页显示checkbox
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 27, 2008 11:00:23 AM
 * </p>
 * 
 * @author 曲静波  
 * @version 3.5.1
 * 
 */
public class CarmgrListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	int i = 1;
	public String getId() {		
		CarMgr carMgr = (CarMgr) getCurrentRowObject();
		return "<input type='checkbox' id='" + carMgr.getId()
				+ "' name='ids' value='" + carMgr.getId() + "'>";
	}
	public String getUid() {		    
		CarMgr carMgr = (CarMgr) getCurrentRowObject();
		return "<input type='radio' id='" + carMgr.getId()
				+ "' name='uid' value='" + carMgr.getId() + "'>";
	}
	public String getNum() {		
		String id=""+i;
		i++;
		return id; 
	}

}
