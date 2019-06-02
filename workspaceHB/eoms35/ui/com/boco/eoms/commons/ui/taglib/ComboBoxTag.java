package com.boco.eoms.commons.ui.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.ui.util.JSONUtil;

import org.apache.struts.taglib.TagUtils;
import org.jdom.Element;

/**
 * 
 */
public class ComboBoxTag extends TagSupport {

	// 元素的id
	protected String id = null;

	// 元素的name
	protected String name = null;

	// 元素的下级联动元素的id
	protected String sub = null;

	// 元素初始化的字典id
	protected String initDicId = null;

	// 指定从自定义的action地址取数据，而不通过字典表
	// ds值为此action的链接地址
	protected String ds = null;

	// 元素的class
	protected String styleClass = null;

	// 默认选中的选项值
	protected String defaultValue = null;

	// formBean名称
	protected String form = null;

	// 是否可以多选
	protected String multiple = null;

	// 显示行数
	protected String size = null;

	// onchange事件监听，其中的JS将在doCombo之后执行
	protected String onchange = null;

	// TODO 加入自定义的html属性
	protected String attributes = null;
	
	//利用alt属性作为Ext框架接口
	protected String alt = null;
	
	

	public int doEndTag() {

		try {
			String selectedValue = null;

			ResourceBundle bundle = ResourceBundle
					.getBundle(Constants.BUNDLE_KEY);
			String selectText = bundle.getString("comboBox.select");
			String waitText = bundle.getString("comboBox.wait");

			Element defaultOp = new Element("option");
			defaultOp.addContent(selectText);
			defaultOp.setAttribute("value", "");

			Element disableOp = new Element("option");
			disableOp.addContent(waitText);
			disableOp.setAttribute("value", "");

			// 创建select元素
			Element rootElement = new Element("select");
			rootElement.setAttribute("id", id);
			rootElement.setAttribute("name", name);

			if (ds != null) {
				rootElement.setAttribute("ds", ds);
			}
			if (styleClass != null) {
				rootElement.setAttribute("class", styleClass);
			}
			if (multiple != null) {
				rootElement.setAttribute("multiple", multiple);
			}
			if (size != null) {
				rootElement.setAttribute("size", size);
			}
			if (alt != null) {
				rootElement.setAttribute("alt", alt);
			}

			// 设置subid属性和onchage事件
			if (sub !=null && !"".equals(sub)) {
				rootElement.setAttribute("subid", sub);
				rootElement.setAttribute("onchange",
						"javascript:eoms.ComboBox.doCombo(this,'" + sub + "');"+StaticMethod.null2String(onchange));
			}
			else if(onchange != null){
				rootElement.setAttribute("onchange", "javascript:"+StaticMethod.null2String(onchange));
			}

			if (form != null) {
				// 是否能直接获取form名称，而不用设置？
				selectedValue = (String) TagUtils.getInstance().lookup(pageContext, form, name, "request");
			}
			// 如果有initDicId，则初始化option选项
			if (initDicId != null) {
				rootElement.setAttribute("initDicId", initDicId);



				// 取字典数据
				ITawSystemDictTypeManager _objDictManager = (ITawSystemDictTypeManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemDictTypeManager");
				List list = _objDictManager.getDictSonsByDictid(initDicId);

				if(list.size()>0){
					if (multiple == null)
						rootElement.addContent(defaultOp);
					String itemName = null;
					String itemId = null;

				// 将list转为option元素插入select元素
				for (Iterator it = list.iterator(); it.hasNext();) {
					TawSystemDictType item = (TawSystemDictType) it.next();
					itemName = StaticMethod.null2String(item.getDictName());
					itemId = StaticMethod.null2String(item.getDictId());

					//修改dictCode为空的时候comboBox联动无数据问题 edit by liqiuye 20080902
					Element option = new Element("option").addContent(itemName);
					option.setAttribute("value", itemId);
					if (form != null && itemId.equals(selectedValue)) {
						option.setAttribute("selected", "true");
					} else if (itemId.equals(defaultValue)) {
						option.setAttribute("selected", "true");
					}
					rootElement.addContent(option);
				}
				}
				else{
					rootElement.addContent(disableOp);
					rootElement.setAttribute("disabled", "true");
				}
			} else {
				rootElement.addContent(disableOp);
				rootElement.setAttribute("disabled", "true");

			}

			pageContext.getOut().println(JSONUtil.getStrElement(rootElement));

		} catch (IOException e) {
			BocoLog.error(this, e.getMessage());
		} catch (JspException e) {
			BocoLog.error(this, e.getMessage());
		}
		return EVAL_PAGE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitDicId() {
		return initDicId;
	}

	public void setInitDicId(String initDicId) {
		this.initDicId = initDicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange.replaceAll("javascript:","");
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
}