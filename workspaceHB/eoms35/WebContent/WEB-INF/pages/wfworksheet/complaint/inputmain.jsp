<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "213");

    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
    System.out.println("=====taskName==" + taskName);

%>

<script type="text/javascript">
    function selectLimit(obj) {
        if ($("${sheetPageName}complaintType1").value == null || $("${sheetPageName}complaintType1").value == "") {
            // alert("请选择故障专业！");
            return false;
        }

        var temp1 = $("${sheetPageName}complaintType1").value;
        var temp2 = $("${sheetPageName}complaintType2").value;
        var temp3 = $("${sheetPageName}complaintType").value;

        Ext.Ajax.request({
            method: "get",
            url: "complaint.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&flowName=ComplaintProcess",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    //$("${sheetPageName}sheetAcceptLimit").value = "";
                    //$('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =
                    <%=localTimes%>
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }

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


<%@ include file="/WEB-INF/pages/wfworksheet/complaint/baseinputmainhtmlnew.jsp" %>

<input type="hidden" name="${sheetPageName}processTemplateName" value="ComplaintProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ComplaintProcess"/>
<input type="hidden" name="${sheetPageName}ifAgent" value="1"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask"/>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iComplaintMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.complaint.model.ComplaintMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.complaint.model.ComplaintLink"/> <!--link表Model对象类名-->
<!-- templateName rule -->


<!-- sheet info -->
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>

<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="0"/>


<br/>


<table id="sheet" class="formTable">
    <c:if test="${status!=1}">

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.urgentDegree"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" initDicId="1010606"
                               defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:ture"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.acceptLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
                />

            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.completeLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
                />
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.mainCompleteLimitT1"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT1" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT1"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}mainCompleteLimitT2',vtext:'T1时限不能晚于T2时限',allowBlank:false"
                />

            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.mainCompleteLimitT2"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT2" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT2"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}mainCompleteLimitT1',vtext:'T2时限不能早于T1时限',allowBlank:false"
                />
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType1"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType1" id="${sheetPageName}complaintType1"
                               sub="${sheetPageName}complaintType2" initDicId="1010601"
                               defaultValue="${sheetMain.complaintType1}" alt="allowBlank:ture"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType2"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType2" id="${sheetPageName}complaintType2"
                               sub="${sheetPageName}complaintType" initDicId="${sheetMain.complaintType1}"
                               defaultValue="${sheetMain.complaintType2}" alt="allowBlank:ture"
                               onchange="selectLimit(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType" id="${sheetPageName}complaintType"
                               sub="${sheetPageName}complaintType4" initDicId="${sheetMain.complaintType2}"
                               defaultValue="${sheetMain.complaintType}" alt="allowBlank:true"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType4"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType4" id="${sheetPageName}complaintType4"
                               sub="${sheetPageName}complaintType5" initDicId="${sheetMain.complaintType}"
                               defaultValue="${sheetMain.complaintType4}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType5"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType5" id="${sheetPageName}complaintType5"
                               sub="${sheetPageName}complaintType6" initDicId="${sheetMain.complaintType4}"
                               defaultValue="${sheetMain.complaintType5}" alt="allowBlank:ture"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType6"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}complaintType6" id="${sheetPageName}complaintType6"
                               sub="${sheetPageName}complaintType7" initDicId="${sheetMain.complaintType5}"
                               defaultValue="${sheetMain.complaintType6}" alt="allowBlank:ture"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintType7"/></td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}complaintType7" id="${sheetPageName}complaintType7"
                               initDicId="${sheetMain.complaintType6}" defaultValue="${sheetMain.complaintType7}"
                               alt="allowBlank:ture"/>
            </td>
        </tr>
    </c:if>

</table>

<br/>

<table id="sheet" class="formTable">
    <c:if test="${status!=1}">

        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.btype1"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}btype1" id="${sheetPageName}btype1"
                       value="${sheetMain.btype1}" alt="allowBlank:true,maxLength:25,vtext:'派发联系人，最多输入25字符'"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.bdeptContact"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact"
                       value="${sheetMain.bdeptContact}" alt="allowBlank:true,maxLength:50,vtext:'联系人电话，最多输入50字符'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.bdeptContactPhone"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}bdeptContactPhone" id="${sheetPageName}bdeptContactPhone"
                               initDicId="10308" defaultValue="${sheetMain.bdeptContactPhone}" alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.repeatComplaintTimes"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}repeatComplaintTimes"
                       id="${sheetPageName}repeatComplaintTimes" value="${sheetMain.repeatComplaintTimes}"
                       alt="allowBlank:true,maxLength:16,vtext:'请输入重复投诉次数，最多输入16字符'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.customerName"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customerName" id="${sheetPageName}customerName"
                       value="${sheetMain.customerName}" alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.customPhone"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customPhone" id="${sheetPageName}customPhone"
                       value="${sheetMain.customPhone}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <!-- <td class="label"><bean:message bundle="complaint" key="complaint.customType"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}customType" id="${sheetPageName}customType" initDicId="1010603" defaultValue="${sheetMain.customType}" alt="allowBlank:true"/>
			  </td>	 -->
            <td class="label"><bean:message bundle="complaint" key="complaint.customLevel"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customLevel" id="${sheetPageName}customLevel"
                       value="${sheetMain.customLevel}" alt="allowBlank:true,maxLength:25,vtext:'用户级别，最多输入25字符'"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.customBrand"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}customBrand" id="${sheetPageName}customBrand" initDicId="1010604"
                               defaultValue="${sheetMain.customBrand}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.startDealCity"/>*</td>
            <!--<td>
			  	<input type="text" class="text" name="${sheetPageName}startDealCity" id="${sheetPageName}startDealCity" 
			  	value="${sheetMain.startDealCity}" alt="allowBlank:false,maxLength:200,vtext:'受理地市，最多输入100汉字'"/>
			  </td>-->
            <td>
                <div id="areaview" class="hide"></div>
                <script type="text/javascript">

                    //viewer
                    var areaViewer = new Ext.JsonView("areaview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {
                            emptyText: '<div>没有选择项目</div>'
                        }
                    );
                    var data = "[{id:'${sheetMain.toDeptId}',name:'<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();

                    //area tree
                    var deptTreeAction = '${app}/xtree.do?method=areaTree';
                    deptetree = new xbox({

                        btnId: '${sheetPageName}showDept',
                        dlgId: 'dlg3',

                        treeDataUrl: deptTreeAction,
                        treeRootId: '-1',
                        treeRootText: '地市',
                        treeChkMode: 'single',
                        treeChkType: 'area',
                        showChkFldId: '${sheetPageName}showDept',
                        saveChkFldId: '${sheetPageName}toDeptId',
                        viewer: areaViewer
                    });
                </script>

                <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept"
                       id="${sheetPageName}showDept" alt="allowBlank:false,vtext:'请选择地域名称'"
                       value="<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>"/>
                <input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId"
                       value="${sheetMain.toDeptId}"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.customAttribution"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}customAttribution"
                       id="${sheetPageName}customAttribution"
                       value="${sheetMain.customAttribution}"
                       alt="allowBlank:true,maxLength:64,vtext:'用户归属地，最多输入32汉字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.faultTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}faultTime" readonly="readonly"
                       id="${sheetPageName}faultTime" value="${eoms:date2String(sheetMain.faultTime)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}complaintTime',vtext:'故障时间不能晚于投诉时间',allowBlank:false"
                />
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}complaintTime" readonly="readonly"
                       id="${sheetPageName}complaintTime" value="${eoms:date2String(sheetMain.complaintTime)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}faultTime',vtext:'投诉时间不能早于故障时间',allowBlank:false"
                />

            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintNum"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}complaintNum" id="${sheetPageName}complaintNum"
                       value="${sheetMain.complaintNum}" alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="complaint" key="complaint.faultSite"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}faultSite" id="${sheetPageName}faultSite"
                       value="${sheetMain.faultSite}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.terminalType"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}terminalType" id="${sheetPageName}terminalType" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'终端描述，最多输入1000汉字'">${sheetMain.terminalType}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.complaintDesc"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}complaintDesc" id="${sheetPageName}complaintDesc" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'投诉内容，最多输入1000汉字'">${sheetMain.complaintDesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="complaint" key="complaint.preDealResult"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}preDealResult" id="${sheetPageName}preDealResult" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'预处理情况，最多输入1000汉字'">${sheetMain.preDealResult}</textarea>
            </td>
        </tr>
        <!-- <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultArea"/></td>
        <td>
        <input type="text" class="text" name="${sheetPageName}faultArea" id="${sheetPageName}faultArea"
        value="${sheetMain.faultArea}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultRoad"/></td>
        <td>
        <input type="text" class="text" name="${sheetPageName}faultRoad" id="${sheetPageName}faultRoad"
        value="${sheetMain.faultRoad}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        </tr>

        <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultNo"/></td>
        <td colspan="3">
        <input type="text" class="text" name="${sheetPageName}faultNo" id="${sheetPageName}faultNo"
        value="${sheetMain.faultNo}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        </tr>

        <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultRoad1"/></td>
        <td>
        <input type="text" class="text" name="${sheetPageName}faultRoad1" id="${sheetPageName}faultRoad1"
        value="${sheetMain.faultRoad1}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultRoad2"/></td>
        <td>
        <input type="text" class="text" name="${sheetPageName}faultRoad2" id="${sheetPageName}faultRoad2"
        value="${sheetMain.faultRoad2}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        </tr>

        <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultVill"/></td>
        <td>
        <input type="text" class="text" name="${sheetPageName}faultVill" id="${sheetPageName}faultVill"
        value="${sheetMain.faultVill}" alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'"/>
        </td>
        <td class="label"><bean:message bundle="complaint" key="complaint.isVisit"/></td>
        <td>
        <eoms:comboBox name="${sheetPageName}isVisit" id="${sheetPageName}isVisit" initDicId="10301"
                       defaultValue="${sheetLink.isVisit}"/>
        </td>
        </tr>

        <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.faultDesc"/></td>
        <td colspan="3">
        <textarea name="faultDesc" id="faultDesc" class="textarea max"
        alt="allowBlank:true,maxLength:1000,vtext:'最多输入500汉字'">${sheetMain.faultDesc}</textarea>
        </td>
        </tr>-->


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
            <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="complaint" />-->
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="complaint"/>
        </td>
    </tr>


</table>


<br/>

<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="complaint" key="role.FirstExcute"/>
        </legend>


        <eoms:chooser id="test" type="role" roleId="214" flowId="052" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>
</c:if>
		