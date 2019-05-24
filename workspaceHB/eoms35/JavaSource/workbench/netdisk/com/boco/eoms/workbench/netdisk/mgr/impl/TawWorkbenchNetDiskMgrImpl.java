package com.boco.eoms.workbench.netdisk.mgr.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;
import com.boco.eoms.workbench.netdisk.mgr.ITawWorkbenchNetDiskMgr;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderMapping;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;
import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;
import com.boco.eoms.workbench.netdisk.webapp.util.NetDiskAttriubuteLocator;

public class TawWorkbenchNetDiskMgrImpl implements ITawWorkbenchNetDiskMgr {

	private ITawWorkbenchNetDiskDao tawWorkbenchNetDiskDao;

	public ITawWorkbenchNetDiskDao getTawWorkbenchNetDiskDao() {
		return tawWorkbenchNetDiskDao;
	}

	public void setTawWorkbenchNetDiskDao(
			ITawWorkbenchNetDiskDao tawWorkbenchNetDiskDao) {
		this.tawWorkbenchNetDiskDao = tawWorkbenchNetDiskDao;
	}

	public float getFreeSpace(String userId, float maxSize) {
		return 0;
	}

	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String id) {
		return tawWorkbenchNetDiskDao.getTawWorkbenchNetDiskFolderShare(id);
	}

	public List listMyShare(String fromUserId) {
		return tawWorkbenchNetDiskDao.listMyShare(fromUserId);
	}

	public List listShareToMeUsers(String toUserId) {
		return tawWorkbenchNetDiskDao.listShareToMeUsers(toUserId);
	}

	public List listShareToMe(String fromUserId, String toUserId) {
		return tawWorkbenchNetDiskDao.listShareToMe(fromUserId, toUserId);
	}

	public void removeTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare) {
		tawWorkbenchNetDiskDao
				.removeTawWorkbenchFolderShare(tawWorkbenchNetDiskFolderShare);
	}

	public void saveTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare) {
		tawWorkbenchNetDiskDao
				.saveTawWorkbenchFolderShare(tawWorkbenchNetDiskFolderShare);
	}

	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String fromUserId, String folderPath) {
		return tawWorkbenchNetDiskDao.getTawWorkbenchNetDiskFolderShare(
				fromUserId, folderPath);
	}

	public boolean removeFolder(String rootPath, String folderMappingId,
			String userId) {
		TawWorkbenchNetDiskFolderShare shareInfo = getTawWorkbenchNetDiskFolderShare(
				userId, folderMappingId);
		removeTawWorkbenchFolderShare(shareInfo);
		delFolderMapping(folderMappingId);
		File folder = new File(rootPath + File.separator + userId
				+ File.separator + folderMappingId);
		try {
			folder.delete();

		} catch (Exception e) {
			// return false;
		}
		List subFolders = listsubFolders(folderMappingId);
		for (int i = 0; i < subFolders.size(); i++) {
			TawWorkbenchNetDiskFolderMapping folderMapping = (TawWorkbenchNetDiskFolderMapping) subFolders
					.get(i);
			removeFolder(rootPath, folderMapping.getId(), userId);
		}
		return true;
	}

	public boolean renameFolder(File currentFolder, File newFolder,
			String userId) {
		// 相对路径
		String currentPath = FilePathProcessor.getRelativePath(userId,
				currentFolder.getPath());
		String newPath = FilePathProcessor.getRelativePath(userId, newFolder
				.getPath());
		try {
			currentFolder.renameTo(newFolder);
		} catch (Exception e) {
			return false;
		}
		// 包括自己在内的子文件夹共享信息列表
		List subFolders = listMyShareInfoIncludeSub(userId, currentPath);
		for (int i = 0; i < subFolders.size(); i++) {
			TawWorkbenchNetDiskFolderShare shareInfo = (TawWorkbenchNetDiskFolderShare) subFolders
					.get(i);
			shareInfo.setShareFolderPath(shareInfo.getShareFolderPath()
					.replaceFirst(currentPath, newPath));
			saveTawWorkbenchFolderShare(shareInfo);
		}
		return true;
	}

	private List listMyShareInfoIncludeSub(String userId, String folderPath) {
		List myShareList = tawWorkbenchNetDiskDao.listMyShare(userId);
		List myShareSubList = new ArrayList();
		for (int i = 0; i < myShareList.size(); i++) {
			TawWorkbenchNetDiskFolderShare shareInfo = (TawWorkbenchNetDiskFolderShare) myShareList
					.get(i);
			if (shareInfo.getShareFolderPath().startsWith(folderPath)) {
				myShareSubList.add(shareInfo);
			}
		}
		return myShareSubList;
	}

	public void delFolderMapping(String id) {
		tawWorkbenchNetDiskDao.delFolderMapping(id);
	}

	public String getFolderNameFromMapping(String id) {
		return tawWorkbenchNetDiskDao.getFolderNameFromMapping(id);
	}

	public String getMappingId(String userId, String folderName, String parentId) {
		return tawWorkbenchNetDiskDao
				.getMappingId(userId, folderName, parentId);
	}

	public String saveFolderMapping(TawWorkbenchNetDiskFolderMapping mapping) {
		return tawWorkbenchNetDiskDao.saveFolderMapping(mapping);
	}

	public List listsubFolders(String parentId) {
		return tawWorkbenchNetDiskDao.listsubFolders(parentId);
	}

	public String initUserRootFolder(String userId, String userRootPath) {
		TawWorkbenchNetDiskFolderMapping folderMapping = new TawWorkbenchNetDiskFolderMapping();
		File file = new File(userRootPath);
		String mappingId = getMappingId(userId, userId, "0");
		if (!file.exists()) {
			file.mkdirs();
		}
		if (mappingId == null || "".equals(mappingId)) {
			folderMapping.setUserId(userId);
			folderMapping.setFolderName(userId);
			folderMapping.setParentId("0");
			mappingId = saveFolderMapping(folderMapping);
		}
		return mappingId;
	}

	public TawWorkbenchNetDiskFolderMapping geTawWorkbenchNetDiskFolderMapping(
			String id) {
		return tawWorkbenchNetDiskDao.geTawWorkbenchNetDiskFolderMapping(id);
	}

	public void removeFolderShareInfo(String folderPath, String fromUserId) {
		tawWorkbenchNetDiskDao.removeFolderShareInfo(folderPath, fromUserId);
	}

	public void delTawWorkbenchNetDiskFile(String userId,
			String folderMappingId, String mappingName) {
		TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile = searchTawWorkbenchNetDiskFile(
				userId, mappingName);
		tawWorkbenchNetDiskDao
				.delTawWorkbenchNetDiskFile(tawWorkbenchNetDiskFile);
		String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
				.getNetDiskRootPath();
		String filePath = rootPath + File.separator + userId + File.separator
				+ folderMappingId + File.separator + mappingName;
		File file = new File(filePath);
		try {
			FileUtils.forceDelete(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TawWorkbenchNetDiskFile searchTawWorkbenchNetDiskFile(String userId,
			String mappingName) {
		TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile = tawWorkbenchNetDiskDao
				.getTawWorkbenchNetDiskFile(userId, mappingName);
		if (tawWorkbenchNetDiskFile.getId() != null
				&& !"".equals(tawWorkbenchNetDiskFile.getId())) {
			String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
					.getNetDiskRootPath();
			String filePath = rootPath + File.separator
					+ tawWorkbenchNetDiskFile.getUserId() + File.separator
					+ tawWorkbenchNetDiskFile.getFolderMappingId()
					+ File.separator + tawWorkbenchNetDiskFile.getMappingName();
			File file = new File(filePath);
			if (!file.exists()) {
				tawWorkbenchNetDiskDao
						.delTawWorkbenchNetDiskFile(tawWorkbenchNetDiskFile);
				return new TawWorkbenchNetDiskFile();
			}
		}
		return tawWorkbenchNetDiskFile;
	}

	public List listTawWorkbenchNetDiskFile(String userId,
			String folderMappingId) {
		List list = tawWorkbenchNetDiskDao.listTawWorkbenchNetDiskFile(userId,
				folderMappingId);
		String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
				.getNetDiskRootPath();
		for (int i = 0; i < list.size(); i++) {
			TawWorkbenchNetDiskFile netDiskFile = (TawWorkbenchNetDiskFile) list
					.get(i);
			File file = new File(rootPath + File.separator
					+ netDiskFile.getUserId() + File.separator
					+ netDiskFile.getFolderMappingId() + File.separator
					+ netDiskFile.getMappingName());
			if (!file.exists()) {
				list.remove(i);
				tawWorkbenchNetDiskDao.delTawWorkbenchNetDiskFile(netDiskFile);
			}
		}
		return list;
	}

	public void saveTawWorkbenchNetDiskFile(
			TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile) {
		tawWorkbenchNetDiskDao
				.saveTawWorkbenchNetDiskFile(tawWorkbenchNetDiskFile);
	}

	public Integer getNumOfFolder(String nodeId) {
		return tawWorkbenchNetDiskDao.getNumOfFolder(nodeId);
	}

	public Integer getNumOfMyShareFolder(String nodeId) {
		return tawWorkbenchNetDiskDao.getNumOfMyShareFolder(nodeId);
	}

	public Integer getNumOfShareToMeFolder(String nodeId) {
		return tawWorkbenchNetDiskDao.getNumOfShareToMeFolder(nodeId);
	}

	public List searchByConditions(String userid, String fileName, String uploadStratTime, String uploadEndTime, String uploadUsers) {
		List returnList = new ArrayList();
		//只有当用户没有选择上传用户或者上传用户包含用户本人时，才去查询自己的文件。
		if (uploadUsers.equals("")) {
			List myFilesList = tawWorkbenchNetDiskDao.searchMyFilesByConditions(fileName, uploadStratTime, uploadEndTime, userid);
			returnList.addAll(myFilesList);
		} else {
			String[] user = uploadUsers.split(",");
			StringBuffer sbUsers = new StringBuffer();
			for (int i=0;i<user.length;i++) {
				if(user[i].equals(userid)) {
					List myFilesList = tawWorkbenchNetDiskDao.searchMyFilesByConditions(fileName, uploadStratTime, uploadEndTime, userid);
					returnList.addAll(myFilesList);
				}
			}
		}
		//查询共享给自己的符合条件的文件
		List shareToMeFilesList = tawWorkbenchNetDiskDao.searchShareToMeFilesByConditions(fileName, uploadStratTime, uploadEndTime, uploadUsers, userid);
		returnList.addAll(shareToMeFilesList);
		return returnList;
	}
	
}
