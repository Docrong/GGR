<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<style type="text/css">
    .table tr.highlight td, .listTable tr.highlight td {
        background-color: #EDF5FD;
    }

    .table tr.highbackground td, .listTable tr.highbackground td {
        background-color: yellow;
    }
</style>
<script type="text/javascript">
    function openSheet(url) {
        if (parent.frames['north']) {
            parent.frames['north'].location.href = url;
        } else {
            location.href = url;
        }
    }

    function checkAll(taskName) {
        var batchIds = document.getElementsByName("batchIds");
        for (var i = 0; i < batchIds.length; i++) {
            var batchIdvalue = batchIds[i].value;
            var row = Ext.get(batchIds[i]).findParent("tr", 3, true);
            row.removeClass("highlight");
            row.removeClass("highbackground");
            if (taskName == batchIdvalue) {
                row.addClass("highlight");
                batchIds[i].checked = true;
            } else if (taskName == "none") {
                batchIds[i].checked = false;
            } else {
                if (batchIds[i].checked == true) {
                    batchIds[i].checked = false;
                }
            }
        }
    }

    function selectedSelf(self) {
        var row = Ext.get(self).findParent("tr", 3, true);
        if (self.checked == true) {
            row.addClass("highlight");
        } else {
            row.removeClass("highlight");
            row.removeClass("highbackground");
        }
    }

    function loadBatchPage(obj) {
        var batchIds = document.getElementsByName("batchIds");
        var operateType = obj.getAttribute("operateType");
        var buttonTaskName = obj.getAttribute("taskName");
        var holdName = ""
        //是批量回复按钮
        if (buttonTaskName == null) {
            var buttonHold = document.getElementById("holdId");
            if (buttonHold != null) {
                holdName = buttonHold.getAttribute("taskName");
            }
        }

        if (batchIds.length > 0) {
            var taskName = "";
            var isSame = true;
            var isChecked = false;
            var id = "";
            var operateRoleId = "";
            var iswaitforSubtask = false;
            var wartforsubtaskList = [];
            //找出第一个选中的值
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].checked == true) {
                    taskName = batchIds[i].value;
                    operateRoleId = batchIds[i].getAttribute("operateRoleId");
                    isChecked = true;
                    break;
                }
            }
            //第一个选中的值和后面选中的值进行比较，如果不相同则中止
            if (isChecked) {
                for (var i = 0; i < batchIds.length; i++) {
                    if (batchIds[i].checked == true) {
                        var ifwaitforsubtask = batchIds[i].getAttribute("ifwaitforsubtask");
                        //是不是有子任务没有完成
                        if (ifwaitforsubtask == "true") {
                            wartforsubtaskList.push(batchIds[i].id);
                            iswaitforSubtask = true;
                        }
                        if (taskName != batchIds[i].value) {
                            isSame = false;
                            break;
                        }
                        //是批量归档按钮
                        if (buttonTaskName != null) {
                            if (batchIds[i].value != buttonTaskName) {
                                isSame = false;
                                alert("请选择待归档处理环节进行批量归档！");
                                return false;
                            }
                        } else {
                            //批量回复
                            if (batchIds[i].value == holdName) {
                                isSame = false;
                                alert("不能选择待归档处理环节进行批量回复！");
                                return false;
                            }
                        }

                        id = id + batchIds[i].id + ",";
                    }
                }
            }
            if (!isChecked) {
                alert("请选择工单进行批量操作！");
                return false;
            }
            if (!isSame) {
                alert("请选择相同处理环节下的工单进行批量操作！");
                return false;
            }
            if (iswaitforSubtask) {
                for (var i = 0; i < wartforsubtaskList.length; i++) {
                    var id = wartforsubtaskList[i];
                    var obj = document.getElementById(id);
                    var row = Ext.get(obj).findParent("tr", 3, true);
                    row.addClass("highbackground");
                }
                alert("请查看黄色背景颜色的工单，这些工单需要等待子任务完成后才能批量操作！");
                return false;
            }
            id = id.substring(0, id.length - 1);
            window.document.location.href = "./${module}.do?method=showDealPage&batch=true&taskIds=" + id
                + "&operateRoleId=" + operateRoleId + "&operateType=" + operateType + "&taskName=" + taskName;

        }
    }
</script>