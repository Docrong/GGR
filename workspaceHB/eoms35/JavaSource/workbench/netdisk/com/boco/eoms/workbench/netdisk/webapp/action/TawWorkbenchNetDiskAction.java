package com.boco.eoms.workbench.netdisk.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.boco.eoms.workbench.netdisk.fileupload.UploadListener;
import com.boco.eoms.workbench.netdisk.fileupload.UploadProcessor;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.netdisk.mgr.ITawWorkbenchNetDiskMgr;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFile;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderMapping;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;
import com.boco.eoms.workbench.netdisk.webapp.form.TawWorkbenchFolderForm;
import com.boco.eoms.workbench.netdisk.webapp.form.TawWorkbenchNetDiskFileForm;
import com.boco.eoms.workbench.netdisk.webapp.util.NetDiskAttriubuteLocator;

public final class TawWorkbenchNetDiskAction extends BaseAction {

    /**
     * 未指定方法
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return netDiskMain(mapping, form, request, response);
    }

    /**
     * 网络U盘主界面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward netDiskMain(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
                .getNetDiskRootPath();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userRootPath = rootPath + File.separator
                + sessionform.getUserid();
        File file = new File(userRootPath);
        // 如果用户根路径不存在,则创建
        String folderMappingId = netdiskMgr.initUserRootFolder(sessionform
                .getUserid(), userRootPath);
        request.getSession().setAttribute("userRootPath", file.getPath());
        request.getSession().setAttribute("folderMappingId", folderMappingId);
        return mapping.findForward("netDiskMain");
    }

    /**
     * 新建文件夹
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newFolder(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String folderPath = request.getParameter("folderPath");
        request.setAttribute("folderName", "");
        request.setAttribute("folderPath", folderPath);
        request.setAttribute("operType", "new");
        return mapping.findForward("folderEdit");
    }

    /**
     * 修改文件夹
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editFolder(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        String folderPath = request.getParameter("folderPath");
        String folderName = netdiskMgr.getFolderNameFromMapping(folderPath);
        request.setAttribute("folderName", folderName);
        request.setAttribute("folderPath", folderPath);
        request.setAttribute("operType", "edit");
        return mapping.findForward("folderEdit");
    }

    /**
     * 保存文件夹
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveFolder(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userRootPath = request.getSession().getAttribute("userRootPath")
                .toString();
        String userId = sessionform.getUserid();
        String operType = request.getParameter("operType");
        TawWorkbenchFolderForm folderForm = (TawWorkbenchFolderForm) form;
        BocoLog.debug(this, "current path is:" + folderForm.getFolderPath());
        BocoLog.debug(this, "new folderName is:" + folderForm.getFolderName());
        String newPath = "";
        File newFolder = null;
        BocoLog.debug(this, "Operation type is:" + operType);
        if ("new".equals(operType)) {
            String mappingId = netdiskMgr.getMappingId(userId, folderForm
                    .getFolderName(), folderForm.getFolderPath());
            if (mappingId != null && !"".equals(mappingId)) {
                request.setAttribute("failInfo", "文件夹<"
                        + folderForm.getFolderName() + ">已经存在, 新建文件夹失败！");
                return mapping.findForward("fail");
            }
            TawWorkbenchNetDiskFolderMapping folderMapping = new TawWorkbenchNetDiskFolderMapping();
            folderMapping.setFolderName(folderForm.getFolderName());
            folderMapping.setUserId(userId);
            folderMapping.setParentId(folderForm.getFolderPath());
            String id = netdiskMgr.saveFolderMapping(folderMapping);
            newPath = userRootPath + File.separator + id;
            newFolder = new File(newPath);
            try {
                BocoLog
                        .debug(this, "New folder path is:"
                                + newFolder.getPath());
                boolean result = newFolder.mkdir();
                BocoLog.debug(this, "Result of creating folder is:" + result);
            } catch (Exception e) {
                netdiskMgr.delFolderMapping(id);
                request.setAttribute("failInfo", "新建文件夹失败！");
                return mapping.findForward("fail");
            }
        } else if ("edit".equals(operType)) {
            BocoLog.debug(this, "Go into edit folder operation...");
            TawWorkbenchNetDiskFolderMapping folderMapping = netdiskMgr
                    .geTawWorkbenchNetDiskFolderMapping(folderForm
                            .getFolderPath());
            if (folderForm.getFolderName()
                    .equals(folderMapping.getFolderName())) {
                request.setAttribute("failInfo", "文件夹<"
                        + folderForm.getFolderName() + ">已经存在！");
                return mapping.findForward("fail");
            }
            folderMapping.setFolderName(folderForm.getFolderName());
            netdiskMgr.saveFolderMapping(folderMapping);
        }
        return mapping.findForward("success");
    }

    /**
     * 生成文件夹树JSON数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getNodes(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = request.getParameter("node");
        BocoLog.debug(this, "Init nodeId is:" + request.getParameter("node"));
        BocoLog.debug(this, "recovered nodeId is:" + nodeId);
        String folderMappingId = request.getSession().getAttribute(
                "folderMappingId").toString();
        String userRootPath = request.getSession().getAttribute("userRootPath")
                .toString();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        JSONArray jsonRoot = new JSONArray();
        if ("-1".equals(nodeId)) { // 初始化三个节点
            jsonRoot = initNodes(folderMappingId);
        } else {
            String nodeType = request.getParameter(UIConstants.JSON_NODETYPE);
            if ("myFolder".equals(nodeType)) {
                jsonRoot = getMyFolderNodes(nodeId, userRootPath);
            } else if ("myShare".equals(nodeType)) {
                jsonRoot = getMyShareNodes(userId, userRootPath);
            } else if ("shareToMe".equals(nodeType)) {
                jsonRoot = initShareToMeNodes(userId);
            } else if ("shareToMeNodes".equals(nodeType)) {
                jsonRoot = getShareToMeNodes(nodeId, userId);
            }
        }
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    /**
     * 生成文件夹树初始化JSON数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private JSONArray initNodes(String folderMappingId) {
        JSONArray jsonRoot = new JSONArray();
        JSONObject myFolder = new JSONObject();
        myFolder.put("id", folderMappingId);
        myFolder.put("text", "我的文件夹");
        myFolder.put(UIConstants.JSON_NODETYPE, "myFolder");
        myFolder.put("allowChild", true);
        myFolder.put("allowEdit", false);
        myFolder.put("allowDelete", false);
        myFolder.put("allowList", false);
        myFolder.put("allowShare", false);
        myFolder.put("leaf", false);
        myFolder.put("iconCls", "folder");
        JSONObject myShare = new JSONObject();
        myShare.put("id", "myShare");
        myShare.put("text", "我的共享");
        myShare.put(UIConstants.JSON_NODETYPE, "myShare");
        myShare.put("allowChild", false);
        myShare.put("allowEdit", false);
        myShare.put("allowDelete", false);
        myShare.put("allowList", false);
        myShare.put("allowShare", false);
        myShare.put("leaf", false);
        myShare.put("iconCls", "folder");
        JSONObject shareToMe = new JSONObject();
        shareToMe.put("id", "shareToMe");
        shareToMe.put("text", "共享给我");
        shareToMe.put(UIConstants.JSON_NODETYPE, "shareToMe");
        shareToMe.put("allowChild", false);
        shareToMe.put("allowEdit", false);
        shareToMe.put("allowDelete", false);
        shareToMe.put("allowList", false);
        shareToMe.put("allowShare", false);
        shareToMe.put("leaf", false);
        shareToMe.put("iconCls", "folder");
        JSONObject search = new JSONObject();
        search.put("id", "search");
        search.put("text", "查询");
        search.put(UIConstants.JSON_NODETYPE, "search");
        search.put("allowChild", false);
        search.put("allowEdit", false);
        search.put("allowDelete", false);
        search.put("allowList", false);
        search.put("allowShare", false);
        search.put("allowClick", true);
        search.put("leaf", true);
        search.put("iconCls", "file");
        jsonRoot.put(myFolder);
        jsonRoot.put(myShare);
        jsonRoot.put(shareToMe);
        jsonRoot.put(search);
        return jsonRoot;
    }

    /**
     * 初始化共享给我的文件夹节点,初始化结果为username为节点的树
     *
     * @param userId
     * @return
     */
    public JSONArray initShareToMeNodes(String userId) {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        List shareToMeList = netdiskMgr.listShareToMeUsers(userId);
        JSONArray jsonRoot = new JSONArray();
        for (int i = 0; i < shareToMeList.size(); i++) {
            Object[] objects = (Object[]) shareToMeList.get(i);
            JSONObject jitem = new JSONObject();
            jitem.put("id", objects[0].toString());
            jitem.put("text", objects[1].toString());
            jitem.put(UIConstants.JSON_NODETYPE, "shareToMeNodes");
            jitem.put("allowChild", false);
            jitem.put("allowEdit", false);
            jitem.put("allowDelete", false);
            jitem.put("allowList", false);
            jitem.put("allowShare", false);
            jitem.put("leaf", false);
            jitem.put("iconCls", "folder");
            jsonRoot.put(jitem);
        }
        return jsonRoot;
    }

