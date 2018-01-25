package com.oleg.maincode.enums;

public enum IgnoredBlock {
    GLOBAL_SETTINGS_SECTION("global-settings-section"),
    FDB_SETTINGS_SECTION("fdb-settings-section"),
    TRASH_SECTION_DO_NOT_PARSE("trash-section-do-not-parse");
    private String name;

    IgnoredBlock(String version) {
        this.name = version;
    }

    public String getName() {
        return name;
    }
}