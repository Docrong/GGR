<jsp:directive.page import="java.util.ArrayList,java.util.Iterator"/>
<jsp:directive.page
        import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function isView() {
        //var toView= document.getElementById('next');
        //toView.style.display="none";
    }

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }

    function deptDisp() {
        var objdept = document.all("subFlag");
        var towrite = document.getElementById('test');
        if (objdept.checked == true) {
            var objTable = document.getElementById("isDept");
            objTable.style.display = "block";
            towrite.innerHTML = '<bean:message key="tawSupplierkpiInstanceList.hidden" />';
        } else {
            var objTable = document.getElementById("isDept");
            objTable.style.display = "none";
            towrite.innerHTML = '<bean:message key="tawSupplierkpiInstanceList.view" />';
        }

    }

    //开始
    var http_request = false;

    function send_request(url) {
        if (window.XMLHttpRequest) {
            http_request = new XMLHttpRequest();
            if (http_request.overrideMimeType) {
                http_request.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) {
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                }
            }
        }
        if (!http_request) {
            window.alert("不能创建XMLHttpRequest对象实例.");
            return false;
        }
        http_request.onreadystatechange = selDlFaultTypeRequest1;
        http_request.open("post", url, true);
        http_request.send(null);
    }

    function saveInfo(url) {
        send_request(url);
    }

    function selDlFaultTypeRequest1() {
        if (http_request.readyState == 4) { // 判断对象状态
            if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
                var returnValue = http_request.responseText;
                if (returnValue) {
                    rowRemove();
                } else {
                    alert("数据保存错误，请检查！");
                }
            } else { //页面不正常
                alert("excume");
            }
        }
    }

    function rowRemove() {
        var arrSortID = new Array();
        var newTable = document.getElementById("isDept");
        var objTableBody = newTable.children(0);
        var rowCount = newTable.rows.length;

        var objOld = document.getElementById("old");
        var objoldTable = objOld.children(0);
        var oldRowCount = objOld.rows.length;

        for (i = 2; i < oldRowCount - 1; i++) {
            if (objOld.rows(i).cells(5).all.tags("textarea")[0].value != null && objOld.rows(i).cells(5).all.tags("textarea")[0].value != "") {

                var newRow = document.createElement("TR")
                newRow.align = "left";
                //if(rowCount%2==0){
                //    newRow.style.background = "#DFEEFF";
                //}
                //else{
                //    newRow.style.background = "#BFDCFF";
                //}

                var objCell1 = document.createElement("TD");
                objCell1.innerText = rowCount - 1;
                newRow.appendChild(objCell1);

                var objCell2 = document.createElement("TD");
                objCell2.innerText = objOld.rows(i).cells(1).innerText;
                newRow.appendChild(objCell2);

                var objCell3 = document.createElement("TD");
                objCell3.innerText = objOld.rows(i).cells(2).innerText;
                newRow.appendChild(objCell3);

                var objCell4 = document.createElement("TD");
                objCell4.innerText = objOld.rows(i).cells(3).innerText;
                newRow.appendChild(objCell4);

                var objCell5 = document.createElement("TD");
                objCell5.innerText = objOld.rows(i).cells(4).innerText;
                newRow.appendChild(objCell5);

                var objCell6 = document.createElement("TD");
                objCell6.innerText = objOld.rows(i).cells(5).all.tags("textarea")[0].value;
                newRow.appendChild(objCell6);

                var objCell7 = document.createElement("TD");
                objCell7.innerText = objOld.rows(i).cells(6).innerText;
                newRow.appendChild(objCell7);

                var objCell8 = document.createElement("TD");
                objCell8.innerText = objOld.rows(i).cells(7).all.tags("textarea")[0].value;
                newRow.appendChild(objCell8);

                objTableBody.appendChild(newRow);
                arrSortID[arrSortID.length] = i;
                rowCount++;
            }
        }

        for (i = (arrSortID.length - 1); i >= 0; i--) {

            objOld.deleteRow(arrSortID[i]);
        }
        oldRowCount = objOld.rows.length;

        if (oldRowCount == 3) {
            var toView = document.getElementById('save');
            toView.style.display = "none";
            //var toView= document.getElementById('next');
            //toView.style.display="block";
        }
        for (i = 2; i < oldRowCount - 1; i++) {
            objOld.rows(i).cells(0).innerText = "";
            objOld.rows(i).cells(0).innerText = (i - 1);
        }
    }
</script>
<%
    String treeId1 = (String) request.getAttribute("TREEID1");
    String treeId2 = (String) request.getAttribute("TREEID2");
    String treeId = "";
    if (treeId1 != null && !treeId1.equals("") && !treeId1.equals("-1")) {
        treeId = treeId1;
    } else if (treeId2 != null && !treeId2.equals("")) {
        treeId = treeId2;
    }
    request.setAttribute("id", treeId);

    String orderType = String.valueOf(request.getAttribute("orderType"));
%>
<body onload="isView();">
<table>
    <tr class="">
        <td>
            <bean:message key="tawSupplierkpiInstanceList.title"/>
        </td>
        <td>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="checkbox" onclick="deptDisp();" name="subFlag" CHECKED/>
        </td>
        <td id="test">
            <bean:message key="tawSupplierkpiInstanceList.hidden"/>
        </td>
    </tr>
