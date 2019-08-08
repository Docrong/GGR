package com.boco.eoms.km.core.crimson.util;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.StringTokenizer;

import com.boco.eoms.km.core.crimson.util.HashMapEx;
import com.boco.eoms.km.core.crimson.util.NodeDescription;

public class XPathParser {
    private static HashMapEx pool = new HashMapEx();
    private static HashMap reader = new HashMap();
    public static final short PATH_ABSOLUTE = 0;
    public static final short PATH_RELATIVE = 1;
    private short m_type;
    private Object nodes[];
    private int cursor;
    private int max;
    private static int MAX_CACHE_SIZE = 10000;

    public static int getMaxCacheSize() {
        return MAX_CACHE_SIZE;
    }

    public static void setMaxCacheSize(int MAX_CACHE_SIZE) {
        MAX_CACHE_SIZE = MAX_CACHE_SIZE;
    }

    public XPathParser(String xPath) {
        m_type = 1;
        nodes = new Object[8];
        max = 8;
        xPath = xPath.trim();
        if (xPath.indexOf("/") == 0)
            m_type = 0;
        StringTokenizer stk = new StringTokenizer(xPath, "/");
        do {
            if (!stk.hasMoreTokens())
                break;
            nodes[cursor] = new NodeDescription(stk.nextToken());
            cursor++;
            if (cursor >= max)
                expand();
        } while (true);
    }

    public ListIterator listIterator() {
        return new XPathListIterator(nodes, cursor);
    }

    private void expand() {
        Object oldObjs[] = nodes;
        max += 8;
        nodes = new Object[max];
        System
                .arraycopy(((Object) (oldObjs)), 0, ((Object) (nodes)), 0,
                        cursor);
    }

    public static XPathParser create(String xPath) {
        XPathParser parser = (XPathParser) reader.get(xPath);
        if (parser == null) {
            parser = new XPathParser(xPath);
            if (pool.size() < MAX_CACHE_SIZE)
                pool.put(xPath, parser);
        }
        return parser;
    }

    public static void put(String xPath) {
        if (pool.size() < MAX_CACHE_SIZE) {
            XPathParser parser = new XPathParser(xPath);
            reader.put(xPath, parser);
            pool.put(xPath, parser);
        }
    }

    public static Object[] getXPaths() {
        return pool.keySet().toArray();
    }
}