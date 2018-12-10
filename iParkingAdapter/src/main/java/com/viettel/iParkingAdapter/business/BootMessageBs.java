package com.viettel.iParkingAdapter.business;

import com.sun.deploy.util.ArrayUtil;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
import com.viettel.iParkingAdapter.message.BootMsgData;
import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import com.viettel.iParkingAdapter.utils.CheckCRC;
import com.viettel.iParkingAdapter.utils.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.glassfish.jersey.client.ClientResponse;
import org.json.JSONException;
import org.json.JSONObject;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.ByteOrder;
import java.util.Arrays;

public class BootMessageBs extends BaseBusiness{

    private byte[] byteData;

    public BootMessageBs(OriginalMessage originalMessage) {
        super(originalMessage);
    }

    @Override
    public void onProcess() {
        byteData = originalMessage.getData();
       // logger.info(byteData);
        //logger.info(CheckCRC.CRC16(byteData,byteData.length));
        logger.info("decode boot msg data from byte data");
        BootMsgData bootMsgData = decodeData();
        logger.info(bootMsgData);
        //build msg response and send it to device
        OriginalMessage responseMsg = buildResponseMsg();
        logger.info(responseMsg);
        responseDevice(responseMsg);
        logger.info("send data boot to cloud");
        sendDataBootToCloud(originalMessage,bootMsgData);
        //sendDataBootToCloudMQTT(originalMessage,bootMsgData);
    }

    private BootMsgData decodeData(){
        BootMsgData bootMsgData = new BootMsgData();
        bootMsgData.setProductSn(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,0,4), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setDeviceType(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,4,5), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setHardwareVersion(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,5,6), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setSoftwareVersion(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,6,10), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setReserved(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,10,12), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setTimelyReportInterval(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,12,14), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setReserved1(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,14,16), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setReserved2(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,16,18), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setImei(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,18,34), ByteOrder.LITTLE_ENDIAN));
        bootMsgData.setImsi(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,34,50), ByteOrder.LITTLE_ENDIAN));
        return bootMsgData;
    }

    private OriginalMessage buildResponseMsg(){
        OriginalMessage responseMsg = new OriginalMessage(originalMessage);
        responseMsg.setFunctionCode(Constants.IParkingFunctionCode.RESPONSE);

        //set data for response msg
        String errorCode = "00";
        String answeredFunctionCode = Constants.IParkingFunctionCode.BOOT;
        byte[] respByteData = ByteUtils.hexStringToByteArray(errorCode);
        respByteData = ArrayUtils.addAll(respByteData,ByteUtils.hexStringToByteArray(answeredFunctionCode));
        responseMsg.setData(respByteData);

        return responseMsg;
    }

    private void sendDataBootToCloud(OriginalMessage orMsg,BootMsgData bMsg){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target("http://cyan.vietteliot.vn/hooks/restin2/bootEvent");



        JSONObject obj = new JSONObject();

        try {
            obj.put("deviceId",orMsg.getTerminalId());
            obj.put("deviceType",bMsg.getDeviceType());
            obj.put("functionCode",orMsg.getFunctionCode());
            obj.put("hardwareVersion",bMsg.getHardwareVersion());
            obj.put("softwareVersion",bMsg.getSoftwareVersion());
            obj.put("timelyReportInterval",bMsg.getTimelyReportInterval());
            obj.put("imei",bMsg.getImei());
            obj.put("imsi",bMsg.getImsi());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Invocation.Builder invocationBuilder
                = webTarget.request(MediaType.APPLICATION_JSON)
                .header("x-api-key", "5b9f1329b35ad715084fbdec-bkgs8Ee3KxFWikUMkxWLXxxDK5OE6HJc");
        Response response = invocationBuilder.post(Entity.entity(obj.toString(), MediaType.APPLICATION_JSON));

        //
        // logger.info(response);
        if(response.getStatus() == 200){
            logger.info("Send boot msg successful");
        }


    }

    private void sendDataBootToCloudMQTT(OriginalMessage orMsg,BootMsgData bMsg){

    }
}
