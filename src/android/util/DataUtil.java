package com.zkmeiling.serialport.util;

public class DataUtil {

    public static byte intData2high(int data) {
        return (byte) (data >> 8);
    }

    public static byte intData2low(int data) {
        return (byte) (data & 0x00ff);
    }

    public static int byte2intData(byte high , byte low){
       short r = (short) (((high& 0xff) << 8) + (low& 0xff));
        return  (int)r;
    }

    public static double byte2doubleData(byte high , byte low){
        short r = (short) (((high& 0xff) << 8) + (low& 0xff));
//        return  (double) (r/10);
        return  (((double)r)/10);
    }

    public static byte doubleData2high(double data) {
        int tempData = (int)(data*10);
        return (byte) (tempData >> 8);
    }

    public static byte doubleData2low(double data) {
        int tempData = (int)(data*10);
        return (byte) (tempData & 0x00ff);
    }
}
