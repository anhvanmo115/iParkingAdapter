package com.viettel.iParkingAdapter.business;


import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.TCPChannelManager;
import io.netty.channel.Channel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class BaseBusiness {

    private Channel channel;
    protected OriginalMessage originalMessage;

    protected Logger logger = LogManager.getLogger(this.getClass());
    public BaseBusiness(OriginalMessage originalMessage) {
        this.originalMessage = originalMessage;
    }

    //processing method corresponding with each business
    public abstract void onProcess();

    public void responseDevice(OriginalMessage responseMsg){
        channel = TCPChannelManager.getChannels().get(responseMsg.getTerminalId());
        if(channel != null){
            logger.info("send response into device id = " + responseMsg.getTerminalId());
            if(channel.isActive() && channel.isWritable()){
                channel.writeAndFlush(responseMsg);
            }else{
                logger.error("channel is not active, can not send to device id = " + responseMsg.getTerminalId());
            }
        }
    }
}
