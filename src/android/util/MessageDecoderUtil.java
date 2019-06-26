package com.zkmeiling.serialport.util;

import android.content.Context;

import com.zkmeiling.serialport.model.Message;
import com.zkmeiling.serialport.model.Received;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Chenl
 * 处理接收到的温度等数据
 */
public class MessageDecoderUtil {

    public static Message getMessage(Received received, Context mContext) throws ParseException {
        Message message = new Message();
        byte[] content = received.getContent();
        /**Pt1000-1*/
        message.setTemperature1(DataUtil.byte2doubleData(content[0],content[1]));
        /**Pt1000-2*/
        message.setTemperature2(DataUtil.byte2doubleData(content[2],content[3]));
        /**平均温度*/
        int sensorState = content[17] & 0xff;
        boolean pt1State = false;
        boolean pt2State = false;
        if ((sensorState & 0b0010_0000) >> 5 == 1) { //Pt1000-1 故障
            pt1State = false;
        } else if ((sensorState & 0b0010_0000) >> 5 == 0) { //Pt1000-1 正常
            pt1State = true;
        }
        if ((sensorState & 0b0001_0000) >> 4 == 1) { //Pt1000-2 故障
            pt2State = false;
        } else if ((sensorState & 0b0001_0000) >> 4 == 0) { //Pt1000-2 正常
            pt2State = true;
        }
        if (pt1State && !pt2State) { // pt1正常 pt2故障 则显示pt1的温度
            message.setAverageTemperate(DataUtil.byte2doubleData(content[0],content[1]));
        }else if (!pt1State && pt2State) { // pt2正常 pt1故障 则显示pt2的温度
            message.setAverageTemperate(DataUtil.byte2doubleData(content[2],content[3]));
        }else {
            message.setAverageTemperate(DataUtil.byte2doubleData(content[4],content[5]));
        }

        /**控制报警温度*/
        message.setControlAlarmTemp(DataUtil.byte2doubleData(content[6], content[7]));
        /**除霜温度*/
        message.setDefrostingTemp(DataUtil.byte2doubleData(content[8], content[9]));
        /**冷凝传感器温度*/
        message.setCondenserTemperature(DataUtil.byte2doubleData(content[10],content[11]));
        /**环境温度*/
        message.setEnvironmentTemperature(DataUtil.byte2doubleData(content[12],content[13]));
        /**湿度*/
        message.setHumidity(DataUtil.byte2doubleData(content[14], content[15]));
        /**报警状态*/
        message.setMessageStates(MessageDecoderUtil.getMessageState(content, mContext));
        initState(message, content, mContext);

        message.setRecodingState((int) content[20]);
        message.setExportFileState((int) content[21]);
        byte[] timeBytes = new byte[5];
        System.arraycopy(content, 22, timeBytes, 0, 5);
        message.setRecordTime(FormatTimeUtil.formatTime(timeBytes));
        message.setReceiveTime(new Date());

        return message;
    }

