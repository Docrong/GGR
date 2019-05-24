package com.boco.eoms.sheet.base.util.flowdefine.xml;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FlowDefine {
	
	public String tasknamespace;
	
	public String getTasknamespace() {
		return tasknamespace;
	}
	
	public void setTasknamespace(String tasknamespace) {
		this.tasknamespace = tasknamespace;
	}
	
	
    public PhaseId phaseId[];
    
	/**
	 * @return Returns the phaseId.
	 */
	public PhaseId[] getPhaseId() {
		return phaseId;
	}
	/**
	 * @param phaseId The phaseId to set.
	 */
	public void setPhaseId(PhaseId[] phaseId) {
		this.phaseId = phaseId;
	}
	
	public String id;
	public String description;
	
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public PhaseId getPhasesByPhaseId(String phaseId)throws Exception{
		PhaseId phase = new PhaseId();
		if(this.getPhaseId()!=null && this.getPhaseId().length>0){
			boolean isReplay = false;
			for(int i=0;i<this.getPhaseId().length;i++){
				if(this.getPhaseId()[i].getId().equals(phaseId)){					
					if(isReplay==true){
						throw new Exception("当前流程:"+this.getDescription()+"定义中"+phaseId+"这个节点出现重复定义");
					}
					phase = this.getPhaseId()[i];
					isReplay = true;
				}
			}
		}else{
			throw new Exception("当前流程:"+this.getDescription()+"中没有定义节点");
		}
		return phase;		
	}
	
	/**
	 * 用查看流程图时需要的参数
	 */
	private String linkservicename;
	
	/**
	 * 用于查看流程图时需要的参数
	 */
	private String dictfilename;

	public String getDictfilename() {
		return dictfilename;
	}

	public void setDictfilename(String dictfilename) {
		this.dictfilename = dictfilename;
	}

	public String getLinkservicename() {
		return linkservicename;
	}

	public void setLinkservicename(String linkservicename) {
		this.linkservicename = linkservicename;
	}


	
	
	
}
