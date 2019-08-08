package com.boco.eoms.sheet.daiweiindexreduction.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiSubtractTableMgr;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiWeiIndexReductionLinkManager;
import com.boco.eoms.sheet.daiweiindexreduction.webapp.form.FileUploadForm;

public final class DaiSubtractTableUploadAction extends BaseAction {

    private TawCommonsAccessoriesDao dao;

    public TawCommonsAccessoriesDao getDao() {
        return dao;
    }

    public void setDao(TawCommonsAccessoriesDao dao) {
        this.dao = dao;
    }

    /**
     * upload 附件上传解析并展现在前台 by wmm 20170805
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward upload(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String appId = StaticMethod.nullObject2String(request
                    .getParameter("appId"));

            /*核减专业*/
            String profDictid = StaticMethod.nullObject2String(request
                    .getParameter("profName"));
            /*核减理由类别*/
            String subtractrCategoryProf = StaticMethod.nullObject2String(request
                    .getParameter("mainSubType"));
            /*核减状态*/
            String profState = StaticMethod.nullObject2String(request
                    .getParameter("ReserveE"));
            /*为否的条数*/
            String noPass = StaticMethod.nullObject2String(request
                    .getParameter("noPass"));
            noPass = "".equals(noPass) ? "0" : noPass;
            /*新建和重派的区别*/
            String sheetFlag = StaticMethod.nullObject2String(request
                    .getParameter("sheetFlag"));
            BocoLog.info(this, "@@@@@@@@@@@@@@@@@@@@@@@sheetFlag" + sheetFlag);
            ID2NameService id2NameService = (ID2NameService) ApplicationContextHolder
                    .getInstance().getBean("id2nameService");
            String profName = id2NameService
                    .id2Name(profDictid, "ItawSystemDictTypeDao");  // 用 ID2NameService 把对象转化为 Name
            BocoLog.info(this, "-----profName-----" + profName);
            FileUploadForm fileUploadForm = (FileUploadForm) form;
            FormFile uploadFile = fileUploadForm.getFiles();
            String fileCnName = uploadFile.getFileName();// 文件名称
            System.out.println("文件上传-文件名称1：" + fileCnName);

            TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
            ITawCommonsAccessoriesConfigManager configManager = (ITawCommonsAccessoriesConfigManager) ApplicationContextHolder
                    .getInstance().getBean(
                            "ItawCommonsAccessoriesConfigManager");
            ITawCommonsAccessoriesManager accessoriesManager = (ITawCommonsAccessoriesManager) ApplicationContextHolder
                    .getInstance().getBean("ItawCommonsAccessoriesManager");
            TawCommonsAccessoriesConfig config = configManager
                    .getAccessoriesConfigByAppcode(appId);

