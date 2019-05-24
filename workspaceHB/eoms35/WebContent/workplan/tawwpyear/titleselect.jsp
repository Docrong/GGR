<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpModelPlanVO"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<html>
<head>
 <meta http-equiv="Cache-Control" content="no-store"/>
  <!-- HTTP 1.0 -->
  <meta http-equiv="Pragma" content="no-cache"/>
  <!-- Prevents caching at the Proxy Server -->
  <meta http-equiv="Expires" content="0"/>
  <title>年度作业计划管理--选择主题</title>
<link rel="stylesheet" href="../css/table_style.css" type="text/css">

<script language="JavaScript">
var callerWindowObj = dialogArguments;

	function JustifyNull1(field) {
		var Ret = true
		var str = "" + field.value

		if(str.length) {
			for(var i=0;i<str.length;i++)
				if(str.charAt(i)!=" ")		break
			if(i>=str.length)	field.value = ""
		}

		if (field.value.length==0)	Ret = false
		return (Ret)
	}// 判断输入字段是否为空

	function  displayuser() {
		if (document.frmAddress.chkuser.checked)
			window.div1.style.display="";
		else
			window.div1.style.display="none";
	}

	function  displaygroup() {
		if (document.frmAddress.chkgroup.checked)
			window.div3.style.display="";
		else
			window.div3.style.display="none";
	}


	function checkForm() {
          		var sValue;
                        var sValuestr;
			if( document.frmAddress.select.value=="" ){
				alert("请选择的网元！需要导入的模版。");
				return false;
			}
                        i = document.frmAddress.select.options.length;
                        for (var cc=0;cc<i ;cc++){
                        if (document.frmAddress.select.options[cc].selected==true )
					{
                                        callerWindowObj.document.forms[0].planname.value=document.frmAddress.select.options[cc].text;
                        		callerWindowObj.document.forms[0].planid.value= document.frmAddress.select.options[cc].value;
					window.close();
					}
                        }
	}
 </script>
</head>
<body>
<FORM name='frmAddress' method='post' onsubmit='return checkForm();'>
	<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
	<TR>
		<TD vAlign=top align=center width='100%'><P>可选模版</P></TD>
	</TR>
	<tr>
		<td>
		<SELECT width='100%' name='select' style='width:200pt'>
		<%
		List modellist = (List)request.getAttribute("modellist"); //获取年度作业计划集合结构
		for (int i=0;i<modellist.size();i++){
  			TawwpModelPlanVO tawwpModelPlanVO = (TawwpModelPlanVO)modellist.get(i);
                      %>
  			<option value="<%=tawwpModelPlanVO.getId()%>"><%=tawwpModelPlanVO.getName()%></option>
  		<%
			}
		%>
		</SELECT>
		</TD>

	</TR>
	<TR>
	<TD borderColor=#008000 width='100%' height=37 colspan='3'><P align=center>
	<input type='button' value='完成' name='sub3' onclick="javascript:checkForm()"> &nbsp;&nbsp;&nbsp;&nbsp;
	<input type='button' value='取消' name='sub4' onclick="javascript:window.close()">
	</P></TD></TR> </TABLE><input type='hidden' name='deptid'  value=''>
	<input type='hidden' name='selvalue' value=''>
	 
</FORM>
</body>
</html>
