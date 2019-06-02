<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>JWChat</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <script type="text/javascript" src="config.js"></script>
    <script type="text/javascript" src="shared.js"></script>
    <script type="text/javascript" src="browsercheck.js"></script>
    <script type="text/javascript" src="sounds.js"></script>
    <script type="text/javascript" src="statusLed.js"></script>

<!-- JabberConnection -->
    <script type="text/javascript" src="jsjac.js"></script>

<!-- Debugger -->
    <script type="text/javascript" src="Debugger.js"></script>

    <script type="text/javascript">
<!--

 /* ***
  * JWChat, a web based jabber client
  * Copyright (C) 2003-2008 Stefan Strigler <steve@zeank.in-berlin.de>
  *
  * This program is free software; you can redistribute it and/or
  * modify it under the terms of the GNU General Public License
  * as published by the Free Software Foundation; either version 2
  * of the License, or (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program; if not, write to the Free Software
  * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
  *
  * Please visit http://jwchat.sourceforge.net for more information!
  */

/************************************************************************
 *                       ******  GLOBAL WARS(tm) *******
 ************************************************************************
 */
var jid;
var pass;
var register = false; // whether to register new account
var nick;
var vcard;
var status = '';
var onlstat = '';
var onlmsg = '';
var onlprio = '8';
var autoPopup = true;
var autoPopupAway = false;
var playSounds = true;
var focusWindows = true;
var timestamps = false;
var usersHidden = false;
var enableLog = false;
var loghost;

/* some globals */
var roster;
var fmd; // frames.main.document
var disco; // holds information from service discovery

var statusLed;
var statusMsg;

var onlstatus = new Object();
onlstatus["available"] = "online";
onlstatus["chat"] = "свободен для чата";
onlstatus["away"] = "отсутствует";
onlstatus["xa"] = "не доступен";
onlstatus["dnd"] = "не беспокоить";
onlstatus["invisible"] = "невидимый";
onlstatus["unavailable"] = "отключен";


/************************************************************************
 *                         ****** Pop-Up's *******
 ************************************************************************
 */

function openGroupchat(aJid,nick,pass) {
  pass = pass || '';
  nick = nick || '';

  var user = roster.getUserByJID(aJid);
  try {
  if(!user) {
    user = roster.addUser(new RosterUser(aJid,'',["Комнаты чата"],aJid.substring(0,aJid.indexOf('@'))));
    user.type = 'groupchat';
    roster.print();
  }
  } catch(e) { Debug.log(e.message, 1); }
  if (!user.chatW || user.chatW.closed)
    user.chatW = open("groupchat.html?jid="+escape(aJid)+"&nick="+escape(nick)+"&pass="+escape(pass),"gchatW"+makeWindowName(aJid+"/"+nick),"width=520,height=390,resizable=yes");
  user.chatW.focus();
}

var subw;
function openSubscription(aJid) {
  var param = (aJid) ? "?jid="+escape(aJid) : "";
  subw = open("subscription.html"+param,"sub","width=320,height=240,resizable=yes");
  subw.focus();
  return false;
}

function openCustomPresence(aJid) {
  var user = roster.getUserByJID(aJid);
  if (!user)
    return;
  if (!user.onlStatW || user.onlStatW.closed)
    user.onlStatW = open("changestatus.html?jid="+escape(aJid),"onlStatW","width=330,height=240,resizable=yes");
  user.onlStatW.focus();
  return false;
}

function sendCustomPresence(aJid,presence,msg) {
  var oPresence = new JSJaCPresence();
  oPresence.setTo(aJid);
  if (roster.getUserByJID(aJid).roster)
    oPresence.setXMLNS();

  switch (presence) {
  case 'offline':
    oPresence.setType('unavailable');
  case 'unavailable':
    oPresence.setType('unavailable');
    presence = "invisible";
  default:
    if (presence != 'available')
      oPresence.setShow(presence);
  }
  
  if (typeof(msg) != 'undefined' && msg != '')
    oPresence.setStatus(msg);
  
  Debug.log(oPresence.xml(),2);
  con.send(oPresence);
}

function openUserProps(aJid) {
  open("userprops.html?jid="+escape(aJid),"uProps"+makeWindowName(aJid),"width=480,height=360,resizable=yes");
  return false;
}

function removeUser(aJid) {
  // get fulljid
  var fulljid = roster.getUserByJID(aJid).fulljid;

  var iq = new JSJaCIQ();
  iq.setType('set');
  var query = iq.setQuery('jabber:iq:roster');
  var item = query.appendChild(iq.getDoc().createElement('item'));
  item.setAttribute('jid',fulljid);
  item.setAttribute('subscription','remove');

  con.send(iq);
}

var vcardW; // my vcardW;
function openUserInfo(aJid) {
  var newin = open("vcard.html?jid="+escape(aJid),"vcardW"+makeWindowName(aJid),"width=400,height=580,scrollbars=yes");

  if (cutResource(aJid) == cutResource(jid))
    vcardW = newin;
  else {
    var user = roster.getUserByJID(cutResource(aJid));
    if (!user) {
      user = new RosterUser(aJid);
      roster.addUser(user);
    }
    if (user.roster) // groupchat(!)
      user = user.roster.getUserByJID(aJid);
    user.vcardW = newin;
  }

  return false;
}

function openUserHistory(aJid) {

  if (typeof(loghost) == 'undefined' || loghost == '')
    return;

  var user = roster.getUserByJID(aJid);

  if (user == null)
    return;

  if (!user.histW || user.histW.closed)
    user.histW = open("userhist.html?jid="+escape(aJid),"histW"+makeWindowName(aJid),"width=600,height=400,resizable=yes,scrollbars=no");
  user.histW.focus();
}

function openUserNote(aJid) { /* store annotations to a user */
  var user = roster.getUserByJID(aJid);
  
  if (user == null)
    return; // unbelievable
  
  if (!user.noteW || user.noteW.closed)
    user.noteW = open("usernote.html?jid="+escape(aJid),"noteW"+makeWindowName(aJid),"width=300,height=200,resizable=yes,scrollbars=no");
  user.noteW.focus();
}

var searchW;
function openSearch() {
  if (!searchW || searchW.closed)
    searchW = open("search.html","searchW"+makeWindowName(jid),"width=480,height=260,resizable=yes,scrollbars=yes");
  searchW.focus();
  return false;
}

