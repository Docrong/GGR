<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript" src="${app}/scripts/form/fieldctrl.js"></script>
<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
    var tree = new WebFXLoadTree("deptuser","${app}/xtree.do?method=deptuser&id=0&t=userDeptTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "revecername";
	tree.hiddField = "revecer"; 
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>
<title><fmt:message key="tawCommonMessageSubscribeDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageSubscribeDetail.heading"/></content>

<html:form action="saveTawCommonMessageSubscribe" method="post" styleId="tawCommonMessageSubscribeForm"> 
<ul>
<html:hidden property="id"/>
     <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.messageid"/>
        <html:errors property="messageid"/>
        <html:select property="messageid">
          <html:options collection="servicelist" property="id" labelProperty="remark"/>
        </html:select>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.receivertype"/>
        <html:errors property="receivertype"/>
     <eoms:comboBox name="receivertype" id="receivertype"  sub="revecer" initDicId="1020201" ds="/message/tawCommonMessageSubscribes.do?method=xsearch&type="/>
      <!--  <html:select property="receivertype" onchange="revecerType(this.value)">
         <html:option value="0">USER</html:option> 
         <html:option value="1">ROLE</html:option> 
         <html:option value="2">DEPT</html:option> 
        </html:select>-->
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.revecer"/>
        <html:errors property="revecer"/>
       <eoms:comboBox name="revecer" id="revecer" styleClass="border"/>
      <!--  <html:hidden property="revecer" styleId="revecer" styleClass="text medium"/>
       <input type="text" name="revecername" id="revecername" class="text medium"/>
       <input type="button" value="<fmt:message key="label.selectTree"/>" onclick="$('checkboxtree').toggle();"/>-->
    </li>
   
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.remarktwo"/>
        <html:errors property="remarktwo"/>
        <html:text property="remarktwo" styleId="remarktwo" styleClass="text medium"/>

    </li>

   

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.startday"/>
        <html:errors property="startday"/>
        <html:text property="startday" styleId="startday" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.starthour"/>
        <html:errors property="starthour"/>
        <html:text property="starthour" styleId="starthour" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.startmin"/>
        <html:errors property="startmin"/>
        <html:text property="startmin" styleId="startmin" styleClass="text medium"/>

    </li>

   <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.endday"/>
        <html:errors property="endday"/>
        <html:text property="endday" styleId="endday" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.endhour"/>
        <html:errors property="endhour"/>
        <html:text property="endhour" styleId="endhour" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.endmin"/>
        <html:errors property="endmin"/>
        <html:text property="endmin" styleId="endmin" styleClass="text medium"/>

    </li>


 <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.sendcount"/>
        <html:errors property="sendcount"/>
        <html:text property="sendcount" styleId="sendcount" styleClass="text medium"/>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.sendcuttime"/>
        <html:errors property="sendcuttime"/>
        <html:text property="sendcuttime" styleId="sendcuttime" styleClass="text medium"/>

    </li>
     <li>
        <eoms:label styleClass="desc" key="tawCommonMessageSubscribeForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageSubscribe')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageSubscribeForm"));
    function revecerType(mestype){
       
    }
</script>

<%@ include file="/common/footer_eoms.jsp"%>