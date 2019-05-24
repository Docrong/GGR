<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.infopub.util.*,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*, com.boco.eoms.common.dao.*,com.boco.eoms.jbzl.model.*,com.boco.eoms.jbzl.dao.*"%>
<html>
  <head>
    <title>测试卡报废纪录查询页面</title>
    <link href="<%=request.getContextPath()%>/css/table_style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
  </head>
	<body>
<html:form  method="post" action="/TawTestcardManager/scrapdo" onsubmit="true">
<script language="javascript">
<!--
//以下四行用于日历显示，放在form后
var outObject;
var old_dd = null;
writeCalender();
bltSetDay(bltTheYear,bltTheMonth);
//-->

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
for (i=0;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value!=main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}
</script>
<html:hidden property="strutsAction" value="5" />
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
                             <tr>
                              <td align="center" class="table_title">
                                <b>请输入测试卡查询条件</b>
                               </td>
                              </tr>
                           </table>
<table cellSpacing="0" borderColorDark="#ffffff" cellPadding="1" width="35%" borderColorLight="#808080" border="1" align="center">
				<tr>
                <td noWrap class="clsfth">&nbsp;&nbsp;经手人</td>
                <td width="250">
                  <html:text property="dealer"/>
                </td>
                </tr>
        <tr>
          <td noWrap class="clsfth">&nbsp;&nbsp;报废分公司</td>
                <td width="250">
                		<html:select property="leave" style="width: 4.0cm;" value="">
                      <html:option value="">
                      </html:option>
                          <html:optionsCollection name="tawTestcardManagerForm" property="beanCollectionDN"/>
                    </html:select>
                </td>
        </tr>
        <tr>
                <td noWrap class="clsfth">&nbsp;&nbsp;卡号(iccid)</td>
                <td  width="250" >
                        <html:text styleClass="clstext" property="cardid" size="20"/>
                </td>
                </tr>
        <tr>
                <td noWrap class="clsfth">&nbsp;&nbsp;msisdn</td>
                <td width="250" >
                        <html:text styleClass="clstext" property="msisdn" size="20"/>
                </td>
        </tr>
      	<tr>
          <td colspan=4>
            <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
              <tr align="right" valign="middle">
                <td>
                  <INPUT type="submit" class="clsbtn2"  value="查询"  name="submit">&nbsp;&nbsp;
        <html:reset styleClass="clsbtn2">
         <bean:message key="label.reset"/>
         </html:reset>
      					</td>
              </tr>
           </table>
          </td>
         </tr>
</table>
</html:form>
</body>
</html>
