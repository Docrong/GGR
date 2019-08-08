<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
    function publicClose() {
        window.close();
    }

</script>

<table width="500" class="formTable">
    <caption>督办记录查看</caption>
    <tr>
        <td class="label">
            督办时间
        </td>
        <td class="content">
            ${object.createTime }
        </td>
    </tr>
    <tr>
        <td class="label">
            地市
        </td>
        <td class="content">
            <!--
	    	${object.city }
		 -->
            <eoms:id2nameDB id="${object.city}" beanId="tawSystemAreaDao"/>
        </td>
        <td class="label">
            区县
        </td>
        <td class="content">
            ${object.country }
        </td>
    </tr>
    <tr>
        <td class="label">
            挂牌类型
        </td>
        <td class="content">
            ${object.listedRegulationType }
        </td>
        <td class="label">
            挂牌周期
        </td>
        <td class="content">
            ${object.listedRegulationCycle }
        </td>
    </tr>

    <tr>
        <td class="label">
            一级网络分类
        </td>
        <td class="content">
            <!--
	    	${object.mainNetSortOne }
		 -->
            <eoms:id2nameDB id="${object.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
        </td>
        <td class="label">
            二级网络分类
        </td>
        <td class="content">
            <!--
	    	${object.mainNetSortTwo }
		 -->
            <eoms:id2nameDB id="${object.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            三级网络分类
        </td>
        <td class="content">
            <!--
	    	${object.mainNetSortThree }
		 -->
            <eoms:id2nameDB id="${object.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            督办规则
        </td>
        <td class="content">
            ${object.supervisetaskRule }
        </td>
        <td class="label">
            督办内容
        </td>
        <td class="content">
            ${object.content }
        </td>
    </tr>
    <br>
    <tr>
        <td class="label">
            督办对象1
        </td>
        <td class="content">
            ${object.noticeUser1 }
        </td>
        <td class="label">
            督办对象1电话
        </td>
        <td class="content">
            ${object.noticeUserphone1 }
        </td>
    </tr>
    <tr>
        <td class="label">
            督办对象2
        </td>
        <td class="content">
            ${object.noticeUser2 }
        </td>
        <td class="label">
            督办对象2电话
        </td>
        <td class="content">
            ${object.noticeUserphone2 }
        </td>
    </tr>
    <tr>
        <td class="label">
            督办对象3
        </td>
        <td class="content">
            ${object.noticeUser3 }
        </td>
        <td class="label">
            督办对象3电话
        </td>
        <td class="content">
            ${object.noticeUserphone3 }
        </td>
    </tr>
    <tr>
        <td class="label">
            督办对象4
        </td>
        <td class="content">
            ${object.noticeUser4 }
        </td>
        <td class="label">
            督办对象4电话
        </td>
        <td class="content">
            ${object.noticeUserphone4 }
        </td>
    </tr>
    <tr>
        <td class="label">
            督办方式
        </td>
        <td class="content">
            ${object.superviseType }
        </td>
    </tr>
</table>


<br><br><br>
<center>
    <input type="button" class="button" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
           onclick="publicClose()">
</center>


<%@ include file="/common/footer_eoms.jsp" %>