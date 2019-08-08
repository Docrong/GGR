package com.boco.eoms.sheet.mofficedata.job;

public class MofficeDataJobStatic {

    private static boolean ifRunningNow = true;

    public static boolean isIfRunningNow() {
        return ifRunningNow;
    }

    public static void setIfRunningNow(boolean ifRunningNow) {
        MofficeDataJobStatic.ifRunningNow = ifRunningNow;
    }
}
