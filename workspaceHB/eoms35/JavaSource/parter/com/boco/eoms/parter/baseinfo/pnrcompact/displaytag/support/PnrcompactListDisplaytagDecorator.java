package com.boco.eoms.parter.baseinfo.pnrcompact.displaytag.support;

import org.displaytag.decorator.TableDecorator;

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
public class PnrcompactListDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox  复选框
	 * 
	 * @return 一个带有checkbox的属性
	 */

	public String getId() {		
		Pnrcompact pnrcompact = (Pnrcompact) getCurrentRowObject();
		return "<input type='checkbox' id='" + pnrcompact.getId()
				+ "' name='ids' value='" + pnrcompact.getId() + "'>";
	}
	/**
	 * uid属性的radio  单选框
	 * 
	 * @return 一个带有radio的属性
	 */
	public String getUid() {		
		Pnrcompact pnrcompact = (Pnrcompact) getCurrentRowObject();
		return "<input type='radio' id='" + pnrcompact.getId()
				+ "' name='uid' value='" + pnrcompact.getId() + "'>";
	}
	// 在表单中生成 列头的序号
	int i = 1;
	public String getNum() {		
		String id=""+i;
		i++;
		return id;
	}

}