            ITawSystemDictTypeManager iTawSystemDictTypeManager = (ITawSystemDictTypeManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSystemDictTypeManager");
            IDaiWeiIndexReductionLinkManager linkMgr = (IDaiWeiIndexReductionLinkManager) ApplicationContextHolder
                    .getInstance().getBean("iDaiWeiIndexReductionLinkManager");
            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            /**
             *
             * 获取核减专业的下一级集合
             * 遍历获取
             * 转化名称后存入list
             *
             * */
            List list = iTawSystemDictTypeManager.getDictSonsByDictid(profDictid); // 查询下一级信息
            Map sonsDictMap = new HashMap();
            String key = "";
            String value = "";
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    TawSystemDictType tawSystemDictType = (TawSystemDictType) list.get(i);
                    key = StaticMethod.nullObject2String(tawSystemDictType.getDictName());
                    value = StaticMethod.nullObject2String(tawSystemDictType.getDictId());
                    sonsDictMap.put(key, value);
                }
            }

            // 获取文件保存路径
            String rootFilePath = AccessoriesMgrLocator
                    .getAccessoriesAttributes().getUploadPath();
            String fileType = fileCnName.substring(fileCnName.indexOf("."));
            String fileName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
                    + this.randomKey(4) + fileType;
            String path = "accessories/uploadfile" + config.getPath();
            String filePath = rootFilePath + path;
            File files = new File(filePath);
            if (!files.exists()) {
                files.mkdir();
            }
            File file = new File(filePath + "/" + fileName);
            FileOutputStream outer = new FileOutputStream(file);
            byte[] buffer = uploadFile.getFileData();
            outer.write(buffer);
            outer.close();
            uploadFile.destroy();
            // 上传文件结束

            // 附件信息入库
            TawCommonsAccessories accessories = new TawCommonsAccessories();
            accessories.setAccessoriesCnName(fileCnName);
            accessories.setAccessoriesName(fileName);
            accessories.setAccessoriesPath(path);
            accessories.setAccessoriesSize(file.length());
            accessories.setAccessoriesUploadTime(StaticMethod.getLocalTime());
            accessories.setAppCode(config.getAppCode());
            accessoriesManager.saveTawCommonsAccessories(accessories);

            IDaiSubtractTableMgr subMgr = (IDaiSubtractTableMgr) ApplicationContextHolder
                    .getInstance().getBean("daiSubtractTableMgr");
            JSONObject json = new JSONObject();
            String faultMsg = ""; // 错误信息提示
            String refSheetId = StaticMethod.nullObject2String(request
                    .getParameter("refSheetId"));
            // 关联的main表中的外键 id
            if ("daiweiindexreduction".equals(appId)) {
                if (!"".equals(fileName)) {
                    System.out.println("filePath:" + file.getPath());
                    FileInputStream inputStream = new FileInputStream(file);
                    // 第一步，创建一个webbook，对应一个Excel文件
                    // HSSFWorkbook wb = new HSSFWorkbook(inputStream);
                    XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                    if (wb != null) {
                        XSSFSheet sheet = wb.getSheetAt(0); // 获取单元格

                        if (sheet != null) {
                            int lastRow = sheet.getLastRowNum();

                            /*核减理由类别 校验*/
                            Set subtracts = new HashSet(); // 核减理由类别放入set集合中

                            boolean isCanSave = true;
                            if (sheetFlag.equals("new")) {  // 新增页面
//								 解决：新建工单核减表已经上传成功 & 入库，但没有派发工单 & 下次重新核减该工单工单号重复问题
//								sqlMgr.updateTasks("DELETE from (select * from DAISUBTRACTSINGLE_TABLE where RESERVEB not in (select mainReserveA from DaiWeiIndexReduction_main)and  reserveF='0')");
                                String sqlserialId = "";
                                for (int rowNum = sheet.getFirstRowNum() + 1; rowNum <= lastRow; rowNum++) {
                                    XSSFRow row = sheet.getRow(rowNum);
                                    if (row == null)
                                        break;
                                    String serialId = StaticMethod.nullObject2String(row.getCell(3));
                                    if (!"".equals(serialId))
                                        sqlserialId = sqlserialId + "'" + serialId + "'";
                                    if (rowNum < lastRow)
                                        sqlserialId = sqlserialId + ",";
                                }

                                if (sqlserialId.length() > 0)
                                    sqlMgr.updateTasks("DELETE from (select * from DAISUBTRACTSINGLE_TABLE where RESERVEB not in (select mainReserveA from DaiWeiIndexReduction_main where mainReserveA is not null)and  reserveF='0' and serialId in(" + sqlserialId + ") )");

                                if ("".equals(refSheetId)) {
                                    refSheetId = UUIDHexGenerator.getInstance().getID();  // 判断关联的main表中的外键 id 是否为空
                                }
                                sqlMgr.updateTasks("delete from DAISUBTRACTSINGLE_TABLE where reserveB ='" + refSheetId + "'");  // 删除导入数据库之前的excel数据

                                Set sheetIds = new HashSet(); // 工单号放入set集合中
                                List allList = linkMgr.getLinksBycondition(" 1=1 and reserveB !='" + refSheetId + "'", "DaiSubtractTable");
                                DaiSubtractTable table = null;
                                for (int t = 0; allList != null && t < allList.size(); t++) {
                                    table = (DaiSubtractTable) allList.get(t);
                                    if (table != null) {
                                        sheetIds.add(table.getSerialId());
                                    }
                                }
                                if ("0".equals(noPass)) {

                                    for (int rowNum = sheet.getFirstRowNum() + 1; rowNum <= lastRow; rowNum++) {
                                        XSSFRow row = sheet.getRow(rowNum);
                                        if (row == null) {
                                            break;
                                        } else {
                                            /* 核减专业 */
                                            String subtractProfessional = StaticMethod
                                                    .nullObject2String(row.getCell(0));
                                            /* 核减理由类别 */
                                            String subtractrCategory = StaticMethod
                                                    .nullObject2String(row.getCell(1));
                                            /* 申请核减说明 */
                                            String applicationReduction = StaticMethod
                                                    .nullObject2String(row.getCell(2));
                                            /*工单流水号*/
                                            String serialId = StaticMethod
                                                    .nullObject2String(row.getCell(3));
                                            /* 工单主题 */
                                            String title = StaticMethod
                                                    .nullObject2String(row.getCell(4));

                                            /* 派单时间 */
                                            String mainDispatchTime = StaticMethod
                                                    .nullObject2String(row.getCell(5));
                                            /* 故障分类 */
                                            String mainFaultClass = StaticMethod
                                                    .nullObject2String(row.getCell(6));
                                            /* 故障历时(清除时间-发生时间) */
                                            String mainFaultDurat = StaticMethod
                                                    .nullObject2String(row.getCell(7));
                                            /* 处理历时（恢复时间-第一次转单时间） */
                                            String mainProDurat = StaticMethod
                                                    .nullObject2String(row.getCell(8));
                                            /* 故障发生时间 */
                                            String mainFaultFaTime = StaticMethod
                                                    .nullObject2String(row.getCell(9));

                                            /* 告警平台清除时间 */
                                            String mainFaultQiTime = StaticMethod
                                                    .nullObject2String(row.getCell(10));
                                            /* 最终处理措施（T1，T2） */
                                            String mainFinalMeasures = StaticMethod
                                                    .nullObject2String(row.getCell(11));
                                            /* T2接单时间 */
                                            String mainT2Time = StaticMethod
                                                    .nullObject2String(row.getCell(12));
                                            /* T2处理时限 */
                                            String mainT2Limit = StaticMethod
                                                    .nullObject2String(row.getCell(13));
                                            /* T2最终处理时间 */
                                            String mainT2FinalTime = StaticMethod
                                                    .nullObject2String(row.getCell(14));
                                            /* 最终处理地市 */
                                            String mainFinalCtiy = StaticMethod
                                                    .nullObject2String(row.getCell(15));
                                            /* 最终处理班组 */
                                            String mainFinalTeam = StaticMethod
                                                    .nullObject2String(row.getCell(16));
                                            /* 最终处理区县 */
                                            String mainFinalCounty = StaticMethod
                                                    .nullObject2String(row.getCell(17));
                                            /* 综合代维区县 */
                                            String mainDaiCounty = StaticMethod
                                                    .nullObject2String(row.getCell(18));
                                            /* 最终处理班组属性 */
                                            String mainFinalAttributes = StaticMethod
                                                    .nullObject2String(row.getCell(19));


                                            // 非空
                                            if ("".equals(subtractProfessional)) {
                                                faultMsg += "第" + rowNum + "行1列核减专业为空,";
                                                isCanSave = false;
                                            } else if (!profName.equals(subtractProfessional)) {
                                                faultMsg += "第" + rowNum + "行1列与工单核减专业信息不符，请检查！";  // ok
                                                isCanSave = false;
                                            }
                                            if ("".equals(subtractrCategory)) {
                                                faultMsg += "第" + rowNum + "行2列核减理由类别为空,";
                                                isCanSave = false;
                                            } else if (!sonsDictMap.containsKey(subtractrCategory)) { // 判断包含关系
                                                faultMsg += "第" + rowNum + "行第1列的核减专业和与第二列的核减理由类别不存在上下级关系，请检查！";
                                                isCanSave = false;
                                            }
                                            if (sonsDictMap.containsKey(subtractrCategory)) {
                                                value = StaticMethod.nullObject2String(sonsDictMap.get(subtractrCategory));
                                            }

                                            if (rowNum == 1) {
                                                subtracts.add(subtractrCategory);
                                            } else if (subtracts.contains(subtractrCategory)) {
                                                //value = StaticMethod.nullObject2String(table.getSubtractrCategory());
                                                isCanSave = true;
                                            } else {
                                                faultMsg += "第" + rowNum + "行第2列的核减理由类别与第1行不一致，请检查！";
                                                isCanSave = false;
                                            }


                                            if ("".equals(applicationReduction)) {
                                                faultMsg += "第" + rowNum + "行3列申请核减说明为空,";
                                                isCanSave = false;
                                            }

                                            if ("".equals(serialId)) {
                                                faultMsg += "第" + rowNum + "行4列工单流水号为空,";
                                                isCanSave = false;
                                            }
                                            if (sheetIds.contains(serialId)) { // 取的工单号的集合  判断包含关系
                                                faultMsg += "第" + rowNum + "行4列的工单流水号重复。";
                                                isCanSave = false; // 如果重复就直接退出，不保存
                                            }
                                            if (!"".equals(serialId)) {
                                                sheetIds.add(serialId); // 不为空，再添加工单号到sheetIds集合中
                                            }

//											 // 日期校验开始
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                            try {
                                                Date date = sdf.parse(mainDispatchTime);
                                            } catch (Exception e) {
                                                faultMsg += "第" + rowNum + "行6列 派单时间 输入的格式不正确！！";
                                                isCanSave = false;
                                                continue;
                                            }

                                            if (mainFaultFaTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainFaultFaTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行10列 故障发生时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }
                                            if (mainFaultQiTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainFaultQiTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行11列 告警平台清除时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }
                                            if (mainT2Time.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2Time);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行13列  T2接单时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (mainT2Limit.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2Limit);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行14列 T2处理时限 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (mainT2FinalTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2FinalTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行15列 T2最终处理时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (isCanSave) { // 校验完成 如果满足条件，入库
                                                //SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                                                DaiSubtractTable vulInfo = new DaiSubtractTable();


                                                vulInfo.setSubtractrCategory(value);
                                                vulInfo.setReserveB(refSheetId);
                                                vulInfo.setSubtractProfessional(profDictid);    // 进行核减专业Name转id

                                                vulInfo.setReserveE("101420202");  // 核减状态 核减中
                                                vulInfo.setReserveG("未通过");  // 增加整个工单核减状态  未通过 ；终审后改
                                                vulInfo.setApplicationReduction(applicationReduction);
                                                vulInfo.setSerialId(serialId);
                                                vulInfo.setTitle(title);


                                                vulInfo.setMainDispatchTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainDispatchTime));

                                                vulInfo.setMainFaultClass(mainFaultClass);
                                                vulInfo.setMainFaultDurat(mainFaultDurat);
                                                vulInfo.setMainProDurat(mainProDurat);
                                                if (mainFaultFaTime.length() > 0)
                                                    vulInfo.setMainFaultFaTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainFaultFaTime));
                                                if (mainFaultQiTime.length() > 0)
                                                    vulInfo.setMainFaultQiTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainFaultQiTime));

                                                vulInfo.setMainFinalMeasures(mainFinalMeasures);
                                                if (mainT2Time.length() > 0)
                                                    vulInfo.setMainT2Time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2Time));
                                                if (mainT2Limit.length() > 0)
                                                    vulInfo.setMainT2Limit((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2Limit));
                                                if (mainT2FinalTime.length() > 0)
                                                    vulInfo.setMainT2FinalTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2FinalTime));

                                                vulInfo.setMainFinalCtiy(mainFinalCtiy);
                                                vulInfo.setMainFinalTeam(mainFinalTeam);
                                                vulInfo.setMainFinalCounty(mainFinalCounty);
                                                vulInfo.setMainDaiCounty(mainDaiCounty);
                                                vulInfo.setMainFinalAttributes(mainFinalAttributes);


                                                vulInfo.setReserveA(fileName);// 当前上传附件的名称
                                                vulInfo.setReserveF("0"); //  设置0标识为上传成功但未派单  “1”标识为派单成功
                                                subMgr.saveDaiSubtractTable(vulInfo);
                                            }
                                        }
                                    }
                                }
                            } else {  // 重派页面
                                if (lastRow == Integer.parseInt(noPass)) { // 判断条数是否相同

                                    for (int rowNum = sheet.getFirstRowNum() + 1; rowNum <= lastRow; rowNum++) {
                                        XSSFRow row = sheet.getRow(rowNum);
                                        if (row == null) {
                                            break;
                                        } else {
                                            /* 核减专业 */
                                            String subtractProfessional = StaticMethod
                                                    .nullObject2String(row.getCell(0));
                                            /* 核减理由类别 */
                                            String subtractrCategory = StaticMethod
                                                    .nullObject2String(row.getCell(1));
                                            /* 申请核减说明 */
                                            String applicationReduction = StaticMethod
                                                    .nullObject2String(row.getCell(2));
                                            /*工单流水号*/
                                            String serialId = StaticMethod
                                                    .nullObject2String(row.getCell(3));
                                            /* 工单主题 */
                                            String title = StaticMethod
                                                    .nullObject2String(row.getCell(4));


                                            /* 派单时间 */
                                            String mainDispatchTime = StaticMethod
                                                    .nullObject2String(row.getCell(5));
                                            /* 故障分类 */
                                            String mainFaultClass = StaticMethod
                                                    .nullObject2String(row.getCell(6));
                                            /* 故障历时(清除时间-发生时间) */
                                            String mainFaultDurat = StaticMethod
                                                    .nullObject2String(row.getCell(7));
                                            /* 处理历时（恢复时间-第一次转单时间） */
                                            String mainProDurat = StaticMethod
                                                    .nullObject2String(row.getCell(8));
                                            /* 故障发生时间 */
                                            String mainFaultFaTime = StaticMethod
                                                    .nullObject2String(row.getCell(9));

                                            /* 告警平台清除时间 */
                                            String mainFaultQiTime = StaticMethod
                                                    .nullObject2String(row.getCell(10));
                                            /* 最终处理措施（T1，T2） */
                                            String mainFinalMeasures = StaticMethod
                                                    .nullObject2String(row.getCell(11));
                                            /* T2接单时间 */
                                            String mainT2Time = StaticMethod
                                                    .nullObject2String(row.getCell(12));
                                            /* T2处理时限 */
                                            String mainT2Limit = StaticMethod
                                                    .nullObject2String(row.getCell(13));
                                            /* T2最终处理时间 */
                                            String mainT2FinalTime = StaticMethod
                                                    .nullObject2String(row.getCell(14));
                                            /* 最终处理地市 */
                                            String mainFinalCtiy = StaticMethod
                                                    .nullObject2String(row.getCell(15));
                                            /* 最终处理班组 */
                                            String mainFinalTeam = StaticMethod
                                                    .nullObject2String(row.getCell(16));
                                            /* 最终处理区县 */
                                            String mainFinalCounty = StaticMethod
                                                    .nullObject2String(row.getCell(17));
                                            /* 综合代维区县 */
                                            String mainDaiCounty = StaticMethod
                                                    .nullObject2String(row.getCell(18));
                                            /* 最终处理班组属性 */
                                            String mainFinalAttributes = StaticMethod
                                                    .nullObject2String(row.getCell(19));


                                            // 非空
                                            if ("".equals(subtractProfessional)) {
                                                faultMsg += "第" + rowNum + "行1列核减专业为空,";
                                                isCanSave = false;
                                            } else if (!profName.equals(subtractProfessional)) {
                                                faultMsg += "第" + rowNum + "行1列与工单核减专业信息不符，请检查！";  // ok
                                                isCanSave = false;
                                            }
                                            if ("".equals(subtractrCategory)) {
                                                faultMsg += "第" + rowNum + "行2列核减理由类别为空,";
                                                isCanSave = false;
                                            } else if (!sonsDictMap.containsKey(subtractrCategory)) { // 判断包含关系
                                                faultMsg += "第" + rowNum + "行第1列的核减专业和与第二列的核减理由类别不存在上下级关系，请检查！";
                                                isCanSave = false;
                                            }
                                            if (sonsDictMap.containsKey(subtractrCategory)) {
                                                value = StaticMethod.nullObject2String(sonsDictMap.get(subtractrCategory));
                                            }
                                            if ("".equals(applicationReduction)) {
                                                faultMsg += "第" + rowNum + "行3列申请核减说明为空,";
                                                isCanSave = false;
                                            }

                                            // 日期校验开始
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            try {
                                                Date date = sdf.parse(mainDispatchTime);
                                            } catch (Exception e) {
                                                faultMsg += "第" + rowNum + "行6列 派单时间 输入的格式不正确！！";
                                                isCanSave = false;
                                                continue;
                                            }
                                            if (mainFaultFaTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainFaultFaTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行10列 故障发生时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }
                                            if (mainFaultQiTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainFaultQiTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行11列 告警平台清除时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }
                                            if (mainT2Time.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2Time);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行13列  T2接单时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (mainT2Limit.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2Limit);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行14列 T2处理时限 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (mainT2FinalTime.length() > 0) {
                                                try {
                                                    Date date = sdf.parse(mainT2FinalTime);
                                                } catch (Exception e) {
                                                    faultMsg += "第" + rowNum + "行15列 T2最终处理时间 输入的格式不正确！！";
                                                    isCanSave = false;
                                                    continue;
                                                }
                                            }

                                            if (isCanSave) { // 校验完成 如果满足条件，入库
                                                List tableList = linkMgr.getLinksBycondition(" serialId='" + serialId + "' and reserveC='1030102' and reserveB='" + refSheetId + "' ", "DaiSubtractTable");
                                                //SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                                                DaiSubtractTable vulInfo = null;
                                                if (tableList != null && tableList.size() > 0) {
                                                    vulInfo = (DaiSubtractTable) tableList.get(0);
                                                    //vulInfo.setReserveB(refSheetId);
                                                    //vulInfo.setSubtractProfessional(profDictid);	// 进行核减专业Name转id

                                                    vulInfo.setSubtractrCategory(value);    /*核减理由*/
                                                    vulInfo.setApplicationReduction(applicationReduction);    /*核减说明*/
                                                    //vulInfo.setSerialId(serialId);
                                                    //vulInfo.setFirstRejection("");
                                                    //vulInfo.setTitle(title);
                                                    //vulInfo.setReserveC("");/*初审环节填的否 置空*/


                                                    vulInfo.setMainDispatchTime(SheetUtils.stringToDate(mainDispatchTime));

                                                    vulInfo.setMainFaultClass(mainFaultClass);
                                                    vulInfo.setMainFaultDurat(mainFaultDurat);
                                                    vulInfo.setMainProDurat(mainProDurat);

                                                    vulInfo.setMainDispatchTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainDispatchTime));


                                                    vulInfo.setMainFaultClass(mainFaultClass);
                                                    vulInfo.setMainFaultDurat(mainFaultDurat);
                                                    vulInfo.setMainProDurat(mainProDurat);

                                                    vulInfo.setMainFaultFaTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainFaultFaTime));

                                                    vulInfo.setMainFaultQiTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainFaultQiTime));

                                                    vulInfo.setMainFinalMeasures(mainFinalMeasures);
                                                    if (mainT2Time.length() > 0)
                                                        vulInfo.setMainT2Time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2Time));
                                                    if (mainT2Limit.length() > 0)
                                                        vulInfo.setMainT2Limit((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2Limit));
                                                    if (mainT2FinalTime.length() > 0)
                                                        vulInfo.setMainT2FinalTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mainT2FinalTime));

                                                    vulInfo.setMainFinalCtiy(mainFinalCtiy);
                                                    vulInfo.setMainFinalTeam(mainFinalTeam);
                                                    vulInfo.setMainFinalCounty(mainFinalCounty);
                                                    vulInfo.setMainDaiCounty(mainDaiCounty);
                                                    vulInfo.setMainFinalAttributes(mainFinalAttributes);

                                                    vulInfo.setReserveA(fileName);// 当前上传附件的名称
                                                    subMgr.saveDaiSubtractTable(vulInfo);
                                                } else {
                                                    faultMsg += "导入的工单流水号不符!";
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    faultMsg += "导入与导出数目不一致";
                                }

                            }
                        }
                    }
                }
                if (!"".equals(faultMsg)) {
                    faultMsg = (String) faultMsg.subSequence(0, faultMsg
                            .length() - 1);
                    json.put("result", "导入数据有误：" + faultMsg);
                    json.put("excel1", "");// 导入失败
                } else {
                    json.put("excel", accessories);
                    json.put("resMsg", "ok");
                    json.put("refSheetId", refSheetId);
                }
                response.setContentType("text/html;charset=UTF-8");// 返回结果输出方式
                response.getWriter().print(json.toString());
            }
        } catch (Exception e) {
            System.out.println("导入的文件异常，请检查！+DaiSubtractTableUploadAction");
            e.printStackTrace();
        }
        return null;
    }

    // 生成随机数，防止循环调用附件下载接口时出现系统文件名重复的问题，传进来的sLen是几位就生成几位的随机数
    public String randomKey(int sLen) {
        String base;
        String temp;
        int i;
        int p;

        base = "1234567890";
        temp = "";
        for (i = 0; i < sLen; i++) {
            p = (int) (Math.random() * 10);
            temp += base.substring(p, p + 1);
        }
        return (temp);
    }

}