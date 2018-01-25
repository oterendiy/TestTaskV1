package com.oleg.maincode.helpers;


import com.oleg.maincode.entity.InterfaceEntry;
import com.oleg.maincode.enums.Field;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Parse diferent part of request-file
 *
 * @author      Oleg Terendii
 * @version     1
 */
public class InterfaceEntryParser {
    /**
     * Read file
     *
     * @param fileLocation path to file
     * @return file as string
     */
    public String readFile(String fileLocation) {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Get instance from string
     *
     * @param value            value to pars
     * @param interfaceVersion interface version
     * @param result           colection to fill in
     */
    public void getInterface(String value, String interfaceVersion, List<InterfaceEntry> result) {
        Pattern pattern = Pattern.compile("(?<!^)interface\\s" + interfaceVersion + "\\s\\{(.|\\n)*?\\}");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            result.add(transformToObject(matcher.group()));
        }
    }

    /**
     * Transform string to object
     *
     * @param value value to transform
     */
    public InterfaceEntry transformToObject(String value) {
        InterfaceEntry interfaceEntry = new InterfaceEntry();
        interfaceEntry.setState(getField(value, Field.STATE).equals("up"));
        interfaceEntry.setMacAddress(getField(value, Field.MAC_ADDRESS));
        interfaceEntry.setName(getField(value, Field.NAME));
        String speed = getField(value, Field.SPEED);
        if (speed != null && speed.contains("MB")) {
            interfaceEntry.setSpeed(Integer.parseInt(speed.replace("MB", "").trim()) * 1024);
        } else if (speed != null && speed.contains("KB")) {
            interfaceEntry.setSpeed(Integer.parseInt(speed.replace("KB", "").trim()));
        } else {
            interfaceEntry.setSpeed(0);
        }

        interfaceEntry.setStartLineNumber(getField(value, Field.START_LINE_NUMBER) == null ? 0 : Integer.parseInt(getField(value, Field.START_LINE_NUMBER)));
        try {
            String ipAddr = getField(value, Field.IP_ADDR);
            if (ipAddr != null) {
                interfaceEntry.setIpAddr(InetAddress.getByName(ipAddr));
            }
        } catch (UnknownHostException e) {
            interfaceEntry.setIpAddr(null);
        }
        return interfaceEntry;
    }

    /**
     * Remove ignored part of file
     *
     * @param value            value to pars
     * @param ignoredBlockName name of bloc to ignored
     * @return filtered string
     */
    public String removeIgnored(String value, String ignoredBlockName) {
        String patternText = "(?<!^)" + ignoredBlockName + "\\s\\{(.|\\n)*?\\}";
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            int openTag = 0;
            int closeTag = 0;
            String updatedPatternText = new String(patternText);
            do {
                Pattern innerPattern = Pattern.compile(updatedPatternText);
                Matcher innerMatcher = innerPattern.matcher(value);
                if (innerMatcher.find()) {
                    openTag = innerMatcher.group().replaceAll("[^{]", "").length();
                    closeTag = innerMatcher.group().replaceAll("[^}]", "").length();
                    // count opened and closed tags to find end of element
                    if (openTag > closeTag) {
                        updatedPatternText = updatedPatternText + "(.|\\n)*?\\}";
                    } else {
                        value = value.replace(innerMatcher.group(), "");
                        break;
                    }
                }
            } while (openTag > closeTag);
        }
        return value;
    }


    private String getField(String value, Field field) {
        Pattern pattern = Pattern.compile(field.getIndicatorRegexp());
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String result = matcher.group();
            for (String replacemen : field.getReplacement()) {
                result = result.replace(replacemen, "");
            }
            return result.trim();
        } else {
            return null;
        }
    }
}