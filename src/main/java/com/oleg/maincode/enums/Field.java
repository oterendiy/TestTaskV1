package com.oleg.maincode.enums;

public enum Field {
    STATE("state(.*)", "state"),
    NAME("name(.*)", "name"),
    MAC_ADDRESS("mac(.*)", "mac", "-", "address"),
    IP_ADDR("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"),
    SPEED("speed(.*)", "speed"),
    START_LINE_NUMBER("start(.*)", "-", "start", "Line", "Number");

    private String indicatorRegexp;
    private String[] replacement;

    Field(String indicatorRegexp, String... replacement) {
        this.indicatorRegexp = indicatorRegexp;
        this.replacement = replacement;
    }

    public String getIndicatorRegexp() {
        return indicatorRegexp;
    }

    public String[] getReplacement() {
        return replacement;
    }
}