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
		var url = "${app}/kmmanager/kmExpertExps.do?method=edit&userId=${userId}&id="+id;
		window.open(url,"eduForm","height=500,width=400,top=100,left=200,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");		
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
			alert("请选择工作经历");
		return checkflag;
	}	
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<html:form action="/kmExpertExps.do" method="post" styleId="kmExpertExpForm">

		<display:table name="kmExpertExpList" cellspacing="0" cellpadding="0"
			id="kmExpertExpList" pagesize="${pageSize}"
			class="table kmExpertExpList" export="false"
			requestURI="${app}/kmmanager/kmExpertExps.do?method=search"
			sort="list" partialList="true" size="resultSize"
			decorator="com.boco.eoms.km.expert.displaytag.support.KmExpertExpDisplaytagDecorator">

			<display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'>" style="width:3%" />
			<display:setProperty name="css.table" value="0," />

			<display:column property="expertCompany" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertCompany" />

			<display:column property="expertStart" sortable="true" format="{0,date,yyyy-MM-dd}" 
				headerClass="sortable" titleKey="kmExpertExp.expertStart" />

			<display:column property="expertEnd" sortable="true" format="{0,date,yyyy-MM-dd}" 
				headerClass="sortable" titleKey="kmExpertExp.expertEnd" />

			<display:column property="expertPosition" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertPosition" />

			<display:column property="expertAddress" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertAddress" />

			<display:column property="expertResponsiblitily" sortable="true"
				headerClass="sortable" titleKey="kmExpertExp.expertResponsiblitily" />

			<display:column titleKey="button.updated">
				<c:if test="${kmExpertExpList != null}">
					<html:link href="javascript:openwin('${kmExpertExpList.id}')">
						<img src="${app }/images/icons/edit.gif">
					</html:link>
				</c:if>
			</display:column>

			<display:setProperty name="paging.banner.item_name" value="kmExpertExp" />
			<display:setProperty name="paging.banner.items_name" value="kmExpertExps" />
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