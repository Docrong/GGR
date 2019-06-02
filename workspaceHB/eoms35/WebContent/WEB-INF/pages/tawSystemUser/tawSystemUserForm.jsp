<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
1111111111111111111111
<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("deptuser","${app}/xtree.do?method=deptuser&id=-1&t=userDeptTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "deptname";
	tree.hiddField = "deptid";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<script type="text/javascript">
	Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=getCptroomTree';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div>${eoms:a2u("所属部门")} </div>'
								
		}
	);
	userViewer.refresh();
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'cptroom',
		viewer:userViewer,saveChkFldId:'cptroomid' 
	});
})
</script>
<title><fmt:message key="tawSystemUserDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemUserDetail.heading"/></content>

<html:form action="saveTawSystemUser" method="post" styleId="tawSystemUserForm" styleClass="required-validate"> 
<c:set var="f">tawSystemUserForm</c:set>
<ul>
<html:hidden property="id"/>
<html:hidden property="updatetime"/>
<html:hidden property="savetime"/>
<html:hidden property="operremotip"/>
<html:hidden property="userdegree"/>
<html:hidden property="deleted"/>
   <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.operuserid"/>
        <html:errors property="operuserid"/>
        <html:text property="operuserid" styleId="operuserid" styleClass="text medium"/>

    </li>
	<li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium required max-length-100" />

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.username"/>
        <html:errors property="username"/>
        <html:text property="username" styleId="username" styleClass="text medium required max-length-100" />
    </li>
       <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.passwd"/>
        <html:errors property="passsword"/>
        <html:text property="password" styleId="password" styleClass="text medium required max-length-100"/>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.cptroomname"/>
        <html:errors property="cptroomid"/>
       <eoms:comboBox name="cptroomid" id="cptroomid" initDicId="3301" form="${f}"/>
       <html:hidden property="cptroomid" styleId="cptroomid" styleClass="text medium"/>
			
				<div id="user-list" class="viewer-list">
				</div>
				     
    		<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn" class="btn"/> 
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.deptid"/>
        <html:errors property="deptid"/>
         <!--  <html:select property="deptid">
          <html:options collection="deptlist" property="deptId" labelProperty="deptName"/>
        </html:select>-->
        <html:hidden property="deptid" styleId="deptid" styleClass="text medium"/>
        <input type="text" name="deptname" id="deptname" class="text medium"/>
        <input type="button" value="<fmt:message key="label.selectTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.email"/>
        <html:errors property="email"/>
        <html:text property="email" styleId="email" styleClass="text medium required max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.familyaddress"/>
        <html:errors property="familyaddress"/>
        <html:text property="familyaddress" styleId="familyaddress" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.fax"/>
        <html:errors property="fax"/>
        <html:text property="fax" styleId="fax" styleClass="text medium  max-length-100"/>

    </li>



    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.mobile"/>
        <html:errors property="mobile"/>
        <html:text property="mobile" styleId="mobile" styleClass="text medium required max-length-100"/>

    </li>

   

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.phone"/>
        <html:errors property="phone"/>
        <html:text property="phone" styleId="phone" styleClass="text medium  max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.sex"/>
        <html:errors property="sex"/>

          <eoms:comboBox name="sex" id="sex" initDicId="3302" form="${f}"/>


    </li>
    
  <li>
        <eoms:label styleClass="desc" key="tawSystemUserForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium max-length-100"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemUser')">
            <fmt:message key="button.delete"/>
        </html:submit>
        <html:submit styleClass="button" property="method.deletes" onclick="bCancel=true; return confirmDelete('TawSystemUser')">
            <fmt:message key="button.deletes"/>
        </html:submit>
        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemUserForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>