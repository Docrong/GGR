package com.boco.eoms.sheet.commonfault.webapp.action;

import org.displaytag.decorator.TableDecorator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultAuto;

public class AutoHoldDisplaytagDecoratorHelper extends TableDecorator {
	public String getId() {
		CommonFaultAuto commonFaultAuto = (CommonFaultAuto) getCurrentRowObject();
		return "<input type='checkbox' id='" + commonFaultAuto.getId()
				+ "' name='ids' value='" + commonFaultAuto.getId() + "'>";
	}
}
