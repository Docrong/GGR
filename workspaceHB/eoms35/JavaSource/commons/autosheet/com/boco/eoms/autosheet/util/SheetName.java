package com.boco.eoms.autosheet.util;

import java.util.*;

import com.boco.eoms.db.util.*;
import com.boco.eoms.common.util.*;

/*****************************************************************
 将sheetname中所有的字段
 从表单表taw_sheetname中取出来放入hashtable,
 并通过getSheetKeyID让其驻留在内存里，并给出取数据的方法。
 *****************************************************************/

public class SheetName {
    /*************************************************
     定义ArrayList变量,用于存储taw_sheetname表中各个字段
     */
    private ArrayList sheet_ids = null, module_ids = null, sh_cname = null, style = null,
            isattach = null, isattachment = null, columnWidth = null, columnHeight = null,
            width = null, height = null, para1 = null;

    private RecordSet rt = null;
    // private ConnStatement cst = null;
    private ConnStatement cstmt = new ConnStatement();
    private StaticObject staticobj = null;

    private int current_index = -1;
    private int array_size = 0;

    /*************************************************
     构造方法
     */
    private SheetName() {
        try {
            staticobj = StaticObject.getInstance();
            current_index = -1;
            getSheetName();
            array_size = sheet_ids.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***构造方法***************************************
     *@params s test commonclass
     * ******/
    public SheetName(String s) {
        try {
            staticobj = StaticObject.getInstance();
            current_index = -1;
            getSheetName();
            array_size = sheet_ids.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SheetName instance = new SheetName();

    /*************************************************
     静态方法,返回SheetName类的唯一实例,
     @return SheetName
     */
    public static SheetName getInstance() {
        return instance;
    }

    public static void removeInstance() {
        instance = new SheetName();
    }

    /*************************************************
     重置SheetName对象,将当前索引重置
     @return boolean
     */
    public boolean reset() {
        this.current_index = -1;
        return true;
    }

    /*********************************************
     初始化SheetName的各种属性
     @throws Exception
     */
    private void getSheetName() throws Exception {
        if (staticobj.getObject("SheetName") == null)
            setSheetName();
        sheet_ids = (ArrayList) (staticobj.getRecordFromObj("SheetName", "sheet_ids"));
        module_ids = (ArrayList) (staticobj.getRecordFromObj("SheetName", "module_ids"));
        sh_cname = (ArrayList) (staticobj.getRecordFromObj("SheetName", "sh_cname"));
        style = (ArrayList) (staticobj.getRecordFromObj("SheetName", "style"));
        isattach = (ArrayList) (staticobj.getRecordFromObj("SheetName", "isattach"));
        isattachment = (ArrayList) (staticobj.getRecordFromObj("SheetName", "isattachment"));
        columnWidth = (ArrayList) (staticobj.getRecordFromObj("SheetName", "columnWidth"));
        columnHeight = (ArrayList) (staticobj.getRecordFromObj("SheetName", "columnHeight"));
        width = (ArrayList) (staticobj.getRecordFromObj("SheetName", "width"));
        height = (ArrayList) (staticobj.getRecordFromObj("SheetName", "height"));
        para1 = (ArrayList) (staticobj.getRecordFromObj("SheetName", "para1"));
    }

    /********************************************
     将SheetName的原有值清空,
     将SheetName的各种属性从数据库中取出到一个statcicObject对象,并放入内存中
     @throws Exception
     */
    public void setSheetName() throws Exception {
        if (sheet_ids != null)
            sheet_ids.clear();
        else
            sheet_ids = new ArrayList();
        if (module_ids != null)
            module_ids.clear();
        else
            module_ids = new ArrayList();
        if (sh_cname != null)
            sh_cname.clear();
        else
            sh_cname = new ArrayList();
        if (style != null)
            style.clear();
        else
            style = new ArrayList();
        if (isattach != null)
            isattach.clear();
        else
            isattach = new ArrayList();
        if (isattachment != null)
            isattachment.clear();
        else
            isattachment = new ArrayList();
        if (columnWidth != null)
            columnWidth.clear();
        else
            columnWidth = new ArrayList();
        if (columnHeight != null)
            columnHeight.clear();
        else
            columnHeight = new ArrayList();
        if (width != null)
            width.clear();
        else
            width = new ArrayList();
        if (height != null)
            height.clear();
        else
            height = new ArrayList();
        if (para1 != null)
            height.clear();
        else
            para1 = new ArrayList();
        String sql = "select sheet_id,module_id,sh_cname,style,isattach,isattachment,columnWidth,columnHeight" +
                ",width,height,para1 from taw_sheetname";
        int count;
        Hashtable ht;
        rt = new RecordSet();
        try {
            rt.execute(sql);
            count = rt.getCounts();
            while (rt.next()) {
                sheet_ids.add(StaticMethod.null2String(rt.getString(1)));
                module_ids.add(StaticMethod.null2String(rt.getString(2)));
                sh_cname.add(StaticMethod.null2String(rt.getString(3)));
                style.add(StaticMethod.null2String(rt.getString(4)));
                isattach.add(StaticMethod.null2String(rt.getString(5)));
                isattachment.add(StaticMethod.null2String(rt.getString(6)));
                columnWidth.add(StaticMethod.null2String(rt.getString(7)));
                columnHeight.add(StaticMethod.null2String(rt.getString(8)));
                width.add(StaticMethod.null2String(rt.getString(9)));
                height.add(StaticMethod.null2String(rt.getString(10)));
                para1.add(StaticMethod.null2String(rt.getString(11)));
            }
        } catch (Exception e) {
            rt = null;
            e.printStackTrace();
        }
        rt = null;
        staticobj.putRecordToObj("SheetName", "sheet_ids", sheet_ids);
        staticobj.putRecordToObj("SheetName", "module_ids", module_ids);
        staticobj.putRecordToObj("SheetName", "sh_cname", sh_cname);
        staticobj.putRecordToObj("SheetName", "style", style);
        staticobj.putRecordToObj("SheetName", "isattach", isattach);
        staticobj.putRecordToObj("SheetName", "isattachment", isattachment);
        staticobj.putRecordToObj("SheetName", "columnWidth", columnWidth);
        staticobj.putRecordToObj("SheetName", "columnHeight", columnHeight);
        staticobj.putRecordToObj("SheetName", "width", width);
        staticobj.putRecordToObj("SheetName", "height", height);
        staticobj.putRecordToObj("SheetName", "para1", para1);
    }

    /*************************************************
     得到SheetName的记录条数
     @return int
     */
    public int getSheetNameCounts() {
        return array_size;
    }

    /*************************************************
     将SheetName的记录向下移动一行
     @return boolean
     */
    public boolean next() {

        if ((current_index + 1) < array_size) {
            current_index++;
            return true;
        } else {
            current_index = -1;
            return false;
        }
    }

    /*************************************************
     根据传入的module_id参数@shEname得到SheetName的属性(sheet_id)
     @param  shEName 表单英语名称
     @return String
     */
    public String getSheetID(String moduleID) {

        current_index = module_ids.indexOf(moduleID);
        if (current_index == -1)
            return "";
        String id = "";
        try {
            id = (String) sheet_ids.get(current_index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(module_id)
     @param id 表单id
     @return String
     */
    public String getmodule_id(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) module_ids.get(current_index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(sh_cname)
     @param id 表单id
     @return String
     */
    public String getSh_cname(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) sh_cname.get(current_index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(style)
     @param id 部门id
     @return String
     */
    public String getStyle(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) style.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(isattach)
     @param id 部门id
     @return String
     */
    public String getIsattach(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) isattach.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(isattach)
     @param id 部门id
     @return String
     */
    public String getIsattachment(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) isattachment.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(columnWidth)
     @param id 部门id
     @return String
     */
    public String getColumnWidth(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) columnWidth.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(columnHeight)
     @param id 部门id
     @return String
     */
    public String getColumnHeight(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) columnHeight.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(width)
     @param id 部门id
     @return String
     */
    public String getWidth(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) width.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(height)
     @param id 部门id
     @return String
     */
    public String getHeight(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) height.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     根据指定的sheet_id得到SheetName的属性(para1)
     @param id 部门id
     @return String
     */
    public String getPara1(String id) {

        current_index = sheet_ids.indexOf(id);
        if (current_index == -1)
            return "";
        String name = "";
        try {
            name = (String) para1.get(current_index);
        } catch (Exception e) {
            LogMan log = LogMan.getInstance();
            log.writeLog(e.toString());
        }
        return name;
    }

    /*************************************************
     返回当前索引的SheetName属性(sheet_id)
     @return String
     */
    public String getSheetID() {
        if (current_index == -1)
            return "";
        return (String) (sheet_ids.get(current_index));
    }

    /*************************************************
     返回当前索引的SheetName属性(module_id)
     @return String
     */
    public String getmodule_id() {
        if (current_index == -1)
            return "";
        return (String) module_ids.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(sh_cname)
     @return String
     @throws Exception
     */
    public String getSh_cname() throws Exception {
        if (current_index == -1)
            return "";
        return (String) sh_cname.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(style)
     @return String
     */
    public String getStyle() {
        if (current_index == -1)
            return "";
        return (String) style.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(isattach)
     @return String
     */
    public String getIsattach() {
        if (current_index == -1)
            return "";
        return (String) isattach.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(isattachment)
     @return String
     */
    public String getIsattachment() {
        if (current_index == -1)
            return "";
        return (String) isattachment.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(columnWidth)
     @return String
     */
    public String getColumnWidth() {
        if (current_index == -1)
            return "";
        return (String) columnWidth.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(columnHeight)
     @return String
     */
    public String getColumnHeight() {
        if (current_index == -1)
            return "";
        return (String) columnHeight.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(width)
     @return String
     */
    public String getWidth() {
        if (current_index == -1)
            return "";
        return (String) width.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(height)
     @return String
     */
    public String getHeight() {
        if (current_index == -1)
            return "";
        return (String) height.get(current_index);
    }

    /*************************************************
     返回当前索引的SheetName属性(para1)
     @return String
     */
    public String getPara1() {
        if (current_index == -1)
            return "";
        return (String) para1.get(current_index);
    }

    /*************************************************
     将SheetName对象从StaticObject对象中删除
     */
    public void removeOSCache() {
        staticobj.removeObject("SheetName");
    }

}