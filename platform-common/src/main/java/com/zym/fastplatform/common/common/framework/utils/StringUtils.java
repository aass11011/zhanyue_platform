package com.zym.fastplatform.common.common.framework.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 提供字符串处理的常用方法
 */
public class StringUtils {

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 空格字符
     */
    public static final String SPACE = " ";

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 下划线
     */
    public static final String UNDERLINE = "_";

    /**
     * 中划线
     */
    public static final String DASH = "-";

    /**
     * 点
     */
    public static final String DOT = ".";

    /**
     * 左括号
     */
    public static final String LEFT_BRACKET = "(";

    /**
     * 右括号
     */
    public static final String RIGHT_BRACKET = ")";

    /**
     * 左花括号
     */
    public static final String LEFT_BRACE = "{";

    /**
     * 右花括号
     */
    public static final String RIGHT_BRACE = "}";

    /**
     * 左方括号
     */
    public static final String LEFT_SQUARE_BRACKET = "[";

    /**
     * 右方括号
     */
    public static final String RIGHT_SQUARE_BRACKET = "]";

    /**
     * 冒号
     */
    public static final String COLON = ":";

    /**
     * 分号
     */
    public static final String SEMICOLON = ";";

    /**
     * 引号
     */
    public static final String QUOTE = "\"";

    /**
     * 单引号
     */
    public static final String SINGLE_QUOTE = "'";

    /**
     * 反斜杠
     */
    public static final String BACKSLASH = "\\";

    /**
     * 正斜杠
     */
    public static final String SLASH = "/";

    /**
     * 换行符
     */
    public static final String NEWLINE = "\n";

    /**
     * 回车符
     */
    public static final String CARRIAGE_RETURN = "\r";

    /**
     * 制表符
     */
    public static final String TAB = "\t";

    /**
     * 私有构造方法，防止实例化
     */
    private StringUtils() {
        throw new AssertionError("StringUtils should not be instantiated");
    }

    // ==================== 判空方法 ====================

    /**
     * 判断字符串是否为null或空字符串
     *
     * @param str 待判断的字符串
     * @return 如果字符串为null或空字符串，返回true；否则返回false
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为null且不为空字符串
     *
     * @param str 待判断的字符串
     * @return 如果字符串不为null且不为空字符串，返回true；否则返回false
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为null、空字符串或仅包含空白字符
     *
     * @param str 待判断的字符串
     * @return 如果字符串为null、空字符串或仅包含空白字符，返回true；否则返回false
     */
    public static boolean isBlank(CharSequence str) {
        if (isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为null、不为空字符串且不全是空白字符
     *
     * @param str 待判断的字符串
     * @return 如果字符串不为null、不为空字符串且不全是空白字符，返回true；否则返回false
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 判断对象是否为null
     *
     * @param obj 待判断的对象
     * @return 如果对象为null，返回true；否则返回false
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象是否不为null
     *
     * @param obj 待判断的对象
     * @return 如果对象不为null，返回true；否则返回false
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断集合是否为null或空
     *
     * @param collection 待判断的集合
     * @return 如果集合为null或空，返回true；否则返回false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为null且不为空
     *
     * @param collection 待判断的集合
     * @return 如果集合不为null且不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组是否为null或空
     *
     * @param array 待判断的数组
     * @return 如果数组为null或空，返回true；否则返回false
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为null且不为空
     *
     * @param array 待判断的数组
     * @return 如果数组不为null且不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断Map是否为null或空
     *
     * @param map 待判断的Map
     * @return 如果Map为null或空，返回true；否则返回false
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断Map是否不为null且不为空
     *
     * @param map 待判断的Map
     * @return 如果Map不为null且不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    // ==================== 字符串截取方法 ====================



    /**
     * 截取字符串的指定范围
     *
     * @param str 原字符串
     * @param start 开始索引（包含）
     * @param end 结束索引（不包含）
     * @return 截取后的字符串
     */
    public static String substring(String str, int start, int end) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (start < 0) {
            start = 0;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return EMPTY;
        }
        return str.substring(start, end);
    }

    /**
     * 截取字符串，从指定索引开始到字符串结束
     *
     * @param str 原字符串
     * @param start 开始索引（包含）
     * @return 截取后的字符串
     */
    public static String substring(String str, int start) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (start < 0) {
            start = 0;
        }
        if (start >= str.length()) {
            return EMPTY;
        }
        return str.substring(start);
    }

