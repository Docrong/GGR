<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<script language="javascript">
    var userTreeAction = '${app}/xtree.do?method=dept';
    var treeAction = '${app}/xtree.do?method=userByDept';

    function deptCallBack(jsonData, data) {
        dispatcherTree.resetRoot(treeAction + "&node=" + data);
    }

    Ext.onReady(function () {
        userTree = new xbox({
            btnId: 'userTreeBtn',
            dlgId: 'dlg-dept',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所属部门")}',
            treeChkMode: 'single',
            treeChkType: 'user',
            showChkFldId: 'tmpDispatchDept',
            saveChkFldId: 'tmpDispatchDeptId',
            callback: deptCallBack
        });
        dispatcherTree = new xbox({
            btnId: 'dispatcherTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: treeAction,
            treeRootId: '-2',
            treeRootText: '${eoms:a2u("人员列表")}',
            treeChkMode: 'single',
            treeChkType: 'user',
            showChkFldId: 'tmpDispatcher',
            saveChkFldId: 'tmpDispatcherId'
        });
    })
</script>
<!-- <form name="tawRmDispatchRecordForm" method="post"
action="${app}/dispatchrecord/tawRmDispatchRecord.do?method=searchList" styleId="tawRmDispatchRecordForm">-->
<html:form action="tawRmDispatchRecord" method="post" styleId="tawRmDispatchRecordForm">

    <table class="formTable">
        <caption>
            <fmt:message key="tawRmDispatchRecordDetail.heading"/>
        </caption>
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmDispatchRecordForm.fileName"/>
            </td>
            <td width="500" colspan="2">
                <input type="text" name="tmpFileName" size="30" value="" class="text">
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmDispatchRecordForm.fileSource"/>
            </td>
            <td width="500" colspan="2">
                <input type="text" name="tmpFileSource" size="30" value="" class="text">
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmDispatchRecordForm.fileProperty"/>
            </td>
            <td width="500" colspan="2">
                <eoms:dict key="dict-plancontent" dictId="fileProperty" beanId="selectXML"
                           isQuery="false" defaultId="${tawRmDispatchRecordForm.fileProperty}"
                           selectId="tmpFileProperty" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmDispatchRecordForm.time"/>
            </td>
            <td width="500" colspan="2">
                <input type="text" name="tmpTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);"
                       readonly="true">
            </td>
        </tr>

        <tr>
            <html:hidden property="tmpDispatchDeptId" styleId="tmpDispatchDeptId" styleClass="text medium"/>

            <td width="100" class="label">
                <eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatchDept"/>
            </td>
            <td width="500" colspan="2">
                <html:text property="tmpDispatchDept" styleId="tmpDispatchDept"
                           styleClass="text medium" readonly="true"/>

                <input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn" class="btn"/>
            </td>
        </tr>

        <tr>
            <html:hidden property="tmpDispatcherId" styleId="tmpDispatcherId" styleClass="text medium"/>
            <td width="100" class="label">
                <eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatcher"/>
            </td>
            <td width="500" colspan="2">
                <html:text property="tmpDispatcher" styleId="tmpDispatcher"
                           styleClass="text medium" readonly="true"/>
                <input type="button" value="${eoms:a2u('人员列表')}" id="dispatcherTreeBtn" class="btn"/>
                <font style="font-size:13px;color:#CC0000;"><strong>${eoms:a2u('请先选择发文单位再选择发文人')}</strong></font>
            </td>
        </tr>

        <!-- <tr>
			<input type="hidden" name="tmpDispatchDeptId"/>
			<td width="100" class="label">
				<fmt:message key="tawRmDispatchRecordForm.dispatchDept" />
			</td> 
			<td width="500" colspan="2">
				<input type="text" name="tmpDispatchDept" size="30" value="" readonly="true" class="text">
				<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn" class="btn" />
			</td>
		</tr>
			
		<tr>
			<input type="hidden" name="tmpDispatcherId"/>
			<td width="100" class="label">
				<fmt:message key="tawRmDispatchRecordForm.dispatcher" />
			</td> 
			<td width="500" colspan="2">
				<input type="text" name="tmpDispatcher" size="30" readonly="true" value="" class="text">
				<input type="button" value="${eoms:a2u('人员列表')}" id="dispatcherTreeBtn" class="btn" />	
			</td>
		</tr>-->
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmPlanContentForm.roomId"/>
            </td>
            <td width="500" colspan="3">
                <html:select property="roomId">
                    <html:option value=""><fmt:message key="tawRmPlanContentForm.select"/></html:option>
                    <html:options collection="roomList" property="id" labelProperty="roomname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmPlanContentForm.startTime"/>
            </td>
            <td width="500" colspan="3">
                <input type="text" name="startTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);"
                       readonly="true">
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <fmt:message key="tawRmPlanContentForm.endTime"/>
            </td>
            <td width="500" colspan="3">
                <input type="text" name="endTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);"
                       readonly="true">
            </td>
        </tr>
    </table>
    <br>
    <!--<input type="submit" value=" ${eoms:a2u("查询")}" name="B1"
    class="submit"> -->

    <!-- </form>-->

    <html:submit styleClass="button" property="method.searchList" onclick="bCancel=false">
        <fmt:message key="button.query"/>
    </html:submit>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
