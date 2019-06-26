package com.zkmeiling.serialport.util;

import com.zkmeiling.serialport.model.Message;
import com.zkmeiling.serialport.model.Received;

import java.io.IOException;
import java.text.ParseException;

/**
 * 处理接收到的参数
 */
public class ParamsDecoderUtil {
    public static Message doDecoder(Received received) throws IOException, ParseException, ArrayIndexOutOfBoundsException {
        Message message = ParamsDecoderUtil.getMessage(received);
        return message;

    }

    public static Message getMessage(Received received) throws IOException, ParseException, ArrayIndexOutOfBoundsException {
        Message readParams = new Message();
        byte[] content = received.getContent();
        readParams.setTemperatureSetting(DataUtil.byte2doubleData(content[1],content[0]));
        readParams.setHighTemperatureWarnDeviation(DataUtil.byte2doubleData(content[3],content[2]));
        readParams.setLowTemperatureWarnDeviation(DataUtil.byte2doubleData(content[5],content[4]));
        String currentTime = DataUtil.byte2intData(content[7], content[6]) + "-" + DataUtil.byte2intData(content[9], content[8]) + "-"
                + DataUtil.byte2intData(content[11], content[10]) + " " + DataUtil.byte2intData(content[13], content[12]) + ":" + DataUtil.byte2intData(content[15], content[14]);
        readParams.setCurrentTime(currentTime);
        readParams.setPrintInterval(DataUtil.byte2intData(content[17], content[16]));
        readParams.setEvnHighTemperatureWarn(DataUtil.byte2doubleData( content[19],content[18]));
        readParams.setDoorHeaterMode(DataUtil.byte2intData(content[21], content[20]));
        readParams.setDisplayMode(DataUtil.byte2intData(content[23], content[22]));
        readParams.setUserMenuPassword(DataUtil.byte2intData(content[25], content[24]));
        readParams.setHardWareVersion(DataUtil.byte2intData(content[27], content[26]));
        readParams.setSoftWareVersion(DataUtil.byte2intData(content[29], content[28]));
        readParams.setConHighTemperatureWarn(DataUtil.byte2doubleData( content[43],content[42]));
        readParams.setControlAlarmRevise(DataUtil.byte2doubleData(content[45], content[44]));
        readParams.setUpperPartTemperatureRevise(DataUtil.byte2doubleData( content[47],content[46]));
        readParams.setLowerPartTemperatureRevise(DataUtil.byte2doubleData( content[49],content[48]));
        readParams.setEvnTemperatureRevise(DataUtil.byte2doubleData( content[51],content[50]));
        readParams.setDefrostTemperatureRevise(DataUtil.byte2doubleData(content[53], content[52]));
        readParams.setConTemperatureRevise(DataUtil.byte2doubleData( content[55],content[54]));
        readParams.setCompressorStartDiff(DataUtil.byte2doubleData( content[57],content[56]));
        readParams.setCompressorStopDiff(DataUtil.byte2doubleData( content[59],content[58]));
        readParams.setCompressorStartInterval(DataUtil.byte2intData(content[61], content[60]));
        readParams.setConCompressorLagTime(DataUtil.byte2intData(content[63], content[62]));
        readParams.setOverTemperatureWarnTime(DataUtil.byte2intData(content[65], content[64]));
        readParams.setOpenDoorWarnTime(DataUtil.byte2intData(content[67], content[66]));
        readParams.setSensorErrorCompressorWorkTime(DataUtil.byte2intData(content[69], content[68]));
        readParams.setSensorErrorCompressorStopTime(DataUtil.byte2intData(content[71], content[70]));
        readParams.setDefrostingCycle(DataUtil.byte2intData(content[73], content[72]));
        readParams.setDefrostingTime(DataUtil.byte2intData(content[75], content[74]));
        readParams.setDoorHeatingTime(DataUtil.byte2intData(content[77], content[76]));
        readParams.setDefrostStopTemp(DataUtil.byte2doubleData(content[79], content[78]));
        readParams.setEvaporatorModel(DataUtil.byte2intData(content[81], content[80]));
        readParams.setEvapFanModel(DataUtil.byte2intData(content[83], content[82]));
        readParams.setHumidityRevise(DataUtil.byte2doubleData(content[85], content[84]));
        readParams.setHumidityMeasureMin(DataUtil.byte2doubleData(content[87], content[86]));
        readParams.setHumidityMeasureMax(DataUtil.byte2doubleData(content[89], content[88]));
        readParams.setHumidityOutOfRange(DataUtil.byte2intData(content[91], content[90]));
        readParams.setBatteryVoltageDetection(DataUtil.byte2intData(content[93], content[92]));
        readParams.setTemperatureDataRecordTime(DataUtil.byte2intData(content[95], content[94]));
        String setRecordingTime = (DataUtil.byte2intData(content[97], content[96])) + "-" + (DataUtil.byte2intData(content[99], content[98])) + "-"
                + (DataUtil.byte2intData(content[101], content[100])) + " " + (DataUtil.byte2intData(content[103], content[102])) + ":" + (DataUtil.byte2intData(content[105], content[104]));
        readParams.setSetRecordingTime(setRecordingTime);
        readParams.setAdminMenuPassword(DataUtil.byte2intData(content[107], content[106]));
        return readParams;
    }

}
