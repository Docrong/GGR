var xmlDom=new ActiveXObject("MSXML2.DOMDocument");
xmlDom.async = false ;
xmlDom.validateOnParse = true ;
var xslDom=new ActiveXObject("MSXML2.DOMDocument");
xslDom.async = false ;
xslDom.validateOnParse = true ;
var subXML=new ActiveXObject("MSXML2.DOMDocument");
subXML.async = false ;
subXML.validateOnParse = true ;
var updateDoc = new ActiveXObject("MSXML2.DOMDocument");

var realPath=window.location.pathname;
if(realPath.substring(0,1)!="/") realPath="/"+realPath;
var rootPath=realPath.split("/")[1];
if(rootPath=='report' || rootPath=='filemanager' || rootPath=='template'){     //处理系统发布在根目录下的情况
   rootPath="";
}else{
   rootPath="/"+rootPath;
}

var imgPath=rootPath+"/images";
var imgplus=rootPath+"/images/img/plus.gif";
var imgminus=rootPath+"/images/img/minus.gif";
var imgleaf=rootPath+"/images/img/minusbottom.gif";
//var imgPath="/"+rootPath+"/images";
//var imgplus="/"+rootPath+"/images/img/plus.gif";
//var imgminus="/"+rootPath+"/images/img/minus.gif";
//var imgleaf="/"+rootPath+"/images/img/minusbottom.gif";
var rootX = new ActiveXObject("MSXML2.DOMDocument");
var rootA="<xTree treeImageBase='"+imgPath+"'></xTree>";
rootX.loadXML (rootA);
var name;//node's name
var memo;//node's memo
var url;//node's url
var order;//node's order
var oldsrc; //mouse action
var oldclass; //mouse action
var output;//reasult of transfrom
var reId;//return node's Id
var receiveRight;//whether receive xml is correct
//Receive Url of xml/xsl file & Transform XML & Return Html
function reportParseError(errObj)//格式化解析错误
{
	var s = "";
	for (var i=1;i<errObj.linepos;i++)
	{
		s += " ";
	}
	var r = "<font face=Verdana size=2><font size=4>XML Error loading " + errObj.url + "</font>" + "<P><B>" + errObj.reason +	"</B></P></font>";
	if (errObj.line > 0)
	{
		r += "<font size=3><XMP>" + "at line " +errObj.line + ",character " + errObj.linepos + "\n" + errObj.srcText + "\n" + s + "^" + "</XMP></font>";
	}
	return r;
}

function reportRuntimeError(errObj)//格式化运行错误
{
	var r = "<font face=Verdana size=2><font size=4>XSL Runtime Error " + "<P><B>" + errObj.description + "</B></P></font>";
	return r;
}
function Transfrom(urlXml,urlXsl,allExp)
{
	if (urlXml)
	{
	if (!xmlDom.load (urlXml))
		{
			var output = reportParseError(xmlDom.parseError);
		}
	else if (!xslDom.load (urlXsl))
		{
			var output = reportParseError(xslDom.parseError);
		}
		try
		{
			var clNode = xmlDom.documentElement.cloneNode(true);
			rootX.documentElement.appendChild (clNode);
			if (allExp)
			{
				rootX.documentElement.setAttribute("allExp",allExp);
			}
			var output=rootX.transformNode(xslDom);
			rootX.documentElement.removeChild(clNode);
		}
		catch(exception)
		{
			var output=reportRuntimeError(exception);
		}
	}
	else
	{
		try
		{
			if (!xslDom.load (urlXsl))
			{
				var output = reportParseError(xslDom.parseError);
			}
			var clNode = xmlDom.documentElement.cloneNode(true);
			rootX.documentElement.appendChild (clNode);
			if (allExp)
			{
				rootX.documentElement.setAttribute("allExp",allExp);
			}
			var output=rootX.transformNode(xslDom);
			rootX.documentElement.removeChild(clNode);
		}
		catch(exception)
		{
			var output=reportRuntimeError(exception);
		}
	}
	return output;
}

//Transform XML of Node & Return HTML of Node
function TranNode(nodeId,urlXsl,allExp)
{
	var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (!node)
	{
		output = "未找到节点"
	}
	else if (!xslDom.load (urlXsl))
	{
		output = reportParseError(XSLRoot.parseError);
	}
	else
		{
			try
			{
				var clNode = node.cloneNode(true);
				rootX.firstChild.appendChild (clNode);
				if (allExp)
				{
					rootX.firstChild.setAttribute("allExp",allExp);
				}
				output=rootX.transformNode(xslDom);
				rootX.firstChild.removeChild(clNode);
			}
			catch(exception)
			{
				output=reportRuntimeError(exception);
			}
		}
	return output;
}

