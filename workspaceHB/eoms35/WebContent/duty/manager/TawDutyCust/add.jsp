<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<head>
<title>值班记录列表</title>

<script language="javascript">
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
      for(i=0;i<form1.chk_sel_id.length;i++){
        if (document.form1.chk_sel_id[i].checked){
           if (sel_idStr==""){
              sel_idStr=document.form1.chk_sel_id[i].value;
            }
            else{
              sel_idStr=sel_idStr + "," + document.form1.chk_sel_id[i].value;
            }
          }
      }
    }
  }
  return sel_idStr;

}

function onAdd(listNum)
{
  var str=save_sel_id(listNum);
  if (str!="")
  {
    if (confirm("你确认要增加选定机房的排班信息到 值班首页 吗？")==true)
    {
      document.form1.id.value=str;
      document.form1.action="save.do";
      document.form1.submit();
    }
  }
  else
  {
    alert("请选择需要增加的机房");
    return false;
  }
  return true;
}
</script>
</head>
<form name="form1" method="post" >
<input type="hidden" name="id" value="">
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="60%" align="center">
<tr>
<td width="60%" align="center" class="table_title">
    &nbsp;&nbsp;选择机房  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="60%" class="clsbkgd" align="center">
    <tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
      <tr class="td_label">
        <td width="6%">
          <div align="center"></div></td>
        <td width="94%"><div align="center">机房名称</div></td>
        </tr>
<%
int listNum=0;
%>

<logic:iterate id="tawRoom" name="DUTYROOMLIST" type=" com.boco.eoms.duty.model.TawApparatusroom">
	
<tr class="tr_show">
     <td> <input type="checkbox" name="chk_sel_id" id="<%=tawRoom.getId()%>" value="<%=tawRoom.getId()%>"></td>
     <td>
     <bean:write name="tawRoom" property="roomName" scope="page"/></td>
     </tr>
<%
listNum = listNum + 1;
%>
</logic:iterate>
      </table>
</td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="60%" align="center">
<tr>
<td height="32" align="right">

      <input type="button" Class="clsbtn2" value="确定" onclick="return onAdd(<%=listNum%>);">
	  <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back()">
  </td>
</tr>
</table>
</body>
