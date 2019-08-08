package com.boco.eoms.workplan.util;

import java.util.*;

import com.boco.eoms.db.util.*;
import com.boco.eoms.common.util.*;

public class CodeContentComsInfo {

    private ArrayList codenames = null;
    private ArrayList recordids = null;
    LogMan log = LogMan.getInstance();
    private RecordSet rt = null;
    private StaticObject staticobj = null;

    private int current_index = -1;
    private int array_size = 0;

    public CodeContentComsInfo(String typeid) {
        try {
            staticobj = StaticObject.getInstance();
            current_index = -1;
            getCodeContentInfo(typeid);
            array_size = recordids.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean reset() {
        this.current_index = -1;
        return true;
    }

    private void getCodeContentInfo(String typeid) throws Exception {
        if (staticobj.getObject("CodeContentInfo" + typeid) == null) {
            setCodeContentInfo(typeid);
        }
        codenames = (ArrayList) (staticobj.getRecordFromObj("CodeContentInfo" +
                typeid, "codenames"));
        recordids = (ArrayList) (staticobj.getRecordFromObj("CodeContentInfo" +
                typeid, "recordids"));

    }

    private void setCodeContentInfo(String typeid) throws Exception {
        if (codenames != null) {
            codenames.clear();
        } else {
            codenames = new ArrayList();
        }
        if (recordids != null) {
            recordids.clear();
        } else {
            recordids = new ArrayList();

        }
        rt = new RecordSet();
        try {
            rt.execute("select distinct dict_id,dict_name,dict_type from taw_ws_dict where deleted=0 and dict_type=" +
                    typeid);
            while (rt.next()) {
                recordids.add(StaticMethod.null2String(rt.getString(1)));
                codenames.add(StaticMethod.null2String(rt.getString(2)));

            }
        } catch (Exception e) {
            // writeLog(e) ;
            throw e;
        }
        staticobj.putRecordToObj("CodeContentInfo" + typeid, "codenames", codenames);
        staticobj.putRecordToObj("CodeContentInfo" + typeid, "recordids", recordids);

    }

    public int getCounts() {
        return array_size;
    }

    public boolean next() {

        if ((current_index + 1) < array_size) {
            current_index++;
            return true;
        } else {
            current_index = -1;
            return false;
        }
    }

    /**************************************
     根据参数定位第n条记录,参数为正指从前往后数,参数为负,表示从后往前数,
     @param index 将记录定位到记录的第index行
     @return boolean
     */
    public boolean absolute(int index) {
        int line = index;
        if (line > 0 && line >= this.getCounts()) {
            line = this.getCounts();
        }
        if (line < 0 && (-line) > this.getCounts()) {
            line = 1;
        } else if (line < 0) {
            line = this.getCounts() + line + 1;
        }
        current_index = line - 1;
        return true;
    }

    public String getRecordID(String name) {
        current_index = codenames.indexOf(name);
        String id = "";
        try {
            id = (String) recordids.get(current_index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    public String getRecordID() {
        return (String) (recordids.get(current_index));
    }

    /**********
     返回
     @return String
     */
    public String getCodeName() {
        //informix
        String myName = (String) codenames.get(current_index);
        return (String) (myName);
    }

    public String getCodeName(String id) {
        current_index = recordids.indexOf(id);
        String name = "";
        try {
            name = (String) codenames.get(current_index);
        } catch (Exception e) {
            log.writeLog(e.toString());
        }
        return name;
    }

    public void removeOSCache() {
        staticobj.removeObject("CodeContentInfo");

    }
}