    /**
     * 生成文件夹树JSON数据(我的文件夹)
     *
     * @param nodeId
     * @return
     */
    private JSONArray getMyFolderNodes(String nodeId, String userRootPath) {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        BocoLog.debug(this, "In getMyFolderNodes nodeId is:" + nodeId);
        List subFolderList = netdiskMgr.listsubFolders(nodeId);
        BocoLog.debug(this, "folder list length is:"
                + String.valueOf(subFolderList.size()));
        JSONArray jsonRoot = new JSONArray();
        if (subFolderList == null) {
            return null;
        }
        for (int i = 0; i < subFolderList.size(); i++) {
            TawWorkbenchNetDiskFolderMapping folderMapping = (TawWorkbenchNetDiskFolderMapping) subFolderList
                    .get(i);
            String folderPath = userRootPath + File.separator
                    + folderMapping.getId();
            // 移植可能导致数据库有记录而文件系统中文件夹不存在
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            BocoLog
                    .debug(this, "file name is:"
                            + folderMapping.getFolderName());
            JSONObject jitem = new JSONObject();
            jitem.put("id", folderMapping.getId());
            jitem
                    .put("text", folderMapping.getFolderName() + "("
                            + netdiskMgr.getNumOfFolder(folderMapping.getId())
                            + "个文件)");
            jitem.put(UIConstants.JSON_NODETYPE, "myFolder");
            jitem.put("allowChild", true);
            jitem.put("allowDelete", true);

            // 设置是否叶节点
            List subFolders = netdiskMgr.listsubFolders(folderMapping.getId());
            if (subFolders != null && subFolders.size() > 0) {
                jitem.put("leaf", false);
            } else {
                jitem.put("leaf", true);
            }
            // 设置图标
            jitem.put("iconCls", "folder");

            jsonRoot.put(jitem);
        }
        return jsonRoot;
    }

