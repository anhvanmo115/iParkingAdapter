package com.viettel.iParkingAdapter.message;

import java.io.Serializable;

public class ConfigurationCommand implements Serializable {
    private String protocolVersion;
    private String functionCode;
    private String terminalId;
    private String messageId;
    private String dataLength;
    private String newId;
    private String timelyReportInterval;
    private String reserved1;
    private String sampleInterval;
    private String ipAdress;
    private String reserved2;
    private String port;
    private String crc;

    public ConfigurationCommand(String protocolVersion, String functionCode, String terminalId, String messageId, String dataLength, String newId, String timelyReportInterval, String reserved1, String sampleInterval, String ipAdress, String reserved2, String crc, String port) {
        this.protocolVersion = protocolVersion;
        this.functionCode = functionCode;
        this.terminalId = terminalId;
        this.messageId = messageId;
        this.dataLength = dataLength;
        this.newId = newId;
        this.timelyReportInterval = timelyReportInterval;
        this.reserved1 = reserved1;
        this.sampleInterval = sampleInterval;
        this.ipAdress = ipAdress;
        this.reserved2 = reserved2;
        this.crc = crc;
        this.port = port;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public String getPort() {
        return port;
    }
    public void setPort() {
        this.port = port;
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

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTimelyReportInterval() {
        return timelyReportInterval;
    }

    public void setTimelyReportInterval(String timelyReportInterval) {
        this.timelyReportInterval = timelyReportInterval;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getSampleInterval() {
        return sampleInterval;
    }

    public void setSampleInterval(String sampleInterval) {
        this.sampleInterval = sampleInterval;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "ConfigurationCommand{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", dataLength='" + dataLength + '\'' +
                ", newId='" + newId + '\'' +
                ", timelyReportInterval='" + timelyReportInterval + '\'' +
                ", reserved1='" + reserved1 + '\'' +
                ", sampleInterval='" + sampleInterval + '\'' +
                ", ipAdress='" + ipAdress + '\'' +
                ", reserved2='" + reserved2 + '\'' +
                ", port='" + port + '\'' +
                ", crc='" + crc + '\'' +
                '}';
    }
}
