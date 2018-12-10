package com.viettel.iParkingAdapter.message;

import java.io.Serializable;

public class ReadBootMsgCommand implements Serializable {
    private String protocolVersion;
    private String functionCode;
    private String terminalId;
    private String messageId;
    private String dataLength;
    private String crc;

    public ReadBootMsgCommand(String protocolVersion, String functionCode, String terminalId, String messageId, String dataLength, String crc) {
        this.protocolVersion = protocolVersion;
        this.functionCode = functionCode;
        this.terminalId = terminalId;
        this.messageId = messageId;
        this.dataLength = dataLength;
        this.crc = crc;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "ReadBootMsgCommand{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", dataLength='" + dataLength + '\'' +
                ", crc='" + crc + '\'' +
                '}';
    }
}
