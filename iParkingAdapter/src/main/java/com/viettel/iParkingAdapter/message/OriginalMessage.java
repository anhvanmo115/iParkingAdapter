package com.viettel.iParkingAdapter.message;

public class OriginalMessage {

    private String protocol;
    private String functionCode;
    private String terminalId;
    private String messageId;
    private String dataLength;
    private byte[] data;
    private String crc;

    public OriginalMessage() {

    }

    public OriginalMessage(OriginalMessage anotherObject) {
        this.protocol = anotherObject.protocol;
        this.functionCode = anotherObject.functionCode;
        this.terminalId = anotherObject.terminalId;
        this.messageId = anotherObject.messageId;
        this.dataLength = anotherObject.dataLength;
        this.data = anotherObject.data;
        this.crc = anotherObject.crc;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
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
        return "OriginalMessage{" +
                "protocol='" + protocol + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", dataLength='" + dataLength + '\'' +
//                ", data='" + data + '\'' +x
                ", crc='" + crc + '\'' +
                '}';
    }
}
