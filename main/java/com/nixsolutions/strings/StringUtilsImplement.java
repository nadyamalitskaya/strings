package com.nixsolutions.strings;

import com.nixsolutions.ppp.strings.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilsImplement implements StringUtils {

    public byte[] ip2Bytes(String ip) {
        String[] array = ip.trim().split("\\.");
        Pattern reg = Pattern.compile("[A-z]+");
        Matcher m = reg.matcher(ip);
        if (m.find()) {
            return null;
        }
        if (array.length > 4) {
            return null;
        }
        byte[] arr = new byte[4];
        for (int i = 0; i < array.length; i++) {
            int temp = Integer.parseInt(array[i]);
            if (temp < 0 || temp > 255) {
                return null;
            }
            arr[i] = (byte) temp;
        }
        return arr;
    }

    public String convertIp(String ip) {
        String[] array = ip.trim().split("\\.");
        Pattern regex = Pattern.compile("[A-z]+");
        Matcher m = regex.matcher(ip);
        if (m.find()) {
            return null;
        }
        if (array.length > 4) {
            return null;
        }
        for (int i = 0; i < array.length; i++) {
            int temp = Integer.parseInt(array[i]);

            if (temp < 0 || temp > 255) {
                return null;
            }
            if (array[i].length() < 2) {
                array[i] = "0" + array[i];
            }
            if (array[i].length() < 3) {
                array[i] = "0" + array[i];
            }
        }
        return String.join(".", array);
    }

    public StringBuilder alphabet() {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (int i = 1; i <= 26; i++) {
            if (i % 2 == 0) {
                stringBuilder.append((char) (64 + i));
            } else {
                stringBuilder.append((char) (96 + i));
            }
        }
        return stringBuilder;
    }

    public String[] uri2Array(String uri) {
        String[] result = new String[8];
        String[] patterns = { "://", ":", "@", ":", "/", "?", "#" };
        int count = 0;
        uri = uri.trim();
        String prev = null;
        for (int index = 0; index < patterns.length; index++) {
            if (!uri.contains("@") && "://".equals(prev)) {
                result[index] = null;
                result[index + 1] = null;
                index = index + 2;
            }
            if (!uri.contains(patterns[index])) {
                result[index] = null;
                count++;
            } else {
                String substring = uri
                        .substring(0, uri.indexOf(patterns[index]));
                if (":".equals(prev) && result[index - 1] == null) {
                    result[index - 1] = substring;
                    if (result[index - 1].isEmpty()) {
                        result[index - 1] = null;
                    }
                    result[index] = null;
                } else {
                    result[index] = substring;
                    if (result[index].isEmpty()) {
                        result[index] = null;
                    }
                }
                uri = uri.substring(
                        uri.indexOf(patterns[index]) + patterns[index]
                                .length());
                count = 0;
            }
            prev = patterns[index];
        }
        if (!uri.isEmpty()) {
            result[7 - count] = uri;
        }
        return result;
    }

    public String toCamelCase(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        str = str.trim();
        boolean isUpper = Character.isUpperCase(str.charAt(0));
        str = str.toLowerCase().replaceAll("(\\s|,)+", " ").trim();
        if (isUpper) {
            str = ((Character) str.charAt(0)).toString().toUpperCase() + str
                    .substring(1);
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                continue;
            }
            str = str.replaceFirst("\\s.",
                    ((Character) str.charAt(i + 1)).toString().toUpperCase());
        }
        return str;
    }

    public String fromCamelCase(String camelStr) {
        if (camelStr == null || camelStr.equals("")) {
            return "";
        }
        camelStr = camelStr.trim();
        for (int i = 1; i < camelStr.length(); i++) {
            if (!Character.isUpperCase(camelStr.charAt(i))) {
                continue;
            }

            camelStr = camelStr
                    .replace(((Character) camelStr.charAt(i)).toString(),
                            (" " + ((Character) (camelStr.charAt(i))).toString()
                                    .toLowerCase()));
        }
        return camelStr;
    }
}
