
var IE = (document.all) ? 1 : 0;
var NS = (document.layers) ? 1 : 0;

// WINDOW STATUS FUNCTIONS
window.defaultStatus = "";
function winStatus( msg )
{
	window.status = msg;
}

// ALERT FUNCTIONS
function adminAlert( msg ) {
	if( msg != null && trim(msg) != "" ) {
		alert( msg );
	}
}

function addToWelcomePage( jvm, webApp, sectionName, URL, formItem ) {
	//alert(sectionName);
	top.adminHeader.location = "/properties/addToWelcomePage.jsp?" +
		"jrun_j=" + escape(jvm) +
		"&jrun_wa=" + escape(webApp) +
		"&sectionName=" + escape(sectionName) +
		"&checkOn=" + formItem.checked +		
		"&jmcLinkURL=" + escape(URL);
}

function goThere( node,loc ) {
	//Only perform folder opening, don't close anything
	if( parent.nav.indexOfEntries[node].isOpen == false )
		parent.nav.clickOnNode(node);
	window.location = loc;
}

function popUp( loc, w, h, menubar ) {
	if( w == null ) { w = 500; }
	if( h == null ) { h = 350; }
	if( menubar == null || menubar == false ) {
		menubar = "";
	} else {
		menubar = "menubar,";
	}

	if( NS ) { w += 50; }
	// Need the var or else IE4 blows up not recognizing editorWin
	var editorWin = window.open(loc,'editWin', menubar + 'resizable,scrollbars,width=' + w + ',height=' + h);
	//if( !newWin.opener )
		//newWin.opener = window;
	editorWin.focus(); //causing intermittent errors
}

function popUpLarge( loc, menubar ) {
	if( menubar == null ) { menubar = false; }
	popUp( loc, 700, 500, menubar );
}

function connectorWizardPrompt( jvm ) {
	// Go to the connector wizard if the user wants to run it for the first time
	if( confirm('You have not configured the connection between this server and an external Web server.\n\n Click OK to run the Connector Wizard.') ) {
		self.location = "/jvm/webserver/connectors/index.jsp?jrun_j=" + escape(jvm);
	}
}

function closeMe() {
	//self.close();
	parent.close();
}

function formSubmit() {
	parent.frames[0].document.forms[0].submit();
}

function openerReload() {
	if( parent.opener.name != null )
		parent.opener.location.reload();
		//alert( parent.opener.name );
		//parent.opener.r();
}

function r() {
	self.setTimeout("rl()", 2000);
}
function rl(){
	self.location.reload();
}

function browseWindow( ele, fileExtension ) {
	if( fileExtension == null ) { fileExtension = ""; }
	page = "/includes/dirReader.jsp?jrun_target_element=" + ele + "&jrun_file_ext=" + fileExtension;
	bWin = window.open(page,'browseWin','resizable,scrollbars,width=500,height=60,screenX=300,screenY=200');
	bWin.focus();
}

// NAV TREE FUNCTIONS
function navRefresh() {
	top.nav.location.reload();
}

// JDBC WIZARD FUNCTIONS
function jdbcCancel() {
        if( confirm('You have chosen to exit the JDBC Wizard. \n\nClick \'OK\' to go to the JRun Welcome Page.') ) {
                // If the jdbc wizard is running solo (not within JMC /index.jsp frameset), then redirect should happen at the top level
                if( top.location == self.location ) {
                        top.location = "/index.jsp";
                } else {
                        self.location = "/welcome.jsp";
                }
        }
}


// CONNECTOR FUNCTIONS
function connectorCancel() {
	if( confirm('You have chosen to exit the Connector Wizard. \n\nClick \'OK\' to go to the JRun Welcome Page.') ) {
		// If the connector wizard is running solo (not within JMC /index.jsp frameset), then redirect should happen at the top level
		if( top.location == self.location ) {
			top.location = "/index.jsp";
		} else {
			self.location = "/welcome.jsp";
		}
	}
}

function connectorDone() {
	// If the connector wizard is running solo (not within JMC /index.jsp frameset), then redirect should happen at the top level
	if( top.location == self.location ) {
		top.location = "/index.jsp";
	} else {
		self.location = "/welcome.jsp";
	}
}

// EJB FUNCTIONS
function redeployAllJarsConfirm( jvm ) {
	if( confirm('You have chosen to redeploy all Jars. \n\nClick \'OK\' to continue.') ) {
		self.location = "/jvm/jarRedeploy.jsp?jrun_j=" + escape(jvm);
	}
}

// STRING FUNCTIONS
function trim( str ) {
	// Immediately return if no trimming is needed
	if( (str.charAt(0) != ' ') && (str.charAt(str.length-1) != ' ') ) { return str; }
	// Trim leading spaces
	while( str.charAt(0)  == ' ' ) {
		str = '' + str.substring(1,str.length);
	}
	// Trim trailing spaces
	while( str.charAt(str.length-1)  == ' ' ) {
		str = '' + str.substring(0,str.length-1);
	}

	return str;
}

function strReplace( entry, bad, good ) {
	temp = "" + entry; // temporary holder
	while( temp.indexOf(bad) > -1 ) {
		pos= temp.indexOf( bad );
		temp = "" + ( temp.substring(0, pos) + good + 
			temp.substring( (pos + bad.length), temp.length) );
	}
	return temp;
}
