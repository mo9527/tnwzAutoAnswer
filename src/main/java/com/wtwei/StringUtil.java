package com.wtwei;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(Object... objs) {
        if(objs == null) {
            return true;
        } else {
            Object[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                if(obj != null && !"".equals(obj)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isNotEmpty(Object... objs) {
        if(objs == null) {
            return false;
        } else {
            Object[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                if(obj == null || "".equals(obj)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String replaceNull(Object obj) {
        return isEmpty(new Object[]{obj})?"":obj.toString();
    }

    public static String replaceNull(Object obj, String strDefault) {
        return isEmpty(new Object[]{obj})?strDefault:obj.toString();
    }

    public static String replaceAll(String src, String match, String as) {
        return !isEmpty(new Object[]{src}) && !isEmpty(new Object[]{match})?src.replace(match, as):src;
    }

    public static String replaceFirst(String src, String match, String as) {
        if(!isEmpty(new Object[]{src}) && !isEmpty(new Object[]{match})) {
            int start = src.indexOf(match);
            return start != -1?(new StringBuilder(src)).replace(start, start + match.length(), replaceNull(as)).toString():src;
        } else {
            return src;
        }
    }

    public static String reverse(String src) {
        return isEmpty(new Object[]{src})?src:(new StringBuilder(src)).reverse().toString();
    }

    public static String replaceLast(String src, String match, String as) {
        if(!isEmpty(new Object[]{src}) && !isEmpty(new Object[]{match})) {
            src = reverse(src);
            match = reverse(match);
            as = reverse(as);
            return reverse(replaceFirst(src, match, as));
        } else {
            return src;
        }
    }


    public static List<String> splitAsList(String src, String delim) {
        Object lstRet = new ArrayList();
        if(isNotEmpty(new Object[]{src})) {
            if(src.indexOf(delim) == 0) {
                src = replaceFirst(src, delim, "");
            }

            if(src.lastIndexOf(delim) == src.length() - 1) {
                src = replaceLast(src, delim, "");
            }

            delim = ".".equals(delim)?"\\.":delim;
            delim = "|".equals(delim)?"\\|":delim;
            lstRet = Arrays.asList(src.split(delim));
        }
        return (List)lstRet;
    }

    public static String joinList(List lstInput, String delim) {
        if(lstInput != null && lstInput.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator it = lstInput.iterator();

            while(it.hasNext()) {
                sb.append(replaceNull(it.next()));
                if(it.hasNext()) {
                    sb.append(delim);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String joinForSqlIn(List lstInput, String delim) {
        if(lstInput != null && lstInput.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator it = lstInput.iterator();

            while(it.hasNext()) {
                sb.append("\'").append(replaceNull(it.next())).append("\'");
                if(it.hasNext()) {
                    sb.append(delim);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static int trueLength(String src) {
        return trueLength(src, "UTF-8");
    }

    public static int trueLength(String src, String encode) {
        int length = 0;
        if(isNotEmpty(new Object[]{src})) {
            try {
                byte[] e = src.getBytes(encode);
                length = e.length;
            } catch (UnsupportedEncodingException var4) {
                length = -1;
            }
        }

        return length;
    }

    public static List<String> getMatches(String strInput, String regex) {
        ArrayList lstResult = new ArrayList();
        if(isNotEmpty(new Object[]{strInput})) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(strInput);

            while(m.find()) {
                lstResult.add(m.group());
            }
        }

        return lstResult;
    }

    public static String capFirst(String str) {
        return capFirst(str, true);
    }

    public static String capFirst(String str, boolean isUpperCase) {
        return isEmpty(new Object[]{str})?str:(isUpperCase?str.substring(0, 1).toUpperCase() + str.substring(1):str.substring(0, 1).toLowerCase() + str.substring(1));
    }

    public static String camelAndRemoveUnderline(String str) {
        if(isEmpty(new Object[]{str})) {
            return str;
        } else {
            String strRet = str.toLowerCase();
            int underlineLoc = str.indexOf("_");

            while(underlineLoc >= 0 && underlineLoc <= strRet.length() - 1) {
                if(underlineLoc == strRet.length() - 1) {
                    strRet = strRet.substring(0, underlineLoc);
                } else {
                    strRet = strRet.substring(0, underlineLoc) + strRet.substring(underlineLoc + 1, underlineLoc + 2).toUpperCase() + strRet.substring(underlineLoc + 2);
                    underlineLoc = strRet.indexOf("_");
                }
            }

            return strRet;
        }
    }

    public static String underlineAndRemoveCamel(String str) {
        if(isEmpty(new Object[]{str})) {
            return str;
        } else {
            String retStr = "";
            char[] var2 = str.toCharArray();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                char c = var2[var4];
                if(Character.isUpperCase(c)) {
                    retStr = retStr + "_" + (c + "").toLowerCase();
                } else {
                    retStr = retStr + c;
                }
            }

            return retStr;
        }
    }


    public static String bytesToHexString(byte[] bArray, boolean upperCase) {
        StringBuilder sb = new StringBuilder(bArray.length * 2);
        byte[] var4 = bArray;
        int var5 = bArray.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte i = var4[var6];
            String sTemp = Integer.toHexString(255 & i);
            if(sTemp.length() < 2) {
                sb.append(0);
            }

            sb.append(sTemp.toUpperCase());
        }

        return upperCase?sb.toString():sb.toString().toLowerCase();
    }

    public static String bytesToHexString(byte[] bArray) {
        return bytesToHexString(bArray, true);
    }

    public static byte[] hexStringToBytes(String hexStr) {
        byte[] byteArr = new byte[hexStr.length() / 2];
        char[] charArr = hexStr.toCharArray();

        for(int i = 0; i < byteArr.length; ++i) {
            String item = Character.toString(charArr[i * 2]) + Character.toString(charArr[i * 2 + 1]);
            byteArr[i] = Integer.valueOf(item, 16).byteValue();
        }

        return byteArr;
    }

    public static String makeClassName(String strInput, String delimRegex) {
        if(isEmpty(new Object[]{strInput})) {
            return strInput;
        } else {
            String[] oriStrList = strInput.split(delimRegex);
            String finalStr = "";
            String[] var4 = oriStrList;
            int var5 = oriStrList.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String word = var4[var6];
                finalStr = finalStr + capFirst(word.toLowerCase());
            }

            return finalStr;
        }
    }

    /**
     * 去掉手机号中间的空格,+86和-
     * @param object
     * @return
     */
    public static String formatMobile(Object object){
        if (object == null || "".equals(object)) return null;
        String tel = object.toString();

        //手机号正则
        String reg = "^[0-9\\+]*?1[34578]\\d{9}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = null;

        //过滤-  如 158-517  =》 158517
//        tel = tel.replaceAll("-", "").replaceAll(" ", "").replaceAll("\\u00A0","");
        tel = filterUnNumber(tel);

        //匹配手机号
        matcher = pattern.matcher(tel);
        if(tel.length() >= 11 && matcher.find()){
            tel = tel.substring(tel.length() - 11);
        }
        
        return tel;
    }

    public static String filterUnNumber(String str) {
        // 只允数字  
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        //替换与模式匹配的所有字符（即非数字的字符将被""替换）  
        return m.replaceAll("").trim();

    }

}
