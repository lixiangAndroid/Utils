package com.lx.utils;

/**
 * @author: lixiang
 * @date: 2018/06/14 13:55
 * @description： 字节数据与十六进制字符串的转换
 */
public class ByteUtli {

    public static byte[] hexStrToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * bytesTo16String
     *
     * @param src byte[]
     * @return String
     */
    public static String bytesToHexStr(byte[] src) {
        if (src == null || src.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String s = Integer.toHexString(v);
            if (s.length() < 2) {
                sb.append("0");
            }
            sb.append(s);
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytesTo16String
     *
     * @param src byte[]
     * @param num int
     * @return String
     */
    public static String bytesToHexStr(byte[] src, int num) {
        if (src == null || src.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int v = src[i] & 0xFF;
            String s = Integer.toHexString(v);
            if (s.length() < 2) {
                sb.append("0");
            }
            sb.append(s);
        }
        return sb.toString().toUpperCase().trim();
    }
}
