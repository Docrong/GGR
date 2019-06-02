package com.boco.eoms.repository.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.webapp.form.TawLocalRepositoryForm;

public class SoftRepDisplaytagDecoratorHelper extends TableDecorator {
	public String getId() {
		TawLocalRepository obj =   (TawLocalRepository)getCurrentRowObject();
		return "<input type='checkbox' name='id'  value='" +obj.getNet()+","+obj.getSoftwareRepository()+"'>";
	}
}
