
























<html>
<head>
  <title>�����ƶ���������ά��ϵͳ</title>
    <meta http-equiv="Cache-Control" content="no-store"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

  <script type="text/javascript" charset="utf-8" src="/eoms/scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="/eoms/scripts/base/eoms.js"></script>
  <script type="text/javascript">
	eoms.appPath = "/eoms";
  </script>
  <link rel="stylesheet" type="text/css" media="all" href="/eoms/styles/default/theme.css" />
  <!-- EXT LIBS verson 1.1 -->
  <script type="text/javascript" src="/eoms/scripts/ext/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="/eoms/scripts/ext/ext-all.js"></script>
  <script type="text/javascript" src="/eoms/scripts/adapter/ext-ext.js"></script>
  <script type="text/javascript" src="/eoms/scripts/ext/source/locale/ext-lang-zh_CN.js"></script>
  <link rel="stylesheet" type="text/css" href="/eoms/scripts/ext/resources/css/ext-all.css" />
  <link rel="stylesheet" type="text/css" href="/eoms/styles/default/ext-adpter.css" />
  <!-- EXT LIBS END -->
  <script type="text/javascript" src="/eoms/scripts/form/Options.js"></script>
  <script type="text/javascript" src="/eoms/scripts/form/combobox.js"></script>
  <script type="text/javascript" src="/eoms/scripts/form/dynamictable.js"></script>  
  <script type="text/javascript" src="/eoms/scripts/widgets/calendar/calendar.js"></script>
  <script type="text/javascript" src="/eoms/scripts/form/validation.js"></script>
</head>

<body>
<div id="page">


  <div id="content" class="clearfix">
    <div id="main"><br/><br/>


<title>��ϸ��Ϣ
</title>
<content tag="heading">
��ϸ��Ϣ
>
123111
</content>

<script type="text/javascript">

	//depttree
	var deptTree;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
	
	Ext.onReady(function() {
		v = new eoms.form.Validation({form:'threadForm'});
	});
	
	
	Ext.onReady(function(){
		deptViewer = new Ext.JsonView("view",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>û��ѡ����Ŀ</div>'
			}
		);
		var s='[{"nodeType":"dept","name":"ʡ��˾","id":"402881a81b457bd7011b457e88a30008"}]';
		deptViewer.jsonData = eoms.JSONDecode(s);
		deptViewer.refresh();
		var	treeAction='/eoms/xtree.do?method=getDeptSubRoleUserTree';
		deptTree = new xbox({
			btnId:'clkOrg',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'��֯�ṹѡ��',treeChkMode:'',treeChkType:'dept',
			showChkFldId:'showOrg',saveChkFldId:'org',viewer:deptViewer,returnJSON:true
		});
	
	});
	
</script>









<form id="threadForm" method="post" action="/eoms/workbench/infopub/thread.do?method=save">

	<ul>

		<input type="hidden" name="id" value="402881a81b457bd7011b457e88840007" />
		<input type="hidden" name="forumsId" value="8a44e4791ab60bf7011ab61cece50004" />
		<input type="hidden" name="createrId" value="qujingbo" />
		<input type="hidden" name="createTime" value="2008-07-21 20:01:04" />
		<input type="hidden" name="threadCount" value="2" />
		<input type="hidden" name="isDel" value="0" />
		<input type="hidden" id="org" name="org"/>
		
		
		<li>
			<label for="title" class="desc">����</label>
			
			<input type="text" name="title" value="fddsa" id="title" class="text medium" alt="allowBlank:false" />
		</li>

		<li>
			<label for="threadTypeId" class="desc">��Ϣ����</label>
			
			<select onchange="javascript:null" name="threadTypeId" id="threadTypeId" alt="allowBlank:false"><option value="">��ѡ��</option><option id="1" value="1" selected="selected">��Ҫ��</option><option id="2" value="2">��Ҫ����</option><option id="3" value="3">�����Ҫ</option><option id="4" value="4">����Ҫ����</option></select>
		</li>

		<li>
			<label for="canread" class="desc">������Χ</label>
			
			<input type="text"  readonly id ="showOrg" name="showOrg"/>
			<input type="button" id="clkOrg" name="clkOrg" onclick="javascript:deptThree.show(this,'showOrg','org')" value="ѡ�񷢲���Χ" />
			<br/><br/>
			Ĭ��Ϊ��ǰ�û��������µ����в��ţ���ѡ�񳬳�ǰ�û����ŷ�Χ����Ҫ��ˡ��統ǰ�û��ڡ�����ֹ�˾����Ҫ���͡��Ĵ��ֹ�˾������Ҫ��ˡ�
			<div id="view"></div>
		</li>



	
			

			

			
			
		</li>
	</ul>
	
</form>

				</div>
			</div>
		</div>
	</body>
</html>

