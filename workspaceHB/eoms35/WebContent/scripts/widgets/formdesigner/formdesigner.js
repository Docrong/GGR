 
var steerer = "<table name='imgtable' class='imgtable'>" +
    "<tr><td></td><td><img src='"+eoms.appPath+"/scripts/widgets/formdesigner/assets/up.gif'onclick='moveUporDown(this,0);'/></td><td></td></tr>" +
    "<tr><td><img src='"+eoms.appPath+"/scripts/widgets/formdesigner/assets/left.gif' onclick=\"move(this,'r');\"/></td>" +
        "<td><img src='"+eoms.appPath+"/scripts/widgets/formdesigner/assets/down.gif' onclick='moveUporDown(this,1);'/></td>" +
        "<td><img src='"+eoms.appPath+"/scripts/widgets/formdesigner/assets/right.gif' onclick=\"move(this,'l');\"/></td>" +
    "</tr></table>"; 
    
var AdjustTable = eoms.$('adjustTable');

//页面元素的调整 
function adjust(){
    //使得每次更新后 tr的序号都是按升序排列 并且td的序号和tr的序号一致     
    var twoColumns = true; 
    Ext.each(AdjustTable.rows, function(row, i){
        row.id = "Tr"+i;
        var labels = Ext.fly(row).query("td.label");
        var contns = Ext.fly(row).query("td.content");
        if(row.cells.length==2){
            labels[0].id = 'Label'+i;
            contns[0].id = 'Content'+i;
            setMark(contns[0],"full");
        }else if(row.cells.length==4){
            labels[0].id = 'lLabel'+i;
            labels[1].id = 'rLabel'+i;
            contns[0].id = 'lContent'+i;
            contns[1].id = 'rContent'+i;
            if(contns[0].innerHTML=="" &&　contns[1].innerHTML!=""){
                setMark(contns[0], "blank");
                setMark(contns[1], "right");
            }
            else if (contns[0].innerHTML!="" &&　contns[1].innerHTML==""){
                setMark(contns[0], "left");
                setMark(contns[1], "blank");
            }
            else{
                setMark(contns[0], "half");
                setMark(contns[1], "half");
            }
            twoColumns = false;
        }
    });
    
    //在IE下 当页面不存在半行的row时，会出现BUG 整个table的样式会发生变化 以下代码为解决这一问题 就是添加一个含有半行的空tr
    if(Ext.isIE && twoColumns){
        Ext.DomHelper.append(AdjustTable,{tag:'tr',html:'<td></td><td></td><td></td><td></td>'});
    }
}


//根据触发事件的图标获取该图标所在的td
function getTd(tmpNode){
    return tmpNode.parentNode.parentNode.parentNode.parentNode.parentNode;
}

/**
 * 获取role属性作为mark的值
 * @param {HTML DOM} el
 * @return {Number} 默认为0
 */
function getMark(el){
    return (el['role'] || el.getAttribute('role')) || 0;
}

/**
 * 设置role属性
 * @param {HTML DOM} el
 */
function setMark(el,type){
    var role = {half:0,full:1,left:2,right:3,blank:4};
    if(el.innerHTML=="") type = "blank";
    el.role = role[type];
    el.setAttribute("role",role[type]);
}

//判断是左面半行还是右面半行
function getLorR(str){
    return str.substring(0,1);
}
//对换
function exChange(a,b){
    var tmp =a.innerHTML;
    a.innerHTML = b.innerHTML;
    b.innerHTML = tmp;
}

/**
 * 交换两个节点的位置
 * @param {HTML DOM} node1
 * @param {HTML DOM} node2
 */
function swapNode(node1,node2){
    //获取父结点
    var _parent = node1.parentNode;
    
    //获取两个结点的相对位置
    var _t1 = node1.nextSibling;
    var _t2 = node2.nextSibling;
 
    //node2插入到原来node1的位置
    if(_t1)_parent.insertBefore(node2,_t1);
    else _parent.appendChild(node2);

    //node1插入到原来node2的位置
    if(_t2)_parent.insertBefore(node1,_t2);
    else _parent.appendChild(node1);
}

