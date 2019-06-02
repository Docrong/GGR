package com.boco.eoms.parter.baseinfo.mainmetermgr.displaytag.support;

import org.displaytag.decorator.TableDecorator;
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
public class MainmetermgrListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */

	public String getId() {		
		Mainmetermgr mainmetermgr = (Mainmetermgr) getCurrentRowObject();
		return "<input type='checkbox' id='" + mainmetermgr.getId()
				+ "' name='ids' value='" + mainmetermgr.getId() + "'>";
	}
	
	public String getUid() {		
		Mainmetermgr mainmetermgr = (Mainmetermgr) getCurrentRowObject();
		return "<input type='radio' id='" + mainmetermgr.getId()
				+ "' name='uid' value='" + mainmetermgr.getId() + "'>";
	}
	// 在表单中生成 列头的序号
	int i = 1;
	public String getNum() {		
		String id=""+i;
		i++;
		return id;
	}

}
