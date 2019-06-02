/*
 * Copyright HBT Co,.Ltd.(2000-2002). Allrights Reserved
 * User: lizhengyou
 * Date: May 21, 2002
 * Time: 3:35:17 PM
 *
 * $Id
 */
// Decompiled by Jad v1.5.7d. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   UploadFile.java

package com.boco.eoms.filemanager.extra.fileupload;


public class UploadFile {

    public UploadFile(String filename, String contenttype, long size, byte data[]) {
        try {
            byte[] dbbyte1 = filename.getBytes("iso-8859-1");
            filename = new String(dbbyte1);
            _$61247 = null;
            _$49449 = null;
            _$49461 = null;
            _$49430 = -1L;
            _$49461 = filename;
            _$49449 = contenttype;
            _$49430 = size;
            _$61247 = data;
        } catch (Exception e) {
            //throw new UploadException();
        }
    }

    public long getFileSize() {
        return _$49430;
    }

    public String getFileName() {
        return _$49461;
    }

    public String getContentType() {
        return _$49449;
    }

    public byte[] getData() {
        return _$61247;
    }

    public void setData(byte data[]) {
        _$61247 = data;
    }

    private byte _$61247[];
    private String _$49449;
    private String _$49461;
    private long _$49430;
}
