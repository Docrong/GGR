<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableGeneralConstants"/>

<html:form action="/kmTableColumns.do?method=save" styleId="kmTableColumnForm" method="post">

<eoms:xbox id="tree" dataUrl="${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTreeForAll" 
	  	rootId="<%=KmTableGeneralConstants.TREE_ROOT_ID%>" 
	  	rootText="绑定类型编号" 
	  	valueField="colDictId" handler="colDictName"
		textField="colDictName"
		checktype="forums" single="true"
		data='${data}'></eoms:xbox>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript">
var initColDictType = "${kmTableColumnForm.colDictType}";
var initColTypeIdArray=new Array();
var initColTypeValueArray=new Array();
var initColTypeTextArray=new Array();
var initSelectedValue;

Ext.onReady(function() {
    v = new eoms.form.Validation({form:'kmTableColumnForm'});
	v.custom = function(){
	    var colDictType = eoms.$('colDictType').value;
	    if(colDictType!=0){
	        var colDictId = eoms.$('colDictId').value;
	        if(colDictId==null||colDictId==""){
	            alert("请选择：绑定类型编号");
	            return false;
	        }
	        
	        document.forms[0].colSize.value='32';
	        document.forms[0].colDefault.value='';
	        document.forms[0].isUnique.value='0';
	        //document.forms[0].isNullable.value='0';
	                           
	    }else{
	        var colType = eoms.$('colType').value;
	        var colSize = eoms.$('colSize').value;
	        var oldColSize = document.forms[0].oldColSize.value;
	        
	        if(colType==1){
	            if(colSize==null||colSize==""){
			      alert("请填写：字段大小");
			      return false;
		        }
		        else if(colSize > 255){
			      alert("普通文本最大长度为：255个字符");
			      return false;		        
		        }
		        else if(colSize < oldColSize){
			      alert("普通文本最大长度为：不能小于以前的字段长度" + oldColSize + "个字符");
			      return false;	        
		        }
	        }else{
	              document.forms[0].colSize.value='2000';
	              document.forms[0].colDefault.value=''; 
	        }

	        //var isUnique = eoms.$('isUnique').value;	
	        //if(isUnique==null||isUnique==""){
	            //alert("请选择：是否唯一");
	            //return false;
	        //}  
	        //var isNullable = eoms.$('isNullable').value;	
	        //if(isNullable==null||isNullable==""){
	            //alert("请选择：是否为空");
	            //return false;
	        //} 	                          
	    }
	    
	    var selIsVisible = isVisible.options[isVisible.options.selectedIndex].value;
	    var selIsOpen = document.forms[0].isOpen.value;
	    
	    if(selIsVisible == 0 && selIsOpen == 1){
	      alert("是否开放必须为否！");
	      return false;
	    }

	    return true;
	}

	init();
});

function changeTree(){
    var colDictType = eoms.$('colDictType').value;
    selectValue(colDictType);
    
    if(colDictType==1||colDictType==2||colDictType==3){
        if(colDictType==initColDictType){
            //xbox_tree.gridData.loadData(xbox_tree.data);
            xbox_tree.gridData.loadData(${data});
            xbox_tree.update();
        }else{
            xbox_tree.reset();
        }
    }
}
	 
function init(){
    var colDictType = eoms.$('colDictType').value;
    var initColType = eoms.$('colType');
    initColType.options.remove(0);
    for(var i=0;i<initColType.length;i++){
	    initColTypeValueArray[i]=initColType.options[i].value; 
	    initColTypeTextArray[i]=initColType.options[i].text; 
	    initColTypeIdArray[i]=initColType.options[i].id;
    }

    var currSelectIndex = initColType.selectedIndex;
    initSelectedValue=initColType.options[currSelectIndex].value;
    selectValue(colDictType);
    xbox_tree.update();
}
	 
function selectValue(colDictType){
    //var colDictType=document.forms[0].colDictType.value;
    
    if(colDictType==0){
        resetColType();
        disableArea();	
        // document.forms[0].colDictName.value='0';
    }else if(colDictType==1){	    
        filterColType()
        enableArea();
        xbox_tree.defaultTree.root.id = '<%=KmTableGeneralConstants.DICT_TREE_ROOT_ID%>';
        xbox_tree.defaultTree.root.setText('<fmt:message key="kmTableGeneral.commonDict" />');	  
        xbox_tree.resetRoot("${app}/kmmanager/kmTableThemes.do?method=getCommonDict");	     
    }else if(colDictType==2){	 
        filterColType();  
        enableArea();	     
        xbox_tree.defaultTree.root.id = '<%=KmTableGeneralConstants.TREE_ROOT_ID%>';
        xbox_tree.defaultTree.root.setText('<fmt:message key="kmTableGeneral.themeId" />');
        xbox_tree.resetRoot("${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTreeForAll");
    }else if(colDictType==3){
        filterColType();
        enableArea();	     
        xbox_tree.defaultTree.root.id = '<%=KmTableGeneralConstants.FILE_TREE_ROOT_ID%>';
        xbox_tree.defaultTree.root.setText('<fmt:message key="kmTableGeneral.fileDict" />');	     
        xbox_tree.resetRoot("${app}/kmmanager/kmTableThemes.do?method=getFileNodes");
    }	    
}
	 
function disableArea(){
    eoms.form.disableArea('colDictName',true);
    eoms.form.disableArea('subNode',true);
	eoms.form.enableArea('colSize',true);
	eoms.form.enableArea('colDefault',true);
	eoms.form.enableArea('isUnique',true);
	//eoms.form.enableArea('isNullable',true);
	display();	    
}

