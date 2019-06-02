<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
var frmReg;
Ext.onReady(function(){
	 frmReg = new eoms.form.Validation({form:'newFormPage'});
});

var frmReg1;
Ext.onReady(function(){
	 frmReg1 = new eoms.form.Validation({form:'newFormPage1'});
});
function returnlist(){
	var sheetKey = window.parent.document.getElementById("sheetKey").value;
	var frame1 = window.parent.document.getElementById("frameid");
	frame1.style.display="block";
	frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey="+sheetKey+"&actionForword=commondid"; 
}

</script>
	<logic:notEmpty name="TawBureaudataHlr">
		HLRID
		<form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=showQuicQuery">
			<table class="listTable taskList">
			<input type="hidden"   name="actionForword" id="actionForword"   value="commondid"/>
			<input type="hidden"   name="modelname" id="modelname"   value="TawBureaudataHlr"/>
			<tr>
				<td class="label">HLRID</td>
				<td >	
					<input type="text"   name="hlrid" id="hlrid"   value=""/>
				</td>
				<td class="label">HLR名称</td>
				<td >
					<input type="text"   name="hlrname" id="hlrname"   value=""/>
				</td>
				<td class="label">归属地市区号</td>
				<td >
					<input type="text"   name="belongcityid" id="belongcityid"   value=""/>
				</td>
				<td>
					<input type="submit"   class="submit"  value="查询"/>
				</td>
				<td>
					<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
				</td>
			</tr>
			</table>
		</form>
		<display:table name="TawBureaudataHlr" cellspacing="0" cellpadding="0"
		id="TawBureaudataHlr" pagesize="${pageSize}" class="listTable taskList"
		export="false"  sort="list" size="TawBureaudataHlrsize" partialList="true" requestURI="numberapply.do">
			<display:column headerClass="sortable" title="" >
				<input type="radio" name="radioid" id="radioid"  value="${TawBureaudataHlr.id }"  />
				<input type="hidden" name="radioidValue" id="radioidValue"  value="${TawBureaudataHlr.hlrid }"  />
			</display:column>
			<display:column property="hlrid" sortable="true" headerClass="sortable" title="HLRID" />
			<display:column property="hlrname" sortable="true" headerClass="sortable" title="HLR名称" />
			<display:column property="belongcityid" sortable="true" headerClass="sortable" title="归属地市区号" />
			<display:column property="hlrsignalid" sortable="true" headerClass="sortable" title="24位信令点" />
		</display:table>
	</logic:notEmpty>
	<logic:notEmpty name="TawpartMscid">
		MSCID
		<form name="newFormPage1" method="POST" id="newFormPage1" action="numberapply.do?method=showQuicQuery">
			<table class="listTable taskList">
			<input type="hidden"   name="actionForword" id="actionForword"   value="commondid"/>
			<input type="hidden"   name="modelname" id="modelname"   value="TawpartMscid"/>
			<tr>
				<td class="label">MSCID</td>
				<td >	
					<input type="text"   name="numberFree" id="numberFree"   value=""/>
				</td>
				<td class="label">分配地市名称</td>
				<td >
					<input type="text"   name="areaName" id="areaName"   value=""/>
				</td>
				<td class="label">创建人</td>
				<td >
					<input type="text"   name="creator" id="creator"   value=""/>
				</td>
				<td class="label">分配人</td>
				<td >
					<input type="text"   name="assigner" id="assigner"   value=""/>
				</td>
				<td>
					<input type="submit"   class="submit"  value="查询"/>
				</td>
				<td>
					<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
				</td>
			</tr>
			</table>
		</form>
		<display:table name="TawpartMscid" cellspacing="0" cellpadding="0"
		id="TawpartMscid" pagesize="${pageSize}" class="listTable taskList"
		export="false"  sort="list" size="TawpartMscidsize" partialList="true" requestURI="numberapply.do">
			<display:column headerClass="sortable" title="" >
				<input type="radio" name="radioid" id="radioid" value="${TawpartMscid.id }"  />
				<input type="hidden" name="radioidValue" id="radioidValue" value="${TawpartMscid.numberFree }"  />
			</display:column>
			<display:column property="numberFree" sortable="true" headerClass="sortable" title="MSCID" />
			<display:column property="areaName" sortable="true" headerClass="sortable" title="分配地市名称" />
			<display:column property="creator" sortable="true" headerClass="sortable" title="创建人" />
			<display:column property="createTime" sortable="true" headerClass="sortable" title="创建时间" />
			<display:column property="assigner" sortable="true" headerClass="sortable" title="分配人" />
			<display:column property="assignTime" sortable="true" headerClass="sortable" title="分配时间" />
		</display:table>
	</logic:notEmpty>
	
	<c:if test="${TawBureaudataHlrsize==0 }">
		HLRID，没有相关记录！
		<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
	</c:if>
	<c:if test="${TawpartMscidsize==0 }">
		MSCID，没有相关记录！
		<input type="button"   class="button"  value="返回列表" onclick="returnlist()"/>
	</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
