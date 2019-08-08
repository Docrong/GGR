package com.boco.eoms.operuser.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.statistic.base.mgr.impl.StatID2NameV35Impl;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.operuser.mgr.OperuserMgr;
import com.boco.eoms.operuser.model.Operuser;
import com.boco.eoms.operuser.webapp.form.OperuserForm;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.TawApparatusMgr;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;
import com.boco.eoms.partner.baseinfo.webapp.form.TawApparatusForm;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:operuser
 * </p>
 * <p>
 * Description:operuser
 * </p>
 * <p>
 * Tue Mar 31 09:42:13 CST 2009
 * </p>
 *
 * @moudle.getAuthor() xiang
 * @moudle.getVersion() 35
 */

public final class OperuserAction extends BaseAction {

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
        return tree(mapping, form, request, response);
    }

    /**
     * operuser树页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward tree(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tree");
    }

    /**
     * operuser查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List list = new ArrayList();
        OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
        try {
            String name = request.getParameter("name");
            String deptname = request.getParameter("deptname");
            list = operuserMgr.getUserByNameOrdeptid(name, deptname);

        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("list", list);
        return mapping.findForward("listsearch");
    }

    /**
     * 生成operuser树JSON数据
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
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        JSONArray jsonRoot = new JSONArray();
        TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
        OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
        List userlist = new ArrayList();
        List deptlist = new ArrayList();
        try {
            deptlist = (ArrayList) deptbo.getNextLevecDepts(nodeId,
                    "0");
            userlist = operuserMgr.getUserBydeptid(nodeId);

            if (deptlist.size() > 0) {
                for (int i = 0; i < deptlist.size(); i++) {
                    TawSystemDept dept = (TawSystemDept) deptlist.get(i);
                    String deptId = dept.getDeptId();
                    String deptName = dept.getDeptName();

                    JSONObject jitem = new JSONObject();
                    jitem.put(UIConstants.JSON_ID, deptId);
                    jitem.put(UIConstants.JSON_TEXT, deptName);
                    jitem.put("allowChild", true);
                    jitem.put("allowDelete", false);
                    jitem.put("allowEdit", false);

                    // 判断是否还有子节点
                    List flaguser = operuserMgr.getUserBydeptid(deptId);
                    List flagdept = deptbo.getNextLevecDepts(deptId, "0");
                    if (flagdept == null || flagdept.size() <= 0) {
                        if (flaguser == null || flaguser.size() <= 0) {
                            jitem.put("leaf", 1);
                        }
                    } else {
                        jitem.put("leaf", 0);
                    }
                    jsonRoot.put(jitem);
                }
            }

            if (userlist.size() > 0) {
                for (int j = 0; j < userlist.size(); j++) {
                    Operuser user = (Operuser) userlist.get(j);

                    JSONObject jitem = new JSONObject();
                    jitem.put(UIConstants.JSON_ID, user.getId());
                    jitem.put(UIConstants.JSON_TEXT, user.getName());
                    jitem.put(UIConstants.JSON_NODETYPE, "user");
                    jitem.put("iconCls", "user");
                    jitem.put("leaf", 1);
                    jitem.put("allowChild", false);
                    jitem.put("allowDelete", true);
                    jitem.put("allowEdit", true);
                    jitem.put("allowClick", true);
                    // TODO 设置鼠标悬浮提示
                    //jitem.put("qtip", "aaa");
                    jsonRoot.put(jitem);
                }
            }
        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * // 取下级节点 List list = operuserMgr.getNextLevelOperusers(nodeId); for
         * (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) { Operuser
         * operuser = (Operuser) nodeIter.next(); JSONObject jitem = new
         * JSONObject(); jitem.put("id", operuser.getNodeId()); // TODO 添加节点名称
         * jitem.put("text", operuser.getName()); // 设置右键菜单
         * jitem.put("allowChild", true); jitem.put("allowEdit", true);
         * jitem.put("allowDelete", true); // 设置左键点击可触发action
         * jitem.put("allowClick", true); // 设置是否为叶子节点 boolean leafFlag = true;
         * if (operuserMgr.isHasNextLevel(operuser.getNodeId())) { leafFlag =
         * false; } jitem.put("leaf", leafFlag); // TODO 设置鼠标悬浮提示
         * jitem.put("qtip", "aaa"); jsonRoot.put(jitem); }
         */
        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }

    /**
     * 运维人力资源模板树
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward operuserTree(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tree");
    }

    /**
     * 新增operuser
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
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        OperuserForm operuserForm = (OperuserForm) form;
        operuserForm.setParentNodeId(nodeId);
        updateFormBean(mapping, request, operuserForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改operuser
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
		/*String nodeId = StaticMethod.null2String(request.getParameter("node"));
		OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
		Operuser operuser = operuserMgr.getOperuserByNodeId(nodeId);
		OperuserForm operuserForm = (OperuserForm) convert(operuser);
		updateFormBean(mapping, request, operuserForm);*/
        ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
        String _strId = request.getParameter("id");
        OperuserForm operuserForm = (OperuserForm) form;
        OperuserMgr mgr = (OperuserMgr) getBean("operuserMgr");

        Operuser _objOneOpt = new Operuser();
        _objOneOpt = mgr.getOperuser(_strId);
        operuserForm.setOid(_strId);
        operuserForm.setName(_objOneOpt.getName());
        operuserForm.setSex(_objOneOpt.getSex());
        operuserForm.setBirthday(_objOneOpt.getBirthday().toString().substring(0, 10));
        operuserForm.setDeptid(_objOneOpt.getDeptid());
        TawSystemDept tawdept = deptMgr.getDeptinfobydeptid(_objOneOpt.getDeptid(), "0");
        operuserForm.setDeptname(tawdept.getDeptName());
        operuserForm.setJoblevele(_objOneOpt.getJoblevele());
        operuserForm.setJobtype(_objOneOpt.getJobtype());
        operuserForm.setMajortype(_objOneOpt.getMajortype());
        operuserForm.setSavetime(_objOneOpt.getSavetime().toString().substring(0, _objOneOpt.getSavetime().toString().length() - 2));
        int age = new Date().getYear() - _objOneOpt.getBirthday().getYear();
        operuserForm.setAge(age + "");
        operuserForm.setPowerlevel(_objOneOpt.getPowerlevel());
        operuserForm.setPrizelevel(_objOneOpt.getPrizelevel());
        operuserForm.setWorklevel(_objOneOpt.getWorklevel());
        operuserForm.setSubarea(_objOneOpt.getSubarea());
        operuserForm.setSchoollevel(_objOneOpt.getSchoollevel());
        operuserForm.setRemark(_objOneOpt.getRemark());
        updateFormBean(mapping, request, operuserForm);

        request.setAttribute("deptname", tawdept.getDeptName());

        return mapping.findForward("edit");
    }

    /**
     * 保存operuser
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
		/*OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
		OperuserForm operuserForm = (OperuserForm) form;
		Operuser operuser = (Operuser) convert(operuserForm);
		operuserMgr.saveOperuser(operuser);*/
        OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
        OperuserForm operuserForm = (OperuserForm) form;
        Operuser operuser = new Operuser();
        try {
            operuser.setId(operuserForm.getOid());
            //operuser.setDeptid((JSONObject)operuserForm.getDeptid());
            // 取JSON对象中的部门ID
            String deptid = StaticMethod.null2String(request
                    .getParameter("deptid"));
			/*if (deptid != null && !("").equals(deptid)
					&& !"[]".equals(deptid)) {
				JSONArray jsonDept = JSONArray.fromString(deptid);
				JSONObject jobj = (JSONObject) jsonDept.get(0);
				String deptId = jobj.getString("id");
				String deptname = jobj.getString("name");*/
            operuser.setDeptid(operuserForm.getDeptid());
            operuser.setDeptname(operuserForm.getDeptname());
            operuser.setJoblevele(operuserForm.getJoblevele());
            operuser.setJobtype(operuserForm.getJobtype());
            operuser.setMajortype(operuserForm.getMajortype());
            operuser.setName(operuserForm.getName());
            operuser.setSex(operuserForm.getSex());
            operuser.setPowerlevel(operuserForm.getPowerlevel());
            operuser.setPrizelevel(operuserForm.getPrizelevel());
            operuser.setRemark(operuserForm.getRemark());
            operuser.setSchoollevel(operuserForm.getSchoollevel());
            operuser.setSubarea(operuserForm.getSubarea());
            operuser.setWorklevel(operuserForm.getWorklevel());
            operuser.setSavetime(new Date());
            String birthday = operuserForm.getBirthday();// + " 00:00:00";
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            operuser.setBirthday(format.parse(birthday));
            //operuser.setBirthday(operuserForm.getBirthday());
            operuserMgr.saveOperuser(operuser);
        } catch (RuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mapping.findForward("success");
    }

    /**
     * 运维人员查询
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
        return mapping.findForward("searchuser");
    }

    /**
     * 运维人员详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
        OperuserForm operuserForm = (OperuserForm) form;
        String id = request.getParameter("id");
        Operuser _objOneOpt = new Operuser();
        _objOneOpt = operuserMgr.getOperuser(id);
        operuserForm.setOid(id);
        operuserForm.setName(_objOneOpt.getName());
        operuserForm.setSex(_objOneOpt.getSex());
        operuserForm.setBirthday(_objOneOpt.getBirthday().toString().substring(0, 10));
        int age = new Date().getYear() - _objOneOpt.getBirthday().getYear();
        operuserForm.setAge(age + "");
        //operuserForm.setDeptid(_objOneOpt.getDeptid());
        //TawSystemDept tawdept = deptMgr.getDeptinfobydeptid(_objOneOpt.getDeptid(), "0");
        operuserForm.setDeptname(_objOneOpt.getDeptname());
        operuserForm.setJoblevele(_objOneOpt.getJoblevele());
        operuserForm.setJobtype(_objOneOpt.getJobtype());
        operuserForm.setMajortype(_objOneOpt.getMajortype());
        operuserForm.setSavetime(_objOneOpt.getSavetime().toString().substring(0, _objOneOpt.getSavetime().toString().length() - 2));
        operuserForm.setPowerlevel(_objOneOpt.getPowerlevel());
        operuserForm.setPrizelevel(_objOneOpt.getPrizelevel());
        operuserForm.setWorklevel(_objOneOpt.getWorklevel());
        operuserForm.setSubarea(_objOneOpt.getSubarea());
        operuserForm.setSchoollevel(_objOneOpt.getSchoollevel());
        operuserForm.setRemark(_objOneOpt.getRemark());
        updateFormBean(mapping, request, operuserForm);

        return mapping.findForward("list");
    }


    /**
     * 删除operuser
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
        String oid = StaticMethod
                .null2String(request.getParameter("id"));
        OperuserMgr operuserMgr = (OperuserMgr) getBean("operuserMgr");
        // operuserMgr.removeOperuserByNodeId(nodeId);
        operuserMgr.removeOperuserById(oid);
        return mapping.findForward("success");
    }

    /**
     * 导入运维人员页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward operuserxlsInput(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("problemRow", "");
        return mapping.findForward("operuserxlsinput");
    }

    /**
     * 运维人员导入
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xlsSave(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        OperuserMgr mgr = (OperuserMgr) getBean("operuserMgr");
        OperuserMgr opermgr = (OperuserMgr) getBean("operuserMgr");
        ITawSystemDeptManager deptmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
        //AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        //StatID2NameV35Impl id2name = (StatID2NameV35Impl) getBean("statBaseDeptId2name_v35");
        // 首先将文件从客户端上传到服务器
        String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
        String sysTemPaht = request.getRealPath("/");
        OperuserForm operForm = (OperuserForm) form;
        String uploadPath = "/WEB-INF/pages/operuser/upfiles";
        String filePath = sysTemPaht + uploadPath + "/" + timeTag + ".xls";
        File tempFile = new File(sysTemPaht + uploadPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        FormFile file = operForm.getAccessoryName();
        try {
            InputStream stream = file.getInputStream(); // 把文件读入
            OutputStream outputStream = new FileOutputStream(filePath); // 建立一个上传文件的输出流

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("fail");
        }
        // 然后把文件的每一条数据读入到form中
        Workbook workbook = null;
        ArrayList formList = new ArrayList();
        ArrayList numberList = new ArrayList();
        InputStream ins = new FileInputStream(filePath);
        try {
            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook, 从输入流创建Workbook

            workbook = Workbook.getWorkbook(ins);
            Sheet dataSheet = workbook.getSheet(0);

            // 读取数据
            for (int i = 1; i < dataSheet.getRows(); i++) {
                Operuser temp = new Operuser();
                if (dataSheet.getCell(0, i).getContents() != null
                        && !"".equals(dataSheet.getCell(0, i).getContents())) {
                    temp.setName(dataSheet.getCell(0, i).getContents()
                            .trim());
                } else {
                    break;
                }
                if (dataSheet.getCell(1, i).getContents() != null
                        && !"".equals(dataSheet.getCell(1, i).getContents())) {
                    if (deptmgr.getDeptnameIsExist(dataSheet.getCell(1, i).getContents().trim(), "0")) {
                        temp.setDeptname(dataSheet.getCell(1, i).getContents().trim());
                        temp.setDeptid(deptmgr.getDeptinfoBydeptname(dataSheet.getCell(1, i).getContents().trim(), "0").getDeptId());
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(2));
                        continue;
                    }

                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(2));
                    continue;
                }
                if (dataSheet.getCell(2, i).getContents() != null
                        && !"".equals(dataSheet.getCell(2, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(), "1150109")) &&
                            (mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(), "1150109")) != null) {
                        temp.setSex(mgr.name2Id(dataSheet.getCell(2, i).getContents().trim(), "1150109"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(3));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(3));
                    continue;
                }
                if (dataSheet.getCell(3, i).getContents() != null
                        && !"".equals(dataSheet.getCell(3, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(3, i).getContents().trim(), "1150101")) &&
                            mgr.name2Id(dataSheet.getCell(3, i).getContents().trim(), "1150101") != null) {
                        temp.setSubarea(mgr.name2Id(dataSheet.getCell(3, i).getContents().trim(), "1150101"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(4));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(4));
                    continue;
                }
                if (dataSheet.getCell(4, i).getContents() != null
                        && !"".equals(dataSheet.getCell(4, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "1150102")) &&
                            mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "1150102") != null) {
                        temp.setMajortype(mgr.name2Id(dataSheet.getCell(4, i).getContents().trim(), "1150102"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(5));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(5));
                    continue;
                }

                if (dataSheet.getCell(5, i).getContents() != null
                        && !"".equals(dataSheet.getCell(5, i).getContents())) {
                    //String birthday = dataSheet.getCell(5, i).getContents().trim();
                    DateCell dc = (DateCell) dataSheet.getCell(5, i);
                    //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    temp.setBirthday(dc.getDate());
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(6));
                    continue;
                }
                if (dataSheet.getCell(6, i).getContents() != null
                        && !"".equals(dataSheet.getCell(6, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "1150103")) &&
                            (mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "1150103")) != null) {
                        temp.setJobtype(mgr.name2Id(dataSheet.getCell(6, i).getContents().trim(), "1150103"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(7));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(7));
                    continue;
                }
                if (dataSheet.getCell(7, i).getContents() != null
                        && !"".equals(dataSheet.getCell(7, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "1150104")) &&
                            (mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "1150104")) != null) {
                        temp.setJoblevele(mgr.name2Id(dataSheet.getCell(7, i).getContents().trim(), "1150104"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(8));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(8));
                    continue;
                }
                if (dataSheet.getCell(8, i).getContents() != null
                        && !"".equals(dataSheet.getCell(8, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(8, i).getContents().trim(), "1150105")) &&
                            (mgr.name2Id(dataSheet.getCell(8, i).getContents().trim(), "1150105")) != null) {
                        temp.setSchoollevel(mgr.name2Id(dataSheet.getCell(8, i).getContents().trim(), "1150105"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(9));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(9));
                    continue;
                }

                if (dataSheet.getCell(9, i).getContents() != null
                        && !"".equals(dataSheet.getCell(9, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(9, i).getContents().trim(), "1150106")) &&
                            (mgr.name2Id(dataSheet.getCell(9, i).getContents().trim(), "1150106")) != null) {
                        temp.setPowerlevel(mgr.name2Id(dataSheet.getCell(9, i).getContents().trim(), "1150106"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(10));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(10));
                    continue;
                }

                if (dataSheet.getCell(10, i).getContents() != null
                        && !"".equals(dataSheet.getCell(10, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(10, i).getContents().trim(), "1150107")) &&
                            (mgr.name2Id(dataSheet.getCell(10, i).getContents().trim(), "1150107")) != null) {
                        temp.setPrizelevel(mgr.name2Id(dataSheet.getCell(10, i).getContents().trim(), "1150107"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(11));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(11));
                    continue;
                }

                if (dataSheet.getCell(11, i).getContents() != null
                        && !"".equals(dataSheet.getCell(11, i).getContents())) {
                    if (!"".equals(mgr.name2Id(dataSheet.getCell(11, i).getContents().trim(), "1150108")) &&
                            (mgr.name2Id(dataSheet.getCell(11, i).getContents().trim(), "1150108")) != null) {
                        temp.setWorklevel(mgr.name2Id(dataSheet.getCell(11, i).getContents().trim(), "1150108"));
                    } else {
                        numberList.add(new Integer(i + 1));
                        numberList.add(new Integer(12));
                        continue;
                    }
                } else {
                    numberList.add(new Integer(i + 1));
                    numberList.add(new Integer(12));
                    continue;
                }

                if (dataSheet.getCell(12, i).getContents() != null
                        && !"".equals(dataSheet.getCell(12, i).getContents())) {

                    temp.setRemark(dataSheet.getCell(12, i).getContents()
                            .trim());

                }
                //TawSystemSessionForm sessionForm = this.getUser(request);
                Date savetime = new Date();
                temp.setSavetime(savetime);
                formList.add(temp);
            }
            for (int i = 0; i < formList.size(); i++) {
                opermgr.saveOperuser((Operuser) formList.get(i));
            }
            String problemRow = "";
            for (int i = 0; i < numberList.size(); i++) {
                problemRow += "," + numberList.get(i);
            }
            String str = "";
            if (!"".equals(problemRow)) {
                String sub = problemRow.substring(1, problemRow.length());
                String[] array = sub.split(",");
                str = "导入失败！以下为不合法的信息：" + "";
                for (int i = 0; i < array.length; i++) {

                    str = str + "第" + array[i] + "行" + "第" + array[i + 1] + "列" + "";
                    i++;
                }
            } else {
                str = "成功录入所有信息";
            }
            request.setAttribute("problemRow", str);
        } catch (Exception e) {
            workbook.close();

            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
            e.printStackTrace();
            return mapping.findForward("fail");
        } finally {
            workbook.close();
            ins.close();
            File fileDel = new File(filePath);
            if (fileDel.exists())
                fileDel.delete();
        }

        return mapping.findForward("operuserxlsinput");
    }


    /**
     * 导出模板
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward outPutModel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        try {
            String sysTemPath = request.getRealPath("/");
            String url = sysTemPath + "/operuser/model/operuser.xls";
            File file = new File(url);
            // 读到流中
            InputStream inStream = new FileInputStream(file);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/x-msdownload;charset=GBK");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "GBK"));

            // 循环取出流中的数据
            byte[] b = new byte[128];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (Exception e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
            return mapping.findForward("fail");
        }

        return null;
    }
}