<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<script language="javascript">
function onRemove(listNo)
{
  var str="";
  str=save_sel_id(listNo);
  //var str=document.form1.chk_sel_id.value;
  //alert(listNo);
  //alert(str);
  if (str!="")
  {
    if (confirm("你确认要删除吗 ？")==true)
    {
      document.form1.infoid.value=str;
      document.form1.action="../../TawInfAppu/removetaker.do";
      document.form1.submit();
      //alert(str);
    }
  }
  else
  {
    alert("请选择要订阅的信息");
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
    for(i=0;i<form1.chk_sel_id.length;i++){
      	if(document.form1.chk_sel_id[i].checked){
           if (sel_idStr==""){
              sel_idStr=document.form1.chk_sel_id[i].value;
            }
            else{
              sel_idStr=sel_idStr + "," + document.form1.chk_sel_id[i].value;
            }
      	}

    }
  }
  return sel_idStr;

}


</script>


<html:html>
<head>
<title><bean:write name="tawInfInfoForm" property="infSortName"/><bean:message key="label.list"/></title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<form name="form1">
  <input type="hidden" name="infoid" value="">

  <body>
	<br>
	  <table border="0" width="100%" cellspacing="0" align="center">
		<tr>
		  <td width="100%" align="center" class="table_title">
			<b>
			  &nbsp;&nbsp;<bean:write name="tawInfInfoForm" property="infSortName"/><bean:message key="label.list"/>
			</b>
		  </td>
		</tr>
	  </table>
      <html:hidden name="tawInfInfoForm" property="infSortId"/>
      <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
		    <td width="100%" colspan="10" height="25" align="center"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></td>
        </tr>
        <tr class="tr_show">
		  <td width="20%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfSortName"/></td>

          <td width="5%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfInfoId"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfInfoName"/></td>

          <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.DeptName"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfUpName"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:message key="TawInfAppu.InfUpTime"/></td>

          <td width="5%" height="25" align="center"><font color="#cc0000">查看</font></td>

		</tr>
<%
int listNum=0;
%>
		<logic:iterate id="tawInfInfo" name="TAW_INF_INFO_LIST" type="com.boco.eoms.infmanage.model.TawInfInfo">
		<tr class="tr_show">
		  <td width="20%" height="25" class="clsfth" align="center">
                        <input type="checkbox" name="chk_sel_id" id="<%=tawInfInfo.getId()%>" value="<bean:write name="tawInfInfo" property="id" scope="page"/>">
                    <bean:write name="tawInfInfo" property="infSortName" scope="page"/></td>

		  <td width="5%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infInfoId" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infInfoName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="deptName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infUpName" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="tawInfInfo" property="infUpTime" scope="page"/></td>

		  <%
			java.util.HashMap map = new java.util.HashMap();

			map.put("id",String.valueOf(tawInfInfo.getId()));  //id
			map.put("deptId", String.valueOf(tawInfInfo.getDeptId()));  //部门Id
			map.put("sortId",String.valueOf(tawInfInfo.getInfSortId())); //类别id
			pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>

           <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/TawInfAppu/view.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="显示"></html:link>&nbsp;</font></td>

     	</tr>
		</logic:iterate>
    　</table>
      <table border="0" width="100%" cellspacing="0">
		  <tr>
		    <td width="100%" colspan="10" height="32" align="right">
 <input type="button" Class="clsbtn2"  value="删除" onclick="return onRemove(<%=listNum%>);">&nbsp;&nbsp;
 <input type='button' Class="clsbtn2"  value='打印' onclick = 'javascript:window.print()'>&nbsp;&nbsp;
		  <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		    </td>
		  </tr>
      </table>
  </body>

</form>
</html:html>