//Transform XML of Node & Return HTML of Node
function fnTransNode(node,xslDom,allExp)
{
			try
			{
				var clNode = node.cloneNode(true);
				rootX.firstChild.appendChild (clNode);
				if (allExp)
				{
					rootX.firstChild.setAttribute("allExp",allExp);
				}
				output=rootX.transformNode(xslDom);
				rootX.firstChild.removeChild(clNode);
			}
			catch(exception)
			{
				output=reportRuntimeError(exception);
			}
	return output;
}

//Updata HTML after XMLDom has been changed
function Updata(Id,nodeId,urlXsl,imgPath)
{
	var eSrc=document.all[Id];
		if (eSrc)
		{
			var uSrc=eSrc.parentElement;
			for (var i=0;i<uSrc.childNodes.length ;i++ )
			{
				if (uSrc.childNodes.item(i).id==eSrc.id)
					break;
			}
			if (uSrc.id=="Root")
			{
				var lSrc=uSrc.parentElement;
				html=TranNode(nodeId,urlXsl);
				lSrc.innerHTML="<BR>"+html;
				uSrc=lSrc.lastChild;
				//uSrc.outerHTML=html;
				if (uSrc.tagName.toUpperCase()=="UL")
				{
					uSrc.firstChild.lastChild.style.display="block";
					uSrc.firstChild.state="open";
					uSrc.firstChild.firstChild.src=imgminus;
				}
				else if (uSrc.tagName.toUpperCase()=="LI")
				{
					uSrc.lastChild.style.display="block";
					uSrc.state="open";
					uSrc.firstChild.src=imgminus;
				}

				//uSrc.lastChild.style.display="block";
				//uSrc.firstChild.src=imgPath;
			}
			else if (uSrc.tagName.toUpperCase()=="UL")
			{
				html=TranNode(nodeId,urlXsl);
				uSrc.childNodes.item(i).outerHTML=html;
				uSrc.childNodes.item(i).state="open";
				uSrc.childNodes.item(i).lastChild.style.display="block";
				uSrc.childNodes.item(i).firstChild.src=imgPath;
			}
		}
}

//Spread Node && Display its childNode
function SpreadNode(Id,imgPath)
{
	var hasNoChild=false;
	var eSrc=document.all[Id];
		if (eSrc)
		{
			var xtreeId=eSrc.id;
			var uSrc=eSrc.lastChild;
			if (uSrc.tagName.toUpperCase()=="DIV")
			{
                if (uSrc.innerHTML!="")
				{
					uSrc.style.display="block";
					eSrc.state="open";
					//eSrc.firstChild.src=imgPath;
                    if(eSrc.nodeType!='root')
                        eSrc.lastChild.previousSibling.previousSibling.previousSibling.src=imgPath;
                }
				else
				{
//					eSrc.firstChild.src=imgleaf;
                    //eSrc.lastChild.previousSibling.previousSibling.previousSibling.src=imgleaf;
                    hasNoChild=true;
				}
			}
		}
	return hasNoChild;
}


//Close Node && Display its childNode
function CloseNode(Id,imgPath)
{
	var eSrc=document.all[Id];
		if (eSrc)
		{
			var xtreeId=eSrc.id;
			var uSrc=eSrc.lastChild;
			if (uSrc.tagName.toUpperCase()=="DIV")
			{
				if (uSrc.innerHTML!="")
				{
					uSrc.style.display="none";
					eSrc.state="closed";
                    if(eSrc.nodeType!='root')
                        eSrc.lastChild.previousSibling.previousSibling.previousSibling.src=imgPath;
					//eSrc.firstChild.src=imgPath;
				}
//				else
//				{
//					eSrc.firstChild.src=imgleaf;
//				}
			}
		}
}

//Add New Node & Return the Node
function AddNode(nodeId,nodeText,nodeMemo,nodeUrl,nodeOrder,xmlDoc)
{
	var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (node.getAttribute("nodeType")!="root")
	{
		node.setAttribute("nodeType","node");
	}
	if (!xmlDoc)
	{
		var newNode = xmlDom.createNode(1, "node", "");
		newNode.setAttribute("nodeType","leaf");
		if (nodeText)
			newNode.setAttribute("name",nodeText);
		if (nodeMemo)
			newNode.setAttribute("memo",nodeMemo);
		if (nodeUrl)
			newNode.setAttribute("Url",nodeUrl);
		if (nodeOrder)
		{
			//newNode.setAttribute("order",nodeOrder);
			var Insert=node.insertBefore (newNode,node.childNodes.item(nodeOrder));
			for (var i=0;i<node.childNodes.length ;i++ )
			{
				node.childNodes.item(i).setAttribute("order",i+1);
			}
		}
		else
		{
			node.appendChild(newNode);
		}
			return true;
	}
	else if (xmlDoc)
	{
		if (nodeOrder)
		{
			//newNode.setAttribute("order",nodeOrder);
			var Insert=node.insertBefore (xmlDoc,node.childNodes.item(nodeOrder));
			for (var i=0;i<node.childNodes.length ;i++ )
			{
				node.childNodes.item(i).setAttribute("order",i+1);
			}
		}
		else
		{
			node.appendChild(xmlDoc);
		}
		return true;
	}
	else
		return -1;
}

