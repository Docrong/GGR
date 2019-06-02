package com.boco.eoms.km.expert.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.km.expert.model.KmExpertCet;

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
public class KmExpertCetDisplaytagDecorator extends TableDecorator {
	/**
	 * id属性的checkbox
	 * 
	 * @return 一个带有checkbox的属性
	 */
	public String getId() {		
		KmExpertCet kmExpertCet = (KmExpertCet) getCurrentRowObject();
		return "<input type='checkbox' id='" + kmExpertCet.getId()
				+ "' name='ids' value='" + kmExpertCet.getId() + "'>";
	}
}
