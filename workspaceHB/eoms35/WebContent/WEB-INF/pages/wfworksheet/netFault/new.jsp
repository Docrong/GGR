<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page import="com.boco.local.webapp.LocalFileUpload,java.util.*" %>
<html:form action="/localFileUpload.do?method=uploadFile" enctype="multipart/form-data">
    <table>
        <tr>
            <td class="label">初始化页面</td>

            <td>
                <input type="file" name="file">
                <input type="hidden" name=flag value=1>
            </td>
            <td><input type="submit" name="sub" value=初始化></td>
            <td>解析集团工单模块（其它模块不需要用)</td>
        </tr>
    </table>
</html:form>

