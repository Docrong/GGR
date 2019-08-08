package com.boco.eoms.km.file.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.file.model.KmFile;
import com.boco.eoms.km.file.model.KmFileHis;
import com.boco.eoms.km.file.mgr.KmFileMgr;
import com.boco.eoms.km.file.dao.KmFileDao;

/**
 * <p>
 * Title:文件管理
 * </p>
 * <p>
 * Description:文件管理
 * </p>
 * <p>
 * Wed Mar 25 11:32:18 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmFileMgrImpl implements KmFileMgr {

    private KmFileDao fileDao;

    public KmFileDao getFileDao() {
        return this.fileDao;
    }

    public void setFileDao(KmFileDao fileDao) {
        this.fileDao = fileDao;
    }

    public void moveFileByNode(String fromNodeId, String toNodeId) {
        //如果删除的是根分类，则将所有附件的删除标记置为1
        //if (KmFileTreeConstants.TREE_ROOT_ID.equals(toNodeId)) {
        fileDao.removeFileByNodeId(fromNodeId);
        //}
        //如果删除的不是根分类，则将所有附件的删除标记置为1
        //else{
        //	fileDao.moveFileByNode(fromNodeId, toNodeId);
        //}
    }

    public List getFiles() {
        return fileDao.getFiles();
    }

    public KmFile getFile(final String id) {
        return fileDao.getFile(id);
    }

    public KmFileHis getFileHis(String id) {
        return fileDao.getFileHis(id);
    }

    public void saveFile(KmFile file) {
        fileDao.saveFile(file);
    }

    public void saveFileHis(KmFileHis fileHis) {
        fileDao.saveFileHis(fileHis);
    }

    public void removeFile(final String id) {
        fileDao.removeFile(id);
    }

    public Map getFiles(final Integer curPage, final Integer pageSize, final String whereStr, final String orderBy) {
        return fileDao.getFiles(curPage, pageSize, whereStr, orderBy);
    }

    public List getKmFileHistoryList(final String id) {
        return fileDao.getKmFileHistoryList(id);
    }

    public void removeFileByNodeId(final String nodeId) {
        fileDao.removeFileByNodeId(nodeId);
    }
}