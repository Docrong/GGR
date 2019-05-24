/*
 * Copyright HBT Co,.Ltd.(2000-2002). Allrights Reserved
 * User: lizhengyou
 * Date: May 21, 2002
 * Time: 3:36:10 PM
 *
 * $Id
 */
// Decompiled by Jad v1.5.7d. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   UploadException.java

package com.boco.eoms.filemanager.extra.fileupload;


public class UploadException extends Exception {

    public UploadException() {
    }

    public UploadException(String msg) {
        super(msg);
    }

    public static String UNKNOWNSTOREMODEL = "Unknown StoreModel";
    public static String INVALIDREQUEST = "Invalid input request";
    public static String UPLOADERROR = "Upload error";
    public static String FOLDERISREADONLY = "Folder is read only";
    public static String CANNOTCREATEFOLDER = "Cannot create folder";
    public static String UPLOADLIMITREACHED = "Upload files limit reached";
    public static String UPLOADFILESIZELIMITREACHED = "Upload file size limit reached";
    public static String UPLOADFILENAMEDENIED = "Upload filename not allowed";
    public static String CANNOTDELETESTORE = "Cannot delete store";
    public static String UPLOADSTORENOTFOUND = "Store not found";
    public static String DATABASESTOREERROR = "Database store error";

}
