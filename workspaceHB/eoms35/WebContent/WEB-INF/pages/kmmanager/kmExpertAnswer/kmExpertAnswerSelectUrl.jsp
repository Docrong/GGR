<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<%
    String realUrl = request.getRequestURL().toString();
    String oldUrl = StaticMethod.null2String(request.getParameter("url"));
    String title = StaticMethod.nullObject2String(request.getAttribute("title"));
    String type = StaticMethod.nullObject2String(request.getAttribute("type"));
%>

<html:form action="/kmExpertAnswer.do?method=selectUrl" styleId="KmExpertAnswerForm" method="post" onsubmit="">

    <td class="label">
        问题主题
    </td>
    <td class="content">
        <input type="text" id="title" name="title" value=""/>

    </td>
    <td class="label">
        分类
    </td>
    <td class="content">
        <eoms:comboBox name="type" id="type"
                       initDicId="1010104" defaultValue="" alt="allowBlank:false,vtext:''" styleClass="select-class"
                       onchange=""/>
    </td>
    <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
</html:form>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <caption>
        <div class="header center">${listTitle}</div>
    </caption>
    <display:table name="kmExpertAnswerList" cellspacing="0" cellpadding="0"
                   id="kmExpertAnswerList" pagesize="${pageSize}" class="table kmExpertAnswerList"
                   export="false"
                   requestURI="${app}/kmmanager/kmExpertAnswer.do?method=search&title=<%=title %>&type=<%=type %>"
                   sort="list" partialList="true" size="resultSize">

        <display:column title="选择 " href="" paramId="id" paramProperty="id">
            <input type="radio" name="url" id="url" value="${kmExpertAnswerList.id}" onclick="changeUrl(this.value);"/>
        </display:column>

        <display:column property="createTime" sortable="true"
                        headerClass="sortable" title="新增时间" paramId="id" paramProperty="id"/>


        <display:column sortable="true" headerClass="sortable" title="主题">
            <a href="${app}/kmmanager/kmExpertAnswer.do?method=detailQuestion&id=${kmExpertAnswerList.id}"
               target=_blank>${kmExpertAnswerList.title}</a>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="创建人">
            <eoms:id2nameDB id="${kmExpertAnswerList.createUserId}" beanId="tawSystemUserDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="问题分类">
            <eoms:id2nameDB id="${kmExpertAnswerList.type}" beanId="ItawSystemDictTypeDao"/>
        </display:column>


        <display:column sortable="true" headerClass="sortable" title="问题状态">
            <eoms:dict key="dict-kmmanager" dictId="questionState" itemId="${kmExpertAnswerList.state}"
                       beanId="id2nameXML"/>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="kmExpertAnswer"/>
        <display:setProperty name="paging.banner.items_name" value="kmExpertAnswers"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>
<br>
相关链接：<input type="text" id="answerUrl" size="70" name="answerUrl" value="<%=oldUrl %>" title='可手动修改'/>
<div style="text-align:center">
    <input type="button" name="answerUrlt" id="answerUrlt" value="修改链接" class="btn" onclick="getUrl()"/>
</div>
<script>
    function changeUrl(id) {
        var url = "<%=realUrl%>?id=" + id + "&method=detailQuestion";
        document.getElementById("answerUrl").value = url;
    }

    function getUrl() {
        var answerUrl = document.getElementById("answerUrl").value
        window.opener.document.all.answerUrla.innerHTML = "相关链接";
        window.opener.document.all.answerUrla.href = answerUrl;
        window.opener.document.all.answerUrl.value = answerUrl;
        self.close();

    }
</script>

<%@ include file="/common/footer_eoms.jsp" %>