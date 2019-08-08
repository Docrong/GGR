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
            var batchIds = document.getElementsByName("batchIds");
            var selfValue = self.value;
            //找出第一个选中的值
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].value == selfValue) {
                    if (batchIds[i].checked == false) {
                        batchIds[i].checked = true;
                        var rowother = Ext.get(batchIds[i]).findParent("tr", 3, true);
                        rowother.addClass("highlight");
                    }
                }
            }
        } else {
            row.removeClass("highlight");
            row.removeClass("highbackground");
        }
    }

    function loadBatchPage(obj) {
        var batchIds = document.getElementsByName("batchIds");

        if (batchIds.length > 0) {
            var sheetId = "";
            var isChecked = false;
            var id = "";
            var isSame = true;

            //找出第一个选中的值
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].checked == true) {
                    sheetId = batchIds[i].value;
                    isChecked = true;
                    break;
                }
            }
            //第一个选中的值和后面选中的值进行比较，如果不相同则中止
            if (isChecked) {
                for (var i = 0; i < batchIds.length; i++) {
                    if (batchIds[i].checked == true) {
                        if (sheetId != batchIds[i].value) {
                            isSame = false;
                            break;
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
                alert("请选择相同工单进行批量回复操作！");
                return false;
            }
            id = id.substring(0, id.length - 1);
            //batchType:sameSheet为同一工单，同一环节。differSheet为不同工单，同一环节
            window.document.location.href = "./${module}.do?method=showBatchDealPage&batchType=sameSheet&taskIds=" + id

        }
    }
</script>