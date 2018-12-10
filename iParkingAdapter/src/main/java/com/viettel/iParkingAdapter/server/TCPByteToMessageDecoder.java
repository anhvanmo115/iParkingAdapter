package com.viettel.iParkingAdapter.server;

import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import com.viettel.iParkingAdapter.utils.TCPChannelManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class TCPByteToMessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static Logger logger = LogManager.getLogger(TCPByteToMessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        logger.info("=========================== do decode =============================");
        try {
            OriginalMessage originalMessage = new OriginalMessage();
//            byte[] bytes = byteBuf.array();
            byte[] bytes = ByteBufUtil.getBytes(byteBuf);
            //FOR TEST
//            String hex = ByteUtils.bytesToHex(bytes);
//            if("123456".equals(hex)){
//                logger.info("123456 put on tcp channel manager");
//                TCPChannelManager.getChannels().put("123456",channelHandlerContext);
//            }else{
//                logger.info("device sensor msg. Forward into 123456");
//                ChannelHandlerContext channel = TCPChannelManager.getChannels().get("123456");
//                if(channel != null){
//                    channel.writeAndFlush(byteBuf);
////                    channelHandlerContext.writeAndFlush(byteBuf);
//                }else{
//                    logger.info("channel is not active");
//                }
//            }
//            logger.info("=========================== end code done =============================");
            //END TEST
//            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(ByteUtils.hexStringToByteArray("123456")));

            originalMessage.setProtocol(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, 0, 1), ByteOrder.LITTLE_ENDIAN));
            originalMessage.setFunctionCode(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, 1, 2), ByteOrder.LITTLE_ENDIAN));
            originalMessage.setTerminalId(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, 2, 4), ByteOrder.LITTLE_ENDIAN));
            originalMessage.setMessageId(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, 4, 6), ByteOrder.LITTLE_ENDIAN));
            originalMessage.setDataLength(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, 6, 8), ByteOrder.LITTLE_ENDIAN));
            int dataLength = Integer.parseInt(originalMessage.getDataLength(), 16);
            originalMessage.setData(Arrays.copyOfRange(bytes, 8, 8 + dataLength));
            originalMessage.setCrc(ByteUtils.bytesToHex(Arrays.copyOfRange(bytes, bytes.length - 2, bytes.length), ByteOrder.LITTLE_ENDIAN));
            logger.info(originalMessage);
            logger.info("hex " + ByteUtils.bytesToHex(bytes));
            list.add(originalMessage);
            logger.info("=========================== end code done =============================");
        } catch (Exception e) {
            logger.error("decode byte to message error", e);
        }

    }


}
