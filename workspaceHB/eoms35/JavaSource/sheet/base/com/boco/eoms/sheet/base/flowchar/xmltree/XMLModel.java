package com.boco.eoms.sheet.base.flowchar.xmltree;

import java.util.ArrayList;
import java.util.List;

public class XMLModel {
	private String id = "";

	private String code = "";

	private String name = "";

	private String status = "";

	private String operate = "";

	private String currentLink;

	private String parentId;

	private String parentLink;
	
	private String dispalyMsg;
	
	private String replyNumber = "";

	private int subSize = 0;
	
	private String mainId = "";
	
	

	private List subModel = new ArrayList();
	
	

	public List getSubModel() {
		return subModel;
	}

	public void setSubModel(List subModel) {
		this.subModel = subModel;
	}

	public String getXML() {
		String xml = null;

		if(subSize > 0){
			xml = "<code" + code 
			                 + " id='" + id + "'" 
                           + " name='" + name + "'"
                         + " status='" + status + "'"
                        + " operate='" + operate + "'"
                    + " currentLink='" + currentLink + "'"
                       + " parentId='" + parentId + "'"
                     + " parentLink='" + parentLink + "'"
                     + " dispalyMsg='" + dispalyMsg + "'"
                     + " mainId='" + mainId + "'"
                     + " replyNumber='" + replyNumber + "'"
         		+ " >";

			for(int i=0; i<subSize; i++){
				XMLModel subnode = (XMLModel) subModel.get(i);
				xml = xml + subnode.getXML();
			}

			xml = xml + "</code" + code + ">";
		}
		else{
			xml = "<code" + code 
			                 + " id='" + id + "'" 
			               + " name='" + name + "'"
			             + " status='" + status + "'"
			            + " operate='" + operate + "'"
			        + " currentLink='" + currentLink + "'"
			           + " parentId='" + parentId + "'"
			         + " parentLink='" + parentLink + "'"
			         + " dispalyMsg='" + dispalyMsg + "'"
			         + " mainId='" + mainId + "'"
			         + " replyNumber='" + replyNumber + "'"
			    + "/>";	
		}

		return xml;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubSize() {
		return subSize;
	}

	public void setSubSize(int subSize) {
		this.subSize = subSize;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentLink() {
		return parentLink;
	}

	public void setParentLink(String parentLink) {
		this.parentLink = parentLink;
	}

	public String getCurrentLink() {
		return currentLink;
	}

	public void setCurrentLink(String currentLink) {
		this.currentLink = currentLink;
	}

	public void addSub(XMLModel xmlModel) {
		subModel.add(xmlModel);
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getDispalyMsg() {
		return dispalyMsg;
	}

	public void setDispalyMsg(String dispalyMsg) {
		this.dispalyMsg = dispalyMsg;
	}
	
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(String replyNumber) {
		this.replyNumber = replyNumber;
	}


}
