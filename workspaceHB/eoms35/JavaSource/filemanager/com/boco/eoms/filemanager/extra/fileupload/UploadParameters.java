/*
 * Copyright HBT Co,.Ltd.(2000-2002). Allrights Reserved
 * User: lizhengyou
 * Date: May 21, 2002
 * Time: 3:40:45 PM
 *
 * $Id
 */
// Source File Name:   UploadParameters.java

package com.boco.eoms.filemanager.extra.fileupload;


public class UploadParameters {

    public UploadParameters(String filename, long size, String contenttype, int storemodel, String storeinfo) {
        _$49419 = -1;
        _$49430 = -1L;
        _$49439 = null;
        _$49449 = null;
        _$49461 = null;
        _$49470 = null;
        _$49461 = filename;
        _$49449 = contenttype;
        _$49430 = size;
        _$49419 = storemodel;
        _$49439 = storeinfo;
        if (_$49461 != null) {
            int ind = _$49461.indexOf(".");
            if (ind != -1)
                _$49470 = _$49461.substring(ind + 1, _$49461.length());
        }
    }

    public int getStoremodel() {
        return _$49419;
    }

    public String getStoremodelname() {
        if (_$49419 == 3)
            return "zip file";
        if (_$49419 == 1)
            return "folder";
        if (_$49419 == 4)
            return "tar gzip file";
        if (_$49419 == 2)
            return "database";
        if (_$49419 == 6)
            return "xml file";
        if (_$49419 == 0)
            return "memory";
        if (_$49419 == 5)
            return "serialized file";
        else
            return "unknow";
    }

    public long getFilesize() {
        return _$49430;
    }

    public String getFilename() {
        return _$49461;
    }

    public String getFileextension() {
        return _$49470;
    }

    public String getStoreinfo() {
        return _$49439;
    }

    public String getContenttype() {
        return _$49449;
    }

    private int _$49419;
    private long _$49430;
    private String _$49439;
    private String _$49449;
    private String _$49461;
    private String _$49470;
}