var ebW
function openEditBookmarks() {
  if (!ebW || ebW.closed)
    ebW = open("editbookmarks.html","ebw"+makeWindowName(jid),"width=330,height=290,resizable=yes");
  return false;
}


/************************************************************************
 * nifty helpers - always there if you need 'em
 ************************************************************************
 */

/* command line history */
var messageHistory = new Array();
var historyIndex = 0;
function getHistory(key, message) {
  if ((key == "up") && (historyIndex > 0)) historyIndex--;
  if ((key == "down") && (historyIndex < messageHistory.length)) historyIndex++;
  if (historyIndex >= messageHistory.length) {
    if (historyIndex == messageHistory.length) return '';
    return message;
  } else {
    return messageHistory[historyIndex];
  }
}

function addtoHistory(message) {
  if (is.ie5)
    messageHistory = messageHistory.concat(message);
  else
    messageHistory.push(message);
  historyIndex = messageHistory.length;
}

/* system sounds */
var soundPlaying = false;
function soundLoaded() {
	soundPlaying = false;
}

function playSound(action) {
  if (!playSounds)
    return;

  if(!SOUNDS[action]) {
    Debug.log("no sound for '" + action + "'",1);
    return;
  }

  if (onlstat != '' && onlstat != 'available' && onlstat != 'chat')
    return;
  
  if (soundPlaying)
    return;
  
  soundPlaying = true;
	
  var frameD = frames["jwc_sound"].document;

  var html = "<embed src=\""+SOUNDS[action]+"\" width=\"1\" height=\"1\" quality=\"high\" pluginspage=\"http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash\" type=\"application/x-shockwave-flash\">";
  frameD.open();
  frameD.write(html);
  frameD.close();
}


function isGateway(aJid) {
  aJid = cutResource(aJid);
  for (var i in disco) {
    if (!disco[i].getNode) continue;
    if (i == aJid)
      if (disco[i].getNode().getElementsByTagName('identity').item(0)) {
	if (disco[i].getNode().getElementsByTagName('identity').item(0).getAttribute('category') == 'gateway')
	  return true;
      }
  }
  return false;
}

/************************************************************************
 *                       ******  CHANGESTATUS   *******
 ************************************************************************
 */

function sendPresence2Groupchats(gc,val,away) {
  var aPresence;
  for (var i=0; i<gc.length; i++) {
    aPresence = new JSJaCPresence();
    //aPresence.setXMLNS();
    aPresence.setTo(gc[i]);
    if (away && away != '')
      aPresence.setStatus(away);
    if (val != 'available')
      aPresence.setShow(val);
    con.send(aPresence);
  }
}

function changeStatus(val,away,prio) {
  
  Debug.log("changeStatus: "+val+","+away+","+prio, 2);

  onlstat = val;
  if (away)
    onlmsg = away;
  
  if (prio && !isNaN(prio))
    onlprio = prio;
  
  if (!con.connected() && val != 'offline') {
    init();
    return;
  }
  
  var aPresence = new JSJaCPresence();
  
  switch(val) {
  case "unavailable":
    val = "invisible";
      aPresence.setType('invisible');
    break;
  case "offline":
    val = "unavailable";
    aPresence.setType('unavailable');
    con.send(aPresence);
    con.disconnect();
    var img = eval(val+"Led");
    statusLed.src = img.src;
    if (away)
      statusMsg.value = away;
    else
      statusMsg.value = onlstatus[val];
    cleanUp();
    return;
    break;
  case "available":
    val = 'available'; // needed for led in status bar
    if (away)
      aPresence.setStatus(away);
    if (prio && !isNaN(prio))
      aPresence.setPriority(prio);
    else
      aPresence.setPriority(onlprio);			
    break;
  case "chat":
    if (prio && !isNaN(prio))
      aPresence.setPriority(prio);
    else
      aPresence.setPriority(onlprio);			
  default:
    if (away)
      aPresence.setStatus(away);
    
    if (prio && !isNaN(prio))
      aPresence.setPriority(prio);
    else
      aPresence.setPriority('0');
    
    aPresence.setShow(val);
  }
  
  con.send(aPresence);
  
  /* send presence to chatrooms
   */
  if (typeof(roster) != 'undefined' && onlstat != 'invisible') {
    sendPresence2Groupchats(roster.getGroupchats(),onlstat,onlmsg);
  }

  var img = eval(val+"Led");
  statusLed.src = img.src;
  if (away)
    statusMsg.value = away;
  else
    statusMsg.value = onlstatus[val];
}

/************************************************************************
 *                   ***** EVENT - HANDLER *****
 ************************************************************************
 */


/************************************************************************
 * handleMessage
 ************************************************************************
 */
