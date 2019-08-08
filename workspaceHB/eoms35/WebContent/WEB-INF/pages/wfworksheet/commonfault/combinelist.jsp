<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    function selectedMain(obj) {
        var mainCheckId = document.getElementsByName("mainCheckId");
        if (mainCheckId.length > 0) {
            var selected = null;
            for (var i = 0; i < mainCheckId.length; i++) {
                if (mainCheckId[i].checked == true && mainCheckId[i].id != obj.id) {
                    selected = mainCheckId[i];
                }
                mainCheckId[i].checked = false;
            }
            var vis = document.getElementById("vis" + obj.id);
            if (vis.checked == false) {
                obj.checked = true;
                document.getElementById("mainCheckedId").value = obj.id;
            } else {
                obj.checked = false;
                if (null != selected) {
                    selected.checked = true;
                }
            }
        }
    }

    function selectedVis(obj) {
        var mainCheckedId = document.getElementById("mainCheckedId").value;
        mainCheckedId = "vis" + mainCheckedId;
        if (obj.id == mainCheckedId) {
            obj.checked = false;
        }
        if (obj.value.split(";")[2] == "combine") {
            Ext.Msg.alert("提示", "该工单是已合并工单的主单，不能作为副单再次合并！");
            obj.checked = false;
        }
    }

    function combineSheet() {
        var mainCheckIds = document.getElementsByName("mainCheckId");
        var visCheckIds = document.getElementsByName("visCheckId");
        var main = "";
        var m = 0;
        var levelMain = 0;
        if (mainCheckIds.length > 0) {
            for (var i = 0; i < mainCheckIds.length; i++) {
                if (mainCheckIds[i].checked == true) {
                    main = main + mainCheckIds[i].value.split(";")[0];
                    levelMain = mainCheckIds[i].value.split(";")[1];
                    m++;
                }
            }
        }
        if ("" == main) {
            Ext.Msg.alert("提示", "请勾选你要合并的主单信息!");
            return;
        }
        var vis = "";
        var visLevel = 0;
        if (visCheckIds.length > 0) {
            for (var i = 0; i < visCheckIds.length; i++) {
                if (visCheckIds[i].checked == true) {
                    vis = vis + visCheckIds[i].value.split(";")[0] + ",";
                    if (visCheckIds[i].value.split(";")[1] > visLevel) {
                        visLevel = visCheckIds[i].value.split(";")[1];
                    }
                    m++;
                }
            }
        }
        var x = '${listSize}';
        if (m > x) {
            Ext.Msg.alert("提示", "超出最大的工单合并数量，请将合并数量少于" + x);
            return;
        }
        if ("" == vis) {
            Ext.Msg.alert("提示", "请勾选你要合并的副单信息!");
            return;
        }
        if (visLevel > levelMain) {
            Ext.Msg.alert("提示", "您所勾选的主单等级不是所勾选的所有工单里等级最高等级的工单，请勾选等级最高的工单为主单！");
            return;
        }
        // to add the method for procee
        document.write("<form action='${app}/sheet/commonfault/commonfault.do?method=performCombineSheet' method='post' name='formx1' style='display:none'>");
        document.write("<input type='hidden' name='mains' value='" + main + "'/>");
        document.write("<input type='hidden' name='viss' value='" + vis + "'/>");
        document.write("<input type='hidden' name='taskName' value='${taskName}'/>");
        document.write("</form>");
        document.formx1.submit();
    }
</script>
<input type="hidden" name="mainCheckedId" id="mainCheckedId"/>
<input type="hidden" name="visCheckedId" id="visCheckedId"/>
<bean:define id="url" value="commonfault.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="false" requestURI="commonfault.do" sort="list"
               size="total" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.BatchCombineSheetDisplaytagDecoratorHelper">

    <display:column sortable="false" property="id" media="html"
                    headerClass="sortable" title="主单"/>
    <display:column sortable="false" property="sendOrgType" media="html"
                    headerClass="sortable" title="副单"/>
    <display:column sortable="false" property="sendDeptId"
                    headerClass="sortable" title="优先级"/>
    <display:column property="sheetId" sortable="true" headerClass="sortable"
                    title="工单流水号"/>
    <display:column property="title" sortable="true" headerClass="sortable"
                    title="工单主题"/>
    <display:column property="sendTime" sortable="true" headerClass="sortable"
                    title="派单时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="mainNetName" sortable="true" headerClass="sortable"
                    title="网元名称"/>
    <display:column sortable="true" sortName="mainNetSortOne"
                    headerClass="sortable" title="网络一级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="mainNetSortTwo"
                    headerClass="sortable" title="网络二级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="mainNetSortThree"
                    headerClass="sortable" title="网络三级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
</display:table>
<c:if test="${total!=0 }">
    <input type="button" class="btn" onclick="javascript:combineSheet();" value="提交"/>
    <input type="button" class="btn" onclick="javascript:window.location='<html:rewrite
            page='/commonfault.do?method=showCombineSheetList&psize=f&&tt=${tt }'/>'" value="每页50张"/>
    <input type="button" class="btn" onclick="javascript:window.location='<html:rewrite
            page='/commonfault.do?method=showCombineSheetList&psize=h&tt=${tt }'/>'" value="每页100张"/>
</c:if>

<%@ include file="/common/footer_eoms.jsp" %>
