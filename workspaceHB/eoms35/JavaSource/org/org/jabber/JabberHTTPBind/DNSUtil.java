// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DNSUtil.java

package org.jabber.JabberHTTPBind;

import java.util.Hashtable;
import javax.naming.directory.*;

public class DNSUtil {
    public static class HostAddress {

        private String host;
        private int port;

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public String toString() {
            return (new StringBuilder()).append(host).append(":").append(port).toString();
        }

        private HostAddress(String host, int port) {
            this.host = host;
            this.port = port;
        }

    }


    private static DirContext context;

    public DNSUtil() {
    }

    public static HostAddress resolveXMPPServerDomain(String domain, int defaultport) {
        if (context == null)
            return new HostAddress(domain, defaultport);
        String host = domain;
        int port = defaultport;
        try {
            Attributes dnsLookup = context.getAttributes((new StringBuilder()).append("_xmpp-client._tcp.").append(domain).toString());
            String srvRecord = (String) dnsLookup.get("SRV").get();
            String srvRecordEntries[] = srvRecord.split(" ");
            port = Integer.parseInt(srvRecordEntries[srvRecordEntries.length - 2]);
            host = srvRecordEntries[srvRecordEntries.length - 1];
        } catch (Exception e) {
            try {
                Attributes dnsLookup = context.getAttributes((new StringBuilder()).append("_jabber-client._tcp.").append(domain).toString());
                String srvRecord = (String) dnsLookup.get("SRV").get();
                String srvRecordEntries[] = srvRecord.split(" ");
                port = Integer.parseInt(srvRecordEntries[srvRecordEntries.length - 2]);
                host = srvRecordEntries[srvRecordEntries.length - 1];
            } catch (Exception e2) {
            }
        }
        if (host.endsWith("."))
            host = host.substring(0, host.length() - 1);
        return new HostAddress(host, port);
    }

    static {
        try {
            Hashtable env = new Hashtable();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            context = new InitialDirContext(env);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