function handleMessage(oMsg) {

  Debug.log(oMsg.xml(),2);

  if (oMsg.getType() == 'error')
    return;
  
  /* check if this is a groupchat invite */
  var x = oMsg.getChild('x','http://jabber.org/protocol/muc#user');
  
  if (x) {
    var from, to, reason, pass;
    to = oMsg.getFrom();
    var aInvite = x.getElementsByTagName('invite').item(0);
    from = aInvite.getAttribute('from');
    if (aInvite.firstChild && aInvite.firstChild.nodeName == 'reason' && aInvite.firstChild.firstChild)
      reason = aInvite.firstChild.firstChild.nodeValue;
    if (x.getElementsByTagName('password').item(0))
      pass = x.getElementsByTagName('password').item(0).firstChild.nodeValue;
    Debug.log("You have been invited to " + jid + " pass " + pass + " by " + from + "\nreason:" + reason,2);
    var user = roster.getUserByJID(cutResource(from));
    if (!user) {// users not in roster (yet)
      Debug.log("creating new user "+from,3);
      user = roster.addUser(new RosterUser(cutResource(from)));
      user.lastsrc = eval(user.status + "Led").src;
      roster.print();
    }
    
    if (typeof(user.iwArr) == 'undefined')
      user.iwArr = new Array();
    
    user.iwArr[to] = open("groupchat_invite.html?to="+escape(to)+"&from="+escape(from)+"&pass="+escape(pass)+"&reason="+escape(reason),"iw"+makeWindowName(to),"width=320,height=320,resizable=yes");
    
    return;
  }
  
  var from = cutResource(oMsg.getFrom());
  var type = oMsg.getType();
  Debug.log("from: "+from+"\noMsg.getFrom(): "+oMsg.getFrom(),3);

  var user = roster.getUserByJID(from);
  if (user == null) {// users not in roster (yet)
    Debug.log("creating new user "+from,3);
    user = roster.addUser(new RosterUser(from));
    user.lastsrc = eval(user.status + "Led").src;
    roster.print();
  }

  Debug.log("got user jid: "+user.jid,3);

  var aRoster = roster;
  if (type != 'groupchat' && user.roster && from != oMsg.getFrom()) { // private groupchat message
    aRoster = user.roster;
    from = oMsg.getFrom(); // use from with resource (had been cut off first)
    user = user.roster.getUserByJID(from);
  }

  /* change icon in roster - but not if it's a groupchat item */
  if (type != 'groupchat') {
    if (!user.lastsrc)
      user.lastsrc = eval(user.status + "Led").src;
      
    var images = aRoster.getUserIcons(from);
    for (var i=0; i<images.length; i++)
      images[i].src = messageImg.src;

    /* user is not visible right now - make him pop up (lastsrc changed!) */
    if (aRoster.usersHidden && user.status == 'unavailable')
      aRoster.print();
  }
  
  // set current timestamp
  var x;
  for (var i=0; i<oMsg.getNode().getElementsByTagName('x').length; i++)
    if (oMsg.getNode().getElementsByTagName('x').item(i).getAttribute('xmlns') == 'jabber:x:delay') {
      x = oMsg.getNode().getElementsByTagName('x').item(i);
      break;
    }
  
  if (x) {
    Debug.log("found offline message: "+x.getAttribute('stamp'),3);
    var stamp = x.getAttribute('stamp');
    oMsg.jwcTimestamp = new Date(Date.UTC(stamp.substring(0,4),stamp.substring(4,6)-1,stamp.substring(6,8),stamp.substring(9,11),stamp.substring(12,14),stamp.substring(15,17)));
  } else
    oMsg.jwcTimestamp = new Date();
  
  if (type == 'chat') {
    
    user.chatmsgs = user.chatmsgs.concat(oMsg);
    
    if (user.chatW && !user.chatW.closed && user.chatW.popMsgs) {
      user.chatW.popMsgs();
      playSound('chat_recv');
    } else if (autoPopup && (autoPopupAway || onlstat == "available" || onlstat == "chat")) {
      aRoster.openChat(from);
      playSound('chat_recv');
    } else {
      if (focusWindows) window.focus();
      playSound('chat_queue');

      // let arrow blink for toggled groups
      for (var i=0; i<user.groups.length; i++) {
	if (user.groups[i] != '') {
	  if (roster.hiddenGroups[user.groups[i]])
	    fmd.images[user.groups[i]+"Img"].src = arrow_right_blinking.src;
	}					
      }
    }

  } else if (type == 'groupchat') {

    /* handle groupchat message 
     */

    user.chatmsgs = user.chatmsgs.concat(oMsg);
    if (user.chatW && !user.chatW.closed && user.chatW.srcW && typeof(user.chatW.srcW.roster) != 'undefined' && user.chatW.popMsgs) {
      user.chatW.popMsgs();
    } 
    
    playSound('chat_recv');
    
  } else {
    if (oMsg.getBody() == '') // don't show empty messages like PEP message or stuff
      return;
    user.messages = user.messages.concat(oMsg);
    if (autoPopup && (autoPopupAway || onlstat == "available" || onlstat == "chat") && (!user.mW || user.mW.closed)) {
      aRoster.openMessage(from);
      playSound('message_recv');
    } else if (user.mW && !user.mW.closed && user.messages.length > 0 && user.mW.document.forms[0]) {
      user.mW.document.forms[0].nextButton.disabled = false;
      if (focusWindows) user.mW.focus();
      playSound('message_recv');
    }	else {
      if (focusWindows) window.focus();
      playSound('message_queue');
      // let arrow blink for toggled groups
      for (var i=0; i<user.groups.length; i++) {
	if (user.groups[i] != '') {
	  if (roster.hiddenGroups[user.groups[i]])
	    fmd.images[user.groups[i]+"Img"].src = arrow_right_blinking.src;
	}					
      }
      
    }

    // [TODO] zeank 2005-10-26
    // archiving of single/plain messages
  }
}

/************************************************************************
 * handleMessageError
 ************************************************************************
 */
var error_messages = new Array();
var errorW;
function handleMessageError(oMsg) {

  if (oMsg.getType() != 'error')
    return;
  
  Debug.log(oMsg.xml(),2);
  
  var user = roster.getUserByJID(cutResource(oMsg.getFrom()));
  
  if (user.chatW && !user.chatW.closed && user.chatW.putMsgHTML) {
    var error = oMsg.getNode().getElementsByTagName('error').item(0);
    if (error) {
      if (error.getElementsByTagName('text').item(0)) {
	user.chatW.putMsgHTML(oMsg);
	playSound('error');
	return;
      }
    }
  }
  
  error_messages = error_messages.concat(oMsg);
  
  if (!errorW || errorW.closed)
    errorW = open("error_message.html","errorW"+makeWindowName(jid),"width=360,height=270,dependent=yes,resizable=yes");
  else if (error_messages.length > 0 && errorW.document.forms[0])
    errorW.document.forms[0].nextButton.disabled = false;
  
  playSound('error');
  
  errorW.focus();
}

/************************************************************************
 * handlePresence
 ************************************************************************
 */

