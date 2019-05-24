package com.boco.eoms.parter.baseinfo.basemetermgr.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.pnrcompact.model.Pnrcompact;


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
public class BasemetermgrListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox  复选框
	 * 
	 * @return 一个带有checkbox的属性
	 */

	public String getId() {		
		Basemetermgr basemetermgr = (Basemetermgr) getCurrentRowObject();
		return "<input type='checkbox' id='" + basemetermgr.getId()
				+ "' name='ids' value='" + basemetermgr.getId() + "'>";
	}
	/**
	 * uid属性的radio  单选框
	 * 
	 * @return 一个带有radio的属性
	 */
	public String getUid() {		
		Basemetermgr basemetermgr = (Basemetermgr) getCurrentRowObject();
		return "<input type='radio' id='" + basemetermgr.getId()
				+ "' name='uid' value='" + basemetermgr.getId() + "'>";
	}
	// 在表单中生成 列头的序号
	int i = 1;
	public String getNum() {		
		String id=""+i;
		i++;
		return id;
	}

}
