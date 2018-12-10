package com.viettel.iParkingAdapter.business;


import com.viettel.iParkingAdapter.message.BootMsgData;
import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.message.TimelyReportMsg;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.ByteOrder;
import java.util.Arrays;

public class TimelyReportBs extends BaseBusiness {

    private byte[] byteData;

    public TimelyReportBs(OriginalMessage originalMessage) {
        super(originalMessage);
    }

    @Override
    public void onProcess() {
        byteData = originalMessage.getData();
        logger.info("decode timely report msg data");
        TimelyReportMsg timelyReportMsg = decodeData();
        logger.info(timelyReportMsg);
        logger.info("send data timely to cloud");
        sendDataTimelyToCloud(originalMessage,timelyReportMsg);
    }

    private TimelyReportMsg decodeData(){
        TimelyReportMsg msg = new TimelyReportMsg();
        msg.setProductSn(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,0,4), ByteOrder.LITTLE_ENDIAN));
        msg.setTerminerStatus(Arrays.copyOfRange(byteData,4,6));
        msg.setRemainingBattery(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,6,7), ByteOrder.LITTLE_ENDIAN));
        msg.setReserved(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,7,8), ByteOrder.LITTLE_ENDIAN));
        msg.setSignalStrength(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,8,12), ByteOrder.LITTLE_ENDIAN));
        msg.setSignalCoverage(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,12,13), ByteOrder.LITTLE_ENDIAN));
        msg.setSnr(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,13,14), ByteOrder.LITTLE_ENDIAN));
        msg.setComunityPci(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,14,16), ByteOrder.LITTLE_ENDIAN));
        msg.setComunityCellId(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,16,20), ByteOrder.LITTLE_ENDIAN));
        msg.setBgMagneticFieldX(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,20,22), ByteOrder.LITTLE_ENDIAN));
        msg.setBgMagneticFieldY(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,22,24), ByteOrder.LITTLE_ENDIAN));
        msg.setBgMagneticFieldZ(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,24,26), ByteOrder.LITTLE_ENDIAN));
        msg.setCurrentMagneticFieldX(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,26,28), ByteOrder.LITTLE_ENDIAN));
        msg.setCurrentMagneticFieldY(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,28,30), ByteOrder.LITTLE_ENDIAN));
        msg.setCurrentMagneticFieldZ(ByteUtils.bytesToHex(Arrays.copyOfRange(byteData,30,32), ByteOrder.LITTLE_ENDIAN));

        return msg;
    }

    private void sendDataTimelyToCloud(OriginalMessage orMsg, TimelyReportMsg tMsg){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target("http://cyan.vietteliot.vn/hooks/restin2/timelyReportEvent");
        JSONObject obj = new JSONObject();

        try {

            obj.put("functionCode",orMsg.getFunctionCode());
            obj.put("remainingBattery",tMsg.getRemainingBattery());
            obj.put("batteryStatus",tMsg.getTerminerStatus().isBp());
            obj.put("wirelessModule",tMsg.getTerminerStatus().isWm());
            obj.put("guard",tMsg.getTerminerStatus().isSf());
            obj.put("deviceStatus",tMsg.getTerminerStatus().isPs());
            obj.put("signalStrength",tMsg.getSignalStrength());
            obj.put("signalCoverageLevel",tMsg.getSignalCoverage());
            obj.put("snr",tMsg.getSnr());
            obj.put("comunityPci",tMsg.getComunityPci());
            obj.put("deviceId",orMsg.getTerminalId());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Invocation.Builder invocationBuilder
                = webTarget.request(MediaType.APPLICATION_JSON)
                .header("x-api-key", "5b9f1329b35ad715084fbdec-bkgs8Ee3KxFWikUMkxWLXxxDK5OE6HJc");
        Response response = invocationBuilder.post(Entity.entity(obj.toString(), MediaType.APPLICATION_JSON));

        if(response.getStatus() == 200){
            logger.info("Send report successful");
        }



    }
}
