/**
 * <p>Description:流程实例图</p>
 * @author zhangxiaobo
 * @version 1.0.0
 * @include "/flowview/flowchar.js"
 */
 
function $(id){
    return document.getElementById(id);    
}

function Point2D(x,y){
    this.x = x;
    this.y = y;    
}

function Xml2Vml(xmlFile){
    this.XML = new ActiveXObject("MSXML2.DOMDocument.5.0");
    this.XML.async=false;
    this.XML.loadXML(xmlFile);
    this.SpanX = 10;
    this.SpanY = 20;
    this.ItemWidth = 100;
    this.ItemHeight = 54;
    this.VmlRoot = document.createElement('<v:group coordsize="1000,1000" style="width:1000px;height:1000px;">');
}

Xml2Vml.prototype.RecursiveParse = function(rootNode,offset){
    var allChild = rootNode.childNodes;
    var xOffset = offset.x;
    var yOffset = offset.y;
    if(allChild.length==0){
        rootNode.setAttribute("LeftX", xOffset);
        rootNode.setAttribute("RightX", xOffset+this.ItemWidth);
        xOffset += this.SpanX + this.ItemWidth;
    }
    else{
    	  //alert("allChild.length = "+allChild.length);
        for(var i=0;i<allChild.length;i++){
            var w = this.RecursiveParse(allChild[i],new Point2D(xOffset,yOffset+2*this.SpanY+this.ItemHeight));
            xOffset += this.SpanX + w;
        }
        rootNode.setAttribute("LeftX", (parseInt(allChild[0].getAttribute("LeftX")) + parseInt(allChild[0].getAttribute("RightX")))/2);
        rootNode.setAttribute("RightX",(parseInt(allChild[allChild.length-1].getAttribute("LeftX")) + parseInt(allChild[allChild.length-1].getAttribute("RightX")))/2);
    }
    rootNode.setAttribute("PosY", yOffset);
    return xOffset-offset.x-this.SpanX;
}

Xml2Vml.prototype.ParseXml = function(){
    this.RecursiveParse(this.XML.documentElement,new Point2D(0,0));    
}

Xml2Vml.prototype.RecursiveRender = function(xmlNode){
    var allChild = xmlNode.childNodes;
    var xl = xmlNode.getAttribute("LeftX");
    var xr = xmlNode.getAttribute("RightX");
    var x = (parseInt(xl)+parseInt(xr))/2;
    var y = xmlNode.getAttribute("PosY");
    
    //var taskName = xmlNode.tagName;
    var taskName = xmlNode.getAttribute("name");
    var taskStatus = xmlNode.getAttribute("status");
    
    var taskId = xmlNode.getAttribute("id");
    var currentLink = xmlNode.getAttribute("currentLink");
    var parentId = xmlNode.getAttribute("parentId");
    var parentLink = xmlNode.getAttribute("parentLink");
	      
    var fillColor = "#ff0033";
    
    if(xmlNode!=this.XML.documentElement){
    	  var SendLine = document.createElement('<v:line from="'+x+','+y+'" to="'+x+','+(parseInt(y)+this.SpanY)+'" />');
    	  var SendLineStroke = document.createElement('<v:stroke endarrow="classic" />');
    	  SendLine.appendChild(SendLineStroke);
        this.VmlRoot.appendChild(SendLine);
    }

    y=parseInt(y)+this.SpanY;

    if( taskStatus == 5){
    	fillColor = "#3366cc";
    }
    
    var RoundRect = document.createElement('<v:RoundRect style="width:'+this.ItemWidth+'px;height:'+this.ItemHeight+'px;left:'+(x-this.ItemWidth/2)+'px;top:'+y+'px" fillcolor="'+fillColor+'" align="center" onClick="showInfo(\''+taskId+'\',\''+currentLink+'\',\''+parentId+'\',\''+parentLink+'\')" onmouseover="upRect()" onmouseout="downRect()>')
    RoundRect.appendChild(document.createElement('<v:shadow on="True" type="single" color="#d7d7d7" offset="5px,4px"/>'));
    var TextBox = document.createElement('<v:TextBox style="font-size:9pt;color:white"/>');
    TextBox.innerHTML = taskName;
    RoundRect.appendChild(TextBox);
    this.VmlRoot.appendChild(RoundRect);
    
    if(allChild.length>0){
        y += this.ItemHeight+this.SpanY;

        //var operate = xmlNode.getAttribute("operate");
        //var OperateRect = document.createElement('<v:RoundRect style="width:0px;height:0px;left:'+(x-this.SpanX)+'px;top:'+(y-this.SpanY)+'px" align="center" />')
        //var OperateText = document.createElement('<v:TextBox style="font-size:9pt;color:#c0c0c0"/>');
        //OperateText.innerHTML = operate;
        //OperateRect.appendChild(OperateText);
        //this.VmlRoot.appendChild(OperateRect);
        
    	  var BackLine = document.createElement('<v:line from="'+x+','+(y-this.SpanY)+'" to="'+x+','+y+'" />');
    	  if(taskStatus == 5){
    	      var BackLineStroke = document.createElement('<v:stroke startarrow="classic" />');
    	      BackLine.appendChild(BackLineStroke);
    	  }
        this.VmlRoot.appendChild(BackLine);
        
        xl = (parseInt(allChild[0].getAttribute("LeftX")) + parseInt(allChild[0].getAttribute("RightX")))/2;
        xr = (parseInt(allChild[allChild.length-1].getAttribute("LeftX")) + parseInt(allChild[allChild.length-1].getAttribute("RightX")))/2;
        this.VmlRoot.appendChild(document.createElement('<v:line from="'+xl+','+y+'" to="'+xr+','+y+'" />'));

        for(var i=0;i<allChild.length;i++){ 
        	  this.RecursiveRender(allChild[i]);
        }
    }
}

Xml2Vml.prototype.RenderVml = function(){	
    this.RecursiveRender(this.XML.documentElement);
    var BorderLine = document.createElement('<v:line from="0,5" to="2000,5" strokecolor="#006699" strokeweight="1pt" />');
    BorderLine.appendChild(document.createElement('<v:Stroke startarrow="classic" endarrow="classic" />'));  
    this.VmlRoot.appendChild(BorderLine);    
}