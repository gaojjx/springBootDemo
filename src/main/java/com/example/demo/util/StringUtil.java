package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil() {
    }

    public static StringUtil getInstance() {
        return SingletonHolder.INSTANT;
    }

    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public boolean isNotBlank(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        return true;
    }

    /**
     * 驼峰转下划线userGroup ---> user_group
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 下划线转驼峰user_group ---> UserGroup
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || param.length() < 1 || param.indexOf("_") < 0) {
            if (param == null || param.length() < 1) {
                return param;
            }
            return firstToUpper(param.toLowerCase());
        }
        String[] arr = param.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(firstToUpper(arr[i].toLowerCase()));
        }
        return sb.toString();
    }

    /**
     * Description: String replace(String s, String strold, String strNew) Replace all strold with strNew in s
     *
     * @param : s String The String to be disposal. String strold String strNew
     * @return : A new string.
     */
    public String replace(String s, String find, String replacewith) {
        String[] chips = split(s, find);
        StringBuffer sreturn = new StringBuffer();
        int len = chips.length;
        if (len < 1) {
            return "";
        }
        sreturn.append(chips[0]);
        for (int i = 1; i < len; i++) {
            sreturn.append(replacewith);
            sreturn.append(chips[i]);
        }
        return sreturn.toString();
    }

    /**
     * Description: Returns a zero-based, one-dimensional array containing a specified number of substrings
     *
     * @param : s String The String to be disposal. strsplit String The split-string.
     * @return : A new string.
     */
    public String[] split(String s, String token) {
        List<String> chips = new ArrayList<String>();
        int len = token.length();
        int form = 0;
        int start;
        while (s.indexOf(token, form) >= form) {
            start = s.indexOf(token, form);
            if (start == form) {
                chips.add("");
            } else {
                chips.add(s.substring(form, start));
            }
            form = start + len;
        }
        if (form < s.length()) {
            chips.add(s.substring(form));
        } else {
            chips.add("");
        }
        return (String[]) chips.toArray();
    }

    /**
     * 字符串前面补足位数
     *
     * @param str
     * @param addedStr  补充的字符
     * @param strLength 字符长度
     * @return
     */
    public String perAddStr(String str, char addedStr, int strLength) {
        if (str == null || str.length() < 1)
            return null;
        if (str.length() >= strLength) {
            return str;
        }
        int count = strLength - str.length();
        for (int i = 0; i < count; i++) {
            str = String.valueOf(addedStr) + str;
        }
        return str;
    }

    public String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;
                    try {
                        b = Character.toString(c).getBytes("utf-8");
                    } catch (Exception ex) {
                        System.out.println(ex);
                        b = new byte[0];
                    }
                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        if (k < 0)
                            k += 256;
                        sb.append("%" + Integer.toHexString(k).toUpperCase());
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sb.delete(0, sb.length());
            sb.setLength(0);
            sb = null;
        }
    }

    public static String firstToUpper(String str) {
        try {
            if (str == null) {
                return str;
            }
            if (str.length() < 1)
                return str;
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String firstToLower(String str) {
        try {
            if (str == null) {
                return str;
            }
            if (str.length() < 1) {
                return str;
            }
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getBefore(String str, String key, String noKeyReturn) {
        try {
            if (str == null || str.length() < 1) {
                System.out.println("str is null || str.length() < 1,return!");
                return null;
            }
            if (key == null || key.length() < 1) {
                System.out.println("key is null || key.length() < 1,return!");
                return null;
            }
            int index = str.indexOf(key);
            if (index < 0) {
                System.out.println("indexOf < 0,return noKeyReturn!");
                return noKeyReturn;
            }
            return str.substring(0, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAfter(String str, String key, String noKeyReturn) {
        try {
            if (str == null || str.length() < 1) {
                System.out.println("str is null || str.length() < 1,return!");
                return null;
            }
            if (key == null || key.length() < 1) {
                System.out.println("key is null || key.length() < 1,return!");
                return null;
            }
            int index = str.indexOf(key);
            if (index < 0) {
                System.out.println("indexOf < 0,return noKeyReturn!");
                return noKeyReturn;
            }
            return str.substring(index + key.length());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getBetween(String str, String keyStart, String keyEnd, String noKeyReturn) {
        try {
            if (str == null || str.length() < 1) {
                System.out.println("str is null || str.length() < 1,return!");
                return null;
            }
            if (keyStart == null || keyStart.length() < 1) {
                System.out.println("keyStart is null || keyStart.length() < 1,return!");
                return null;
            }
            if (keyEnd == null || keyEnd.length() < 1) {
                System.out.println("keyEnd is null || keyEnd.length() < 1,return!");
                return null;
            }
            int indexStart = str.indexOf(keyStart);
            int indexEnd = str.indexOf(keyEnd);
            if (indexStart < 0) {
                System.out.println("keyStart indexOf < 0,return noKeyReturn!");
                return noKeyReturn;
            }
            if (indexEnd < 0) {
                System.out.println("keyEnd indexOf < 0,return noKeyReturn!");
                return noKeyReturn;
            }
            if (indexStart > indexEnd) {
                System.out.println("keyEnd'index < keyStart'index,return noKeyReturn!");
                return noKeyReturn;
            }
            return str.substring(indexStart + keyStart.length(), indexEnd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String replaceContentBetweenStartAndEnd(String str, String newStr, String keyStart, String keyEnd, String errorStr) {
        try {
            if (str == null || str.length() < 1) {
                System.out.println("str is null || str.length() < 1,return!");
                return null;
            }
            if (keyStart == null || keyStart.length() < 1) {
                System.out.println("keyStart{=" + keyStart + "} is null || keyStart.length() < 1,return!");
                return null;
            }
            if (keyEnd == null || keyEnd.length() < 1) {
                System.out.println("keyEnd{=" + keyEnd + "} is null || keyEnd.length() < 1,return!");
                return null;
            }
            if (newStr == null) {
                System.out.println("newStr is null ,return!");
                return null;
            }
            String before = getBefore(str, keyStart, null);
            String after = getAfter(str, keyEnd, null);
            if (before == null) {
                System.out.println("get before string error ,return!");
                return errorStr;
            }
            if (after == null) {
                System.out.println("get after string error ,return!");
                return errorStr;
            }
            return before + newStr + after;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转化为16进制
     *
     * @param str
     * @return
     */
    public String str2HexString(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] b = str.getBytes();
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toLowerCase();
        }
        return ret;
    }

    /**
     * 16进制字符串转为字符串
     *
     * @param hexStr
     * @return
     */
    public String hexStr2Str(String hexStr) {
        byte[] baKeyword = new byte[hexStr.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new String(baKeyword);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * 屏蔽email部分字符串
     *
     * @param email
     * @return
     */
    public String subStrEmail(String email) {
        //获得email中@前字符串的长度
        int num = email.substring(0, email.indexOf("@")).length();
        //获取@之前的字符串
        String temp = email.substring(0, email.indexOf("@"));
        //获取@之后的字符串
        String last = email.substring(email.indexOf("@"), email.length());
        StringBuffer tempEmail = new StringBuffer();
        //如果字符串长度《=3，截取最后一位，转换为*
        if (num <= 3) {
            char[] chs = temp.toCharArray();
            for (int i = 0; i < num; i++) {
                if (i == num - 1) {
                    tempEmail.append("*");
                } else {
                    tempEmail.append(String.valueOf(chs[i]));
                }
            }
        }
        if (num >= 4) {
            char[] chs = temp.toCharArray();
            for (int i = 0; i < num; i++) {
                if (i == 3 || i == 4) {
                    tempEmail.append("*");
                } else {
                    tempEmail.append(String.valueOf(chs[i]));
                }
            }
        }
        tempEmail.append(last);
        return tempEmail.toString();
    }

    private static class SingletonHolder {

        private final static StringUtil INSTANT = new StringUtil();
    }
}
