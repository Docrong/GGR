/**
 * 选择对象弹出窗口控件的标签封装
 * 
 * @author mios Created on 2008-04-01
 */
package com.boco.eoms.commons.ui.taglib;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.role.util.RoleFilter;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.ui.util.UIConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChooserTag extends TagSupport {
	/**
	 * 唯一标识,必填 定义了页面JS类 Chooser的对象名称 
	 * 如id="dlg",则在JS中可用chooser_dlg访问JS对象
	 */
	protected String id = null;

	/**
	 * 选择器类型,非必填 缺省为显示普通树图，树图地址由panels属性定义 
	 * type="role"
	 *   表示为工单选择子角色专用类型，在主选择面板中会显示指定role下的子角色 
	 *   含有设置组长和弹出过滤窗口等特殊功能
	 */
	protected String type = null;

	// 派发类型定义,必填,JSON格式，如[{id:'send',text:'派发'},{id:'copy',text:'抄送'}]
	protected String category = null;

	// 选择器设置,非必填,JSON格式,如{returnJSON:true,showLeader:true}
	protected String config = null;

	/**
	 * 已选数据的字符串,JSON格式
	 * 
	 * @example 例如 data="[{id:'admin',nodeType:'user',categoryId:'send2'},
	 *          {id:'8aa082861c20cd55011c20d38737001c',nodeType:'subrole',categoryId:'send2'}]"
	 */
	protected String data = null;

	/**
	 * 面板设置
	 * 
	 * @example 例如 panels="[ {text:'部门',dataUrl:'${app}/xtree.do?method=dept'},
	 *          {text:'部与人员门',dataUrl:'${app}/xtree.do?method=userFromDept'} ]"
	 */
	protected String panels = null;

	// 大角色ID
	protected long roleId;

	// 工单ID
	protected String flowId = null;
	
	protected String ifSpecial = null;

	protected String btnText = "选择派发对象";

	static ITawSystemRoleManager roleManager = (ITawSystemRoleManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemRoleManager");

	public int doEndTag() {
		try {
			StringBuffer html = new StringBuffer();

			html.append("\n<!-- chooser [" + this.id + "] start -->\n");
			html.append("<div id=\"" + this.id + "\">\n");
			html.append("\t<input class=\"btn\" type=\"button\" value=\"" + this.btnText + "\"/>\n");
			html.append("\t<div id=\"" + this.id
					+ "-chooser-show\" class=\"viewer-list\"></div>\n");
			html.append("</div>\n");
			html.append("\n<div id=\"" + this.id + "-chooser\"");
			html.append(" class=\"x-layout-inactive-content\"></div>\n");

			if ("role".equalsIgnoreCase(this.type)) {
				html.append(getFilterDlgHtml());
			}

			html.append("\n<script type=\"text/javascript\">\n");
			html.append(createScripts());
			html.append("</script>\n");
			html.append("\n<!-- chooser [" + this.id + "] end -->\n");
			pageContext.getOut().write(html.toString());
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
		}
		return EVAL_PAGE;
	}

	/**
	 * 创建过滤器窗口的html容器
	 * 
	 * @return {String}
	 * @throws Exception
	 */
	private String getFilterDlgHtml() throws Exception {
		StringBuffer dom = new StringBuffer();
		dom.append("<div id=\"" + this.id + "-chooser-filter-dlg\"").append(
				" class=\"x-layout-inactive-content\">\n");

		dom.append("\t<div class=\"x-dlg-hd\">搜索器</div>\n");
		dom.append("\t<div class=\"x-dlg-tab\" title=\"请选择区分度\">\n");
		dom.append("\t\t<div class=\"inner-tab\">\n");
		dom.append("\t\t\t<div id=\"" + this.id + "-chooser-deptList\">\n");
		dom.append(getDeptList(this.id, this.roleId));
		dom.append("\t\t\t</div>\n");
		dom.append("\t\t\t<div id=\"" + this.id + "-chooser-classList\">\n");
		dom.append(getClassList()).append("</div>\n");
		dom.append("\t\t</div>\t</div></div>");
		return dom.toString();
	}

	/**
	 * 创建前台JS代码
	 * 
	 */
	private StringBuffer createScripts() throws Exception {
		JSONObject config = new JSONObject();
		config.put("id", this.id);
		if (this.type != null)
			config.put("type", this.type);
		config.put("category", this.category);
		config.put("config", this.config);
		if (this.panels != null)
			config.put("panels", this.panels);

		// type=role 表示选择子角色时的派发树类型，有设置组长和显示搜索窗口的特殊功能
		if ("role".equalsIgnoreCase(this.type)) {
			config.put("roleId", this.roleId);
			config.put("flowId", this.flowId);
			config.put("ifSpecial", this.ifSpecial);
			config.put("rootText", roleManager.getRoleNameById(this.roleId));

			TawSystemRole role = (TawSystemRole) roleManager
					.getTawSystemRole(roleId);
			// 是否虚拟组
			config.put("isVirtual", String.valueOf(role.getRoleTypeId())
					.equals(RoleConstants.ROLETYPE_VIRTUAL));

				config.put("showFilter", false);
				config.remove("rootText");
				config.put("rootText", "可以选择项目");
		}

		StringBuffer scripts = new StringBuffer();
		scripts.append("\tExt.onReady(function(){").append(
				"\n\t\tchooser_" + this.id + " = new Chooser("
						+ config.toString() + ");");
		// 添加已选择数据
		if (this.data != null) {
			scripts.append("\n\n\t\tchooser_" + this.id + ".add("
					+ getChosenData() + ");");
			scripts.append("\n\n\t\tchooser_" + this.id + ".update();");
		}

		scripts.append("\n\t});\n");
		return scripts;
	}

	/**
	 * 构造已选项数据,返回JSON格式字符串
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getChosenData() throws Exception {
		JSONArray jsonSrc = JSONArray.fromString(this.data);
		int l = jsonSrc.length();
		for (int i = 0; i < l; i++) {
			JSONObject item = (JSONObject) jsonSrc.get(i);
			String id = item.getString(UIConstants.JSON_ID);
			String nodeType = item.getString(UIConstants.JSON_NODETYPE);
			String name = null;

			// dept
			if (UIConstants.NODETYPE_DEPT.equals(nodeType)) {
				ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemDeptManager");
				name = mgr.id2Name(id);
			}

			// user
			if (UIConstants.NODETYPE_USER.equals(nodeType)) {
				ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemUserManagerFlush");
				name = mgr.id2Name(id);
			}

			// subRole
			if (UIConstants.NODETYPE_SUBROLE.equals(nodeType)) {

				ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemSubRoleManager");
				TawSystemSubRole subRole = subRoleMgr.getTawSystemSubRole(id);
				name = subRole.getSubRoleName();

				ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
						.getInstance().getBean("itawSystemUserRefRoleManager");
				ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
						.getInstance().getBean("itawSystemUserManager");

				Map map = mgr.getGroupUserbyroleid(id);

				JSONArray userRoot = new JSONArray();

				Iterator it = map.keySet().iterator();

				while (it.hasNext()) {
					String userId = it.next().toString();
					TawSystemUser user = userMgr.getUserByuserid(userId);
					String groupType = map.get(userId).toString();
					String userName = user.getUsername();
					String userText = groupType
							.equals(RoleConstants.groupType_leader) ? userName
							+ "(组长)" : userName;

					JSONObject userJSON = new JSONObject();
					userJSON.put(UIConstants.JSON_ID, user.getUserid());
					userJSON.put(UIConstants.JSON_TEXT, userText);
					userJSON.put(UIConstants.JSON_NAME, userName);
					userJSON.put(UIConstants.JSON_NODETYPE,
							UIConstants.NODETYPE_USER);
					userJSON.put(UIConstants.JSON_ICONCLS,
							UIConstants.NODETYPE_USER);
					userJSON.put("grouptype", map.get(user));
					userRoot.put(userJSON);
				}
				if (userRoot.length() > 0) {
					item.put("user", userRoot);
				}
			}
			item.put(UIConstants.JSON_TEXT, name);
			item.put(UIConstants.JSON_NAME, name);
		}
		return jsonSrc.toString();
	}

	/**
	 * 通过roleId获取关联的可选部门/地域列表
	 */
	public static String getDeptList(String chooserId, long roleId) {
		// List deptList = roleManager.getDeptByRoleId(roleId);
		List list = roleManager.getAreaByRoleId(roleId);
		StringBuffer strDeptRows = new StringBuffer();
		strDeptRows.append("<dl class=\"list\"><dt>请选择地域</dt>").append(
				"<dd><select name=\"deptId\">");
		for (int i = 0; list != null && i < list.size(); i++) {
			TawSystemArea area = (TawSystemArea) list.get(i);
			strDeptRows.append("<option value=\"" + area.getAreaid() + "\"/>"
					+ area.getAreaname() + "</option>");
		}
		strDeptRows.append("</select></dd></dl>");
		return strDeptRows.toString();
	}

	/**
	 * 通过flowId获取关联的可选专业列表
	 */
	private String getClassList() throws Exception {
		List filters = RoleMapSchema.getInstance().getRoleMappingListById(
				this.flowId);
		StringBuffer strClassRows = new StringBuffer();
		strClassRows
				.append("<dl class=\"list\"><dt>请选择专业 &nbsp;")
				.append(
						"<label for=\"allclass\">查询所有专业</label><input type=\"checkbox\" class=\"checkbox\" name=\"allClass\"></dt><dd><ul>");
		for (int i = 0; filters != null && i < filters.size(); i++) {
			RoleFilter filter = (RoleFilter) filters.get(i);
			String filterId = this.id + filter.getBusinessName();
			String subId = this.id + filter.getSub();
			String extend = (filter.getSub() == null) ? ""
					: "onchange=javascript:eoms.ComboBox.doCombo(this,\""
							+ subId + "\") subid=\"" + subId + "\"";
									
			String filterText = "";
			
			try{
				ResourceBundle bundle = ResourceBundle.getBundle(Constants.BUNDLE_KEY);
				filterText = bundle.getString(filter.getName());
			}catch(Exception e){
				BocoLog.error(this, e.getMessage());
				filterText = "请选择";
			}
			
			strClassRows.append("<li><label class=\"desc\">" + filterText + "</label>");

			strClassRows.append("<select name=\"" + filter.getBusinessName()
					+ "\" id=\"" + filterId + "\" " + extend + ">");

			// 初始化配置中含有dictId属性的节点
			String dictId = filter.getDictId();
			if (dictId != null) {
				ITawSystemDictTypeManager _objDictManager = (ITawSystemDictTypeManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemDictTypeManager");
				List listClass = _objDictManager.getDictSonsByDictid(dictId);
				for (int j = 0; listClass != null && j < listClass.size(); j++) {
					if (j == 0) {
						strClassRows.append("<option value=\"\"/>请选择</option>");
					}
					TawSystemDictType dict = (TawSystemDictType) listClass
							.get(j);
					strClassRows.append("<option  value=\""
							+ dict.getDictCode() + "\"/>" + dict.getDictName()
							+ "</option>");
				}
			}

			strClassRows.append("</select></li>");
		}
		strClassRows.append("</ul></dd></dl>");
		return strClassRows.toString();
	}

	/**
	 * @return Returns the flowId.
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * @param flowId
	 *            The flowId to set.
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * @return Returns the roleId.
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            The roleId to set.
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return Returns the config.
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            The config to set.
	 */
	public void setConfig(String config) {
		this.config = config;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the data.
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            The data to set.
	 */
	public void setData(String data) {
		this.data = "".equals(data) ? null : data;
	}

	/**
	 * @return Returns the panels.
	 */
	public String getPanels() {
		return panels;
	}

	/**
	 * @param panels
	 *            The panels to set.
	 */
	public void setPanels(String panels) {
		this.panels = "".equals(panels) ? null : panels;
	}

	public String getIfSpecial() {
		return ifSpecial;
	}

	public void setIfSpecial(String ifSpecial) {
		this.ifSpecial = ifSpecial;
	}
}
