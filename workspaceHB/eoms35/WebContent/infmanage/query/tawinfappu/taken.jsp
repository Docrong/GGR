<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<script language="javascript">
function onRemovefile(listNo)
{
  var str=save_sel_id(listNo);
  if (str!="")
  {
    if (confirm("你确认要将选择的内容删除 吗 ？")==true)
    {
      document.form1.infoid.value=str;
      document.form1.action="removeAtt.do";
      document.form1.submit();
      alert(str);
    }
  }
  else
  {
    alert("请选择要将选择的内容放入 \"我的文件柜\" 的信息");
    return false;
  }
  return true;
}

function save_sel_id(listNum){
  var parent_id="";
  var sel_idStr="";
  var i = 0;

  if (document.form1.chk_sel_id!=undefined) {
    if (document.form1.chk_sel_id.length==undefined){
      if (document.form1.chk_sel_id.checked){
        sel_idStr=document.form1.chk_sel_id.value;
      }
    }
    else {
      i=0;
      while (parent_id=="" && i<form1.chk_sel_id.length){
        if (document.form1.chk_sel_id[i].checked){
          parent_id=document.form1.chk_sel_pid[i].value;
        }
        i+=1;
      }
      for(i=0;i<form1.chk_sel_id.length;i++){
        if (document.form1.chk_sel_id[i].checked){
          if (document.form1.chk_sel_pid[i].value!=parent_id){
            alert("不能对不同级别的同时操作");
            return "";
          }
          else{
            if (sel_idStr==""){
              sel_idStr=document.form1.chk_sel_id[i].value;
            }
            else{
              sel_idStr=sel_idStr + "," + document.form1.chk_sel_id[i].value;
            }
            parent_id=document.form1.chk_sel_pid[i].value;
          }
        }
      }
    }
  }
  return sel_idStr;

}


</script>
<html:html>
<head>
<title>f</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<%
	request.getAttribute("tawInfInfoForm");
%>
<form name="form1">
  <body>
	<br>
	  <table border="0" width="100%" cellspacing="0" align="center">
		<tr>
		  <td width="100%" align="center" class="table_title">
			<b>
			  &nbsp;&nbsp;<bean:message key="label.list"/>
			</b>
		  </td>
		</tr>
	  </table>

      <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">

        <tr class="tr_show">




          <td width="5%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfInfoId"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfInfoName"/></td>

          <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.DeptName"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfUpName"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfUpTime"/></td>

          		</tr>

<%
int listNum=0;
%>
          <logic:iterate id="tawInfInfo" name="tawInfInfoForm" type="com.boco.eoms.infmanage.model.TawInfInfo">
		<tr class="tr_show">

		  <td width="15%" height="25" class="clsfth" align="center">
                    <input type="checkbox" name="chk_sel_id" id="<%=tawInfInfo.getId()%>" value="<bean:write name="tawInfInfo" property="id" scope="page"/>">
                    <bean:write name="tawInfInfo" property="infInfoId" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infInfoName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="deptName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infUpName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infUpTime" scope="page"/></td>


               	</tr>
		</logic:iterate>
    　</table>
      <table border="0" width="100%" cellspacing="0">
		  <tr>
		    <td width="100%" colspan="10" height="32" align="right">
 		<input type='button' Class="clsbtn2"  value='删除' onclick = 'onRemovefile(<%=listNum%>)'>&nbsp;&nbsp;
		  <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		    </td>
		  </tr>
      </table>
  </body>

</form>
<input type="hidden" name="infoid" value="">


<script language="javascript">

</script>
</html:html>
