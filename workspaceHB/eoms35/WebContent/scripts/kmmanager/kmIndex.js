function subLongString(str,maxLength)
{
	var temp = str.replace(/[\W]/g,'##');
	if(temp.length>maxLength){
		var result;
		var chineseArray = temp.substring(0,maxLength).match(/#/g);
		if(chineseArray==null) result = str.substring(0,maxLength-1)+"..";
		else result = str.substring(0,maxLength-1-chineseArray.length/2)+"..";
		return result;
	}
	return str;
}

function switchTab(url, str, num)
{
	for(var i=1;i<=5;i++)
	{
		document.getElementById(str+"Order"+i).style.display="none";//600
		document.getElementById(str+"Tab"+i).className="orderTabDefault";
	}
	showOrder(url, str, num);
	document.getElementById(str+"order"+num).style.display="";
	document.getElementById(str+"tab"+num).className="orderTabOver";
}
function out(obj)
{
	if(obj.className!="orderTabOver")obj.className="orderTabDefault";
}

var currentPicture = 0;
var maxStep = 0;
var flag = true;
function scanExpert()
{
	if(flag){
		var pictures = document.getElementById("pictureContainer").getElementsByTagName("span");
		var length = pictures.length;
		var clientWidth = document.getElementById("pictureList").clientWidth;
		var appendCount= clientWidth-(length-1)*70;
		if(appendCount>0)
		{
			var appendCount = parseInt(appendCount/70+1);
			var container = document.getElementById("pictureContainer");
			try{
				for(var i=0;i<appendCount;i++)
				{
					var copyObj = container.childNodes[i%length].cloneNode(true); 
					container.appendChild(copyObj);
				}
			}catch(e){return;}
			length = pictures.length;
			container.style.width=70*length+70;
		}
		maxStep += 1;
		for(var i=0;i<length;i++)
		{
			try{
				pictures[i].style.left = parseInt(pictures[i].style.left)-1;
			}catch(e){
				pictures[i].style.left = -1;
			}
			if(maxStep>=70){
				pictures[currentPicture].style.left=(length-1-currentPicture)*70;
				if(currentPicture>=length-1)
					currentPicture = 0;
				else
					currentPicture++;
				maxStep = 0;
			}
		}
	}
	window.setTimeout(scanExpert,30);
}
function scanExpertOver()
{
	flag = false;
}
function scanExpertOut()
{
	flag = true;
}



//初始化页面内容

function on_off(e){
	e=e.parentNode.parentNode;
	var open="dir_item_open";
	e.className="dir_item_"+(e.className==open?"close":"open");
} 

function en(s){
  return encodeURI(encodeURI(s));
}

function sb(){
    var m = document.getElementById('actionName').value;
    var w = document.getElementById('actionValue').value;
    //alert(m);
    //alert(w);
    window.location.href = 'http://10.218.7.20:8888/search/' + m + '?q='+en(w);
}
function createRequest()
{
	var httpRequest = null;
	if(window.XMLHttpRequest){
     httpRequest=new XMLHttpRequest();
    }else if(window.ActiveXObject){
     httpRequest=new ActiveXObject("MIcrosoft.XMLHttp");
    }
    return httpRequest;
}

function showUser(url)
{
	var url = url + "/kmmanager/kmIndexs.do?method=getUserInfo";
	var httpRequest = createRequest();
	if(httpRequest){
	     httpRequest.open("POST",url,true);
	     httpRequest.onreadystatechange=function()
	     {
	     	if(httpRequest.readyState==4)
		     	if(httpRequest.status==200){
		     		var json = eval(httpRequest.responseText);
		     		document.getElementById("knowledgeScore").innerHTML="知识积分："+json[0].knowledgeScore;
		     		document.getElementById("expertScore").innerHTML="专家积分："+json[0].expertScore;
		     		document.getElementById("expertPhoto").src="${app}/images/head/"+json[0].expertPhoto;
				}	
	     }
	     httpRequest.send(null);
	}
}
function showOnDutyExperts(url)
{
	var url = url + "/kmmanager/kmIndexs.do?method=listOnDutyExperts";
	var httpRequest = createRequest();
	if(httpRequest){
	     httpRequest.open("POST",url,true);
	     httpRequest.onreadystatechange=function()
	     {
	     	if(httpRequest.readyState==4)
		     	if(httpRequest.status==200){
		     		var json = eval(httpRequest.responseText);
		     		var container = document.getElementById("pictureContainer");
		     		var expertName;
		     		for(var i=0;i<json.length;i++){
		     				var newSpan = document.createElement("span");
		     				newSpan.className = "picture";
		     				expertName = subLongString(json[i].userName,8);
		     				newSpan.innerHTML = '<img src="kmIndexImg/'+json[i].photo+'" width=60 height=60 /><div title='+json[i].userName+'>'+expertName+'</div>';
			     			container.appendChild(newSpan);
		     		}
		     		scanExpert();
				}	
	     }
	     httpRequest.send(null);
	}
}
function showOrder(url, str, num)
{
	if(document.getElementById(str+"Order"+num).innerHTML!="") return;
	
	var method = "knowledgeTableAmountOrder";
	if(str=="readcount") method="knowledgeReadCountOrder";
	else if(str=="score") method="userKnowledgeScoreOrder"; 
	else if(str=="contribute") method="userKnowledgeContributeOrder";

	var timeType = ['','','YEAR','QUARTER','MONTH','WEEK'];
	
	var url = url + "/kmmanager/kmIndexs.do?method="+method+"&time="+timeType[num]+"&orderNumber=5";
 	var httpRequest = createRequest();
	if(httpRequest){
	     httpRequest.open("POST",url,true);
	     httpRequest.onreadystatechange=function()
	     {
	     	if(httpRequest.readyState==4)
		     	if(httpRequest.status==200){
		     		var json = eval(httpRequest.responseText);
		     		for(var i=0;i<json.length;i++)
		     			document.getElementById(str+"Order"+num).innerHTML += '<div class="orderContentDetailItem"><div class="orderContentDetailItemNum">0'
		     			+(i+1)+'</div><div title="'+json[i].user+'" style="cursor:pointer">'+subLongString(json[i].user,14)+'</div><div class="orderContentDetailItemScore">'+json[i].score+'</div></div>';//
				}	
	     }
	     httpRequest.send(null);
	}
}

function showContent(url, type)//type为1则是知识，为2则是文档
{
	var method = "listKmContents";
	if(type==2) method = "listKmFiles";
	
	var url = url + "/kmmanager/kmIndexs.do?method="+method+"&count=10";
 	var httpRequest = createRequest();
	if(httpRequest){
	     httpRequest.open("POST",url,true);
	     httpRequest.onreadystatechange=function()
	     {
	     	if(httpRequest.readyState==4)
		     	if(httpRequest.status==200){
		     		var json = eval(httpRequest.responseText);
		     		if(type==1)
			     		for(var i=0;i<json.length;i++)
			     			document.getElementById("dataDetailContent").innerHTML += '<div class="dataDetailContentItem"><span class="span1">'+json[i].tableName+'</span><span class="span2">'+json[i].contentTitle+'</span><span class="span3">'+json[i].createTime+'</span><span class="span4">'+json[i].userName+'</span></div>';//
			     	else
			     		for(var i=0;i<json.length;i++)
			     			document.getElementById("dataDetailFile").innerHTML += '<div class="dataDetailContentItem"><span class="span1">'+json[i].nodeName+'</span><span class="span2">'+json[i].fileName+'</span><span class="span3">'+json[i].uploadTime+'</span><span class="span4">'+json[i].userName+'</span></div>';//
				}	
	     }
	     httpRequest.send(null);
	}
}

function showNavigationTree(url, type)//type为1则是知识管理，为2则是文件管理，为3则是课程管理
{
	var method = "listKmContentTree";
	if(type==2) method = "listKmFileTree";
	else if(type==3) method = "listKmLessonTree";
	
	var url = url + "/kmmanager/kmIndexs.do?method="+method+"&count=10";
 	var httpRequest = createRequest();
	if(httpRequest){
	     httpRequest.open("POST",url,true);
	     httpRequest.onreadystatechange=function()
	     {
	     	if(httpRequest.readyState==4)
		     	if(httpRequest.status==200){
		     		var json = eval(httpRequest.responseText);
		     		if(type==1)
			     		for(var i=0;i<json.length;i++)
			     			document.getElementById("contextTree").innerHTML += '<div class="firstLevel" title="'+json[i].text+'">'+subLongString(json[i].text,22)+'</div>';//
			     	else if(type==2)
			     		for(var i=0;i<json.length;i++)
			     			document.getElementById("fileTree").innerHTML += '<div class="firstLevel" title="'+json[i].text+'">'+subLongString(json[i].text,22)+'</div>';//
			     	else 
			     		for(var i=0;i<json.length;i++)
			     			document.getElementById("lessonTree").innerHTML += '<div class="firstLevel" title="'+json[i].text+'">'+subLongString(json[i].text,22)+'</div>';//
				}	
	     }
	     httpRequest.send(null);
	 }
}