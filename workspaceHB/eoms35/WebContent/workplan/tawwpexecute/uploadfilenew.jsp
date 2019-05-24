<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import="com.boco.eoms.workplan.mgr.ITawwpExecuteMgr"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="org.apache.struts.upload.FormFile"%>
 
<%@ page import="java.io.File"%>
 

<% 
String action="";
if(request.getAttribute("action")!=null){
	action=(String)request.getAttribute("action");
}
String resulturl="";
if(request.getAttribute("resulturl")!=null){
	resulturl=(String)request.getAttribute("resulturl");
}
String name="";
if(request.getAttribute("name")!=null){
	name=(String)request.getAttribute("name");
}
String numname="";
if(request.getAttribute("numname")!=null){
	numname=(String)request.getAttribute("numname");
}

String executeContentUserIdName="";
if(request.getAttribute("executecontentuseridname")!=null){
	executeContentUserIdName=(String)request.getAttribute("executecontentuseridname");
}

String executeContentUserId="";
if(request.getAttribute("executecontentuserid")!=null){
	executeContentUserId=(String)request.getAttribute("executecontentuserid");
}
String executeContentId=""; 
if(request.getAttribute("executecontentid")!=null){
	executeContentId=(String)request.getAttribute("executecontentid");
}

String userId="";
if(request.getAttribute("userid")!=null){
	userId=(String)request.getAttribute("userid");
}

String stubUser="";
if(request.getAttribute("stubuser")!=null){
	stubUser=(String)request.getAttribute("stubuser");
}
String strShowSpanId="";
if(request.getAttribute("showspanid")!=null){
	strShowSpanId=(String)request.getAttribute("showspanid");
}
String newExecuteContentUserId = "";

String delFile="";
String myResulturl="";

String formName = "";
if(request.getAttribute("formname")!=null){
    formName = (String)request.getAttribute("formname");
} 


%>
<script language="JavaScript">
var callerWindowObj = dialogArguments;

function Transfer() {
    
	callerWindowObj.document.<%=formName%>.<%=name%>.value='<%=resulturl%>';
	callerWindowObj.document.<%=formName%>.<%=numname%>.value=tawRmCycleTableForm.num.value;
    callerWindowObj.document.<%=formName%>.<%=executeContentUserIdName%>.value=tawRmCycleTableForm.executecontentuserid.value;

  //var father = opener;
  var showSpan = callerWindowObj.document.getElementById("<%=strShowSpanId%>");
  var linkStr = "<%=resulturl%>";
  var arrLink = linkStr.split(',');
  var strTemp = "";

  for(i=0;i<arrLink.length;i++){
    if(arrLink[i]!=null&&arrLink[i]!=""){
      var arrLinkInfo = arrLink[i].split("@");
	  	strTemp += "<li><a target='_blank' href='"+arrLinkInfo[1]+"'>"+arrLinkInfo[0]+"</a><br>";
      }

  }
  showSpan.innerHTML = strTemp;
   
}

function WinClose() {
	window.close();
	}

function checkdelete(){
  var bolCheck= false;
  var myform=document.tawRmCycleTableForm;
  myform.submitBut.disabled=false;
  if (myform.elements[0].name!="ListDelete")
  {

  }
  else{
  myform.resulturl.value="";
  myform.delFile.value="";
    if(myform.ListDelete.length>=0){
      var listNum=myform.ListDelete.length;
      for(i=0;i<listNum;i++){
          if(!myform.ListDelete[i].checked)
          {
		if(myform.resulturl.value==""){
		 myform.resulturl.value = myform.ListDelete[i].value;
		}else{
		myform.resulturl.value = myform.resulturl.value+","+myform.ListDelete[i].value;
		}
          }else{
            myform.submitBut.disabled=true;
            	if(myform.delFile.value==""){
		 myform.delFile.value = myform.ListDelete[i].value;
		}else{
		myform.delFile.value = myform.delFile.value+","+myform.ListDelete[i].value;
		}

            }
       }

    }else{
	if(!myform.ListDelete.checked)
          {
		myform.resulturl.value = myform.resulturl.value+","+myform.ListDelete.value;
          }else{
           	myform.submitBut.disabled=true;
		myform.delFile.value = myform.delFile.value+","+myform.ListDelete.value;
            }
	}
  }
}
 
</script>

 

<form method="post"  target="mywindow" name="tawRmCycleTableForm" action="../tawwpexecute/uploadfilenew.do" enctype="multipart/form-data">
<fmt:bundle basename="config/ApplicationResources-workplan" >
 
<table class="formTable">
   <caption><fmt:message key="uploadfile.tawwpexecute.formTitle"  />  </caption>
   <tr>
	  <td width="30%" class="label"> <%if (!action.equals("read")){%><fmt:message key="uploadfile.tawwpexecute.labFileRemove" /><%}else{%>><fmt:message key="uploadfile.tawwpexecute.labFileDown" />  <%}%></td>
	<td>
		<%
        String[] n=null;
		String[] a=resulturl.split(",");
		int num=0;
		for(int i=0;i<a.length;i++)
		{
			if (!a[i].equals("")){
			if (!action.equals("read")){
                        n=a[i].split("@");
                        if(n.length==2){
                        if(!n[0].equals("")||!n[1].equals("")||n[0]!=null||n[1]!=null){
                          out.println("<input type=\"checkbox\" name=ListDelete value=\""+a[i]+"\" onclick=\"checkdelete()\"><a target=\"_blank\" href=\""+n[1]+"\">"+n[0]+"</a><br>");
                          }
                        }
			}else{
                          n=a[i].split("@");
                        if(!n[0].equals("")||!n[1].equals("")||n[0]!=null||n[1]!=null){
                          out.println("<li><a target=\"_blank\" href=\""+n[1]+"\">"+n[0]+"</a>");
                        }
  			}
		//附件数量
		num++;
			}
		}
		%>
	<input type="hidden" name="resulturl" VALUE="<%=resulturl%>">
	    <input type="hidden" name="action" value="add">
	    <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="delFile" value="">
	    <input type="hidden" name="numname" value="<%=numname%>">
	    <input type="hidden" name="num" value="<%=num%>">
        <input type="hidden" name="executecontentuseridname" VALUE="<%=executeContentUserIdName%>">
        <input type="hidden" name="executecontentuserid" value="<%=executeContentUserId%>">
        <input type="hidden" name="executecontentid" value="<%=executeContentId%>">
        <input type="hidden" name="userid" value="<%=userId%>">
        <input type="hidden" name="stubuser" value="<%=stubUser%>">
        <input type="hidden" name="showspanid" value="<%=strShowSpanId%>">
        <input type="hidden" name="formname" value="<%=formName%>">
	</td>
</tr>
<%if (!action.equals("read")){%>
<tr>
	  <td class="label"><fmt:message key="uploadfile.tawwpexecute.labFileUp" /> </td>
	  <td>
        <input type='file' name='thisFile' size='20' maxlength='20'>
	</td>
</tr>
<tr>
	<td colspan="2" align=center>
		<input type="hidden" name="action" value="add">
		<input type="submit" value="<fmt:message key='uploadfile.tawwpexecute.btnSubmit' />" class="submit"  >
		<input type="button" name="submitBut" value="<fmt:message key='uploadfile.tawwpexecute.btnComplete' />" class="button" onclick="Transfer();WinClose();">
	</td>
</tr>
<%}%>

</table>
</form> 
<script language="JavaScript"> 
	 window.name = "mywindow";
	 checkdelete();
	 Transfer(); 
</script>
 



</fmt:bundle>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
