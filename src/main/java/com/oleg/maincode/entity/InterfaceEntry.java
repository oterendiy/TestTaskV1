package com.oleg.maincode.entity;

import java.net.InetAddress;

public class InterfaceEntry {
    boolean state; // State of interface: up or down.
    String name; // Name of interface.
    String macAddress; // MAC address of interface.
    InetAddress ipAddr; // IP address of interface.
    Integer speed; // Rate of speed in Kbytes. (in config can be represented in KB, MB, GB).
    int startLineNumber; // Number of line where interface section start in “config.txt” file.

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public InetAddress getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(InetAddress ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public int getStartLineNumber() {
        return startLineNumber;
    }

    public void setStartLineNumber(int startLineNumber) {
        this.startLineNumber = startLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterfaceEntry that = (InterfaceEntry) o;

        if (state != that.state) return false;
        if (startLineNumber != that.startLineNumber) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (macAddress != null ? !macAddress.equals(that.macAddress) : that.macAddress != null) return false;
        if (ipAddr != null ? !ipAddr.equals(that.ipAddr) : that.ipAddr != null) return false;
        return speed != null ? speed.equals(that.speed) : that.speed == null;

    }

    @Override
    public int hashCode() {
        int result = (state ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (macAddress != null ? macAddress.hashCode() : 0);
        result = 31 * result + (ipAddr != null ? ipAddr.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + startLineNumber;
        return result;
    }
}

