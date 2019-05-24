package com.boco.eoms.workbench.netdisk.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderMapping;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;

public class TawWorkbenchNetDiskDaoHibernate extends BaseDaoHibernate implements
		ITawWorkbenchNetDiskDao {

	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String id) {
		TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare = (TawWorkbenchNetDiskFolderShare) getHibernateTemplate()
				.get(TawWorkbenchNetDiskFolderShare.class, id);
		if (tawWorkbenchNetDiskFolderShare == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchNetDiskFolderShare.class, id);
		}
		return tawWorkbenchNetDiskFolderShare;
	}

	public void saveTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare) {
		if (tawWorkbenchNetDiskFolderShare.getId() == null
				|| "".equals(tawWorkbenchNetDiskFolderShare.getId())) {
			getHibernateTemplate().save(tawWorkbenchNetDiskFolderShare);
		} else {
			getHibernateTemplate().saveOrUpdate(tawWorkbenchNetDiskFolderShare);
		}
	}

	public void removeTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare) {
		if (tawWorkbenchNetDiskFolderShare.getId() != null
				&& !"".equals(tawWorkbenchNetDiskFolderShare.getId())) {
			getHibernateTemplate().delete(tawWorkbenchNetDiskFolderShare);
		}
	}

	public List listMyShare(String fromUserId) {
		String hql = " from TawWorkbenchNetDiskFolderShare foldershare where foldershare.fromUserId='"
				+ fromUserId + "'";
		return getHibernateTemplate().find(hql);
	}

	public List listShareToMeUsers(String toUserId) {
		String hql = "select distinct(foldershare.fromUserId),foldershare.fromUserName from TawWorkbenchNetDiskFolderShare foldershare where (foldershare.toUserId like('%"
				+ toUserId
				+ "%') or foldershare.toUserId='all_users') and foldershare.fromUserId!='"
				+ toUserId + "'";
		return getHibernateTemplate().find(hql);
	}

	public List listShareToMe(String fromUserId, String toUserId) {
		String hql = " from TawWorkbenchNetDiskFolderShare foldershare where (foldershare.toUserId like('%"
				+ toUserId
				+ "%') and fromUserId='"
				+ fromUserId
				+ "') or (foldershare.toUserId='all_users' and fromUserId='"
				+ fromUserId + "')";
		return getHibernateTemplate().find(hql);
	}

	public void removeFolderShareInfo(String folderPath, String fromUserId) {
		String hql = " from TawWorkbenchNetDiskFolderShare foldershare where foldershare.shareFolderPath='"
				+ folderPath
				+ "' and foldershare.fromUserId='"
				+ fromUserId
				+ "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawWorkbenchNetDiskFolderShare shareInfo = new TawWorkbenchNetDiskFolderShare();
				for (int i = 0; i < list.size(); i++) {
					shareInfo = (TawWorkbenchNetDiskFolderShare) list.get(i);
					getHibernateTemplate().delete(shareInfo);
				}
			}
		}
	}

	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String fromUserId, String folderPath) {
		String hql = " from TawWorkbenchNetDiskFolderShare foldershare where foldershare.shareFolderPath='"
				+ folderPath
				+ "' and foldershare.fromUserId='"
				+ fromUserId
				+ "'";
		TawWorkbenchNetDiskFolderShare folderShareInfo = new TawWorkbenchNetDiskFolderShare();
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				folderShareInfo = (TawWorkbenchNetDiskFolderShare) list.get(0);
			}
		}
		return folderShareInfo;
	}

	public void delFolderMapping(String id) {
		TawWorkbenchNetDiskFolderMapping mapping = geTawWorkbenchNetDiskFolderMapping(id);
		getHibernateTemplate().delete(mapping);
	}

	public String getFolderNameFromMapping(String id) {
		String folderPath = "";
		TawWorkbenchNetDiskFolderMapping mapping = geTawWorkbenchNetDiskFolderMapping(id);
		if (mapping.getFolderName() != null
				&& !"".equals(mapping.getFolderName())) {
			folderPath = mapping.getFolderName();
		}
		return folderPath;
	}

	public String getMappingId(String userId, String folderName, String parentId) {
		String id = "";
		String hql = " from TawWorkbenchNetDiskFolderMapping mapping where mapping.userId='"
				+ userId
				+ "' and mapping.folderName='"
				+ folderName
				+ "' and mapping.parentId='" + parentId + "'";
		TawWorkbenchNetDiskFolderMapping mapping = new TawWorkbenchNetDiskFolderMapping();
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				mapping = (TawWorkbenchNetDiskFolderMapping) list.get(0);
				id = mapping.getId();
			}
		}
		return id;
	}

	public String saveFolderMapping(TawWorkbenchNetDiskFolderMapping mapping) {
		if (mapping.getId() == null || "".equals(mapping.getId())) {
			getHibernateTemplate().save(mapping);
		} else {
			getHibernateTemplate().saveOrUpdate(mapping);
		}
		return mapping.getId();
	}

	public TawWorkbenchNetDiskFolderMapping geTawWorkbenchNetDiskFolderMapping(
			String id) {
		TawWorkbenchNetDiskFolderMapping tawWorkbenchNetDiskFolderMapping = (TawWorkbenchNetDiskFolderMapping) getHibernateTemplate()
				.get(TawWorkbenchNetDiskFolderMapping.class, id);
		if (tawWorkbenchNetDiskFolderMapping == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchNetDiskFolderMapping.class, id);
		}
		return tawWorkbenchNetDiskFolderMapping;
	}

	public List listsubFolders(String parentId) {
		String hql = " from TawWorkbenchNetDiskFolderMapping mapping where mapping.parentId='"
				+ parentId + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	public void delTawWorkbenchNetDiskFile(
			TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile) {
		if (tawWorkbenchNetDiskFile.getId() != null
				&& !"".equals(tawWorkbenchNetDiskFile.getId())) {
			getHibernateTemplate().delete(tawWorkbenchNetDiskFile);
		}
	}

	public TawWorkbenchNetDiskFile getTawWorkbenchNetDiskFile(String userId,
			String mappingName) {
		String hql = " from TawWorkbenchNetDiskFile file where file.userId='"
				+ userId + "' and file.mappingName='" + mappingName + "'";
		TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile = new TawWorkbenchNetDiskFile();
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				tawWorkbenchNetDiskFile = (TawWorkbenchNetDiskFile) list.get(0);
			}
		}
		return tawWorkbenchNetDiskFile;
	}

	public List listTawWorkbenchNetDiskFile(String userId,
			String folderMappingId) {
		String hql = " from TawWorkbenchNetDiskFile file where file.userId='"
				+ userId + "' and file.folderMappingId='" + folderMappingId
				+ "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	public void saveTawWorkbenchNetDiskFile(
			TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile) {
		if (tawWorkbenchNetDiskFile.getId() == null
				|| "".equals(tawWorkbenchNetDiskFile.getId())) {
			getHibernateTemplate().save(tawWorkbenchNetDiskFile);
		} else {
			getHibernateTemplate().saveOrUpdate(tawWorkbenchNetDiskFile);
		}
	}

	public Integer getNumOfFolder(String nodeId) {
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find("select count(id) from TawWorkbenchNetDiskFile file where folderMappingId=?",nodeId);
		return (Integer)list.get(0);
	}

	public Integer getNumOfMyShareFolder(String nodeId) {
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find("select count(file.id) from TawWorkbenchNetDiskFolderShare sharefile,TawWorkbenchNetDiskFile file where sharefile.shareFolderPath=file.folderMappingId and sharefile.shareFolderPath=? ",nodeId);
		return (Integer)list.get(0);
	}

	public Integer getNumOfShareToMeFolder(String nodeId) {
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find("select count(file.id) from TawWorkbenchNetDiskFolderShare sharefile,TawWorkbenchNetDiskFile file where sharefile.shareFolderPath=file.folderMappingId and sharefile.shareFolderPath=? ",nodeId);
		return (Integer)list.get(0);
	}

	public List searchMyFilesByConditions(String fileName, String uploadStratTime, String uploadEndTime, String uploadUser) {
		StringBuffer sb = new StringBuffer("from TawWorkbenchNetDiskFile file where userId = ? ");
		List objectList = new ArrayList();
		objectList.add(uploadUser);
		if (!fileName.equals("")) {
			sb.append("and fileName like ? ");
			objectList.add("%"+fileName+"%");
		}
		if (!uploadStratTime.equals("")) {
			sb.append("and uploadTime > ? ");
			objectList.add(uploadStratTime);
		}
		if (!uploadEndTime.equals("")) {
			sb.append("and uploadTime < ? ");
			objectList.add(uploadEndTime);
		}
		//Object[] obj = {"%"+fileName+"%",uploadStratTime,uploadEndTime,uploadUser};
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(sb.toString(),objectList.toArray());
		return list;
	}

	public List searchShareToMeFilesByConditions(String fileName, String uploadStratTime, String uploadEndTime, String uploadUsers, String searchUser) {
		StringBuffer sb = new StringBuffer("select file from TawWorkbenchNetDiskFile file,TawWorkbenchNetDiskFolderShare sharefile where sharefile.shareFolderPath=file.folderMappingId and (sharefile.toUserId = ? or (sharefile.toUserId = 'all_users' and sharefile.fromUserId != '"+searchUser+"'))");
		List objectList = new ArrayList();
		objectList.add(searchUser);
		if (!fileName.equals("")) {
			sb.append("and file.fileName like ? ");
			objectList.add("%"+fileName+"%");
		}
		if (!uploadStratTime.equals("")) {
			sb.append("and file.uploadTime > ? ");
			objectList.add(uploadStratTime);
		}
		if (!uploadEndTime.equals("")) {
			sb.append("and file.uploadTime < ? ");
			objectList.add(uploadEndTime);
		}
		if (!uploadUsers.equals("")) {
			String[] user = uploadUsers.split(",");
			StringBuffer sbUsers = new StringBuffer();
			for (int i=0;i<user.length;i++) {
				sbUsers.append("'"+user[i]+"',");
			}
			sb.append("and sharefile.fromUserId in ("+sbUsers.substring(0, sbUsers.length()-1)+") ");
			//objectList.add(uploadUsers);
		}
		//Object[] obj = {"%"+fileName+"%",uploadStratTime,uploadEndTime,uploadUser};
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(sb.toString(),objectList.toArray());
		return list;
	}

}
