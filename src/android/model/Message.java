package com.zkmeiling.serialport.model;

import java.util.Date;
import java.util.List;

public class Message extends ReadParams {

    /**数据类型*/
    private Integer type;

    /**
     * 温度1
     */
    private Double temperature1;
    /**
     * 温度2
     */
    private Double temperature2;
    /**
     * 平均温度
     */
    private Double averageTemperate;
    /**
     * 控制报警温度
     */
    private Double controlAlarmTemp;
    /**
     * 除霜温度
     */
    private Double defrostingTemp;
    /**
     * 冷凝器温度
     */
    private Double condenserTemperature;
    /**
     * 环境温度
     */
    private Double environmentTemperature;
    /**
     * 湿度
     */
    private Double humidity;
    /**
     * 记录时间
     */
    private Date recordTime;
    /**
     * 接收时间
     */
    private Date receiveTime;
    /**
     * 记录仪状态
     */
    private Integer recodingState;
    /**
     * U盘导出数据状态
     */
    private Integer exportFileState;

    private String sourceData;
    private Integer sourceDataState;
    private String sourceDataStateDesc;

    List<MessageState> messageStates;

    /**
     * 门开关状态
     */
    private Integer doorOpenState;
    /**
     * 除霜状态
     */
    private Integer defrostState;
    /**
     * 是否断电
     */
    private Integer powerState;
    /**
     * wifi
     */
    private Integer wifiState;
    /**
     * 门加热状态
     */
    private Integer doorHeatState;
    /**
     * 制冷开关
     */
    private Integer coolingState;
    /**
     * 低电量状态
     */
    private Integer lowBatteryState;
    /**
     * 打印状态
     */
    private Integer printState;
    /**
     * 锁开关状态
     */
    private Integer lockState;
    /**
     * 风机状态
     */
    private Integer fanState;
    /**
     * 温度1传感器状态
     */
    private Integer temperature1State;
    /**
     * 温度2传感器状态
     */
    private Integer temperature2State;
    /**
     * 控制报警传感器状态
     */
    private Integer contAlarmSensorState;
    /**
     * 除霜传感器状态
     */
    private Integer defrostSensorState;
    /**
     * 冷凝器传感器状态
     */
    private Integer condenserSensorState;
    /**
     * 环境传感器状态
     */
    private Integer environmentSensorState;
    /**
     * 温度高温报警状态：
     */
    private Integer temperature1HighWarn;
    /**
     * 温度低温报警状态
     */
    private Integer temperature1LowWarn;
    /**
     * 环境温度高温报警状态
     */
    private Integer environmentTemperatureHighWarn;
    /**
     * 冷凝器温度高温报警状态
     */
    private Integer condenserTemperateHighWarn;
    /**
     * 门长时间打开报警状态
     */
    private Integer doorOpenTimeout;
    /**
     * 接收到手动打印状态
     */
    private Integer manualPrintState;
    /**
     * 电源电压检测故障状态
     */
    private Integer powerVoltageState;
    /**
     * 主控板通信故障状态
     */
    private Integer correspondenceState;
    /**
     * 电池电量检测故障状态：
     */
    private Integer batteryDetectionState;
    /**
     * 蜂鸣器鸣叫状态
     */
    private Integer buzzerState;
    /**
     * 湿度传感器故障状态
     */
    private Integer humidityDetectionState;
    /**
     * USB导出pdf文件结束标志
     */
    private Integer exportUsbFileState;
    /**
     * USB通信故障状态
     */
    private Integer usbCorrespondenceState;
    /**
     * 取消静音状态
     */
    private Integer muteState;
    /**
     * 高湿报警状态
     */
    private Integer highHumidityWarn;
    /**
     * 低湿报警状态
     */
    private Integer lowHumidityWarn;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Double temperature1) {
        this.temperature1 = temperature1;
    }

    public Double getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Double temperature2) {
        this.temperature2 = temperature2;
    }

    public Double getControlAlarmTemp() {
        return controlAlarmTemp;
    }

    public void setControlAlarmTemp(Double controlAlarmTemp) {
        this.controlAlarmTemp = controlAlarmTemp;
    }

    public Double getDefrostingTemp() {
        return defrostingTemp;
    }

    public void setDefrostingTemp(Double defrostingTemp) {
        this.defrostingTemp = defrostingTemp;
    }

    public Double getCondenserTemperature() {
        return condenserTemperature;
    }

    public void setCondenserTemperature(Double condenserTemperature) {
        this.condenserTemperature = condenserTemperature;
    }

    public Double getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public void setEnvironmentTemperature(Double environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getRecodingState() {
        return recodingState;
    }

    public void setRecodingState(Integer recodingState) {
        this.recodingState = recodingState;
    }

    public Integer getExportFileState() {
        return exportFileState;
    }

    public void setExportFileState(Integer exportFileState) {
        this.exportFileState = exportFileState;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public Integer getSourceDataState() {
        return sourceDataState;
    }

    public void setSourceDataState(Integer sourceDataState) {
        this.sourceDataState = sourceDataState;
    }

    public String getSourceDataStateDesc() {
        return sourceDataStateDesc;
    }

    public void setSourceDataStateDesc(String sourceDataStateDesc) {
        this.sourceDataStateDesc = sourceDataStateDesc;
    }

    public List<MessageState> getMessageStates() {
        return messageStates;
    }

    public void setMessageStates(List<MessageState> messageStates) {
        this.messageStates = messageStates;
    }

    public Integer getDoorOpenState() {
        return doorOpenState;
    }

    public void setDoorOpenState(Integer doorOpenState) {
        this.doorOpenState = doorOpenState;
    }

    public Integer getDefrostState() {
        return defrostState;
    }

    public void setDefrostState(Integer defrostState) {
        this.defrostState = defrostState;
    }

    public Integer getPowerState() {
        return powerState;
    }

    public void setPowerState(Integer powerState) {
        this.powerState = powerState;
    }

    public Integer getWifiState() {
        return wifiState;
    }

    public void setWifiState(Integer wifiState) {
        this.wifiState = wifiState;
    }

    public Integer getDoorHeatState() {
        return doorHeatState;
    }

    public void setDoorHeatState(Integer doorHeatState) {
        this.doorHeatState = doorHeatState;
    }

    public Integer getCoolingState() {
        return coolingState;
    }

    public void setCoolingState(Integer coolingState) {
        this.coolingState = coolingState;
    }

    public Integer getLowBatteryState() {
        return lowBatteryState;
    }

    public void setLowBatteryState(Integer lowBatteryState) {
        this.lowBatteryState = lowBatteryState;
    }

    public Integer getPrintState() {
        return printState;
    }

    public void setPrintState(Integer printState) {
        this.printState = printState;
    }

    public Integer getLockState() {
        return lockState;
    }

    public void setLockState(Integer lockState) {
        this.lockState = lockState;
    }

    public Integer getFanState() {
        return fanState;
    }

    public void setFanState(Integer fanState) {
        this.fanState = fanState;
    }

    public Integer getTemperature1State() {
        return temperature1State;
    }

    public void setTemperature1State(Integer temperature1State) {
        this.temperature1State = temperature1State;
    }

    public Integer getTemperature2State() {
        return temperature2State;
    }

    public void setTemperature2State(Integer temperature2State) {
        this.temperature2State = temperature2State;
    }

    public Integer getContAlarmSensorState() {
        return contAlarmSensorState;
    }

    public void setContAlarmSensorState(Integer contAlarmSensorState) {
        this.contAlarmSensorState = contAlarmSensorState;
    }

    public Integer getDefrostSensorState() {
        return defrostSensorState;
    }

    public void setDefrostSensorState(Integer defrostSensorState) {
        this.defrostSensorState = defrostSensorState;
    }

    public Integer getCondenserSensorState() {
        return condenserSensorState;
    }

    public void setCondenserSensorState(Integer condenserSensorState) {
        this.condenserSensorState = condenserSensorState;
    }

    public Integer getEnvironmentSensorState() {
        return environmentSensorState;
    }

    public void setEnvironmentSensorState(Integer environmentSensorState) {
        this.environmentSensorState = environmentSensorState;
    }

    public Integer getTemperature1HighWarn() {
        return temperature1HighWarn;
    }

    public void setTemperature1HighWarn(Integer temperature1HighWarn) {
        this.temperature1HighWarn = temperature1HighWarn;
    }

    public Integer getTemperature1LowWarn() {
        return temperature1LowWarn;
    }

    public void setTemperature1LowWarn(Integer temperature1LowWarn) {
        this.temperature1LowWarn = temperature1LowWarn;
    }

    public Integer getEnvironmentTemperatureHighWarn() {
        return environmentTemperatureHighWarn;
    }

    public void setEnvironmentTemperatureHighWarn(Integer environmentTemperatureHighWarn) {
        this.environmentTemperatureHighWarn = environmentTemperatureHighWarn;
    }

    public Integer getCondenserTemperateHighWarn() {
        return condenserTemperateHighWarn;
    }

    public void setCondenserTemperateHighWarn(Integer condenserTemperateHighWarn) {
        this.condenserTemperateHighWarn = condenserTemperateHighWarn;
    }

    public Integer getDoorOpenTimeout() {
        return doorOpenTimeout;
    }

    public void setDoorOpenTimeout(Integer doorOpenTimeout) {
        this.doorOpenTimeout = doorOpenTimeout;
    }

    public Integer getManualPrintState() {
        return manualPrintState;
    }

    public void setManualPrintState(Integer manualPrintState) {
        this.manualPrintState = manualPrintState;
    }

    public Integer getPowerVoltageState() {
        return powerVoltageState;
    }

    public void setPowerVoltageState(Integer powerVoltageState) {
        this.powerVoltageState = powerVoltageState;
    }

    public Integer getCorrespondenceState() {
        return correspondenceState;
    }

    public void setCorrespondenceState(Integer correspondenceState) {
        this.correspondenceState = correspondenceState;
    }

    public Integer getBatteryDetectionState() {
        return batteryDetectionState;
    }

    public void setBatteryDetectionState(Integer batteryDetectionState) {
        this.batteryDetectionState = batteryDetectionState;
    }

    public Integer getBuzzerState() {
        return buzzerState;
    }

    public void setBuzzerState(Integer buzzerState) {
        this.buzzerState = buzzerState;
    }

    public Integer getHumidityDetectionState() {
        return humidityDetectionState;
    }

    public void setHumidityDetectionState(Integer humidityDetectionState) {
        this.humidityDetectionState = humidityDetectionState;
    }

    public Integer getExportUsbFileState() {
        return exportUsbFileState;
    }

    public void setExportUsbFileState(Integer exportUsbFileState) {
        this.exportUsbFileState = exportUsbFileState;
    }

    public Integer getUsbCorrespondenceState() {
        return usbCorrespondenceState;
    }

    public void setUsbCorrespondenceState(Integer usbCorrespondenceState) {
        this.usbCorrespondenceState = usbCorrespondenceState;
    }

    public Integer getMuteState() {
        return muteState;
    }

    public void setMuteState(Integer muteState) {
        this.muteState = muteState;
    }

    public Integer getHighHumidityWarn() {
        return highHumidityWarn;
    }

    public void setHighHumidityWarn(Integer highHumidityWarn) {
        this.highHumidityWarn = highHumidityWarn;
    }

    public Integer getLowHumidityWarn() {
        return lowHumidityWarn;
    }

    public void setLowHumidityWarn(Integer lowHumidityWarn) {
        this.lowHumidityWarn = lowHumidityWarn;
    }

    public Double getAverageTemperate() {
        return averageTemperate;
    }

    public void setAverageTemperate(Double averageTemperate) {
        this.averageTemperate = averageTemperate;
    }
}
