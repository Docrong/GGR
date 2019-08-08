<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<script type="text/javascript"
        src="${app}/scripts/layout/AppFrameTree.js"></script>
<script type="text/javascript">
    var config = {
        /**************
         * Tree Configs
         **************/
        treeGetNodeUrl: "${app}/duty/tawRmPlanContent.do?method=getNodes",
        treeRootId: '-1',
        treeRootText: "${eoms:a2u('日志管理')}",
        pageFrame: 'formPanel-page',
        pageFrameId: 'formPanel-page',
        onClick: {url: "${app}/duty/tawRmPlanContent.do?method=allSearch&id=", text: '${eoms:a2u('查询')}'},
        ctxMenu: [
            {
                id: 'Workplan',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/duty/tawRmPlanContent.do?method=edit&folderPath='
            },
            {
                id: 'WorkplanQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmPlanContent.do?method=planSearch&folderPath='
            },
            {
                id: 'GdRecord',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/duty/tawRmWorkorderRecord.do?method=edit&folderPath='
            },
            {
                id: 'GdRecordQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmWorkorderRecord.do?method=workOrderSearch&folderPath='
            },
            {
                id: 'DutyRecord',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/workbench/memo/editTawWorkbenchMemoSendLog.do?folderPath='
            },
            {
                id: 'DutyRecordQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/workbench/memo/tawWorkbenchMemoNodes.do?method=memoSearch&folderPath='
            },
            {
                id: 'SfRecord',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/duty/tawRmDispatchRecord.do?method=edit&folderPath='
            },
            {
                id: 'SfRecordQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmDispatchRecord.do?method=dispatchRecordSearch&folderPath='
            },
            {
                id: 'PersonCheckin',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/duty/tawRmVisitRecord.do?method=edit&folderPath='
            },
            {
                id: 'PersonCheckinQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmVisitRecord.do?method=visitRecordSearch&folderPath='
            },
            {
                id: 'WpCheckin',
                text: '${eoms:a2u('新增')}',
                cls: 'new-mi',
                url: '${app}/duty/tawRmLoanRecord.do?method=edit&folderPath='
            },
            {
                id: 'WpCheckinQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmLoanRecord.do?method=loanRecordSearch&folderPath='
            },
            {
                id: 'ReliefQuery',
                text: '${eoms:a2u('查询')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmReliefRecord.do?method=reliefRecordSearch&folderPath='
            },
            {
                id: 'LogUniteQuery',
                text: '${eoms:a2u('查询日志合并记录')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmLogUnite.do?method=logUniteSearch&folderPath='
            },
            {
                id: 'LogUnite',
                text: '${eoms:a2u('日志合并')}',
                cls: 'list-mi',
                url: '${app}/duty/tawRmLogUnite.do?method=toLogUnite&folderPath='
            }

        ],//end of ctxMenu
        /************************s
         * Custom onLoad Functions
         *************************/
        onLoadFunctions: function () {
        }
    }; // end of config
    Ext.onReady(AppFrameTree.init, AppFrameTree);
</script>
<style type="text/css">
    body {
        background-image: none;
    }
</style>
<div id="headerPanel" class="x-layout-inactive-content">
    <h1>
        ${eoms:a2u('日志管理')}
    </h1>
