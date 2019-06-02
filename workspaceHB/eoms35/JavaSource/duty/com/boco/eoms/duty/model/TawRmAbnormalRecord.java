package com.boco.eoms.duty.model;

import java.sql.Timestamp;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.dao.TawRmAbnormalRecordDAO;
import com.boco.eoms.duty.model.*;

public class TawRmAbnormalRecord{
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
      ConnectionPool.getInstance();

    public TawRmAbnormalRecord() {
    }
    
    /** 异常值班记录编号 */
    private int id;
    
    /** 类别 */
    private int abnormalType;
    
    /** 开始时间 */
    private String starttime;
    
    /** 结束时间 */
    private String endtime;

    /** 相应的值班记录编号 */
    private String relId;
    
    /** 相应的值班记录附加表编号 */
    private String relAddId;
    
    /** 机房编号编号 */
    private int roomId;
    
    private String roomName;

		private String urldata;
		
	public String getUrldata() {
        return this.urldata;
    }

    public void setUrldata(String urldata) {
        this.urldata = urldata;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbnormalType() {
        return this.abnormalType;
    }

    public void setAbnormalType(int abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRelId() {
        return this.relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }
    
    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
  
    public String getRoomName() {
    	  int id=getRoomId();
    	  TawRmAbnormalRecordDAO dao=new TawRmAbnormalRecordDAO(ds);
    	  this.roomName=dao.getApparatusroomName(id);
    	  dao=null;
        return this.roomName;
    }

	public String getRelAddId() {
		return relAddId;
	}

	public void setRelAddId(String relAddId) {
		this.relAddId = relAddId;
	}
}