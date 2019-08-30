#### 普冷冰箱通信插件

**安装**

cordova plugin add ordinart-plugin

**使用**

1.打开串口

ordinartPlugin.openSerialPort(arg0, successCallback, errorCallback);

2.关闭串口

ordinartPlugin.closeSerialPort(successCallback, errorCallback);

3.发送串口指令	-向串口发送数据 (**所有参数的json字符串**)

ordinartPlugin.sendSerialPort(arg0, successCallback, errorCallback);

4.读取串口数据	-读取串口发出数据

ordinartPlugin.readSerial(successCallback, errorCallback);

5.反馈主控板	-读取到串口数据后需要反馈主控板一次，否则控制板会报通信故障

ordinartPlugin.sendDataInfo(successCallback, errorCallback);

6.读取参数	-读取控制板参数需要发送该指令

ordinartPlugin.sendParamInfo(successCallback, errorCallback);

7.开电磁锁

ordinartPlugin.openLock(successCallback, errorCallback);

8.开led灯

ordinartPlugin.openLed(successCallback, errorCallback);

**示例**

```
onDeviceReady: function() {
        ordinartPlugin.openSerialPort(["/dev/ttyS3", 9600], function(success) {
	        alert(success);
	        ordinartPlugin.readSerial(function(data){
	        	ordinartPlugin.sendDataInfo(function(s){}, function(e){});
	            alert(data);
	            var obj = JSON.parse(data);
	            if(obj.sourceDataState == 1) {
	                //数据正常
	                if(obj.type == 6) {
	                    //获取冰箱运行状态数据
	                }else if(obj.type == 5) {
	                    //获取参数设置
	                }
	            }else if(obj.sourceDataState == 99) {
	                //crc解析错误
	            }
		    }, function(error) {
		        alert(error);
		    })
		}, function(error) {
		    alert(error);
		});

        var btn = document.getElementById('button');
        btn.onclick = function() {
            ordinartPlugin.openLed(function(s){}, function(e){});
        }
    }
```

**字段说明**

```
temperature1			——	上部温度(double ℃)
temperature2			——	下部温度(double ℃)
averageTemperate		——	平均温度(double ℃)
controlAlarmTemp		——	控制报警温度(double ℃)
defrostingTemp			——	除霜温度(double ℃)
condenserTemperature	——	冷凝器温度(double ℃)
environmentTemperature	——	环境温度(double ℃)
humidity				——	湿度(double %RH)
doorOpenState			——	门开关状态(int 1-开 0-关)
defrostState			——	除霜状态(int 1-开 0-关)
powerState				——	是否断电(int 1-断电 0-未断电)
coolingState			——	制冷状态(int 1-开 0-关)
lockState				——	锁状态(int 1-开 0-关)
fanState				——	风机状态(int 1-开 0-关)
temperature1State		——	上部传感器状态(int 1-故障 0-正常)
temperature2State		——	下部传感器状态(int 1-故障 0-正常)
contAlarmSensorState	——	控制报警传感器状态(int 1-故障 0-正常)
defrostSensorState		——	除霜传感器状态(int 1-故障 0-正常)
condenserSensorState	——	冷凝器传感器状态(int 1-故障 0-正常)
environmentSensorState	——	环境传感器状态(int 1-故障 0-正常)
temperature1HighWarn	——	高温报警(int 1-报警 0-正常)
temperature1LowWarn		——	低温报警(int 1-报警 0-正常)
environmentTemperatureHighWarn	——	环温高温报警(int 1-报警 0-正常)
condenserTemperateHighWarn		——	冷凝器高温报警(int 1-报警 0-正常)
doorOpenTimeout			——	门长时间打开报警(int 1-报警 0-正常)
correspondenceState		——	主控板通信故障状态(int 1-故障 0-正常)
humidityDetectionState	——	湿度传感器故障状态(int 1-故障 0-正常)
highHumidityWarn		——	高湿报警状态(int 1-报警 0-正常)
lowHumidityWarn			——	低湿报警状态(int 1-报警 0-正常)
```



##### 设置参数

```
	/**温度设定*/
    private Double temperatureSetting;
    /**高温报警偏差*/
    private Double highTemperatureWarnDeviation;
    /**低温报警偏差*/
    private Double lowTemperatureWarnDeviation;
    /**打印间隔*/
    private int printInterval;
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
```