function handlePresence(presence) {
  Debug.log(presence.xml(),2);

  var from = cutResource(presence.getFrom());
  var type = presence.getType();
  var show = presence.getShow();
  var status = presence.getStatus();

  var aRoster = roster;

  // roster subscriptions synchronisation
  var x;
  if (isGateway(from.substring(from.indexOf('@')+1))) {
    var x = null;
    if (presence.getChild('x', 'http://jabber.org/protocol/roster-subsync'))
      x = presence.getChild('x', 'http://jabber.org/protocol/roster-subsync');
    else if (presence.getChild('x', 'http://delx.cjb.net/protocol/roster-subsync'))
      x = presence.getChild('x', 'http://delx.cjb.net/protocol/roster-subsync');
    
    if (x) {

      Debug.log("detected roster-subsync presence",2);

      var items = x.getElementsByTagName("item");
      for (var i=0; i<items.length; i++) {
	var aItem = items.item(i);
	if (type == 'subscribe' && aItem.getAttribute("subscription") == 'both') {
	  // insert into roster
	  var aIQ = new JSJaCIQ();
	  aIQ.setType('set');
	  var query = aIQ.setQuery('jabber:iq:roster');
	  var bItem = query.appendChild(aIQ.getDoc().createElement('item'));
	  bItem.setAttribute('jid',from);
	  if (aItem.getAttribute('name') && aItem.getAttribute('name') != '')
	    bItem.setAttribute('name',aItem.getAttribute('name'));
	  else 
	    bItem.setAttribute('name',from.substring(0,from.lastIndexOf('@')).replace(/%/,'@'));
	  var itemGroups = aItem.getElementsByTagName("group");
	  for (var j=0; j<itemGroups.length; j++)
	    bItem.appendChild(itemGroups.item(j));
	  
	  Debug.log("roster-subsync setting roster:"+aIQ.xml(),2);
	  
	  con.send(aIQ);
	  
	  // Approve Subscription Request
	  var aPresence = new JSJaCPresence();
	  aPresence.setTo(from);
	  aPresence.setType('subscribed');
	  con.send(aPresence);
	  
	  // Subscribe to gateway contact's presence
	  var bPresence = new JSJaCPresence();
	  bPresence.setTo(from);
	  bPresence.setType('subscribe');
	  con.send(bPresence);	  
	}
      }
      return;
    }
  }
  
  switch (type) {
  case null:
  case '':
    break;
  case 'subscribe':
    if (isGateway(from)) { // automatically subscribe gateways
      
      // Approve Subscription Request
      var aPresence = new JSJaCPresence();
      aPresence.setTo(presence.getFrom());
      aPresence.setType('subscribed');
      con.send(aPresence);
      
      // Subscribe to Gateway's Presence
      var bPresence = new JSJaCPresence();
      bPresence.setTo(presence.getFrom());
      bPresence.setType('subscribe');
      con.send(bPresence);
    }	else {
      if (status)
	window.open("subscriptionRequest.html?jid="+escape(from)+"&msg="+escape(status),"sr"+makeWindowName(from),"width=320,height=240");
      else
	window.open("subscriptionRequest.html?jid="+escape(from),"sr"+makeWindowName(from),"width=320,height=240");
    }
    return;
  case 'unsubscribe':
    if (!isGateway(from))
      alert("Вы отписались от "+presence.getFrom()); /* [TODO] don't use alert here */
    break;
  case 'error':
    var user = roster.getUserByJID(from);
    if (user && user.chatW && !user.chatW.closed && user.chatW.putMsgHTML) {
      if (presence.getNode().getElementsByTagName('error').item(0)) {
	var error = presence.getNode().getElementsByTagName('error').item(0);
	if (error.getElementsByTagName('text').item(0))
	  user.chatW.putMsgHTML(presence);
	else if (error.firstChild && error.firstChild.nodeValue)
	  user.chatW.putMsgHTML(error.firstChild.nodeValue,new Date(),from,null,true);								
      }
      
    }
  }
  
  var user = roster.getUserByJID(from);
  if (!user) { // presence from unsubscribed user
    Debug.log("presence from "+from+" not found on roster", 2);
    return;
  }
  
  /* handle presence for MUC */
  x = null; // reset
  for (var i=0; i<presence.getNode().getElementsByTagName('x').length; i++)
    if (presence.getNode().getElementsByTagName('x').item(i).getAttribute('xmlns') == 'http://jabber.org/protocol/muc#user') {
      x = presence.getNode().getElementsByTagName('x').item(i);
      break;
    }
  
  if (user.roster && x) {
    Debug.log("muc presence detected", 2);
    var ofrom = presence.getFrom().substring(presence.getFrom().indexOf('/')+1);
    
    Debug.log("jabber.from:"+presence.getFrom()+", ofrom:"+ofrom,3);
    
    var ouser = user.roster.getUserByJID(presence.getFrom());
    if (!ouser) // no user? create one!
      ouser = new GroupchatRosterUser(presence.getFrom(),ofrom);
    
    var item = x.getElementsByTagName('item').item(0);
    
    ouser.affiliation = item.getAttribute('affiliation');
    ouser.role = item.getAttribute('role');
    ouser.nick = item.getAttribute('nick');
    ouser.realjid = item.getAttribute('jid');
    if (item.getElementsByTagName('reason').item(0))
      ouser.reason = item.getElementsByTagName('reason').item(0).firstChild.nodeValue;
    if (actor = item.getElementsByTagName('actor').item(0)) {
      if (actor.getAttribute('jid') != null)
	ouser.actor = actor.getAttribute('jid');
      else if (item.getElementsByTagName('actor').item(0).firstChild != null)
				ouser.actor = item.getElementsByTagName('actor').item(0).firstChild.nodeValue;
    }
    if (ouser.role != '') {
      ouser.add2Group(ouser.role+'s');
      
      /* check if it is our own presence
       * must be done here cause we want to be sure that role != ''
       */
      
      if (ouser.name == htmlEnc(user.roster.nick)) { // seems to be me
	user.roster.me = ouser; // store this reference
	if (user.chatW.updateMe)
	  user.chatW.updateMe();
      }
    }
    
    Debug.log("ouser.jid: "+ ouser.jid + ", ouser.fulljid:" + ouser.fulljid + ", ouser.name:"+ouser.name+", user.roster.nick:"+user.roster.nick,3);
    
    
    var nickChanged = false;
    if (x.getElementsByTagName('status').item(0)) {
      var code = x.getElementsByTagName('status').item(0).getAttribute('code');
      switch (code) {
      case '201': // room created
	/* popup dialog to ask for whether to accept default
	 * configuration or make a custom room 
	 */
	if (confirm("Новая комната была создана, но её ещё требуется настроить. Хотите настроить её сейчас?\nПримечание: нажмите 'Отмена' чтобы использовать настройки по умолчанию"))
	  user.chatW.openConfig();
	else {
	  var iq = new JSJaCIQ();
	  iq.setType('set');
	  iq.setTo(user.jid);
	  var query = iq.setQuery('http://jabber.org/protocol/muc#owner');
	  query.appendChild(
		iq.buildNode('x', {'xmlns': NS_XDATA, 'type': 'submit'}));
	  
	  con.send(iq);
	}
	break;
      case '303': // nick change
	// display message
	if (!ouser.nick)
	  return;
	
	var oMsg = new JSJaCMessage();
	oMsg.setFrom(user.jid);
	oMsg.setBody("Пользователь "+ouser.name+" теперь известен как "+htmlEnc(ouser.nick));
	user.chatmsgs = user.chatmsgs.concat(oMsg);
	if (user.chatW && !user.chatW.closed && user.chatW.popMsgs)
	  user.chatW.popMsgs();
	
	// update nick if it's me
	if (ouser.name == htmlEnc(user.roster.nick))
	  user.roster.nick = ouser.nick;
	
	// remove old user
	var aChatW = ouser.chatW;
	user.roster.removeUser(ouser);
	
	// add new user
	ouser = new GroupchatRosterUser(presence.getFrom().substring(0,presence.getFrom().lastIndexOf('/')+1).concat(ouser.nick),ouser.nick);
	
	if (aChatW && !aChatW.closed) {
	  ouser.chatW = aChatW;
	  ouser.chatW.user = ouser;
	}
	user.roster.addUser(ouser);
	nickChanged = true;
	break;
      case '301': // user has been kicked
	var oMsg = new JSJaCMessage();
	oMsg.setFrom(user.jid);
	var body;
	if (ouser.actor)
	  body = ""+ouser.actor+" забанил(а) "+ouser.name;
	else
	  body = "Пользователя "+ouser.name+" забанили";
	if (ouser.reason)
	  body += ": " + ouser.reason;
	oMsg.setBody(body);
	user.chatmsgs = user.chatmsgs.concat(oMsg);
	if (user.chatW && !user.chatW.closed && user.chatW.popMsgs)
	  user.chatW.popMsgs();			
	
	playSound('chat_recv');
	break;
      case '307': // user has been kicked
	var oMsg = new JSJaCMessage();
	oMsg.setFrom(user.jid);
	var body;
	if (ouser.actor)
	  body = ""+ouser.actor+" выкинул(а) "+ouser.name;
	else
	  body = "Пользователя "+ouser.name+" выкинули";
	if (ouser.reason)
	  body += ": " + ouser.reason;
	oMsg.setBody(body);
	user.chatmsgs = user.chatmsgs.concat(oMsg);
	if (user.chatW && !user.chatW.closed && user.chatW.popMsgs)
	  user.chatW.popMsgs();	
	
	playSound('chat_recv');
	break;
      }
    }
    
    Debug.log("<"+ouser.name+"> affiliation:"+ouser.affiliation+", role:"+ouser.role,3);

    if (!user.roster.getUserByJID(presence.getFrom()) && !nickChanged) {
      // add user
      user.roster.addUser(ouser);
      
      // show join message
      var oMsg = new JSJaCMessage();
      oMsg.setFrom(user.jid);
      oMsg.setBody("Пользователь "+ouser.name+" вошёл в систему");
      user.chatmsgs = user.chatmsgs.concat(oMsg);
      if (user.chatW && !user.chatW.closed && user.chatW.popMsgs)
	user.chatW.popMsgs();			
      
      playSound('online');
      
    } else if (presence.getType() == 'unavailable' && !nickChanged) {
      // show part message
      var oMsg = new JSJaCMessage();
      oMsg.setFrom(user.jid);
      var body = "Пользователь "+ouser.name+" вышел";
      if (presence.getStatus())
	body += ": " + presence.getStatus();
      oMsg.setBody(body);
      user.chatmsgs = user.chatmsgs.concat(oMsg);
      if (user.chatW && !user.chatW.closed && user.chatW.popMsgs)
	user.chatW.popMsgs();			
      
      playSound('offline');
      
    } else
      user.roster.updateGroups();
    
    // relink roster and user
    aRoster = user.roster;
    user = ouser;
  } 

  if (show) {
    if (user.status == 'unavailable')
      playSound('online');
    // fix broken pressenc status
    if (show != 'chat' && show != 'away' && show != 'xa' && show != 'dnd')
      show = 'available';
    user.status = show;
  } else if (type) {
    if (type == 'unsubscribe') {
      user.subscription = 'from';
      user.status = 'stalker';
    } else if (user.status != 'stalker')
      user.status = 'unavailable';
    if (aRoster.name == 'GroupchatRoster' && !nickChanged) { // it's a groupchat roster
      // remove user
      if (!user.chatW || user.chatW.closed)
	aRoster.removeUser(user); // we don't need offline users in there
    }
    playSound('offline');
  } else {
    if (user.status == 'unavailable') // user was offline before
      playSound('online');
    user.status = 'available';
  }

  var img = eval(user.status+"Led");
  
  if (user.lastsrc) // message is pending
    user.lastsrc = img.src;
  
  // show away message
  if (status)
    user.statusMsg = status;
  else
    user.statusMsg = null;
  
  // update presence indicator of chat window
  if (user.chatW && !user.chatW.closed && user.chatW.updateUserPresence) 
    user.chatW.updateUserPresence();

  aRoster.print(); // update roster
}

