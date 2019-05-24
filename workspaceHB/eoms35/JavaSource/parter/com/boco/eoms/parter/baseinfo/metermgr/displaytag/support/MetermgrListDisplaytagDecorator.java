package com.boco.eoms.parter.baseinfo.metermgr.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;


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
public class MetermgrListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */

	public String getId() {		
		Metermgr metermgr = (Metermgr) getCurrentRowObject();
		return "<input type='checkbox' id='" + metermgr.getId()
				+ "' name='ids' value='" + metermgr.getId() + "'>";
	}
	
	public String getUid() {		
		Metermgr metermgr = (Metermgr) getCurrentRowObject();
		return "<input type='radio' id='" + metermgr.getId()
				+ "' name='uid' value='" + metermgr.getId() + "'>";
	}
	// 在表单中生成 列头的序号
	int i = 1;
	public String getNum() {		
		String id=""+i;
		i++;
		return id;
	}

}
