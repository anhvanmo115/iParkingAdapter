package com.viettel.iParkingAdapter.message;

import java.io.Serializable;

public class EnterSleepModeCommand implements Serializable {
    private String protocolVersion;
    private String functionCode;
    private String terminalId;
    private String messageId;
    private String dataLength;
    private String data;
    private String crc;

    public EnterSleepModeCommand(String protocolVersion, String functionCode, String terminalId, String messageId, String dataLength, String data, String crc) {
        this.protocolVersion = protocolVersion;
        this.functionCode = functionCode;
        this.terminalId = terminalId;
        this.messageId = messageId;
        this.dataLength = dataLength;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "EnterSleepModeCommand{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", dataLength='" + dataLength + '\'' +
                ", data='" + data + '\'' +
                ", crc='" + crc + '\'' +
                '}';
    }
}
