<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">

    function addexecute() {
        location.href = "../sheetflowline/preAllocated.do?method=add";
    }

    function delexecute() {
        var obj = document.getElementsByName('id');
        var ids = '';
        for (var i = 0; i < obj.length; i++) {
            if (obj[i].checked) {
                ids = obj[i].value + "," + ids
            }
        }
        location.href = "../sheetflowline/preAllocated.do?method=delete&ids=" + ids;
    }

    function excelexecute() {
        location.href = "../sheetflowline/preAllocated.do?method=showImportPage";
    }

</script>


<jsp:include page="/WEB-INF/pages/sheetflowline/preallocated/quicklyquery.jsp"/>
<bean:define id="url" value="preAllocated.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="preAllocated.do"
               sort="external" size="total" partialList="true"
>

    <display:caption media="html">
        <span class="map serious">预分配列表</span>
    </display:caption>

    <display:column sortable="true" headerClass="sortable" class="icon" media="html">
        <input type="checkbox" id="id" name="id" value="${taskList.id}"/>
    </display:column>
    <display:column property="startTime" sortable="true"
                    headerClass="sortable" title="开始时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="endTime" sortable="true"
                    headerClass="sortable" title="结束时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true"
                    headerClass="sortable" title="网络分类一级"
    >
        <eoms:id2nameDB id="${taskList.netTypeOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true"
                    headerClass="sortable" title="网络分类二级"
    >
        <eoms:id2nameDB id="${taskList.netTypeTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true"
                    headerClass="sortable" title="网络分类三级"
    >
        <eoms:id2nameDB id="${taskList.netTypeThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="厂商">
        <eoms:id2nameDB id="${taskList.vendor}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="人员">
        <eoms:id2nameDB id="${taskList.dutyUserId}" beanId="tawSystemUserDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="故障响应级别">
        <eoms:id2nameDB id="${taskList.faultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column property="count" sortable="true" headerClass="sortable" title="分配循环数量"/>
    <display:column sortable="true" headerClass="sortable" title="开关">
        <c:if test="${taskList.isopen=='1' }">开</c:if>
        <c:if test="${taskList.isopen=='0' }">关</c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="操作">
        <a href="../sheetflowline/preAllocated.do?method=edit&id=${taskList.id}">
            <img src="${app }/images/icons/edit.gif"
            />
        </a>
    </display:column>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<input type="button" id="b1" value="新增" class="btn" onclick="addexecute();">
<input type="button" name="b2" id="b2" value="删除" class="btn" onclick="delexecute();"/>
<input type="button" name="b3" id="b3" value="EXCEL导入" class="btn" onclick="excelexecute();"/>
<%@ include file="/common/footer_eoms.jsp" %>