//Other Special Attribute
function SpecAtt(nodeId,attName,attText,operStr)
{
	var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (node)
	{
		switch (operStr)
		{
			case "add":
				node.setAttribute(attName,attText);
				break;
			case "mod":
				node.setAttribute(attName,attText);
				break;
			case "dis":
				var att=node.getAttribute(attName);
				return att;
				break;
		}
	}
}

//Delete Node
function DelNode(nodeId)
{
	var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (node)
	{
		var parNode=node.parentNode;
		if (parNode.removeChild(node))
			return 1;
		else
			return -1;
	}
	else
		return -1;
}

//Modify Node's Attributes
function ModNode(nodeId,nodeText,nodeMemo,nodeUrl,nodeOrder)
{
	if (xmlDom)
	{
		var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");

		if (nodeText)
			node.setAttribute("name",nodeText);
		if (nodeMemo)
			node.setAttribute("memo",nodeMemo);
		if (nodeUrl)
			node.setAttribute("Url",nodeUrl);
		if (nodeOrder!=null)
		{

			var Insert=pnode.insertBefore (node,pnode.childNodes.item(nodeOrder));
			for (var i=0;i<pnode.childNodes.length ;i++ )
			{
				pnode.childNodes.item(i).setAttribute("order",i+1);
			}
			//node.setAttribute("order",nodeOrder);
		}
		return node;
	}
	else
		return -1;
}

//Move Node
function MovNode(nodeId,tNodeId)
{
	var currNode=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	var tarNode=xmlDom.selectSingleNode("//*[@ID='"+tNodeId+"']");
	if (currNode && tarNode)
	{
		tarNode.setAttribute("nodeType","node");
		var tarLevel=tarNode.getAttribute("level");
		if (tarLevel)
		{
			currNode.setAttribute("level",parseInt(tarLevel)+1);
		};

		try
		{
			tarNode.appendChild(currNode);
		}
		catch(exception)
		{
			alert(exception.description);
			/*var output=reportRuntimeError(exception);
			alert(output);*/
			return -1;
		}
		for (var i=0;i<currNode.parentNode.childNodes.length ;i++ )
		{
			currNode.parentNode.childNodes.item(i).setAttribute("order",i+1);
		}
		for (var j=0;j<tarNode.childNodes.length ;j++ )
		{
			tarNode.childNodes.item(j).setAttribute("order",j+1);
		}
		return true;
	}
	else
		return -1;
}

//Copy Node
function copyNode(nodeId,tNodeId)
{
	var currNode=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	var tarNode=xmlDom.selectSingleNode("//*[@ID='"+tNodeId+"']");
	if (currNode && tarNode)
	{
		//var samNode=tarNode.selectSingleNode("*[@name='"+currNode.getAttribute("name")+"']");
		//if (!samNode)
		//{
			var copyNode = currNode.cloneNode(true);
			tarNode.appendChild(copyNode);
			return copyNode;
		//}
		//else
			//return -1;
	}
	else
		return -1;
}

//Display Node's Attributes
function  DiaplayNode(nodeId)
{
	var node=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (node)
	{
		//Id=nodeId;
		name=node.getAttribute("name");
		memo=node.getAttribute("memo");
		url=node.getAttribute("Url");
		order=node.getAttribute("order");
		level=node.getAttribute("level");
		topicFolder=node.getAttribute("topicFolder");
		folder=node.getAttribute("folder");
	}
	else
		return -1;
}


//Load SubTree & Return ParentNode
function LoadSubtree(subNode,nodeId)
{
	//if (subUrl)
	//{
	//	subXML.load(subUrl);
	//}
	var pNode=xmlDom.selectSingleNode("//*[@ID='"+nodeId+"']");
	if (pNode)
	{
		pNode.appendChild(subNode);
		return pNode;
	}
	else
		return -1;
}

//Change Cursor or not
function ChangeCursor()
{
	return true;
}

