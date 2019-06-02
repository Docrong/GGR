<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpAddonsTableVO"%>
<%@ page import ="java.util.Iterator"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>

<script language="JavaScript">
Ext.onReady(function(){
	colorRows('list-table');
})

  function onAdd(){
    document.form1.action="designadd.do";
    document.form1.submit();
  }

  function onRemove(addonsid){
      if (confirm("<bean:message key="addonslist.title.ifDelete" />")==true){
        document.form1.action="addonsremove.do?addonsid="+addonsid;
        document.form1.submit();
      }
  }
function onAddExcel(){
    document.form1.action="addonsadd.do";
    document.form1.submit();
  }
function onFile(obj,obj2)
{
	var objectName=obj.name;
	var objectValue=obj.value;
	var object2Name=obj2.name;
	var object2Value=obj2.value;
	var _sHeight = 200;
    var _sWidth = 420;
    var sTop=(window.screen.availHeight-_sHeight)/2;
    var sLeft=(window.screen.availWidth-_sWidth)/2;
    var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
    window.showModalDialog('../addons/fileupload.jsp?name='+ objectName+'&numname='+ object2Name+'&resulturl='+objectValue+'&action=edit',window,sFeatures);
}
</script>

<%
List addonslist = (List)request.getAttribute("addonslist");
TawwpAddonsTableVO tawwpAddonsTableVO = null;
Enumeration tablesEnumeration = null;
String id="";
%>
  <form name="form1" method="POST">
   <table width="600" class="listTable" id="list">
    <caption><bean:message key="addonslist.title.formTitle" /></caption>
  <%
    //如果附加表hashtable不为空
    if(addonslist.size()!= 0){
  %>
   <thead>
         <tr>
           <td width="30%"><bean:message key="addonslist.title.formName" /></td>
           <td width="20%"><bean:message key="addonslist.title.formComments" /></td>
           <td width="10%"><bean:message key="addonslist.title.formView" /></td>
           <td width="10%"><bean:message key="addonslist.title.formEdit" /></td>
           <td width="10%"><bean:message key="addonslist.title.formRemove" /></td>
     	  </tr>
       </thead>
     <%
       for(int i=0; i<addonslist.size(); i++){
          tawwpAddonsTableVO = (TawwpAddonsTableVO)addonslist.get(i);
     %>
       <tbody>
         <tr>
           <td><%=tawwpAddonsTableVO.getName()%></td>
           <td><span><%=tawwpAddonsTableVO.getText()%></span></td>
           <td>           
<%    
			if(tawwpAddonsTableVO.getUrl()!=null&&tawwpAddonsTableVO.getUrl().endsWith(".xls")){
			  
 %>
               <a href='../addons/ntko/showDetail.jsp?path=<%=tawwpAddonsTableVO.getUrl()%>' ><bean:message key="addonslist.title.formView" /></a> 

<%			}else{
 %>            <a href="addonsview.do?action=read&window=self&myid=&model=50&addonsid=<%=tawwpAddonsTableVO.getId()%>&reaction=/tawwpaddons/addonslist.do"><bean:message key="addonslist.title.formView" /></a>

<%			}
 %>        </td>
           <td>
  			  <a href="designedit.do?addonsid=<%=tawwpAddonsTableVO.getId()%>">
                  <img src="${app }/images/icons/edit.gif" width="19" height="19">
               </a>
          </td>
           <td>
               <a href="javascript:onRemove('<%=tawwpAddonsTableVO.getId()%>')" >
                  <img src="${app }/images/icons/icon.gif" width="23" height="30">
               </a>
           </td>
        </tr>
    <%
      }
    %>

 <% }else{%>
        <tr>
	       <td height="25"   >
  		     <bean:message key="addonslist.title.nolist" />
  		     
		   </td>
        </tr>
     

 <%
    }
     
 %>
  </tbody>
</table>
 <br/><%--
 <input type="button" Class="button" value="<bean:message key="addonslist.title.formAdd" />" onclick="onAdd();">
 --%><input type="button" Class="button" value="<bean:message key="addonslist.title.formAdd" />EXCEL" onclick="onAddExcel()">
 <br/>
</form>
<!--
<table>
  <tr>
    <td>
测试显示、修改导入导出等实例
	<a href="addonsread.do?action=edit&myid=testmytest&model=50&addonsid=8a8086ba07b0b8b70107b0b9dd210001&reaction=test.jsp&window=new">修改</a>
	<a href="addonsexport.do?reaction=addons/filedownload.jsp&url=saveXML/50/testmytest.xml">导出附加表</a>

	导入测试
	<form action="addonsimport.do" method="post" enctype="multipart/form-data">
	<input type="file" name="theFile"><input type="submit" value="导入">
          <input type="hidden" name="addonsid" value="8a8086ba07b0b8b70107b0b9dd210001">
          <input type="hidden" name="reaction" value="/tawwpaddons/addonslist.do">
</form>

</td>
</tr>
</table>
-->

<%@ include file="/common/footer_eoms.jsp"%>