//向上或向下移动
function moveUporDown(node,type){   
    var nowTr = getTd(node).parentNode;
    var changeTr;
    //向上
    if (type===0){
        changeTr = Ext.fly(nowTr).getPrevSibling();
    }
    //向下
    else if(type===1){
        changeTr = Ext.fly(nowTr).getNextSibling();
    }
    if(!changeTr){
        //alert("can not move now!it is the edge!");
    }else{
        //上下类型相同
        if(nowTr.cells.length==changeTr.cells.length && nowTr.cells.length==4){
            var cur_labels = $(nowTr).select("td[class='label']");
            var cur_contns = $(nowTr).select("td[class='content']");
            var tar_labels = $(changeTr).select("td[class='label']");
            var tar_contns = $(changeTr).select("td[class='content']");
            //当前节点在左侧
            if(getLorR(getTd(node).id)=='l'){
                exChange(cur_labels[0],tar_labels[0]);
                exChange(cur_contns[0],tar_contns[0]);
                setMark(cur_contns[0],cur_contns[1].innerHTML=="" ? "left" : "half");
                setMark(tar_contns[0],tar_contns[1].innerHTML=="" ? "left" : "half");
            }
            //当前节点在右侧
            else if(getLorR(getTd(node).id)=='r'){
                exChange(cur_labels[1],tar_labels[1]);   
                exChange(cur_contns[1],tar_contns[1]);
                setMark(cur_contns[1],cur_contns[0].innerHTML=="" ? "right" : "half");
                setMark(tar_contns[1],tar_contns[0].innerHTML=="" ? "right" : "half");   
            }
            if(cur_contns[0].innerHTML=="" && cur_contns[1].innerHTML=="") AdjustTable.deleteRow(nowTr.rowIndex);
            if(tar_contns[0].innerHTML=="" && tar_contns[1].innerHTML=="") AdjustTable.deleteRow(changeTr.rowIndex);
        }
        //上下类型不同
        else{
            //交换两行
            swapNode($(nowTr),$(changeTr));
        }       
    }
    adjust();
}

/**
 * 向左右移动
 * @param {HTML DOM} node
 * @param {String} dir 方向
 */
function move(node, dir){
    var nowTr = getTd(node).parentNode;
    if(nowTr.cells.length==4){
        if(getLorR(getTd(node).id)==dir){
            var labels = Ext.fly(nowTr).query("td.label");
            var contns = Ext.fly(nowTr).query("td.content");
            exChange(labels[0],labels[1]);
            exChange(contns[0],contns[1]);
            if(contns[1].innerHTML=="") setMark(contns[0],"left");
            if(contns[0].innerHTML=="") setMark(contns[1],"right");
        }
    }
}   

//整行与半行的切换功能
function changeLineType(node){
    var curLabelCell = Ext.fly(node).findParent("td.label");
    var curContnCell = Ext.fly(curLabelCell).getNextSibling();
    var nowTr = curLabelCell.parentNode;
    var pos = nowTr.rowIndex;
    //节点是半行节点转整行
    if(nowTr.cells.length==4){
        //节点是左侧节点
        if(getLorR(curLabelCell.id)=='l'){
            var newTr = $("adjustTable").insertRow(pos);
            Ext.DomHelper.append(newTr,{tag:'td',cls:'label',html:curLabelCell.innerHTML});
            Ext.DomHelper.append(newTr,{tag:'td',cls:'content',colspan:'3', role:'1', html:curContnCell.innerHTML});
                
            //右面有节点 右面的向下移到下一行左侧,右面弄成空的
            if(hasInfo($(nowTr).select("td[class='content']")[1])){
                exChange($(nowTr).select("td[class='label']")[0],$(nowTr).select("td[class='label']")[1]);
                exChange($(nowTr).select("td[class='content']")[0],$(nowTr).select("td[class='content']")[1]);
                $(nowTr).select("td[class='label']")[1].innerHTML = steerer;
                $(nowTr).select("td[class='content']")[1].innerHTML = '';
            }else{
              //节点右面无节点 删除原来的那一行
               $("adjustTable").deleteRow(pos+1);
            }                           
        }else if(getLorR(curLabelCell.id)=='r'){
        //节点是右侧节点
            var newTr = $("adjustTable").insertRow(pos+1);
            Ext.DomHelper.append(newTr,{tag:'td',cls:'label',html:curLabelCell.innerHTML});
            Ext.DomHelper.append(newTr,{tag:'td',cls:'content',colspan:'3', role:'1', html:curContnCell.innerHTML});
            
            //左面有节点 将原来的节点变成空的  
            if(hasInfo($(nowTr).select("td[class='content']")[0])){
                $(nowTr).select("td[class='label']")[1].innerHTML = steerer;
                $(nowTr).select("td[class='content']")[1].innerHTML = '';
            }//左面没节点 删除原来的行
            else{
                $("adjustTable").deleteRow(pos);
            }
        }
    }//节点是整行变成半行的情况 新增2个半行 将原来的内容左侧半行内，右面的半行为空填充 然后删除原来的行 
    else if(nowTr.cells.length==2){
        curContnCell.setAttribute("colSpan", 1);
        curContnCell.colSpan = 1;
        Ext.DomHelper.append(nowTr,{tag:'td',cls:'label',html:steerer});
        Ext.DomHelper.append(nowTr,{tag:'td',cls:'content', role:'4'});
    }
    adjust();
}

