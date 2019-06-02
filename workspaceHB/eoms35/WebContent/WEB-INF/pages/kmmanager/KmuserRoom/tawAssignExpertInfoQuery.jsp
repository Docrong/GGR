<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*;"%>
<fmt:bundle basename="com/boco/eoms/duty/config/ApplicationResources-duty">
	<!--根据给定的实例名生成标题 -->
	<title>专家值班查询</title>
	<html:form action="TawExpertInfo.do?method=xinfoquery" method="post"
		styleId="tawRmAssignExpertForm">

		<table class="formTable middle" align="center">
			<tr>
				<td colspan="6" align="center">
					<h2>
						专家列表查询
					</h2>
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					专家姓名
				</td>
				<td colspan="2">
					<html:text property="expertName" styleId="expertName"
						styleClass="text medium" />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					工作地点
				</td>
				<td colspan="2">
					<html:text property="adress" styleId="adress"
						styleClass="text medium" />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					专家特长
				</td>
				<td colspan="2">
					<html:text property="beGoodAt" styleId="beGoodAt"
						styleClass="text medium" />

				</td>
				<td colspan="3" align="center">
					<html:submit styleClass="button" property="method.query">
						查询
					</html:submit>
			</tr>

		</table>
	</html:form>
	<logic:present name="tawExpertInfoFormList">
		<display:table name="tawExpertInfoFormList" cellspacing="0"
			cellpadding="0" id="tawExpertInfoFormList" pagesize="12"
			class="table tawExpertInfoFormList"
			requestURI="${app}/duty/TawRmAssignExpert.do?method=xinfoquery"
			sort="external" size="${resultSize}">

			<display:column headerClass="sortable" titleKey="expert.name" property="expertName"/>
			
			<display:column property="adress" headerClass="sortable"
				titleKey="TawExpertInfo.address"  />
			
			<display:column property="telephone" headerClass="sortable"
				titleKey="TawExpertInfo.telephone"  />
			<display:column property="mobile" headerClass="sortable"
				titleKey="TawExpertInfo.mobile"  />	
				
				<display:column headerClass="sortable"
							titleKey="TawExpertInfo.search">
							<html:link
								href="${app}/duty/TawExpertInfo.do?method=searchfrominfo"
								paramId="expertId" paramProperty="expertId" paramName="tawExpertInfoFormList">
								查看
							</html:link>
						</display:column>
				<c:choose>
					<c:when
						test="${tawExpertInfoFormList.creater==sessionform.userid}">
				
			<display:column headerClass="sortable"
							titleKey="TawExpertInfo.edit">
							<html:link
								href="${app}/duty/TawExpertInfo.do?method=toExpertInfo"
								paramId="expertId" paramProperty="expertId" paramName="tawExpertInfoFormList">
								编辑
							</html:link>
						</display:column>
						<display:column headerClass="sortable"
							titleKey="TawExpertInfo.delete">
							<html:link
								href="${app}/duty/TawExpertInfo.do?method=oneDelete" onclick="javascript:return ConfirmDel();"
								paramId="expertId" paramProperty="expertId" paramName="tawExpertInfoFormList">
								删除
							</html:link>
						</display:column>	
						</c:when>
						<c:otherwise>
						<display:column headerClass="sortable"
							titleKey="TawExpertInfo.edit">
								编辑
						</display:column>
						<display:column headerClass="sortable"
							titleKey="TawExpertInfo.delete">
							
								删除
						</display:column>	
						</c:otherwise>
						</c:choose>
			
								
		</display:table>
	</logic:present>
	<script type="text/javascript">
	function notdeal(){
	alert("您不是创建者或者已经发布，不能进行修改！");
	}
	function ConfirmDel(){
		var msg = "确定删除该记录？";
		if (confirm(msg)==true){
			return true;
		}else{
			return false;
		}
    }
	</script>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
