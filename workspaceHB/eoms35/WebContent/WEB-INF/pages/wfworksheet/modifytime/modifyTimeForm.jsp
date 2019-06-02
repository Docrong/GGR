<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><bean:message bundle="modifytimeForm" key="modifytimeForm.title" /></title>

<script type="text/javascript">
  var v;
   Ext.onReady(function(){   
     v = new eoms.form.Validation({form:'modifyTimeForm'});
   });
 </script>
<!-- <content tag="heading"><fmt:message key="modifytimeDetail.heading"/></content> -->
<%
String isAdd=request.getParameter("isAdd");
if(isAdd!=null&!"".equals(isAdd)){
   request.setAttribute("isAdd",isAdd);
}
String isEdit=request.getParameter("isEdit");
if(isEdit!=null&!"".equals(isEdit)){
   request.setAttribute("isEdit",isEdit);
}
String isDelete=request.getParameter("isDelete");
if(isDelete!=null&!"".equals(isDelete)){
   request.setAttribute("isDelete",isDelete);
}
%>
<script type="text/javascript">
   function confirmDelete(){
   //alert(11111);
   modifyTimeForm.action="modifyTime.do?method=xdelete";
   modifyTimeForm.submit();
   }
   function goList(){
   //alert(11111);
   modifyTimeForm.action="modifyTime.do?method=xquery&isAdd=${isAdd}&isEdit=${isEdit}&isDelete=${isDelete}";
   modifyTimeForm.submit();
   }
</script>
<!--对表单的自动生成的处�?-->
<html:form action="modifyTime.do?method=xsave" method="post"  styleId="modifyTimeForm"> 
<table class="listTable">

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
	       <input type="hidden" name="isAdd" value="${isAdd}">
	       <input type="hidden" name="isEdit" value="${isEdit}">
	       <input type="hidden" name="isDelete" value="${isDelete}">
	 <tr>
      <td class="label">
         <bean:message bundle="modifytimeForm" key="modifytimeForm.beginTime" />*
         </td>
     <td class="content">
        <html:errors property="beginTime"/> 
          <input type="text" class="text" name="beginTime" readonly="readonly" 
					id="beginTime" value="${modifyTimeForm.beginTime}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>	    
       </td>
        <td class="label">
         <bean:message bundle="modifytimeForm" key="modifytimeForm.endTime" />*
        </td>
     <td class="content">
        <html:errors property="endTime"/>
	 <input type="text" class="text" name="endTime" readonly="readonly" 
					id="endTime" value="${modifyTimeForm.endTime}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
	  </td>
   </tr>
		 <tr>
     <td class="label">
        <bean:message bundle="modifytimeForm" key="modifytimeForm.functionary" />*
         </td>
     <td class="content">
         <html:errors property="functionary"/>
	     <html:text property="functionary" styleId="functionary" styleClass="text medium" alt="allowBlank:false"/>
		    
       </td>
        <td class="label">
         <bean:message bundle="modifytimeForm" key="modifytimeForm.local" />*
        </td>
     <td class="content">
          <html:errors property="local"/>
              <eoms:comboBox name="local" id="local" defaultValue="${modifyTimeForm.local}" initDicId="1010504" 
	  	      size="1" alt="allowBlank:false" styleClass="select-class"/>      
       </td>
   </tr>
 <tr>
     <td class="label">
        <bean:message bundle="modifytimeForm" key="modifytimeForm.metNet" />
         </td>
     <td class="content">
         <html:errors property="metNet"/>
		 <html:text property="metNet" styleId="metNet" styleClass="text medium" alt="allowBlank:true"/>
       </td>
   	  <td class="label"><bean:message bundle="modifytimeForm" key="modifytimeForm.specialtyOne" />*</td>
		  <td>
			  	 <eoms:comboBox name="specialtyOne" id="specialtyOne" 
			  	      sub="specialtyTwo" initDicId="1010104" defaultValue="${modifyTimeForm.specialtyOne}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
	      </td>	
   </tr>
      <tr>
					
			  <td class="label"><bean:message bundle="modifytimeForm" key="modifytimeForm.specialtyTwo" />*</td>
		      <td>
			    <eoms:comboBox name="specialtyTwo" id="specialtyTwo" 
			  	      sub="specialtyThree" initDicId="${modifyTimeForm.specialtyOne}" defaultValue="${modifyTimeForm.specialtyTwo}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>	  

			  <td class="label"><bean:message bundle="modifytimeForm" key="modifytimeForm.specialtyThree" />*</td>
		    <td>
			  	 <eoms:comboBox name="specialtyThree" id="specialtyThree" initDicId="${modifyTimeForm.specialtyTwo}" defaultValue="${modifyTimeForm.specialtyThree}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>					
	  
	</tr>
		 <tr>
     <td class="label">
        <bean:message bundle="modifytimeForm" key="modifytimeForm.title" />*
         </td>
     <td class="content">
        <html:errors property="title"/>
		<html:text property="title" styleId="title" styleClass="text medium" alt="allowBlank:false"/>
       </td>
        <td class="label">
        <bean:message bundle="modifytimeForm" key="modifytimeForm.type" />*
        </td>
     <td class="content">
         <html:errors property="type"/>
		 <eoms:comboBox name="type" id="type" defaultValue="${modifyTimeForm.type}" initDicId="1010504" 
	  	      size="1" alt="allowBlank:false" styleClass="select-class"/> 
       </td>
   </tr>

		    <!--li>
		     <bean:message bundle="modifytimeForm" key="modifytimeForm.remarks" />
		        <html:errors property="remarks"/>
			        <html:text property="remarks" styleId="remarks" styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入提示信�?')}'"/>
		    </li>
		    <li>
		        <bean:message bundle="modifytimeForm" key="modifytimeForm.introduction" />
		        <html:errors property="introduction"/>
			        <html:text property="introduction" styleId="introduction" styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入提示信�?')}'"/>
		    </li>
		    <li>
		    <bean:message bundle="modifytimeForm" key="modifytimeForm.deleted" />
		        <html:errors property="deleted"/>
			        <html:text property="deleted" styleId="deleted" styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入提示信�?')}'"/>
		    </li>
		    -->  
</table>
  <!--自动生成的Javascript脚本-->
    <tr class="buttonBar bottom">       
           <c:if test="${(operateType==1&&isEdit==1)||(operateType!=1&&isAdd==1)}">
	     <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
        </c:if>
         <c:if test="${operateType==1&&isDelete==1}">
	       <html:button styleClass="button" property="method.delete" onclick="confirmDelete()">
            <fmt:message key="button.delete"/>
           </html:button>
        </c:if>
           <html:button styleClass="button" property="method.back" onclick="goList()">
            <fmt:message key="button.back"/>
           </html:button>
       
    </tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>