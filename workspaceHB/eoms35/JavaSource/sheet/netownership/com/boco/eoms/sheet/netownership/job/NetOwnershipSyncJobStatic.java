package com.boco.eoms.sheet.netownership.job;

public class NetOwnershipSyncJobStatic {
	private static boolean ifExcuteJob =true;

	public static boolean isIfExcuteJob() {
		return ifExcuteJob;
	}

	public static void setIfExcuteJob(boolean ifExcuteJob) {
		NetOwnershipSyncJobStatic.ifExcuteJob = ifExcuteJob;
	}
}
