<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<%@ page import="com.boco.eoms.common.tree.WKTree" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
<%@ page
        import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    String str = (String) request.getAttribute("StringTree");
%>
<style>
    body, select {
        font-size: 9pt;
        font-family: Verdana;
    }

    select {
        background-color: #F0F0F0;
    }
</style>

<script language="JavaScript"
        src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE=JavaScript>
    var s = ["s1", "s2", "s3"];
    var opt0 = ["", "", ""];
    var dsy = new Dsy();
    <%=str%>

    function setup() {
        for (i = 0; i < s.length - 1; i++)
            document.getElementById(s[i]).onchange = new Function("change(dsy," + (i + 1) + ",s,opt0)");
        change(dsy, 0, s, opt0);
    }

    var x = ["x1", "x2", "x3"];
    <%=str%>

    function setup1() {
        for (i = 0; i < x.length - 1; i++)
            document.getElementById(x[i]).onchange = new Function("change(dsy," + (i + 1) + ",x,opt0)");
        change(dsy, 0, x, opt0);
    }

    Ext.onReady(function () {
        setup();
        setup1();
        changeCardType(1);
    });
</SCRIPT>

<html:form method="post" action="/TawTestcard/save" onsubmit="return check(this);">

    <%
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) session
                .getAttribute("sessionform");
        String userName = saveSessionBeanForm.getUsername();
        String userId = saveSessionBeanForm.getUserid();
        String deptId = String.valueOf(saveSessionBeanForm.getDeptid())
                .toString();
    %>
    <html:hidden property="userId" value="userId"/>
    <html:hidden property="deptId" value="deptId"/>

    <SCRIPT LANGUAGE=javascript>
        function isnumeric(p) {
            if (p == "")
                return false;
            var l = p.length;
            var count = 0;
            for (var i = 0; i < l; i++) {
                var digit = p.charAt(i);
                if (digit == ".") {
                    ++count;
                    if (count > 1) {
                        return false;
                    }
                } else if (digit < "0" || digit > "9") {
                    return false;
                }
            }
            return true;
        }

        function bb(main) {
            if (document.tawTranfaultreportForm.city.options.length != 0) {
                for (i = 1; i <= document.tawTranfaultreportForm.city.options.length; i++) {
                    if (document.tawTranfaultreportForm.city.options[i].value == main) {
                        document.tawTranfaultreportForm.city.options[i].selected = "true";
                        document.tawTranfaultreportForm.city.disabled = "false";
                    }
                }
            }
        }

        function countrySelect() {
            var con = document.tawTestcardForm.fromCanton.value;
            alert(document.tawTestcardForm.fromCanton.value);
        }

        function check() {
            var iccidVar = $('iccid').value.trim();
            var msisdnVar = $('msisdn').value.trim();
            try {
                if (iccidVar == '') {
                    alert('${eoms:a2u("iccid不可为空!")}');
                    return false;
                }
                if (msisdnVar == '') {
                    alert('${eoms:a2u("msisdn不可为空!")}');
                    return false;
                }
                if (!isnumeric(iccidVar)) {
                    alert('${eoms:a2u("iccid包含非数字字符!")}');
                    return false;
                }
                if (!isnumeric(msisdnVar)) {
                    alert('${eoms:a2u("msisdn包含非数字字符!")}');
                    return false;
                }

                return true;
            } catch (e) {
                alert(e.description);
                return false;
            }

        }

        var DB = 0;

        function changeCardType(v) {
            document.getElementById("visitField").style.display = (v == 1 || v == 2 || v == 5 || v == 4) ? "none" : "block";
            document.getElementById("leaveid").style.display = (v == 0 || v == 3 || v == 6) ? "none" : "block";
            if (v == 0 || v == 3 || v == 6) {
                DB = 1;
            } else {
                DB = 0;
            }
        }

        function showbutton(v) {
            if (v == 35) {
                ms0.style.display = "none";
                ms1.style.display = "block";
                im0.style.display = "none";
                im1.style.display = "block";
                tr1.style.display = "block";
            } else {
                ms0.style.display = "block";
                ms1.style.display = "none";
                im0.style.display = "block";
                im1.style.display = "none";
                tr1.style.display = "none";
            }
        }

        function defaultButton(bool) {
            if (!bool) {
                clickUnlock();
                return false;
            } else {
                document.forms[0].submit();
            }
        }

        //表单验证
        function checkConfirm() {
            clickRefuse();

            var count = 0;

            var frm = document.forms[0];
            if (count == 0) {
                if (!checkLength(frm.iccid, 1, 20)) return false;
                if (DB == 0) {
                    if (!checkLength(frm.leave, 1, 20)) return false;
                }
                if (!checkLength(frm.cardpackage, 1, 20)) return false;
                //if (!checkLength(frm.oldNo,1,20)) return false;
                //if (!checkLength(frm.msisdn,1,20)) return false;
                //if (!checkLength(frm.imsi,1,20)) return false;
                if (!checkLength(frm.exes, 1, 20)) return false;
                //if (!checkLength(frm.offer,1,20)) return false;
                //if (!checkLength(frm.msgcenterno,1,20)) return false;
                if (!checkLength(frm.begintime, 1, 20)) return false;
            }

            return true;
        }
    </script>
    <br>

    <html:hidden property="strutsAction"/>
    <html:hidden property="id"/>

    <table width="100%" class="formTable" align="center">
        <caption>
            <b> ${eoms:a2u("测 试 卡 入 库--")} <c:choose>
                <c:when
                        test="${requestScope['tawTestcardForm'].strutsAction == 1}">
                    ${eoms:a2u("添加")}
                </c:when>
                <c:otherwise>
                    ${eoms:a2u("编辑")}
                </c:otherwise>
            </c:choose> </b>
        </caption>
        <tr>

            <td noWrap width="100" class="label">
                    ${eoms:a2u("卡类型")}
            </td>
            <td colspan=3 width="80%" align="left">
                <input type="radio" name="cardType" value="2" checked="checked"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("省际来访卡")}&nbsp;
                <input type="radio" name="cardType" value="3"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("省际出访卡")}&nbsp;
                <input type="radio" name="cardType" value="5"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("省内来访卡")}&nbsp;
                <input type="radio" name="cardType" value="6"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("省内出访卡")}&nbsp;
                <input type="radio" name="cardType" value="4"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("本地测试卡")}&nbsp;
                <input type="radio" name="cardType" value="1"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("国际来访卡")}&nbsp;
                <input type="radio" name="cardType" value="0"
                       onclick="changeCardType(this.value);"/>
                    ${eoms:a2u("国际出访卡")}&nbsp;
            </td>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("归属地")}
            </td>
            <td width="100%" height="25" colspan="3" nowrap="nowrap">
                    ${eoms:a2u("国家")}
                <select name="fromCountry" id="s1" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("省份")}
                <select name="fromCrit" id="s2" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("地市")}
                <select name="fromCity" id="s3" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("运营商")}
                <html:text styleClass="clstext" property="fromOpe"/>
            </td>
        </tr>
        <tr id="visitField">
            <td noWrap width="100" class="label">
                    ${eoms:a2u("拜访地")}
            </td>
            <td width="100%" height="25" colspan="3" nowrap="nowrap">
                    ${eoms:a2u("国家")}
                <select name="toCountry" id="x1" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("省份")}
                <select name="toCrit" id="x2" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("地市")}
                <select name="toCity" id="x3" style="width: 3cm;"></select>
                &nbsp&nbsp ${eoms:a2u("运营商")}
                <input type="text" name="toOpe"/>
            </td>
        </tr>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("存放公司")}
            </td>
            <td width="380" id="leaveid"><%--
						<html:select property="leave" style="width: 4.0cm;" value=""
							title="存放公司">
							<html:optionsCollection name="tawTestcardForm"
								property="beanCollectionDN" />
						</html:select>
					--%> <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/></td>

            <td class="label" width="100">
                    ${eoms:a2u("套餐类型")}
            </td>
            <td>
                <html:select property="cardpackage" style="width: 4.0cm;" value=""
                             onchange="showbutton(this.value);" title="${eoms:a2u('套餐类型')}">
                    <html:optionsCollection name="tawTestcardForm"
                                            property="beCollep"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("卡号")}(iccid)
            </td>
            <td>
                <html:text styleClass="clstext" property="iccid" size="20"
                           title="${eoms:a2u('卡号(iccid)')}"/>
                <font color="#ff0000">*</font>
            </td>
            <td noWrap width="100" class="label">
                    ${eoms:a2u('电话号码')}
            </td>
            <td>
                <html:text styleClass="clstext" property="phoneNumber" size="20" title="${eoms:a2u('电话号码')}"/>
            </td>
            <!-- td noWrap width="80" class="label">
						${eoms:a2u("单卡编号")}
					</td>
					<td width="380">
						<html:text styleClass="clstext" property="oldNo" size="20"
							title="${eoms:a2u('单卡编号')}" />
					</td-->

        </tr>
        <tr>
            <td id='ms0' noWrap width="100" class="label">
                msisdn
            </td>
            <td id='ms1' noWrap width="100" class="label" style="display:none">
                msisdn1
            </td>
            <td>
                <html:text styleClass="clstext" property="msisdn" size="20"
                           title="msisdn"/>
                <font color="#ff0000">*</font>
            </td>
            <td id='im0' noWrap width="100" class="label" style="display:block">
                IMSI
            </td>
            <td id='im1' noWrap width="100" class="label" style="display:none">
                IMSI1
            </td>
            <td>
                <html:text styleClass="clstext" property="imsi" size="20"
                           title="IMSI"/>
            </td>
        </tr>
        <tr id='tr1' style="display:none">
            <td id='td00' noWrap width="100" class="label">
                msisdn2
            </td>
            <td>
                <html:text styleClass="clstext" property="msisdn1" size="20"
                           title="msisdn2"/>
            </td>
            <td noWrap width="80" class="label">
                IMSI2
            </td>
            <td>
                <html:text styleClass="clstext" property="imsi1" size="20"
                           title="IMSI2"/>
            </td>
        </tr>
        <tr>
            <td noWrap width="80" class="label">
                    ${eoms:a2u('个人识别码')}
            </td>
            <td width="380">
                <html:text styleClass="clstext" property="pin1" size="20" title="个人识别码"/>
            </td>

            <td noWrap width="80" class="label"> ${eoms:a2u('解锁码')}</td>
            <td width="380"><html:text styleClass="clstext" property="puk1" size="20" title="解锁码1"/></td>
            </td>
        </tr>
        <tr>
            <td noWrap width="80" class="label">${eoms:a2u('开机密码')}</td>
            <td width="380"><html:text styleClass="clstext" property="password" size="20" title="开机密码"/></td>
            <td noWrap width="80" class="label"></td>
            <td width="380"></td>
        </tr>
        <!--<tr>
          <td class="label">册号</td>
          <td><html:text property="volumenum" size="20" styleClass="clstext" title="册号"/></td>
          <td class="label">页号</td>
          <td><html:text property="pagenum" size="20" styleClass="clstext" title="册号"/>
          </td>
        </tr>

       <tr>
               <td noWrap width="80"  class= "label">
                      个人识别码1
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="pin1" size="20" title="个人识别码1"/>
                </td>
                <td noWrap width="80"  class= "label">
                      个人识别码2
                </td>
                <td width="380">
                       <html:text styleClass="clstext" property="pin2" size="20" title="个人识别码2"/>
                </td>
       </tr>
        <tr style="display:none">
               <td noWrap width="80"  class= "label">解锁码1</td>
               <td width="380"><html:text styleClass="clstext" property="puk1" size="20" title="解锁码1"/></td>
               <td noWrap width="80"  class= "label">解锁码2</td>
               <td width="380"><html:text styleClass="clstext" property="puk2" size="20" title="解锁码2"/></td>
       </tr>
       <tr style="display:none">
               <td noWrap width="80"  class= "label">开机密码</td>
               <td width="380"><html:text styleClass="clstext" property="password" size="20" title="开机密码"/></td>
               <td noWrap width="80"  class= "label">旧系统编号</td>
               <td width="380"><html:text property="oldNo" styleClass="clstext" title="旧系统编号"/></td>
       </tr>-->
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("状态")}
            </td>
            <td>
                <html:select property="state" style="width: 4.0cm;">
                    <html:optionsCollection name="tawTestcardForm"
                                            property="beanCollection"/>
                </html:select>
            </td>
            <td class="label">
                    ${eoms:a2u("费用情况")}
            </td>
            <td>
                <html:text styleClass="clstext" property="exes" size="20"
                           title="${eoms:a2u('费用情况')}"/>
            </td>
        </tr>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("归属HLR厂商")}
            </td>
            <td>
                <html:text styleClass="clstext" property="offer" size="20"
                           title="${eoms:a2u('归属HLR厂商')}"/>
            </td>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("归属HLR GT")}
            </td>
            <td>
                <html:text styleClass="clstext" property="msgcenterno" size="20"
                           title="${eoms:a2u('归属HLR GT')}"/>
            </td>
        </tr>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("入库人")}
            </td>
            <td>
                <%=userName%>
            </td>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("入库时间")}
            </td>
            <td>
                <html:text property="begintime" styleClass="clstext"
                           onclick="popUpCalendar(this, this);" readonly="true" title="${eoms:a2u('入库时间')}"/>
            </td>
            <!--<td noWrap width="80"  class= "label">注销时间</td>
            <td width="380"><html:text property="endtime" styleClass="clstext" onfocus="setday(this)" readonly="true"/></td>-->
        </tr>
        <!--<tr>
            <td noWrap width="80"  class= "label">短信中心号码</td>
            <td width="380"><html:text styleClass="clstext" property="msgcenterno" size="20" title="短信中心号码"/></td>
            <td  noWrap width="80"  class= "label">
             最后测试时间
                </td>
                <td width="380" >
                      <html:text styleClass="clstext" property="lasttesttime" onfocus="setday(this)" readonly="true"/>
                </td>
       </tr>
       <tr>
            <td noWrap width="80"  class= "label">
             	测试结果
            </td>
            <td width="380">
            	<html:text styleClass="clstext" property="testresult" size="20" title="测试结果"/>
            </td>
            <td  noWrap width="80"  class= "label">
             	处理结果
            </td>
            <td width="380" >
            	<html:text styleClass="clstext" property="dealresult" size="20" title="处理结果"/>
            </td>
       </tr>-->
        <tr>

            <td noWrap width="100" class="label">
                    ${eoms:a2u("存放位置")}
            </td>
            <td width="380" colspan="3">
                <html:text styleClass="clstext" property="position" size="77"
                           title="${eoms:a2u('存放位置')}"/>
            </td>
        </tr>
        <tr>
            <td noWrap width="100" class="label">
                    ${eoms:a2u("备注")}
            </td>
            <td width="380" colspan="3">
                <html:textarea property="operation" rows="4" cols="88" title="${eoms:a2u('备注')}"/>
            </td>
        </tr>
    </table>
    <tr>
        <td colspan=4 align="left">
            <%
                String checkConfirm = "return defaultButton(checkConfirm());";
            %>

            <html:submit styleClass="button">${eoms:a2u("保存")}</html:submit>
            <html:reset styleClass="button">
                ${eoms:a2u("重置")}
            </html:reset>
            &nbsp;&nbsp;

        </td>
    </tr>

    </center>
</html:form>
<logic:messagesPresent>
    <html:messages id="error">
        <script type="text/javascript">

        </script>
    </html:messages>
</logic:messagesPresent>

<%@ include file="/common/footer_eoms.jsp" %>