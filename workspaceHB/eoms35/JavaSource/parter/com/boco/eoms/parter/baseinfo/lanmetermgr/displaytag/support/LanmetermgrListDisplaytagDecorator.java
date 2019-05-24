package com.boco.eoms.parter.baseinfo.lanmetermgr.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;


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
public class LanmetermgrListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	int i = 1;
	public String getId() {		
		Lanmetermgr lanmetermgr = (Lanmetermgr) getCurrentRowObject();
		return "<input type='checkbox' id='" + lanmetermgr.getId()
				+ "' name='ids' value='" + lanmetermgr.getId() + "'>";
	}
	public String getUid() {		    
		Lanmetermgr lanmetermgr = (Lanmetermgr) getCurrentRowObject();
		return "<input type='radio' id='" + lanmetermgr.getId()
				+ "' name='uid' value='" + lanmetermgr.getId() + "'>";
	}
	public String getNum() {		
		String id=""+i;
		i++;
		return id; 
	}

}
