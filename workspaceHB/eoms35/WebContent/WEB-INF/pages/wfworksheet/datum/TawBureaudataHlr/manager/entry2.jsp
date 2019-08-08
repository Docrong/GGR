<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<script language="javascript" type="text/javascript">
    function checkForm() {
        var hlrName = document.getElementById("hlrName").value;
        var hlrSignalId = document.getElementById("hlrSignalId").value;
        var hlrId = document.getElementById("hlrId").value;
        if (hlrName == '') {
            alert('请输入HLR名称！');
            document.getElementById("hlrName").select();
            return false;
        }

        if (document.getElementById("hlrId").value == '') {
            alert('请输入HLR ID！');
            document.getElementById("hlrId").select();
            return false;
        }
        if (hlrSignalId.value == '') {
            alert('请输入HLR信令点！');
            document.getElementById("hlrSignalId").select();
            return false;
        } else {
            //alert(hlrSignalId);
            Ext.Ajax.request({
                form: "",
                method: "post",
                url: "bureaudataHlr.do?method=checkForm&hlrSignalId=" + hlrSignalId,
                success: function (v, c) {
                    var returnValue = v.responseText
                    //alert(returnValue);
                    if (returnValue == '0') {
                        alert('您输入HLR信令点已存在！');
                        document.getElementById("hlrSignalId").select();
                        return false;
                    } else {
                        document.forms[0].action = "bureaudataHlr.do?method=update";
                        document.forms[0].submit()
                    }
                }
            });
        }
    }

    function torecover() {
        location.href = "./bureaudataHlr.do?method=recover&id=${bureaudataHlr.id}&hlrname=${bureaudataHlr.hlrname}&hlrsignalid=${bureaudataHlr.hlrsignalid}&hlrid=${bureaudataHlr.hlrid}&remark=${bureaudataHlr.remark}&bureauid=${bureaudataHlr.bureauid}&belongcityid=${bureaudataHlr.belongcityid}&deleted=${bureaudataHlr.deleted}";
    }

    function todeleteforever() {
        location.href = "./bureaudataHlr.do?method=remove&id=${bureaudataHlr.id}";
    }
</script>

<html:form action="/bureaudataHlr.do?method=update" styleId="theform">
    <div align="center">
        <center>
            <br>
            <table class="formTable">
                <tr>
                    <td class="label" align="center">
                        HLR数据
                    </td>
                </tr>
            </table>
            <input type="hidden" name="id" value="${bureaudataHlr.id  }"/>

            <input type="hidden" name="hlrid" value="${bureaudataHlr.hlrid  }"/>
            <input type="hidden" name="remark" value="${bureaudataHlr.remark  }"/>
            <input type="hidden" name="bureauid" value="${bureaudataHlr.bureauid  }"/>
            <input type="hidden" name="belongcityid" value="${bureaudataHlr.belongcityid  }"/>
            <input type="hidden" name="deleted" value="${bureaudataHlr.deleted  }"/>
            <table class="formTable">
                <tr>
                    <td class="label">HLR ID</td>
                    <td>${bureaudataHlr.hlrid }</td>
                </tr>
                <tr>
                    <td class="label">HLR名称</td>
                    <td><input type="text" class="text" name="hlrname" value="${bureaudataHlr.hlrname }"></td>
                </tr>
                <tr>
                    <td class="label">HLR信令点</td>
                    <td><input type="text" class="text" name="hlrsignalid" value="${bureaudataHlr.hlrsignalid }"></td>
                </tr>

            </table>
            <table class="formTable">
                <tr>
                    <td width="100%" height="32" align="right">
                        <input type="button" onclick="return checkForm();" class="btn" value="保存">
                        <html:reset styleClass="btn" value="重置"></html:reset>
                        <c:if test="${bureaudataHlr.deleted==0 }">
                            <input type="button" value="删除" class="btn" onclick="torecover();">
                        </c:if>
                        <c:if test="${bureaudataHlr.deleted==1 }">
                            <input type="button" value="恢复" class="btn" onclick="torecover();">
                        </c:if>
                        <input type="button" value="永久删除" class="btn" onclick="todeleteforever();">
                    </td>
                </tr>
            </table>
        </center>
    </div>

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