/************************************************************************
 * handleIQSet
 ************************************************************************
 */

function handleIQSet(iq) {
  if (iq.getType() != "set") {
    Debug.log("not handling iq:\n"+iq.xml(),3);
    return;
  }
  
  Debug.log("got iq type 'set':\n"+iq.xml(),2);
  
  if (iq.getQueryXMLNS() != 'jabber:iq:roster') { // only handle roster items so far
    Debug.log("not handling iq:\n"+iq.xml(),1);
    return;
  }
  
  for (var i=0; i<iq.getQuery().childNodes.length; i++) {
    var item = iq.getQuery().childNodes.item(i);
    var user = roster.getUserByJID(cutResource(item.getAttribute('jid')));
    if (user) {
      user.subscription = item.getAttribute('subscription');
      if (item.getAttribute('subscription') == 'remove') {
	Debug.log("removing user " + user.jid,2);
        roster.removeUser(user);
      } else { // update user
        user.name = item.getAttribute('name')? htmlEnc(item.getAttribute('name')) : item.getAttribute('jid');
        user.groups = new Array('');
	for (var j=0; j<item.childNodes.length; j++)
	  if (item.childNodes.item(j).nodeName == 'group')
	    user.groups = user.groups.concat(item.childNodes.item(j).firstChild.nodeValue);
        roster.updateGroups();
      }
    } else {// got a new user
      if (isGateway(item.getAttribute('jid'))) { // auto add gateways
	// get name
	var name = cutResource(item.getAttribute('jid'));
	for (var i in disco) {
	  if (typeof(disco[i]) != 'object') continue;
	  if (i == cutResource(item.getAttribute('jid')))
	    name = disco[i].getQuery().getElementsByTagName('identity').item(0).getAttribute('name');
	}

	// add to roster
	var aUser = new RosterUser(cutResource(item.getAttribute('jid')),item.getAttribute('subscription'),["Gateways"],name);
	//aUser.fulljid = item.getAttribute('jid');
	roster.addUser(aUser);
	
	// set name and group
	var aIQ = new JSJaCIQ();
	aIQ.setType('set');
	var query = aIQ.setQuery('jabber:iq:roster');
	var aItem = query.appendChild(aIQ.getDoc().createElement('item'));
	aItem.setAttribute('jid',item.getAttribute('jid'));
	aItem.setAttribute('name',name);
	aItem.appendChild(iq.getDoc().createElement('group')).appendChild(iq.getDoc().createTextNode('Gateways'));
	
	con.send(aIQ);
      } else { // new but not a gateway
        var name = item.getAttribute('name')? item.getAttribute('name') : item.getAttribute('jid');
	if (name.indexOf('@') != -1)
	  name = name.substring(0,name.indexOf('@'));
	
	item.setAttribute('name',name);
        var groups = new Array('');
	for (var j=0; j<item.childNodes.length; j++)
	  if (item.childNodes.item(j).nodeName == 'group')
	    groups = groups.concat(item.childNodes.item(j).firstChild.nodeValue);
	
	roster.addUser(new RosterUser(cutResource(item.getAttribute('jid')),item.getAttribute('subscription'),groups,name));
	
	var aIQ = new JSJaCIQ();
	aIQ.setType('set');
	var query = aIQ.setQuery('jabber:iq:roster');
	
	var aItem = item.cloneNode(true);
	aItem.removeAttribute('subscription');
	query.appendChild(aItem);
	
	con.send(aIQ); // set stripped name
	
	if (item.getAttribute('subscription') == "from" && item.getAttribute('ask') != 'subscribe')
	  openSubscription(item.getAttribute('jid')); // subscribe to user
      }
    }
  }
  roster.print();
}