    /**
     * 解析原始数据的4类状态
     */
    private static Message initState(Message message, byte[] content, Context mContext) {

        if (content.length < 20) {
            return null;
        }
        int state1 = content[16] & 0xff;
        int state2 = content[17] & 0xff;
        int state3 = content[18] & 0xff;
        int state4 = content[19] & 0xff;

        //----------------------------------state1 start---------------------------------------
        // 1-门开 0-门关
        message.setDoorOpenState((state1 >> 7));
        // 除霜状态 0-关 1-开
        message.setDefrostState((state1 & 0b0100_0000) >> 6);
        // 1-断电 0-未断电
        message.setPowerState((state1 & 0b0010_0000) >> 5);
        // 1-连接正常 0-未连接
        message.setWifiState((state1 & 0b0001_0000) >> 4);
        // 门加热状态 1-开 0-关
        message.setDoorHeatState((state1 & 0b0000_1000) >> 3);
        // 1-制冷开 0-制冷关
        message.setCoolingState((state1 & 0b0000_0100) >> 2);
        // 1-电量低 0-电量正常
        message.setLowBatteryState((state1 & 0b0000_0010) >> 1);
        // 1-打印 0-未打印
        message.setPrintState(state1 & 0b0000_0001);
        //----------------------------------state1 end---------------------------------------

        //----------------------------------state2 start---------------------------------------
        // 锁状态 1-开 0-关
        message.setLockState(state2 >> 7);
        // 蒸发风机状态 1-开 0-关
        message.setFanState((state2 & 0b0100_0000) >> 6);
        // 上部传感器 1-故障 0-正常
        message.setTemperature1State((state2 & 0b0010_0000) >> 5);
        // 下部传感器 1-故障 0-正常
        message.setTemperature2State((state2 & 0b0001_0000) >> 4);
        // 控制传感器 1-故障 0-正常
        message.setContAlarmSensorState((state2 & 0b0000_1000) >> 3);
        // 除霜传感器 1-故障 0-正常
        message.setDefrostSensorState((state2 & 0b0000_0100) >> 2);
        // 冷凝器传感器 1-故障 0-正常
        message.setCondenserSensorState((state2 & 0b0000_0010) >> 1);
        // 环境温度传感器 1-故障 0-正常
        message.setEnvironmentSensorState(state2 & 0b0000_0001);
        //----------------------------------state2 end---------------------------------------

        //----------------------------------state3 start---------------------------------------
        // 高温报警 1-报警 0-正常
        message.setTemperature1HighWarn(state3 >> 7);
        // 低温报警 1-报警 0-正常
        message.setTemperature1LowWarn((state3 & 0b0100_0000) >> 6);
        // 环温高温报警 1-报警 0-正常
        message.setEnvironmentTemperatureHighWarn((state3 & 0b0010_0000) >> 5);
        // 冷凝器高温报警 1-报警 0-正常
        message.setCondenserTemperateHighWarn((state3 & 0b0001_0000) >> 4);
        // 门长时间打开报警 1-报警 0-正常
        message.setDoorOpenTimeout((state3 & 0b0000_1000) >> 3);
        // 接收到手动打印状态 1-接收到 0-未接收到
        message.setManualPrintState((state3 & 0b0000_0100) >> 2);
        // 电源电压检测故障状态 1-故障 0-正常
        message.setPowerVoltageState((state3 & 0b0000_0010) >> 1);
        // 主控板通信故障状态 1-故障 0-正常
        message.setCorrespondenceState(state3 & 0b0000_0001);
        //----------------------------------state3 end---------------------------------------

        //----------------------------------state4 start---------------------------------------
        // 电池电量检测故障状态状态 1-故障 0-正常
        message.setBatteryDetectionState(state4 >> 7);
        // 蜂鸣器鸣叫状态 1-鸣叫 0-不鸣叫
        message.setBuzzerState((state4 & 0b0100_0000) >> 6);
        // 湿度传感器故障 1-故障 0-正常
        message.setHumidityDetectionState((state4 & 0b0010_0000) >> 5);
        // USB导出pdf文件结束标志 1-结束 0-没结束
        message.setExportUsbFileState((state4 & 0b0001_0000) >> 4);
        // USB通信故障状态 1-故障 0-正常
        message.setUsbCorrespondenceState((state4 & 0b0000_1000) >> 3);
        // 高湿报警 1-报警 0-正常
        message.setHighHumidityWarn((state4 & 0b0000_0100) >> 2);
        // 低湿报警 1-报警 0-正常
        message.setLowHumidityWarn((state4 & 0b0000_0010) >> 1);
        // 取消静音状态 1-取消静音 0-允许静音
        message.setMuteState(state4 & 0b0000_0001);
        //----------------------------------state4 end---------------------------------------

        return message;
    }


