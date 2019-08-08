package org.jabber.JabberHTTPBind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Session {
    public static final String DEFAULT_CONTENT = "text/xml; charset=utf-8";
    public static final int MAX_INACTIVITY = 60;
    public static final int MAX_REQUESTS = 2;
    public static final int MAX_WAIT = 300;
    public static final int MIN_POLLING = 2;
    private static final int READ_TIMEOUT = 1;
    private static final int SOCKET_TIMEOUT = 6000;
    public static final int DEFAULT_XMPPPORT = 5222;
    protected static final String SESS_START = "starting";
    protected static final String SESS_ACTIVE = "active";
    protected static final String SESS_TERM = "term";
    private static Hashtable sessions = new Hashtable();

    private static TransformerFactory tff = TransformerFactory.newInstance();
    private String authid;
    boolean authidSent = false;

    boolean streamFeatures = false;

    private String content = "text/xml; charset=utf-8";
    private DocumentBuilder db;
    private int hold = 1;

    private String inQueue = "";
    private BufferedReader br;
    private String key;
    private long lastActive;
    private long lastPoll = 0L;
    private OutputStreamWriter osw;
    private TreeMap responses;
    private String status = "starting";
    private String sid;
    public Socket sock;
    private String to;
    private DNSUtil.HostAddress host = null;

    private int wait = 300;

    private String xmllang = "en";

    private boolean reinit = false;

    private boolean secure = false;

    private boolean pauseForHandshake = false;
    private Pattern streamPattern;
    private Pattern stream10Test;
    private Pattern stream10Pattern;
    private int init_retry = 0;
    private long lastDoneRID;

    private static String createSessionID(int len) {
        String charlist = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";

        Random rand = new Random();

        String str = new String();
        for (int i = 0; i < len; i++)
            str = str + charlist.charAt(rand.nextInt(charlist.length()));
        return str;
    }

    public static Session getSession(String sid) {
        return (Session) sessions.get(sid);
    }

    public static Enumeration getSessions() {
        return sessions.elements();
    }

    public static int getNumSessions() {
        return sessions.size();
    }

    public static void stopSessions() {
        for (Enumeration e = sessions.elements(); e.hasMoreElements(); )
            ((Session) e.nextElement()).terminate();
    }

    public Session(String to, String route)
            throws UnknownHostException, IOException {
        this.to = to;

        int port = 5222;

        this.sock = new Socket();
        setLastActive();
        try {
            this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (Exception e) {
        }
        if ((route != null) && (!route.equals(""))) {
            JHBServlet.dbg("Trying to use 'route' attribute to open a socket...", 3);

            if (route.startsWith("xmpp:"))
                route = route.substring("xmpp:".length());
            int i;
            if ((i = route.lastIndexOf(":")) != -1) {
                try {
                    int p = Integer.parseInt(route.substring(i + 1));
                    if ((p >= 0) && (p <= 65535)) {
                        port = p;
                        JHBServlet.dbg("...route attribute holds a valid port (" + port + ").", 3);
                    }
                } catch (NumberFormatException nfe) {
                }

                route = route.substring(0, i);
            }

            JHBServlet.dbg("Trying to open a socket to '" + route + "', using port " + port + ".", 3);
            try {
                this.sock.connect(new InetSocketAddress(route, port), 6000);
            } catch (Exception e) {
                JHBServlet.dbg("Failed to open a socket using the 'route' attribute", 3);
            }

        }

        if ((this.sock == null) || (!this.sock.isConnected())) {
            JHBServlet.dbg("Trying to use 'to' attribute to open a socket...", 3);

            this.host = DNSUtil.resolveXMPPServerDomain(to, 5222);
            try {
                JHBServlet.dbg("Trying to open a socket to '" + this.host.getHost() + "', using port " + this.host.getPort() + ".", 3);

                this.sock.connect(new InetSocketAddress(this.host.getHost(), this.host.getPort()), 6000);
            } catch (UnknownHostException uhe) {
                JHBServlet.dbg("Failed to open a socket using the 'to' attribute", 3);

                throw uhe;
            } catch (IOException ioe) {
                JHBServlet.dbg("Failed to open a socket using the 'to' attribute", 3);

                throw ioe;
            }

        }

        try {
            if (this.sock.isConnected()) {
                JHBServlet.dbg("Succesfully connected to " + to, 2);
            }
            this.sock.setSoTimeout(6000);

            this.osw = new OutputStreamWriter(this.sock.getOutputStream(), "UTF-8");

            this.osw.write("<stream:stream to='" + this.to + "'" + " xmlns='jabber:client' " + " xmlns:stream='http://etherx.jabber.org/streams'" + " version='1.0'" + ">");

            this.osw.flush();

            while (sessions.get(this.sid = createSessionID(24)) != null) ;
            JHBServlet.dbg("creating session with id " + this.sid, 2);

            sessions.put(this.sid, this);

            this.responses = new TreeMap();

            this.br = new BufferedReader(new InputStreamReader(this.sock.getInputStream(), "UTF-8"));

            this.streamPattern = Pattern.compile(".*<stream:stream[^>]*id=['|\"]([^'|^\"]+)['|\"][^>]*>.*", 32);

            this.stream10Pattern = Pattern.compile(".*<stream:stream[^>]*id=['|\"]([^'|^\"]+)['|\"][^>]*>.*(<stream.*)$", 32);

            this.stream10Test = Pattern.compile(".*<stream:stream[^>]*version=['|\"]1.0['|\"][^>]*>.*", 32);

            setStatus("active");
        } catch (IOException ioe) {
            throw ioe;
        }
    }

    public synchronized Response addResponse(Response r) {
        while ((this.responses.size() > 0) && (this.responses.size() >= 2))
            this.responses.remove(this.responses.firstKey());
        return (Response) this.responses.put(new Long(r.getRID()), r);
    }

    public NodeList checkInQ(long rid)
            throws IOException {
        NodeList nl = null;

        this.inQueue += readFromSocket(rid);

        JHBServlet.dbg("inQueue: " + this.inQueue, 2);

        if ((this.init_retry < 1000) && ((this.authid == null) || (isReinit())) && (this.inQueue.length() > 0)) {
            this.init_retry += 1;
            if (this.stream10Test.matcher(this.inQueue).matches()) {
                Matcher m = this.stream10Pattern.matcher(this.inQueue);
                if (m.matches()) {
                    this.authid = m.group(1);
                    this.inQueue = m.group(2);
                    JHBServlet.dbg("inQueue: " + this.inQueue, 2);

                    this.streamFeatures = (this.inQueue.length() > 0);
                } else {
                    JHBServlet.dbg("failed to get stream features", 2);
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException ie) {
                    }
                    return checkInQ(rid);
                }
            } else {
                Matcher m = this.streamPattern.matcher(this.inQueue);
                if (m.matches()) {
                    this.authid = m.group(1);
                } else {
                    JHBServlet.dbg("failed to get authid", 2);
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException ie) {
                    }
                    return checkInQ(rid);
                }
            }
            this.init_retry = 0;
        }

        if (!this.inQueue.equals("")) {
            try {
                Document doc = null;
                if (this.streamFeatures)
                    doc = this.db.parse(new InputSource(new StringReader("<doc>" + this.inQueue + "</doc>")));
                else
                    try {
                        doc = this.db.parse(new InputSource(new StringReader("<doc xmlns='jabber:client'>" + this.inQueue + "</doc>")));
                    } catch (SAXException sex) {
                        try {
                            doc = this.db.parse(new InputSource(new StringReader("<stream:stream>" + this.inQueue)));

                            terminate();
                        } catch (SAXException sex2) {
                        }
                    }
                if (doc != null)
                    nl = doc.getFirstChild().getChildNodes();
                if (this.streamFeatures) {
                    for (int i = 0; i < nl.item(0).getChildNodes().getLength(); i++) {
                        if (!nl.item(0).getChildNodes().item(i).getNodeName().equals("starttls"))
                            continue;
                        if (!isReinit()) {
                            JHBServlet.dbg("starttls present, trying to use it", 2);

                            this.osw.write("<starttls xmlns='urn:ietf:params:xml:ns:xmpp-tls'/>");

                            this.osw.flush();
                            String response = readFromSocket(rid);
                            JHBServlet.dbg(response, 2);
                            try {
                                SSLSocketFactory sslFact = (SSLSocketFactory) SSLSocketFactory.getDefault();

                                SSLSocket tls = (SSLSocket) sslFact.createSocket(this.sock, this.sock.getInetAddress().getHostName(), this.sock.getPort(), false);

                                tls.addHandshakeCompletedListener(new HandShakeFinished(this));

                                this.pauseForHandshake = true;
                                JHBServlet.dbg("initiating handshake");
                                tls.startHandshake();
                                try {
                                    while (this.pauseForHandshake) {
                                        JHBServlet.dbg(".");
                                        Thread.sleep(5L);
                                    }
                                } catch (InterruptedException ire) {
                                }
                                JHBServlet.dbg("TLS Handshake complete", 2);

                                this.sock = tls;
                                this.sock.setSoTimeout(6000);

                                this.br = new SSLSocketReader(tls);

                                this.osw = new OutputStreamWriter(tls.getOutputStream(), "UTF-8");

                                this.inQueue = "";
                                setReinit(true);
                                this.osw.write("<stream:stream to='" + this.to + "'" + " xmlns='jabber:client' " + " xmlns:stream='http://etherx.jabber.org/streams'" + " version='1.0'" + ">");

                                this.osw.flush();

                                return checkInQ(rid);
                            } catch (Exception ssle) {
                                JHBServlet.dbg("STARTTLS failed: " + ssle.toString(), 1);

                                setReinit(false);
                                if (isSecure()) {
                                    if ((!this.sock.getInetAddress().getHostName().equals("localhost")) && (!getResponse(rid).getReq().getServerName().equals(this.sock.getInetAddress().getHostName()))) {
                                        JHBServlet.dbg("secure connection requested but failed", 2);

                                        throw new IOException();
                                    }

                                    JHBServlet.dbg("secure requested and we're local", 1);
                                } else {
                                    JHBServlet.dbg("tls failed but we don't need to be secure", 2);
                                }

                                if (this.sock.isClosed()) {
                                    JHBServlet.dbg("socket closed", 1);

                                    Socket s = new Socket();
                                    s.connect(this.sock.getRemoteSocketAddress(), 6000);

                                    this.sock = s;
                                    this.sock.setSoTimeout(6000);
                                    this.br = new BufferedReader(new InputStreamReader(this.sock.getInputStream(), "UTF-8"));

                                    this.osw = new OutputStreamWriter(this.sock.getOutputStream(), "UTF-8");

                                    this.inQueue = "";
                                    setReinit(true);

                                    this.osw.write("<stream:stream to='" + this.to + "'" + " xmlns='jabber:client' " + " xmlns:stream='http://etherx.jabber.org/streams'" + " version='1.0'" + ">");

                                    this.osw.flush();

                                    return checkInQ(rid);
                                }
                            }
                        } else {
                            nl.item(0).removeChild(nl.item(0).getChildNodes().item(i));
                        }
                    }
                }

                this.inQueue = "";
            } catch (SAXException sex3) {
                setReinit(false);
                JHBServlet.dbg("failed to parse inQueue: " + this.inQueue + "\n" + sex3.toString(), 1);

                return null;
            }
        }
        setReinit(false);
        setLastActive();
        return nl;
    }

    public synchronized boolean checkValidRID(long rid) {
        try {
            if ((rid <= ((Long) this.responses.lastKey()).longValue() + 2L) && (rid >= ((Long) this.responses.firstKey()).longValue())) {
                return true;
            }
            JHBServlet.dbg("invalid request id: " + rid + " (last: " + ((Long) this.responses.lastKey()).longValue() + ")", 1);

            return false;
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    public String getAuthid() {
        return this.authid;
    }

    public String getContent() {
        return this.content;
    }

    public int getHold() {
        return this.hold;
    }

    public synchronized String getKey() {
        return this.key;
    }

    public synchronized long getLastActive() {
        return this.lastActive;
    }

    public synchronized long getLastPoll() {
        return this.lastPoll;
    }

    public synchronized Response getResponse(long rid) {
        return (Response) this.responses.get(new Long(rid));
    }

    public String getSID() {
        return this.sid;
    }

    public String getTo() {
        return this.to;
    }

    public int getWait() {
        return this.wait;
    }

    public String getXMLLang() {
        return this.xmllang;
    }

    public synchronized int numPendingRequests() {
        int num_pending = 0;
        Iterator it = this.responses.values().iterator();
        while (it.hasNext()) {
            Response r = (Response) it.next();
            if (!r.getStatus().equals("done"))
                num_pending++;
        }
        return num_pending;
    }

    public synchronized long getLastDoneRID() {
        return this.lastDoneRID;
    }

    private String readFromSocket(long rid)
            throws IOException {
        String retval = "";
        char[] buf = new char[16];
        int c = 0;

        Response r = getResponse(rid);

        while ((!this.sock.isClosed()) && (!isStatus("term"))) {
            setLastActive();
            try {
                if (this.br.ready()) {
                    while ((this.br.ready()) && ((c = this.br.read(buf, 0, buf.length)) >= 0))
                        retval = retval + new String(buf, 0, c);
                    break;
                }
                if (((this.hold == 0) && (r != null) && (System.currentTimeMillis() - r.getCDate() > 200L)) || ((this.hold > 0) && (((r != null) && (System.currentTimeMillis() - r.getCDate() >= getWait() * 1000)) || (numPendingRequests() > getHold()) || (!retval.equals("")))) || (r.isAborted())) {
                    JHBServlet.dbg("readFromSocket done for " + rid, 3);
                    break;
                }
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException ie) {
                    System.err.println(ie.toString());
                }
            } catch (IOException e) {
                System.err.println("Can't read from socket");
                terminate();
            }
        }

        if (this.sock.isClosed()) {
            throw new IOException();
        }
        return retval;
    }

    public Session sendNodes(NodeList nl) {
        String out = "";
        StreamResult strResult = new StreamResult();
        try {
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty("omit-xml-declaration", "yes");

            for (int i = 0; i < nl.getLength(); i++) {
                strResult.setWriter(new StringWriter());
                tf.transform(new DOMSource(nl.item(i)), strResult);
                String tStr = strResult.getWriter().toString();
                out = out + tStr;
            }
        } catch (Exception e) {
            JHBServlet.dbg("XML.toString(Document): " + e, 1);
        }
        try {
            if (isReinit()) {
                JHBServlet.dbg("Reinitializing Stream!", 2);
                this.osw.write("<stream:stream to='" + this.to + "'" + " xmlns='jabber:client' " + " xmlns:stream='http://etherx.jabber.org/streams'" + " version='1.0'" + ">");
            }

            this.osw.write(out);
            this.osw.flush();
        } catch (IOException ioe) {
            JHBServlet.dbg(this.sid + " failed to write to stream", 1);
        }

        return this;
    }

    public Session setContent(String content) {
        this.content = content;
        return this;
    }

    public Session setHold(int hold) {
        if ((hold < 2) && (hold >= 0))
            this.hold = hold;
        return this;
    }

    public synchronized void setKey(String key) {
        this.key = key;
    }

    public synchronized void setLastActive() {
        this.lastActive = System.currentTimeMillis();
    }

    public synchronized void setLastDoneRID(long rid) {
        this.lastDoneRID = rid;
    }

    public synchronized void setLastPoll() {
        this.lastPoll = System.currentTimeMillis();
    }

    public int setWait(int wait) {
        if (wait < 0)
            wait = 0;
        if (wait > 300)
            wait = 300;
        this.wait = wait;
        return wait;
    }

    public Session setXMLLang(String xmllang) {
        this.xmllang = xmllang;
        return this;
    }

    public synchronized boolean isReinit() {
        return this.reinit;
    }

    public synchronized boolean isSecure() {
        return this.secure;
    }

    public synchronized void setReinit(boolean reinit) {
        this.reinit = reinit;
    }

    public synchronized void setStatus(String status) {
        this.status = status;
    }

    public synchronized boolean isStatus(String status) {
        return this.status == status;
    }

    public void terminate() {
        JHBServlet.dbg("terminating session " + getSID(), 2);
        setStatus("term");
        synchronized (this.sock) {
            if (!this.sock.isClosed())
                try {
                    this.osw.write("</stream:stream>");
                    this.osw.flush();
                    this.sock.close();
                } catch (IOException ie) {
                }
            this.sock.notifyAll();
        }
        sessions.remove(this.sid);
    }

    public synchronized void setSecure(boolean secure) {
        this.secure = secure;
    }

    private class HandShakeFinished
            implements HandshakeCompletedListener {
        private Session sess;

        public HandShakeFinished(Session sess) {
            this.sess = sess;
        }

        public void handshakeCompleted(HandshakeCompletedEvent event) {
            JHBServlet.dbg("startTLS: Handshake is complete", 2);
            sess.pauseForHandshake = false;
        }
    }
}