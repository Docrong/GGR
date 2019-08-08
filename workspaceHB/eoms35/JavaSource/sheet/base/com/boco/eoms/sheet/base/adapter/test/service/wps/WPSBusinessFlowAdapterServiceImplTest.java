/*
 * Created on 2007-8-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.test.service.wps;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-24 16:20:09
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class WPSBusinessFlowAdapterServiceImplTest extends ConsoleTestCase {

    IBusinessFlowAdapterService service;


    public void testGetWorkflowManager() {
    }

    public void testSetWorkflowManager() {
    }

    public void testGetProcessTemplatesCount() {
    }

    public void testGetProcessTemplates() {
    }

    public void testGetProcessTemplateInfo() {
    }

    public void testGetProcessInstancesCountByProcessTemplateName() {
    }

    public void testGetProcessInstancesByProcessTemplateName() {
    }

    public void testGetStartedProcessInstancesCount() {
    }

    public void testGetStartedProcessInstances() {
    }

    public void testGetAdministratedProcessInstancesCount() {
    }

    public void testGetAdministratedProcessInstances() {
    }

    public void testGetSubProcessInstancesCount() {
    }

    public void testGetSubProcessInstances() {
    }

    public void testGetProcessInstancesCount() {
    }

    public void testGetProcessInstances() {
    }

    public void testGetProcessInstanceInfo() {
    }

    /*
     * Class under test for String triggerProcess(String, String, HashMap)
     */
    public void testTriggerProcessStringStringHashMap() {

        //        IBusinessFlowService businessFlowService = (IBusinessFlowService)
        // getBean("businessFlowService");
        //        String processTemplateName = "ResourceAssessProcess";
        //        String operateName = "start";
        //
        //        HashMap map = new HashMap();
        //        HashMap parameters = new HashMap();
        //        parameters.put("sendPostId", new Integer(123));
        //
        //        parameters.put("result", "fdakfdka");
        //
        //        parameters.put("sheetId", "123");
        //        parameters.put("title", "hello wps");
        //
        //        parameters.put("sendTime", new Date());
        //
        //        map.put("main", parameters);
        //
        //        try {
        //            service.triggerProcess(processTemplateName, operateName, map);
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            fail();
        //        }
    }

    /*
     * Class under test for String triggerProcess(String, String, HashMap,
     * HashMap)
     */
    public void testTriggerProcessStringStringHashMapHashMap() {
    }

    public void testGetProcessInstanceCustomPropertyNameList() {
    }

    public void testGetProcessInstanceCustomProperties() {
    }

    public void testGetProcessInstanceCustomPropertyValue() {
    }

    public void testSetProcessInstanceCustomProperty() {
    }

    public void testSetProcessInstanceCustomProperties() {
    }

    public void testCreateActivityWorkitem() {
    }

    public void testCreateProcessWorkitem() {
    }

    public void testDeleteActivityWorkitem() {
    }

    public void testDeleteProcessWorkitem() {
    }

    public void testGetProcessVariableValue() {
    }

    public void testSetProcessVariable() {
    }

    public void testGetUsersInProcessRole() {
    }

    public void testGetUsersInActivityRole() {
    }

    public void testIsSystemAdministrator() {
    }

    public void testIsSystemMonitor() {
    }

    public void testPauseProcess() {
    }

    public void testResumeProcess() {
    }

    public void testTerminateProcess() {
    }

    public void testDeleteProcess() {
    }

    public void testGetOwnTasksCount() {
    }

    /*
     * Class under test for List getOwnTasks(HashMap)
     */
    public void testGetOwnTasksHashMap() {
    }

    /*
     * Class under test for List getOwnTasks(int, int, HashMap, String, HashMap)
     */
    public void testGetOwnTasksintintHashMapStringHashMap() {
    }

    public void testGetAdministratedTasksCount() {
    }

    /*
     * Class under test for List getAdministratedTasks(HashMap)
     */
    public void testGetAdministratedTasksHashMap() {
    }

    /*
     * Class under test for List getAdministratedTasks(int, int, HashMap)
     */
    public void testGetAdministratedTasksintintHashMap() {
    }

    public void testClaimTask() {
    }

    public void testUnclaimTask() {
    }

    public void testGetActivityInputData() {
    }

    public void testSetTaskOutputData() {
    }

    /*
     * Class under test for void finishTask(String)
     */
    public void testFinishTaskString() {
    }

    /*
     * Class under test for void finishTask(String, HashMap)
     */
    public void testFinishTaskStringHashMap() {
    }

    public void testTransferTask() {
    }

    public void testCreateHashMap() {
    }

    public void testGetActivityInstanceId() {
    }

    public void testTransferIdFormActionToDB() {
        try {
            String tkid = service.transferIdFormActionToDB("_TKI:a01b0117.86fec5b1.f5fe573f.74e1054c");
        } catch (Exception e) {

        }

    }

    public void testTransferIdFromDBToAction() {
        try {
            String tkid = service.transferIdFromDBToAction("oBsBF4b+xbH1/lc/dOEFTA==");
        } catch (Exception e) {

        }

    }

}
