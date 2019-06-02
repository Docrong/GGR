<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page
	import="java.util.*,java.lang.*,com.boco.eoms.common.util.StaticMethod"%>

<%
	String strCondition = String.valueOf(request.getAttribute("strCondition"));
%>

<script language="javascript">
function TableBgLock(Obj){
	if(!Obj.checked){
		Obj.style.backgroundColor='';
	}else{
		Obj.style.backgroundColor='orange';
	}
}
function checkall(){
      var form = document.forms[0];
      for (var i = 0; i < form.elements.length; i++){
        var obj = form.elements[i];
        if ( obj.name != 'chkall'  && obj.type=='checkbox')
          obj.checked = form.chkall.checked;
        TableBgLock(obj);
      }
}
function checkforms(){
  if ( confirm("<bean:message key="TawRmRecord.confirm"/>") ){
      var form = document.forms[0];
      form.chkSel.value = "";
      for (var i = 0; i < form.elements.length; i++){
        var obj = form.elements[i];
        if ( obj.name != 'chkall'  && obj.type=='checkbox')
          if ( obj.checked )
            form.chkSel.value = form.chkSel.value + "," + obj.name;
      }
      form.chkSel.value = form.chkSel.value.substring(1);
      //alert(form.chkSel.value);
      if( form.chkSel.value == "" || form.chkSel.value == null ){
        alert("<bean:message key="TawRmRecord.selectaudit"/>");
        return false;
      }
      if( form.auContent.value == "" || form.auContent.value == null ){
        alert("<bean:message key="TawRmRecord.inputauditadvice"/>");
        return false;
      }
      return true;
  }
  return false;
}
function onExport()
{ 
   window.location.href= "export.do?strCondition=<%=strCondition%>";
}
</script>

<html:form action="/Kmrecord/auditBatch">
	<input type="hidden" name="chkSel" value="">
	<html:hidden property="roomId" />
	<html:hidden property="starttime" />
	<html:hidden property="endtime" />
	<input type="hidden" name="dutyRecord"   value="<%=StaticMethod.null2String(request.getParameter("dutyRecord"))%>" />
	<input type="hidden" name="pager.size"   value="<%=StaticMethod.null2String(request.getParameter("pager.size"))%>">
	<input type="hidden" name="pager.offset" value="<%=StaticMethod.null2String(request.getParameter("pager.offset"))%>">

	<table width="100%">
		<tr>
			<td align="center">
				<b>值班记录列表</b>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:write name="pagerHeader" scope="request" filter="false" />
			</td>
		</tr>
	</table>


	<table class="formTable">
		<TR>
			<TD align="center" class="label">
				<input type="checkbox" name="chkall" onclick="checkall()">全选
			</td>
			<TD align="center" class="label">
				<bean:message key="TawRmRecord.flag" />
			</td>
			<TD align="center" class="label">
				<bean:message key="TawRmRecord.starttime" />
			</td>
			<TD align="center" class="label">
				<bean:message key="TawRmRecord.endtime" />
			</td>
			<TD align="center" class="label">
				<bean:message key="TawRmRecord.dutyman" />
			</td>
			<%--
			<TD align="center" class="label">
			    <bean:message key="TawRmRecord.dutyrecord" />
			</td>
			<TD align="center" class="label">
			    批注人
			</td>
			<TD align="center" class="label">
			    批注时间
			</td>
			--%>
			<TD align="center" class="label">
				<bean:message key="label.view" />
			</td>
		</tr>

		<logic:iterate id="tawRmRecord" name="TAWRMRECORDS" type="com.boco.eoms.km.duty.model.Kmrecord">
			<tr class="tr_show">
				<TD align="center">
					<input type="checkbox" name="<%=String.valueOf(tawRmRecord.getId())%>">
				</td>
				<TD align="center">
					<% if (tawRmRecord.getFlag() == 0) { %>
					<bean:message key="TawRmRecord.compltetNo" />
					<% } else { %>
					<bean:message key="TawRmRecord.compltetYes" />
					<% } %>
				</td>
				<TD align="center">
					<bean:write name="tawRmRecord" property="starttime" scope="page" />
				</td>
				<TD align="center">
					<bean:write name="tawRmRecord" property="endtime" scope="page" />
				</td>
				<TD align="center">
					<bean:write name="tawRmRecord" property="dutyman" scope="page" />
				</td>
				<%-- 
				<TD align="center">
				    <%=tawRmRecord.getDutyrecord()%>
				</td>
				<TD align="center">
				    <bean:write name="tawRmRecord" property="auditor" scope="page" />
				</td>
				<TD align="center">
				    <bean:write name="tawRmRecord" property="auTime" scope="page" />
				</td>
				--%>
				<TD align="center">
					<html:link href="view.do" paramId="id" paramName="tawRmRecord" paramProperty="id">
						<img src="<%=request.getContextPath()%>/duty/images/an_xs.gif" border="0">
					</html:link>
				</td>
			</tr>

		</logic:iterate>

	</table>

	<%--<table border="0" width="100%" cellspacing="1" cellpadding="1"
					class="formTable" align=center>
					<tr class="tr_show">
						<td class="label">
							<bean:message key="TawRmRecord.dutyadvice" />
						</td>
						<td>
							<textarea id="auContent" name="auContent" rows=2 cols=80></textarea>
						</td>
					</tr>
				</table>
			--%>
	<br>

	<center>
		<%--
		<html:submit onclick="return checkforms();" styleClass="button">
		  批注
		</html:submit>
		--%>
		<html:button onclick="javascript:onExport();" styleClass="button"
			property="button">
			<bean:message key="TawRmRecord.export" />
		</html:button>
	</center>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>

