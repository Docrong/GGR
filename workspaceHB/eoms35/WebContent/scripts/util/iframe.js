function autoIframe() {
    if (parent == window) return;
    var i = parent.document.getElementById(window.name);
    iHeight = document.body.scrollHeight;
    iWidth = document.body.scrollWidth;
    i.style.height = iHeight + "px";
    i.style.width = iWidth + "px";
}

if (window.addEvent) {
    window.addEvent('domready', autoIframe);
}
if (window.attachEvent) {
    window.attachEvent("onload", autoIframe);
}
if (window.addEventListener) {
    window.addEventListener('load', autoIframe, false);
}