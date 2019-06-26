package com.zkmeiling.serialport.model;

public class ReadParams {

    /**帧头*/
    private Integer head = 0x5A;
    /**功能码*/
    private Integer dataType = 0x02;
    /**字节数*/
    private Integer length = 0x6c;
    /**温度设定*/
    private Double temperatureSetting;
    /**高温报警偏差*/
    private Double highTemperatureWarnDeviation;
    /**低温报警偏差*/
    private Double lowTemperatureWarnDeviation;
    /*查看时间*/
    private String currentTime;
    /**打印间隔*/
    private Integer printInterval;
    /**环温高温报警值*/
    private Double evnHighTemperatureWarn;
    /**门加热器模式*/
    private Integer doorHeaterMode;
    /**显示模式*/
    private Integer displayMode;
    /**按键锁密码*/
    private Integer userMenuPassword;
    /**硬件版本*/
    private Integer hardWareVersion;
    /**软件版本*/
    private Integer softWareVersion;
    /**冷凝传感器高温报警值*/
    private Double conHighTemperatureWarn;
    /**控制报警传感器温度校正*/
    private Double controlAlarmRevise;
    /**上部传感器温度校正*/
    private Double upperPartTemperatureRevise;
    /**下部传感器温度校正*/
    private Double lowerPartTemperatureRevise;
    /**环境传感器温度校正*/
    private Double evnTemperatureRevise;
    /**化霜传感器温度校正*/
    private Double defrostTemperatureRevise;
    /**冷凝器传感器温度校正*/
    private Double conTemperatureRevise;
    /**压缩机开机回差*/
    private Double compressorStartDiff;
    /**压缩机停机回差*/
    private Double compressorStopDiff;
    /**压缩机启动间隔*/
    private Integer compressorStartInterval;
    /**冷凝风机滞后压缩机停机时间*/
    private Integer conCompressorLagTime;
    /**超温报警延时*/
    private Integer overTemperatureWarnTime;
    /**开门报警延时*/
    private Integer openDoorWarnTime;
    /**控制报警传感器故障时压缩机工作时间*/
    private Integer sensorErrorCompressorWorkTime;
    /**控制报警传感器故障时压缩机停机时间*/
    private Integer sensorErrorCompressorStopTime;
    /**化霜周期*/
    private Integer defrostingCycle;
    /**化霜时间*/
    private Integer defrostingTime;
    /**模式1门加热持续时间*/
    private Integer doorHeatingTime;
    /**除霜终止温度*/
    private Double defrostStopTemp;
    /**冷凝风机工作模式选择*/
    private Integer evaporatorModel;
    /**蒸发风机工作模式选择*/
    private Integer evapFanModel;
    /**湿度传感器校正*/
    private Double humidityRevise;
    /**湿度传感器测量最小值*/
    private Double humidityMeasureMin;
    /**湿度传感器测量最大值*/
    private Double humidityMeasureMax;
    /**湿度超限处理*/
    private Integer humidityOutOfRange;
    /**电池低电压检测 - 是否使能断电报警功能*/
    private Integer batteryVoltageDetection;
    /**温度数据记录周期*/
    private Integer temperatureDataRecordTime;
    /*设定记录仪时间*/
    private String setRecordingTime;
    /**管理员菜单密码设置*/
    private Integer adminMenuPassword;
    /**CRC8*/
    private Integer crc8;

    /**接收参数时间*/
    private  String receivedTime;

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getTemperatureSetting() {
        return temperatureSetting;
    }

    public void setTemperatureSetting(Double temperatureSetting) {
        this.temperatureSetting = temperatureSetting;
    }

    public Double getHighTemperatureWarnDeviation() {
        return highTemperatureWarnDeviation;
    }

    public void setHighTemperatureWarnDeviation(Double highTemperatureWarnDeviation) {
        this.highTemperatureWarnDeviation = highTemperatureWarnDeviation;
    }

    public Double getLowTemperatureWarnDeviation() {
        return lowTemperatureWarnDeviation;
    }

    public void setLowTemperatureWarnDeviation(Double lowTemperatureWarnDeviation) {
        this.lowTemperatureWarnDeviation = lowTemperatureWarnDeviation;
    }

