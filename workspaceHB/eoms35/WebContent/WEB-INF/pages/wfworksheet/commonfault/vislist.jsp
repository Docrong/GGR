<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    function deleteSome(mid, vid, obj) {
        if (confirm("确定撤销该副单吗？")) {
            Ext.Ajax.request({
                method: "post",
                params: {
                    m: mid, v: vid
                },
                url: "${app}/sheet/commonfault/commonfault.do?method=cancelViSheet",
                success: function (x) {
                    var data = eoms.JSONDecode(x.responseText);
                    Ext.each(data, function (d) {
                        if (d.status == '0') {
                            var tab = document.getElementById('taskList');
                            var rowother = Ext.get(obj).findParent("tr", 3, null).rowIndex;
                            tab.deleteRow(rowother);
                            Ext.MessageBox.alert('提示信息', "撤单成功！");
                        } else {
                            Ext.each(d.data, function (o) {
                                Ext.MessageBox.alert('提示信息', o.text);
                            });

                        }
                    });

                }
            });
        }
    }
</script>

<bean:define id="url" value="commonfault.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="false" requestURI="commonfault.do" sort="list"
               size="total" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.BatchCombineSheetDisplaytagDecoratorHelper">

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
    <display:column property="status" sortable="true"
                    headerClass="sortable" title="操作"/>
</display:table>

<%@ include file="/common/footer_eoms.jsp" %>
