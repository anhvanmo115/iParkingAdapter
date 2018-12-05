package com.viettel.iParkingAdapter.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * Created by luyenct on 3/20/2018.
 */
public class ByteUtils {
    public static String toPrintableDecimalString(byte[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.toString();
            b.append(" ");
        }
    }
    public static String toPrintableHexString(byte... a){
        if (a == null)
            return "";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(byteToHex(a[i]));
            if (i == iMax)
                return b.toString();
            b.append(" ");
        }
    }

    //order byte array with BIG ENDIAN or LITTLE ENDIAN order and convert into hex
    public static String bytesToHex(byte[] bytes, ByteOrder byteOrder){
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(byteOrder);
        String hexstr = "";
        while( bb.hasRemaining()) {
            hexstr =  byteToHex(bb.get()) + hexstr;
        }
        return hexstr;
    }

    public static String bytesToHex(byte... a){
        if (a == null)
            return "";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(byteToHex(a[i]));
            if (i == iMax)
                return b.toString();
            b.append("");
        }
    }
    public static String byteToHex(byte value){
        int i = value & 0xFF;
        String x = Integer.toHexString(i);
        if(x.length() == 1) return "0"+x;
        else return x;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

//    public static byte[] orderByteArray(byte[] bytes, ByteOrder byteOrder){
//        return  ByteBuffer.wrap(bytes).order(byteOrder).array();
//    }
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
    public static String hexToString(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            Long num = Long.parseLong(str);
            num = num*16/10 + num%10;
            output.append(num);
        }

        return output.toString();
    }


    public static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }

    public static Long byteToDecimal(byte ... bytes){
        return new BigInteger(bytes).longValue();
    }

    public static void main(String[] args) {
        String hex = "01";
        byte[] bytes = hexStringToByteArray(hex);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        char v=0;
        String hexstr = "";
        while( bb.hasRemaining()) {
//            v = bb.get();
            /* Do something with v... */
            hexstr =  byteToHex(bb.get()) + hexstr;
//            System.out.println(Integer.toHexString(v & 0xffff));
        }
        System.out.println(hexstr);



    }
}