    public Integer getPrintInterval() {
        return printInterval;
    }

    public void setPrintInterval(Integer printInterval) {
        this.printInterval = printInterval;
    }

    public Double getEvnHighTemperatureWarn() {
        return evnHighTemperatureWarn;
    }

    public void setEvnHighTemperatureWarn(Double evnHighTemperatureWarn) {
        this.evnHighTemperatureWarn = evnHighTemperatureWarn;
    }

    public Integer getDoorHeaterMode() {
        return doorHeaterMode;
    }

    public void setDoorHeaterMode(Integer doorHeaterMode) {
        this.doorHeaterMode = doorHeaterMode;
    }

    public Integer getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(Integer displayMode) {
        this.displayMode = displayMode;
    }

    public Integer getUserMenuPassword() {
        return userMenuPassword;
    }

    public void setUserMenuPassword(Integer userMenuPassword) {
        this.userMenuPassword = userMenuPassword;
    }

    public Integer getHardWareVersion() {
        return hardWareVersion;
    }

    public void setHardWareVersion(Integer hardWareVersion) {
        this.hardWareVersion = hardWareVersion;
    }

    public Integer getSoftWareVersion() {
        return softWareVersion;
    }

    public void setSoftWareVersion(Integer softWareVersion) {
        this.softWareVersion = softWareVersion;
    }

    public Double getConHighTemperatureWarn() {
        return conHighTemperatureWarn;
    }

    public void setConHighTemperatureWarn(Double conHighTemperatureWarn) {
        this.conHighTemperatureWarn = conHighTemperatureWarn;
    }

    public Double getControlAlarmRevise() {
        return controlAlarmRevise;
    }

    public void setControlAlarmRevise(Double controlAlarmRevise) {
        this.controlAlarmRevise = controlAlarmRevise;
    }

    public Double getEvnTemperatureRevise() {
        return evnTemperatureRevise;
    }

    public void setEvnTemperatureRevise(Double evnTemperatureRevise) {
        this.evnTemperatureRevise = evnTemperatureRevise;
    }

    public Double getDefrostTemperatureRevise() {
        return defrostTemperatureRevise;
    }

    public void setDefrostTemperatureRevise(Double defrostTemperatureRevise) {
        this.defrostTemperatureRevise = defrostTemperatureRevise;
    }

    public Double getConTemperatureRevise() {
        return conTemperatureRevise;
    }

    public void setConTemperatureRevise(Double conTemperatureRevise) {
        this.conTemperatureRevise = conTemperatureRevise;
    }

    public Double getCompressorStartDiff() {
        return compressorStartDiff;
    }

    public void setCompressorStartDiff(Double compressorStartDiff) {
        this.compressorStartDiff = compressorStartDiff;
    }

    public Double getCompressorStopDiff() {
        return compressorStopDiff;
    }

    public void setCompressorStopDiff(Double compressorStopDiff) {
        this.compressorStopDiff = compressorStopDiff;
    }

    public Integer getCompressorStartInterval() {
        return compressorStartInterval;
    }

    public void setCompressorStartInterval(Integer compressorStartInterval) {
        this.compressorStartInterval = compressorStartInterval;
    }

    public Integer getConCompressorLagTime() {
        return conCompressorLagTime;
    }

    public void setConCompressorLagTime(Integer conCompressorLagTime) {
        this.conCompressorLagTime = conCompressorLagTime;
    }

    public Integer getOverTemperatureWarnTime() {
        return overTemperatureWarnTime;
    }

    public void setOverTemperatureWarnTime(Integer overTemperatureWarnTime) {
        this.overTemperatureWarnTime = overTemperatureWarnTime;
    }

    public Integer getOpenDoorWarnTime() {
        return openDoorWarnTime;
    }

    public void setOpenDoorWarnTime(Integer openDoorWarnTime) {
        this.openDoorWarnTime = openDoorWarnTime;
    }

    public Integer getSensorErrorCompressorWorkTime() {
        return sensorErrorCompressorWorkTime;
    }

