package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawRmLoanRecordForm extends BaseForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 909434438599369783L;
	private String id;
	private String articleName;
	private String piece;
	private String borrowerName;
	private String loanTime;
	private String returnTime;
	private String roomId;
	private String userId;
	private String workSerial;
	private String createTime;
	private String startTime;
	private String endTime;
	private String tmpArticleName;
	private String tmpPiece;
	private String tmpBorrowerName;
	private String tmpLoanTime;
	private String tmpReturnTime;
	private String articleType;
	private String articleId;
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}
	
	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
	public String getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}
	
	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getWorkSerial() {
		return workSerial;
	}

	public void setWorkSerial(String workSerial) {
		this.workSerial = workSerial;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getTmpArticleName() {
		return tmpArticleName;
	}

	public void setTmpArticleName(String tmpArticleName) {
		this.tmpArticleName = tmpArticleName;
	}

	public String getTmpPiece() {
		return tmpPiece;
	}

	public void setTmpPiece(String tmpPiece) {
		this.tmpPiece = tmpPiece;
	}
	
	public String getTmpBorrowerName() {
		return tmpBorrowerName;
	}

	public void setTmpBorrowerName(String tmpBorrowerName) {
		this.tmpBorrowerName = tmpBorrowerName;
	}
	
	public String getTmpLoanTime() {
		return tmpLoanTime;
	}

	public void setTmpLoanTime(String tmpLoanTime) {
		this.tmpLoanTime = tmpLoanTime;
	}
	
	public String getTmpReturnTime() {
		return tmpReturnTime;
	}

	public void setTmpReturnTime(String tmpReturnTime) {
		this.tmpReturnTime = tmpReturnTime;
	}
	
	/* To add non XDoclet-generated methods, create a file named
    xdoclet-TawWorkbenchMemoForm.java 
    containing the additional code and place it in your metadata/web directory.
	 */
	/**
	* @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	*                                                javax.servlet.http.HttpServletRequest)
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	 // reset any boolean data types to false
	
	}
}
