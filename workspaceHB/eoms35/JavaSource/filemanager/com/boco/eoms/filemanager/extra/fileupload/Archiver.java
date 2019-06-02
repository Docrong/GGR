
package com.boco.eoms.filemanager.extra.fileupload;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

// Referenced classes of package javazoom.upload:
//            UploadException, UploadFile

public class Archiver {

    private Archiver() {
    }

    public static synchronized Archiver getInstance() {
        if (_$1790 == null)
            _$1790 = new Archiver();
        return _$1790;
    }

    public synchronized boolean append(String zipFile, UploadFile file, int maxFiles)
            throws IOException, UploadException {
        java.io.File f = new java.io.File(zipFile);
        java.io.File tmpf = new java.io.File(String.valueOf(String.valueOf(zipFile)).concat(".tmp.zip"));
        if (f.renameTo(tmpf)) {
            f.delete();
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipFile zf = new ZipFile(tmpf);
            Enumeration e = zf.entries();
            ZipEntry ze = null;
            int amount = 0;
            boolean found = false;
            do {
                if (!e.hasMoreElements())
                    break;
                ze = (ZipEntry) e.nextElement();
                if (!ze.getName().equals("EmptyFile")) {
                    InputStream in = zf.getInputStream(ze);
                    if (ze.getName().equals(file.getFileName()))
                        found = true;
                    zos.putNextEntry(ze);
                    byte buffer[] = new byte[1024];
                    for (int bytesRead = 0; (bytesRead = in.read(buffer)) != -1;)
                        zos.write(buffer, 0, bytesRead);

                    zos.closeEntry();
                    in.close();
                    amount++;
                }
            } while (true);
            zf.close();
            if (amount < maxFiles) {
                if (!found)
                    zos.putNextEntry(new ZipEntry(file.getFileName()));
                else
                    zos.putNextEntry(new ZipEntry(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(file.getFileName())))).append(".").append(System.currentTimeMillis())))));
                zos.write(file.getData());
                zos.closeEntry();
                zos.flush();
                zos.close();
                tmpf.delete();
                return true;
            } else {
                zos.flush();
                zos.close();
                tmpf.delete();
                throw new UploadException(UploadException.UPLOADLIMITREACHED);
            }
        } else {
            throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADSTORENOTFOUND)))).append(":").append(f.getName()))));
        }
    }

    private static Archiver _$1790 = null;

}