function handleConError(e) {
  switch (e.getAttribute('code')) {
  case '401':
    alert("Ошибка авторизации");
    if (!con.connected())
      window.close();
    break;
  case '409':
    alert("Регистрация не удалась!\n\nВыберите другое имя пользователя!");
    break;
  case '503':
    alert("Сервис недоступен");
    break;
  case '500':
    if (!con.connected() && !logoutCalled && onlstat != 'offline')
      if (confirm("Внутренняя ошибка сервера.\n\nСоединение разорвано.\n\nПодключиться снова?"))
	changeStatus(onlstat,onlmsg);
    break;
  default:
    alert("An Error Occured:\nCode: "+e.getAttribute('code')+"\nType: "+e.getAttribute('type')+"\nCondition: "+e.firstChild.nodeName); // this shouldn't happen :)
    break;
  }
}

function handleDisconnect() {
  if (logoutCalled || onlstat == 'offline')
    return;
  
  // disconnecting not with onunload handler triggered
  statusLed.src = unavailableLed.src; // offline icon
  statusMsg.value = '';
  
  fmd.getElementById('roster').innerHTML = '';
  
// 	if (confirm("Отключено.\n\nПодключиться вновь?"))
// 		changeStatus(onlstat,onlmsg);
}

function handleConnected() {

  Debug.log("Connected",0);
  
  if (register && opener && opener.document.forms[0] && opener.document.forms[0].register)
    opener.document.forms[0].register.checked = false; 
  
  /* get/setup roster */
  iq = new JSJaCIQ();
  iq.setIQ(null,'get','roster_1');
  iq.setQuery('jabber:iq:roster');
  con.send(iq,getRoster); // cascading information retrieval
}

/* *** cascading onconnect handlers *** */
function getRoster(iq) {
  if (!iq || iq.getType() != 'result') {
    if (iq)
      Debug.log("Error fetching roster:\n"+iq.xml(),1);
    else
      Debug.log("Error fetching roster",1);
    return;
  }
  
  Debug.log("got roster:\n"+iq.xml(),2);
  
  roster = new Roster(iq.getQuery().childNodes,fmd);
  roster.usersHidden = usersHidden;
  roster.nick = jid.substring(0,jid.indexOf('@')); // remember nick for 1:1 Chats
  
  // get saved state
  iq = new JSJaCIQ();
  iq.setIQ(null,'get','jwchat_state');
  var query = iq.setQuery('jabber:iq:private');
  query.appendChild(
	iq.buildNode('jwchat', {'xmlns': 'jwchat:state'}));

  con.send(iq,getSavedState);
}

function getSavedState(iq) {
  if (!iq || iq.getType() != 'result') {
    if (iq)
      Debug.log("Error retrieving saved state:\n"+iq.xml(),1);
    else
      Debug.log("Error retrieving saved state",1);
  
  } else {
    Debug.log(iq.xml(), 2);
    var jNode = iq.getNode().getElementsByTagName('jwchat').item(0);
    for (var i=0; i<jNode.childNodes.length; i++) {
      var item = jNode.childNodes.item(i);
	  try {
		switch (item.nodeName) {
		case 'presence': 
		  if (onlstat == '' && item.firstChild.nodeValue != 'offline')
			onlstat = item.firstChild.nodeValue;
		  break;
		case 'onlmsg': 
		  onlmsg = item.firstChild.nodeValue;
		  break;
		case 'hiddenGroups':
		  var hiddenGroups = item.firstChild.nodeValue.split(',');
		  for (var j=0; j<hiddenGroups.length; j++)
			if (hiddenGroups[j] != '')
			  roster.hiddenGroups[hiddenGroups[j]] = true;
		}
	  } catch(e) { Debug.log(e.toString(), 1); }
    }
  }
  
  // get prefs
  iq = new JSJaCIQ();
  iq.setIQ(null,'get','jwchat_prefs');
  var query = iq.setQuery('jabber:iq:private');
  query.appendChild(
	iq.buildNode('jwchat', {'xmlns': 'jwchat:prefs'}));
  
  con.send(iq,getPrefs);
}

