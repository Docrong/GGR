<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<body>
<div id="page">


  <div id="content" class="clearfix">
    <div id="main"><br/><br/>
<html>
<head>
<title>table</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">

<script language="javascript">
function addattach()
{
  if (tawRmDutyfileForm.attachName.value==""){
  return false;
  }
  else
  {
  var fileStr= tawRmDutyfileForm.attachName.value;
  var index=fileStr.lastIndexOf("\\");
  fileStr=fileStr.substring(index+1,fileStr.length);
  tawRmDutyfileForm.filename.value=fileStr;
  //alert (fileStr);
  tawRmDutyfileForm.submit();
  return true;
  }
}
function checkdelete(){
  var bolCheck= false;
   
  if (document.forms[0].elements[0].name!="ListDelete")
  {
    alert("");
    return bolCheck;
  }
  else{
    if(document.forms[0].ListDelete.length>0){
      var listNum=document.forms[0].ListDelete.length;
      document.forms[0].selected_id.value = "-1";
      for(i=0;i<listNum;i++){
          if(forms[0].ListDelete[i].checked)
          {
            document.forms[0].selected_id.value = document.forms[0].selected_id.value+","+document.forms[0].ListDelete[i].value;
            bolCheck=true;
          }
       }
    }
    else{
     if(document.forms[0].ListDelete.checked)
     {
          document.forms[0].selected_id.value=document.forms[0].ListDelete.value;
          bolCheck=true;
     }

    }
    if (!bolCheck){
     alert("");
    }
    else{
      if (confirm("确认你要删除此附件吗？")){
         tawRmDutyfileForm.action="delete.do";
         tawRmDutyfileForm.submit();
      }
    }
    //alert(document.tawRmDutyfileForm.selected_id.value);
    return bolCheck;
  }
}

</script>
</head>

<body leftmargin="0" topmargin="0">
<div style="width:100%;height:100%;background-color:#E7EFFF;">
<form id="tawRmDutyfileForm" method="post" action="/eoms/duty/TawRmDutyfile/save.do" >
<table cellpadding="1" cellspacing="0" border="0" width="100%" class=""formTable>



<tr>
<td class="label">${eoms:a2u('任务工单')}</td>
<td>
<a href='/eoms/duty/upload/2008070922014653.ppt' target='blank'>${eoms:a2u('EOMS3.5任务工单培训手册.ppt')}</a>
</td>
<td>
<a href='/eoms/duty/upload/2008070922015242.doc' target='blank'>${eoms:a2u('EOMS-V3.5任务工单用户手册.doc')}</a>
</td>
</tr>

<tr>
<td class="label">${eoms:a2u('值班管理')}</td>
<td>
<a href='/eoms/duty/upload/2008070922020344.doc' target='blank'>${eoms:a2u('EOMS-V3.5值班管理用户手册.doc')}</a>
</td>
<td>
<a href='/eoms/duty/upload/2008070922021102.ppt' target='blank'>${eoms:a2u('EOMS-V3.5值班管理培训手册.ppt')}</a>
</td>
</tr>

<tr>
<td class="label">${eoms:a2u('个人首页')}</td>
<td>
<a href='/eoms/duty/upload/2008070922040034.ppt' target='blank'>${eoms:a2u('EOMS-V3.5培训手册_个人首页.ppt')}</a>
</td>
<td>
<a href='/eoms/duty/upload/2008070922042263.doc' target='blank'>${eoms:a2u('EOMS-V3.5用户手册_个人首页.doc')}</a>

</td>
</tr>

<tr>
<td class="label">${eoms:a2u('组织管理')}</td>
<td>
<a href='/eoms/duty/upload/2008070922041215.ppt' target='blank'>${eoms:a2u('EOMS-V3.5培训手册_组织管理.ppt')}</a>
</td>
<td>
<a href='/eoms/duty/upload/2008070922043033.doc' target='blank'>${eoms:a2u('EOMS-V3.5用户手册_组织管理.doc')}</a>
</td>
</tr>

<tr>
<td class="label">${eoms:a2u('作业计划')}</td>
<td>
<a href='/eoms/duty/upload/2008070922160490.ppt' target='blank'>${eoms:a2u('EOMS-3.5培训手册_作业计划.ppt')}</a>
</td>
<td>
<a href='/eoms/duty/upload/2008070922161104.doc' target='blank'>${eoms:a2u('EOMS-V3.5用户手册_作业计划.doc')}</a>
</td>
</tr>

</table>

</form>
</div>
</body>
</html>