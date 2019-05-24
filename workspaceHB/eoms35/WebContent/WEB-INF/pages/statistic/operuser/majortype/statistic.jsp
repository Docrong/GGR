<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@page
	import="com.boco.eoms.commons.statistic.base.config.excel.*,java.util.*"%>
<%
	Excel excelConfig = (Excel) request.getAttribute("excelConfig");
	if (excelConfig == null)
		throw new Exception("读取配置统计文件失败!");

	String excelConfigURL = String.valueOf(request
			.getAttribute("excelConfigURL"));
	String findListForward = String.valueOf(request
			.getAttribute("findListForward"));
	Calendar cld = Calendar.getInstance();
%>

<script language="JavaScript" type="text/JavaScript"
	src="${app}/scripts/form/ajax.js">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'operuserForm'});
});	
</script>

<html:form action="/statoperuser.do?method=performStatistic"
	styleId="theform">

	<table class="formTable middle" align="left">
		<caption>
			输入条件
		</caption>
		<tr>
			<td colspan="2">
				<input type="hidden" name="excelConfigURL"
					value="<%=excelConfigURL%>">
				<input type="hidden" name="findListForward"
					value="<%=findListForward%>">
			</td>
		</tr>

		<tr>
			<td noWrap class="label">
				专业分类
			</td>
			<td>
				<eoms:comboBox name="majortype" id="majortype" initDicId="1150102" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="115010201"/>	
			</td>
		</tr>

		<tr>
			<td noWrap class="label">
				所属地州
			</td>
			<td>
				<eoms:comboBox name="subarea" id="subarea" initDicId="1150101" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="115010101" />	
				<!-- <input type="hidden" name="areaName" id="areaName" value="">
				<input type="hidden" name="type_true" id="type_true"> -->
			</td>
		</tr>
		<input type="hidden" name="reportIndex" value="0">
		<!--  <tr>
			<td noWrap class="label">
				合作伙伴名称
			</td>
			<td>
				<select name="dept_id" id="dept_id" size="1">
				</select>
				<input type="hidden" id="deptName" name="deptName" value="">
			</td>
		</tr>
		
		<c:if test="${stateFromAction=='0'}">
		<tr>
			<td noWrap class="label">
				油机功率
			</td>
			<td>
				<eoms:comboBox name="type" id="type" initDicId="11207" />
			</td>

		</tr>
			<input type="hidden" name="reportIndex" value="0">
			<tr>
			<td align="left" colspan="2">
				<html:submit styleClass="btn" property="method.save"
					styleId="method.save" onclick="typeCheck();">
					<bean:message bundle="statistic" key="button.done" />
				</html:submit>
			</td>
		</tr>
		</c:if>
		<input type="hidden" name="state_true" id="state_true">
		<c:if test="${stateFromAction=='1'}">
		<tr>
			<td noWrap class="label">
				油机性质
			</td>
			<td>
				<eoms:comboBox name="type" id="type" initDicId="11206" />
			</td>

		</tr>
			<input type="hidden" name="reportIndex" value="1">

			<tr>
				<td noWrap class="label">
					油机状态
				</td>
				<td>
					<eoms:comboBox name="state" id="state" initDicId="11205" />
				</td>

			</tr>
		
		<tr>
			<td align="left" colspan="2">
				<html:submit styleClass="btn" property="method.save"
					styleId="method.save" onclick="stateCheck();">
					<bean:message bundle="statistic" key="button.done" />
				</html:submit>
			</td>
		</tr>
		</c:if>-->
		<tr>
			<td align="left" colspan="2">
				<html:submit styleClass="btn" property="method.save"
					styleId="method.save" >
					<bean:message bundle="statistic" key="button.done" />
				</html:submit>
			</td>
		</tr>
	</table>
	<br />
</html:form>
<script type="text/javascript">

var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/statistic/notflow/partner/baseinfo/apparatusStat.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 var deptId ="";
			 changeList(url,fieldName,deptId);
function changeDep()
		{    
			 var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/statistic/notflow/partner/baseinfo/apparatusStat.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 changeList(url,fieldName,"");
		}
function typeCheck()
        {
        var jsType =  document.getElementById("type").value;
        if(jsType==""){
        document.getElementById("type_true").value='1';
        }else{
        document.getElementById("type_true").value=document.getElementById("type").value;
        }
      }
   function stateCheck()
        {
        var jsType =  document.getElementById("type").value;
        var state =  document.getElementById("state").value;
       
        if(jsType==""){
        document.getElementById("type_true").value='1';
        }else{
        document.getElementById("type_true").value=document.getElementById("type").value;
        }
        if(state==""){
        document.getElementById("state_true").value='1';
        }else{
        document.getElementById("state_true").value=document.getElementById("state").value;
        }
      }
  </script>

<%@ include file="/common/footer_eoms.jsp"%>