function getPrefs(iq) {
  if (!iq || iq.getType() != 'result')
    if (iq)
      Debug.log("Error retrieving preferences:\n"+iq.xml(), 1);
    else
      Debug.log("Error retrieving preferences",1);
  
  if (iq && iq.getType() == 'result') {
    Debug.log(iq.xml() ,2);
    if (iq.getNode().getElementsByTagName('jwchat').item(0)) {
      var jNode = iq.getNode().getElementsByTagName('jwchat').item(0);
      for (var i=0; i<jNode.childNodes.length; i++) {
		try {
		  switch (jNode.childNodes.item(i).nodeName) {
	case 'usersHidden':
	  if (eval(jNode.childNodes.item(i).firstChild.nodeValue) != usersHidden)
	    roster.toggleHide();
	  break;
	case 'timerval':
	  timerval = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  con.setPollInterval(timerval);
	  break;
	case 'autoPopup':
	  autoPopup = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
	case 'autoPopupAway':
	  autoPopupAway = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
	case 'playSounds':
	  playSounds = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
	case 'focusWindows':
	  focusWindows = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
	case 'timestamps':
	  timestamps = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
	case 'enableLog':
	  enableLog = eval(jNode.childNodes.item(i).firstChild.nodeValue);
	  break;
		  }
		} catch(e) { Debug.log(e.toString(),1); }
      }
    }
  }
  
  // print roster
  roster.print();
  
  if (opener.prio)
    onlprio = opener.prio;
  else
    onlprio = DEFAULTPRIORITY;
  
  // send presence
  if (onlstat == '')
    onlstat = 'available';
  changeStatus(onlstat,onlmsg,onlprio);
  
  playSound('connected');
  
  // Start Service Discovery
  iq = new JSJaCIQ();
  iq.setIQ(con.domain,'get','disco_item_1');
  iq.setQuery('http://jabber.org/protocol/disco#items');
  
  con.send(iq,getDiscoItems);
  
  // get bookmarks
  iq = new JSJaCIQ();
  iq.setIQ(null,'get','storage_bookmarks');
  var query = iq.setQuery('jabber:iq:private');
  query.appendChild(
	iq.buildNode('storage', {'xmlns': 'storage:bookmarks'}));
  
  con.send(iq,getBookmarks);
  
  // get annotations
  iq = new JSJaCIQ();
  iq.setIQ(null,'get','jwchat_notes');
  var query = iq.setQuery('jabber:iq:private');
  query.appendChild(
	iq.buildNode('storage', {'xmlns': 'storage:rosternotes'}));
  
  con.send(iq,getAnnotations);
}

function getDiscoItems(iq) {
  if (!iq)
    return;
  
  Debug.log(iq.xml(),2);
  
  disco = new Object();
  
  var items = iq.getNode().firstChild.childNodes;
  
  /* query items */
  for (var i=0; i<items.length; i++) {
    if (items[i].nodeName != 'item' || !items[i].getAttribute('jid') || items[i].getAttribute('node')!=null) // skip those
      continue;
    var aIQ = new JSJaCIQ();
    aIQ.setIQ(items[i].getAttribute('jid'),'get','disco_info_'+i);
    aIQ.setQuery("http://jabber.org/protocol/disco#info");
    
    con.send(aIQ,getDiscoInfo);
  }
}

function getDiscoInfo(iq) {
  if (!iq || iq.getType() != 'result')
    return;
  
  Debug.log(iq.xml(),2);
  if (iq.getType() == 'result') {
    disco[iq.getFrom()] = iq;
    
    // If the identity does not have a name, set the name to jid
    if(iq.getNode().getElementsByTagName('identity').item(0).getAttribute('name') == null)
      iq.getNode().getElementsByTagName('identity').item(0).setAttribute('name', iq.getFrom());
    
    // set loghost
    if (iq.getNode().getElementsByTagName('identity').item(0)) {
      if (iq.getNode().getElementsByTagName('identity').item(0).getAttribute('type') == 'archive') {
	for (var j=0; j<iq.getNode().getElementsByTagName('feature').length; j++) {
	  if (iq.getNode().getElementsByTagName('feature').item(j).getAttribute('var') == 'http://jabber.org/protocol/archive') {
	    loghost = iq.getFrom();
	    break;
	  }
	}
      }
    }
  }
}

var bookmarks;
function getBookmarks(iq) {
  if (!iq || iq.getType() != 'result')
    return;
  
  Debug.log(iq.xml(),2);
  
  bookmarks = new Array();

  if (iq.getNode().getElementsByTagName('storage').item(0)) {
    var jNode = iq.getNode().getElementsByTagName('storage').item(0);
    for (var i=0; i<jNode.childNodes.length; i++) {
      var item = jNode.childNodes.item(i);
      if (item.nodeName == 'conference') {
	var bookmark = new Object();
	bookmark.jid = item.getAttribute('jid');
	bookmark.name = item.getAttribute('name');
	if (item.getAttribute('autojoin') == '1')
	  bookmark.autojoin = '1';
	if (item.getElementsByTagName('nick').item(0))
	  bookmark.nick = item.getElementsByTagName('nick').item(0).firstChild.nodeValue;
	if (item.getElementsByTagName('pass').item(0))
	  bookmark.pass = item.getElementsByTagName('pass').item(0).firstChild.nodeValue;
	bookmarks[bookmarks.length] = bookmark;
	if (bookmark.autojoin == '1') {
	  openGroupchat(bookmark.jid, bookmark.nick, bookmark.pass);
	}
      }
    }
  }
}

var annotations;
function getAnnotations(iq) {
  if (!iq || iq.getType() != 'result')
    return;
	
  Debug.log(iq.xml(),2);
	
  annotations = new Object();

  if (iq.getType() == 'result') {
    if (iq.getNode().getElementsByTagName('storage').item(0)) {
      var jNode = iq.getNode().getElementsByTagName('storage').item(0);
      for (var i=0; i<jNode.childNodes.length; i++)
	if (jNode.childNodes.item(i).nodeName == 'note' && jNode.childNodes.item(i).firstChild)
	  annotations[jNode.childNodes.item(i).getAttribute('jid')] = jNode.childNodes.item(i).firstChild.nodeValue;
    }
  }
}


/************************************************************************
 *                       ******  END HANDLERS  ******* 
 ************************************************************************
 */


/************************************************************************
 *                           ******  INIT  *******
 ************************************************************************
 */
