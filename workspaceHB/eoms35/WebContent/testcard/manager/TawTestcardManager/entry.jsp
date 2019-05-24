<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
 <%

String webapp = request.getContextPath();
String date = StaticMethod.getLocalString();
String aftertommorrow = StaticMethod.getLocalString(7,0);
 String iccidlist =(String)request.getAttribute("iccidlist");
 %>
<html>
<head>
  <title> ${eoms:a2u("æµè¯å¡ååºæä½é¡µé¢")}</title>
 
</head>
<body>
<html:form method="post" action="/TawTestcardManager/save" onsubmit="return check(this);">
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
			new xbox({
				btnId:'sort1_userName',dlgId:'dlg-audit',dlgTitle:'${eoms:a2u('è¯·éæ©äººå')}',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('ææäººå')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'sort1_userName',saveChkFldId:'sort1_userid'
			}); 
})
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawTestcardManagerForm'});
});
 
</script>
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

  //if(document.forms[0].lenddept.value=="")
//{
  //alert('${eoms:a2u("åç¨é¨é¨ä¸å¾ä¸ºç©º")}');
  //return false;
//}
if(document.forms[0].lender.value=="")
{
  alert('${eoms:a2u("åç¨äººä¸å¾ä¸ºç©º")}');
  return false;
}
//if(document.forms[0].contect.value=="")
//{
//alert('${eoms:a2u("èç³»æ¹å¼ä¸å¾ä¸ºç©º")}');
  //return false;
//}
if (document.forms[0].belongtime.value=="")
{
alert('${eoms:a2u("åºå½è¿æ¶é´ä¸å¾ä¸ºç©º")}');
  return false;
}
document.forms[0].leave.value="<%=request.getAttribute("leave")%>";
  return false;
}
</script>
<html:hidden property="strutsAction" />
<html:hidden property="id"/>
<html:hidden property="leave"/>
<table cellSpacing="0" borderColorDark="#808080" cellPadding="2" width="70%" class="listTable" border="1" align="center">
        <tr><td  colspan="4" height="25" valign="middle"  class="label" align="center"><font size="-1">${eoms:a2u("æµ è¯ å¡ å åº ç®¡ ç")}&nbsp&nbsp&nbsp&nbsp</font></td></tr>
        <tr>
                <td noWrap width="100" class="label">&nbsp${eoms:a2u("ååºäºº")}</td>
                <td  colspan = 3>
                  <input readonly="readonly" name="dealer" value="<%=request.getAttribute("filler")%>" type="text" />
                </td>
<!--                 <td noWrap width="100" bgcolor="#BEDEF8">&nbspæ¹åäºº</td>
                <td width="380">
                  <html:text styleClass="clstext" property="checker" size="20"/>
                </td>
        <tr>
        <td noWrap width="100" bgcolor="#BEDEF8">&nbspæ¹åäººèç³»æ¹å¼</td>
                <td  width="380" >
                        <html:text styleClass="clstext" property="checktel" size="20"/>
                </td>
          <td noWrap width="100" bgcolor="#BEDEF8">&nbspåç¨é¨é¨</td>
                <td width="380">
                        <html:text styleClass="clstext" property="lenddept" size="20"/>
                </td>
                 </tr>   -->
        <tr>
                <td noWrap width="100"  class="label">&nbsp${eoms:a2u("åç¨äºº")}</td>
                
               <td  width="20%">   
			<html:text property="sort1_userName" styleId="sort1_userName"
				styleClass="text medium" readonly="true" alt="allowBlank:false,vtext:'${eoms:a2u('è¯·éæ©åç¨äºº')}'" /> 
			<html:hidden property="sort1_userid" />
               </td>
                <%--
                 <html:hidden property="sort1_deptid"  value=""/>
                 <html:hidden property="sort1_userid"  value=""/>
                 <html:hidden property="sort1_postid"  value=""/>
                <span id="sort1_text"></span>
                <td width="10%" nowrap="nowrap" colspan="2">
                 <INPUT type="button" class="clsbtn2"  value=${eoms:a2u("éæ©")} name="button" Onclick="selectTree(1);">
               </td>
                
                --%>
            
                
                </tr><%--
                <tr>
         <td noWrap width="100" bgcolor="#BEDEF8">&nbspåç¨äººèç³»æ¹å¼</td>
                <td  width="380" >
                        <html:text styleClass="clstext" property="contect" size="20"/>
                </td>
                </tr>
       --%><tr>
               <td noWrap width="100"  class="label">
                     &nbsp${eoms:a2u("åç¨æ¶é´")}
              </td>
              <td >
               <input type="text" name="leantime" size="30"  value="<%=date%>" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"
                      
              </td>
          <td noWrap width="100"  class="label">
                     &nbsp${eoms:a2u("åºå½è¿æ¶é´")}
              </td>
              <td>
               <input type="text" name="belongtime" size="30"  value="<%=aftertommorrow%>" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"
             
                    
              </td>
 <!--              <td noWrap width="100"  class= "clsfth">
                     å½è¿æ¶é´
              </td>
              <td width="380">
                     <html:text property="returntime" styleClass="clstext" onfocus="setday(this)" readonly="true"/>
              </td>
              <td noWrap width="100" align="center" bgcolor="#BEDEF8">
                     ç»­åæé
              </td>
              <td colspan="3" width="380">
                    <html:text property="renewlimit" styleClass="clstext" size="20"/>
              </td>-->
       </tr>
       <tr>
            <td noWrap width="100"  class="label">
                      &nbsp${eoms:a2u("ç¨é")}
                </td>
                <td colspan="3">
                       <html:textarea  property="reason" rows="4" cols="79" />
                       <INPUT TYPE="hidden" name="iccidlist" id="iccidlist" value="<%=iccidlist%>">
                       <%System.out.println("iccidlist==" + iccidlist); %>
                </td>
       </tr>
<!--       <tr>
         <td height="25" bgcolor="#E5EDF8" width="20%">å¯¼å¥æä»¶çè·¯å¾ä¸åç§°ï¼</td>
         <td height="25" bgcolor="#E5EDF8" width="80%" colspan="3">
         <input type="file" name="theFile" size="40" value=""  maxlength="255" >
         </td>
       </tr>-->
       <td colspan=4>
			<table  height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
               <tr align="right" valign="middle"><td>
                     <html:submit styleClass="button" onclick="return check()">${eoms:a2u("ä¿å­")}</html:submit>
                     <html:reset styleClass="button">${eoms:a2u("éç½®")}</html:reset>&nbsp;&nbsp;
				</td></tr>
            </table>
          </td>
</tr>
</table>

</html:form>
</body>
</html>
