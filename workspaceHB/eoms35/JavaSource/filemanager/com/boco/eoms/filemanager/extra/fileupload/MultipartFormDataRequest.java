
package com.boco.eoms.filemanager.extra.fileupload;

import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.FilePart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class MultipartFormDataRequest {

    public MultipartFormDataRequest(HttpServletRequest req, int maxcontentlength)
            throws IOException, UploadException {
        _$49163 = null;
        _$49174 = null;
        _$49180 = 0x1400000;
        _$49197 = null;
        _$49205 = null;
        _$49180 = maxcontentlength;
        _$49163 = new Hashtable();
        _$49174 = new Hashtable();
        if (req == null)
            new UploadException(UploadException.INVALIDREQUEST);
        MultipartParser parser = new MultipartParser(req, _$49180, true, true);
        do {
            Part part;
            if ((part = parser.readNextPart()) == null)
                break;
            String name = part.getName();
            if (part.isParam()) {
                ParamPart paramPart = (ParamPart) part;
                String value = paramPart.getStringValue();
                Vector existingValues = (Vector) _$49163.get(name);
                if (existingValues == null) {
                    existingValues = new Vector();
                    _$49163.put(name, existingValues);
                }
                existingValues.addElement(value);
            } else if (part.isFile()) {
                FilePart filePart = (FilePart) part;
                String fileName = filePart.getFileName();
                if (fileName != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    long size = filePart.writeTo(baos);
                    byte data[] = baos.toByteArray();
                    _$49174.put(name, new UploadFile(fileName, filePart.getContentType(), size, data));
                } else {
                    _$49174.put(name, new UploadFile(null, null, -1L, null));
                }
            }
        } while (true);
    }

    public MultipartFormDataRequest(HttpServletRequest req)
            throws IOException, UploadException {
        this(req, 0x1400000);
    }

    public Hashtable getFiles() {
        return _$49174;
    }

    public Enumeration getParameterNames() {
        return _$49163.keys();
    }

    public String getParameter(String name) {
        String s1;
        try {
            Vector v = (Vector) _$49163.get(name);
            if (v == null || v.size() == 0) {
                String s = null;
                return s;
            } else {
                String val = (String) v.elementAt(v.size() - 1);
                String s2 = val;
                return s2;
            }
        } catch (Exception ex) {
            s1 = null;
        }
        return s1;
    }

    public String[] getParameterValues(String name) {
        String as1[];
        try {
            Vector v = (Vector) _$49163.get(name);
            if (v == null || v.size() == 0) {
                String as[] = null;
                return as;
            } else {
                String valuesArray[] = new String[v.size()];
                v.copyInto(valuesArray);
                String as2[] = valuesArray;
                return as2;
            }
        } catch (Exception ex) {
            as1 = null;
        }
        return as1;
    }

    public static boolean isMultipartFormData(HttpServletRequest req) {
        String type = null;
        String type1 = req.getHeader("Content-Type");
        String type2 = req.getContentType();
        if (type1 == null && type2 != null)
            type = type2;
        else if (type2 == null && type1 != null)
            type = type1;
        else if (type1 != null && type2 != null)
            type = type1.length() <= type2.length() ? type2 : type1;
        return type != null && type.toLowerCase().startsWith("multipart/form-data");
    }

    public static int MAXCONTENTLENGTHALLOWED = 0x1400000;
    private Hashtable _$49163;
    private Hashtable _$49174;
    private int _$49180;
    private HttpServletRequest _$49197;
    private String _$49205;

    static {
        MAXCONTENTLENGTHALLOWED = 0x1400000;
    }
}
