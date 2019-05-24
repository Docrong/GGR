<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/formlibs.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>
<%@ include file="/common/xtreelibs.jsp"%>

<%
String webapp = request.getContextPath();
String path = request.getContextPath();
%>

<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("dept","${app}/xtree.do?method=dept&id=-1&t=deptCheckboxTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "deptName";
	tree.hiddField = "deptId";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<html:form action="saveTawSystemPost" method="post" styleId="tawSystemPostForm">


<center>
<br>
<table border="1" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center">
        <b>
        <eoms:label styleClass="desc" key="tawSystemPostForm.addPost"/>
        </b>
    </td>
  </tr>
</table>

<table border="1" width="95%" cellspacing="1" cellpadding="1"
class="table_show" align=center>

  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth"><eoms:label styleClass="desc" key="tawSystemPostForm.postName"/>
  	</td>
  	<td width="70%" height="25">
        <html:text property="postName" styleId="postName" styleClass="text medium" value=""/>
  	</td>
  </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth"><eoms:label styleClass="desc" key="tawSystemPostForm.parentPostName"/>
  	</td>
  	<td width="70%" height="25">
        <html:hidden property="parentId"/>
       <bean:write name="tawSystemPostForm" property="postName" scope="request"/>
  	</td>
  </tr>
  <tr>
  	<td width="30%" height="25" class="clsfth">
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.deptId"/>
    </td>
    <td width="70%" height="25">
        <html:errors property="deptId"/>
        <html:hidden property="deptId" styleId="deptId" styleClass="text medium"/>
        <input type="text" name="deptName" id="deptId" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </td>
  </tr>
  <tr>
  	<td width="30%" height="25" class="clsfth">
        <eoms:label styleClass="desc" key="tawSystemPostForm.postUsers"/>
    </td>
    <td width="70%" height="25">
        <html:errors property="userId"/>
        <input type="text" name="userId" id="userId" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth"><eoms:label styleClass="desc" key="tawSystemPostForm.postRemark"/>
   </td>
    <td width="70%" height="25">
      <html:textarea property="notes" rows="6" style="width:5.8cm;" styleClass="clstext"/>
     </td>
  </tr>
 </table>
 
 <table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
    	<html:submit styleClass="clsbtn2" property="method.saveNew">
            <fmt:message key="button.save"/>
        </html:submit>&nbsp;&nbsp;
      <html:reset styleClass="clsbtn2"><bean:message key="button.reset"/>
      </html:reset>&nbsp;&nbsp;
      <input type="button" class="clsbtn2"  value="<bean:message key="button.cancel"/>"  name="button" onclick="goto();">
      &nbsp;&nbsp;
	</td>
  </tr>
</table>

</center>

</html:form>


<%@ include file="/common/footer_eoms.jsp"%>
