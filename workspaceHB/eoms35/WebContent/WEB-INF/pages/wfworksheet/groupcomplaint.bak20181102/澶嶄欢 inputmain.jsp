<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "305");

    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("=====taskName==" + taskName);

%>

<script type="text/javascript">


</script>

<c:if test="${empty sheetMain.piid}">
    <script type="text/javascript">
        //if($("${sheetPageName}dealTime1").value==""){
        //var dt = new Date();
        //alert(dt);
        //$("${sheetPageName}dealTime1").value=dt.format('Y-m-d H:i:s');
        //}

        //if($("${sheetPageName}dealTime2").value==""){
        //var dt = new Date();
        //alert(dt);
        //$("${sheetPageName}dealTime2").value=dt.format('Y-m-d H:i:s');
        //}
    </script>
</c:if>


<%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/baseinputmainhtmlnew.jsp" %>

<input type="hidden" name="${sheetPageName}processTemplateName" value="GroupComplaintProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask"/>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iGroupComplaintMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink"/> <!--link表Model对象类名-->

<!-- templateName rule -->


<!-- sheet info -->
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="0"/>


<br/>


<table id="sheet" class="formTable">
    <c:if test="${status!=1}">

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.urgentDegree"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" initDicId="1010102"
                               defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:ture"/>
            </td>
        </tr>

        <!-- <tr>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime1"/></td>
        <td class="content">
        <input type="text" class="text" name="${sheetPageName}dealTime1" readonly="readonly"
        id="${sheetPageName}dealTime1" value="${eoms:date2String(sheetMain.dealTime1)}"
        onclick="popUpCalendar(this, this)"/>

        </td>
        <td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime2"/></td>
        <td class="content">
        <input type="text" class="text" name="${sheetPageName}dealTime2" readonly="readonly"
        id="${sheetPageName}dealTime2" value="${eoms:date2String(sheetMain.dealTime2)}"
        onclick="popUpCalendar(this, this)"/>
        </td>
        </tr>-->

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.acceptLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"
                />

            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.completeLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"
                />
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT1"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT1" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT1"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}mainCompleteLimitT2',vtext:'${eoms:a2u("T1时限不能晚于T2时限")}',allowBlank:false"
                />

            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT2"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT2" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT2"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}mainCompleteLimitT1',vtext:'${eoms:a2u("T2时限不能早于T1时限")}',allowBlank:false"
                />
            </td>
        </tr>

    </c:if>

</table>

<br/>

<table id="sheet" class="formTable">
    <c:if test="${status!=1}">

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.btype1"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}btype1" id="${sheetPageName}btype1" initDicId="1011002"
                               defaultValue="${sheetMain.btype1}" alt="allowBlank:ture"/>
            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.bservType"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}bservType" id="${sheetPageName}bservType" initDicId="1011001"
                               defaultValue="${sheetMain.bservType}" alt="allowBlank:ture"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContact"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact"
                       value="${sheetMain.bdeptContact}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('业务部门联系人，最多输入20字符')}'"/>
            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContactPhone"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}bdeptContactPhone"
                       id="${sheetPageName}bdeptContactPhone" value="${sheetMain.bdeptContactPhone}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('业务部门联系人电话，最多输入20字符')}'"/>

            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.faultTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}faultTime" readonly="readonly"
                       id="${sheetPageName}faultTime" value="${eoms:date2String(sheetMain.faultTime)}"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1,-1)"
                       alt="vtype:'lessThen',link:'${sheetPageName}complaintTime',vtext:'${eoms:a2u("故障时间不能晚于投诉时间")}',allowBlank:false"
                />
            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}complaintTime" readonly="readonly"
                       id="${sheetPageName}complaintTime" value="${eoms:date2String(sheetMain.complaintTime)}"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1,-1)"
                       alt="vtype:'moreThen',link:'${sheetPageName}faultTime',vtext:'${eoms:a2u("投诉时间不能早于故障时间")}',allowBlank:false"
                />

            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintDesc"/></td>
            <td colspan="3">
                <textarea name="complaintDesc" id="complaintDesc" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('投诉内容，最多输入1000汉字')}'">${sheetMain.complaintDesc}</textarea>
            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.customerName"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customerName" id="${sheetPageName}customerName"
                       value="${sheetMain.customerName}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('客户姓名，最多输入20字符')}'"/>

            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.customPhone"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customPhone" id="${sheetPageName}customPhone"
                       value="${sheetMain.customPhone}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('客户电话，最多输入20字符')}'"/>

            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintNum"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}complaintNum" id="${sheetPageName}complaintNum"
                       value="${sheetMain.complaintNum}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('投诉号码，最多输入20字符')}'"/>
            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintLoca"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}complaintLoca" id="${sheetPageName}complaintLoca"
                       value="${sheetMain.complaintLoca}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('投诉位置，最多输入20字符')}'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.affectedAreas"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}affectedAreas" id="${sheetPageName}affectedAreas"
                               initDicId="1011003" defaultValue="${sheetMain.affectedAreas}" alt="allowBlank:ture"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.enterpriseCode"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}enterpriseCode"
                       id="${sheetPageName}enterpriseCode" value="${sheetMain.enterpriseCode}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('企业代码，最多输入20字符')}'"/>

            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.serverCode"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode"
                       value="${sheetMain.serverCode}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('服务代码，最多输入20字符')}'"/>

            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.apnName"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}apnName" id="${sheetPageName}apnName"
                       value="${sheetMain.apnName}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('APN名称，最多输入20字符')}'"/>

            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.circuitCode"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode"
                       value="${sheetMain.circuitCode}"
                       alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('传输专线电路代号，最多输入20字符')}'"/>

            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.ecsiType"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}ecsiType" id="${sheetPageName}ecsiType" initDicId="1011004"
                               defaultValue="${sheetMain.ecsiType}" alt="allowBlank:ture"/>
            </td>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.connectType"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}connectType" id="${sheetPageName}connectType" initDicId="1011005"
                               defaultValue="${sheetMain.connectType}" alt="allowBlank:ture"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="group" key="groupcomplaint.termType"/></td>
            <td colspan="3">
                <textarea name="termType" id="termType" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('终端描述，最多输入1000汉字')}'">${sheetMain.termType}</textarea>
            </td>
        </tr>


    </c:if>
</table>


<br/>
<table id="sheet" class="formTable">
    <c:if test="${status!=1}">


    </c:if>
</table>

<br/>

<%-- accessories --%>


<table id="sheet" class="formTable">
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">
            <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="groupcomplaint" />-->
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="groupcomplaint"/>
        </td>
    </tr>


</table>


<br/>

<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="group" key="role.FirstExcute"/>
        </legend>


        <eoms:chooser id="test" type="role" roleId="306" flowId="057" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',allowBlank:true,vtext:'${eoms:a2u('请选择派发对象')}'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>
</c:if>
		