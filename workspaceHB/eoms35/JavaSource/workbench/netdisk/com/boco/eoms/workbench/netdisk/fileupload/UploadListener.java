package com.boco.eoms.workbench.netdisk.fileupload;

public class UploadListener implements OutputStreamListener {
	// ����״̬���ڲ������
	private FileUploadStats fileUploadStats = new FileUploadStats();
	// ���췽��
	public UploadListener(long totalSize) {
		fileUploadStats.setTotalSize(totalSize);
	}
	public void start() {
		// ���õ�ǰ״̬Ϊ��ʼ
		fileUploadStats.setCurrentStatus("start");
	}
	public void bytesRead(int byteCount) {
		// ���Ѷ�ȡ����ݱ��浽״̬������
		fileUploadStats.incrementBytesRead(byteCount);
		// ���õ�ǰ��״̬Ϊ��ȡ�����
		fileUploadStats.setCurrentStatus("reading");
	}
	public void error(String s) {
		// ���õ�ǰ״̬Ϊ���
		fileUploadStats.setCurrentStatus("error");
	}
	public void done() {
		// ���õ�ǰ�Ѷ�ȡ��ݵ�������ݴ�С
		fileUploadStats.setBytesRead(fileUploadStats.getTotalSize());
		// ���õ�ǰ״̬Ϊ���
		fileUploadStats.setCurrentStatus("done");
	}
	public FileUploadStats getFileUploadStats() {
		// ���ص�ǰ״̬����
		return fileUploadStats;
	}
	// ����״̬��
	public static class FileUploadStats {
		private long totalSize = 0;// ����ݵĴ�С
		private long bytesRead = 0;// �Ѷ���ݴ�С
		private long startTime = System.currentTimeMillis();// ��ʼ��ȡ��ʱ��
		private String currentStatus = "none";// Ĭ�ϵ�״̬
		public long getTotalSize()// ����totalSize��get����
		{
			return totalSize-100;
		}
		public void setTotalSize(long totalSize) {
			this.totalSize = totalSize;
		}
		public long getBytesRead()// ����bytesRead��get����
		{
			return bytesRead;
		}
		public long getElapsedTimeInSeconds()// ����Ѿ��ϴ���ʱ��
		{
			return (System.currentTimeMillis() - startTime) / 1000;
		}
		public String getCurrentStatus()// ����currentStatus��get����
		{
			return currentStatus;
		}
		public void setCurrentStatus(String currentStatus) {
			this.currentStatus = currentStatus;
		}
		public void setBytesRead(long bytesRead) {
			this.bytesRead = bytesRead;
		}
		public void incrementBytesRead(int byteCount) {
			this.bytesRead += byteCount;
		}
	}
}
