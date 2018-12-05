package com.viettel.iParkingAdapter.message;

import com.viettel.iParkingAdapter.utils.ByteUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class TimelyReportMsg {

    private String productSn;
    private TerminalStatus terminerStatus;
    private String remainingBattery;
    private String reserved;
    private String signalStrength;
    private String signalCoverage;
    private String snr;
    private String comunityPci;
    private String comunityCellId;
    private String BgMagneticFieldX;
    private String BgMagneticFieldY;
    private String BgMagneticFieldZ;
    private String CurrentMagneticFieldX;
    private String CurrentMagneticFieldY;
    private String CurrentMagneticFieldZ;

    @Override
    public String toString() {
        return "TimelyReportMsg{" +
                "productSn='" + productSn + '\'' +
                ", terminerStatus='" + terminerStatus + '\'' +
                ", remainingBattery='" + remainingBattery + '\'' +
                ", reserved='" + reserved + '\'' +
                ", signalStrength='" + signalStrength + '\'' +
                ", signalCoverage='" + signalCoverage + '\'' +
                ", snr='" + snr + '\'' +
                ", comunityPci='" + comunityPci + '\'' +
                ", comunityCellId='" + comunityCellId + '\'' +
                ", BgMagneticFieldX='" + BgMagneticFieldX + '\'' +
                ", BgMagneticFieldY='" + BgMagneticFieldY + '\'' +
                ", BgMagneticFieldZ='" + BgMagneticFieldZ + '\'' +
                ", CurrentMagneticFieldX='" + CurrentMagneticFieldX + '\'' +
                ", CurrentMagneticFieldY='" + CurrentMagneticFieldY + '\'' +
                ", CurrentMagneticFieldZ='" + CurrentMagneticFieldZ + '\'' +
                '}';
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getRemainingBattery() {
        return remainingBattery;
    }

    public void setRemainingBattery(String remainingBattery) {
        this.remainingBattery = remainingBattery;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(String signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getSignalCoverage() {
        return signalCoverage;
    }

    public void setSignalCoverage(String signalCoverage) {
        this.signalCoverage = signalCoverage;
    }

    public String getSnr() {
        return snr;
    }

    public void setSnr(String snr) {
        this.snr = snr;
    }

    public String getComunityPci() {
        return comunityPci;
    }

    public void setComunityPci(String comunityPci) {
        this.comunityPci = comunityPci;
    }

    public String getComunityCellId() {
        return comunityCellId;
    }

    public void setComunityCellId(String comunityCellId) {
        this.comunityCellId = comunityCellId;
    }

    public String getBgMagneticFieldX() {
        return BgMagneticFieldX;
    }

    public void setBgMagneticFieldX(String bgMagneticFieldX) {
        BgMagneticFieldX = bgMagneticFieldX;
    }

    public String getBgMagneticFieldY() {
        return BgMagneticFieldY;
    }

    public void setBgMagneticFieldY(String bgMagneticFieldY) {
        BgMagneticFieldY = bgMagneticFieldY;
    }

    public String getBgMagneticFieldZ() {
        return BgMagneticFieldZ;
    }

    public void setBgMagneticFieldZ(String bgMagneticFieldZ) {
        BgMagneticFieldZ = bgMagneticFieldZ;
    }

    public String getCurrentMagneticFieldX() {
        return CurrentMagneticFieldX;
    }

    public void setCurrentMagneticFieldX(String currentMagneticFieldX) {
        CurrentMagneticFieldX = currentMagneticFieldX;
    }

    public String getCurrentMagneticFieldY() {
        return CurrentMagneticFieldY;
    }

    public void setCurrentMagneticFieldY(String currentMagneticFieldY) {
        CurrentMagneticFieldY = currentMagneticFieldY;
    }

    public String getCurrentMagneticFieldZ() {
        return CurrentMagneticFieldZ;
    }

    public void setCurrentMagneticFieldZ(String currentMagneticFieldZ) {
        CurrentMagneticFieldZ = currentMagneticFieldZ;
    }

    public TerminalStatus getTerminerStatus() {
        return terminerStatus;
    }

    public void setTerminerStatus(byte[] bytes) {
        this.terminerStatus = new TerminalStatus(bytes);
    }

    public class TerminalStatus{

        private boolean bp;
        private boolean ack;
        private boolean wm;
        private boolean sf;
        private boolean ps;

        public TerminalStatus(boolean bp, boolean ack, boolean wm, boolean sf, boolean ps) {
            this.bp = bp;
            this.ack = ack;
            this.wm = wm;
            this.sf = sf;
            this.ps = ps;
        }

        public TerminalStatus(byte[] bytes){
            ByteBuffer wrapped = ByteBuffer.wrap(bytes);
            wrapped.order(ByteOrder.LITTLE_ENDIAN);
            short b = wrapped.getShort();
            ps = (b & 16) == 16;
            sf = (b & 8) == 8;
            wm = (b & 4) == 4;
            sf = (b & 2) == 2;
            bp = (b & 1) == 1;
        }

        @Override
        public String toString() {
            return "TerminalStatus{" +
                    "bp=" + bp +
                    ", ack=" + ack +
                    ", wm=" + wm +
                    ", sf=" + sf +
                    ", ps=" + ps +
                    '}';
        }

        public boolean isBp() {
            return bp;
        }

        public void setBp(boolean bp) {
            this.bp = bp;
        }

        public boolean isAck() {
            return ack;
        }

        public void setAck(boolean ack) {
            this.ack = ack;
        }

        public boolean isWm() {
            return wm;
        }

        public void setWm(boolean wm) {
            this.wm = wm;
        }

        public boolean isSf() {
            return sf;
        }

        public void setSf(boolean sf) {
            this.sf = sf;
        }

        public boolean isPs() {
            return ps;
        }

        public void setPs(boolean ps) {
            this.ps = ps;
        }

    }
    public static void main(String[] args) {
        String hex = "001F";
        byte[] bytes = ByteUtils.hexStringToByteArray(hex);
        TimelyReportMsg msg = new TimelyReportMsg();
        msg.setTerminerStatus(bytes);
        System.out.println(msg);
    }

}
