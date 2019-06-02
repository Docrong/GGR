package com.boco.eoms.commons.ui.taglib;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.commons.loging.BocoLog;
import net.sf.json.JSONObject;

/**
 * 
 */
public class XboxTag extends TagSupport {

	// id
	protected String id = null;

	// 触发点击事件的元素id，例如一个按钮的id
	protected String handler = null;

	// 树图url
	protected String dataUrl = null;

	// 树图根节点id
	protected String rootId = null;

	// 树图根节点名称
	protected String rootText = null;

	// 是否单选
	protected boolean single = false;

	// 指定哪种树图类型的节点可被选择,如checktype='user'
	protected String checktype = null;

	// 保存节点名称的输入框id
	protected String textField = null;

	// 保存节点Id的输入框id
	protected String valueField = null;

	// 已选择数据的JSON字符串
	protected String data = null;

	// 是否关联viewer控件
	protected boolean viewer = false;
	
	// 窗口标题
	protected String dlgTitle = null;
	
	// 是否使用JSON格式给后台返回数据
	protected boolean returnJSON = false;

	// 利用alt属性作为Ext框架接口
	protected String alt = null;

	// 模式，如clearPath
	protected String mode = null;

	public int doEndTag() {

		try {
			JSONObject config = new JSONObject();
			config.put("id", this.id);
			config.put("btnId", this.handler);
			config.put("treeDataUrl", this.dataUrl);
			config.put("treeRootId", this.rootId);
			config.put("treeRootText", this.rootText);
			config.put("saveChkFldId", this.valueField);

			if(this.single == true) config.put("single", this.single);
			if(this.checktype != null) config.put("checktype", this.checktype);
			if(this.textField != null) config.put("showChkFldId", this.textField);
			if(this.data != null) config.put("data", this.data);
			if(this.viewer == true) config.put("viewer", this.viewer);
			if(this.dlgTitle != null) config.put("dlgTitle", this.dlgTitle);
			if(this.returnJSON == true) config.put("returnJSON", this.returnJSON);
			if(this.mode != null) config.put("mode", this.mode);

			StringBuffer s = new StringBuffer();
			
			if(this.viewer == true) s.append("\n<div id='xbox_" + this.id + "_view' class='viewer-list'></div>");
			
			s.append("\n\n<script type=\"text/javascript\">");
			s.append("\n\tExt.onReady(function(){");
			s.append("\n\t\txbox_" + this.id + " =");
			s.append(" new xbox(" + config.toString() + ");");
			s.append("\n\t});");
			s.append("\n</script>\n");
						
			pageContext.getOut().println(s);

		} catch (IOException e) {
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

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getChecktype() {
		return checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getRootText() {
		return rootText;
	}

	public void setRootText(String rootText) {
		this.rootText = rootText;
	}

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public boolean isViewer() {
		return viewer;
	}

	public void setViewer(boolean viewer) {
		this.viewer = viewer;
	}

	public String getDlgTitle() {
		return dlgTitle;
	}

	public void setDlgTitle(String dlgTitle) {
		this.dlgTitle = dlgTitle;
	}

	public boolean isReturnJSON() {
		return returnJSON;
	}

	public void setReturnJSON(boolean returnJSON) {
		this.returnJSON = returnJSON;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}