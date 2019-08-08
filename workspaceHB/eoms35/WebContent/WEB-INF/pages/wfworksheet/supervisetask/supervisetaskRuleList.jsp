<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod
            .getLocalString(-1);
    String endDate = com.boco.eoms.base.util.StaticMethod
            .getCurrentDateTime();
    ;
%>

<script type="text/javascript"><!--
function onRemove(id) {
    if (confirm("确认要删除该规则吗?") == true) {
        location.href = "../supervisetask/supervisetask.do?method=deletesupervisetaskRule&id=" + id;
    }
}

Ext.onReady(function () {

    initCity();
    openQuery(document.getElementsByName("openQuery"));

});


//初始化地市
function initCity() {
    Ext.Ajax.request({
        url: "${app}/sheet/commonfault/commonfaultSmsRule.do?method=initCity&parentareaid=15",
        method: 'post',
        success: function (data) {
            var value = eoms.JSONDecode(data.responseText);
            document.getElementById("tdToDeptId").innerHTML = value[0].cityOpt;
        }
    });
}


function openQuery(handler) {
    var el = Ext.get('listQueryObject');
    if (el.isVisible()) {
        el.slideOut('t', {useDisplay: true});
        handler.innerHTML = "打开快速查询";
    } else {
        el.slideIn();
        handler.innerHTML = "关闭快速查询";
    }
}

function addRule() {
    window.open("../supervisetask/supervisetask.do?method=supervisetaskRuleAdd");
}

function plshanchu() {//批量删除

    var piids = [];
    var piid = document.getElementsByName("piid");
    for (i = 0; i < piid.length; i++) {
        if (piid[i].checked) {
            var a = piid[i].value;
            piids.push(a);
        }
    }
    if (piids == '') {
        alert("未选中任何记录！");
    } else {
        if (confirm("确认要删除该规则吗?") == true) {
            location.href = "../supervisetask/supervisetask.do?method=deletesupervisetaskRule&type=plshanchu&piids=" + piids;
        }
    }

}

--></script>

<div style="border:1px solid #98c0f4;padding:5px;width:98%;"
     class="x-layout-panel-hd">工具栏： <img
        src="${app}/images/icons/search.gif" align="absmiddle"
        style="cursor:pointer"/> <span id="openQuery" style="cursor:pointer"
                                       onclick="openQuery(this);">打开快速查询</span></div>

<div id="listQueryObject"
     style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
    <form name="queryform" method="post"
          action="../supervisetask//supervisetask.do?method=supervisetaskRuleList">

        <table width="100%" class="formTable">


            <tr>
                <td colspan="2">
                    <input type="button" onclick="addRule()" value="新增" class="button">
                    <input type="button" value="批量删除" class="button" onclick="plshanchu();"></td>
            </tr>

        </table>

    </form>
</div>


<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable" export="false"
               size="${total }" partialList="true" requestURI="supervisetask.do">

    <display:column sortable="true" headerClass="sortable" title="序号">
        ${taskList_rowNum}
        <input type="checkbox" name="piid" value="${taskList.id}"/>
    </display:column>

    <display:column title="专业">
        ${taskList.major }
    </display:column>
    <display:column title="网络一级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>

    </display:column>
    <display:column title="网络二级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column title="网络三级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column title="地市">
        <eoms:id2nameDB id="${taskList.city}" beanId="tawSystemAreaDao"/>
    </display:column>
    <display:column title="区县">
        ${taskList.country }
    </display:column>
    <display:column title="挂牌类型">
        ${taskList.listedRegulationType }
    </display:column>
    <display:column title="挂牌周期">
        ${taskList.listedRegulationCycle }
    </display:column>

    <display:column title="编辑" media="html">
        <a
                href='../supervisetask/supervisetask.do?method=supervisetaskRuleEdit&id=${taskList.id}&isAdmin=${isAdmin }'
                target="_blank"> <img src="${app }/images/icons/edit.gif"/> </a>
    </display:column>


    <display:column title="删除" media="html">
        <a href="javascript:onRemove('${taskList.id}')"> <img
                src="${app }/images/icons/nodetype/empty.gif"> </a>
    </display:column>
    <c:if test="${isAdmin eq 'true' }">
    </c:if>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>
