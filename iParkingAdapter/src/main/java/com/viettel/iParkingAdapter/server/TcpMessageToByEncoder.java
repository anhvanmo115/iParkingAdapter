package com.viettel.iParkingAdapter.server;

import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class TcpMessageToByEncoder extends MessageToMessageEncoder<OriginalMessage> {

    private static Logger logger = LogManager.getLogger(TcpMessageToByEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OriginalMessage orgMsg, List<Object> list) throws Exception {
        logger.info("=========================== do code done =============================");
        logger.info(orgMsg);
        byte[] bytes = ByteUtils.hexStringToByteArray(orgMsg.getProtocol());
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(orgMsg.getFunctionCode()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(orgMsg.getTerminalId()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(orgMsg.getMessageId()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(orgMsg.getDataLength()));
        bytes = ArrayUtils.addAll(bytes,orgMsg.getData());
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(orgMsg.getCrc()));
        list.add(bytes);
        logger.info("=========================== end code done =============================");
    }

}
