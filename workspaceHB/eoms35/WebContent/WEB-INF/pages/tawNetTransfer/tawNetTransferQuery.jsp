<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<fmt:bundle basename="config/ApplicationResources-duty">
<!--根据给定的实例名生成标题 -->
<title>新增网调信息
</title>
	<html:form action="TawNetTransfer.do?method=xquery" method="post"
		styleId="TawNetTransferForm">

		<html:hidden property="id" />
		<table class="formTable large" align="center">
			<tr>
				<td colspan="12" align="center">
					<h2>
						网调信息查询
					</h2>
				</td>
			</tr>
			
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					专业名称
					<html:errors property="speciality" />
				</td>
				<td colspan="3">
				<eoms:dict key="dict-duty" dictId="speciality" beanId="selectXML" 
                 defaultId="" isQuery="false"  selectId="speciality"/>
				</td>
				<td class="label" nowrap="nowrap" align="right" >
					设备所属<br><center>部门</center>
				</td>
				<td colspan="3">
					<html:errors property="equipmentDept" />
						<input type="hidden" id="equipmentDept" name="equipmentDept" />
						<div id="user-list" class="viewer-list"></div>
						<input type="button" value="选择部门" id="userTreeBtn" class="btn" />
				</td>
				<td class="label" nowrap="nowrap" align="right" >
					状态
				</td>
				<td colspan="3">
					<html:text property="state" styleId="state"
						styleClass="text medium" />
				</td>
				</tr>
				<tr>
				<td class="label" nowrap="nowrap" align="right">
					开始时间
				</td>
				<td colspan="3">
					<html:text property="startTime" styleId="startTime"
						styleClass="text medium" readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); "/>
				</td>
				<td class="label" nowrap="nowrap" align="right">
					结束时间
				</td>
				<td colspan="3">
					<html:text property="endTime" styleId="endTime"
						styleClass="text medium"  readonly="true"
					onclick="popUpCalendar(this, this,null,null,null,true,-1); "/>
				</td>
				<td colspan="4" align="center">
				<html:submit styleClass="button" property="method.query">
						查询
					</html:submit>
				</tr>
		</table>
	</html:form>
	<logic:present name="tawNetTransferlist" >
	<display:table name="tawNetTransferlist" cellspacing="0"
			cellpadding="0" id="tawNetTransferlist" pagesize="12"
			class="table tawNetTransferlist"
			requestURI="${app}/duty/TawNetTransfer.do?method=xquery"
			sort="external" size="resultSize">
			<logic:present name="tawNetTransferlist" property="id">
			<display:column property="dispatchNum" headerClass="sortable"
					titleKey="TawNetTransfer.dispatchNum" />
			<display:column property="title" headerClass="sortable"
					titleKey="TawNetTransfer.title" />
			<display:column property="date" headerClass="sortable"
					titleKey="TawNetTransfer.startTime" format = "{0,date,yyyy-MM-dd HH:mm:ss}"/>	
			<display:column  headerClass="sortable"
					titleKey="TawNetTransfer.speciality" >
					<eoms:dict key="dict-duty" dictId="speciality" itemId="${tawNetTransferlist.speciality }" beanId="id2nameXML" />
					</display:column>		
			<display:column  headerClass="sortable"
					titleKey="TawNetTransfer.state" >
					<eoms:dict key="dict-duty" dictId="netState" itemId="${tawNetTransferlist.state }" beanId="id2nameXML" />
					</display:column>	
	<c:choose>					
	<c:when
		test="${tawNetTransferlist.originater==sessionform.userid&&tawNetTransferlist.hasub=='0'}">				
			<display:column headerClass="sortable"
							titleKey="TawNetTransfer.deal">
							<html:link
								href="${app}/duty/TawNetTransfer.do?method=xdeal"
								paramId="id" paramProperty="id" paramName="tawNetTransferlist">
								<img src="${app}/duty/images/an_bj.gif">
							</html:link>
						</display:column>
			</c:when>			
			<c:otherwise>
			<display:column headerClass="sortable"
							titleKey="TawNetTransfer.deal">
								<img src="${app}/duty/images/an_bj.gif" onclick="notdeal();">
						</display:column>
			</c:otherwise>
			</c:choose>		
			<logic:notEqual name="tawNetTransferlist" property="state" value="0">	
			<display:column headerClass="sortable"
							titleKey="TawNetTransfer.spread">
							<html:link
								href="${app}/duty/TawNetTransfer.do?method=xspread" 
								paramId="id" paramProperty="id" paramName="tawNetTransferlist">
								<img src="${app }/images/plus.gif">
							</html:link>
						</display:column>						
			</logic:notEqual>
			<logic:equal name="tawNetTransferlist" property="state" value="0">
			<display:column headerClass="sortable"
							titleKey="TawNetTransfer.spread">
						</display:column>						
			</logic:equal>
			</logic:present>
		</display:table>
	</logic:present>
	</fmt:bundle>
	<script type="text/javascript">
	function notdeal(){
	  alert("您不是创建者或者已经发布，不能进行修改！");
	}
	
	Ext.onReady(function() {
	  v = new eoms.form.Validation({form:'TawNetTransferForm'});
		
	  var userTreeAction='${app}/xtree.do?method=dept';
	  userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: false,		
			emptyText : '<div>选择部门</div>'								
		}
	  );
	  userViewer.refresh();
	
	  setTimeout(function(){
	    userTree = new xbox({
		  btnId:'userTreeBtn',	
		  treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'部门选择',treeChkMode:'single',treeChkType:'dept',
		  viewer:userViewer,saveChkFldId:'equipmentDept'
	    });
	  },10);
  });
  </script>
<%@ include file="/common/footer_eoms.jsp"%>
