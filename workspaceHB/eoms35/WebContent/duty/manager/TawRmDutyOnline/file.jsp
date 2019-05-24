<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.*"%>
<script language="JavaScript" src="../css/Validator.js"></script>
<script>
function checkForm(form,num){ 
 
 
    if(document.getElementById("thisFile").value==null||document.getElementById("thisFile").value==""){
    	alert("${eoms:a2u('请选择上传文件')}");
    	return false;
    }
   if(document.getElementById("thisFile").value.indexOf(".JPG")>0 ||document.getElementById("thisFile").value.indexOf(".png")>0 ||document.getElementById("thisFile").value.indexOf(".bmt")>0 ||document.getElementById("thisFile").value.indexOf(".jif")>0 ||document.getElementById("thisFile").value.indexOf(".jpg")>0 ){
    	 return Validator.Validate(form,num);
   }
    else{
  	 alert("${eoms:a2u('请选择图片格式的文件上传,比如说.JPG,.png,.jif 文件')}");
    	return false;
    }
}
function GoBack()
{
  window.history.back()
}
</script>
 
<html:form action="TawRmDutyOnline/save" method="post"
	styleId="TawRmDutyOnlineForm" enctype="multipart/form-data"
	onsubmit="return checkForm()">
	<table border=0 cellspacing="1" cellpadding="1" >
	   <tr>
		   <td>
	<input name="thisFile" type="file" class="file" />
	<input type="submit" value="<bean:message key="designer.title.btnSubmit" />" class="button">
			${eoms:a2u('请选择图片格式的文件上传,比如说.JPG,.png,.jif 文件')} 
			</td>
		</tr>
 
	</table>
</html:form>

 <%
	synchronized (application) {
		String Name = (String) session.getAttribute("UserName");
		Vector outMessage=null;
		outMessage= (Vector)application.getAttribute("Message");
		String url= (String) request.getAttribute("url");
		String say = (String)request.getAttribute("SAY");
		System.out.println(url);
		if(url!=null){
		 
		
		//outMessage= new Vector(30,10);
		String path = request.getContextPath();
		System.out.println(path);
		String outstr="";
		outstr ="<font color=blue>"+Name+"  "+say+"</font>: <br><img src='"+path+"/"+url+"' /><br>"  ;
		String str1="no";
		String systemSpeak = "yes";
		outMessage.addElement(str1);
		outMessage.addElement(Name);
		outMessage.addElement(str1);
		outMessage.addElement(outstr);
		outMessage.addElement(systemSpeak);
		outMessage.addElement(str1);
		application.setAttribute("Message", outMessage);
		 
		}
	
	}

%>
<%@ include file="/common/footer_eoms.jsp"%>
