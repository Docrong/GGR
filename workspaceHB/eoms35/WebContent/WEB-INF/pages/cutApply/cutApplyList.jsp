<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript">
function ConfirmDel(){
		var msg = "确认删除该记录?";
		if (confirm(msg)==true){
			return true;
		}else{
			return false;
		}
    };

function showDetail(link){
		var par = "height=550,width=450,status=yes,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
		var detail = window.open(link,'',par);
		detail.focus();
};
function newPage(){
		 document.forms[0].action="cutApplys.do?method=add";
      	 document.forms[0].submit(); 
	} 
</script>
<form action="cutApplys.do?method=add" id="fr" name="fr" method="post">
	<fmt:bundle basename="config/applicationResource-cutapply">

		<content tag="heading">
		<center>
			<H2>
				干线割接管理列表
			</H2>
		</center>
		</content>
		<display:table name="cutApplyList" cellspacing="0" cellpadding="0"
			id="cutApplyList" class="table cutApplyList" pagesize="12"
			sort="list" requestURI="${app}/cutapply/cutApplys.do?method=search"
			size="resultSize">

			<logic:present name="cutApplyList" property="id">
				<display:column title="割接申请人">
					<eoms:id2nameDB id="${cutApplyList.userId}"
						beanId="tawSystemUserDao" />
				</display:column>
				<display:column title="割接申请人部门">
					<eoms:id2nameDB id="${cutApplyList.deptId}"
						beanId="tawSystemDeptDao" />
				</display:column>
				<display:column title="割接所属地州">
					<eoms:id2nameDB id="${cutApplyList.areaId}"
						beanId="tawSystemDictTypeDao" />
				</display:column>
				<display:column title="割接开始时间" property="cutStartTime">
				</display:column>
				<display:column title="割接结束时间" property="cutEndTime">
				</display:column>
				<display:column title="是否影响业务">
					<eoms:id2nameDB id="${cutApplyList.isAffect}"
						beanId="tawSystemDictTypeDao" />
				</display:column>
				<display:column title="查看">
					<html:link href="${app}/cutapply/cutApplys.do?method=xget"
						paramId="id" paramProperty="id" paramName="cutApplyList"
						onclick="showDetail(this);return false;">
						<bean:message key="button.view" />
					</html:link>
				</display:column>
				<c:choose>
					<c:when test="${cutApplyList.isEdit=='1'}">
						<display:column headerClass="sortable" paramId="id"
							paramProperty="id" title="编辑">
							<html:link href="${app}/cutapply/cutApplys.do?method=edit"
								paramId="id" paramProperty="id" paramName="cutApplyList">
								<bean:message key="button.edit" />
							</html:link>
						</display:column>
						<display:column headerClass="sortable" title="删除">
							<html:link href="${app}/cutapply/cutApplys.do?method=remove"
								paramId="id" paramProperty="id" paramName="cutApplyList"
								onclick="javascript:return ConfirmDel();">
								<bean:message key="button.delete" />
							</html:link>
						</display:column>
					</c:when>
					<c:otherwise>
						<display:column headerClass="sortable" paramId="id"
							paramProperty="id" title="编辑">
							<bean:message key="button.edit" />
						</display:column>
						<display:column headerClass="sortable" title="删除">
							<bean:message key="button.delete" />
						</display:column>
					</c:otherwise>
				</c:choose>

			</logic:present>
		</display:table>
		<br />
		<table width="80%">
			<tr>
				<td>
					<a
						href="<%=request.getContextPath()%>/cutapply/cutApplys.do?method=add">新增
					</a>
					&nbsp;&nbsp;&nbsp;
					<a
						href="<%=request.getContextPath()%>/cutapply/cutApplys.do?method=exportXml">导出到Excel
					</a>
				</td>
			</tr>
		</table>
	</fmt:bundle>
</form>

<%@ include file="/common/footer_eoms.jsp"%>
