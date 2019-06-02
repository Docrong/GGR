<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <script src="switchStyle.js"></script>
    <script src="shared.js"></script>
    <script src="pngfix.js"></script>
    <script>
      <!--

function openHelp(button) {
  window.open("help.html","help","scrollbars=yes,resizable=yes");
  button.blur();
  return false;
}
      
var ow;
function openOptions(el) {
  if (!top.ow || top.ow.closed)
    top.ow = open("options.html","ow"+makeWindowName(parent.jid),"width=380,height=380,resizable=yes");
  el.blur();
  top.ow.focus();
  return false;
}

function addUser() {
  top.openSubscription();
  return false;
}

// function searchUser() {
//   top.openSearch();
// 	return false;
// }

var groupw;
function openGroupchat() {
  if (!groupw || groupw.closed)
    groupw = open("joingroupchat.html","joingroupw"+makeWindowName(parent.jid),"width=330,height=250,resizable=yes");
  groupw.focus();
  return false;
}

var onlStatW;
function openChangeStatus() {
  if (!onlStatW || onlStatW.closed)
    onlStatW = open("changestatus.html","onlStatW"+makeWindowName(parent.jid),"width=330,height=240,resizable=yes");
  onlStatW.focus();
  return false;
}

function selStatusMessage(el) {
  if (!el.selected)
    el.className = 'myStatusMsgSelected';
  else
    el.className = 'myStatusMsg';
  el.selected = !el.selected;
  return true;
}

function setStatusMessage() {
  var el = document.getElementById('statusMsg');
  if (!el.selected)
    return true;
  selStatusMessage(el);
  top.changeStatus(top.onlstat,el.value);
  el.blur();
  return true;
}

function keyPressed(e) {
  if (e.ctrlKey && e.keyCode == 74)
    parent.Debug.start();
  if (e.keyCode != 13)
    return;
  var firingObj = (top.is.ie)?event.srcElement:e.target;
  if (firingObj.id != 'statusMsg')
    return;
  return setStatusMessage();
}      

function handleClick(e) {
  var firingObj = (top.is.ie)?e.srcElement:e.target;
  if (firingObj.id == 'statusMsg' && !firingObj.selected)
    return selStatusMessage(firingObj);
  else
    return setStatusMessage();
}      

var adduser = new Image();
adduser.src = "images/adduser.png";
var adduserOver = new Image();
adduserOver.src = "images/adduser_over.png";
// var searchuser = new Image();
// searchuser.src = "images/searchuser.png";
// var searchuserOver = new Image();
// searchuserOver.src = "images/searchuser_over.png";
var groupchat = new Image();
groupchat.src = "images/groupchat.png";
var groupchatOver = new Image();
groupchatOver.src = "images/groupchat_over.png";

function changePic(oldEl,newEl) {
  if (oldEl.tagName == "SPAN") // from pngfix.js
    oldEl.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+newEl.src+", sizingMethod='scale')";
  else
    oldEl.src = newEl.src;
  oldEl.style.cursor = 'pointer';
}

function cleanUp() { //close dependant windows
  if (ow && !ow.closed)
    ow.close();
  if(groupw && !groupw.closed)
    groupw.close();
  if (onlStatW && !onlStatW.closed)
    onlStatW.close();
}

onclick = handleClick;
onunload = cleanUp;
onkeydown = keyPressed;
      //-->
    </script>
    <script for="document" event="onclick()" language="JScript">
    if (window.event)
      handleClick(window.event);
    </script>

    <script for="document" event="onkeydown()" language="JScript">
    if (window.event)
      keyPressed(window.event);
    </script>
  </head>

  <body>
<table height="100%" width="100%" border=0 cellspacing=0 cellpadding=0>

<!-- the menubar -->
<tr><td class="menubar"><a href="#" class="menubar" onClick="return openOptions(this);">Preferencias</a></td><td align="right" class="menubar"><a href="#" class="menubar" onClick="return openHelp(this);">Ayuda</a></td></tr>

<!-- me myself and my microphone -->
<tr><td colspan=2 class="rosterInnerElement" style="padding-bottom:0px;">
            <img id="statusLed" name="statusLed" src="images/unavailable.gif" align="left" title="Cambiar presencia" onClick="return openChangeStatus();"><div><span id="myNickname" class="nickName" onClick='return top.openUserInfo(top.cutResource(top.jid))' title="Cambiar informaci&oacute;n de usuario"></span><br>
            <input type="text" id="statusMsg" title="Cambiar mensaje de estado" class="myStatusMsg"></div>
          </td></tr>

<!-- the roster himself -->
<tr><td height="100%" colspan=2 class="rosterInnerElement"><iframe src="iRoster.html" name="iRoster" id="iRoster" scrolling="auto" style="width:100%;height:100%;border:2px groove;" frameborder=0></iframe></td></tr>

<!-- buttonbar -->
<tr><td colspan=2 class="rosterInnerElement"><img class="actionButton" src="images/adduser.png" onMouseOver="changePic(this,adduserOver);" onMouseOut="changePic(this,adduser);" align="left" onClick="addUser();" title="A&ntilde;adir contacto"><!--img class="actionButton" src="images/searchuser.png" onMouseOver="changePic(this,searchuserOver);" onMouseOut="changePic(this,searchuser);" align="left" onClick="searchUser();" title="Buscar usuario"--><img class="actionButton" src="images/groupchat.png" onMouseOver="changePic(this,groupchatOver);" onMouseOut="changePic(this,groupchat);" onClick="openGroupchat();" title="Entrar en una sala de charla"></td></tr>

</table>
  </body>
</html>