var con, Debug, srcW;
function init() {

  /* initialise debugger */
  if (!Debug || typeof(Debug) == 'undefined' || !Debug.start) {
    if (typeof(Debugger) != 'undefined')
      Debug = new Debugger(DEBUG_LVL,'JWChat ' + cutResource(jid));
    else {
      Debug = new Object();
      Debug.log = function() {};
      Debug.start = function() {};
    }
  }
  if (DEBUG &&  (!USE_DEBUGJID || DEBUGJID == cutResource(jid)))
    Debug.start();
  
  Debug.log("jid: "+jid+"\npass: "+pass,2);

  /* get some refs to static elements */
  statusLed = frames["jwc_main"].document.getElementById('statusLed');
  statusMsg = frames["jwc_main"].document.getElementById('statusMsg');
  fmd = frames["jwc_main"].iRoster.document;
  
  /* set title */
  document.title = "JWChat - " + nick;

  /* set nick */
  frames["jwc_main"].document.getElementById('myNickname').innerHTML = nick;  

  /* init empty roster */
  roster = new Roster();
  
  /* ***
   * create new connection
   */
  var oArg = {oDbg: Debug, httpbase: HTTPBASE, timerval: timerval};
  
  if (BACKEND_TYPE == 'binding')
    con = new JSJaCHttpBindingConnection(oArg);
  else
    con = new JSJaCHttpPollingConnection(oArg);
  
  /* register handlers */
  con.registerHandler('iq',handleIQSet);
  con.registerHandler('presence',handlePresence);
  con.registerHandler('message',handleMessage);
  con.registerHandler('message',handleMessageError);
  con.registerHandler('ondisconnect',handleDisconnect);
  con.registerHandler('onconnect',handleConnected);
  con.registerHandler('onerror',handleConError);
  
  /* connect to remote */
  oArg = {domain:JABBERSERVER,username:jid.substring(0,jid.indexOf('@')),resource:jid.substring(jid.indexOf('/')+1),pass:pass,register:register}
  
  if (BACKEND_TYPE == 'binding') {
    if (opener.connect_port && !isNaN(opener.connect_port))
      oArg.port = opener.connect_port;
    if (opener.connect_host && opener.connect_host != '')
      oArg.host = opener.connect_host;
    if (opener && opener.connect_secure)
      oArg.secure = true;
  }
  con.connect(oArg);
}

/************************************************************************
 *                       ******  LOGOUT  *******
 ************************************************************************
 */

function cleanUp() {
  /* close dependent windows */
  if (roster)
    roster.cleanUp();
  
  if (subw && !subw.closed)
    subw.close();
  
  if (typeof(ow) != 'undefined' && ow && !ow.closed)
    ow.close();
  
  if (searchW && !searchW.closed)
    searchW.close();
  
  if (ebW && !ebW.closed)
    ebW.close();
  
  fmd.getElementById('roster').innerHTML = '';

  // clear frames
  frames["jwc_sound"].document.open();
  frames["jwc_sound"].document.write();
  frames["jwc_sound"].document.close();
}

var logoutCalled = false;
function logout() {
  logoutCalled = true;
  cleanUp();
  
  if (!con.connected())
    return;
  
  /* save state */
  var iq = new JSJaCIQ();
  iq.setIQ(null,'set');
  var query = iq.setQuery('jabber:iq:private');
  var aNode = query.appendChild(
	iq.buildNode('jwchat', {'xmlns': 'jwchat:state'}));
  
  // save presence
  if (onlstat != 'offline')
    aNode.appendChild(iq.buildNode('presence', onlstat));

  if (onlmsg != '')
    aNode.appendChild(iq.buildNode('onlmsg', onlmsg));
  
  var hiddengroups = '';
  if (typeof(roster) != 'undefined') {
    for (var i in roster.hiddenGroups)
	  hiddengroups += i+",";
  } 
  
  if (hiddengroups != '')
    aNode.appendChild(iq.buildNode('hiddenGroups', hiddengroups));
  
  Debug.log(iq.xml(), 2);
  con.send(iq);
  
  var aPresence = new JSJaCPresence();
  aPresence.setType('unavailable');
  con.send(aPresence);

  con.disconnect();
}

/************************************************************************
 *                     ******  INITIALISE VARS  *******
 ************************************************************************
 */

/* get args */
getArgs();

var JABBERSERVER;
var BACKEND_TYPE;
var HTTPBASE;

if (opener && opener.JABBERSERVER)
	JABBERSERVER = opener.JABBERSERVER;
else if (passedArgs['server'])
	JABBERSERVER = passedArgs['server'];

if (opener && opener.BACKEND_TYPE)
	BACKEND_TYPE = opener.BACKEND_TYPE;
else if (passedArgs['btype'])
	BACKEND_TYPE = passedArgs['btype'];

if (opener && opener.HTTPBASE)
	HTTPBASE = opener.HTTPBASE;
else if (passedArgs['base'])
	HTTPBASE = passedArgs['base'];

if (opener && opener.pass)
	pass = opener.pass;
else if (passedArgs['pass'])
	pass = passedArgs['pass'];

if (opener && opener.jid)
	jid = opener.jid;
else if (passedArgs['jid'])
	jid = passedArgs['jid'];

if (opener && opener.register)
	register = opener.register
else if (passedArgs['register'])
	register = eval(passedArgs['register']);

if (!jid) {
	alert("Отсутствует JID.\nОтмена операции...");
  window.close();
}
if (!pass) {
	alert("Отсутствует пароль.\nПрерывание операции...");
	window.close();
}

if (!isValidJID(jid))
  window.close();

nick = jid.substring(0,jid.indexOf('@'));

/* get style */
if (opener && opener.myStyle)
  stylesheet = THEMESDIR + "/" + opener.myStyle + "/" + stylesheet;
else if (passedArgs['myStyle']) 
  stylesheet = THEMESDIR + "/" + passedArgs['myStyle'] + "/" + stylesheet;

function updateStyleIE() {
  if (roster)
    roster.updateStyleIE();
}

onload = init;
onunload = logout;
onresize = updateStyleIE;

//-->
    </script>
    <script src="roster.js"></script>
  </head>
  <frameset rows="100%,0,0,0,0" border="0">
    <frame src="roster.html" name="jwc_main" marginwidth="0" marginheight="0" scrolling="no">
    <frame src="empty.html" name="jwc_sound" marginwidth="0" marginheight="0" onLoad="soundLoaded();">
  </frameset>
  <body>
    Ваш браузер должен поддерживать фреймы и javascript, чтобы использовать данное приложение. Пожалуйста, обратитесь к нашему  <a href="help.html">руководству</a> за списком поддерживаемых браузеров.
  </body>
</html>
