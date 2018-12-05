package com.viettel.iParkingAdapter.server;

import com.viettel.iParkingAdapter.business.BaseBusiness;
import com.viettel.iParkingAdapter.business.BootMessageBs;
import com.viettel.iParkingAdapter.business.TimelyReportBs;
import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.TCPChannelManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.viettel.iParkingAdapter.utils.Constants;

public class TCPServerHandler extends ChannelInboundHandlerAdapter {

    private String deviceId;
    private static Logger logger = LogManager.getLogger(TCPServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("begin handler");
        OriginalMessage originalMessage = (OriginalMessage)msg;
        //put device's channel into list tcp channel
        deviceId = originalMessage.getTerminalId();
        TCPChannelManager.getChannels().putIfAbsent(deviceId,ctx.channel());

        //get business corresponding with message
        BaseBusiness baseBusiness = null;
        switch (originalMessage.getFunctionCode()){
            case Constants.IParkingFunctionCode.BOOT:
                baseBusiness = new BootMessageBs(originalMessage);
                break;
            case Constants.IParkingFunctionCode.TIMELY_REPORT:
                baseBusiness = new TimelyReportBs(originalMessage);
                break;
                default:
                    logger.error(String.format("can not find business corresponding with message {0}, function code {1}",
                            originalMessage.getMessageId(),originalMessage.getFunctionCode()));
        }
        if(baseBusiness != null) {
            baseBusiness.onProcess();
        }
        logger.info("finish handler message id " + originalMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
