package com.boco.eoms.workbench.netdisk.mgr;

import java.io.File;
import java.util.List;

import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderMapping;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;

/**
 * 
 * <p>
 * Title:网络U盘
 * </p>
 * <p>
 * Description:个人文件和文件夹管理，包括文件夹共享。
 * </p>
 * <p>
 * Date:2008-4-30 15:58:43
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface ITawWorkbenchNetDiskMgr {

	/**
	 * 根据Id获取文件夹共享信息
	 * 
	 * @param id
	 *            文件夹共享数据库表主键
	 */
	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String id);

	/**
	 * 保存文件夹共享信息
	 * 
	 * @param tawWorkbenchNetDiskFolderShare
	 *            文件夹共享信息类
	 */
	public void saveTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare);

	/**
	 * 删除文件夹共享信息
	 * 
	 * @param tawWorkbenchNetDiskFolderShare
	 *            文件夹共享信息类
	 * 
	 */
	public void removeTawWorkbenchFolderShare(
			TawWorkbenchNetDiskFolderShare tawWorkbenchNetDiskFolderShare);

	/**
	 * 获取共享文件夹给我的全部用户
	 * 
	 * @param toUserId
	 *            接受共享的用户ID
	 * @return 共享给我文件夹的用户列表,list中的对象有两个属性,分别为用户ID,用户名
	 */
	public List listShareToMeUsers(String toUserId);

	/**
	 * 获取某用户共享给我的全部文件夹
	 * 
	 * @param fromUserId
	 *            共享来源用户ID
	 * @param toUserId
	 *            共享目标用户ID
	 * @return 共享信息列表
	 */
	public List listShareToMe(String fromUserId, String toUserId);

	/**
	 * 获取我共享的全部文件夹
	 * 
	 * @param fromUserId
	 *            共享人ID
	 * @return 共享信息列表
	 */
	public List listMyShare(String fromUserId);

	/**
	 * 根据文件夹所属用户Id和文件夹路径获取文件夹共享信息
	 * 
	 * @param fromUserId
	 *            共享人ID
	 * @param folderPath
	 *            文件夹路径(hibernate32位Id)
	 * @return 文件夹共享信息
	 */
	public TawWorkbenchNetDiskFolderShare getTawWorkbenchNetDiskFolderShare(
			String fromUserId, String folderPath);

	/**
	 * 重命名文件夹
	 * 
	 * @param currentFolder
	 *            当前文件夹
	 * @param newFolder
	 *            新文件夹
	 * @param userId
	 *            所属用户Id
	 */
	public boolean renameFolder(File currentFolder, File newFolder,
			String userId);

	/**
	 * 删除文件夹
	 * 
	 * @param rootPath
	 *            网络U盘根路径
	 * @param folderMappingId
	 *            文件夹映射ID
	 * @param userId
	 *            用户ID
	 * @return 删除操作结果
	 */
	public boolean removeFolder(String rootPath, String folderMappingId,
			String userId);

	/**
	 * 从映射中查询文件夹名
	 * 
	 * @param id
	 *            主键（也是在服务器上实际创建的文件夹名）
	 * @return 文件夹名称
	 */
	public String getFolderNameFromMapping(String id);

	/**
	 * 根据用户ID和虚拟路径查询主键，即真实路径
	 * 
	 * @param userId
	 *            用户ID
	 * @param folderName
	 *            文件夹名称
	 * @return 文件夹相对路径(hibernate32位Id)
	 */
	public String getMappingId(String userId, String folderName, String parentId);

	/**
	 * 保存映射关系
	 * 
	 * @param mapping
	 *            映射
	 * @return ID主键
	 */
	public String saveFolderMapping(TawWorkbenchNetDiskFolderMapping mapping);

	/**
	 * 根据主键删除映射关系
	 * 
	 * @param id
	 *            主键
	 */
	public void delFolderMapping(String id);

	/**
	 * 获取子文件夹列表
	 * 
	 * @param parentId
	 *            父文件夹Id
	 */
	public List listsubFolders(String parentId);

	/**
	 * 初始化用户根文件夹,返回文件夹映射Id
	 * 
	 * @param userId
	 *            用户ID
	 * @param userRootPath
	 *            根文件夹路径
	 * @return 根文件夹映射Id
	 */
	public String initUserRootFolder(String userId, String userRootPath);

	/**
	 * 根据主键查询映射信息
	 * 
	 * @param id
	 *            主键(文件夹真实的相对路径)
	 * @return 文件夹映射信息
	 */
	public TawWorkbenchNetDiskFolderMapping geTawWorkbenchNetDiskFolderMapping(
			String id);

	/**
	 * 删除某用户某文件夹的所有共享信息
	 * 
	 * @param folderPath
	 *            文件夹路径(真实的相对路径,即映射表的Id)
	 * @param fromUserId
	 *            共享人ID
	 */
	public void removeFolderShareInfo(String folderPath, String fromUserId);

	/**
	 * 保存上传的文件信息到数据库
	 * 
	 * @param tawWorkbenchNetDiskFile
	 *            文件类
	 */
	public void saveTawWorkbenchNetDiskFile(
			TawWorkbenchNetDiskFile tawWorkbenchNetDiskFile);

	/**
	 * 通过所属用户ID和映射文件名查询文件信息,映射文件名是精确到毫秒的时间
	 * 
	 * @param userId
	 *            所属用户ID
	 * @param mappingName
	 *            映射文件名
	 * @return 文件类
	 */
	public TawWorkbenchNetDiskFile searchTawWorkbenchNetDiskFile(String userId,
			String mappingName);

	/**
	 * 查询某用户某文件夹下的文件列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param folderMappingId
	 *            文件夹映射ID
	 * @return 文件列表
	 */
	public List listTawWorkbenchNetDiskFile(String userId,
			String folderMappingId);

	/**
	 * 删除数据库文件信息，并从服务器上删除文件
	 * 
	 * @param userId
	 *            用户ID
	 * @param folderMappingId
	 *            文件夹映射ID
	 * @param mappingName
	 *            映射文件名
	 */
	public void delTawWorkbenchNetDiskFile(String userId,
			String folderMappingId, String mappingName);
	
	/**
	 * 返回文件夹中文件的个数
	 * @author wangbeiying
	 * @param nodeId
	 * @return 
	 */
	public Integer getNumOfFolder(String nodeId);
	
	/**
	 * 返回我共享的文件夹中的文件个数
	 * @author wangbeiying
	 * @param nodeId
	 * @return 
	 */
	public Integer getNumOfMyShareFolder(String nodeId);
	
	/**
	 * 返回共享给我的文件夹中的文件个数
	 * @author wangbeiying
	 * @param nodeId
	 * @return 
	 */
	public Integer getNumOfShareToMeFolder(String nodeId);
	
	/**
	 * 返回查询结果集
	 * @author wangbeiying
	 * @param userid
	 * @param fileName
	 * @param uploadStratTime
	 * @param uploadEndTime
	 * @param uploadUser
	 * @return
	 */
	public List searchByConditions(String userid, String fileName,String uploadStratTime,String uploadEndTime,String uploadUser);

}

