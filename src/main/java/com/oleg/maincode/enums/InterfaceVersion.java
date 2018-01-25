package com.oleg.maincode.enums;


public enum InterfaceVersion {
    VERSION111("1/1.1"),
    VERSION12("1/2"),
    VERSION13("1/3");
    private String version;

    InterfaceVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
