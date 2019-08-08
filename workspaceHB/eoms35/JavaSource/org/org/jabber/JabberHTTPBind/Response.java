package org.jabber.JabberHTTPBind;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Response {
    private static TransformerFactory tff = TransformerFactory.newInstance();
    public static final String STATUS_LEAVING = "leaving";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_DONE = "done";
    private long cDate;
    private Document doc;
    private Element body;
    private long rid;
    private String contentType = "text/xml; charset=utf-8";
    private String status;
    private HttpServletRequest req;
    private boolean aborted;

    public Response(Document doc) {
        this.doc = doc;

        this.body = this.doc.createElement("body");
        this.doc.appendChild(this.body);

        this.body.setAttribute("xmlns", "http://jabber.org/protocol/httpbind");

        this.cDate = System.currentTimeMillis();

        setStatus("pending");
    }

    public Response(Document doc, HttpServletRequest req) {
        this(doc);
        this.req = req;
    }

    public Response setAttribute(String key, String val) {
        this.body.setAttribute(key, val);
        return this;
    }

    public Response setContentType(String type) {
        this.contentType = type;
        return this;
    }

    public Response addNode(Node n, String ns) {
        try {
            if (!((Element) n).getAttribute("xmlns").equals(ns))
                ((Element) n).setAttribute("xmlns", ns);
        } catch (ClassCastException e) {
        }
        this.body.appendChild(this.doc.importNode(n, true));
        return this;
    }

    public synchronized void send(HttpServletResponse response) {
        StringWriter strWtr = new StringWriter();
        StreamResult strResult = new StreamResult(strWtr);
        try {
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty("omit-xml-declaration", "yes");
            tf.transform(new DOMSource(this.doc.getDocumentElement()), strResult);
            response.setContentType(this.contentType);
            JHBServlet.dbg("sending response [" + getRID() + "]: " + strResult.getWriter().toString(), 2);
            response.getWriter().println(strResult.getWriter().toString());
            JHBServlet.dbg("sent response for " + getRID(), 3);
        } catch (Exception e) {
            JHBServlet.dbg("XML.toString(Document): " + e, 1);
        }
        setStatus("done");
    }

    public synchronized String getStatus() {
        return this.status;
    }

    public synchronized void setStatus(String status) {
        JHBServlet.dbg("response status " + status + " for " + getRID(), 3);
        this.status = status;
    }

    public long getRID() {
        return this.rid;
    }

    public Response setRID(long rid) {
        this.rid = rid;
        return this;
    }

    public synchronized long getCDate() {
        return this.cDate;
    }

    public synchronized HttpServletRequest getReq() {
        return this.req;
    }

    public synchronized void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public synchronized boolean isAborted() {
        return this.aborted;
    }

    public synchronized void setAborted(boolean aborted) {
        this.aborted = aborted;
    }
}