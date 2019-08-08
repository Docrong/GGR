package com.boco.eoms.sheet.base.adapter.service.wps;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.NewIBaseSheet;
import com.boco.eoms.util.AttachRef;
import com.boco.eoms.util.InterfaceUtil;

public class WPSEngineServiceMethod {
    public Map setAcceptRole(String subRoleId, Map map) throws Exception {
        Map roleMap = SheetUtils.getUserNameForSubRole(subRoleId);
        String leader = StaticMethod.nullObject2String(roleMap.get("leaderId"));
        if (leader.equals(""))
            map.put("dealPerformerLeader", subRoleId);
        else
            map.put("dealPerformerLeader", leader);

        map.put("dealPerformer", subRoleId);
        map.put("dealPerformerType", "subrole");
        map.put("toOrgRoleId", subRoleId);
        return map;
    }

    public Map setAuditRole(String subRoleId, Map map) throws Exception {
        Map roleMap = SheetUtils.getUserNameForSubRole(subRoleId);
        String leader = StaticMethod.nullObject2String(roleMap.get("leaderId"));
        if (leader.equals(""))
            map.put("auditPerformerLeader", subRoleId);
        else
            map.put("auditPerformerLeader", leader);
        map.put("auditPerformer", subRoleId);
        map.put("auditPerformerType", "subrole");
        map.put("toOrgRoleId", subRoleId);
        return map;
    }

    /**
     * 获取匹配出来的子角色
     *
     * @param roleConfigName roleconfig.xml里配置的名称
     * @param toDeptId       派往部门id
     * @param bigRole        派往大类角色
     * @param map            含有区分度值的map
     * @return
     * @throws Exception
     */
    public TawSystemSubRole getMatchRoles(String roleConfigName, String toDeptId, String bigRole, Map map) throws Exception {
        HashMap classMap = RoleMapSchema.getInstance().getStyleIDsBySheet(roleConfigName);
        Hashtable hash = new Hashtable();
        hash.put("deptId", StaticMethod.nullObject2String(toDeptId));
        Set filterSet = classMap.keySet();
        Iterator filterIt = filterSet.iterator();
        while (filterIt.hasNext()) {
            String key = StaticMethod.nullObject2String(filterIt.next());
            String name = StaticMethod.nullObject2String(classMap
                    .get(key));
            if (!name.equals("") && map.get(name) != null)
                hash.put(key, map.get(name));
        }

        List perform = RoleManage.getInstance().getSubRoles(
                bigRole, hash);
        TawSystemSubRole tawSystemSubRole = SheetUtils.getMaxFilterSubRole(perform, null);

        return tawSystemSubRole;
    }

