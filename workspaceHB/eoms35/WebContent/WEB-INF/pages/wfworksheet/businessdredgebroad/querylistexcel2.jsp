<%@ page language="java" import="java.util.*,com.boco.eoms.base.api.EOMSMgr,com.boco.local.model.*,com.boco.eoms.base.util.StaticMethod" pageEncoding="UTF-8"%>\
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="/WEB-INF/eoms.tld" prefix="eoms" %>
<%@ taglib uri="/WEB-INF/tlds/priv.tld" prefix="priv" %>
<%@ page contentType="text/html; charset=gb2312" %>
<% response.setContentType("application/vnd.ms-excel;charset=UTF-8"); %>
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
		List listExcel = (ArrayList)request.getAttribute("listExcel");
%>
<%
	 String text="eoms_excel.xls";
	 response.setHeader("Content-Disposition: ","attachment; filename="+text);
%>
<form action="" method="post"> 
<table cellpadding="1" cellspacing="1" border="1" width="1800">
	<tr>
		<td >工单流水号</td>
		<td>工单主题</td>
		<td>集团客户业务分类</td>
		<td>所属地市</td>
		<td>所属区县</td>
		<td>联系人姓名</td>
		<td>联系方式1</td>
		<td>第二联系人联系方式</td>
		<td>联系方式2	</td>
		<td>接入类型</td>
		<td>带宽</td>
		<td>IP地址分配方式</td>
		<td>所属小区名称</td>
		<td>安装详细地址</td>
		<td>用户账号</td>
		<td>账号密码</td>
		<td>设备编码</td>
		<td>设备名称</td>
		<td>设备端口</td>
		<td>设备地址</td>
		<td>用户要求</td>
	</tr>
	<%
		for(int i=0;i<listExcel.size();i++){
			LocalBusinessDredgebroad businessDredgebroad = (LocalBusinessDredgebroad)listExcel.get(i);
			String sendtime = StaticMethod.date2String(businessDredgebroad.getSendtime());
			String operatetime = StaticMethod.date2String(businessDredgebroad.getOperatetime());
	%>
	<tr>
		<td >
			<%=businessDredgebroad.getSheetid() %>
		</td>
		<td >
			&nbsp;<%=businessDredgebroad.getTitle() %>
		</td>
		<td >
			<%=EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)businessDredgebroad.getBusinesstype1(),"ItawSystemDictTypeDao")%>
		</td>
		<td >
			<%=businessDredgebroad.getCityname() %>
		</td>
		<td >
			<%=businessDredgebroad.getIsradiusvalidate() %>
		</td>
		<td >
			<%=businessDredgebroad.getApnippool() %>
		</td>
		<td>
			<%=businessDredgebroad.getDoubleggsn() %>
		</td>
		<td>
			<%=businessDredgebroad.getBcode() %>
		</td>
		<td>
			<%=businessDredgebroad.getSecondippool() %>
		</td>
		<td>
			<%=EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)businessDredgebroad.getSiconnectmmsgatewayname(),"ItawSystemDictTypeDao")%>
		</td>
		<td>
			<%=EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)businessDredgebroad.getSienterprisecode(),"ItawSystemDictTypeDao")%>
		</td>
		<td>
			<%=EOMSMgr.getSysMgrs().getDictMgrs().getID2NameMgr().id2Name((String)businessDredgebroad.getSiipadd(),"ItawSystemDictTypeDao")%>
		</td>
		<td>
			<%=businessDredgebroad.getSiuplinkurl() %>
		</td>
		<td>
			<%=businessDredgebroad.getComprotocol() %>
		</td>
		<td>
			<%=businessDredgebroad.getFlowcontrolpriority() %>
		</td>
		<td>
			<%=businessDredgebroad.getPortrateisdown() %>
		</td>
		<td>
			<%=businessDredgebroad.getSmssigned() %>
		</td>
		<td>
			<%=businessDredgebroad.getHostipadd() %>
		</td>
		<td>
			<%=businessDredgebroad.getConnectgatewayname() %>
		</td>
		<td>
			<%=businessDredgebroad.getConnectgatewayid() %>
		</td>
		<td>
			<%=businessDredgebroad.getAmount() %>
		</td>
	</tr>
<%}%>
</table>
</form>

<%@ include file="/common/footer_eoms.jsp"%>