</div>
<div id="helpPanel" style="padding:10px;"
     class="x-layout-inactive-content">
    <dl>
        <dt>
            ${eoms:a2u('功能说明')}
        </dt>
        <dd>
            ${eoms:a2u('值班日志记录分为日常维护作业计划、处理工单记录等功能记录。交班时必须完成作业计划中的所有内容（作业计划不存在跨班情况）。日常维护作业计划内容：作业时间、作业描述、作业记录、是否完成、完成时间等项目；处理工单记录：记录该值班人员值班时间派发受理工单的情况，内容包括待办工单名称、受理工单、工单标题等，该内容由工单管理功能自动生成；事务记录/值班记事：记录该值班人员值班时间的除日常作业计划外其他工作，内容包括：事务时间、事务内容等；收/发文记录：记录该值班人员值班时间收到和发出的非电子类型的文件工单，内容包括：收/发文时间、收/发文标题、备注；外来人员登记：记录外来人员情况，内容包括；来访人姓名、单位、证件，来访时间等 。外借物品登记：记录外借物品情况，内容包括；外借物品名称、归还情况等  交接班情况：接班人员姓名、接班时间、未完成的工单列表、接班备注等。')}
        </dd>
        <dt>
            ${eoms:a2u('日常维护作业计划:')}
        </dt>
        <dd>
            ${eoms:a2u('新增记录是从作业计划中提取出来符合条件的记录列表，点击【保存】按钮即保存所有记录，如果没有符合条件的记录，则会提示没有记录可以新增。')}
        </dd>
        <dt>
            ${eoms:a2u('处理工单记录:')}
        </dt>
        <dd>
            ${eoms:a2u('新增记录是从工单记录中提取出来符合条件的记录列表，点击【保存】按钮即保存所有记录，如果没有符合条件的记录，则会提示没有记录可以新增。')}
        </dd>
        <dt>
            ${eoms:a2u('事务记录/值班记事:')}
        </dt>
        <dd>
            ${eoms:a2u('新增：鼠标右键点击树图中的【事务记录/值班记事】，选择【新增】，进入新增界面，填写相关内容后，点击【保存】按钮即保存完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【事务记录/值班记事】，选择【查询】，进入查询界面，输入相关查询 条件后点击【查询】按钮。相应的事务记录列表被显示出来。')}
        </dd>
        <dt>
            ${eoms:a2u('事务记录/值班记事:')}
        </dt>
        <dd>
            ${eoms:a2u('新增：鼠标右键点击树图中的【事务记录/值班记事】，选择【新增】，进入新增界面，填写相关内容后，点击【保存】按钮即保存完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【事务记录/值班记事】，选择【查询】，进入查询界面，输入相关查询 条件后点击【查询】按钮。相应的事务记录列表被显示出来。')}
        </dd>
        <dt>
            ${eoms:a2u('收/发文记录:')}
        </dt>
        <dd>
            ${eoms:a2u('新增：鼠标右键点击树图中的【收/发文记录】，选择【新增】，进入新增界面，填写相关内容后，点击【保存】按钮即保存完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【收/发文记录】，选择【查询】，进入查询界面，输入相关查询 条件后点击【查询】按钮。相应的收/发文记录列表被显示出来。')}
        </dd>
        <dd>
            ${eoms:a2u('修改和删除：在查询后得到的收/发文记录列表中，点击想进行操作的记录的【文件名称】 链接进入修改和删除界面,在页面中填写信息后点击【保存】按钮则完成修改操作，如果想删除则直接点击【删除】按钮')}
        </dd>
        <dt>
            ${eoms:a2u('外来人员登记:')}
        </dt>
        <dd>
            ${eoms:a2u('新增：鼠标右键点击树图中的【外来人员登记】，选择【新增】，进入新增界面，填写相关内容后,点击【保存】按钮即保存完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【外来人员登记】，选择【查询】，进入查询界面，输入相关查询 条件后点击【查询】按钮。相应的外来人员登记列表被显示出来。')}
        </dd>
        <dd>
            ${eoms:a2u('修改和删除：当用户在值班状态下的时候，可以对记录进行修改和删除,在查询后得到的外来人员登记列表中，点击想进行操作的记录的【来访者姓名】 链接进入修改和删除界面,当离开时间在记录里是空的时候，记录可以进行修改和删除操作，在页面中填写信息后点击【保存】按钮则完成修改操作，如果想删除则直接点击【删除】按钮，如果已经存在离开时间，则记录不能进行修改和删除操作')}
        </dd>
        <dd>
            ${eoms:a2u('查看：当用户不在值班状态下，查询到的记录不能进行修改和删除操作，点击记录的链接则只能查看记录内容。')}
        </dd>
        <dt>
            ${eoms:a2u('外借物品登记:')}
        </dt>
        <dd>
            ${eoms:a2u('新增：鼠标右键点击树图中的【外借物品登记】，选择【新增】，进入新增界面，填写相关内容后，点击【保存】按钮即保存完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【外借物品登记】，选择【查询】，进入查询界面，输入相关查询 条件后点击【查询】按钮。相应的外借物品登记列表被显示出来。')}
        </dd>
        <dd>
            ${eoms:a2u('修改和删除：当用户在值班状态下的时候，可以对记录进行修改和删除,在查询后得到的外借物品登记列表中，点击想进行操作的记录的【物品名称】 链接进入修改和删除界面,当物品归还时间在记录里是空的时候，记录可以进行修改和删除操作，在页面中填写信息后点击【保存】按钮则完成修改操作，如果想删除则直接点击【删除】按钮，如果已经存在物品归还时间，则记录不能进行修改和删除操作')}
        </dd>
        <dd>
            ${eoms:a2u('查看：当用户不在值班状态下，查询到的记录不能进行修改和删除操作，点击记录的链接则只能查看记录内容。')}
        </dd>
        <dt>
            ${eoms:a2u('交接班情况:')}
        </dt>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【交接班情况】，选择【查询】，进入查询界面，输入相关查询条件后点击【查询】按钮。相应的交接班情况列表被显示出来。')}
        </dd>
        <dd>
            ${eoms:a2u('查看：查询到的记录点击【交班人员】链接则只能查看记录内容。')}
        </dd>
        <dt>
            ${eoms:a2u('日志合并:')}
        </dt>
        <dd>
            ${eoms:a2u('日志合并：鼠标右键点击树图中的【日志合并】，选择【日志合并】，进入日志合并界面，系统会把日常维护作业计划内容、处理工单记录、事务记录/值班记事、收发文记录、外来人员登记、外借物品登记、交接班情况等日志会自动提取出来合并成一定格式的文本显示在输入框中，点击【合并】按钮即完成操作。')}
        </dd>
        <dd>
            ${eoms:a2u('查询：鼠标右键点击树图中的【日志合并】，选择【查询日志合并记录】，进入查询界面，输入相关查询条件后点击【查询】按钮。相应的日志合并记录列表被显示出来。')}
        </dd>
        <dd>
            ${eoms:a2u('查看：查询到的记录点击【班次】链接则只能查看记录内容。')}
        </dd>
    </dl>
</div>
<div id="treePanel" class="x-layout-inactive-content">
    <div id="treePanel-tb" class="tb"></div>
    <div id="treePanel-body"></div>
</div>
<div id="formPanel" class="x-layout-inactive-content">
    <iframe id="formPanel-page" name="formPanel-page" frameborder="no"
            style="width:100%;height:100%" src=""></iframe>
</div>

<%@ include file="/common/footer_eoms.jsp" %>
