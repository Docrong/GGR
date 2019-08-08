package com.boco.eoms.sheet.netownershipwireless.job;

public class NetOwnershipwirelessSyncJobStatic {
    private static boolean ifExcuteJob = true;

    public static boolean isIfExcuteJob() {
        return ifExcuteJob;
    }

    public static void setIfExcuteJob(boolean ifExcuteJob) {
        NetOwnershipwirelessSyncJobStatic.ifExcuteJob = ifExcuteJob;
    }
}
