<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import="com.boco.eoms.testcard.controller.TawTestcardForm,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

 <html>
<head>
<title> ${eoms:a2u("测试卡激活统计")}</title> 
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/areafortestcard.js"></script>

</head>
<body>
<html:form  method="post" action="/TawTestcard/activateStat">

<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
	<tr>
        <td align="center" class="table_title">
        	<b> ${eoms:a2u("测 试 卡 激 活 统 计")}</b>
        </td>
        </tr>
</table>
<table border="0" width="95%" cellspacing="1" class="formTable middle" align="center">
          <tr  id="visitField2">
                <td noWrap width="80"  class="label">
                       ${eoms:a2u("存放公司")}
                </td>
                <td width="380">
                    <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
                   
                </td>
                
       </tr>
       
       <tr>
		<td colspan=2>
                <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
                <tr align="middle" valign="middle">
                <td>
                        <html:submit styleClass="button" onclick="return check()">
        			 ${eoms:a2u("统计")}
      			</html:submit>
      		 </td>
        </tr>
	</table>
        </td>
	</tr>
</table>
</div>
</td>
</tr>
</table>
</html:form>
</body>
</html>
