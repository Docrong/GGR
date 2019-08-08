package com.boco.eoms.km.file.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.file.mgr.KmFileMgr;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.file.model.KmFile;
import com.boco.eoms.km.file.model.KmFileHis;
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.file.util.FileConstants;
import com.boco.eoms.km.file.util.FileUploadProcessor;
import com.boco.eoms.km.file.util.KmFilesAttriubuteLocator;
import com.boco.eoms.km.file.webapp.form.KmFileForm;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditerStep.mgr.KmAuditerStepMgr;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.util.KmOperateDefine;

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
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 */
public final class KmFileAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * 新增文件管理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        //String operateUserId = sessionform.getUserid();

        String nodeId = request.getParameter("nodeId");
        // request.setAttribute(FileConstants.KM_FILETREE_NODEID, nodeId);

        KmFileForm fileForm = (KmFileForm) form;
        fileForm.setUserId(sessionform.getUserid());
        fileForm.setDeptId(sessionform.getDeptid());
        fileForm.setPhone(sessionform.getContactMobile());
        fileForm.setNodeId(nodeId);
        fileForm.setIsDeleted("0");

        return mapping.findForward("edit");
    }

    /**
     * 修改文件管理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmFile file = fileMgr.getFile(id);
        KmFileForm fileForm = (KmFileForm) convert(file);
        updateFormBean(mapping, request, fileForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存文件管理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        KmFileForm fileForm = (KmFileForm) form;
        boolean isNew = (null == fileForm.getId() || "".equals(fileForm.getId()));
        KmFile file = (KmFile) convert(fileForm);
        if (isNew) {
            file.setVersion("1.0");
            fileMgr.saveFile(file);
        } else {
            int version = StaticMethod.nullObject2int(file.getVersion());
            file.setVersion((version + 1) + ".0");
            fileMgr.saveFile(file);
        }
        KmFileHis fileHis = (KmFileHis) convert(file);
        fileMgr.saveFileHis(fileHis);
        return search(mapping, fileForm, request, response);
    }

    /**
     * 删除文件管理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        fileMgr.removeFile(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示文件管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String nodeId = request.getParameter("nodeId");
        //下一步审核人提示
        request.setAttribute("auditName", request.getAttribute("auditName"));

        // 部门权限
        KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
        List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateDeptId, "dept");
        Map kmOperateMap = new HashMap();
        for (int i = 0; i < kmOperateToDeptList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToDeptList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }
        // 人员权限
        List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateUserId, "user");
        for (int i = 0; i < kmOperateToUserList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToUserList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }

        String operateType = StaticMethod.nullObject2String(kmOperateMap.get(nodeId));
        KmFileTreeMgr kmFileTreeMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
        KmFileTree kmFileTree = kmFileTreeMgr.getKmFileTreeByNodeId(nodeId);
        if ("".equals(operateType)
                && "1".equals(kmFileTree.getHasParentOperate())) {
            String parentId = kmFileTree.getParentNodeId();
            operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
        }
        request.setAttribute("operateType", operateType);
        //
        if ((null == nodeId) || (nodeId.trim().equals(""))) {
            nodeId = request.getAttribute(FileConstants.KM_FILETREE_NODEID).toString();
        }
        request.setAttribute(FileConstants.KM_FILETREE_NODEID, nodeId);

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                FileConstants.FILE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        // Map map = (Map) fileMgr.getFiles(pageIndex, pageSize, " where
        // substr(file.nodeId, 0, length(" + nodeId + "))='" + nodeId + "'");
        Map map = (Map) fileMgr.getFiles(pageIndex, pageSize,
                " WHERE file.isDeleted='0' and file.nodeId like '" + nodeId + "%' ",
                " order by file.uploadTime desc");
        List list = (List) map.get("result");

        request.setAttribute(FileConstants.FILE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
    }

    /**
     * 分页显示文件草稿管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchAllDraft(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                FileConstants.FILE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String whereSql = " WHERE file.isDeleted='0' and file.state='1' and file.userId='" + operateUserId + "'";
        Map map = (Map) fileMgr.getFiles(pageIndex, pageSize, whereSql, " order by file.uploadTime desc");
        List list = (List) map.get("result");

        request.setAttribute(FileConstants.FILE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("allList");
    }

    /**
     * 分页显示文件草稿管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchAllUpload(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                FileConstants.FILE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String whereSql = " WHERE file.isDeleted='0' and file.userId='" + operateUserId + "'";
        Map map = (Map) fileMgr.getFiles(pageIndex, pageSize, whereSql, " order by file.uploadTime desc");
        List list = (List) map.get("result");

        request.setAttribute(FileConstants.FILE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("allList");
    }

    public ActionForward upload(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Boolean maxLengthExceeded = (Boolean) request
                .getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
            return mapping.findForward("maxLength");
        }

        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String nodeId = request.getParameter("nodeId");
        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByNodeid(nodeId);
        KmFileForm fileForm = (KmFileForm) form;
        String id = fileForm.getId();

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        KmFile file = null;
        if ((null == id) || ("".equals(id))) {
            file = new KmFile();

            if (kmAuditer.getId() != null) {
                String masterAudit = kmAuditer.getMasterAudit();
                String isSign = kmAuditer.getIsSign();
                if (("1030101").equals(masterAudit)) {
                    file.setState("5");
                } else {
                    file.setState("6");
                }
                if (("1030101").equals(isSign)) {
                    // request.setAttribute("TableInfo/STATE", "1");
                }
            } else {
                file.setState("2");
            }

            file.setClickCount(new Integer(0));
            file.setUploadTime(StaticMethod.getCurrentDateTime());
            file.setDeptId(operateDeptId);
            file.setUserId(operateUserId);
            file.setNodeId(nodeId);
            file.setVersion("1");
        } else {
            file = fileMgr.getFile(id);
            if (kmAuditer.getId() != null) {
                String masterAudit = kmAuditer.getMasterAudit();
                String isSign = kmAuditer.getIsSign();
                if (("1030101").equals(masterAudit)) {
                    file.setState("5");
                } else {
                    file.setState("6");
                }
                if (("1030101").equals(isSign)) {
                    // request.setAttribute("TableInfo/STATE", "1");
                }
            } else {
                file.setState("2");
            }
            nodeId = file.getNodeId();
            file.setUploadTime(StaticMethod.getCurrentDateTime());
            int version = StaticMethod.null2int(file.getVersion());
            file.setVersion(String.valueOf(version + 1));
            request.setAttribute(FileConstants.KM_FILETREE_NODEID, nodeId);
        }

        FormFile formFile = fileForm.getFile();

        String encoding = request.getCharacterEncoding();
        String fileName = null; //文件名
        String keywords = null; //关键字
        String fileAbstract = null; //文件路径
        if ("UTF-8".equals(encoding.toUpperCase())) {
            fileName = formFile.getFileName();
            keywords = fileForm.getKeywords();
            fileAbstract = fileForm.getFileAbstract();
        } else {
            fileName = new String(formFile.getFileName().getBytes(encoding), "UTF-8");
            keywords = new String(fileForm.getKeywords().getBytes(encoding), "UTF-8");
            fileAbstract = new String(fileForm.getFileAbstract().getBytes(encoding), "UTF-8");
        }

        if ((null != formFile) && (!"".equals(fileName))) {
            // if (fileName == null || "".equals(fileName)) {
            // UploadProcessor.sendCompleteResponse(response, "Could not read
            // the file！");
            // return;
            // }
            String uploadTime = StaticMethod.getCurrentDateTime("yyMMddHHmmssS");
            String expand = fileName.substring(fileName.lastIndexOf('.') + 1);// 文件扩展名
            String mappingName = nodeId + "_" + uploadTime + "." + expand; // 映射文件名
            file.setMappingName(mappingName);
            file.setExpand(expand);
            file.setFileName(fileName);
            file.setFileSize(String.valueOf(request.getContentLength() / 1000));
            String rootPath = KmFilesAttriubuteLocator.getKmFilesAttributes().getNetDiskRootPath();
            String filePath = rootPath + File.separator + mappingName;
            FileUploadProcessor.processUploadedFile(formFile, filePath);
        }

        file.setFileAbstract(fileAbstract);
        file.setFileGrade(fileForm.getFileGrade());
        file.setKeywords(keywords);
        file.setPhone(fileForm.getPhone());
        file.setIsDeleted(fileForm.getIsDeleted());
        fileMgr.saveFile(file);

        KmFileHis fileHis = new KmFileHis();
        BeanUtils.copyProperties(fileHis, file);
        fileHis.setFileId(file.getId());
        fileHis.setUploadTime(StaticMethod.getCurrentDateTime());
        fileMgr.saveFileHis(fileHis);
        if (kmAuditer.getId() != null) {
            KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
            Map kmAuditerStepMap = new HashMap();
            kmAuditerStepMap.put("type", "file");
            kmAuditerStepMap.put("auditResult", "");
            kmAuditerStepMap.put("remark", "");
            kmAuditerStepMap.put("userId", operateUserId);
            kmAuditerStepMap.put("kmId", file.getId());
            kmAuditerStepMap.put("state", "1");
            KmAuditerStep kmAuditerStep = kmAuditerStepMgr.saveKmAuditerStep(kmAuditer, kmAuditerStepMap);
            request.setAttribute("auditName", kmAuditerStep.getToOrgName());
        }

        // 记录:操作记录表-附件上传
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        kmOperateLogMgr.saveKmOperateLog(file.getNodeId(), "KMFILE", file.getId(),
                KmOperateDefine.KM_OPERATE_NAME_FILE_UP,
                operateUserId, operateDeptId, operateUserId);
        // 记录：日操作记录表-附件上传
        KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
        kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                KmOperateDefine.KM_OPERATE_NAME_FILE_UP,
                operateUserId, operateDeptId);

        return search(mapping, form, request, response);
    }

    public ActionForward download(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        String nodeId = request.getParameter("nodeId");
        KmFile file = fileMgr.getFile(id);
        // 更新文件浏览次数
        file.setClickCount(new Integer(file.getClickCount().intValue() + 1));
        fileMgr.saveFile(file);

        String rootPath = KmFilesAttriubuteLocator.getKmFilesAttributes().getNetDiskRootPath();
        File temp = new File(rootPath + File.separator + file.getMappingName());
        // 判断文件是否存在
        if (!temp.exists()) {
            ActionMessages messages = new ActionMessages();
            messages.add("", new ActionMessage("您要下载的文件不存在", false));
            this.saveMessages(request, messages);
            return mapping.findForward("fail");
        } else {
            // 读取：当前操作用户信息
            TawSystemSessionForm sessionform = this.getUser(request);
            String operateDeptId = sessionform.getDeptid();
            String operateUserId = sessionform.getUserid();

            // 记录:操作记录表-附件下载
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            kmOperateLogMgr.saveKmOperateLog(file.getNodeId(), "KMFILE", file.getId(),
                    KmOperateDefine.KM_OPERATE_NAME_FILE_DOWN,
                    operateUserId, operateDeptId, operateUserId);
            // 记录：日操作记录表-附件下载
            KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
            kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                    KmOperateDefine.KM_OPERATE_NAME_FILE_DOWN,
                    operateUserId, operateDeptId);

            // 读到流中
            InputStream inStream = new FileInputStream(temp);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "GBK"));

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
            return null;
        }
    }

    public ActionForward downloadHis(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        String nodeId = request.getParameter("nodeId");
        KmFileHis fileHis = fileMgr.getFileHis(id);
        // 更新文件浏览次数
        fileHis.setClickCount(new Integer(fileHis.getClickCount().intValue() + 1));
        fileMgr.saveFileHis(fileHis);

        String rootPath = KmFilesAttriubuteLocator.getKmFilesAttributes().getNetDiskRootPath();
        File temp = new File(rootPath + File.separator + fileHis.getMappingName());
        // 判断文件是否存在
        if (!temp.exists()) {
            ActionMessages messages = new ActionMessages();
            messages.add("", new ActionMessage("您要下载的文件不存在", false));
            this.saveMessages(request, messages);
            return mapping.findForward("fail");
        } else {
            // 读取：当前操作用户信息
            TawSystemSessionForm sessionform = this.getUser(request);
            String operateDeptId = sessionform.getDeptid();
            String operateUserId = sessionform.getUserid();

            // 记录:操作记录表-附件下载
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            kmOperateLogMgr.saveKmOperateLog(fileHis.getNodeId(), "KMFILE", fileHis.getHisId(),
                    KmOperateDefine.KM_OPERATE_NAME_FILE_DOWN,
                    operateUserId, operateDeptId, operateUserId);
            // 记录：日操作记录表-附件下载
            KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
            kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                    KmOperateDefine.KM_OPERATE_NAME_FILE_DOWN,
                    operateUserId, operateDeptId);

            // 读到流中
            InputStream inStream = new FileInputStream(temp);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(fileHis.getFileName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "GBK"));

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
            return null;
        }
    }

    /**
     * 详细信息页面
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String operateType = request.getParameter("operateType");
        request.setAttribute("operateType", operateType);
        String nodeId = request.getParameter("nodeId");
        request.setAttribute(FileConstants.KM_FILETREE_NODEID, nodeId);
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmFile file = fileMgr.getFile(id);
        // 若无此贴子则转向失败页面，不记录查看记录历史
        if (file == null || file.getId() == null) {
            ActionMessages messages = new ActionMessages();
            messages.add("", new ActionMessage("文件ID不存在", false));
            this.saveMessages(request, messages);
            return mapping.findForward("fail");
        }
        KmFileForm fileForm = (KmFileForm) convert(file);
        request.setAttribute("kmFileForm", fileForm);

        // 读取:知识内容历史版本信息
        List kmFileHistory = fileMgr.getKmFileHistoryList(id);
        request.setAttribute("KmFileHistoryList", kmFileHistory);

        // 查询该知识审核的步骤
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        String auditStepSql = "kmId = '" + id + "' order by createTime";
        List stepList = kmAuditerStepMgr.getKmAuditerSteps(auditStepSql);
        List auditList = new ArrayList();
        for (int i = 1; i < stepList.size(); i++) {
            auditList.add(stepList.get(i));
        }
        request.setAttribute("kmAuditColumnList", auditList);

        return mapping.findForward("detail");
    }

    public ActionForward detailHistory(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识版本号
        String VERSION = StaticMethod.null2String(request
                .getParameter("VERSION"));
        // 读取:知识内容详细信息
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        KmFileHis kmFileHis = fileMgr.getFileHis(ID);
        String nodeId = kmFileHis.getNodeId();
        request.setAttribute(FileConstants.KM_FILETREE_NODEID, nodeId);
        request.setAttribute("kmFileHis", kmFileHis);
        return mapping.findForward("detailHistory");
    }

    /**
     * 跳转到文件查询页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward querypage(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("filequerypage");
    }

    /**
     * 分页显示文件检索列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author mosquito
     */
    public ActionForward searchFile(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pageIndexName = new org.displaytag.util.ParamEncoder(//页数的参数名
                FileConstants.FILE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();//每页包含记录条数
        final Integer pageIndex = new Integer(GenericValidator//当前页数
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmFileForm fileForm = (KmFileForm) form;
        StringBuffer whereStr = new StringBuffer(" WHERE file.isDeleted='0' ");
        whereStr.append(" and file.uploadTime between '");
        whereStr.append(fileForm.getStarttime());
        whereStr.append("' and '");
        whereStr.append(fileForm.getEndtime());
        whereStr.append("'");

        //文件名
        String fileName = fileForm.getFileName();
        if (fileName != null && !fileName.equals("")) {
            whereStr.append(" and file.fileName like '%");
            whereStr.append(fileName);
            whereStr.append("%'");
        }
        // 创建者
        String userId = fileForm.getUserId();
        if (userId != null && !userId.equals("")) {
            whereStr.append(" and file.userId = '");
            whereStr.append(userId);
            whereStr.append("'");
        }
        // 关键字
        String keywords = fileForm.getKeywords();
        if (keywords != null && !keywords.equals("")) {
            whereStr.append(" and file.keywords like '%");
            whereStr.append(keywords);
            whereStr.append("%'");
        }
        // 文件格式
        String expand = fileForm.getExpand();
        if (expand != null && !expand.equals("")) {
            whereStr.append(" and file.expand = '");
            whereStr.append(expand);
            whereStr.append("'");
        }

        //排序方式
        StringBuffer orderStr = new StringBuffer(" order by file.uploadTime ");
        orderStr.append(request.getParameter("timeSort"));

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        Map map = (Map) fileMgr.getFiles(pageIndex, pageSize, whereStr.toString(), orderStr.toString());
        List list = (List) map.get("result");

        request.setAttribute(FileConstants.FILE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("filequerylist");// 转向查询结果页面
    }

}