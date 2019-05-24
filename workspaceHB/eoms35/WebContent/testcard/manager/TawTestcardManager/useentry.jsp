<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.taglib.AttachmentTag"%>
<html>
<head>
  <title>${eoms:a2u("测试卡测试")}</title>
 
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
</head>
<body>
<html:form method="post" action="/TawTestcardManager/usesave" onsubmit="return check(this);">
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
if(document.tawTestcardTestingForm.outcome.value=="")
{
  alert('${eoms:a2u("测试结果不得为空")}');
  return false;
}
document.tawTestcardTestingForm.iccid.value="<%=request.getAttribute("iccid")%>";
document.tawTestcardTestingForm.leave.value="<%=request.getAttribute("leave")%>";
}
</script>

<input type="hidden" name="iccid" value="<%=request.getAttribute("iccid")%>"/>
<input type="hidden" name="leave" value="<%=request.getAttribute("leave")%>"/>

<table cellSpacing="0" borderColorDark="#808080" cellPadding="2" width="70%" class="formTable" border="1" align="center">
                                    <tr> <td  colspan="4" height="25" valign="middle" bgcolor="#e9e9e9" align="center"><font size="-1"><b>
							${eoms:a2u("测 试 卡 测 试 管 理")}&nbsp&nbsp&nbsp&nbsp
                               </b>  </font> </td></tr>
        
        <tr>
              <td noWrap width="100" bgcolor="#BEDEF8">&nbspmsisdn</td>
                <td width="380" >
                  <input readonly="readonly" name="msisdn" value="<%=request.getAttribute("msisdn")%>" />
                </td>
        </tr>
        <tr>
                <td noWrap width="100" bgcolor="#BEDEF8">&nbsp${eoms:a2u("测试人")}</td>
                <td width="380">
                  <input readonly="readonly" name="conner" value="<%=request.getAttribute("conner")%>" type="text" />
                </td>
                </tr>
       <tr>
               <td noWrap width="100" bgcolor="#BEDEF8">
                     &nbsp${eoms:a2u("测试时间")}
              </td>
              <td width="380">
                     <input type="text" name="testtime" size="30"  onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true" 
					class="text">
              </td>
      </tr>
       <tr>
            <td noWrap width="100" bgcolor="#BEDEF8">
                      &nbsp${eoms:a2u("测试结果")}
                </td>
                <td>
                       <html:textarea  property="outcome" rows="4" cols="68" />&nbsp
                </td>
       </tr>
       <tr>
            <td noWrap width="100" bgcolor="#BEDEF8">&nbsp &nbsp${eoms:a2u("附件")}</td>
            
            <td ><%--<eoms:attachment idList="" idField="accessories" appCode="wfworksheet"/>
            --%><eoms:attachment idList="" idField="accessories" appCode="testcard" scope="request"  property="accessories"/>
            
            </td>
       </tr>
       <td align="right" colspan="2">
     <html:submit styleClass="button" onclick="return check()">
       ${eoms:a2u("保存")}
      </html:submit>
      <html:reset styleClass="button">
      ${eoms:a2u("重置")}
          
      </html:reset>&nbsp;&nbsp;
          </td>
	</tr>
</table>
</html:form>
</body>
</html>
