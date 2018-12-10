package com.viettel.iParkingAdapter.command;

import com.viettel.iParkingAdapter.message.*;
import com.viettel.iParkingAdapter.utils.ByteUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TransformCommand {
    private static Logger logger = LogManager.getLogger(TransformCommand.class);
    public static OriginalMessage transformConf(ConfigurationCommand conf){
        logger.info("transform data");
        //logger.info(conf);
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

    public static OriginalMessage transformReset(ResetCommand rs){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(rs.getProtocolVersion());
        orMsg.setFunctionCode(rs.getFunctionCode());
        orMsg.setTerminalId(rs.getTerminalId());
        orMsg.setMessageId(rs.getMessageId());
        orMsg.setDataLength(rs.getDataLength());
        orMsg.setCrc(rs.getCrc());
        return orMsg;
    }

    public static OriginalMessage transformGuard(GuardCommand gd){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(gd.getProtocolVersion());
        orMsg.setFunctionCode(gd.getFunctionCode());
        orMsg.setTerminalId(gd.getTerminalId());
        orMsg.setMessageId(gd.getMessageId());
        orMsg.setDataLength(gd.getDataLength());
        orMsg.setCrc(gd.getCrc());
        byte[] bytes = ByteUtils.hexStringToByteArray(gd.getData());
        orMsg.setData(bytes);
        return orMsg;
    }

    public static OriginalMessage transformReadBootMsg(ReadBootMsgCommand rbmc){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(rbmc.getProtocolVersion());
        orMsg.setFunctionCode(rbmc.getFunctionCode());
        orMsg.setTerminalId(rbmc.getTerminalId());
        orMsg.setMessageId(rbmc.getMessageId());
        orMsg.setDataLength(rbmc.getDataLength());
        orMsg.setCrc(rbmc.getCrc());
        return orMsg;
    }

    public static OriginalMessage transformRestoreFactory(RestoreFactoryDefaultCommand rfdc){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(rfdc.getProtocolVersion());
        orMsg.setFunctionCode(rfdc.getFunctionCode());
        orMsg.setTerminalId(rfdc.getTerminalId());
        orMsg.setMessageId(rfdc.getMessageId());
        orMsg.setDataLength(rfdc.getDataLength());
        orMsg.setCrc(rfdc.getCrc());
        return orMsg;
    }

    public static OriginalMessage transformSleepMode(EnterSleepModeCommand esmc){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(esmc.getProtocolVersion());
        orMsg.setFunctionCode(esmc.getFunctionCode());
        orMsg.setTerminalId(esmc.getTerminalId());
        orMsg.setMessageId(esmc.getMessageId());
        orMsg.setDataLength(esmc.getDataLength());
        orMsg.setCrc(esmc.getCrc());
        return orMsg;
    }

    public static OriginalMessage transformBGMagneticField(BGMagneticField bgmf){
        logger.info("transform data");
        OriginalMessage orMsg = new OriginalMessage();
        orMsg.setProtocol(bgmf.getProtocolVersion());
        orMsg.setFunctionCode(bgmf.getFunctionCode());
        orMsg.setTerminalId(bgmf.getTerminalId());
        orMsg.setMessageId(bgmf.getMessageId());
        orMsg.setDataLength(bgmf.getDataLength());
        orMsg.setCrc(bgmf.getCrc());
        return orMsg;
    }
}
