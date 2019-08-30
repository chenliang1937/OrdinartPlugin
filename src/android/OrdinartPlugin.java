package com.zkmeiling.serialport;

import android.util.Log;

import com.google.gson.Gson;
import com.zkmeiling.serialport.model.Message;
import com.zkmeiling.serialport.model.Received;
import com.zkmeiling.serialport.model.ReadParams;
import com.zkmeiling.serialport.util.CrcUtil;
import com.zkmeiling.serialport.util.MessageDecoderUtil;
import com.zkmeiling.serialport.util.ParamsDecoderUtil;
import com.zkmeiling.serialport.util.ParamsEncoderUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

/**
 * @author: Chenl
 * @date: 2019/6/14
 * @desc:
 */
public class OrdinartPlugin extends CordovaPlugin {

    private final String TAG = "OrdinartPlugin";

    public SerialPort serialPort = null;
    public InputStream inputStream = null;
    public OutputStream outputStream = null;
    private boolean isSerialClose = false;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "openSerialPort":
                String path = args.getString(0);
                int baudrate = args.getInt(1);
                openSerialPort(path, baudrate, callbackContext);
                return true;
            case "closeSerialPort":
                closeSerialPort(callbackContext);
                return true;
            case "sendSerialPort":
                String sendData = args.getString(0);
                sendSerialPort(sendData, callbackContext);
                return true;
            case "readSerial":
                readSerial(callbackContext);
                return true;
            case "sendDataInfo":
                sendDataInfo(callbackContext);
                return true;
            case "sendParamInfo":
                sendParamInfo(callbackContext);
                return true;
            case "openLock":
                openLock(callbackContext);
                return true;
            case "openLed":
                openLed(callbackContext);
                return true;
            default:
                callbackContext.error("error");
                return false;
        }
    }

    /**
     * 打开串口
     * @return serialPort串口对象
     */
    public SerialPort openSerialPort(String path, int baudrate, CallbackContext callbackContext){
        try {
            serialPort = new SerialPort(new File(path),baudrate,0);

            //获取打开的串口中的输入输出流，以便于串口数据的收发
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "openSerialPort: 打开串口异常：" + e.toString());
            callbackContext.error("open serialport error");
        }catch (SecurityException e) {
            Log.e(TAG, "openSerialPort: 没有串口读写权限：" + e.toString());
            callbackContext.error("no read-write permission");
        }
        callbackContext.success("open serialport success");
        return serialPort;
    }

    /**
     * 关闭串口
     * @param callbackContext
     */
    public void closeSerialPort(CallbackContext callbackContext){
        try {
            isSerialClose = true;
            inputStream.close();
            outputStream.close();

            serialPort.close();
        } catch (IOException e) {
            Log.e(TAG, "closeSerialPort: 关闭串口异常："+e.toString());
            callbackContext.error("close serialport error");
            return;
        }
        callbackContext.success("close serialport success");
    }

    /**
     * 发送串口指令（字符串）
     * @param data String数据指令
     */
    public void sendSerialPort(String data, CallbackContext callbackContext){
        try {
            ReadParams sendParams = new Gson().fromJson(data, ReadParams.class);

            byte[] sendData = ParamsEncoderUtil.doEncoder(sendParams);
            if (sendData.length > 0) {
                outputStream.write(sendData);
                outputStream.flush();
                Log.d(TAG, "sendSerialPort: 串口数据发送成功");
                callbackContext.success("send data success");
            }
        } catch (IOException e) {
            Log.e(TAG, "sendSerialPort: 串口数据发送失败："+e.toString());
            callbackContext.error("send data error");
        }
    }

    /**
     * 发送串口指令（byte数组）
     * @param sendData
     * @param callbackContext
     */
    public void sendSerialPort(byte[] sendData, CallbackContext callbackContext){

        try {
            if (sendData.length > 0) {
                outputStream.write(sendData);
                Log.d(TAG, "sendSerialPort: 串口数据发送成功");
                callbackContext.success("send data success");
            }
        } catch (IOException e) {
            Log.e(TAG, "sendSerialPort: 串口数据发送失败："+e.toString());
            callbackContext.error("send data error");
        }
    }

    public void readSerial(CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                while(!isSerialClose) {
                    try {
                        if (inputStream.available() <= 31) {
                            Thread.sleep(200);
                        } else {
                            Thread.sleep(100);
                            onDataReceived(inputStream, callbackContext);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        callbackContext.error("read serialport data error");
                        return;
                    }
                }
            }
        });
    }

    private void onDataReceived(InputStream inputStream, CallbackContext callbackContext) {
        try {
            Received received = new Received();
            while (true) {
                int head = inputStream.read();
                if (head == 0x5a) {
                    break;
                }
            }
            int type = inputStream.read();
            int length = inputStream.read();

            byte[] content = new byte[length + 1];
            inputStream.read(content);
            int crcread = inputStream.read();

            received.setLength(length);
            received.setType(type);
            received.setCrcCode(crcread);
            received.setContent(content);
            
            int docrc = CrcUtil.doCrc(received.getContent(), received);

            if (crcread != docrc) {
                Message message = new Message();
                message.setSourceDataState(99);
                message.setSourceDataStateDesc("原始数据CRC校验错误");
//                onDataReceiveListener.onDataReceive(message);
                PluginResult crcError = new PluginResult(PluginResult.Status.OK, new Gson().toJson(message));
                crcError.setKeepCallback(true);
                callbackContext.sendPluginResult(crcError);
                return;
            }
            switch (received.getType()) {
                case 6:
                    Message message = MessageDecoderUtil.getMessage(received);
                    message.setSourceDataState(1);
                    message.setSourceDataStateDesc("原始数据正常");
                    message.setType(6);
//                    onDataReceiveListener.onDataReceive(message);
                    PluginResult runningResult = new PluginResult(PluginResult.Status.OK, new Gson().toJson(message));
                    runningResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(runningResult);
                    break;
                case 5:
                    Message readParams = ParamsDecoderUtil.doDecoder(received);
                    readParams.setType(5);
                    readParams.setSourceDataState(1);
//                    onDataReceiveListener.onDataReceive(readParams);
                    PluginResult paramResult = new PluginResult(PluginResult.Status.OK, new Gson().toJson(readParams));
                    paramResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(paramResult);
                    sendDataInfo(callbackContext);
                    break;
                default:
                    Log.e(TAG, "发送数据类型错误");
//                    onDataReceiveListener.onDataReceive(null);
                    PluginResult typeError = new PluginResult(PluginResult.Status.ERROR, "type error");
                    typeError.setKeepCallback(true);
                    callbackContext.sendPluginResult(typeError);
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "run: 数据读取异常：" + e.toString());
        }
    }

    /**
     * 反馈主控板
     * @param callbackContext
     */
    public void sendDataInfo(CallbackContext callbackContext) {
        try {
            Received received = new Received();
            received.setType(0x04);
            received.setLength(3);
            byte[] bytes1 = new byte[4];
            bytes1[0] = 0;
            bytes1[1] = 0;
            bytes1[2] = 0;
            bytes1[3] = 0;

            int crc8 = CrcUtil.sendInfoDoCrc(bytes1, received);

            byte[] mBuffer = new byte[8];
            mBuffer[0] = (byte) 0xA5;
            mBuffer[1] = 0x04;
            mBuffer[2] = 3;
            mBuffer[3] = 0;
            mBuffer[4] = 0;
            mBuffer[5] = 0;
            mBuffer[6] = 0;
            mBuffer[7] = (byte) crc8;
            if (outputStream != null) {
                outputStream.write(mBuffer);
                callbackContext.success("sendDataInfo success");
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error("sendDataInfo error");
        }
    }

    /**
     * 读取参数
     * @param callbackContext
     */
    public void sendParamInfo(CallbackContext callbackContext) {
        try {
            Received received = new Received();
            received.setType(0x02);
            received.setLength(0);
            byte[] bytes1 = new byte[1];
            bytes1[0] = 0;
            int crc8 = CrcUtil.sendInfoDoCrc(bytes1, received);

            byte[] mBuffer = new byte[5];
            mBuffer[0] = (byte) 0xA5;
            mBuffer[1] = 0x02;
            mBuffer[2] = 0;
            mBuffer[3] = 0;
            mBuffer[4] = (byte) crc8;
            if (outputStream != null) {
                outputStream.write(mBuffer);
                callbackContext.success("sendParamInfo success");
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error("sendParamInfo error");
        }
    }

    /**
     * 开电磁锁
     * @param callbackContext
     */
    public void openLock(CallbackContext callbackContext) {
        try {
            Received received = new Received();
            received.setType(0x04);
            received.setLength(3);
            byte[] bytes1 = new byte[4];
            bytes1[0] = 1;
            bytes1[1] = 0;
            bytes1[2] = 0;
            bytes1[3] = 0;

            int crc8 = CrcUtil.sendInfoDoCrc(bytes1, received);

            byte[] mBuffer = new byte[8];
            mBuffer[0] = (byte) 0xA5;
            mBuffer[1] = 0x04;
            mBuffer[2] = 3;
            mBuffer[3] = 1;
            mBuffer[4] = 0;
            mBuffer[5] = 0;
            mBuffer[6] = 0;
            mBuffer[7] = (byte) crc8;
            if (outputStream != null) {
                outputStream.write(mBuffer);
                callbackContext.success("open lock success");
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error("open lock error");
        }
    }

    /**
     * 开led灯
     * @param callbackContext
     */
    public void openLed(CallbackContext callbackContext) {
        try {
            Received received = new Received();
            received.setType(0x04);
            received.setLength(3);
            byte[] bytes1 = new byte[4];
            bytes1[0] = (byte) 0x80;
            bytes1[1] = 0;
            bytes1[2] = 0;
            bytes1[3] = 0;

            int crc8 = CrcUtil.sendInfoDoCrc(bytes1, received);

            byte[] mBuffer = new byte[8];
            mBuffer[0] = (byte) 0xA5;
            mBuffer[1] = 0x04;
            mBuffer[2] = 3;
            mBuffer[3] = (byte) 0x80;
            mBuffer[4] = 0;
            mBuffer[5] = 0;
            mBuffer[6] = 0;
            mBuffer[7] = (byte) crc8;
            if (outputStream != null) {
                outputStream.write(mBuffer);
                callbackContext.success("open led success");
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error("open led error");
        }
    }

}