//Mouse over
function over(src)
{
	oldclass=src.className;
	src.className="clsMouseOver";
	if (ChangeCursor())
		src.style.cursor="hand";
	else
		src.style.cursor="default";
}

//Mouse Down
function down(src)
{
	if (oldsrc)
	{
		oldsrc.className="";
	}
	oldsrc=src;
	src.className="clsMouseDown";
	oldclass="clsCurrentHasFocus";
}

//Mouse Out
function out(src)
{
	src.className=oldclass;
}

//Select a Node
function NodeInfo(eSrc)
{
	if (eSrc.parentElement.url)
	{
		parent.doc.location.replace(eSrc.parentElement.url);
	}
}

//whether Select node and Open it at same time
function SameTime()
{
	return true;
}

function ExpendNode(Id,nId)
{
	var nodeState=document.all[Id].state;
	if (nodeState=="closed")
	{
		var hasNoChild=SpreadNode(Id,imgminus);
		if (hasNoChild)
		{
			var thenode=xmlDom.selectSingleNode("//*[@ID='"+nId+"']");
			if (thenode)
			{
				thenode.setAttribute("nodeType","leaf");
			}
		}
	}
	else if (nodeState=="open")
		CloseNode(Id,imgplus);
	else if (nodeState=="load")
	{
		loadtree(Id,nId);
	}

}

//Refresh the Tree
function RefreshTree()
{
	window.location.reload(true);
}

//When Node State is "Load"
function loadtree()
{
	return true;
}
//Receive Operation String  as URL
function ReceiveUrl(urlStr)
{
	GetOrder(urlStr);
	if (receiveRight)
	{
		return (temp)
	} else 	{
		return false;
	}
}
//Get xml from server
function GetOrder (urlStr)
  {
     xmlHttp = new ActiveXObject ("MSXML2.XMLHTTP");
     xmlHttp.Open("GET",urlStr, false);
     xmlHttp.setRequestHeader("Content-type:","text/xml");
     xmlHttp.setRequestHeader("Depth","1");
     //xmlHttp.onreadystatechange = FinishSave;
      xmlHttp.Send();
     FinishSave();
  }


//Receive Operation String as XML
function ReceiveStr(operStr)
{
	updateDoc.loadXML(operStr);
	PostOrder(updateDoc,xmlManage);
	if (receiveRight)
	{
		return (temp)
	}
	else
	{

		return false;
	}
}

//Post xml to server
function PostOrder (xmldoc,xmlManage)
{    //alert(xmlManage);
     xmlHttp = new ActiveXObject ("MSXML2.XMLHTTP");
     xmlHttp.Open("POST",xmlManage, false);
     xmlHttp.setRequestHeader("Content-type:","text/xml");
     xmlHttp.setRequestHeader("Depth","1");
     //xmlHttp.onreadystatechange = FinishSave;
      xmlHttp.Send(xmldoc);
      FinishSave();
  }

//Receive xml from ASP
function FinishSave()
{
	if (xmlHttp!=null && xmlHttp.readyState == 4 )
	{
		temp=new ActiveXObject("MSXML2.DOMDocument");
		temp.async = false ;
		temp.validateOnParse = true ;
		if (xmlHttp.status != 200)
		{
			var returnXml= "<return><returnInfo result='0' errInfo='xmlhttp状态" + xmlHttp.status + " " + xmlHttp.statusText.replace(/\\'/g, "\'") + "'/><treeInfo/></return>"
		}
		else
		{
			temp.load(xmlHttp.responseXML);
			if(temp.xml=="")
			{
			var returnXml=xmlHttp.responseText;
				temp.loadXML(returnXml);
			}
			xmlHttp=null;
		}
		reXml(temp);//返回temp
	}
}

//Judge the Return Xml
function reXml(temp)
{
	return true;
}
function reportParseError(errObj)//格式化解析错误
{
	var s = "";
	for (var i=1;i<errObj.linepos;i++)
	{
		s += " ";
	}
	var r = "<font face=Verdana size=2><font size=4>XML Error loading " + errObj.url + "</font>" + "<P><B>" + errObj.reason +	"</B></P></font>";
	if (errObj.line > 0)
	{
		r += "<font size=3><XMP>" + "at line " +errObj.line + ",character " + errObj.linepos + "\n" + errObj.srcText + "\n" + s + "^" + "</XMP></font>";
	}
	return r;
}

function reportRuntimeError(errObj)//格式化运行错误
{
	var r = "<font face=Verdana size=2><font size=4>XSL Runtime Error " + "<P><B>" + errObj.description + "</B></P></font>";
	return r;
}