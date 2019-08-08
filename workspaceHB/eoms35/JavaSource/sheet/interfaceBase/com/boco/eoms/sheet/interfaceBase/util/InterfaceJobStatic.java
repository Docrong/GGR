package com.boco.eoms.sheet.interfaceBase.util;


public class InterfaceJobStatic {
    public static boolean ifExcuteJob = true;

    public synchronized static boolean isIfExcuteJob() {
        return ifExcuteJob;
    }

    public synchronized static void setIfExcuteJob(boolean ifExcuteJob) {
        InterfaceJobStatic.ifExcuteJob = ifExcuteJob;
    }


}
