/*
 * Created on 2007-12-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.DictTagHelper;
import com.boco.eoms.commons.system.dict.model.IDictTagHelper;
import com.boco.eoms.commons.system.dict.tags.IDictTagStrategyHelper;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.role.util.RoleModel;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SelectRoleType implements IDictTagStrategyHelper {
	private Logger logger = Logger.getLogger(SelectRoleType.class);

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.dict.tags.IDictTagStrategyHelper#doEndTag(com.boco.eoms.commons.system.dict.model.IDictTagHelper, javax.servlet.jsp.PageContext)
	 */
	public int doEndTag(IDictTagHelper dictTag, PageContext pageContext) throws JspException {
		// TODO Auto-generated method stub
		JspWriter out = pageContext.getOut();
		Element rootElement = new Element("select");
		rootElement.setAttribute("name", dictTag.getSelectId());
        rootElement.setAttribute("id", dictTag.getSelectId());
        if(dictTag.getAlt()!=null && !dictTag.getAlt().equals("")){
            rootElement.setAttribute("alt", dictTag.getAlt());
        }
        if(dictTag.getCls()!=null && !dictTag.getCls().equals("")){
            rootElement.setAttribute("class", StaticMethod.null2String(dictTag
                .getCls()));
        }
        if(dictTag.getStyle()!=null && !dictTag.getStyle().equals("")){
            rootElement.setAttribute("style", StaticMethod.null2String(dictTag
                .getStyle()));
        }
        
        Element oElement = new Element("option");
        oElement.setAttribute("id", "");
        oElement.addContent("请选择");
        rootElement.addContent(oElement);
        
        try{
	        List modelList = RoleMapSchema.getInstance().getModelLists();
	        if(modelList!=null){
	        	for (Iterator it = modelList.iterator(); it.hasNext();) {
	                RoleModel item = (RoleModel) it.next();
	                //列表中的option加入
	                Element option = new Element("option");
	                option.setAttribute("id", item.getModelId());
	                option.setAttribute("value", item.getModelId());
	                option.addContent(item.getModelName());
	                rootElement.addContent(option);
	            }
	        }
        }catch(Exception err){
        	logger.error(err);
        }
		try {
            pageContext.getOut().print(JSONUtil.getStrElement(rootElement));
        } catch (IOException e1) {
            logger.error(e1);
        }
        return 0;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.dict.tags.IDictTagStrategyHelper#doStartTag(com.boco.eoms.commons.system.dict.model.DictTagHelper, javax.servlet.jsp.PageContext)
	 */
	public int doStartTag(DictTagHelper dictTag, PageContext pageContext) throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

}