    /**
     * 生成文件夹树JSON数据(我的共享)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private JSONArray getMyShareNodes(String userId, String userRootPath) {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        List myShareList = netdiskMgr.listMyShare(userId);
        JSONArray jsonRoot = new JSONArray();
        for (int i = 0; i < myShareList.size(); i++) {
            TawWorkbenchNetDiskFolderShare folderShareInfo = (TawWorkbenchNetDiskFolderShare) myShareList
                    .get(i);
            TawWorkbenchNetDiskFolderMapping folderMapping = netdiskMgr
                    .geTawWorkbenchNetDiskFolderMapping(folderShareInfo
                            .getShareFolderPath());
            TawWorkbenchNetDiskFolderMapping parentMapping = netdiskMgr
                    .geTawWorkbenchNetDiskFolderMapping(folderMapping
                            .getParentId());
            String folderName = folderMapping.getFolderName();
            if (!"0".equals(folderMapping.getParentId())) {
                while (!"0".equals(parentMapping.getParentId())
                        && !"".equals(parentMapping.getId())
                        && parentMapping.getId() != null) {
                    TawWorkbenchNetDiskFolderMapping mp = netdiskMgr
                            .geTawWorkbenchNetDiskFolderMapping(parentMapping
                                    .getParentId());
                    folderName = parentMapping.getFolderName() + File.separator
                            + folderName;
                    parentMapping = mp;
                }
            }
            File shareFolder = new File(userRootPath + File.separator
                    + folderMapping.getId());
            if (!shareFolder.exists()) {
                netdiskMgr.removeTawWorkbenchFolderShare(folderShareInfo);
            } else {
                JSONObject jitem = new JSONObject();
                jitem.put("id", folderMapping.getId());
                jitem.put("text", folderName
                        + "("
                        + netdiskMgr.getNumOfMyShareFolder(folderMapping
                        .getId()) + "个文件)");
                jitem.put(UIConstants.JSON_NODETYPE, "myShare");
                jitem.put("allowChild", false);
                jitem.put("allowDelete", false);
                jitem.put("allowEdit", false);
                jitem.put("leaf", true);
                jitem.put("iconCls", "folder");
                jsonRoot.put(jitem);
            }
        }
        return jsonRoot;
    }

    /**
     * 生成文件夹树JSON数据(共享给我的文件夹)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private JSONArray getShareToMeNodes(String fromUserId, String userId) {
        String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
                .getNetDiskRootPath();
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        List shareToMeList = netdiskMgr.listShareToMe(fromUserId, userId);
        JSONArray jsonRoot = new JSONArray();
        BocoLog.debug(this, "***** Share to me list size is : "
                + shareToMeList.size());
        for (int i = 0; i < shareToMeList.size(); i++) {
            TawWorkbenchNetDiskFolderShare folderShareInfo = (TawWorkbenchNetDiskFolderShare) shareToMeList
                    .get(i);
            TawWorkbenchNetDiskFolderMapping folderMapping = netdiskMgr
                    .geTawWorkbenchNetDiskFolderMapping(folderShareInfo
                            .getShareFolderPath());
            TawWorkbenchNetDiskFolderMapping parentMapping = netdiskMgr
                    .geTawWorkbenchNetDiskFolderMapping(folderMapping
                            .getParentId());
            String folderName = folderMapping.getFolderName();
            if (!"0".equals(folderMapping.getParentId())) {
                while (!"0".equals(parentMapping.getParentId())
                        && !"".equals(parentMapping.getId())
                        && parentMapping.getId() != null) {
                    TawWorkbenchNetDiskFolderMapping mp = netdiskMgr
                            .geTawWorkbenchNetDiskFolderMapping(parentMapping
                                    .getParentId());
                    folderName = parentMapping.getFolderName() + File.separator
                            + folderName;
                    parentMapping = mp;
                }
            }
            File shareFolder = new File(rootPath + File.separator + fromUserId
                    + File.separator + folderMapping.getId());
            BocoLog.debug(this, "***** share folder is : "
                    + shareFolder.getPath());
            if (!shareFolder.exists()) {
                netdiskMgr.removeTawWorkbenchFolderShare(folderShareInfo);
            } else {
                JSONObject jitem = new JSONObject();
                jitem.put("id", fromUserId + "," + folderMapping.getId());
                jitem.put("text", folderName
                        + "("
                        + netdiskMgr.getNumOfShareToMeFolder(folderMapping
                        .getId()) + "个文件)");
                jitem.put(UIConstants.JSON_NODETYPE, "shareToMeNodes");
                jitem.put("allowChild", false);
                jitem.put("allowEdit", false);
                jitem.put("allowDelete", false);
                jitem.put("allowShare", false);
                jitem.put("leaf", true);
                jitem.put("iconCls", "folder");
                jsonRoot.put(jitem);
            }
        }
        return jsonRoot;
    }

    /**
     * 打开文件列表主界面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listFilesMain(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        // 数据库移植
        // 共享给我中的节点跳转单独的文件列表
        String nodeType = StaticMethod.null2String(request
                .getParameter(UIConstants.JSON_NODETYPE));
        if ("shareToMeNodes".equals(nodeType)) {
            String folderPath = request.getParameter("folderPath");
            String obj[] = folderPath.split(",");
            String fromUserId = obj[0];
            String folderId = obj[1];
            List list = netdiskMgr.listTawWorkbenchNetDiskFile(fromUserId,
                    folderId);
            Iterator fileIt = list.iterator();
            request.setAttribute("fileIt", fileIt);
            request.getSession().setAttribute(
                    UploadProcessor.NET_DISK_OPER_FOLDERPATH_KEY, folderPath);
            request.getSession().setAttribute(
                    UploadProcessor.NET_DISK_OPER_FOLDERID_KEY, folderId);
            return mapping.findForward("fileListShareToMe");
        } else {
            String folderId = request.getParameter("folderPath");
            String folderPath = request.getSession().getAttribute(
                    "userRootPath").toString()
                    + File.separator + folderId;
            String freeSpaceInfo = NetDiskAttriubuteLocator
                    .getFreeSpaceSizeInfo(userId);
            request.getSession().setAttribute(
                    UploadProcessor.NET_DISK_OPER_FOLDERPATH_KEY, folderPath);
            request.getSession().setAttribute(
                    UploadProcessor.NET_DISK_OPER_FOLDERID_KEY, folderId);
            request.setAttribute("freeSpaceInfo", freeSpaceInfo);
            return mapping.findForward("fileListMain");
        }
    }

    /**
     * 打开文件列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listFiles(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        String folderId = request.getSession().getAttribute(
                UploadProcessor.NET_DISK_OPER_FOLDERID_KEY).toString();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        List list = netdiskMgr.listTawWorkbenchNetDiskFile(userId, folderId);
        Iterator fileIt = list.iterator();
        request.setAttribute("fileIt", fileIt);

        return mapping.findForward("fileList");
    }

    /**
     * 上传文件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void upload(ActionMapping mapping, ActionForm form,
                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Boolean maxLengthExceeded = (Boolean) request
                .getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
            UploadProcessor.sendCompleteResponse(response,
                    "File size out of required！");
            return;
        }

        TawWorkbenchNetDiskFileForm netDiskFileForm = (TawWorkbenchNetDiskFileForm) form;
        FormFile formFile = netDiskFileForm.getFile();
        String fileName = new String(formFile.getFileName().getBytes(
                "ISO-8859-1"), "UTF-8");
        if (fileName == null || "".equals(fileName)) {
            UploadProcessor.sendCompleteResponse(response,
                    "Could not read the file！");
            return;
        }
        // request.setCharacterEncoding("utf-8");
        // response.setCharacterEncoding("utf-8");
        // response.setContentType("text/html; charset=GBK");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();

        // 检测上传文件大小是否超过限制
        int maxFileSizeMB = Integer.parseInt(NetDiskAttriubuteLocator
                .getNetDiskAttributes().getMaxFileSize());
        long maxFileSizeB = maxFileSizeMB * 1024 * 1024;
        long currentFileSize = request.getContentLength();
        long availableSize = (long) NetDiskAttriubuteLocator
                .getAvailableSize(userId) * 1024 * 1024;
        if (currentFileSize > availableSize) {
            UploadProcessor.sendCompleteResponse(response,
                    "Not enough space left！");
            return;
        } else if (currentFileSize > maxFileSizeB) {
            UploadProcessor.sendCompleteResponse(response,
                    "File size out of required！");
            return;
        }

        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        String folderMappingId = request.getSession().getAttribute(
                UploadProcessor.NET_DISK_OPER_FOLDERID_KEY).toString();
        TawWorkbenchNetDiskFile netDiskFile = new TawWorkbenchNetDiskFile();
        String mappingName = "";

        // 创建UploadListener对象
        UploadListener listener = new UploadListener(request.getContentLength());
        listener.start();// 启动监听状态
        // 将监听器对象的状态保存在Session中
        request.getSession().setAttribute("FILE_UPLOAD_STATS",
                listener.getFileUploadStats());
        request.getSession().setAttribute("bytesRead", "0");
        // 创建MonitoredDiskFileItemFactory对象
//		 FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
        // 通过该工厂对象创建ServletFileUpload对象
        // ServletFileUpload upload = new ServletFileUpload(factory);
        // upload.setHeaderEncoding("utf-8");
        // 将转化请求保存到list对象中
        // List items = upload.parseRequest(request);

        // 停止使用监听器
        listener.done();

        // if (items == null || items.size() <= 0) {
        // UploadProcessor.sendCompleteResponse(response,
        // "Could not read the file！");
        // return;
        // }
        // FileItem fileItem = (FileItem) items.get(0);
        // String fullFileName = fileItem.getName();
        // BocoLog.debug(this, "***** fullFileName is : " + fullFileName);
        // if (fullFileName == null || "".equals(fullFileName)) {
        // return;
        // }
        // // 转换文件名
        // String fileName = fullFileName.substring(fullFileName
        // .lastIndexOf(File.separator) + 1);// 带后缀的文件名
        String expand = fileName.substring(fileName.lastIndexOf('.') + 1);// 文件扩展名
        Date puredate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = dateFormat.format(puredate);
        String attend = String.valueOf(puredate.getTime()); // 取得毫秒数
        attend = attend.substring(attend.length() - 2, attend.length()); // 取毫秒的后两位,避免有同时上传的可能性
        mappingName = date + attend + "." + expand; // 映射文件名
        BocoLog.debug(this, "***** mappingName is : " + mappingName);
        netDiskFile.setUserId(userId);
        netDiskFile.setFileName(fileName);
        netDiskFile.setMappingName(mappingName);
        netDiskFile.setExpand(expand);
        netDiskFile.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(puredate));
        netDiskFile.setFolderMappingId(folderMappingId);
        netDiskFile.setFileSize(String
                .valueOf(request.getContentLength() / 1000));

        boolean hasError = false;
        // 创建HttpSession对象
        // HttpSession session = request.getSession();
        // if (!fileItem.isFormField()) {// 如果该FileItem不是表单域
        // try {
        // UploadProcessor.processUploadedFile(fileItem, session,
        // mappingName);// 调用processUploadedFile方法,将数据保存到文件中
        // fileItem.delete();// 内存中删除该数据流
        // netdiskMgr.saveTawWorkbenchNetDiskFile(netDiskFile);
        // } catch (Exception e) {
        // UploadProcessor.sendCompleteResponse(response, e.getMessage());
        // }
        // }
        HttpSession session = request.getSession();
        try {
            UploadProcessor.processUploadedFile(formFile, session, mappingName);// 调用processUploadedFile方法,将数据保存到文件中
            netdiskMgr.saveTawWorkbenchNetDiskFile(netDiskFile);
        } catch (Exception e) {
            UploadProcessor.sendCompleteResponse(response, e.getMessage());
        }
        if (!hasError) {// 如果没有出现错误
            UploadProcessor.sendCompleteResponse(response, null);// 调用sendCompleteResponse方法
        } else {
            UploadProcessor
                    .sendCompleteResponse(response,
                            "Could not process uploaded file. Please see log for details.");
        }
    }

    /**
     * 监视文件上传
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void status(ActionMapping mapping, ActionForm form,
                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=GBK");
        HttpSession session = request.getSession();

        if ("status".equals(request.getParameter("c"))) {// 如果请求中c的值为status
            UploadProcessor.doStatus(session, response);// 调用doStatus方法
        }
    }

    /**
     * 下载文件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void download(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String mappingName = request.getParameter("mappingName"); // 下载文件映射文件名
        String userId = request.getParameter("userId"); // 下载文件所属用户Id
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawWorkbenchNetDiskFile netDiskFile = netdiskMgr
                .searchTawWorkbenchNetDiskFile(userId, mappingName);

        // 更新文件浏览次数
        netDiskFile.setScanTimes(new Integer(netDiskFile.getScanTimes()
                .intValue() + 1));
        netdiskMgr.saveTawWorkbenchNetDiskFile(netDiskFile);

        String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
                .getNetDiskRootPath();
        File file = new File(rootPath + File.separator + userId
                + File.separator + netDiskFile.getFolderMappingId()
                + File.separator + mappingName);

        // 读到流中
        InputStream inStream = new FileInputStream(file);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/x-msdownload;charset=GBK");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode(netDiskFile.getFileName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes("UTF-8"), "GBK"));

        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = inStream.read(b)) > 0)
            response.getOutputStream().write(b, 0, len);
        inStream.close();
    }

    /**
     * 删除文件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String folderMappingId = request.getSession().getAttribute(
                UploadProcessor.NET_DISK_OPER_FOLDERID_KEY).toString();
        String mappingName = request.getParameter("mappingName");
        netdiskMgr.delTawWorkbenchNetDiskFile(userId, folderMappingId,
                mappingName);
        return listFiles(mapping, form, request, response);
    }

    /**
     * 删除文件夹
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public ActionForward deleteFolder(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String folderMappingId = request.getParameter("folderPath"); // 待删除文件夹路径
        String rootPath = NetDiskAttriubuteLocator.getNetDiskAttributes()
                .getNetDiskRootPath();
        if (!netdiskMgr.removeFolder(rootPath, folderMappingId, userId)) {
            request.setAttribute("failInfo", "删除文件夹失败！");
            return mapping.findForward("fail");
        }
        return mapping.findForward("success");
    }

    /**
     * 打开文件夹共享页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward shareFolder(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String folderId = StaticMethod.null2String(request
                .getParameter("folderPath"));
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();

        // 获取已共享人员列表;
        String fromUserId = userId;
        String shareToUserIds = getShareToUserIds(fromUserId, folderId);

        request.getSession().setAttribute(
                UploadProcessor.NET_DISK_OPER_FOLDERID_KEY, folderId);
        request.setAttribute("folderMappingId", folderId);
        request.setAttribute("shareToUserIds", shareToUserIds);
        return mapping.findForward("folderShare");
    }

    /**
     * 保存文件夹共享信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveFolderShareInfo(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String fromUserId = sessionform.getUserid();
        String fromUserName = sessionform.getUsername();
        String folderMappingId = request.getParameter("folderMappingId");
        String userId = StaticMethod
                .null2String(request.getParameter("userId"));
        ;
        // 是否共享给所有用户
        String shareType = StaticMethod.null2String(request
                .getParameter("shareType"));
        if ("allUsers".equals(shareType) || userId.indexOf("all_users") >= 0) {
            userId = "all_users";
        }

        TawWorkbenchNetDiskFolderShare folderShareInfo = new TawWorkbenchNetDiskFolderShare();
        netdiskMgr.removeFolderShareInfo(folderMappingId, fromUserId);
        if (userId != null && !"".equals(userId)) {
            folderShareInfo.setFromUserId(fromUserId);
            folderShareInfo.setFromUserName(fromUserName);
            folderShareInfo.setToUserId(userId);
            folderShareInfo.setShareFolderPath(folderMappingId);

            netdiskMgr.saveTawWorkbenchFolderShare(folderShareInfo);
        }
        return mapping.findForward("success");
    }

    /**
     * 获取文件夹的共享用户列表
     *
     * @param fromUserId
     * @param folderPath
     * @return
     */
    private String getShareToUserIds(String fromUserId, String folderPath) {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawWorkbenchNetDiskFolderShare folderShareInfo = netdiskMgr
                .getTawWorkbenchNetDiskFolderShare(fromUserId, folderPath);
        JSONArray jsonRoot = new JSONArray();
        String userId = folderShareInfo.getToUserId();
        if (userId == null || "".equals(userId)) {
            return "[]";
        } else if ("all_users".equals(userId)) {
            JSONObject jitem = new JSONObject();
            jitem.put("id", userId);
            jitem.put("name", "所有用户");
            jsonRoot.put(jitem);
            return jsonRoot.toString();
        }
        String[] toUserIds = userId.split(",");

        if (toUserIds != null) {
            for (int i = 0; i < toUserIds.length; i++) {
                JSONObject jitem = new JSONObject();
                String toUserId = toUserIds[i];

                jitem.put("id", toUserId);
                jitem.put("name", DictMgrLocator.getId2NameService().id2Name(
                        toUserId, "tawSystemUserDao"));
                jsonRoot.put(jitem);
            }
            return jsonRoot.toString();
        } else {
            return "[]";
        }
    }