    public void setSensorErrorCompressorWorkTime(Integer sensorErrorCompressorWorkTime) {
        this.sensorErrorCompressorWorkTime = sensorErrorCompressorWorkTime;
    }

    public Integer getSensorErrorCompressorStopTime() {
        return sensorErrorCompressorStopTime;
    }

    public void setSensorErrorCompressorStopTime(Integer sensorErrorCompressorStopTime) {
        this.sensorErrorCompressorStopTime = sensorErrorCompressorStopTime;
    }

    public Integer getDefrostingCycle() {
        return defrostingCycle;
    }

    public void setDefrostingCycle(Integer defrostingCycle) {
        this.defrostingCycle = defrostingCycle;
    }

    public Integer getDefrostingTime() {
        return defrostingTime;
    }

    public void setDefrostingTime(Integer defrostingTime) {
        this.defrostingTime = defrostingTime;
    }

    public Integer getDoorHeatingTime() {
        return doorHeatingTime;
    }

    public void setDoorHeatingTime(Integer doorHeatingTime) {
        this.doorHeatingTime = doorHeatingTime;
    }

    public Double getDefrostStopTemp() {
        return defrostStopTemp;
    }

    public void setDefrostStopTemp(Double defrostStopTemp) {
        this.defrostStopTemp = defrostStopTemp;
    }

    public Integer getEvaporatorModel() {
        return evaporatorModel;
    }

    public void setEvaporatorModel(Integer evaporatorModel) {
        this.evaporatorModel = evaporatorModel;
    }

    public Integer getEvapFanModel() {
        return evapFanModel;
    }

    public void setEvapFanModel(Integer evapFanModel) {
        this.evapFanModel = evapFanModel;
    }

    public Double getHumidityRevise() {
        return humidityRevise;
    }

    public void setHumidityRevise(Double humidityRevise) {
        this.humidityRevise = humidityRevise;
    }

    public Double getHumidityMeasureMin() {
        return humidityMeasureMin;
    }

    public void setHumidityMeasureMin(Double humidityMeasureMin) {
        this.humidityMeasureMin = humidityMeasureMin;
    }

    public Double getHumidityMeasureMax() {
        return humidityMeasureMax;
    }

    public void setHumidityMeasureMax(Double humidityMeasureMax) {
        this.humidityMeasureMax = humidityMeasureMax;
    }

    public Integer getHumidityOutOfRange() {
        return humidityOutOfRange;
    }

    public void setHumidityOutOfRange(Integer humidityOutOfRange) {
        this.humidityOutOfRange = humidityOutOfRange;
    }

    public Integer getBatteryVoltageDetection() {
        return batteryVoltageDetection;
    }

    public void setBatteryVoltageDetection(Integer batteryVoltageDetection) {
        this.batteryVoltageDetection = batteryVoltageDetection;
    }

    public Integer getTemperatureDataRecordTime() {
        return temperatureDataRecordTime;
    }

    public void setTemperatureDataRecordTime(Integer temperatureDataRecordTime) {
        this.temperatureDataRecordTime = temperatureDataRecordTime;
    }

    public Integer getAdminMenuPassword() {
        return adminMenuPassword;
    }

    public void setAdminMenuPassword(Integer adminMenuPassword) {
        this.adminMenuPassword = adminMenuPassword;
    }

    public Integer getCrc8() {
        return crc8;
    }

    public void setCrc8(Integer crc8) {
        this.crc8 = crc8;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Double getUpperPartTemperatureRevise() {
        return upperPartTemperatureRevise;
    }

    public void setUpperPartTemperatureRevise(Double upperPartTemperatureRevise) {
        this.upperPartTemperatureRevise = upperPartTemperatureRevise;
    }

    public Double getLowerPartTemperatureRevise() {
        return lowerPartTemperatureRevise;
    }

    public void setLowerPartTemperatureRevise(Double lowerPartTemperatureRevise) {
        this.lowerPartTemperatureRevise = lowerPartTemperatureRevise;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getSetRecordingTime() {
        return setRecordingTime;
    }

    public void setSetRecordingTime(String setRecordingTime) {
        this.setRecordingTime = setRecordingTime;
    }
}
