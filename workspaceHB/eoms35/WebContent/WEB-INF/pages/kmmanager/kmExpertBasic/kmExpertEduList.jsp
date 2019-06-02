<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript">
  	var checkflag = "false";
  	
	function choose() {
		var objs = document.getElementsByName("ids");
		if(checkflag=="false"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = true;
      			checkflag="true";
			}
		}else if(checkflag=="true"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = false;
      			checkflag="false"
			}
		}
	}
	  	
	function openwin(id) {
		var url = "${app}/kmmanager/kmExpertEdus.do?userId=${userId}&method=edit&id="+id;
		window.open(url,"eduWin","height=500,width=400,top=100,left=200,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");		
	}
	
	function del(){
		return checkSelect();
	}
	
	function checkSelect(){
		var objs = document.getElementsByName("ids");
		var checkflag = false;
		for(var i=0; i<objs.length; i++) {
		    if(objs[i].type.toLowerCase() == "checkbox" && objs[i].checked == true)      			
      		    checkflag = true;
		}

		if(!checkflag)
		    alert("请选择教育背景");
		return checkflag;
	}	
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
    <html:form action="/kmExpertEdus.do" method="post" styleId="kmExpertEduForm">	

		<display:table name="kmExpertEduList" cellspacing="0" cellpadding="0"
			id="kmExpertEduList" pagesize="${pageSize}"
			class="table kmExpertEduList" export="false"
			requestURI="${app}/kmmanager/kmExpertEdus.do?method=search"
			sort="list" partialList="true" size="resultSize" 
			decorator="com.boco.eoms.km.expert.displaytag.support.KmExpertEduDisplaytagDecorator">
			
			<display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'>" style="width:3%" />
			<display:setProperty name="css.table" value="0," />

			<display:column property="expertEduSch" sortable="true"
				headerClass="sortable" titleKey="kmExpertEdu.expertEduSch" />
			
			<display:column sortable="true" headerClass="sortable"
				titleKey="kmExpertEdu.expertEduEdu">
				<eoms:id2nameDB id="${kmExpertEduList.expertEduEdu}" beanId="ItawSystemDictTypeDao" />
			</display:column>
		
			<display:column property="expertEduStart" sortable="true" format="{0,date,yyyy-MM-dd}" 
				headerClass="sortable" titleKey="kmExpertEdu.expertEduStart" />
			
			<display:column property="expertEduEnd" sortable="true" format="{0,date,yyyy-MM-dd}" 
				headerClass="sortable" titleKey="kmExpertEdu.expertEduEnd" />

			<display:column property="expertEduPro" sortable="true"
				headerClass="sortable" titleKey="kmExpertEdu.expertEduPro" />

			<display:column property="expertEduProName" sortable="true"
				headerClass="sortable" titleKey="kmExpertEdu.expertEduProName" />

			<display:column property="expertEduCity" sortable="true"
				headerClass="sortable" titleKey="kmExpertEdu.expertEduCity" />

			<display:column property="expertEduDes" sortable="true"
				headerClass="sortable" titleKey="kmExpertEdu.expertEduDes" />

			<display:column titleKey="button.updated">
				<c:if test="${kmExpertEduList != null}">
					<html:link href="javascript:openwin('${kmExpertEduList.id}')">
					    <img src="${app }/images/icons/edit.gif">
					</html:link>
				</c:if>
			</display:column>

			<display:setProperty name="paging.banner.item_name" value="kmExpertEdu" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertEdus" />
		</display:table>		
 
		<table>
		    <tr>
		        <td>
		            <html:button styleClass="btn" property="method.added" onclick="openwin('')">
		                <fmt:message key="button.add" />
		            </html:button>		            
		            <html:submit styleClass="btn" property="method.remove" onclick="return del()">
		                <fmt:message key="button.delete" />
		            </html:submit>
		        </td>
		    </tr>
		</table>
		
		 <html:hidden property="userId" />

	</html:form>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>