//判断congtent的td中是否为空
function hasInfo(tdNode){
    var hasInfo = false; 
    if(tdNode.childNodes.length>0){     
        for(var i=0;i<tdNode.childNodes.length;i++){
            if(tdNode.childNodes[i].id){
                hasInfo = true;
            }   
        }   
    }
    return hasInfo;
}

//保存页面设置结果到隐藏域result 提交到后台
// 使用td[class='content']上的role属性存储字段的布局类型(mark)，0为两个字段各占半行，1为一个字段占整行，3为一个字段占前半行，4为一个字段占后半行
function getResult(){
    var pos =1;
    var result = "[";
    var resultNodes = new Array();
    
    var tdNodes = $("adjustTable").select("td[class='content']");       
    for(var k=0;k<tdNodes.length;k++){              
        for(var i=0;i<tdNodes[k].childNodes.length;i++){
            if(tdNodes[k].childNodes[i].id){
                resultNodes.push(tdNodes[k].childNodes[i]);
                break;
            }   
        }
    }
    
    var mark = 0;
    for(var m=0;m<resultNodes.length;m++){
        var tdInfo = resultNodes[m].id;
        if(tdInfo.substring(0,19)=="UIFrame1-TableInfo/"){
            tdInfo = tdInfo.substring(19,tdInfo.length);
        }
        mark = getMark(resultNodes[m].parentNode);
        result += '{id:\''+tdInfo+'\',pos:\''+(m+1)+'\',mark:\''+mark+'\'}';
            
        if(m<resultNodes.length-1){
            result+=',';            
        }   
    }
    result+="]"
    $('result').value = result;
    eoms.log(result);
    return result;
       
}

function save(){
    var result = getResult();
    $("adjustForm").action = $("adjustForm").action+"&result="+result;
    $("adjustForm").submit();   
}

//在一行中 两个td中都没有内容时 删除这一行 主要是在整行半行切换时会新增出 许多空td 当2个空td在一行时去掉
function killBlank(){
    return;
}

//预览功能 根据button的value值来切换和预览状态
function preView(){
    if($("preview").value=="预览"){
        $("preview").value = "编辑";
        var imageTables = $("adjustTable").select("table[name ='imgtable']");
        Ext.each(imageTables,function(item){
            item.style.display = "none";        
        });
        var btns = $("adjustTable").select("input[name ='changeBtn']");
        Ext.each(btns,function(item){
            item.style.display = "none";        
        });
    }else if($("preview").value=="编辑"){
        $("preview").value = "预览";
        var imageTables = $("adjustTable").select("table[name ='imgtable']");
        Ext.each(imageTables,function(item){
            item.style.display = "";        
        });
        var btns = $("adjustTable").select("input[name ='changeBtn']");
        Ext.each(btns,function(item){
            item.style.display = "";        
        });
    }
}

adjust();  