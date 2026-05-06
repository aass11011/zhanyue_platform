package com.zym.fastplatform.framework.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    /**
     * 将中文字符串转换为大写拼音首字母字符串
     * @param chinese 中文字符串
     * @return 大写拼音首字母字符串（非中文字符保持原样）
     */
    public static String toUpperFirstLetter(String chinese) {
        if (chinese == null || chinese.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE); // 大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 无音调

        for (char c : chinese.toCharArray()) {
            // 跳过空格
            if (Character.isSpaceChar(c)) {
                continue;
            }

            // 处理中文字符
            if (c >= 0x4e00 && c <= 0x9fa5) { // 中文字符范围
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        // 取第一个拼音的首字母
                        result.append(pinyinArray[0].charAt(0));
                    } else {
                        // 无法转换的生僻字，保持原样
                        result.append(c);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    result.append(c);
                }
            } else {
                // 非中文字符（如字母、数字、符号）保持原样
                result.append(c);
            }
        }

        return result.toString();
    }

    public static Boolean isChinese(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c >= 0x4e00 && c <= 0x9fa5) {
                return true;
            }
        }
        return false;
    }

    public static Boolean containsChinese(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if(isChinese(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
