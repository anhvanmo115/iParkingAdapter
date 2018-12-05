package com.viettel.iParkingAdapter.message;

public class BootMsgData {

    private String productSn;
    private String deviceType;
    private String hardwareVersion;
    private String softwareVersion;
    private String reserved;
    private String timelyReportInterval;
    private String reserved1;
    private String reserved2;
    private String imei;
    private String imsi;

    @Override
    public String toString() {
        return "BootMsgData{" +
                "productSn='" + productSn + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", hardwareVersion='" + hardwareVersion + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", reserved='" + reserved + '\'' +
                ", timelyReportInterval='" + timelyReportInterval + '\'' +
                ", reserved1='" + reserved1 + '\'' +
                ", reserved2='" + reserved2 + '\'' +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                '}';
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
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

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