</table>

<table class="list-title">
    <tr>
        <%
            String serviceTy = (String) request.getAttribute("serviceTy");
            String specialTy = (String) request.getAttribute("specialTy");
            String statictsCy = (String) request.getAttribute("statictsCy");
            String timeLati = (String) request.getAttribute("timeLati");
            String years = (String) request.getAttribute("_year");
            String latitu = "";
            if (years != null && !years.equals("")) {
                if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("year")) {
                    latitu = years + "\u5E74\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("one")) {
                    latitu = years + "\u5E74\u7B2C\u4E00\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("two")) {
                    latitu = years + "\u5E74\u7B2C\u4E8C\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("three")) {
                    latitu = years + "\u5E74\u7B2C\u4E09\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")
                        && timeLati.equals("four")) {
                    latitu = years + "\u5E74\u7B2C\u56DB\u5B63\u5EA6";
                } else if (timeLati != null && !timeLati.equals("")) {
                    latitu = years + "-" + timeLati;
                }
            }

            if (serviceTy != null && !serviceTy.equals("")) {
        %>
        <td width="2%">
        </td>
        <td width="30%">
            <bean:message key="tawSupplierkpiInstanceList.serviceType"/>
            :
            <eoms:id2nameDB id="<%=serviceTy%>" beanId="tawSupplierkpiDictDao"/>
        </td>
        <td width="30%">
            <bean:message key="tawSupplierkpiInstanceList.specialType"/>
            :
            <eoms:id2nameDB id="<%=specialTy%>" beanId="tawSupplierkpiDictDao"/>
        </td>
        <td width="30%">
            <bean:message key="tawSupplierkpiInstanceList.timeLatitude"/>
            :
            <%=latitu%>
        </td>
        <%
            }
        %>

    </tr>
    <!--
		<tr>
			<td colspan="4">
				<br>
				<label>
					${eoms:a2u('排序')}
				</label>
				:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message key="tawSupplierkpiInstanceList.supplier" />
				<select name="supplier" id="supplier">
					<option value="-1">
						=
						<label>
							${eoms:a2u('请选择厂商名称')}
						</label>
						=
					</option>
					<%						
						ArrayList manufacturers = (ArrayList) request
								.getAttribute("manufacturers");					    
						Iterator iterator = manufacturers.iterator();
						while (iterator.hasNext()) {
							String manufacturerName = (String)iterator.next();
					%>
					<option value="<%=manufacturerName%>">
						<%=manufacturerName %>
					</option>
					<%
					}
					%>
				</select>

				<bean:message key="tawSupplierkpiInstanceList.itemType" />
				<select name="itemTypes">
					<option value="-1">
						=
						<label>
							${eoms:a2u('请选择项目分类')}
						</label>
						=
					</option>
					<%
						ArrayList itemTypes = (ArrayList) request.getAttribute("itemTypes");
						Iterator iterators = itemTypes.iterator();
						while (iterators.hasNext()) {
							String itemType = (String) iterators.next();
					%>
					<option value="<%=itemType%>">
						<eoms:id2nameDB id="<%=itemType%>" beanId="ItawSystemDictTypeDao" />
					</option>
					<%
					}
					%>
				</select>
			</td>
		</tr>
-->
</table>

