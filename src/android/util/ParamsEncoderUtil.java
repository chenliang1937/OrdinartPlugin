package com.zkmeiling.serialport.util;

import com.zkmeiling.serialport.model.ReadParams;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * 发送参数
 */
public class ParamsEncoderUtil {

    public static byte[] doEncoder(ReadParams params) throws IOException, ParseException {
        byte[] bytes = ParamsEncoderUtil.getBytes(params);
        return bytes;

    }

    /**
     * 参数设置
     *
     * @param params
     * @return
     * @throws ParseException
     */
    public static byte[] getBytes(ReadParams params) throws ParseException {
        byte[] bytes = new byte[113];
        bytes[0] = (byte)0xA5;
        bytes[1] = 0x03;
        bytes[2] = 0x6C;
        /*温度设定*/
        bytes[3] = DataUtil.doubleData2low(params.getTemperatureSetting());
        bytes[4] = DataUtil.doubleData2high(params.getTemperatureSetting());
        /*高温报警偏差*/
        bytes[5] = DataUtil.doubleData2low(params.getHighTemperatureWarnDeviation());
        bytes[6] = DataUtil.doubleData2high(params.getHighTemperatureWarnDeviation());
        /*低温报警偏差*/
        bytes[7] = DataUtil.doubleData2low(params.getLowTemperatureWarnDeviation());
        bytes[8] = DataUtil.doubleData2high(params.getLowTemperatureWarnDeviation());
        /*查看当前日期-年 -无意义*/
        bytes[9] = 0;
        bytes[10] = 0;
        /*查看当前日期 月 -无意义*/
        bytes[11] = 0;
        bytes[12] = 0;
        /*查看当前日期 日 -无意义*/
        bytes[13] = 0;
        bytes[14] = 0;
        /*查看当前日期 时 -无意义*/
        bytes[15] = 0;
        bytes[16] = 0;
        /*查看当前日期 分 -无意义*/
        bytes[17] = 0;
        bytes[18] = 0;
        /*打印间隔*/
        bytes[19] = DataUtil.intData2low(params.getPrintInterval());
        bytes[20] = DataUtil.intData2high(params.getPrintInterval());
        /*环温高温报警值*/
        bytes[21] = DataUtil.doubleData2low(params.getEvnHighTemperatureWarn());
        bytes[22] = DataUtil.doubleData2high(params.getEvnHighTemperatureWarn());
        /**门加热模式*/
        bytes[23] = DataUtil.doubleData2low(params.getDoorHeaterMode());
        bytes[24] = DataUtil.doubleData2high(params.getDoorHeaterMode());
        /**显示模式*/
        bytes[25] = DataUtil.intData2low(params.getDisplayMode());
        bytes[26] = DataUtil.intData2high(params.getDisplayMode());
        /*用户菜单密码设置*/
        bytes[27] = DataUtil.intData2low(params.getUserMenuPassword());
        bytes[28] = DataUtil.intData2high(params.getUserMenuPassword());
        /*硬件版本*/
        bytes[29] = DataUtil.intData2low(params.getHardWareVersion());
        bytes[30] = DataUtil.intData2high(params.getHardWareVersion());
        /*软件版本*/
        bytes[31] = DataUtil.intData2low(params.getSoftWareVersion());
        bytes[32] = DataUtil.intData2high(params.getSoftWareVersion());
        /*控制报警传感器温度*/
        bytes[33] = 0;
        bytes[34] = 0;
        /*上部传感器温度*/
        bytes[35] = 0;
        bytes[36] = 0;
        /*下部传感器*/
        bytes[37] = 0;
        bytes[38] = 0;
        /*环温传感器*/
        bytes[39] = 0;
        bytes[40] = 0;
        /*化霜传感器温度*/
        bytes[41] = 0;
        bytes[42] = 0;
        /*冷凝传感器温度*/
        bytes[43] = 0;
        bytes[44] = 0;
        /*冷凝传感器高温报警值*/
        bytes[45] = DataUtil.doubleData2low(params.getConHighTemperatureWarn());
        bytes[46] = DataUtil.doubleData2high(params.getConHighTemperatureWarn());
        /**控制报警传感器校正*/
        bytes[47] = DataUtil.doubleData2low(params.getControlAlarmRevise());
        bytes[48] = DataUtil.doubleData2high(params.getControlAlarmRevise());
        /*PT1000-1传感器温度校正*/
        bytes[49] = DataUtil.doubleData2low(params.getUpperPartTemperatureRevise());
        bytes[50] = DataUtil.doubleData2high(params.getUpperPartTemperatureRevise());
        /*PT1000-2传感器温度校正*/
        bytes[51] = DataUtil.doubleData2low(params.getLowerPartTemperatureRevise());
        bytes[52] = DataUtil.doubleData2high(params.getLowerPartTemperatureRevise());
        /*环境传感器温度校正*/
        bytes[53] = DataUtil.doubleData2low(params.getEvnTemperatureRevise());
        bytes[54] = DataUtil.doubleData2high(params.getEvnTemperatureRevise());
        /**化霜传感器温度校正*/
        bytes[55] = DataUtil.doubleData2low(params.getDefrostTemperatureRevise());
        bytes[56] = DataUtil.doubleData2high(params.getDefrostTemperatureRevise());
        /*冷凝器传感器温度校正*/
        bytes[57] = DataUtil.doubleData2low(params.getConTemperatureRevise());
        bytes[58] = DataUtil.doubleData2high(params.getConTemperatureRevise());
        /*压缩机开机回差*/
        bytes[59] = DataUtil.doubleData2low(params.getCompressorStartDiff());
        bytes[60] = DataUtil.doubleData2high(params.getCompressorStartDiff());
        /*压缩机停机回差*/
        bytes[61] = DataUtil.doubleData2low(params.getCompressorStopDiff());
        bytes[62] = DataUtil.doubleData2high(params.getCompressorStopDiff());
        /*压缩机启动间隔*/
        bytes[63] = DataUtil.intData2low(params.getCompressorStartInterval());
        bytes[64] = DataUtil.intData2high(params.getCompressorStartInterval());
        /*冷凝风机滞后压缩机停机时间*/
        bytes[65] = DataUtil.intData2low(params.getConCompressorLagTime());
        bytes[66] = DataUtil.intData2high(params.getConCompressorLagTime());
        /*超温报警延时*/
        bytes[67] = DataUtil.intData2low(params.getOverTemperatureWarnTime());
        bytes[68] = DataUtil.intData2high(params.getOverTemperatureWarnTime());
        /*开门报警延时*/
        bytes[69] = DataUtil.intData2low(params.getOpenDoorWarnTime());
        bytes[70] = DataUtil.intData2high(params.getOpenDoorWarnTime());
        /*PT1000-1/2传感器故障时压缩机工作时间*/
        bytes[71] = DataUtil.intData2low(params.getSensorErrorCompressorWorkTime());
        bytes[72] = DataUtil.intData2high(params.getSensorErrorCompressorWorkTime());
        /*PT1000-1/2传感器故障时压缩机停机时间*/
        bytes[73] = DataUtil.intData2low(params.getSensorErrorCompressorStopTime());
        bytes[74] = DataUtil.intData2high(params.getSensorErrorCompressorStopTime());
        /**化霜周期*/
        bytes[75] = DataUtil.intData2low(params.getDefrostingCycle());
        bytes[76] = DataUtil.intData2high(params.getDefrostingCycle());
        /**化霜时间*/
        bytes[77] = DataUtil.intData2low(params.getDefrostingTime());
        bytes[78] = DataUtil.intData2high(params.getDefrostingTime());
        /**模式1门加热时间*/
        bytes[79] = DataUtil.intData2low(params.getDoorHeatingTime());
        bytes[80] = DataUtil.intData2high(params.getDoorHeatingTime());
        /**除霜终止温度*/
        bytes[81] = DataUtil.doubleData2low(params.getDefrostStopTemp());
        bytes[82] = DataUtil.doubleData2high(params.getDefrostStopTemp());
        /*冷凝风机工作模式选择*/
        bytes[83] = DataUtil.intData2low(params.getEvaporatorModel());
        bytes[84] = DataUtil.intData2high(params.getEvaporatorModel());
        /**蒸发风机工作模式选择*/
        bytes[85] = DataUtil.intData2low(params.getEvapFanModel());
        bytes[86] = DataUtil.intData2high(params.getEvapFanModel());
        /**湿度传感器校正*/
        bytes[87] = DataUtil.doubleData2low(params.getHumidityRevise());
        bytes[88] = DataUtil.doubleData2high(params.getHumidityRevise());
        /**湿度传感器测量最小值*/
        bytes[89] = DataUtil.doubleData2low(params.getHumidityMeasureMin());
        bytes[90] = DataUtil.doubleData2high(params.getHumidityMeasureMin());
        /**湿度传感器测量最大值*/
        bytes[91] = DataUtil.doubleData2low(params.getHumidityMeasureMax());
        bytes[92] = DataUtil.doubleData2high(params.getHumidityMeasureMax());
        /**湿度超限处理*/
        bytes[93] = DataUtil.doubleData2low(params.getHumidityOutOfRange());
        bytes[94] = DataUtil.doubleData2high(params.getHumidityOutOfRange());
        /*电池低电压检测*/
        bytes[95] = DataUtil.intData2low(params.getBatteryVoltageDetection());
        bytes[96] = DataUtil.intData2high(params.getBatteryVoltageDetection());
        /*温度数据记录周期*/
        bytes[97] = DataUtil.intData2low(params.getTemperatureDataRecordTime());
        bytes[98] = DataUtil.intData2high(params.getTemperatureDataRecordTime());
        // String setRecordingTime = params.getSetRecordingTime();

        int year = 250;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
//         /*以- 开头*/
//         if (setRecordingTime.startsWith("-")) {
// //            String[] time = setRecordingTime.split("-");
// //            year = 0 - Integer.parseInt(time[1]);
//             month = Integer.parseInt(setRecordingTime.split("-")[2]);
//             String timeLeft = setRecordingTime.split("-")[3];
//             day = Integer.parseInt(timeLeft.substring(0, timeLeft.indexOf(" ")));
//             hour = Integer.parseInt(timeLeft.substring(timeLeft.indexOf(" ") + 1, timeLeft.indexOf(":")));
//             minute = Integer.parseInt(timeLeft.substring(timeLeft.indexOf(":") + 1));
//         } else {
// //            year = Integer.parseInt(setRecordingTime.substring(0, setRecordingTime.indexOf("-")));
//             month = Integer.parseInt(setRecordingTime.substring(setRecordingTime.indexOf("-") + 1, setRecordingTime.lastIndexOf("-")));
//             day = Integer.parseInt(setRecordingTime.substring(setRecordingTime.lastIndexOf("-") + 1, setRecordingTime.indexOf(" ")));
//             hour = Integer.parseInt(setRecordingTime.substring(setRecordingTime.indexOf(" ") + 1, setRecordingTime.indexOf(":")));
//             minute = Integer.parseInt(setRecordingTime.substring(setRecordingTime.indexOf(":") + 1));
//         }

        /*设定记录仪模块的时间   -年*/
        bytes[99] = DataUtil.intData2low(year);
        bytes[100] = DataUtil.intData2high(year);
        /*设定记录仪模块的时间   -月*/
        bytes[101] = DataUtil.intData2low(month);
        bytes[102] = DataUtil.intData2high(month);
        /*设定记录仪模块的时间   -日*/
        bytes[103] = DataUtil.intData2low(day);
        bytes[104] = DataUtil.intData2high(day);
        /*设定记录仪模块的时间   -时*/
        bytes[105] = DataUtil.intData2low(hour);
        bytes[106] = DataUtil.intData2high(hour);
        /*设定记录仪模块的时间   -分*/
        bytes[107] = DataUtil.intData2low(minute);
        bytes[108] = DataUtil.intData2high(minute);
        /*管理员菜单密码设置*/
        bytes[109] = DataUtil.intData2low(params.getAdminMenuPassword());
        bytes[110] = DataUtil.intData2high(params.getAdminMenuPassword());
        bytes[111] = 0;

        byte[] contents = Arrays.copyOfRange(bytes, 0, 112);
        int crc = CrcUtil.doCrc(contents);
        bytes[112] = (byte) crc;

        return bytes;
    }

}
