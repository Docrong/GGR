package com.ggr.leecode.string;

/**
 * 判断是否是回文
 *
 * @author gr
 */
public class 回文 {

    public static void main(String[] args) {
        System.out.println(istrue(12321));
        ;
    }

    static boolean istrue(int x) {
        String str = String.valueOf(x);

        StringBuffer sb = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        if (sb.toString().equals(str))
            return true;
        return false;
    }
}