    /**
     * 显示查询页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangbeiying
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        request.setAttribute("userIdSearch", sessionform.getUserid());
        return mapping.findForward("searchIndex");
    }

    /**
     * 显示查询结果
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author wangbeiying
     */
    public ActionForward searchDo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileName = request.getParameter("fileName").trim();
        String uploadStratTime = request.getParameter("uploadStratTime").trim();
        String uploadEndTime = request.getParameter("uploadEndTime").trim();
        String uploadUser = request.getParameter("uploadUser").trim();
        String userIdSearch = request.getParameter("userIdSearch").trim();

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        if (userIdSearch.equals("")) {
            userIdSearch = sessionform.getUserid();
        }

        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        List resultList = netdiskMgr.searchByConditions(userIdSearch, fileName, uploadStratTime, uploadEndTime, uploadUser);
        request.setAttribute("netDiskFiles", resultList);

        request.setAttribute("userIdSearch", userIdSearch);
        request.setAttribute("fileName", fileName);
        request.setAttribute("uploadStratTime", uploadStratTime);
        request.setAttribute("uploadEndTime", uploadEndTime);
        request.setAttribute("uploadUser", uploadUser);

        return mapping.findForward("searchList");
    }

    /**
     * 删除文件
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public ActionForward deleteSearchFile(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawWorkbenchNetDiskMgr netdiskMgr = (ITawWorkbenchNetDiskMgr) getBean("ItawWorkbenchNetDiskMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String folderMappingId = request.getParameter("folderMappingId").trim();
        String mappingName = request.getParameter("mappingName");
        netdiskMgr.delTawWorkbenchNetDiskFile(userId, folderMappingId,
                mappingName);
        String fileName = request.getParameter("fileName").trim();
        String uploadStratTime = request.getParameter("uploadStratTime").trim();
        String uploadEndTime = request.getParameter("uploadEndTime").trim();
        String uploadUser = request.getParameter("uploadUser").trim();
        String userIdSearch = request.getParameter("userIdSearch").trim();
        request.setAttribute("userIdSearch", userIdSearch);
        request.setAttribute("fileName", fileName);
        request.setAttribute("uploadStratTime", uploadStratTime);
        request.setAttribute("uploadEndTime", uploadEndTime);
        request.setAttribute("uploadUser", uploadUser);

        return searchDo(mapping, form, request, response);
    }
}
