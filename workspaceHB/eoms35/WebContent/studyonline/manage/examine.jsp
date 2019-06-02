<%@ page contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*"%>
<html:html>
<head>
<title>
在线考试
</title>
<script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<%
  //int Page = StaticMethod.nullObject2int( request.getParameter("pager.size") );
  int offset = StaticMethod.nullObject2int( request.getParameter("pager.offset") );
  int subnum = offset + 1;
%>

<script language="JavaScript" >
function checkItem(form)
{
   for (var i=0;i<form.elements.length;i++) {
     var e = form.elements[i];
     if (e.type=='checkbox' && e.alt != 'tag'){
	  if (e.checked){
            if( form.options.value == "" || form.options.value == null )
              form.options.value = e.name;
            else
              form.options.value = form.options.value + ";" + e.name;
	  }
     }
     else if (e.type=='checkbox' && e.alt == 'tag' ){
       if(e.checked){
         if( form.tags.value == "" || form.tags.value == null )
             form.tags.value = e.name;
         else
             form.tags.value = form.tags.value + ";" + e.name;
       }
     }
   }
}

function view(image){
        var win;
        win=window.open("/EOMS_J2EE/studyonline/manage/view.jsp?fileName="+image,"图片显示","height=350,width=350,left=0,top=350,resizable=yes,scrollbars=yes,status=no");
}

function finish(){
  if ( !confirm("交卷后则不能再进行试卷填写。是否确认？"))
    return false;
  var form=document.forms[0];
  form.isFinish.value="true";
  checkItem(form);
  form.submit();
}
function goPage(length,offset){
  var form=document.forms[0];
  form.action = form.action + "?pager.offset="+offset+"&pager.size="+length;
  checkItem(form);
  //alert(form.action);
  form.submit();
}
</script>
</head>

<bean:define id="Config" name="OnlineConfig" type="com.boco.eoms.studyonline.model.OnlineConfig"/>
<bean:define id="hadSel" name="Sel" type="java.lang.String"/>

<%//取出标记内容
  String tagsSess = StaticMethod.nullObject2String( request.getAttribute("tags") );
  String[] tagsArray = tagsSess.split(";");
%>

<body bgcolor="#ffffff">
<html:form action="examine.do">
<input type="hidden" name="options" >
<input type="hidden" name="tags" >
<input type="hidden" name="isFinish" >
  <table cellpadding="0" cellspacing="0" width="95%" border="0">
  <tr>
    <td align="left" width="10">
      <input type="button" value="交卷" onclick="return finish()"/>
    </td>
    <td align="center" class="table_title" width="60%">
      <%=StaticMethod.getGBString( Config.getTitle() )%>
    </td>
    <td align="center" class="table_title" width="30%">
      考试时间:<%=Config.getStartTime().toString().substring(11,19)%>--<%=Config.getEndTime().toString().substring(11,19)%>
    </td>
  </tr>
  <tr align="right">
    <td colspan="3"><bean:write name="pagerHeader" scope="request" filter="false"/></td>  <!--分页-->
  </tr>
  <tr>
    <td colspan="2">
       已标记的题目:&nbsp;
       <%
         for(int k=0; k<tagsArray.length && !"".equalsIgnoreCase(tagsArray[0]); k++){
           String[] tmpArray = tagsArray[k].split(":");
           String tagurl = "<a href='examine.do?pager.offset="+tmpArray[1]+"'>"+tmpArray[0]+"</a>";
           out.print( tagurl + "&nbsp;" );
         }
       %>
    </td>
  </tr>
</table>

<div id="divTable" style="position: relative; align: center; top: 5px;width:  100%; height:  85%; z-index: 1; overflow: auto; overflow-x: hidden">
<table cellpadding="1" cellspacing="1" width="95%" border="0" class="table_show">
<logic:iterate id="SubjectObj" name="OnlineWarehouse" type="com.boco.eoms.studyonline.model.OnlineWarehouse">
<%
  String[] options = SubjectObj.getOptions().split(";");
  //System.out.println(options[0]);
%>
 <tr align="center" class="tr_show">
   <td width="12%" bgcolor="#BEDEF8">                                       <!--标记-->
     标记此题
     <input name="<%=SubjectObj.getSubId() + subnum%>" type="checkbox"  alt="tag" <%if(tagsSess.indexOf("("+subnum+")") != -1) out.println("checked");%>>
   </td>
   <td width="88%" align="left" bgcolor="#BEDEF8">
     题<%=subnum++%>：
     <%=StaticMethod.getGBString(SubjectObj.getTitle())%>                  <!--标题-->
     <logic:notEmpty name="SubjectObj" property="image">                   <!--图片-->
          <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" onclick="view('<%=SubjectObj.getImage()%>')">
     </logic:notEmpty>
   </td>
 </tr>

<%//选项格式为  A|B.选项1;B|D.选项2
  for(int i = 0; i < options.length; i++ ){
    String comment =
       options[i].trim().substring(0,1) + options[i].trim().substring(2).trim().substring(1);
    String opt = options[i].trim().substring(2).trim().substring(0,1);
    String checkName = SubjectObj.getSubId() + opt;
%>
  <tr align="center" class="tr_show">
    <td width="12%">
      <input name="<%=checkName%>" type="checkbox"  <%if( hadSel.indexOf( checkName ) != -1 ) out.print("checked");%>>
    </td>
    <td width="88%" align="left">
      <%=StaticMethod.getGBString(comment)%>
    </td>
  </tr>
<%
  }
%>
</logic:iterate>
</table>
</div>
</html:form>
</body>
</html:html>