    /**
     * 派发工单
     *
     * @param map
     * @param columnMap
     * @param userId
     * @param processTemplateName
     * @param operateName
     * @return
     * @throws Exception
     */
    public Map sendNewSheet(HashMap WpsMap, String userId, String processTemplateName, String operateName) throws Exception {
//		HashMap WpsMap = prepareMap(map,columnMap);
        HashMap sessionMap = registWpsSecutiry(userId);

        IBusinessFlowService businessFlowService =
                (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");
        businessFlowService.initProcess(processTemplateName, operateName,
                WpsMap, sessionMap);

        HashMap mainMap = (HashMap) WpsMap.get("main");
        return mainMap;
    }

    /**
     * 处理工单
     *
     * @param sheetId    工单编号
     * @param map
     * @param columnMap
     * @param userId
     * @param taskName
     * @param mainBeanId
     * @param taskBeanId
     * @return
     * @throws Exception
     */
    public String dealSheet(String sheetKey, Map map, Map columnMap, String userId, ITaskService iTaskService) throws Exception {
        try {
//			ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(taskBeanId);
            List list = iTaskService.getCurrentUndoTask(sheetKey);

            //System.out.println("renew dealSheet");
            BocoLog.info(this, "renew dealSheet");
            for (int i = 0; i < list.size(); i++) {
                ITask task = (ITask) list.get(i);
                String taskId = task.getId();
                map.put("activeTemplateId", task.getTaskName());
                map.put("aiid", task.getId());
                Map serializableMap = SheetUtils.serializableParemeterMap(map);
                Iterator it = serializableMap.keySet().iterator();
                HashMap WpsMap = new HashMap();
                while (it.hasNext()) {
                    String mapKey = (String) it.next();
                    Map tempMap = (Map) serializableMap.get(mapKey);
                    if (taskId.equals("")) {
                        Object obj = tempMap.get("aiid");
                        if (obj.getClass().isArray()) {
                            Object[] obja = (Object[]) obj;
                            obj = obja[0];
                        }
                        taskId = StaticMethod.nullObject2String(obj);
                    }
                    HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
                    HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                            tempColumnMap);
                    WpsMap.putAll(tempWpsMap);
                }

                //System.out.println("start wps login");
                BocoLog.info(this, "start wps login");
                HashMap sessionMap = registWpsSecutiry(userId);

                //System.out.println("login success");
                BocoLog.info(this, "login success");
                IBusinessFlowService businessFlowService =
                        (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");
                businessFlowService.completeHumanTask(taskId, WpsMap,
                        sessionMap);
            }
            return sheetKey;
        } catch (Exception err) {
            throw err;
        }
    }

    public void claimTask(String taskId, Map map, Map columnMap, String userId)
            throws Exception {
        Map serializableMap = SheetUtils.serializableParemeterMap(map);
        Iterator it = columnMap.keySet().iterator();
        HashMap WpsMap = new HashMap();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Map tempMap = (Map) serializableMap.get(mapKey);
            HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
            if (tempMap != null) {
                // 页面上有相应的输入值
                HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                        tempColumnMap);
                WpsMap.putAll(tempWpsMap);
            } else {
                // 页面上没有相应的输入值，需要自行构建
                Iterator iterator = tempColumnMap.keySet().iterator();
                HashMap temp = new HashMap();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    temp.put(key, null);
                }
                WpsMap.putAll(temp);
            }
        }
        HashMap sessionMap = registWpsSecutiry(userId);
        IBusinessFlowService businessFlowService =
                (IBusinessFlowService) ApplicationContextHolder.getInstance().getBean("businessFlowService");
        // 申请任务
        businessFlowService.claimTask(taskId, WpsMap, sessionMap);
    }

    /**
     * 调用event事件，如阶段回复
     *
     * @param sheetKey
     * @param map
     * @param columnMap
     * @param userId
     * @param taskName
     * @param mainBeanId
     * @param taskBeanId
     * @param processTemplateName
     * @param operateName
     * @return
     * @throws Exception
     */
    public void performProcessEvent(String taskId, IMainService iMainService, ILinkService iLinkService, ITaskService iTaskService, HashMap dataMap) throws Exception {
        HashMap linkMap = (HashMap) dataMap.get("link");
        HashMap operateMap = (HashMap) dataMap.get("operate");
        String operateType = StaticMethod.nullObject2String(linkMap.get("operateType"));
        String copyPerformer = StaticMethod.nullObject2String(operateMap.get("copyPerformer"));
        ITask task = null;
        BaseLink linkbean = null;
        String processTemplateName = iMainService.getFlowTemplateName();
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        //得到main对象Start--通过link中的mainId得到main对象；当新增时还没有mainId，则从MainMap中取得main的数据
        BaseMain main = null;
        String mainId = StaticMethod.nullObject2String(linkMap.get("mainId"));
        if (mainId.equals("")) {
            if (dataMap.get("main") != null) {
                HashMap mainMap = (HashMap) dataMap.get("main");
                mainId = StaticMethod.nullObject2String(mainMap.get("id"));
                main = iMainService.getMainObject();
                main.setId(mainId);
                main.setSheetId(StaticMethod.nullObject2String(mainMap.get("sheetId")));
                main.setTitle(StaticMethod.nullObject2String(mainMap.get("title")));
                main.setPiid(StaticMethod.nullObject2String(mainMap.get("piid")));
            }
        } else {
            main = iMainService.getSingleMainPO(mainId);
        }
        //得到main对象end
        if (Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD) {
            // 阶段通知
            if (!taskId.equals("")) {
                BocoLog.info(this, "===优化======已阅===");
                task = (ITask) iTaskService.getSinglePO(taskId);
                task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
                iTaskService.addTask(task);
            } else {
                BocoLog.info(this, "===优化======阶段通知===");
                String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
                String dealPerformerLeader = StaticMethod.nullObject2String(operateMap.get("dealPerformerLeader"));
                String dealPerformerType = StaticMethod.nullObject2String(operateMap.get("dealPerformerType"));

                String[] dealPerformers = dealPerformer.split(",");
                String[] dealPerformerLeaders = dealPerformerLeader.split(",");
                String[] dealPerformerTypes = dealPerformerType.split(",");

                for (int i = 0; i < dealPerformers.length; i++) {
                    linkbean = (BaseLink) iLinkService.getLinkObject().getClass().newInstance();
                    SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
                    linkbean.setId(UUIDHexGenerator.getInstance().getID());
                    linkbean.setMainId(mainId);
                    linkbean.setOperateType(new Integer(operateType));
                    linkbean.setOperateTime(nowDate);
                    linkbean.setToOrgRoleId(dealPerformers[i]);
                    linkbean.setOperateDay(calendar.get(Calendar.DATE));
                    linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
                    linkbean.setOperateYear(calendar.get(Calendar.YEAR));
                    // 保存task数据
                    task = (ITask) iTaskService.getTaskModelObject().getClass().newInstance();
                    task.setId(UUIDHexGenerator.getInstance().getID());
                    task.setTaskName("advice");
                    task.setTaskDisplayName("阶段通知");
                    task.setOperateRoleId(dealPerformers[i]);
                    task.setTaskOwner(dealPerformerLeaders[i]);
                    task.setFlowName(processTemplateName);
                    task.setOperateType(dealPerformerTypes[i]);
                    newSaveTask(main, linkbean, task, iTaskService);
                    linkbean.setAiid(task.getId());
                    iLinkService.addLink(linkbean);
                }
            }
        } else if (Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP) {
            // 阶段回复
            task = iTaskService.getSinglePO(taskId);
            if (task.getTaskName().equals("reply")) {
                BocoLog.info(this, "===优化======已阅===");
                task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
                iTaskService.addTask(task);
            } else {
                BocoLog.info(this, "===优化======阶段回复===");

                String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
                String dealPerformerLeader = StaticMethod.nullObject2String(operateMap.get("dealPerformerLeader"));
                String dealPerformerType = StaticMethod.nullObject2String(operateMap.get("dealPerformerType"));

                String[] dealPerformers = dealPerformer.split(",");
                String[] dealPerformerLeaders = dealPerformerLeader.split(",");
                String[] dealPerformerTypes = dealPerformerType.split(",");

                for (int i = 0; i < dealPerformers.length; i++) {
                    linkbean = (BaseLink) iLinkService.getLinkObject().getClass().newInstance();
                    SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
                    String Aiid = StaticMethod.nullObject2String(linkbean.getAiid(), "");
                    ITask tmptask = iTaskService.getSinglePO(Aiid);
                    linkbean.setActiveTemplateId(tmptask.getTaskName());
                    linkbean.setId(UUIDHexGenerator.getInstance().getID());
                    linkbean.setMainId(mainId);
                    linkbean.setOperateType(new Integer(operateType));
                    linkbean.setOperateTime(nowDate);
                    linkbean.setToOrgRoleId(dealPerformers[i]);
                    linkbean.setOperateDay(calendar.get(Calendar.DATE));
                    linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
                    linkbean.setOperateYear(calendar.get(Calendar.YEAR));
                    // 保存task数据
                    task = (ITask) iTaskService.getTaskModelObject().getClass().newInstance();
                    task.setId(UUIDHexGenerator.getInstance().getID());
                    task.setTaskName("reply");
                    task.setTaskDisplayName("阶段回复");
                    task.setOperateRoleId(dealPerformers[i]);
                    task.setTaskOwner(dealPerformerLeaders[i]);
                    task.setFlowName(processTemplateName);
                    task.setOperateType(dealPerformerTypes[i]);
                    newSaveTask(main, linkbean, task, iTaskService);
                    linkbean.setAiid(task.getId());
                    iLinkService.addLink(linkbean);
                }
            }
        } else if (copyPerformer.equals("") && Integer.parseInt(operateType) == Constants.ACTION_READCOPY) {
            // 抄送确认
            BocoLog.info(this, "===优化======抄送确认===");
            linkbean = (BaseLink) iLinkService.getLinkObject().getClass().newInstance();
            SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
            linkbean.setId(UUIDHexGenerator.getInstance().getID());
            linkbean.setOperateType(new Integer(Constants.ACTION_READCOPY));
            iLinkService.addLink(linkbean);
            ITask perTask = iTaskService.getTaskByPreLinkid(linkbean.getPreLinkId());
            perTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
            perTask.setCurrentLinkId(linkbean.getId());
            iTaskService.addTask(perTask);
        } else if (!copyPerformer.equals("") && Integer.parseInt(operateType) != Constants.ACTION_DRAFT) {
            BocoLog.info(this, "===优化======抄送===");
            // 抄送
            String[] copyPerformers = copyPerformer.split(",");
            // 抄送多人
            String copyPerformerLeader = StaticMethod.nullObject2String(operateMap.get("copyPerformerLeader"));
            String copyPerformerType = StaticMethod.nullObject2String(operateMap.get("copyPerformerType"));
            String[] copyPerformerLeaders = copyPerformerLeader.split(",");
            String[] copyPerformerTypes = copyPerformerType.split(",");

            for (int i = 0; i < copyPerformers.length; i++) {
                linkbean = (BaseLink) iLinkService.getLinkObject().getClass().newInstance();
                SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
                linkbean.setId(UUIDHexGenerator.getInstance().getID());
                linkbean.setOperateType(new Integer(Constants.ACTION_MAKECOPYFOR));
                linkbean.setOperateTime(nowDate);
                linkbean.setMainId(mainId);
                linkbean.setToOrgRoleId(copyPerformers[i]);
                linkbean.setOperateDay(calendar.get(Calendar.DATE));
                linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
                linkbean.setOperateYear(calendar.get(Calendar.YEAR));
                // 保存task数据
                task = (ITask) iTaskService.getTaskModelObject().getClass().newInstance();
                task.setTaskName("cc");
                task.setTaskDisplayName("抄送");
                task.setOperateRoleId(copyPerformers[i]);
                task.setTaskOwner(copyPerformerLeaders[i]);
                task.setFlowName(processTemplateName);
                task.setOperateType(copyPerformerTypes[i]);
                newSaveTask(main, linkbean, task, iTaskService);
                linkbean.setAiid(task.getId());
                iLinkService.addLink(linkbean);
                // 如果为抄送再抄送，则需要结束上条task
                if (Integer.parseInt(operateType) == Constants.ACTION_READCOPY) {
                    ITask perTask = iTaskService.getTaskByPreLinkid(linkbean.getPreLinkId());
                    perTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
                    perTask.setCurrentLinkId(linkbean.getId());
                    iTaskService.addTask(perTask);
                }
            }
        }
    }

    /**
     * 通过工单编号和任务名称获取task对象
     *
     * @param sheetId
     * @param taskName
     * @param mainBeanId
     * @param taskBeanId
     * @return
     * @throws Exception
     */
    public ITask getTask(String sheetKey, String taskName, String taskBeanId) throws Exception {
        ITaskService taskService = (ITaskService) ApplicationContextHolder.getInstance().getBean(taskBeanId);
        ITask task = taskService.getTask(sheetKey, taskName);

        if (task == null)
            throw new Exception("没找到sheetNo＝" + sheetKey + "的" + taskName + "任务");
        return task;

    }

    public HashMap prepareMap(Map map, Map columnMap) throws Exception {
        try {
            Map serializableMap = SheetUtils.serializableParemeterMap(map);
            Iterator it = serializableMap.keySet().iterator();
            HashMap WpsMap = new HashMap();
            while (it.hasNext()) {
                String mapKey = (String) it.next();
                Map tempMap = (Map) serializableMap.get(mapKey);
                HashMap tempColumnMap = (HashMap) columnMap.get(mapKey);
                HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap,
                        tempColumnMap);
                WpsMap.putAll(tempWpsMap);
            }

            return WpsMap;
        } catch (Exception er) {
            er.printStackTrace();
            throw er;
        }
    }

    private HashMap registWpsSecutiry(String userId) throws Exception {
//		IWorkflowSecutiryService safeService=
//			 (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
//		
//		Subject subject = safeService.logIn(userId, "111");
        HashMap sessionMap = new HashMap();
//		sessionMap.put("wpsSubject", subject);
        sessionMap.put("userId", userId);
        sessionMap.put("password", "111");
        return sessionMap;
    }

    /**
     * 下载附件
     *
     * @param cnName     中文名称
     * @param url
     * @param attachCode 工单附件编码
     * @return 附件id
     */
    public String getAttachFromOtherSystem(String cnName, String url, String attachCode) {
        ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
        return mgr.downFromOtherSystem(cnName, url, attachCode);
    }

    /**
     * 转换附件id，目前将附件id保存的到工单表中需要加单引号（汗!）
     *
     * @param attachId
     * @return
     */
    public String getAttachStr(String attachId) {
        return "'" + attachId + "'";
    }

    /**
     * 下载附件
     *
     * @param attachRef  xml格式
     * @param attachCode
     * @return
     */
    public String getAttach(String attachRef, String attachCode) {
        InterfaceUtil interfaceUtil = new InterfaceUtil();
        List attachList = interfaceUtil.getAttachRefFromXml(attachRef);

        return this.getAttach(attachList, attachCode);
    }

    public String getAttach(List attachList, String attachCode) {
        if (attachList != null && attachList.size() > 0) {
            if (attachCode.length() == 0)
                System.out.println("attachCode为空");
            else {
                System.out.println("start getAttach");
                String fileName = "";
                ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
                for (int i = 0; i < attachList.size(); i++) {
                    AttachRef attach = (AttachRef) attachList.get(i);
                    String cnName = attach.getAttachName();
                    String url = attach.getAttachURL();
                    fileName += this.getAttachStr(mgr.downFromOtherSystem(cnName, url, attachCode)) + ",";
                }
                if (fileName.length() > 0)
                    fileName = fileName.substring(0, fileName.length() - 1);
                return fileName;
            }
        }
        return null;
    }

    /**
     * 如果存在单条电路派发时，结束多余流程实例的方法
     * 例如：由实施驳回多条电路到方案制定，方案制定完成后派发给实施
     *
     * @param taskName
     * @param sheetKey
     * @param beanName
     * @throws Exception
     */
    public void finishRedundant(String taskName, String sheetKey, String beanName) throws Exception {

        IBaseSheet basesheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(beanName);

        List taskList = basesheet.getTaskService().getTasksByTaskNameAndSheetId(taskName, sheetKey);
        if (taskList != null && taskList.size() > 0) {
            //登录信息
            HashMap sessionMap = new HashMap();
            sessionMap.put("userId", "admin");
            sessionMap.put("password", "111");
            //构造数据
            HashMap map = new HashMap();
            map.put("main", basesheet.getMainService().getSingleMainPO(sheetKey));
            map.put("link", basesheet.getLinkService().getLinkObject().getClass().newInstance());
            map.put("operate", basesheet.getPageColumnName());

            String flowName = basesheet.getMainService().getFlowTemplateName();
            ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSystemWorkflowManager");
            TawSystemWorkflow wf = wfManager.getTawSystemWorkflowByName(flowName);
            String mainservicebeanid = wf.getMainServiceBeanId();

            HashMap WpsMap = new HashMap();
            HashMap requestMap = new HashMap();
            requestMap.put("beanId", mainservicebeanid);
            requestMap.put("phaseId", "Over");
            requestMap.put("mainClassName", basesheet.getMainService().getMainObject().getClass().getName());
            HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(requestMap, map);
            WpsMap.putAll(tempWpsMap);
            for (int i = 0; i < taskList.size(); i++) {
                ITask tmpTask = (ITask) taskList.get(i);
                if (!tmpTask.getTaskStatus().equals("5")) {
                    basesheet.getBusinessFlowService().completeHumanTask(tmpTask.getId(), WpsMap, sessionMap);
                    tmpTask.setDeleted("1");
                    tmpTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
                    basesheet.getTaskService().addTask(tmpTask);
                }
            }
        }
    }

    /**
     * 单条电路派发和驳回的event提交
     *
     * @param sheetKey
     * @param taskId
     * @param phaseId
     * @param beanName
     * @param ifSaveLink
     * @param requestMap
     * @throws Exception
     */
    public void singleEventPerform(String sheetKey, String taskId, String phaseId, NewIBaseSheet basesheet, Boolean ifSaveLink, Map requestMap) throws Exception {
//		IBaseSheet basesheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean(beanName);
        //登录信息
        HashMap sessionMap = new HashMap();
        sessionMap.put("userId", "admin");
        sessionMap.put("password", "111");

        //-----构造数据 start------
        //得到piid和corrKey
        ITask task = basesheet.getTaskService().getSinglePO(taskId);
        String piid = task.getProcessId();
        Map variableMap = basesheet.getBusinessFlowService().getVariable(piid, "corrKey", sessionMap);
        String corrKey = (String) variableMap.get("corrKey");

        //构造EngineMap
        HashMap map = new HashMap();
        map.put("link", basesheet.getLinkService().getLinkObject().getClass().newInstance());
        map.put("operate", basesheet.getPageColumnName());

        //得到beanId
        String processTemplateName = basesheet.getMainService().getFlowTemplateName();
        ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
                .getInstance().getBean("ITawSystemWorkflowManager");
        TawSystemWorkflow wf = wfManager.getTawSystemWorkflowByName(processTemplateName);
        String mainservicebeanid = wf.getMainServiceBeanId();

        //如果为驳回
        String operateType = StaticMethod.nullObject2String(requestMap.get("operateType"));
        String dealPerformer = StaticMethod.nullObject2String(requestMap.get("dealPerformer"));
        if (operateType.equals("4") && dealPerformer.equals("")) {
            //得到操作人
            List tmpTaskList = basesheet.getTaskService().getTasksByTaskNameAndSheetId(phaseId, sheetKey);
            if (tmpTaskList != null && tmpTaskList.size() > 0) {
                ITask tmpTask = (ITask) tmpTaskList.get(0);
                requestMap.put("dealPerformer", StaticMethod.nullObject2String(tmpTask.getOperateRoleId()));
                requestMap.put("dealPerformerLeader", StaticMethod.nullObject2String(tmpTask.getTaskOwner()));
                requestMap.put("dealPerformerType", StaticMethod.nullObject2String(tmpTask.getOperateType()));
            }
        }
        requestMap.put("linkRejectFlag", "1");
        //link数据
        requestMap.put("activeTemplateId", task.getTaskName());
        requestMap.put("operateRoleId", task.getOperateRoleId());
        requestMap.put("operateUserId", task.getTaskOwner());
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getTaskOwner(), "tawSystemUserDao");
        if (!name.equals("")) {
            ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
            TawSystemUser user = userManager.getUserByuserid(task.getTaskOwner());
            requestMap.put("operaterContact", user.getMobile());
            requestMap.put("operateDeptId", user.getDeptid());
        }
        //设置参数
        String extendKey1 = "";
        if (ifSaveLink.booleanValue()) {
            String[] array = dealPerformer.split(",");
            for (int i = 0; i < array.length; i++) {
                extendKey1 += UUIDHexGenerator.getInstance().getID() + ",";
            }
        }
        requestMap.put("beanId", mainservicebeanid);
        requestMap.put("phaseId", phaseId);
        requestMap.put("mainClassName", basesheet.getMainService().getMainObject().getClass().getName());
        requestMap.put("linkClassName", basesheet.getLinkService().getLinkObject().getClass().getName());
        requestMap.put("extendKey1", extendKey1);
        requestMap.put("extendKey2", UUIDHexGenerator.getInstance().getID());
        HashMap tempWpsMap = SheetUtils.actionMapToEngineMap(requestMap, map);
        tempWpsMap.put("corrKey", corrKey);
        tempWpsMap.put("saveMainFlag", ifSaveLink.toString());
        requestMap.put("hasNextTaskFlag", "invokeProcess");
        HashMap WpsMap = new HashMap();
        WpsMap.putAll(tempWpsMap);
        //-----构造数据 end------

        //提交
        String flowName = task.getFlowName();
        List taskList = null;
        if (operateType.equals("4")) {
            taskList = basesheet.getTaskService().getTasksByCondition(
                    "( taskStatus=" + Constants.TASK_STATUS_READY
                            + " or taskStatus=" + Constants.TASK_STATUS_CLAIMED
                            + " or taskStatus=" + Constants.TASK_STATUS_INACTIVE + " ) and sheetKey='" + sheetKey + "' and taskName='" + phaseId + "' ");
        }
        if (taskList == null || taskList.size() == 0) {
            basesheet.getBusinessFlowService().triggerEvent(piid, flowName, "singleSend", WpsMap, sessionMap);
        }
    }

    private void newSaveTask(BaseMain main, BaseLink link, ITask task, ITaskService iTaskService) throws Exception {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        if (task.getId() == null)
            task.setId(UUIDHexGenerator.getInstance().getID());
        if (task.getCreateTime() == null)
            task.setCreateTime(nowDate);
        if (task.getTaskStatus() == null)
            task.setTaskStatus(Constants.TASK_STATUS_READY);
        if (task.getProcessId() == null && main.getPiid() != null)
            task.setProcessId(main.getPiid());
        if (task.getSheetKey() == null)
            task.setSheetKey(main.getId());
        if (task.getSheetId() == null)
            task.setSheetId(main.getSheetId());
        if (task.getTitle() == null)
            task.setTitle(main.getTitle());
        if (task.getAcceptTimeLimit() == null)
            task.setAcceptTimeLimit(link.getNodeAcceptLimit());
        if (task.getCompleteTimeLimit() == null)
            task.setCompleteTimeLimit(link.getNodeCompleteLimit());
        if (task.getPreLinkId() == null)
            task.setPreLinkId(link.getId());
        if (task.getIfWaitForSubTask() == null)
            task.setIfWaitForSubTask("false");

        task.setCreateDay(calendar.get(Calendar.DATE));
        task.setCreateMonth(calendar.get(Calendar.MONTH) + 1);
        task.setCreateYear(calendar.get(Calendar.YEAR));

        iTaskService.addTask(task);
    }
}
