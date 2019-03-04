package com.qywl.ebchat.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：张艳珍 on 2018/12/25 15:40
 * 描述：检验工具类
 */
public class CheckUtils {
    /**
     * 校验用户名或密码
     * @param paramString
     * @return
     */
    public static boolean checkName8_20(String paramString) {
        // 长度为8到18位,由英文、数字组成,且不能为纯数字
        boolean result = Pattern.compile("(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%\\^&*?]{8,20}$").matcher(paramString).matches();
        return result;
    }

    /**
     * 不校验手机号格式注意！！！！！！！！
     * @param paramString
     * @return
     */
    public static boolean chekPhone(String paramString) {

        return true;
    }

    /**
     * 校验手机号
     * @return
     */
  /*  public static boolean checkPhone(String paramString) {
        if (!checkStringNoNull(paramString)){
            return false;
        }
        return Pattern.compile("[1][345678]\\d{9}").matcher(paramString).matches();
    }*/

    public static boolean checkStringWithHtmlTag(String param) {
        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>}
        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>}
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        boolean result = !Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE).matcher(param).matches();
        result = result && !Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE).matcher(param).matches();
        result = result && !Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE).matcher(param).matches();
        result = result && !param.contains("<") && !param.contains(">");
        return result;
    }
    /**
     * 返回true就是包含非法字符，返回false就是不包含非法字符 系统内容过滤规则 1、包含 『 and 1 特殊字符 』， 特殊字符指>,<,=,
     * in , like 字符 2、『 /特殊字符/ 』，特殊字符指 *字符 3、『<特殊字符 script 』特殊字符指空字符 4、『 EXEC 』
     * 5、『 UNION SELECT』 5、『 UPDATE SET』 5、『 INSERT INTO VALUES』 5、『
     * SELECT或DELETE FROM』 5、『CREATE或ALTER或DROP或TRUNCATE TABLE或DATABASE』
     */
    public static boolean isAttack(String input) {
        Log.e("input:",input);
        String regExpr="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pat = Pattern.compile(regExpr);
        Matcher mat = pat.matcher(input);
        boolean rs = mat.find();
        if(rs){
            return false;
        }
        return true;
    }
    public static boolean isAttack2(String input) {
        Log.e("input:",input);
        String regExpr="[`~@#$%^&*()+=|{}\\[\\].<>/~@#￥%……&*（）——+|{}【】]";
        Pattern pat = Pattern.compile(regExpr);
        Matcher mat = pat.matcher(input);
        boolean rs = mat.find();
        if(rs){
            return false;
        }
        return true;
    }
    /**
     * 校验字符串是否为空
     * @param paramString
     * @return
     */
    public static boolean checkStringNoNull(String paramString) {
        return null != paramString && paramString.trim().length() > 0;
    }
}