function enableArea(){
    eoms.form.enableArea('colDictName',true);
    eoms.form.enableArea('subNode',true);
    eoms.form.disableArea('colSize',true);
    eoms.form.disableArea('colDefault',true);
    eoms.form.disableArea('isUnique',true);
    //eoms.form.disableArea('isNullable',true);	
	display(); 
}
	
function filterColType(){
    var objSelect = eoms.$('colType');
    objSelect.length=0;	  
	var len= initColTypeValueArray.length;	
	for(var i=0;i<len;i++){
	    if(initColTypeValueArray[i] =='5'||initColTypeValueArray[i] =='6') { //单选字典,多选字典
	        var varItem = new Option(initColTypeTextArray[i],initColTypeValueArray[i]);
	        objSelect.options.add(varItem);
	        if(initColTypeValueArray[i]==initSelectedValue){
                  objSelect.options[objSelect.length-1].selected = true;
	        }
	    }
	}
}

function resetColType(){
    var objSelect = eoms.$('colType');
    objSelect.length=0;
    for(var i=0;i<initColTypeIdArray.length;i++){
        if(initColTypeValueArray[i]== '5'||initColTypeValueArray[i]=='6'){//单选字典,多选字典
            continue;                              
        }
        var varItem = new Option(initColTypeTextArray[i],initColTypeValueArray[i]);       
        objSelect.options.add(varItem); 
        if(initColTypeValueArray[i]==initSelectedValue){
            objSelect.options[objSelect.length-1].selected = true;
        }
    }
}

function display(){
    var colType = eoms.$('colType').value;
    if(colType==1){//普通文本
        eoms.form.enableArea('colSize',true);
        eoms.form.enableArea('colDefault',true);
    }else{
        eoms.form.disableArea('colSize',true);	     
	    eoms.form.disableArea('colDefault',true);
	}
}
</script>

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmTableColumn.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmTableColumn.colChname" />
		</td>
		<td class="content">
			<html:text property="colChname" styleId="colChname" styleClass="text medium" alt="allowBlank:false,vtext:'请填写字段名称'" value="${kmTableColumnForm.colChname}" />
			<input type="hidden" id="colName" name="colName" value="${kmTableColumnForm.colName}" />
		</td>
			<td class="label">
			<fmt:message key="kmTableColumn.colDictType" />
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="colDictType" isQuery="false" alt="allowBlank:false,vtext:'请选择绑定类型'"
				defaultId="${kmTableColumnForm.colDictType}" selectId="colDictType" beanId="selectXML" onchange="changeTree()"/>			
		</td>		
	</tr>

	<tr>	
		<td class="label">
			<fmt:message key="kmTableColumn.colDictId" />
		</td>
		<td class="content">
			<input type="text"   id="colDictName" name="colDictName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${kmTableColumnForm.colDictId}" beanId="kmTableThemeDao" />' alt="allowBlank:false'"/>
			<input type="hidden" id="colDictId"   name="colDictId" value="${kmTableColumnForm.colDictId}"/>	 
		</td>
		<td class="label">
			<fmt:message key="kmTableColumn.colType" />
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="colType" isQuery="false" alt="allowBlank:false,vtext:'请选择字段类型'"
				defaultId="${kmTableColumnForm.colType}" selectId="colType" beanId="selectXML" onchange="display()"/>
		</td>	
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableColumn.colSize" />
		</td>
		<td class="content">
			<html:text property="colSize" styleId="colSize" styleClass="text medium" alt="vtype:'number'" value="${kmTableColumnForm.colSize}" />
			<input type="hidden" name="oldColSize" value="${kmTableColumnForm.colSize}" />
		</td>
		<td class="label">
			<fmt:message key="kmTableColumn.colDefault" />
		</td>
		<td class="content">
			<html:text property="colDefault" styleId="colDefault" styleClass="text medium" alt="allowBlank:true,vtext:'请填写字段默认值'" value="${kmTableColumnForm.colDefault}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableColumn.orderBy" />
		</td>
		<td class="content">
			<html:text property="orderBy" styleId="orderBy" styleClass="text medium" alt="allowBlank:false,vtype:'number',vtext:'请填写排序值(数字)'" value="${kmTableColumnForm.orderBy}" />
		</td>
		<td class="label">
			<fmt:message key="kmTableColumn.isNullable" />
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" alt="allowBlank:false,vtext:'请选择是否为空'"
				defaultId="${kmTableColumnForm.isNullable}" selectId="isNullable" beanId="selectXML" />					
		</td>		
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmTableColumn.subNode" />
		</td>
		<td class="content" colspan="3">
		    <html:select property="subNode" styleId="subNode" styleClass="text medium">
		        <html:option value="">无关联字段</html:option>
                 <html:optionsCollection label="colChname" name="subColList" value="colName" />
            </html:select>		
		</td>		
	</tr>

    <input type="hidden" name="isOpen"    value="1" />
    <input type="hidden" name="isVisibl"  value="0" />
    <input type="hidden" name="isUnique"  value="0" />
    <input type="hidden" name="isIndex"   value="0" />
	<input type="hidden" name="isDeleted" value="0" />
	<input type="hidden" name="mark"      value="0" />

	<html:hidden property="id" value="${kmTableColumnForm.id}" />
	<html:hidden property="tableId" value="${kmTableColumnForm.tableId}" />
	<html:hidden property="nodeId" value="${nodeId}" />
	<html:hidden property="parentNode" value="${kmTableColumnForm.parentNode}" />
</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmTableColumnForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('<fmt:message key="message.delMessage"/>')){
						var url='${app}/kmmanager/kmTableColumns.do?method=remove&id=${kmTableColumnForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>