<div class="list">
    <table cellspacing="0" cellpadding="0" border="0">
        <table id="old">
            <tr align="center">
                <td colspan="8">
                    <bean:message key="tawSupplierkpiInstanceList.notFill"/>
                </td>
            </tr>

            <tr class="header" align="center">
                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.numb"/>
                </td>
                <td width="8%">
                    <!-- <a href="/eoms/supplierkpi/tawSupplierkpiInstances.do?id=<%=treeId%>&manufacturerId=<%=orderType %>"> -->
                    <bean:message key="tawSupplierkpiInstanceList.supplier"/>
                    <!--</a> -->
                </td>

                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.statictsCycle"/>
                </td>
                <td width="21%">
                    <!--<a href="/eoms/supplierkpi/tawSupplierkpiInstances.do?id=<%=treeId%>&itemType=<%=orderType %>"> -->
                    <bean:message key="tawSupplierkpiInstanceList.itemType"/>
                    <!--</a> -->
                </td>
                <td width="15%">
                    <bean:message key="tawSupplierkpiInstanceList.assessContent"/>
                </td>
                <td width="20%">
                    <bean:message key="tawSupplierkpiInstanceList.examineContent"/>
                </td>
                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.unit"/>
                </td>
                <td width="20%">
                    <bean:message key="tawSupplierkpiInstanceForm.memo"/>
                </td>

            </tr>
            <%int i = 0; %>
            <logic:notEmpty name="tawSupplierkpiInstanceList">
            <logic:iterate id="aal" name="tawSupplierkpiInstanceList"
                           indexId="aalid">
            <bean:define id="statictsCycle" name="aal"
                         property="statictsCycle" type="java.lang.String"/>
            <bean:define id="unit" name="aal" property="unit"
                         type="java.lang.String"/>
            <bean:define id="year" name="aal" property="year"
                         type="java.lang.String"/>
            <bean:define id="itemType" name="aal" property="itemType"
                         type="java.lang.String"/>

            <tr class="normal">
                <td>
                    <%=++i %>
                </td>
                <td>
                    <bean:write name="aal" property="manufacturerName"/>
                </td>

                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="statictsCycle" itemId="<%=statictsCycle%>"
                               beanId="id2nameXML"/>
                </td>
                <td>
                    <!--eoms:id2nameDB id="<%=itemType%>"
									beanId="ItawSystemDictTypeDao" /-->
                    <bean:write name="aal" property="kpiName"/>
                </td>
                <td>
                    <bean:write name="aal" property="assessContent"/>
                </td>

                <td>
								<textarea style="width:100%;border:1px solid #006699" rows="4" name='examineContent'
                                          value='<bean:write name="aal" property="examineContent" />'></textarea>
                    <input type="hidden" name="infoId"
                           value='<bean:write name="aal" property="id" />'>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="unit" itemId="<%=unit%>" beanId="id2nameXML"/>
                    <input type="hidden" name="unit" value='<bean:write name="aal" property="unit" />'/>
                    <input type="hidden" name="isImpersonality"
                           value='<bean:write name="aal" property="isImpersonality" />'>

                </td>
                <td>
								<textarea style="width:100%;border:1px solid #006699" rows="4" name='memo'
                                          value='<bean:write name="aal" property="memo" />'></textarea>
                </td>
                </logic:iterate>
                </logic:notEmpty>
            <tr align="right">
                <td colspan="8">
										<span id="save"><input type="button" class="btn" onclick="toSetFormData();"
                                                               value="<bean:message key="button.save" />"/></span>
                    <!--                 <span id="next"><input type="button" onclick="javascript:window.location.reload();"
							value="${eoms:a2u('下一页')}" /></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="button" class="btn" 
							property="method.save" styleId="method.save"
							 value="${eoms:a2u('增加售前售中指标')}" onclick="sendToAdd();" />	 -->
                </td>
            </tr>
        </table>

        <table id="isDept">
            <tr align="center">
                <td colspan="8">

                    <bean:message key="tawSupplierkpiInstanceList.fillFinish"/>
                </td>
            </tr>
            <tr class="header" align="center">
                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.numb"/>
                </td>
                <td width="8%">
                    <bean:message key="tawSupplierkpiInstanceList.supplier"/>
                </td>

                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.statictsCycle"/>
                </td>
                <td width="21%">
                    <bean:message key="tawSupplierkpiInstanceList.itemType"/>
                </td>
                <td width="15%">
                    <bean:message key="tawSupplierkpiInstanceList.assessContent"/>
                </td>
                <td width="20%">
                    <bean:message key="tawSupplierkpiInstanceList.examineContent"/>
                </td>
                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.unit"/>
                </td>
                <td width="20%">
                    <bean:message key="tawSupplierkpiInstanceForm.memo"/>
                </td>

            </tr>
        </table>

    </table>
    <script>
        function toSetFormData() {
            var form = document.forms[0];
            var exam = document.getElementsByName("examineContent");
            var memo = document.getElementsByName("memo");
            var infoId = document.getElementsByName("infoId");
            var unit = document.getElementsByName("unit");
            var isImpersonality = document.getElementsByName("isImpersonality");
            var ids = "";
            var ecs = "";
            var mos = "";
            var flag = true;
            for (i = 0; i < infoId.length; i++) {
                if (exam[i].value == "") {
                    continue;
                }
                if (isImpersonality[i].value == "1030102") {//判断指标是否主观评价，非主观评价才有必要验证评估数据格式。
                    if (!isNaN(exam[i].value)) {
                        if (unit[i].value == "106010502") {//判断单位是否百分比
                            if (exam[i].value > 100 || exam[i].value < 0) {
                                alert('${eoms:a2u("单位是百分比的评估数据范围在0-100")}');
                                flag = false;
                            }
                        } else if (exam[i].value < 0) {
                            alert('${eoms:a2u("数字大于0")}');
                            flag = false;
                        }
                    } else {
                        alert('${eoms:a2u("评估数据请输入数字！")}');
                        flag = false;
                    }
                }
                ids += "@@" + infoId[i].value;
                ecs += "@@ " + encodeURI(encodeURI(exam[i].value));
                mos += "@@ " + encodeURI(encodeURI(memo[i].value));
            }
            if (flag) {
                saveInfo("<%=request.getContextPath()%>/supplierkpi/saveTawSupplierkpiInstance.do?updateid=" + ids + "&examineContentJs=" + ecs + "&memoJs=" + mos + "&treeId=<%=treeId%>");
            }
        }

        function sendToAdd() {
            var url = "<c:url value="/supplierkpi/tawSupplierkpiInstances.do?method=addSell&specialType="/>";
            url += "<%=specialTy%>";
            url += "&serviceType=" + "<%=serviceTy%>";
            location.href = url;
        }
    </script>
</div>
</body>

<%@ include file="/common/footer_eoms.jsp" %>
				