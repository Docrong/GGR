package com.boco.eoms.km.expert.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.km.expert.model.KmExpertEdu;

/**
 * <p>
 * Title:tawDutyEventList.jsp页面分页显示checkbox
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-03-26
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertEduDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	public String getId() {		
		KmExpertEdu kmExpertEdu = (KmExpertEdu) getCurrentRowObject();
		return "<input type='checkbox' id='" + kmExpertEdu.getId()
				+ "' name='ids' value='" + kmExpertEdu.getId() + "'>";
	}
}
