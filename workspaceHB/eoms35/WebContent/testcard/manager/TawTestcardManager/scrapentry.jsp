<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
<%@ page import="com.boco.eoms.testcard.dao.TawEventDicDAO"%>
<%


 String iccidlist =(String)request.getAttribute("iccidlist");
 %>



<html>
<head>
  <title>${eoms:a2u("测试卡报废")}</title>

</head>
<body>
<html:form method="post" action="/TawTestcardManager/scrapsave" onsubmit="return check(this);">
<script language="javascript">


function isnumeric(p)
{
 if (p == "")
  return false;
 var l = p.length;
 var count=0;
 for(var i=0; i<l; i++)
 {
  var digit = p.charAt(i);
  if(digit == "." )
 {
    ++count;
    if(count>1) {
	return false; }
   }
  else if(digit < "0" || digit > "9")
  {
  return false;
  }
 }
 return true;
}
</script>
<script language="JavaScript" >
function bb(main){
if (document.tawTranfaultreportForm.city.options.length!=0) {
for (i=1;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value==main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}

function check(){
if(document.tawTestcardManagerForm.contect.value=="")
{
  alert("${eoms:a2u('联系方式不得为空')}") 
  return false;
}
document.tawTestcardManagerForm.leave.value="<%=request.getAttribute("leave")%>";
}
</script>
<html:hidden property="strutsAction" value="5" />
<html:hidden property="id"/>
<html:hidden property="leave"/>
<table cellSpacing="0" class="formTable" cellPadding="2" width="35%"   border="1" align="center">
                                    <tr> <td  colspan="4" height="25" valign="middle"  align="center"><font size="-1"><b>
							${eoms:a2u("测 试 卡 报 废 管 理")}&nbsp&nbsp&nbsp&nbsp
                               </b>  </font> </td></tr>
        <tr>
                <td noWrap bgcolor="#BEDEF8" width="100"> ${eoms:a2u("卡号(iccid)")}</td>
                <td >
                  <input value="<%=request.getAttribute("cardid")%>" readonly="readonly" name="cardid" type="text" />
                </td>
                </tr>
                <tr>
              <td noWrap width="100" bgcolor="#BEDEF8">${eoms:a2u("MSISDN")}</td>
                <td  >
                  <input readonly="readonly" name="msisdn" value="<%=request.getAttribute("msisdn")%>" />
                </td>
        </tr>
        <tr>
                <td noWrap width="100" bgcolor="#BEDEF8">${eoms:a2u("报废人")}</td>
                <td >
                  <input readonly="readonly" name="dealer" value="<%=request.getAttribute("filler")%>" type="text" />
                </td>
       </tr>
       <tr>
         <td noWrap width="100" >${eoms:a2u("联系方式")}</td>
                <td>
                        <html:text  property="contect" size="20"/>
                </td>
                </tr>
                <tr>
               <td noWrap width="100">
                     ${eoms:a2u("报废时间")}
              </td>
              <td width="380">
                      
                     <input type="text" name="leantime" size="30" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true" 
					class="text">
              </td>
              </tr>
       <tr>
            <td noWrap width="100" >
                      ${eoms:a2u("备注")}
                </td>
                <td>
                       <html:textarea  property="reason" rows="4" cols="30" />
                       <INPUT TYPE="hidden" name="iccidlist" id="iccidlist" value="<%=iccidlist%>">
                       <%System.out.println("iccidlist==" + iccidlist); %>
                </td>
                
       </tr>
       
       
<!--       <tr>
         <td height="25" bgcolor="#E5EDF8" width="20%">导入文件的路径与名称：</td>
         <td height="25" bgcolor="#E5EDF8" width="80%" colspan="3">
         <input type="file" name="theFile" size="40" value=""  maxlength="255" >
         </td>
       </tr>-->
       <td colspan=4>
				                 <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">

                           <tr align="right" valign="middle">
						        <td>


                     <html:submit styleClass="button" onclick="return check()">
                     ${eoms:a2u("保存")}
        <%--<bean:message key="label.save"/>
      --%></html:submit>
      <html:reset styleClass="button">
         ${eoms:a2u("重置")}
      <%--
   
         <bean:message key="label.reset"/>
      --%></html:reset>&nbsp;&nbsp;
						        </td>
					               </tr>
       </table>
          </td>
	</tr>
</table>
</html:form>
</body>
</html>
