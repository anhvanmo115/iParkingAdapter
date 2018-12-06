package com.viettel.iParkingAdapter.command;

import com.viettel.iParkingAdapter.message.ConfigurationCommand;
import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TransformCommand {
    private static Logger logger = LogManager.getLogger(TransformCommand.class);
    public static OriginalMessage transformConf(ConfigurationCommand conf){
        logger.info("transform data");
        logger.info(conf);
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(conf.getProtocolVersion());
        orMsg.setFunctionCode(conf.getFunctionCode());
        orMsg.setTerminalId(conf.getTerminalId());
        orMsg.setMessageId(conf.getMessageId());
        orMsg.setDataLength(conf.getDataLength());
        orMsg.setCrc(conf.getCrc());
        byte[] bytes = ByteUtils.hexStringToByteArray(conf.getNewId());
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getTimelyReportInterval()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getReserved1()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getSampleInterval()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getIpAdress()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getPort()));
        bytes = ArrayUtils.addAll(bytes,ByteUtils.hexStringToByteArray(conf.getReserved2()));
        orMsg.setData(bytes);
        return orMsg;
    }
}
