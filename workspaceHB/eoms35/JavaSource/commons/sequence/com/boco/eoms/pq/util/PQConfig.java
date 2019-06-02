package com.boco.eoms.pq.util;

public class PQConfig {

	private String maxRetainPQFailCount;

	private Integer pageSize;

	public String getMaxRetainPQFailCount() {
		return maxRetainPQFailCount;
	}

	public void setMaxRetainPQFailCount(String maxRetainPQFailCount) {
		this.maxRetainPQFailCount = maxRetainPQFailCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
