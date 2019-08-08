style = "jwchat.css"; // fallback

// look for stylesheet
/*if (parent.top.stylesheet)
  style = parent.top.stylesheet;
else if (top.opener.top.stylesheet)
  style = top.opener.top.stylesheet;
else if (top.opener.opener.top.stylesheet)
	style = top.opener.opener.top.stylesheet;
	
	if (parent.parent.stylesheet)
  style = parent.parent.stylesheet;
else if (parent.opener.parent.stylesheet)
  style = parent.opener.parent.stylesheet;
else if (parent.opener.opener.parent.stylesheet)
	style = parent.opener.opener.parent.stylesheet;*/


document.write('<link rel="styleSheet" type="text/css" href="' + style + '">');
