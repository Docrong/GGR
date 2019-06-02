
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>
<bean:write name="msg" scope="request"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<%
 String str =(String)request.getAttribute("StorageTree");
 String xmltree =(String)request.getAttribute("xmltree");
  List objtypelist =(List)request.getAttribute("objtypelist");
   List positionlist =(List)request.getAttribute("positionlist");
	   List typelist =(List)request.getAttribute("typelist");

%>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE = JavaScript>
var s=["s1","s2"];
var opt0 = ["",""];
var dsyStorageTree = new Dsy();
<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsyStorageTree,"+(i+1)+",s,opt0)");
	change(dsyStorageTree,0,s,opt0);
}
</script>
<script language="javascript" src="<%=request.getContextPath()%>/duty/css/prototype.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/duty/css/linkage.js"></script>

<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup();changesheetid()">
<div align="center">
<br>
<form action="../part/term.do" method="post" name="TawOrderForm" onsubmit="return checkdata()">
<xml id="dataSrc2" style="height:0px; width:0px; visibility:hidden;">
<TreeNodes>
	<%=xmltree%>

</TreeNodes>
</xml>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('填写出库申请单')}</b>
      </td>
    </tr>
</table>

<!--html:hidden property="spList" name="tawOrderForm" /-->
<table border="0" width="75%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('出库类型：')}</td>
                  <td width="70%" height="25" colspan="2">       
                         <select name="orderType" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;" onchange="changesheetid()">

  	                        <option value="">${eoms:a2u('请选择出库类型')}</option>
                          <%
                             for (int k = 0; k < typelist.size(); k++) {
 								              String[] strt = (String[])typelist.get(k);
                          %>
                            <option value="<%=strt[0].toString()%>"><%=strt[1].toString()%></option>
                            <%
                            }
                            %>
                         </select>
                  <font color="#FF0000">**</font></td>
    </tr>	
    <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="order.operater"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" readonly="readonly" value="<bean:write name="userName" scope="request"/>"size="35"  maxlength="50" name="operater">
						  <input type="hidden" readonly="readonly" value="<bean:write name="userId" scope="request"/>"size="35"  maxlength="50" name="operaterId">
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('备件所在分公司：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <select name="prop_dept" id="s1" style="width: 6.8cm;"></select>
                  <!--
                         <select name="prop_dept" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                      <logic:iterate id="DEPT" name="DEPT" type="com.boco.eoms.jbzl.model.TawDept">
                           <option value="<bean:write name="DEPT" property="id" scope="page"/>"><bean:write name="DEPT" property="deptName" scope="page"/></option>
  	                   </logic:iterate>
                         </select>
                 -->
                         <font color="#FF0000">**</font>
                   </td>
    </tr>
    <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所在仓库仓库：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="destStorageId" id="s2" style="width: 6.8cm;"></select>
                    <!--
                  	<select name="destStorageId" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
            				<logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
                    </logic:iterate>
                        </select>
                   -->
                         <font color="#FF0000">**</font>
                     </td>
    </tr>

    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('申请人：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" readonly="readonly" value="<bean:write name="userName" scope="request"/>"size="35"  maxlength="50" name="proposer">
						  <input type="hidden" readonly="readonly" value="<bean:write name="userId" scope="request"/>"size="35"  maxlength="50" name="proposerId">			  
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('工单号：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="sheetid" size="35"  value="<bean:write name="sheetid" scope="request"/>" maxlength="255" readonly="readonly">
                  <font color="#FF0000">**</font></td>
                  <!--
									<a href="<%=request.getContextPath()%>/newworksheet/All/query.do">选择</a>
                  -->
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('原因说明：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="reason" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('大专业：')}</td>
                  <td width="70%" height="25" colspan="2">
		           <select name="nettype" id="nettype" USEDATA="dataSrc2" SUBCLASS="1">
                  </td>
	 </tr>	
    <tr class="tr_show">	
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('名称：')}</td>
                  <td width="70%" height="25" colspan="2">
					    <select name="objecttype" id="objecttype" USEDATA="dataSrc2" SUBCLASS="2">
                 </td>
     </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('规格型号：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <!--<input type="text" name="objtype" size="35"  maxlength="255" >-->
                        <select name="objtype" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                          <option value="">${eoms:a2u('全选')}</option>
                          <%
                             for (int i = 0; i < objtypelist.size(); i++) {
 								              String stro = (String)objtypelist.get(i);
                          %>
                            <option value="<%=stro%>"><%=stro%></option>
                            <%
                            }
                            %>
                        </select>							
                  </td>
    </tr>
    <tr class="tr_show" >
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('具体存放位置：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <!--<input type="text" name="position" size="35"  maxlength="255" >-->
                        <select name="position" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                          <option value="">${eoms:a2u('全选')}</option>
                          <%
                             for (int i = 0; i < positionlist.size(); i++) {
 								              String strp = (String)positionlist.get(i);
                          %>
                            <option value="<%=strp%>"><%=strp%></option>
                            <%
                            }
                            %>
                        </select>					  
                  </td>
    </tr>	  
</table>
<table border="0" width="75%" cellspacing="0">
   <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="reset" value="<bean:message key="label.reset"/>" name="reset" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
<script language="javascript">
function checkdata() {

        if ( document.TawOrderForm.operater.value == "" ) {
                alert('${eoms:a2u("请输入经手人！")}');
                document.TawOrderForm.operater.focus();
                return false;
        }

        if ( document.TawOrderForm.proposer.value == "" ) {
                alert('${eoms:a2u("请输入申请人！")}');
                document.TawOrderForm.proposer.focus();
                return false;
        }
        if ( document.TawOrderForm.orderType.value == "" ) {
                alert('${eoms:a2u("请出库类型！")}');
                document.TawOrderForm.orderType.focus();
                return false;
        }
//<!--        if ( document.TawOrderForm.flditimelong.value == "" )
//        {
//             document.TawOrderForm.flditimelong.value="120";
//        }
//        else
//        {
//           if(!IsNumber(document.TawOrderForm.flditimelong.value))
//           {
//              alert("保修时长必须为数字!");
//              document.form1.flditimelong.focus();
//              return false;
//           }
//        }-->

       return true;
}
function changesheetid() {
var linkage2 = new Linkage("dataSrc2");
linkage2.init();
var type=document.TawOrderForm.orderType.value;
var sheetid=document.TawOrderForm.sheetid.value;
var b_str=sheetid.substr(0,11);
//alert(b_str);
var a_str=sheetid.substr(12,16);
//alert(a_str);
sheetid=b_str+type+a_str;
//alert(sheetid);
document.TawOrderForm.sheetid.value=sheetid;
}
</script>
