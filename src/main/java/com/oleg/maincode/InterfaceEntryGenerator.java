package com.oleg.maincode;


import com.oleg.maincode.entity.InterfaceEntry;
import com.oleg.maincode.enums.IgnoredBlock;
import com.oleg.maincode.enums.InterfaceVersion;
import com.oleg.maincode.helpers.InterfaceEntryParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Made to solve test task
 *
 * @author      Oleg Terendii
 * @version     1
 */
public class InterfaceEntryGenerator {
    /**
     * Parse file to find all legal Interfaces
     *
     * @param fileLocation location of file to parse
     * @return list of interfaces
     */
    public List<InterfaceEntry> parse(String fileLocation) {
        InterfaceEntryParser interfaceEntryParser = new InterfaceEntryParser();
        List<InterfaceEntry> interfaces = new ArrayList<>();
        String fileValue = interfaceEntryParser.readFile(fileLocation);
        for (IgnoredBlock ignoredBlock : IgnoredBlock.values()) {
            fileValue = interfaceEntryParser.removeIgnored(fileValue, ignoredBlock.getName());
        }
        for (InterfaceVersion interfaceVersion : InterfaceVersion.values()) {
            interfaceEntryParser.getInterface(fileValue, interfaceVersion.getVersion(), interfaces);
        }
        return interfaces;
    }
}