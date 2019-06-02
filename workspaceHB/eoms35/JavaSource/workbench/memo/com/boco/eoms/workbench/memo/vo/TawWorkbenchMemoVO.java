package com.boco.eoms.workbench.memo.vo;

public class TawWorkbenchMemoVO {

    protected String id;

    protected String deleted;

    protected String title;

    protected String content;

    protected String userid;

    protected String creattime;

    protected String level;

    protected String sendflag;

    protected String sendtime;

    protected String reciever;
	
    protected String sendmanner;
    
    protected String levelName;
    protected String sendMannerName;
    protected String sendflagName;
    public void setSendMannerName(String sendManner){
    	
    	if(sendManner.equals("1")){
    		this.sendMannerName="短信";
    	}
    	if(sendManner.equals("2")){
    		this.sendMannerName="e_mail";
    	}
    	if(sendManner.equals("3")){
    		this.sendMannerName="系统";
    	}
    	 
    }
    public void setSendflagName(String sendflag){
    	if(sendflag.equals("0")){
    		this.sendflagName="否";
    	}else if(sendflag.equals("1")){
    		this.sendflagName="是";
    	}   
    }
    
    public void setLevelName(String level){
    	 
    	if(level.equals("1")){
    		this.levelName="重要紧急";
    	}
    	if(level.equals("2")){
    		this.levelName="重要不紧急";
    	}
    	if(level.equals("3")){
    		this.levelName="紧急不重要";
    	}
    	if(level.equals("4")){
    		this.levelName="不重要不紧急";
    	}
    	
    
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getSendflag() {
		return sendflag;
	}

	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}

	public String getSendmanner() {
		return sendmanner;
	}

	public void setSendmanner(String sendmanner) {
		this.sendmanner = sendmanner;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