    /**
     * 截取字符串，从开始到指定索引
     *
     * @param str 原字符串
     * @param end 结束索引（不包含）
     * @return 截取后的字符串
     */
    public static String substringBefore(String str, int end) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (end <= 0) {
            return EMPTY;
        }
        if (end >= str.length()) {
            return str;
        }
        return str.substring(0, end);
    }

    /**
     * 截取字符串，从指定字符第一次出现的位置开始到字符串结束
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @return 截取后的字符串
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (isEmpty(separator)) {
            return str;
        }
        int index = str.indexOf(separator);
        if (index == -1) {
            return EMPTY;
        }
        return str.substring(index + separator.length());
    }

    /**
     * 截取字符串，从开始到指定字符第一次出现的位置
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @return 截取后的字符串
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (isEmpty(separator)) {
            return EMPTY;
        }
        int index = str.indexOf(separator);
        if (index == -1) {
            return str;
        }
        return str.substring(0, index);
    }

    /**
     * 截取字符串，从指定字符最后一次出现的位置开始到字符串结束
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @return 截取后的字符串
     */
    public static String substringAfterLast(String str, String separator) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (isEmpty(separator)) {
            return str;
        }
        int index = str.lastIndexOf(separator);
        if (index == -1) {
            return EMPTY;
        }
        return str.substring(index + separator.length());
    }

    /**
     * 截取字符串，从开始到指定字符最后一次出现的位置
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @return 截取后的字符串
     */
    public static String substringBeforeLast(String str, String separator) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (isEmpty(separator)) {
            return EMPTY;
        }
        int index = str.lastIndexOf(separator);
        if (index == -1) {
            return str;
        }
        return str.substring(0, index);
    }

    // ==================== 字符串分割方法 ====================

    /**
     * 分割字符串
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @return 分割后的字符串数组
     */
    public static String[] split(String str, String separator) {
        if (isEmpty(str)) {
            return new String[0];
        }
        if (isEmpty(separator)) {
            return new String[]{str};
        }
        return str.split(separator);
    }

    /**
     * 分割字符串，限制分割次数
     *
     * @param str 原字符串
     * @param separator 分隔符
     * @param limit 限制次数
     * @return 分割后的字符串数组
     */
    public static String[] split(String str, String separator, int limit) {
        if (isEmpty(str)) {
            return new String[0];
        }
        if (isEmpty(separator)) {
            return new String[]{str};
        }
        return str.split(separator, limit);
    }

    /**
     * 分割字符串，使用空白字符作为分隔符
     *
     * @param str 原字符串
     * @return 分割后的字符串数组
     */
    public static String[] splitByWhitespace(String str) {
        if (isEmpty(str)) {
            return new String[0];
        }
        return str.trim().split("\\s+");
    }

    // ==================== 字符串连接方法 ====================

    /**
     * 连接字符串数组
     *
     * @param array 字符串数组
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(Object[] array, String separator) {
        if (isEmpty(array)) {
            return EMPTY;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    /**
     * 连接集合中的元素
     *
     * @param collection 集合
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(Collection<?> collection, String separator) {
        if (isEmpty(collection)) {
            return EMPTY;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object obj : collection) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    /**
     * 重复字符串
     *
     * @param str 原字符串
     * @param count 重复次数
     * @return 重复后的字符串
     */
    public static String repeat(String str, int count) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (count <= 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 填充字符串到指定长度
     *
     * @param str 原字符串
     * @param length 目标长度
     * @param padChar 填充字符
     * @param left 是否左填充
     * @return 填充后的字符串
     */
    public static String pad(String str, int length, char padChar, boolean left) {
        if (str == null) {
            str = EMPTY;
        }
        if (str.length() >= length) {
            return str;
        }
        int padLength = length - str.length();
        StringBuilder sb = new StringBuilder();
        if (left) {
            sb.append(repeat(String.valueOf(padChar), padLength));
            sb.append(str);
        } else {
            sb.append(str);
            sb.append(repeat(String.valueOf(padChar), padLength));
        }
        return sb.toString();
    }

    /**
     * 左填充字符串到指定长度
     *
     * @param str 原字符串
     * @param length 目标长度
     * @param padChar 填充字符
     * @return 填充后的字符串
     */
    public static String leftPad(String str, int length, char padChar) {
        return pad(str, length, padChar, true);
    }

    /**
     * 右填充字符串到指定长度
     *
     * @param str 原字符串
     * @param length 目标长度
     * @param padChar 填充字符
     * @return 填充后的字符串
     */
    public static String rightPad(String str, int length, char padChar) {
        return pad(str, length, padChar, false);
    }



    /**
     * 检查字符串是否为数字
     *
     * @param str 原字符串
     * @return 如果字符串是数字，返回true；否则返回false
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符串是否为有效的邮箱地址
     *
     * @param str 原字符串
     * @return 如果字符串是有效的邮箱地址，返回true；否则返回false
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(str).matches();
    }

    /**
     * 检查字符串是否为有效的手机号码
     *
     * @param str 原字符串
     * @return 如果字符串是有效的手机号码，返回true；否则返回false
     */
    public static boolean isPhone(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String phoneRegex = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(str).matches();
    }

    /**
     * 检查字符串是否为有效的URL
     *
     * @param str 原字符串
     * @return 如果字符串是有效的URL，返回true；否则返回false
     */
    public static boolean isUrl(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String urlRegex = "^(https?:\\/\\/)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*\\/?$";
        Pattern pattern = Pattern.compile(urlRegex);
        return pattern.matcher(str).matches();
    }

    /**
     * 从字符串中提取数字
     *
     * @param str 原字符串
     * @return 提取的数字字符串
     */
    public static String extractNumber(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        return str.replaceAll("\\D+", "");
    }

    /**
     * 从字符串中提取字母
     *
     * @param str 原字符串
     * @return 提取的字母字符串
     */
    public static String extractLetter(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        return str.replaceAll("[^a-zA-Z]+", "");
    }

    /**
     * 从字符串中提取汉字
     *
     * @param str 原字符串
     * @return 提取的汉字字符串
     */
    public static String extractChinese(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        return str.replaceAll("[\\u0000-\\u00ff]+", "");
    }

    /**
     * 隐藏字符串的中间部分
     *
     * @param str 原字符串
     * @param start 开始保留的长度
     * @param end 结束保留的长度
     * @param mask 掩码字符
     * @return 隐藏中间部分后的字符串
     */
    public static String mask(String str, int start, int end, char mask) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        if (start + end >= str.length()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, start));
        sb.append(repeat(String.valueOf(mask), str.length() - start - end));
        sb.append(str.substring(str.length() - end));
        return sb.toString();
    }

    /**
     * 隐藏手机号中间4位
     *
     * @param phone 手机号
     * @return 隐藏中间4位后的手机号
     */
    public static String maskPhone(String phone) {
        if (isEmpty(phone) || phone.length() != 11) {
            return phone;
        }
        return mask(phone, 3, 4, '*');
    }

    /**
     * 隐藏邮箱
     *
     * @param email 邮箱
     * @return 隐藏后的邮箱
     */
    public static String maskEmail(String email) {
        if (isEmpty(email)) {
            return email;
        }
        int atIndex = email.indexOf('@');
        if (atIndex <= 2) {
            return email;
        }
        return email.substring(0, 2) + repeat("*", atIndex - 2) + email.substring(atIndex);
    }

    /**
     * 隐藏身份证号中间部分
     *
     * @param idCard 身份证号
     * @return 隐藏中间部分后的身份证号
     */
    public static String maskIdCard(String idCard) {
        if (isEmpty(idCard) || idCard.length() < 10) {
            return idCard;
        }
        return mask(idCard, 3, 4, '*');
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public static String getFileExtension(String fileName) {
        if (isEmpty(fileName)) {
            return EMPTY;
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return EMPTY;
        }
        return fileName.substring(lastDotIndex + 1);
    }

    /**
     * 获取文件名（不含扩展名）
     *
     * @param fileName 文件名
     * @return 文件名（不含扩展名）
     */
    public static String getFileNameWithoutExtension(String fileName) {
        if (isEmpty(fileName)) {
            return EMPTY;
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }
}