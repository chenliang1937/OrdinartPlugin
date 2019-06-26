var ordinartPlugin = {
    /** 打开串口 */
    openSerialPort: function (arg0, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'openSerialPort',
            arg0
        );
    },

    /** 关闭串口 */
    closeSerialPort: function (successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'closeSerialPort',
            []
        );
    },

    /** 发送串口指令 */
    sendSerialPort: function (arg0, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'sendSerialPort',
            [arg0]
        );
    },

    /** 读取串口数据 */
    readSerial: function (successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'readSerial',
            []
        );
    },

    /** 反馈主控板 */
    sendDataInfo: function (successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'sendDataInfo',
            []
        );
    },

    /** 读取参数 */
    sendParamInfo: function (successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'sendParamInfo',
            []
        );
    },

    /** 开电磁锁 */
    openLock: function (successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'OrdinartPlugin',
            'openLock',
            []
        );
    }
};

module.exports = ordinartPlugin;