    private static List<MessageState> getMessageState(byte[] content, Context mContext) {
        if (content.length < 20) {
            return null;
        }
        MessageState messageState = null;
        List<MessageState> messageStates = new ArrayList<>();
        int state1 = content[16] & 0xff;
        int state2 = content[17] & 0xff;
        int state3 = content[18] & 0xff;
        int state4 = content[19] & 0xff;
        /*
         * 状态1
         * 6种
         */
        if ((state1 & 0b0010_0000) >> 5 == 1) {
            messageState = new MessageState("POWER_OFF", mContext.getString(R.string.POWER_OFF));
            messageStates.add(messageState);
        } else if ((state1 & 0b0010_0000) >> 5 == 0) {
            messageState = new MessageState("POWER_ON", mContext.getString(R.string.POWER_ON));
            messageStates.add(messageState);
        }

        if ((state1 & 0b0000_0010) >> 1 == 1) {
            messageState = new MessageState("BATTERY_LOW", mContext.getString(R.string.BATTERY_LOW));
            messageStates.add(messageState);
        } else if ((state1 & 0b0000_0010) >> 1 == 0) {
            messageState = new MessageState("BATTERY_NORMAL", mContext.getString(R.string.BATTERY_NORMAL));
            messageStates.add(messageState);
        }
        /*
         * 状态2
         * 5种
         */
        if ((state2 & 0b0010_0000) >> 5 == 1) {
            messageState = new MessageState("TEMPERATURE1_ERROR", mContext.getString(R.string.TEMPERATURE1_ERROR));
            messageStates.add(messageState);
        } else if ((state2 & 0b0010_0000) >> 5 == 0) {
            messageState = new MessageState("TEMPERATURE1_NORMAL", mContext.getString(R.string.TEMPERATURE1_NORMAL));
            messageStates.add(messageState);
        }

        if ((state2 & 0b0001_0000) >> 4 == 1) {
            messageState = new MessageState("TEMPERATURE2_ERROR", mContext.getString(R.string.TEMPERATURE2_ERROR));
            messageStates.add(messageState);
        } else if ((state2 & 0b0001_0000) >> 4 == 0) {
            messageState = new MessageState("TEMPERATURE2_NORMAL", mContext.getString(R.string.TEMPERATURE2_NORMAL));
            messageStates.add(messageState);
        }

        if ((state2 & 0b0000_1000) >> 3 == 1) {
            messageState = new MessageState("CONTROL_SENSOR_ERROR", mContext.getString(R.string.CONTROL_SENSOR_ERROR));
            messageStates.add(messageState);
        }else if ((state2 & 0b0000_1000) >> 3 == 0) {
            messageState = new MessageState("CONTROL_SENSOR_NORMAL", mContext.getString(R.string.CONTROL_SENSOR_NORMAL));
            messageStates.add(messageState);
        }

        if ((state2 & 0b0000_0100) >> 2 == 1) {
            messageState = new MessageState("DEFROSTING_SENSOR_ERROR", mContext.getString(R.string.DEFROSTING_SENSOR_ERROR));
            messageStates.add(messageState);
        }else if ((state2 & 0b0000_0100) >> 2 == 0) {
            messageState = new MessageState("DEFROSTING_SENSOR_NORMAL", mContext.getString(R.string.DEFROSTING_SENSOR_NORMAL));
            messageStates.add(messageState);
        }

        if ((state2 & 0b0000_0010) >> 1 == 1) {
            messageState = new MessageState("CONDENSER_TEMPERATURE_ERROR", mContext.getString(R.string.CONDENSER_TEMPERATURE_ERROR));
            messageStates.add(messageState);
        } else if ((state2 & 0b0000_0010) >> 1 == 0) {
            messageState = new MessageState("CONDENSER_TEMPERATURE_NORMAL", mContext.getString(R.string.CONDENSER_TEMPERATURE_NORMAL));
            messageStates.add(messageState);
        }

        if ((state2 & 0b0000_0001) == 1) {
            messageState = new MessageState("ENVIRONMENT_TEMPERATURE_ERROR", mContext.getString(R.string.ENVIRONMENT_TEMPERATURE_ERROR));
            messageStates.add(messageState);
        } else if ((state2 & 0b0000_0001) == 0) {
            messageState = new MessageState("ENVIRONMENT_TEMPERATURE_NORMAL", mContext.getString(R.string.ENVIRONMENT_TEMPERATURE_NORMAL));
            messageStates.add(messageState);
        }
        /*
         * 状态3
         * 8种
         */
        if (state3 >> 7 == 1) {
            messageState = new MessageState("TEMPERATURE1_HEIGH", mContext.getString(R.string.TEMPERATURE1_HEIGH));
            messageStates.add(messageState);
        } else if (state3 >> 7 == 0) {
            messageState = new MessageState("TEMPERATURE1_HEIGH_NORAML", mContext.getString(R.string.TEMPERATURE1_HEIGH_NORAML));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0100_0000) >> 6 == 1) {
            messageState = new MessageState("TEMPERATURE1_LOW", mContext.getString(R.string.TEMPERATURE1_LOW));
            messageStates.add(messageState);
        } else if ((state3 & 0b0100_0000) >> 6 == 0) {
            messageState = new MessageState("TEMPERATURE1_LOW_NORMAL", mContext.getString(R.string.TEMPERATURE1_LOW_NORMAL));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0010_0000) >> 5 == 1) {
            messageState = new MessageState("ENVIRONMENT_TEMPERATURE_HEIGH", mContext.getString(R.string.ENVIRONMENT_TEMPERATURE_HEIGH));
            messageStates.add(messageState);
        } else if ((state3 & 0b0010_0000) >> 5 == 0) {
            messageState = new MessageState("ENVIRONMENT_TEMPERATURE_HEIGH_NORMAL", mContext.getString(R.string.ENVIRONMENT_TEMPERATURE_HEIGH_NORMAL));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0001_0000) >> 4 == 1) {
            messageState = new MessageState("CONDENSER_TEMPERTURE_HEIGH", mContext.getString(R.string.CONDENSER_TEMPERTURE_HEIGH));
            messageStates.add(messageState);
        } else if ((state3 & 0b0001_0000) >> 4 == 0) {
            messageState = new MessageState("CONDENSER_TEMPERTURE_HEIGH_NORMAL", mContext.getString(R.string.CONDENSER_TEMPERTURE_HEIGH_NORMAL));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0000_1000) >> 3 == 1) {
            messageState = new MessageState("DOOR_OPEN_TIMEOUT", mContext.getString(R.string.DOOR_OPEN_TIMEOUT));
            messageStates.add(messageState);
        } else if ((state3 & 0b0000_1000) >> 3 == 0) {
            messageState = new MessageState("DOOR_OPEN_TIMEOUT_NORMAL", mContext.getString(R.string.DOOR_OPEN_TIMEOUT_NORMAL));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0001_0010) >> 1 == 1) {
            messageState = new MessageState("POWER_VOTAGE_ERROR", mContext.getString(R.string.POWER_VOTAGE_ERROR));
            messageStates.add(messageState);
        } else if ((state3 & 0b0001_0010) >> 1 == 0) {
            messageState = new MessageState("POWER_VOTAGE_NORMAL", mContext.getString(R.string.POWER_VOTAGE_NORMAL));
            messageStates.add(messageState);
        }

        if ((state3 & 0b0001_0001) == 1) {
            messageState = new MessageState("CORRESPONDENCE_ERROR", mContext.getString(R.string.CORRESPONDENCE_ERROR));
            messageStates.add(messageState);
        } else if ((state3 & 0b0001_0001) == 0) {
            messageState = new MessageState("CORRESPONDENCE_NORMAL", mContext.getString(R.string.CORRESPONDENCE_NORMAL));
            messageStates.add(messageState);
        }
        /*
         * 状态4
         * 5种
         */
        if (state4 >> 7 == 1) {
            messageState = new MessageState("BATTERY_DETECTION_ERROR", mContext.getString(R.string.BATTERY_DETECTION_ERROR));
            messageStates.add(messageState);
        } else if (state4 >> 7 == 0) {
            messageState = new MessageState("BATTERY_DETECTION_NORMAL", mContext.getString(R.string.BATTERY_DETECTION_NORMAL));
            messageStates.add(messageState);
        }

        if ((state4 & 0b0010_0000) >> 5 == 1) {
            messageState = new MessageState("HUMIDITY_SENSOR_ERROR", mContext.getString(R.string.HUMIDITY_SENSOR_ERROR));
            messageStates.add(messageState);
        } else if ((state4 & 0b0010_0000) >> 5 == 0) {
            messageState = new MessageState("HUMIDITY_SENSOR_NORMAL", mContext.getString(R.string.HUMIDITY_SENSOR_NORMAL));
            messageStates.add(messageState);
        }

        if ((state4 & 0b0000_0100) >> 2 == 1) {
            messageState = new MessageState("LOW_HUMIDITY_WARN", mContext.getString(R.string.LOW_HUMIDITY_WARN));
            messageStates.add(messageState);
        } else if ((state4 & 0b0000_0100) >> 2 == 0) {
            messageState = new MessageState("LOW_HUMIDITY_NORMAL", mContext.getString(R.string.LOW_HUMIDITY_NORMAL));
            messageStates.add(messageState);
        }

        if ((state4 & 0b0000_0010) >> 1 == 1) {
            messageState = new MessageState("HIGH_HUMIDITY_WARN", mContext.getString(R.string.HIGH_HUMIDITY_WARN));
            messageStates.add(messageState);
        } else if ((state4 & 0b0000_0010) >> 1 == 0) {
            messageState = new MessageState("HIGH_HUMIDITY_NORMAL", mContext.getString(R.string.HIGH_HUMIDITY_NORMAL));
            messageStates.add(messageState);
        }
        return messageStates;
    }

}
