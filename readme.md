#### 普冷冰箱通信插件

**安装**

cordova plugin add ordinart-plugin

**使用**

1.打开串口

ordinartPlugin.openSerialPort(arg0, successCallback, errorCallback);

2.关闭串口

ordinartPlugin.closeSerialPort(successCallback, errorCallback);

3.发送串口指令	-向串口发送数据 (string类型)

ordinartPlugin.sendSerialPort(arg0, successCallback, errorCallback);

4.读取串口数据	-读取串口发出数据

ordinartPlugin.readSerial(successCallback, errorCallback);

5.反馈主控板	-读取到串口数据后需要反馈主控板一次，否则控制板会报通信故障

ordinartPlugin.sendDataInfo(successCallback, errorCallback);

6.读取参数	-读取控制板参数需要发送该指令

ordinartPlugin.sendParamInfo(successCallback, errorCallback);

7.开电磁锁

ordinartPlugin.openLock(successCallback, errorCallback);

**示例**

```
onDeviceReady: function() {
​```
    ordinartPlugin.openSerialPort(["/dev/ttyS1", 9600], function(success) {
        alert(success);
        ordinartPlugin.readSerial(function(data){
            alert(data);
        }, function(error) {
            alert(error);
        })
    }, function(error) {
        alert(error);
    });
​```
},
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