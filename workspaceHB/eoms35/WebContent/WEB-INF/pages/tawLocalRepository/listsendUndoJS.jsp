<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
function openQuery(handler){
	var el = Ext.get('listQueryObject');
	if(el.isVisible()){
		el.slideOut('t',{useDisplay:true});
		handler.innerHTML = "打开快速查询";
	}
	else{
		el.slideIn();
		handler.innerHTML = "关闭快速查询";
	}
}
</script>

<div style="border:1px solid #98c0f4;padding:5px;width:98%;" class="x-layout-panel-hd">
工具栏： 
  <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
  <span id="openQuery"  style="cursor:pointer" onclick="openQuery(this);">关闭快速查询</span>
</div>

<div id="listQueryObject" style="display:;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawLocalRepositoryForm'});
	
});
</script>

<html:form action="/tawLocalRepositorys.do?method=softquery" styleId="tawLocalRepositoryForm" method="post" target="netname"> 
<fmt:bundle basename="config/applicationResource-tawlocalrepository">
<table width="100%">
	<tr>
		<td >
			<fmt:message key="tawLocalRepository.city" />
		</td>
		<td >
			<eoms:comboBox name="${sheetPageName}city" id="${sheetPageName}city"  initDicId="1010107" defaultValue="${tawLocalRepositoryForm.city}"/>
		</td>
		<td >
			<fmt:message key="tawLocalRepository.state" />
		</td>
		<td >
		<eoms:dict key="dict-repository" dictId="state" beanId="selectXML" 
				defaultId="1" isQuery="false" 
				selectId="state" />
		</td>
		<td >
			<fmt:message key="tawLocalRepository.net" />
		</td>
		<td >
			<html:text property="net" styleId="net"
						styleClass="text medium"
						  value="${tawLocalRepositoryForm.net}" />
		</td>
		<td >
			<fmt:message key="tawLocalRepository.networkTimeFrom" />
		</td>
		<td >
			
		<html:text property="networkTimeFrom" styleId="networkTimeFrom"
						styleClass="text medium"  readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"
						alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryForm.networkTimeFrom}" />
		</td>
		<td >
			<fmt:message key="tawLocalRepository.networkTimeTo" />
		</td>
		<td >
		<html:text property="networkTimeTo" styleId="networkTimeTo"
						styleClass="text medium"  readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"
						alt="allowBlank:true,vtext:''" value="${tawLocalRepositoryForm.networkTimeTo}" />
		</td>
	</tr>
	<tr>
		<td >
			<fmt:message key="tawLocalRepository.netType" />
		</td>
		<td colspan="7">
			<eoms:comboBox name="${sheetPageName}netType" id="${sheetPageName}netType" sub="${sheetPageName}driverTpye" initDicId="1011701" defaultValue="${tawLocalRepositoryForm.netType}" />
			<eoms:comboBox name="${sheetPageName}driverTpye" id="${sheetPageName}driverTpye" sub="${sheetPageName}company" initDicId="${tawLocalRepositoryForm.netType}" defaultValue="${tawLocalRepositoryForm.driverTpye}"/>
			<eoms:comboBox name="${sheetPageName}company" id="${sheetPageName}company" sub="${sheetPageName}netModale" initDicId="${tawLocalRepositoryForm.driverTpye}" defaultValue="${tawLocalRepositoryForm.company}" />
			<eoms:comboBox name="${sheetPageName}netModale" id="${sheetPageName}netModale" sub="${sheetPageName}hardwareRepository" initDicId="${tawLocalRepositoryForm.company}" defaultValue="${tawLocalRepositoryForm.netModale}" />
			<eoms:comboBox name="${sheetPageName}hardwareRepository" id="${sheetPageName}hardwareRepository" sub="${sheetPageName}softwareRepository" initDicId="${tawLocalRepositoryForm.netModale}" defaultValue="${tawLocalRepositoryForm.hardwareRepository}" />
			<eoms:comboBox name="${sheetPageName}softwareRepository" id="${sheetPageName}softwareRepository" initDicId="${tawLocalRepositoryForm.hardwareRepository}" defaultValue="${tawLocalRepositoryForm.softwareRepository}" />
		</td>
	</tr>
</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.query"/>" />
		 </td>
	</tr>
</table>
</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>