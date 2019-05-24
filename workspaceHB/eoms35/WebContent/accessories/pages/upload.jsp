<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.ApplicationContextHolder,
                 java.util.*,com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig" %>
<fmt:bundle basename="config/ApplicationResources-accessories">
<%
 String appId=StaticMethod.nullObject2String(request.getParameter("appId"));
 String filelist=StaticMethod.nullObject2String(request.getParameter("filelist"));
 String idField=StaticMethod.nullObject2String(request.getParameter("idField"));
 ITawCommonsAccessoriesConfigManager mgrr = 
   (ITawCommonsAccessoriesConfigManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesConfigManager");
   TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig = mgrr
					.getAccessoriesConfigByAppcode(appId);
String type = tawCommonsAccessoriesConfig.getAllowFileType();
%>
<html>          
<head>
<title>
  <fmt:message key="tawCommonsAccessoriesDetail.title"/>
</title>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/theme.css" />
<script type="text/javascript">
 function confirmForm(){ 
  var frm=document.tawCommonsAccessoriesForm; 
  var appId=frm.appId.value;
  var filelist=frm.filelist.value;
  var idField=frm.idField.value;
  var filetype = '<%=type%>';
   if(frm.FILE.value=="")
    {
      alert("${eoms:a2u('请选择您要上传的文件。')}");
      return;
    }
    //alert(document.getElementById("FILE").value);
    //var test=new Array()
    var fileName = document.getElementById("FILE").value;
    var fileLast = fileName.substring(fileName.lastIndexOf(".") + 1)
    //alert(fileLast);
    //test = frm.FILE.value.split(".");
    //var lasttype = test[1];
    if(filetype!=null || !filetype.eques("")){
    if(filetype.indexOf(fileLast.toLowerCase())<0){
    	alert("${eoms:a2u('请正确选择上传文件的类型。')}" + "\n" + "${eoms:a2u('文件类型为xls,txt,doc,jpg,gif!')}");
    	return;
    }
    }
  frm.action="${app}/accessories/pages/upload.jsp?appId="+appId
             +"&filelist="+filelist+"&idField="+idField;
  
  frm.submit();
 }
 
 function deleteFile()
  {
    var frm=document.tawCommonsAccessoriesForm;
    var temp = "";
    var filelist=frm.filelist.value;
    if(filelist==""){
      alert("${eoms:a2u('请选择您要删除的文件。')}");
      return;
    }
     if(document.uploadfile.files.length != null)
     {
      for(var i=0;i<document.uploadfile.files.length;i++)
      {
        if(document.uploadfile.files[i].checked)
        {
          temp = temp + "'" + document.uploadfile.files[i].value+"',";
        }
      }
    }
    else
    {
      if(document.uploadfile.files.checked)
      {
        temp = temp + "'" + document.uploadfile.files.value+"',";
      }
    }
    if(temp.length==0)
    {
      alert("${eoms:a2u('请选择您要删除的文件。')}");
      return;
    }
   if(confirm("${eoms:a2u('您确定要删除上传的文件吗？')}")){
   
    
      location.href="remove.jsp?appId=<%=appId%>&removeid="
                     + temp.substring(0,temp.length-1)
					 +"&filelist="+filelist+"&idField=<%=idField%>";
  }
    
  }   
 
</script>
<script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
</head>
<body style="background-image:none">
<div class="upload-box">
<%
 if(!appId.equals("")){
  ITawCommonsAccessoriesManager mgr = 
   (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
  List fileList=mgr.saveFile(request,appId,filelist);
  
  if(fileList!=null){
%>
   <form name="uploadfile">
<% 
    filelist="";
    for(int i=0;i<fileList.size();i++){
      TawCommonsAccessories file=(TawCommonsAccessories)fileList.get(i);
      String fileName=file.getAccessoriesCnName();
      filelist=filelist+",'"+file.getAccessoriesName()+"'";
%>
  <input type='checkbox' name='files' value='<%=file.getAccessoriesName()%>' class="checkbox">
  <a href='${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=file.getId()%>' class="filelink">
    <%=fileName%>
  </a>
  <br>
   
<% 
  }
  if(filelist.indexOf(",")==0){
    filelist=filelist.substring(1);
  } 
 %> 
 </form>
 <%}
   else {filelist="";}
 }
%>

 <form name="tawCommonsAccessoriesForm" enctype="multipart/form-data" 
       method="post" id="tawCommonsAccessoriesForm"> 
  <table>
   <tr>
     <td colspan="2">
      
       <input type="file" id="FILE" name="FILE" size="30" class="upload" class="file">
       <input type="hidden" name="appId" value="<%=appId%>"> 
       <input type="hidden" name="filelist" value="<%=filelist%>" >
       <input type="hidden" name="idField" value="<%=idField%>" >   
     </td>
     <tr>
       <td>
          <input type="button" class="btn" value="<fmt:message key="tawCommonsAccessories.upload"/>" name="button" onclick="confirmForm();">
       </td>
       <td>
          <input type="button" class="btn" value="<fmt:message key="tawCommonsAccessories.delete"/>" name="button" onclick="deleteFile();">
       </td>
     </tr>
  </table>    
 </form>
</div>
<script type="text/javascript" src="${app}/scripts/util/iframe.js"></script>
<script type="text/javascript">
   try{
   	 parent.document.getElementById('<%=idField%>').value= "<%=filelist%>";
	 if("<%=filelist%>"!="") parent.v.clear();
   }
   catch(e){};
</script>
</body>
</html>
</fmt:bundle>